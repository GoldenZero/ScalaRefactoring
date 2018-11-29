val scalaTestVersion = "3.0.5"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      scalaVersion := "2.12.6",
      version := "0.1.0-SNAPSHOT",
      libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % scalaTestVersion % Test))),
    name := "test"
)
