# 4GForce

App Android ultraligera que abre el menú oculto de radio de Android para forzar el modo **Solo LTE (4G)** manualmente.

## Qué hace

Un solo botón abre `RadioInfo`, la pantalla nativa de Android donde puedes cambiar el tipo de red preferida a **LTE Only**. La app no modifica la configuración del teléfono — solo te lleva al menú correcto.

## Advertencia

Al seleccionar Solo LTE, el teléfono **no podrá hacer ni recibir llamadas ni SMS**. Anota el tipo de red original antes de cambiarlo para poder restaurarlo después.

## Idiomas

- Español (por defecto)
- English

## Build

```bash
./gradlew assembleDebug
```

APK en `app/build/outputs/apk/debug/app-debug.apk`.

### Release

```bash
./gradlew assembleRelease
```

Necesita signing config en `app/build.gradle.kts` o firma manual con `apksigner`.

## Requisitos

- Android Studio o JDK 17+
- Android SDK 35 (compileSdk)
- minSdk 21 (Android 5.0+)

## Compatibilidad

No todos los teléfonos permiten abrir el menú de radio. Algunos fabricantes bloquean el acceso. Si no funciona, la app muestra un mensaje indicándolo.
