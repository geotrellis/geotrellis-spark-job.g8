// This build is for this Giter8 template.
// To test the template run `g8` or `g8Test` from the sbt session.
// See http://www.foundweekends.org/giter8/testing.html#Using+the+Giter8Plugin for more details.
lazy val root = (project in file(".")).
  settings(
    name := "geotrellis-spark-batch",
    scalaVersion := "2.11.8",
    scalaVersion in ThisBuild := "2.11.8"//,
    /*
    test in Test := {
      val _ = (g8Test in Test).toTask("").value
    }
    */
  )
