ThisBuild / organization := "jahubba"
ThisBuild / scalaVersion := "2.11.8"

lazy val root = (project in file("."))
  .settings(
    name := "DatabricksJobServer",
    resolvers += "job-server rpo" at "https://dl.bintray.com/spark-jobserver/maven/",
    libraryDependencies += "spark.jobserver" % "job-server_2.11" % "0.7.0" % Provided,
    libraryDependencies += "org.apache.spark" % "spark-sql_2.11" % "2.1.1" % Provided
  )
