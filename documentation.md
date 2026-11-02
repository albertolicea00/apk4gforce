# Mobile Network Documentation — 4GForce

This documentation covers everything you need to know about mobile networks, frequency bands, forcing 4G, and the inner workings of network selection on Android. It is intended for both general users and technicians.

---

## Table of Contents

1. [Mobile Network Generations](#1-mobile-network-generations)
2. [Frequency Bands](#2-frequency-bands)
3. [Preferred Network Type on Android](#3-preferred-network-type-on-android)
4. [What Is Forcing 4G?](#4-what-is-forcing-4g)
5. [VoLTE (Voice over LTE)](#5-volte-voice-over-lte)
6. [RadioInfo and the Engineering Menu](#6-radioinfo-and-the-engineering-menu)
7. [How It Works Internally](#7-how-it-works-internally)
8. [Common Use Cases](#8-common-use-cases)
9. [Risks and Precautions](#9-risks-and-precautions)
10. [Frequently Asked Questions](#10-frequently-asked-questions)

---

## 1. Mobile Network Generations

Mobile networks have evolved through several "generations," each bringing significant improvements in speed, capacity, and functionality.

### 1G — First Generation (1980s)

The first generation was entirely analog. It only supported voice calls with limited quality and no encryption. Systems like AMPS (Advanced Mobile Phone System) in the United States and NMT (Nordic Mobile Telephone) in Europe were the main representatives. It is no longer in use anywhere in the world.

### 2G — Second Generation (1990s)

The second generation introduced digital communication, which meant better voice quality, basic encryption, and the ability to send text messages (SMS).

- **GSM (Global System for Mobile Communications):** The most widely deployed 2G standard globally. It operates on the 850, 900, 1800, and 1900 MHz bands. Data speed: up to 14.4 kbps.
- **GPRS (General Packet Radio Service):** An extension of GSM that introduced packet-switched data transmission. Theoretical speeds of up to 114 kbps, though in practice it rarely exceeded 40 kbps. Sometimes called "2.5G."
- **EDGE (Enhanced Data rates for GSM Evolution):** An improvement over GPRS reaching theoretical speeds of up to 384 kbps (in practice, 100–200 kbps). Known as "2.75G." It is the fastest 2G technology.

**Current status:** Many operators are shutting down their 2G networks. However, in some developing countries and rural areas, 2G remains the only available coverage. Some simple IoT devices (meters, basic GPS trackers) still rely on 2G due to its low power consumption and wide coverage.

### 3G — Third Generation (2000s)

The third generation brought significantly higher data speeds, enabling web browsing, video calls, and basic streaming.

- **UMTS (Universal Mobile Telecommunications System):** The 3G standard based on WCDMA (Wideband Code Division Multiple Access) access technology. Speeds of up to 384 kbps in early deployments.
- **HSPA (High Speed Packet Access):** An evolution of UMTS that includes:
  - **HSDPA (High Speed Downlink Packet Access):** Download speeds of up to 14.4 Mbps theoretical.
  - **HSUPA (High Speed Uplink Packet Access):** Upload speeds of up to 5.76 Mbps theoretical.
- **HSPA+ (Evolved HSPA):** The enhanced version reaching up to 42 Mbps download and 11.5 Mbps upload in theory. In practice, download speeds of 5–15 Mbps were common. Some operators marketed it as "4G" even though it technically is not.
- **TD-SCDMA (Time Division Synchronous Code Division Multiple Access):** A 3G standard developed in China, used mainly by China Mobile. Less widespread globally.

**Current status:** Like 2G, 3G networks are being progressively shut down in many countries. The United States has already disconnected most of its 3G networks. In Europe and Asia, the process is underway.

### 4G — Fourth Generation (2010s)

The fourth generation represented a qualitative leap in speed and latency, designed from the ground up for data rather than voice.

- **LTE (Long Term Evolution):** The most widely deployed 4G standard. Technically, early versions of LTE did not meet the ITU requirements to be called "4G" (which demanded 100 Mbps while mobile and 1 Gbps while stationary), but the ITU allowed the commercial use of the term.
  - Theoretical speeds: up to 150 Mbps download and 50 Mbps upload (Category 4).
  - Real-world speeds: typically 10–50 Mbps download.
  - Latency: 30–50 ms (significantly better than 3G).
- **LTE-Advanced (LTE-A):** True 4G by ITU standards.
  - Introduces Carrier Aggregation, which combines multiple frequency bands simultaneously.
  - Theoretical speeds: up to 1 Gbps download (Category 16) and 500 Mbps upload.
  - Real-world speeds: 50–300 Mbps download, depending on the operator and conditions.
  - Advanced MIMO (4×4 MIMO): uses multiple antennas to increase throughput.
- **LTE-Advanced Pro:** An evolution introducing technologies such as Massive MIMO (up to 64 antennas), LAA (Licensed Assisted Access) using unlicensed spectrum (5 GHz), and theoretical speeds of up to 3 Gbps.

**LTE Device Categories:**

| Category | Max Download | Max Upload | Notes |
|----------|-------------|------------|-------|
| Cat 1 | 10 Mbps | 5 Mbps | Basic IoT |
| Cat 3 | 100 Mbps | 50 Mbps | Older smartphones |
| Cat 4 | 150 Mbps | 50 Mbps | Common smartphones |
| Cat 6 | 300 Mbps | 50 Mbps | With carrier aggregation |
| Cat 9 | 450 Mbps | 50 Mbps | High-end |
| Cat 12 | 600 Mbps | 100 Mbps | Recent high-end |
| Cat 16 | 1 Gbps | 150 Mbps | LTE-Advanced |
| Cat 18 | 1.2 Gbps | 200 Mbps | LTE-Advanced Pro |

### 5G — Fifth Generation (2020s)

The fifth generation offers massive speeds, ultra-low latency, and the capacity to connect millions of devices simultaneously.

- **5G NR (New Radio):** The radio standard for 5G.
  - **Sub-6 GHz:** Uses frequencies below 6 GHz (mainly 3.5 GHz). Offers a good balance between speed and coverage. Typical speeds: 100–900 Mbps.
  - **mmWave (millimeter wave):** Uses frequencies from 24–100 GHz. Extreme speeds (up to 10 Gbps theoretical, 1–4 Gbps real-world) but very limited range (a few hundred meters) and poor building penetration.
- **5G NSA (Non-Standalone):** The first 5G deployment, which uses existing 4G infrastructure as an anchor. The phone connects simultaneously to 4G and 5G.
- **5G SA (Standalone):** Pure 5G, without 4G dependency. Offers the full advantages of 5G, including network slicing and ultra-low latency (1 ms).

**Note:** 4GForce focuses on LTE because it remains the most widely deployed and reliable data network globally. Even in areas with 5G, LTE coverage tends to be more consistent.

---

## 2. Frequency Bands

### What Is a Frequency Band?

A frequency band is a specific range of the electromagnetic spectrum assigned for mobile communications. Each operator holds licenses to use certain bands in certain countries. Bands are identified by standardized numbers defined by 3GPP (the body that defines mobile telecommunications standards).

### Band Characteristics

Bands are generally divided into two categories with different physical properties:

**Low bands (below 1 GHz):**
- Greater range (waves travel farther)
- Better penetration through buildings and obstacles
- Less available bandwidth (lower speeds)
- Ideal for rural and indoor coverage
- Examples: B5 (850 MHz), B8 (900 MHz), B12/B17 (700 MHz), B20 (800 MHz), B28 (700 MHz)

**High bands (above 1 GHz):**
- Shorter range
- Worse building penetration
- More available bandwidth (higher speeds)
- Ideal for urban areas with high user density
- Examples: B1 (2100 MHz), B3 (1800 MHz), B7 (2600 MHz), B41 (2500 MHz)

### Most Common LTE Bands Worldwide

#### FDD (Frequency Division Duplex)

In FDD, upload and download use different frequencies simultaneously.

| Band | Frequency | Main Regions | Notes |
|------|-----------|-------------|-------|
| **B1** | 2100 MHz | Global (Europe, Asia, Latin America) | One of the most widespread worldwide |
| **B2** | 1900 MHz | Americas (USA, Canada, Latin America) | PCS, very common in the Americas |
| **B3** | 1800 MHz | Global (Europe, Asia, Africa) | The most deployed LTE band in the world |
| **B4** | 1700/2100 MHz (AWS) | Americas (USA, Canada, Mexico) | T-Mobile, AT&T |
| **B5** | 850 MHz | Americas, Asia-Pacific | Low band with good coverage |
| **B7** | 2600 MHz | Global (Europe, Latin America, Asia) | High urban capacity |
| **B8** | 900 MHz | Europe, Asia, Africa | Excellent rural coverage |
| **B12** | 700 MHz | USA (T-Mobile) | Critical low band for indoor coverage |
| **B13** | 700 MHz | USA (Verizon) | Verizon's main low band |
| **B17** | 700 MHz | USA | Subset of B12 |
| **B20** | 800 MHz | Europe | Main low band in Europe |
| **B25** | 1900 MHz | USA (Sprint/T-Mobile) | Extension of B2 |
| **B26** | 850 MHz | USA, Japan | Extension of B5 |
| **B28** | 700 MHz | Asia-Pacific, Latin America | APT 700, increasingly used |
| **B66** | 1700/2100 MHz | USA, Canada | Extension of B4 (AWS-3) |
| **B71** | 600 MHz | USA (T-Mobile) | Ultra-low band, excellent range |

#### TDD (Time Division Duplex)

In TDD, upload and download share the same frequency but alternate in time.

| Band | Frequency | Main Regions | Notes |
|------|-----------|-------------|-------|
| **B38** | 2600 MHz | Europe, Asia | TDD complement of B7 |
| **B39** | 1900 MHz | China | China Mobile |
| **B40** | 2300 MHz | Asia, India | Widely used in India |
| **B41** | 2500 MHz | Global | Sprint, China Mobile, India. High capacity |
| **B42** | 3500 MHz | Europe | Also used for 5G NR |
| **B43** | 3700 MHz | Europe | Also used for 5G NR |

### Bands by Region

**North America (USA and Canada):**
- Main bands: B2, B4, B5, B7, B12, B13, B17, B25, B26, B41, B66, B71
- If you buy a phone to use in the USA, make sure it supports B2, B4, B12/B13 at a minimum.

**Europe:**
- Main bands: B1, B3, B7, B8, B20, B28, B38
- The combination B3 + B7 + B20 covers most European operators.

**Latin America:**
- Main bands: B1, B2, B3, B4, B5, B7, B28
- Varies significantly by country. B4 and B7 are common in Mexico; Brazil uses B7 and B28 heavily.

**Cuba and the Caribbean:**
- ETECSA (Cuba) primarily operates on B3 (1800 MHz) and B1 (2100 MHz) for LTE.
- In the Caribbean generally: B2, B4, B7, B13 are common depending on the operator.

**Asia:**
- Main bands: B1, B3, B5, B7, B8, B28, B38, B39, B40, B41
- China has a unique ecosystem with B39, B40, and B41 (TD-LTE).

**Africa:**
- Main bands: B1, B3, B7, B8, B20, B28
- B8 and B3 are the most widespread.

### Why Do Bands Matter When Buying a Phone?

A phone can only connect to bands for which it has compatible radio hardware. If you buy a phone designed for the Asian market and use it in the United States, it may not support the local bands and you could have little or no LTE coverage.

**Recommendations:**
- Before buying an imported phone, check which LTE bands it supports (this information is in the technical specifications).
- Compare those bands with the ones your operator uses in your country.
- "Global" or "international unlocked" phones typically support more bands.

---

## 3. Preferred Network Type on Android

### What Is the Preferred Network Type?

The "Preferred Network Type" is an internal Android setting that tells the phone's modem which network technologies it can use and which to prioritize. This setting determines which network generation your phone will connect to.

### Available Modes

The available modes vary by manufacturer, model, and operator, but the most common ones are:

| Mode | Description | Behavior |
|------|-------------|----------|
| **LTE/WCDMA/GSM (auto)** | Full automatic mode | The phone chooses the best available network. Prioritizes LTE, then 3G, then 2G. This is the default mode. |
| **LTE/WCDMA** | 4G and 3G only | The phone uses LTE or 3G but never drops to 2G. |
| **LTE Only** | 4G only | The phone **only** connects to LTE. It does not use 3G or 2G under any circumstances. |
| **WCDMA/GSM (auto)** | 3G and 2G only | The phone does not use LTE. Useful if LTE has issues in your area. |
| **WCDMA Only** | 3G only | Only uses UMTS/HSPA. Does not use LTE or 2G. |
| **GSM Only** | 2G only | Only uses GSM/EDGE. Very low speeds but maximum coverage and battery savings. |
| **LTE/TD-SCDMA/UMTS** | 4G/3G (variant with TD-SCDMA) | Common on phones sold in China. Includes TD-SCDMA support. |
| **LTE/TD-SCDMA/WCDMA/GSM** | All technologies | Automatic mode that includes TD-SCDMA. |

### How Does the Phone Choose the Network?

In automatic mode, the phone follows these general rules:

1. **Scans** all available bands and technologies.
2. **Prioritizes** the highest technology (LTE > 3G > 2G).
3. **Evaluates** signal quality. If LTE has a very weak signal, it may drop to 3G.
4. **Considers** operator instructions. SIM cards contain operator configurations that can influence selection.
5. **Performs handover** between technologies as you move. For example, if you leave an area with LTE coverage, the phone automatically drops to 3G.

### The Problem with Automatic Mode

In automatic mode, the phone can make suboptimal decisions:

- **Network ping-pong:** In areas with borderline coverage, the phone may constantly alternate between LTE and 3G, causing disconnections and slowdowns.
- **Unnecessary downgrade to 2G/3G:** Some operators configure their networks so the phone drops to 3G during voice calls (Circuit-Switched Fallback or CSFB), and sometimes the phone is slow to return to LTE.
- **3G congestion:** In areas where many people are on 3G, the experience can be worse than if you forced LTE, even with a weaker LTE signal.

This is why forcing LTE can significantly improve the experience in certain scenarios.

---

## 4. What Is Forcing 4G?

### Definition

Forcing 4G means configuring your phone to **exclusively** connect to LTE networks, completely ignoring 2G and 3G networks. The phone will not drop to a lower technology under any circumstances, even if the LTE signal is weak.

### Why Force 4G?

**1. Superior data speeds**
LTE offers significantly higher speeds than 3G. If you are in an area with LTE coverage, forcing 4G guarantees you will always get the best available speeds.

**2. Avoid 3G congestion**
In many areas, 3G networks are congested because they share capacity with many users. The LTE network, having more capacity and bandwidth, is usually less congested.

**3. Connection stability**
Automatic network switching (handover) can cause brief interruptions. If LTE is available reliably in your area, forcing 4G eliminates these switches and provides a more consistent connection.

**4. Lower latency**
LTE has a typical latency of 30–50 ms, while 3G usually has 70–200 ms. For online games, video calls, and web browsing, this difference is noticeable.

**5. Avoid operator-forced downgrade**
Some operators aggressively configure their networks to push devices to 3G, especially during calls (CSFB). Forcing LTE prevents this (although it also prevents calls if you don't have VoLTE).

**6. Testing and diagnostics**
Network technicians and advanced users use LTE Only mode to test coverage and performance of the LTE network in a specific area, without "interference" from legacy networks.

### What You Lose by Forcing 4G

- **Voice calls** (on most phones): Traditional phone calls use circuit-switched technology that is only available on 2G and 3G. Without VoLTE enabled, your phone will not be able to make or receive calls.
- **SMS** (in some cases): Similar to calls. Without VoLTE/VoNR, SMS may not work.
- **Coverage**: If you move to an area without LTE coverage, you will lose your connection entirely. In automatic mode, the phone would have dropped to 3G or 2G.
- **Emergency calls**: On some phones and networks, forcing LTE Only may prevent emergency calls (112, 911). Although many phones have safety mechanisms that allow emergency calls regardless of configuration, this is not guaranteed.

---

## 5. VoLTE (Voice over LTE)

### What Is VoLTE?

VoLTE (Voice over LTE) is a technology that allows making voice calls directly over the LTE network, without needing to drop to 2G or 3G. The voice is transmitted as IP data, similar to a WhatsApp or Telegram call, but integrated at the network level.

### Advantages of VoLTE

- **Superior voice quality:** VoLTE uses wideband audio codecs (AMR-WB or EVS), offering significantly better HD voice quality than 2G/3G calls.
- **Faster connection:** VoLTE calls are established in 1–2 seconds, compared to 5–8 seconds on 3G calls (which require CSFB).
- **Simultaneous data:** You can browse the internet at LTE speeds while talking on the phone.
- **Lower battery consumption:** No network switching (switching from LTE to 3G and back consumes extra radio energy).
- **LTE Only compatibility:** With VoLTE enabled, you can force LTE Only without losing the ability to make and receive calls and SMS.

### How to Know If You Have VoLTE

1. **Status bar indicator:** When VoLTE is active, many phones display a "VoLTE" or "HD" icon next to the signal indicator.
2. **Phone settings:** Go to Settings → Mobile Network → VoLTE Calls (or similar). If the option exists and is enabled, your phone supports VoLTE.
3. **During a call:** If the network indicator still shows "4G" or "LTE" instead of dropping to "3G" or "H+" while making a call, VoLTE is working.
4. **Service code:** On some phones, dial `*#*#4636#*#*` → Phone Information and look for "VoLTE Provisioned" or similar.

### VoLTE Requirements

For VoLTE to work, **three** conditions must be met simultaneously:

1. **The phone must support VoLTE:** Not all phones support it. Mid-range and high-end phones from the last 5–6 years generally do.
2. **The operator must have VoLTE deployed:** Not all operators offer VoLTE, especially in developing countries.
3. **The phone must be enabled/certified for VoLTE with that specific operator:** Some operators only enable VoLTE on phones they have certified. A phone that supports VoLTE with one operator may not support it with another.

### VoWiFi (Voice over WiFi)

Similar to VoLTE, VoWiFi allows making calls over WiFi when cellular coverage is poor. If your operator supports VoWiFi, you can enable it as a complement to VoLTE to ensure voice coverage indoors.

---

## 6. RadioInfo and the Engineering Menu

### What Is RadioInfo?

RadioInfo is a hidden activity within the Android system "Phone" application (com.android.phone). It provides direct access to detailed information about the phone's radio modem and allows modifying network settings that are normally not available in the regular settings.

### How to Access It

There are several ways to access RadioInfo:

1. **Through 4GForce:** The app opens RadioInfo directly.
2. **Dial code:** Open the Phone app and dial `*#*#4636#*#*`. This opens the testing menu, where you can select "Phone Information" to reach RadioInfo.
3. **ADB (for developers):** From a computer with ADB:
   ```
   adb shell am start -n com.android.phone/.settings.RadioInfo
   ```
   or on older versions:
   ```
   adb shell am start -n com.android.phone/.RadioInfo
   ```

### Information Displayed in RadioInfo

RadioInfo shows a large amount of technical information:

- **IMEI:** The unique identifier of the cellular modem.
- **Phone Number:** The phone number associated with the SIM (if available).
- **Network:** The operator you are currently connected to.
- **Signal Strength:** The signal strength in dBm and ASU.
  - **dBm (decibel-milliwatts):** Typical values:
    - LTE: -44 dBm (excellent) to -140 dBm (no signal). Above -80 dBm is good, below -100 dBm is weak.
    - 3G: -60 dBm (excellent) to -120 dBm (no signal).
  - **ASU (Arbitrary Strength Unit):** A scale that varies by technology. For LTE, ASU = RSRP + 140.
- **Service State:** Service status (In Service, Out of Service, Emergency Only).
- **Data State:** Data connection status (Connected, Disconnected, Suspended).
- **Data Network Type:** The active data technology (LTE, HSPA+, UMTS, EDGE, etc.).
- **Voice Network Type:** The active voice technology.
- **Roaming:** Indicates whether you are roaming.

### Set Preferred Network Type

This is the most important option for forcing 4G. It is a dropdown menu showing all network modes supported by your phone. The most common values and their internal codes are:

| Value | RIL Code | Description |
|-------|----------|-------------|
| LTE/WCDMA/GSM (PRL) | 9 | Automatic with LTE priority |
| LTE/GSM/WCDMA | 9 | Same as above (alternative name) |
| LTE Only | 11 | **LTE only — the one 4GForce uses** |
| LTE/WCDMA | 12 | LTE and 3G, no 2G |
| WCDMA preferred | 0 | 3G preferred, with fallback to 2G |
| GSM Only | 1 | 2G only |
| WCDMA Only | 2 | 3G only |
| GSM/WCDMA (auto) | 3 | 3G and 2G automatic |
| LTE/CDMA/EvDo/GSM/WCDMA | 10 | Global automatic (includes CDMA) |
| NR/LTE/WCDMA/GSM | 33 | 5G/4G/3G/2G automatic |
| NR/LTE | 41 | 5G and 4G only |
| NR Only | 23 | 5G only |

**Important note:** The available codes and options can vary significantly between manufacturers. Some phones show all modes, others only those the operator has enabled.

### Other Useful Options in RadioInfo

- **Toggle Radio:** Turns the radio module on and off (equivalent to airplane mode for cellular).
- **Enable/Disable Data:** Activates or deactivates the data connection.
- **SMSC:** Allows viewing and changing the SMS message center.
- **Ping Test:** Performs a ping to verify data connectivity.
- **Cell Info:** Shows detailed information about the cell you are connected to (Cell ID, LAC/TAC, frequency, etc.).

### The Testing Menu (*#*#4636#*#*)

The code `*#*#4636#*#*` opens a broader menu that includes:

1. **Phone Information:** The RadioInfo screen described above. If the phone has two SIMs, there may be "Phone Information (SIM 1)" and "Phone Information (SIM 2)."
2. **Battery Information:** Detailed battery information (voltage, temperature, technology, health status).
3. **Usage Statistics:** Application usage statistics (first use time, last use, times launched).
4. **WiFi Information:** Information about the current WiFi connection.

**Note:** Not all phones respond to this code. Some manufacturers (especially Samsung on some One UI versions) disable it or redirect it to another menu. In those cases, 4GForce attempts to open RadioInfo directly through its internal component.

---

## 7. How It Works Internally

### Android Network Stack Architecture

Network selection on Android involves several layers, from the application to the radio hardware:

```
┌─────────────────────────────┐
│  Application (4GForce)      │  ← User layer
├─────────────────────────────┤
│  Android Framework          │  ← TelephonyManager, PhoneFactory
├─────────────────────────────┤
│  RIL Daemon (rild)          │  ← Interface layer
├─────────────────────────────┤
│  Vendor RIL (libril)        │  ← Manufacturer implementation
├─────────────────────────────┤
│  Modem / Baseband           │  ← Radio hardware
└─────────────────────────────┘
```

### RIL (Radio Interface Layer)

The RIL is a fundamental abstraction layer in Android that acts as a bridge between the operating system and the radio hardware (modem/baseband). Its function is to translate high-level requests from the Android framework into specific commands that the radio hardware understands.

**RIL Components:**

1. **rild (RIL Daemon):** A system process that runs constantly and handles communication between the framework and the hardware.
2. **Vendor RIL Library:** A library provided by the chipset manufacturer (Qualcomm, MediaTek, Samsung, etc.) that implements hardware-specific commands.
3. **RIL Java (Telephony Framework):** The Java layer in the Android framework that applications and system services use to interact with telephony.

**Flow of a network change request:**

1. The user selects "LTE Only" in RadioInfo (or 4GForce opens RadioInfo).
2. RadioInfo calls `TelephonyManager.setPreferredNetworkType()`.
3. The framework sends a `RIL_REQUEST_SET_PREFERRED_NETWORK_TYPE` request to rild.
4. rild passes the request to the vendor RIL library.
5. The vendor library translates this into AT or QMI (Qualcomm MSM Interface) commands for the modem.
6. The modem reconfigures its radio protocol stack to only search for and connect to LTE cells.
7. The modem confirms the change back through the chain.

### The Modem / Baseband

The modem (also called the baseband processor) is a separate processor inside the phone dedicated exclusively to cellular communications. It has its own firmware, its own real-time operating system (RTOS), and operates independently from the main application processor.

**Major modem manufacturers:**
- **Qualcomm (Snapdragon Modem):** The most widespread, used in most Android phones.
- **MediaTek:** Common in mid-range and budget phones.
- **Samsung (Exynos Modem):** Used in some Galaxy devices (especially in markets outside the USA).
- **HiSilicon (Balong):** Used in Huawei phones.

**When you switch to LTE Only:**

1. The modem stops scanning 2G and 3G frequencies.
2. It only maintains LTE neighbor cell tables.
3. If it loses the LTE signal, it enters a "No Service" state instead of searching for alternative networks.
4. The savings from not scanning multiple technologies can slightly improve battery life in areas with good LTE coverage.

### Configuration Persistence

An important technical detail: when you change the preferred network type through RadioInfo, this change is saved in the Android system settings (`Settings.Global.PREFERRED_NETWORK_MODE`). However:

- Some phones **persist** the change even after rebooting.
- Other phones **revert** to the operator's default mode after rebooting.
- Updating the operating system or operator configuration can revert the change.
- Removing and reinserting the SIM can revert the change.

---

## 8. Common Use Cases

### Data Speed Optimization

**Scenario:** You are in an urban area with stable LTE coverage but notice inconsistent speeds.

**Probable cause:** Your phone is alternating between LTE and 3G, or the operator is forcing CSFB (dropping to 3G for calls) and the phone is slow to return to LTE.

**Solution:** Force LTE Only to maintain a stable connection and consistent speeds. Make sure VoLTE is active if you need to receive calls.

### LTE Coverage Testing

**Scenario:** You are a network technician and need to verify LTE coverage in a specific area.

**Use:** Force LTE Only and move around the area observing signal strength (RSRP, RSRQ, SINR) in RadioInfo. This gives you a clear picture of LTE coverage without the phone masking problems by dropping to 3G.

### Avoiding 3G Congestion

**Scenario:** You are at a massive event (concert, stadium) or in an area with limited network infrastructure. The data connection is extremely slow.

**Probable cause:** Most users are connected to 3G, congesting that network. The LTE network may be less loaded because not all phones support it or the operator has more LTE capacity.

**Solution:** Forcing LTE Only can give you access to a less congested network with better speeds.

### IoT Devices and 4G Routers

**Scenario:** You have a portable 4G router or an IoT device that only needs data and not voice.

**Use:** Forcing LTE Only is ideal for these devices since they don't need to make calls and you ensure they always use the fastest available connection.

### Rural Areas with Mixed Signal

**Scenario:** You live in a rural area where there is weak but functional LTE signal, and strong 3G signal. Your phone prefers 3G due to the stronger signal, but the speed is very low.

**Solution:** Forcing LTE Only keeps you on the faster network, even though the signal is weaker. As long as the RSRP is above approximately -115 dBm, LTE will continue to work.

### Speed Testing and Benchmarking

**Scenario:** You want to measure the real performance of your operator's LTE network.

**Use:** Forcing LTE Only before running speed tests (Speedtest, Fast.com) ensures the results exclusively reflect LTE performance, without interference from handovers or network changes during the test.

### Selective Battery Saving

**Scenario:** You are in an area with good LTE coverage and don't need calls (you use WhatsApp/Telegram to communicate).

**Use:** Forcing LTE Only can save some battery by preventing the modem from constantly scanning 2G and 3G networks.

---

## 9. Risks and Precautions

### Loss of Calls and SMS

**This is the most important risk.** Without VoLTE enabled and working, forcing LTE Only means:

- **You cannot make phone calls.** The phone does not have access to the 2G/3G networks needed for circuit-switched calls.
- **You cannot receive calls.** Incoming calls will not reach your phone.
- **SMS may not work.** Traditional text messages depend on 2G/3G infrastructure on most networks.

**Mitigation:**
- Verify that VoLTE is active before forcing LTE Only.
- If you don't have VoLTE, use messaging apps (WhatsApp, Telegram, Signal) to communicate.
- Remember to restore automatic mode when you no longer need LTE Only.

### Emergency Calls

**Critical precaution:** In LTE Only mode, emergency calls (911, 112, etc.) may not work on all phones and networks.

- Many phones have a safety mechanism that allows emergency calls regardless of network configuration.
- However, this is **not guaranteed** on all devices.
- If you are in a situation where you might need to call emergency services, **do not use LTE Only**.

### Total Loss of Coverage

In automatic mode, if you leave an area with LTE coverage, your phone automatically drops to 3G or 2G and maintains the connection. In LTE Only, you will lose all network connectivity.

**Mitigation:**
- Do not use LTE Only if you are going to travel or move through areas with variable coverage.
- Especially dangerous in rural areas where LTE coverage is spotty.

### Battery Impact

The battery impact of LTE Only can go both ways:

- **Better battery:** If you have good, stable LTE signal. The modem does not spend energy scanning other networks or performing handovers.
- **Worse battery:** If the LTE signal is weak or unstable. The modem consumes more energy trying to maintain a weak connection and continuously searching for better LTE cells.

### Behavior After Rebooting

Some phones maintain the LTE Only setting after rebooting, others revert to the default mode. Do not assume it will automatically return to automatic mode.

**Recommendation:** If you have forced LTE Only, manually verify after rebooting whether the setting was maintained or reverted.

### Operator Updates

Operators can send network configuration updates (carrier config updates) that reset the preferred network type to the default value. This can happen without prior notice.

---

## 10. Frequently Asked Questions

### Does forcing 4G damage my phone?

**No.** Changing the preferred network type is a completely safe software setting. It does not affect the hardware in any way. You can switch between modes as many times as you want.

### Is forcing 4G legal?

**Yes.** You are changing a setting on your own phone. You are not hacking the network or violating any service agreement. The option exists in Android precisely to be used.

### Why doesn't my phone have the "LTE Only" option?

Possible reasons:

1. **The operator has hidden it:** Some operators configure the phone's software to hide advanced network options. RadioInfo may show options that the normal settings menu does not.
2. **The phone does not support LTE:** If it is a very old phone, it may not have LTE hardware.
3. **The manufacturer has removed it:** Some manufacturers remove the option in their customization layers.
4. **The dial code doesn't work:** The code `*#*#4636#*#*` does not work on all phones. Try using 4GForce, which accesses the RadioInfo component directly.

### Can I force 5G the same way?

Yes, on phones with 5G, RadioInfo generally includes options like "NR Only" or "NR/LTE." However, 5G options are less common and depend heavily on the manufacturer and Android version.

### Why doesn't my speed improve when forcing 4G?

Possible causes:

1. **You were already on LTE:** If your phone was already connected to LTE in automatic mode, forcing LTE Only will not change anything.
2. **LTE congestion:** The LTE network in your area may be congested. Forcing LTE does not fix this.
3. **Plan limitation:** Your operator may limit speed based on your data plan.
4. **Weak signal:** A weak LTE signal (RSRP below -110 dBm) can result in low speeds.
5. **Device limitation:** Your phone's LTE category limits the maximum speed.

### How do I go back to normal mode?

1. Open 4GForce and tap the button to open RadioInfo.
2. Look for "Set Preferred Network Type."
3. Select "LTE/WCDMA/GSM (auto)" or similar (the mode you noted before changing).
4. The phone will return to automatic behavior immediately.

### Does it work with dual SIM phones?

Yes, but the setting is applied per SIM. On dual SIM phones, RadioInfo may show information for each SIM separately ("Phone Information (SIM 1)" and "Phone Information (SIM 2)"). You must change the setting on the SIM you want to force to LTE.

### Can I use 4GForce with a data-only SIM?

Yes, and in fact it is an ideal use case. Data-only SIMs don't need voice or SMS, so you don't lose any functionality by forcing LTE Only.

### Why doesn't 4GForce change the setting directly?

Android does not allow normal applications (without system/root permissions) to programmatically change the preferred network type. This is a security restriction by design. The only way to change it is through the system interface (RadioInfo) or with special permissions that only system applications have.

That is why 4GForce acts as a shortcut: it quickly takes you to the correct menu where you can make the change manually with a single tap.

### What do RSRP, RSRQ, and SINR mean?

These are technical metrics of LTE signal quality that you can see in RadioInfo:

- **RSRP (Reference Signal Received Power):** The reference signal power. This is the primary signal strength metric.
  - Excellent: > -80 dBm
  - Good: -80 to -90 dBm
  - Acceptable: -90 to -100 dBm
  - Weak: -100 to -110 dBm
  - Very weak: < -110 dBm

- **RSRQ (Reference Signal Received Quality):** The signal quality, which takes interference into account.
  - Excellent: > -10 dB
  - Good: -10 to -15 dB
  - Acceptable: -15 to -20 dB
  - Poor: < -20 dB

- **SINR (Signal to Interference plus Noise Ratio):** The signal-to-noise ratio. Indicates how clean the signal is.
  - Excellent: > 20 dB
  - Good: 13–20 dB
  - Acceptable: 0–13 dB
  - Poor: < 0 dB

---

*Documentation created for the 4GForce application. For more information, visit the [GitHub repository](https://github.com/albertolicea00/apk4gforce).*
