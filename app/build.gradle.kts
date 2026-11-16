plugins {
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("com.google.firebase.crashlytics")
    id("org.jetbrains.kotlin.plugin.compose")
    // ДОБАВЬ ЭТУ СТРОКУ:
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.pr11"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.pr11"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file(System.getenv("KEYSTORE_PATH") ?: "keystore.jks")
            storePassword = System.getenv("KEYSTORE_PASSWORD") ?: "123456"
            keyAlias = System.getenv("KEY_ALIAS") ?: "pr11-key"
            keyPassword = System.getenv("KEY_PASSWORD") ?: "123456"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
    // ВАЖНО: Блок composeOptions УДАЛЕН. Плагин Compose Compiler сам управляет версией.
}

dependencies {
    // Compose
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.9.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-config")

    // Тесты
    testImplementation("junit:junit:4.13.2")
}