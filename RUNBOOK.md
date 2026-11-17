# Руководство по работе с проектом PRR9 Auth

Документ содержит инструкции по сборке, тестированию и диагностике типичных проблем проекта.

## 📋 Системные требования

- **Android Studio:** Iguana, Jellyfish, Ladybug или более новые версии.
- **JDK:** JetBrains Runtime (JBR) 17 (проверка: `File → Project Structure → SDK Location`).
- **Gradle:** Wrapper версии 8.7.x или 8.9.x.

## 🛠 Команды сборки (через Terminal)

```bash
# Очистка артефактов предыдущих сборок (рекомендуется при возникновении странных ошибок)
./gradlew clean

# Полная пересборка проекта
./gradlew build

# Запуск Unit-тестов для проверки Domain-слоя
./gradlew test