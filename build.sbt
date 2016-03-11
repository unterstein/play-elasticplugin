name := "play-elasticplugin"

version := "0.8.0-SNAPSHOT"

organization := "com.github.unterstein"

resolvers ++= Seq(
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Spring releases" at "http://repo.springsource.org/release",
  "Spring milestones" at "http://repo.spring.io/milestone",
  "Spring snapshots" at "http://repo.spring.io/snapshot",
  "Local Maven" at "file://" + Path.userHome.absolutePath + "/.m2/repository"
)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.5.0",
  "com.typesafe.play" %% "play-java" % "2.5.0",

  // spring data stuff
  "org.springframework.data" % "spring-data-elasticsearch" % "1.4.0.BUILD-SNAPSHOT",

  // elastic stuff
  "org.elasticsearch" % "elasticsearch" % "2.2.0",

  // Inject stuff
  "javax.inject" % "javax.inject" % "1"
)

publishTo <<= version {
  case v if v.trim.endsWith("SNAPSHOT") => Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))
  case _ => Some(Resolver.file("Github Pages",  new File("../unterstein.github.io/repo")))
}
