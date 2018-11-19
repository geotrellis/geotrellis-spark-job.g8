import sbt._

libraryDependencies += {
  //"org.scala-sbt" %% "scripted-plugin" % "1.0.0-M4",
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, scalaMajor)) =>
      println(s"\n\n !!!!! This is the scalaMajor: $scalaMajor")
      println(s"\n\n !!!!! This is the scalaVersion.value: ${scalaVersion.value}")
      if (scalaMajor >= 12)
        "org.scala-sbt" %% "scripted-plugin" % "1.1.6"
      else
        "org.scala-sbt" %% "scripted-plugin" % "1.0.0-M4"
    case None =>
        println(s"\n\n\n !!!!!!!!! Nothing was found"); "org.scala-sbt" %% "scripted-plugin" % "1.0.0-M4"
  }
}

addSbtPlugin("org.foundweekends.giter8" %% "sbt-giter8" % "0.12.0-M1")
