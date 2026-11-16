plugins {
    id("com.android.application") version "9.0.1" apply false
    id("com.google.devtools.ksp") version "2.3.6" apply false
    id("com.google.firebase.crashlytics") version "3.0.2" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.10" apply false
    // ДОБАВЬ ЭТУ СТРОКУ:
    id("com.google.gms.google-services") version "4.4.2" apply false
}