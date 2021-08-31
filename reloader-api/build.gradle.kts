plugins {
    `maven-publish`
    signing
}

group = rootProject.group
version = rootProject.version

tasks {
    create<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    create<Jar>("javadocJar") {
        archiveClassifier.set("javadoc")
        from(javadoc)
    }
}


publishing {
    publications {
        create<MavenPublication>("${rootProject.name}-api") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            repositories {
                maven {
                    name = "MavenCentral"
                    val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
                    val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                    url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)

                    credentials.runCatching {
                        val MAVEN_UPLOAD_USER: String = (project.properties["ossUserName"] as String?)!!
                        val MAVEN_UPLOAD_PWD: String = (project.properties["ossPassword"] as String?)!!
                        username = MAVEN_UPLOAD_USER
                        password = MAVEN_UPLOAD_PWD
                    }
                }

                pom {
                    name.set(rootProject.name)
                    description.set("This is minecraft reload plugin api")
                    url.set("https://github.com/Netherald/reloader")
                    licenses {
                        license {
                            name.set("GNU General Public License Version 3")
                            url.set("https://www.gnu.org/licenses/gpl-3.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("Netherald")
                            name.set("Netherald")
                            email.set("me@projecttl.net")
                        }
                    }
                    scm {
                        connection.set("scm:git:https://github.com/Netherald/reloader.git")
                        developerConnection.set("scm:git:https://github.com/Netherald/reloader.git")
                        url.set("https://github.com/Netherald/reloader.git")
                    }
                }
            }
        }
    }
}

signing {
    isRequired = true
    sign(publishing.publications["${rootProject.name}-api"])
}
