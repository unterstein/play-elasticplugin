name := "play-elasticplugin-examples"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "com.github.unterstein" %% "play-elasticplugin" % "0.1.0-SNAPSHOT",
  "org.webjars" % "bootstrap" % "3.2.0"

)


resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers += "unterstein.github.io" at "http://unterstein.github.io/repo"

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
