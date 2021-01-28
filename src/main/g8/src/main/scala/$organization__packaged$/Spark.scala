package $organization$

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

import scala.util.Properties

object Spark {
  def conf: SparkConf = new SparkConf()
    .setIfMissing("spark.master", "local[*]")
    .setAppName("$name$")
    .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    .set("spark.kryo.registrator", "geotrellis.spark.store.kryo.KryoRegistrator")
    .set("spark.executionEnv.AWS_PROFILE", Properties.envOrElse("AWS_PROFILE", "default"))

  implicit val session: SparkSession = SparkSession.builder.config(conf).enableHiveSupport.getOrCreate
}
