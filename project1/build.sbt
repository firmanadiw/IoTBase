name := "project1"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  javaJpa,
  cache
)     

play.Project.playJavaSettings

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.46"