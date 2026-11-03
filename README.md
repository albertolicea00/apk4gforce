# 4GForce

Lightweight Android app that opens Android's hidden radio menu to manually force **LTE Only (4G)** mode.

## What it does

One button opens `RadioInfo`, Android's native screen where you can change the preferred network type to **LTE Only**. The app does not modify phone settings — it just takes you to the right menu.

If the button doesn't work on your device, the app explains how to manually dial `*#*#4636#*#*` to access the hidden testing menu.

## Features

- Opens Android's native RadioInfo menu with one tap
- Step-by-step instructions on how to force 4G
- Warning section explaining risks and how to restore settings
- Fallback dial code for unsupported devices
- Info screen with version, developer, and links
- Zero permissions required
- No ads, no tracking, no internet
- Light and dark theme (follows system)
- Spanish and English

## Warning

Selecting LTE Only means your phone **cannot make or receive calls or SMS**. Note your original network type before changing it so you can restore it later.

## Build

See the [Makefile](Makefile) for build, version management, and release commands.

## Compatibility

Not all phones allow opening the radio menu. Some manufacturers block access. If the main button doesn't work, the app provides the manual dial code `*#*#4636#*#*` as a fallback.
