# 🛠️ Проект для автоматизированного тестирования API и UI 🧪

Добро пожаловать в репозиторий с тестовым заданием по автоматизированному тестированию API и UI! Этот проект создан для демонстрации уровня навыков работы с инструментами для тестирования REST API с использованием **RestAssured** и автоматизации UI с помощью **Selenium/Selenide**.

Проект включает два основных задания:
1. Тестирование API с помощью RestAssured.
2. Тестирование UI с использованием Selenium и Selenide.

---

## 📝 Описание задания

### 🔧 Задание 1 — API тесты (RestAssured)

**API для тестирования:** [Reqres.in](https://reqres.in/)

#### Кейсы:
1. **Регистрация пользователя:**
    - ✅ Успешная регистрация с валидными данными.
    - ❌ Регистрация с ошибкой из-за отсутствия пароля (проверка статуса кода `400`).

2. **Получение списка пользователей (страница 2):**
    - Получение списка пользователей.
    - Убедиться, что email всех пользователей заканчиваются на `@reqres.in`.

3. **Удаление второго пользователя:**
    - Удаление пользователя и проверка, что статус код в ответе `204`.

4. **Обновление информации о пользователе:**
    - Обновление данных пользователя с помощью метода `PATCH`.
    - Проверка, что дата обновления совпадает с текущей датой системы.

**Технологии:**
- **RestAssured** для работы с API.
- **JUnit 5** для написания тестов.
- **Hamcrest** для написания утверждений.

Все тесты реализованы в классе `ApiTests.java`, включая использование **Request/Response Specifications** для удобной конфигурации запросов.

### 💻 Задание 2 — UI тесты (Selenium/Selenide)

**UI для тестирования:** [Demoblaze](https://www.demoblaze.com/)

#### Кейсы:
1. **Регистрация и авторизация:**
    - Использование библиотеки для генерации тестовых данных.
    - Регистрация с тестовыми данными.
    - Авторизация с этими данными.

2. **Работа с корзиной:**
    - Добавление в корзину по одному товару из каждой категории.
    - Сравнение цен на товары на карточке и в общем списке.
    - Переход в корзину и проверка, что общая сумма рассчитана корректно.

3. **Оформление заказа:**
    - Оформление заказа и проверка, что дата заказа совпадает с текущей системной датой.

**Технологии:**
- **Selenium/Selenide** для взаимодействия с веб-страницей.
- **JUnit 5** для организации тестов.
- Использование паттерна **Page Object** и **Page Factory** для построения структуры тестов.

UI тесты реализованы в классе `FullUITest.java`.

---

## 📂 Структура проекта

```plaintext
src
├── main
│   └── java
│       └── com
│           └── apiTests
│               └── UIPages
│                   ├── CartPage.java
│                   ├── CategoryPage.java
│                   ├── CheckoutPage.java
│                   ├── HomePage.java
│                   ├── LoginPage.java
│               └── Main.java
├── test
│   └── java
│       └── com
│           └── apiTests
│               ├── ApiTests.java
│               ├── BaseTest.java
│               └── FullUITest.java
pom.xml
README.md
```
## Описание папок:
- `src/main/java/com/apiTests/UIPages` — страницы для UI тестов с реализацией Page Object.
- `src/test/java/com/apiTests/ApiTests.java` — тесты для API.
- `src/test/java/com/apiTests/FullUITest.java` — тесты для UI.
- `src/test/java/com/apiTests/BaseTest.java` — базовый класс для UI тестов, содержащий общие настройки.

## 🚀 Как запустить проект?

### 🛠️ Запуск тестов через Maven:

Для запуска всех тестов (API и UI), выполните команду:

```bash
mvn clean test
```

Если хотите выполнить только API тесты:
```bash
mvn -Dtest=ApiTests test
```

Для выполнения только UI тестов:
```bash
mvn -Dtest=FullUITest test
```

## Требования для запуска:
- **Java** (рекомендуется версия 17 и выше).
- **Maven** для сборки и управления зависимостями.

## Установка зависимостей:
После клонирования репозитория выполните команду:

```bash
mvn clean install
```
## 🛠️ Используемые технологии:
- **Java 17** — язык программирования.
- **Maven** — система управления проектом.
- **RestAssured** — библиотека для тестирования REST API.
- **JUnit 5** — тестовый фреймворк.
- **Selenium/Selenide** — библиотека для автоматизации UI тестов.
- **Faker** — генерация тестовых данных для регистрации пользователей.

## 📝 Заключение:
Этот проект демонстрирует примеры использования **RestAssured** для API тестов и **Selenium/Selenide** для тестирования UI с использованием паттерна **Page Object**. Все тесты автоматизированы и могут быть легко запущены с помощью Maven.

Надеюсь, этот проект поможет оценить мои навыки работы с инструментами автоматизации тестирования! 😄

## 💻 Контакты:
Если у вас возникнут вопросы, не стесняйтесь связаться со мной через [Телеграм](https://t.me/SyBeRGEN)!
