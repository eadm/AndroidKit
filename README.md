# Android Kit
Android Kit is a framework for Android development

## Usage

Add the following to the root `build.gradle` file
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

Next, you need to [generate a GitHub Token](https://github.com/settings/tokens/new) with the `read:packages` permission and place it in the root `gradle.properties` file (usually located in `~/.gradle/gradle.properties`).
```
GITHUB_USER=YOUR_GITHUB_USER_NAME
GITHUB_PERSONAL_ACCESS_TOKEN=YOUR_GITHUB_ACCESS_TOKEN
```

# Artifacts

### Model
A set of basic classes and extensions for models

![](https://img.shields.io/static/v1?label=&message=KMM&color=blueviolet)
![](https://img.shields.io/static/v1?label=core:model&message=1.0.8&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.app.core:model:1.0.8")
}
```

### domain-rx

Extensions for working with RxJava

![](https://img.shields.io/static/v1?label=&message=android&color=green)

```groovy
dependencies {
    implementation 'ru.nobird.android.domain:rx:x.y.z'
}
```

## Presentation

### presentation-base
Base for the presentation layer

![](https://img.shields.io/static/v1?label=&message=android&color=green)

```groovy
dependencies {
    implementation 'ru.nobird.android.presentation:base:1.1.0'
}
```

### presentation-redux
Base for the presentation-redux layer, an alternative to `presentation-base`

![](https://img.shields.io/static/v1?label=&message=KMM&color=blueviolet)
![](https://img.shields.io/static/v1?label=presentation:presentation-redux&message=1.3.1&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.app.presentation:presentation-redux:1.3.1")
}
```

### presentation-redux-coroutines
Extensions for the presentation-redux architecture with coroutines

![](https://img.shields.io/static/v1?label=&message=KMM&color=blueviolet)
![](https://img.shields.io/static/v1?label=presentation:presentation-redux-coroutines&message=1.3.1&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.app.presentation:presentation-redux-coroutines:1.3.1")
}
```

### presentation-redux-rx
Extensions for the presentation-redux architecture with RxJava 2

![](https://img.shields.io/static/v1?label=&message=android&color=green)
![](https://img.shields.io/static/v1?label=presentation:redux-rx&message=1.3.0&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.android.presentation:redux-rx:1.3.0")
}
```

## View

### AdapterDelegates

Delegates for list adapters

![](https://img.shields.io/static/v1?label=&message=android&color=green)
![](https://img.shields.io/static/v1?label=ui:adapter-delegates&message=1.1.1&color=blue)

```groovy
dependencies {
    implementation 'ru.nobird.android.ui:adapter-delegates:1.1.1'
}
```

### Adapters

Extensions for RecycleView adapters, including automatic updates via diff callback and selection handling

![](https://img.shields.io/static/v1?label=&message=android&color=green)
![](https://img.shields.io/static/v1?label=ui:adapters&message=1.1.1&color=blue)

```groovy
dependencies {
    implementation 'ru.nobird.android.ui:adapters:1.1.1'
}
```

### view-injection

Extensions for Dependency Injection

![](https://img.shields.io/static/v1?label=&message=android&color=green)
![](https://img.shields.io/static/v1?label=view:injection&message=1.1.0&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.android.view:injection:1.1.0")
}
```

### view-navigation

Extensions for navigation via [Cicerone](https://github.com/terrakok/Cicerone)

![](https://img.shields.io/static/v1?label=&message=android&color=green)

```groovy
dependencies {
    implementation 'ru.nobird.android.view:navigation:1.1.0'
}
```

### view-ui

A set of UI extensions

![](https://img.shields.io/static/v1?label=&message=android&color=green)
![](https://img.shields.io/static/v1?label=view:ui&message=1.1.0&color=blue)

```groovy
dependencies {
    implementation 'ru.nobird.android.view:ui:1.1.0'
}
```

### view-redux

UI extensions for the redux architecture

![](https://img.shields.io/static/v1?label=&message=android&color=green)
![](https://img.shields.io/static/v1?label=view:redux&message=1.3.0&color=blue)

```groovy
dependencies {
    implementation("ru.nobird.android.view:redux:1.3.0")
}
```
