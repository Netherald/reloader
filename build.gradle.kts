plugins {
    groovy
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "net.projecttl"
version = "0.1.0"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "groovy")
    apply(plugin = "com.github.johnrengelman.shadow")

    repositories {
        mavenCentral()
        maven("https://papermc.io/repo/repository/maven-public/")
    }

    dependencies {
        implementation("org.codehaus.groovy:groovy-all:3.0.8")
        compileOnly("com.destroystokyo.paper:paper-api:1.12.2-R0.1-SNAPSHOT")
    }
}