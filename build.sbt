name := """pixarus"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  javaJdbc,
  javaEbean,
  cache,
  javaWs
)
