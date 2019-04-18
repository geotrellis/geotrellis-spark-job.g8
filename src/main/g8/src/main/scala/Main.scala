package geotrellis.batch

import geotrellis.raster._
import geotrellis.raster.histogram.Histogram
import geotrellis.raster.io._
import geotrellis.spark._
import geotrellis.spark.pyramid.Pyramid
import geotrellis.spark.tiling.ZoomedLayoutScheme
import geotrellis.spark.io._
import geotrellis.spark.io.index.ZCurveKeyIndexMethod
import geotrellis.spark.io.kryo.KryoRegistrator
import geotrellis.proj4.WebMercator

import cats.implicits._
import com.monovore.decline._

import org.apache.spark._
import org.apache.spark.rdd._


object Main extends CommandApp(
  name = "spark-batch-job",
  header = "Creates a Spark batch job",
  main = {
    val inputsOpt = Opts.options[String]("inputPath", help = "The path that points to data that will be read")
    val nameOpt = Opts.option[String]("name", help = "The name of the output layer")
    val zoomOpt = Opts.option[Int]("zoom", help = "The max zoom level the catalog should be saved as").withDefault(13)
    val numPartitionsOpt = Opts.option[Int]("numPartitions", help = "The number of partitions to use").orNone
    val outputOpt = Opts.option[String]("outputPath", help = "The path of the output catlaog")

    (inputsOpt, nameOpt, zoomOpt, numPartitionsOpt, outputOpt).mapN { (inputs, name, zoom, numPartitions, output) =>
      val conf = new SparkConf()
        .setIfMissing("spark.master", "local[*]")
        .setAppName("GeoTrellis Batch Job")
        .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
        .set("spark.kryo.registrator", "geotrellis.spark.io.kryo.KryoRegistrator")
        .set("spark.executionEnv.AWS_PROFILE", Properties.envOrElse("AWS_PROFILE", "default"))

      implicit val ss: SparkSession = SparkSession.builder.config(conf).enableHiveSupport.getOrCreate
      implicit val sc: SparkContext = ss.sparkContext

      try {
        val tileLayer: MultibandTileLayerRDD[SpatialKey] = ProcessInputs(inputs.toList, zoom, numPartitions)

        tileLayer.persist()

        val pyramid: Stream[(Int, MultibandTileLayerRDD[SpatialKey])] =
          Pyramid.levelStream(tileLayer, ZoomedLayoutScheme(WebMercator), startZoom = zoom, endZoom = 0)

        val layerWriter: LayerWriter[LayerId] = LayerWriter(output)

        // TODO: Save a histogram for each band.
        // Currently, giter8 has problems creatings templates that
        // have string interpolation in its code.
        // See this issue for more info: https://github.com/foundweekends/giter8/issues/333

        /*
        val histograms: Array[Histogram[Double]] = tileLayer.histogram

        histograms.zipWithIndex.foreach { case (hist, index) =>
          layerWriter
            .attributeStore
            .write[Histogram[Double]](LayerId(name, zoom), s"band\_\${index}\_histogram", hist)
        }
        */

        pyramid.foreach { case (z, layer) =>
          layerWriter.write(LayerId(name, z), layer, ZCurveKeyIndexMethod)
        }

        tileLayer.unpersist()

      } catch {
        case e: Exception => throw e
      } finally {
        sc.stop()
      }
    }
  }
)
