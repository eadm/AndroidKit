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

## Артефакты

### AdapterDelegates
[ ![Download](https://api.bintray.com/packages/eadm/ru.nobird.android/ru.nobird.android.ui.adapterdelegates/images/download.svg) ](https://bintray.com/eadm/ru.nobird.android/ru.nobird.android.ui.adapterdelegates/_latestVersion)

Делегаты для адаптеров списков

```groovy
dependencies {
    implementation 'ru.nobird.android.ui:adapter-delegates:x.y.z'
}
```

### Adapters
[ ![Download](https://api.bintray.com/packages/eadm/ru.nobird.android/ru.nobird.android.ui.adapters/images/download.svg) ](https://bintray.com/eadm/ru.nobird.android/ru.nobird.android.ui.adapters/_latestVersion)

Набор расширений для адаптеров списков, включая автоматическое обновление через diff callback и работу с выделением

```groovy
dependencies {
    implementation 'ru.nobird.android.ui:adapters:x.y.z'
}
```

### Model
Набор базовых классов и расширений для моделей

![](https://img.shields.io/static/v1?label=core:model&message=1.0.7&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.app.core:model:1.0.7")
}
```

### domain-rx
[ ![Download](https://api.bintray.com/packages/eadm/ru.nobird.android/ru.nobird.android.domain/images/download.svg) ](https://bintray.com/eadm/ru.nobird.android/ru.nobird.android.domain/_latestVersion)

Расширения для работы с RxJava

```groovy
dependencies {
    implementation 'ru.nobird.android.domain:rx:x.y.z'
}
```

### presentation-base
База для presentation слоя

```groovy
dependencies {
    implementation 'ru.nobird.android.presentation:base:1.1.0'
}
```

### presentation-redux
База для presentation-redux слоя, является альтернативой presentation-base

![](https://img.shields.io/static/v1?label=presentation:presentation-redux&message=1.3.0&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.app.presentation:presentation-redux:1.3.0")
}
```

### presentation-redux-rx
Набор расширений для presentation-redux архитектуры с RxJava 2

```groovy
dependencies {
    implementation 'ru.nobird.android.presentation:redux-rx:1.1.0'
}
```

### presentation-redux-coroutines
Набор расширений для presentation-redux архитектуры с корутинами

![](https://img.shields.io/static/v1?label=presentation:presentation-redux-coroutines&message=1.3.0&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.app.presentation:presentation-redux-coroutines:1.3.0")
}
```

### view-injection

Набор расширений для DI

![](https://img.shields.io/static/v1?label=view:injection&message=1.1.0&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.android.view:injection:1.1.0")
}
```

### view-navigation

Набор расширений для навигации через [Cicerone](https://github.com/terrakok/Cicerone)

```groovy
dependencies {
    implementation 'ru.nobird.android.view:navigation:1.0.2'
}
```

### view-ui

Набор UI расширений

```groovy
dependencies {
    implementation 'ru.nobird.android.view:ui:1.0.8'
}
```

### view-redux

Набор UI расширений для redux архитектры

![](https://img.shields.io/static/v1?label=view:redux&message=1.3.0&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.android.view:redux:1.3.0")
}
```
