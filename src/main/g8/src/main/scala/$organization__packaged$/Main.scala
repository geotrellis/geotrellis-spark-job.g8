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

object  Main {
  @transient private[this] lazy val logger = getLogger

  private val inputsOpt = Opts.options[String]("inputPath", help = "The path that points to data that will be read")
  private val outputOpt = Opts.option[String]("outputPath", help = "The path of the output tiffs")
  private val partitionsOpt =  Opts.option[Int]("numPartitions", help = "The number of partitions to use").orNone

  private val command = Command(name = "$name$", header = "GeoTrellis App: $name$") {
    (inputsOpt, outputOpt, partitionsOpt).tupled
  }

  def main(args: Array[String]): Unit = {
    command.parse(args, sys.env) match {
      case Left(help) =>
        System.err.println(help)
        sys.exit(1)

      case Right((i, o, p)) =>
        run(i.toList, o, p)
    }
  }

  def run(inputs: List[String], output: String, numPartitions: Option[Int]): Unit = {
    implicit val sc = Spark.session.sparkContext

    try {
      // Job logic
    } finally {
      Spark.session.stop()
    }
  }
}
