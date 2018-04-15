import com.github.play2war.plugin._

name := "IoTBase"

version := "1.0"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.46",
  "org.mindrot" % "jbcrypt" % "0.4"
)

play.Project.playJavaSettings

Play2WarPlugin.play2WarSettings

Play2WarKeys.servletVersion := "3.0"

// Konfigurasi Build Project Ke WAR File
// -----------------------------------------------------------
// Play2WarKeys.servletVersion := "2.5"
// Untuk Server Tomcat 7, dan Jetty 7
// -----------------------------------------------------------
// Play2WarKeys.servletVersion := "3.0"
// Untuk Server Tomcat 7, Jetty 8, Jetty 9.0, JBoss 7.0
// JBoss 7.1, Glassfish 3, Websphere Community Edition 3.0,
// dan Websphere 8.5 Liberty Profile
// -----------------------------------------------------------
// Play2WarKeys.servletVersion := "3.1"
// Untuk Server Tomcat 8, Jetty 9.1, JBoss Wildfly 8,
// dan Glassfish 4
// -----------------------------------------------------------
// Untuk build ke war file gunakan perintah :
// play war
