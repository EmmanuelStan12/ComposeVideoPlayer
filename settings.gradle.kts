pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "ComposeVideoPlayer"
include(":app")
include(":libraries:room-lib")
include(":libraries:ui-base-lib")
include(":features:base")
include(":features:home")
include(":libraries:storage-lib")
include(":libraries:data-lib")
include(":libraries:utils-base-lib")
