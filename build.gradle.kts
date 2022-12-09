import nebula.plugin.release.git.opinion.Strategies

plugins {
  id("otel.java-conventions")
  id("java-library")
  id("otel.publish-conventions")
  id("nebula.release")
}

release {
  defaultVersionStrategy = Strategies.getSNAPSHOT()
}

tasks {
  named("release") {
    mustRunAfter("snapshotSetup", "finalSetup")
  }
}

repositories {
  mavenCentral()
  mavenLocal()
}

dependencies {
  implementation(project(":proto"))
}

group = "io.opentelemetry"

tasks.jar {
  from(sourceSets.main.get().output)
  dependsOn(configurations.runtimeClasspath)
  from({
    configurations.runtimeClasspath.get().filter { it.name.equals("proto.jar") }.map { zipTree(it) }
  })
  duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
