pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://dl.bintray.com/ekito/koin")
    }
}


rootProject.name = "RickAndMorty"
include(":androidApp")
include(":shared")