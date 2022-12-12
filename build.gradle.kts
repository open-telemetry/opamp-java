plugins {
  id("otel.java-conventions")
  id("java-library")
  id("otel.publish-conventions")
  id("nebula.release")
  id("io.github.gradle-nexus.publish-plugin")
}
apply(from = "version.gradle.kts")

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
