name := "$name$"
organization := "$organization$"
version := "0.1.0"

scalaVersion := "$scala_version$"

libraryDependencies ++= Seq(
  "com.monovore" %% "decline" % "0.6.2",
  "org.locationtech.geotrellis" %% "geotrellis-spark" % "$geotrellis_version$",
  "org.locationtech.geotrellis" %% "geotrellis-s3" % "$geotrellis_version$",
  "org.apache.spark" %% "spark-core" % "$spark_version$" % Provided
)

resolvers ++= Seq(
  "LocationTech Snapshots" at "https://repo.locationtech.org/content/groups/snapshots",
  "LocationTech Releases" at "https://repo.locationtech.org/content/groups/releases",
  Resolver.url("typesafe", url("http://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns)
)