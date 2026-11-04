import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

fun signingProperties(name: String) = Properties().apply {
    val propertiesFile = rootProject.file("keys/$name.properties")
    if (propertiesFile.exists()) {
        propertiesFile.inputStream().use { load(it) }
    }
}

val releaseProps = signingProperties("release")
val apklistProps = signingProperties("apklist")

android {
    namespace = "com.albertolicea00.force4g"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.albertolicea00.force4g"
        minSdk = 16
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
    }

    signingConfigs {
        create("apklist") {
            storeFile = rootProject.file("keys/apklist.jks")
            storePassword = apklistProps.getProperty("storePassword")
            keyAlias = apklistProps.getProperty("keyAlias", "albertolicea00")
            keyPassword = apklistProps.getProperty("keyPassword")
        }
        create("release") {
            storeFile = rootProject.file("keys/release.jks")
            storePassword = releaseProps.getProperty("storePassword")
            keyAlias = releaseProps.getProperty("keyAlias", "albertolicea00")
            keyPassword = releaseProps.getProperty("keyPassword")
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
        create("apklist") {
            initWith(getByName("release"))
            signingConfig = signingConfigs.getByName("apklist")
            matchingFallbacks += "release"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    applicationVariants.all {
        if (buildType.name == "release" || buildType.name == "apklist") {
            val apkName = "4gforce-v${versionName}-${buildType.name}.apk"
            outputs.all {
                (this as BaseVariantOutputImpl).outputFileName = apkName
            }
        }
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")
}
