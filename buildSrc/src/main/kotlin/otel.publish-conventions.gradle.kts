import java.time.Duration
plugins {
    `maven-publish`
    signing
}

publishing {
    publications {
        register<MavenPublication>("maven") {
            afterEvaluate {
                artifactId = base.archivesName.get()
                pom.description.set(project.description)
            }

            from(components["java"])
            versionMapping {
                allVariants {
                    fromResolutionResult()
                }
            }

            pom {
                name.set("OpenTelemetry Protocol")
                url.set("https://github.com/open-telemetry/opamp-java")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("opentelemetry")
                        name.set("OpenTelemetry")
                        url.set("https://github.com/open-telemetry/community")
                    }
                }

                scm {
                    connection.set("scm:git:git@github.com:open-telemetry/opamp-java.git")
                    developerConnection.set("scm:git:git@github.com:open-telemetry/opamp-java.git")
                    url.set("git@github.com:open-telemetry/opamp-java.git")
                }
            }
        }
    }
    repositories {
        maven {
            val releasesRepoUrl = layout.buildDirectory.dir("repos/releases")
            val snapshotsRepoUrl = layout.buildDirectory.dir("repos/snapshots")
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
        }
    }
}

