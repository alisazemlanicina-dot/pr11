package com.example.pr11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfig

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Включаем Crashlytics (Мониторинг)
        Firebase.crashlytics.setCrashlyticsCollectionEnabled(true)

        // 2. Настраиваем Remote Config
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(mapOf("welcome_text" to "Привет из кода!"))

        // Получаем текст с сервера (если он там задан)
        remoteConfig.fetchAndActivate()

        setContent {
            MaterialTheme {
                MainScreen(remoteConfig)
            }
        }
    }
}

@Composable
fun MainScreen(remoteConfig: FirebaseRemoteConfig) {
    // Достаем строку из Remote Config
    val welcomeText = remoteConfig.getString("welcome_text")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = welcomeText, style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                // Кнопка для теста Crashlytics (искусственное падение)
                throw RuntimeException("Тестовый краш для Crashlytics")
            }) {
                Text("Тест Crashlytics")
            }
        }
    }
}