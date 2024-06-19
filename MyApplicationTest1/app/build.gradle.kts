plugins {
    alias(libs.plugins.androidApplication)
    id("com.google.gms.google-services") version "4.4.1"
}

android {
    namespace = "com.example.myapplicationtest1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplicationtest1"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    packaging {
        resources {
            exclude("META-INF/spring.tooling")
            exclude("META-INF/spring.handlers")
            exclude("META-INF/spring-configuration-metadata.json")
            exclude("META-INF/additional-spring-configuration-metadata.json")
            exclude("META-INF/spring.factories")
            exclude("META-INF/spring/aot.factories")
            exclude("META-INF/spring.schemas")
            exclude("META-INF/license.txt")
            exclude("META-INF/notice.txt")
            exclude("META-INF/DEPENDENCIES")
            exclude("META-INF/INDEX.LIST")
        }
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.amqp.client)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
}