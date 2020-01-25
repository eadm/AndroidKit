# Android Kit
Фреймворк для Android разработки

## Использование

В корневом `build.gradle` необходимо подключить репозиторий
```groovy
allprojects {
    repositories {
        maven { url "https://dl.bintray.com/eadm/ru.nobird.android" }
    }
}
```

## Артефакты

### AdapterDelegates
[ ![Download](https://api.bintray.com/packages/eadm/ru.nobird.android/ru.nobird.android.ui.adapterdelegates/images/download.svg) ](https://bintray.com/eadm/ru.nobird.android/ru.nobird.android.ui.adapterdelegates/_latestVersion)

Делегаты для адаптеров списков

```groovy
dependencies {
    implementation 'ru.nobird.android.ui:AdapterDelegates:x.y.z'
}
```

### Adapters
[ ![Download](https://api.bintray.com/packages/eadm/ru.nobird.android/ru.nobird.android.ui.adapters/images/download.svg) ](https://bintray.com/eadm/ru.nobird.android/ru.nobird.android.ui.adapters/_latestVersion)

Набор расширений для адаптеров списков, включая автоматическое обновление через diff callback и работу с выделением

```groovy
dependencies {
    implementation 'ru.nobird.android.ui:Adapters:x.y.z'
}
```

### presentation-base
[ ![Download](https://api.bintray.com/packages/eadm/ru.nobird.android/ru.nobird.android.presentation/images/download.svg) ](https://bintray.com/eadm/ru.nobird.android/ru.nobird.android.presentation/_latestVersion)

База для presentation слоя

```groovy
dependencies {
    implementation 'ru.nobird.android.presentation:base:x.y.z'
}
```

### view-injection

Набор расширений для DI

```groovy
dependencies {
    implementation 'ru.nobird.android.view:injection:1.0.0'
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
    implementation 'ru.nobird.android.view:ui:1.0.6'
}
```