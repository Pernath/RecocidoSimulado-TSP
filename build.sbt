import Dependencies._

lazy val root = (project in file("."))
  .settings(
    name := "Prueba",
    scalaVersion := "2.12.1",
    libraryDependencies += scalaTest % Test,
    retrieveManaged := true	
  )
