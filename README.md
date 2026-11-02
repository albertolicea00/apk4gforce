# 4GForce

Lightweight Android app that opens Android's hidden radio menu to manually force **LTE Only (4G)** mode.

## What it does

Three buttons:

1. **Open Android radio menu** — opens `RadioInfo`, the native screen where you can change the preferred network type to **LTE Only**. The app does not modify phone settings — it just takes you to the right menu.
2. **Show IMEI (*#06#)** — opens the dialer with the IMEI code.
3. **Open testing menu (*#\*#4636#\*#\*)** — opens the dialer with the hidden testing menu code.

## Warning

Selecting LTE Only means your phone **cannot make or receive calls or SMS**. Note your original network type before changing it so you can restore it later.

## Languages

- Spanish (default)
- English

## Build

```bash
./gradlew assembleDebug
```

APK at `app/build/outputs/apk/debug/app-debug.apk`.

### Release

```bash
./gradlew assembleRelease
```

Requires signing config in `app/build.gradle.kts` or manual signing with `apksigner`.

## Requirements

- Android Studio or JDK 17+
- Android SDK 35 (compileSdk)
- minSdk 21 (Android 5.0+)
- Zero permissions required

## Compatibility

Not all phones allow opening the radio menu. Some manufacturers block access. If it doesn't work, the app shows a message indicating so.
