name := "playbackendsecretmessage"

version := "1.0"

lazy val `playbackendsecretmessage` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( javaJdbc ,  cache , javaWs, javaJpa,
  "mysql" % "mysql-connector-java" % "5.1.41",
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final"
)

routesGenerator := InjectedRoutesGenerator

fork in run := true


