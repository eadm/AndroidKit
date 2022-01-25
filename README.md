# Android Kit
Фреймворк для Android разработки

## Использование

В корневом `build.gradle` необходимо подключить репозиторий
```groovy
allprojects {
    repositories {
        maven { 
            url "https://maven.pkg.github.com/eadm/AndroidKit"
            credentials {
                username = System.getenv('GITHUB_USER') ?: project.properties['GITHUB_USER']
                password = System.getenv('GITHUB_PERSONAL_ACCESS_TOKEN') ?: project.properties['GITHUB_PERSONAL_ACCESS_TOKEN']
            }
        }
    }
}
```

Далее необходимо [сгенерировать GitHub Token](https://github.com/settings/tokens/new) с разрешением `read:packages` и поместить его в корневой файл `gradle.properties` (обычно лежит в `~/.gradle/gradle.properties`).
```
GITHUB_USER=YOUR_GITHUB_USER_NAME
GITHUB_PERSONAL_ACCESS_TOKEN=YOUR_GITHUB_ACCESS_TOKEN
```

# Артефакты

### Model
Набор базовых классов и расширений для моделей

![](https://img.shields.io/static/v1?label=&message=KMM&color=blueviolet)
![](https://img.shields.io/static/v1?label=core:model&message=1.0.7&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.app.core:model:1.0.7")
}
```

### domain-rx

Расширения для работы с RxJava

![](https://img.shields.io/static/v1?label=&message=android&color=green)

```groovy
dependencies {
    implementation 'ru.nobird.android.domain:rx:x.y.z'
}
```

## Presentation

### presentation-base
База для presentation слоя

![](https://img.shields.io/static/v1?label=&message=android&color=green)

```groovy
dependencies {
    implementation 'ru.nobird.android.presentation:base:1.1.0'
}
```

### presentation-redux
База для presentation-redux слоя, является альтернативой presentation-base

![](https://img.shields.io/static/v1?label=&message=KMM&color=blueviolet)
![](https://img.shields.io/static/v1?label=presentation:presentation-redux&message=1.3.0&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.app.presentation:presentation-redux:1.3.0")
}
```

### presentation-redux-coroutines
Набор расширений для presentation-redux архитектуры с корутинами

![](https://img.shields.io/static/v1?label=&message=KMM&color=blueviolet)
![](https://img.shields.io/static/v1?label=presentation:presentation-redux-coroutines&message=1.3.0&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.app.presentation:presentation-redux-coroutines:1.3.0")
}
```

### presentation-redux-rx
Набор расширений для presentation-redux архитектуры с RxJava 2

![](https://img.shields.io/static/v1?label=&message=android&color=green)
![](https://img.shields.io/static/v1?label=presentation:redux-rx&message=1.3.0&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.android.presentation:redux-rx:1.3.0")
}
```

## View

### AdapterDelegates

Делегаты для адаптеров списков

![](https://img.shields.io/static/v1?label=&message=android&color=green)
![](https://img.shields.io/static/v1?label=ui:adapter-delegates&message=1.1.1&color=blue)

```groovy
dependencies {
    implementation 'ru.nobird.android.ui:adapter-delegates:1.1.1'
}
```

### Adapters

Набор расширений для адаптеров списков, включая автоматическое обновление через diff callback и работу с выделением

![](https://img.shields.io/static/v1?label=&message=android&color=green)
![](https://img.shields.io/static/v1?label=ui:adapters&message=1.1.1&color=blue)

```groovy
dependencies {
    implementation 'ru.nobird.android.ui:adapters:1.1.1'
}
```

### view-injection

Набор расширений для DI

![](https://img.shields.io/static/v1?label=&message=android&color=green)
![](https://img.shields.io/static/v1?label=view:injection&message=1.1.0&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.android.view:injection:1.1.0")
}
```

### view-navigation

Набор расширений для навигации через [Cicerone](https://github.com/terrakok/Cicerone)

![](https://img.shields.io/static/v1?label=&message=android&color=green)

```groovy
dependencies {
    implementation 'ru.nobird.android.view:navigation:1.0.2'
}
```

### view-ui

Набор UI расширений

![](https://img.shields.io/static/v1?label=&message=android&color=green)
![](https://img.shields.io/static/v1?label=view:ui&message=1.1.0&color=blue)

```groovy
dependencies {
    implementation 'ru.nobird.android.view:ui:1.1.0'
}
```

### view-redux

Набор UI расширений для redux архитектры

![](https://img.shields.io/static/v1?label=&message=android&color=green)
![](https://img.shields.io/static/v1?label=view:redux&message=1.3.0&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.android.view:redux:1.3.0")
}
```
