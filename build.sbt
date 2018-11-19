// This build is for this Giter8 template.
// To test the template run `g8` or `g8Test` from the sbt session.
// See http://www.foundweekends.org/giter8/testing.html#Using+the+Giter8Plugin for more details.
lazy val root = (project in file(".")).
  settings(
    name := "geotrellis-spark-batch",
    scalaVersion := "2.11.12",
    scalaVersion in ThisBuild := "2.11.12",
    crossScalaVersions := Seq(scalaVersion.value, "2.12.7"),
    test in Test := {
      val _ = (g8Test in Test).toTask("").value
    },
    //scriptedLaunchOpts ++= List("-Xms1024m", "-Xmx1024m", "-XX:ReservedCodeCacheSize=128m", "-Xss2m", "-Dfile.encoding=UTF-8"),
    libraryDependencies ++= Seq(
      "org.locationtech.geotrellis" %% "geotrellis-spark" % "2.1.0",
      "org.locationtech.geotrellis" %% "geotrellis-s3" % "2.1.0",
      "org.locationtech.geotrellis" %% "geotrellis-accumulo" % "2.1.0",
      "org.locationtech.geotrellis" %% "geotrellis-hbase" % "2.1.0",
      "org.locationtech.geotrellis" %% "geotrellis-cassandra" % "2.1.0",
      "com.monovore" %% "decline" % "0.5.0",
      "org.apache.spark" %% "spark-core" % "2.3.0" % "provided"
    ),
    resolvers ++= Seq(
      "LocationTech Snapshots" at "https://repo.locationtech.org/content/groups/snapshots",
      "LocationTech Releases" at "https://repo.locationtech.org/content/groups/releases",
      DefaultMavenRepository,
      Resolver.url("typesafe", url("http://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns)
    )
  )
