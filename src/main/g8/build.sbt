// give the user a nice default project!
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "geotrellis",
      scalaVersion := "2.11.8"
    )),
    name := "geotrellis-spark-batch",
    libraryDependencies ++= Seq(
      "org.locationtech.geotrellis" %% "geotrellis-spark" % "2.1.0",
      "org.locationtech.geotrellis" %% "geotrellis-s3" % "2.1.0",
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
