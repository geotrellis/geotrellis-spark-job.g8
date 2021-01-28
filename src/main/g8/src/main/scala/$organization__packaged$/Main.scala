package $organization$

import geotrellis.layer._
import geotrellis.vector._
import geotrellis.proj4._
import geotrellis.raster._
import geotrellis.spark._

import cats.implicits._
import com.monovore.decline._

import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.rdd._

import org.log4s._

object Main extends CommandApp(
  name = "$name$",
  header = "GeoTrellis Spark: $name$",
  main = {
    @transient val logger = getLogger

    val inputsOpt = Opts.options[String]("inputPath", help = "The path that points to data that will be read")
    val outputOpt = Opts.option[String]("outputPath", help = "The path of the output catlaog")
    val numPartitionsOpt = Opts.option[Int]("numPartitions", help = "The number of partitions to use").orNone

    (inputsOpt, outputOpt, numPartitionsOpt).mapN { (inputs, output, numPartitions) =>
      implicit val sc = Spark.session.sparkContext

      try {
        // Job logic ...
      } finally {
        Spark.session.stop()
      }
    }
  }
)
