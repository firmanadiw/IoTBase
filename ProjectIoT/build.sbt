name := "ProjectIoT"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  javaJpa,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.46",
  "org.mindrot" % "jbcrypt" % "0.4"
)     

play.Project.playJavaSettings
