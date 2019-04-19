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
import org.apache.spark.sql.SparkSession
import org.apache.spark.rdd._

import scala.util.Properties

object Main extends CommandApp(
  name = "$name$",
  header = "Creates a Spark batch job",
  main = {
    val inputsOpt = Opts.options[String]("inputPath", help = "The path that points to data that will be read")
    val outputOpt = Opts.option[String]("outputPath", help = "The path of the output catlaog")
    val numPartitionsOpt = Opts.option[Int]("numPartitions", help = "The number of partitions to use").orNone

    (inputsOpt, outputOpt, numPartitionsOpt).mapN { (inputs, output, numPartitions) =>
      val conf = new SparkConf()
        .setIfMissing("spark.master", "local[*]")
        .setAppName("GeoTrellis Batch Job")
        .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
        .set("spark.kryo.registrator", "geotrellis.spark.io.kryo.KryoRegistrator")
        .set("spark.executionEnv.AWS_PROFILE", Properties.envOrElse("AWS_PROFILE", "default"))

      implicit val spark = SparkSession.builder.config(conf).enableHiveSupport.getOrCreate
      implicit val sc = spark.sparkContext

      // Job logic ...

      spark.stop()
    }
  }
)
