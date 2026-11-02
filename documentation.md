# Documentación de Red Móvil — 4GForce

Esta documentación cubre todo lo que necesitas saber sobre las redes móviles, las bandas de frecuencia, el forzado de 4G y el funcionamiento interno de la selección de red en Android. Está dirigida tanto a usuarios generales como a técnicos.

---

## Tabla de contenidos

1. [Generaciones de red móvil](#1-generaciones-de-red-móvil)
2. [Bandas de frecuencia](#2-bandas-de-frecuencia)
3. [Tipo de red preferida en Android](#3-tipo-de-red-preferida-en-android)
4. [¿Qué es forzar 4G?](#4-qué-es-forzar-4g)
5. [VoLTE (Voice over LTE)](#5-volte-voice-over-lte)
6. [RadioInfo y el menú de ingeniería](#6-radioinfo-y-el-menú-de-ingeniería)
7. [Cómo funciona internamente](#7-cómo-funciona-internamente)
8. [Casos de uso comunes](#8-casos-de-uso-comunes)
9. [Riesgos y precauciones](#9-riesgos-y-precauciones)
10. [Preguntas frecuentes](#10-preguntas-frecuentes)

---

## 1. Generaciones de red móvil

Las redes móviles han evolucionado a lo largo de varias "generaciones", cada una con mejoras significativas en velocidad, capacidad y funcionalidad.

### 1G — Primera generación (años 80)

La primera generación fue completamente analógica. Solo soportaba llamadas de voz con calidad limitada y sin cifrado. Sistemas como AMPS (Advanced Mobile Phone System) en Estados Unidos y NMT (Nordic Mobile Telephone) en Europa fueron los representantes principales. Ya no está en uso en ningún lugar del mundo.

### 2G — Segunda generación (años 90)

La segunda generación introdujo la comunicación digital, lo que significó mejor calidad de voz, cifrado básico y la capacidad de enviar mensajes de texto (SMS).

- **GSM (Global System for Mobile Communications):** El estándar 2G más extendido globalmente. Opera en bandas de 850, 900, 1800 y 1900 MHz. Velocidad de datos: hasta 14.4 kbps.
- **GPRS (General Packet Radio Service):** Una extensión de GSM que introdujo la transmisión de datos por paquetes. Velocidades teóricas de hasta 114 kbps, aunque en la práctica raramente superaba los 40 kbps. A veces llamado "2.5G".
- **EDGE (Enhanced Data rates for GSM Evolution):** Una mejora sobre GPRS que alcanza velocidades teóricas de hasta 384 kbps (en la práctica, 100-200 kbps). Conocido como "2.75G". Es la tecnología 2G más rápida.

**Estado actual:** Muchos operadores están apagando sus redes 2G. Sin embargo, en algunos países en desarrollo y en áreas rurales, 2G sigue siendo la única cobertura disponible. Algunos dispositivos IoT simples (medidores, rastreadores GPS básicos) aún dependen de 2G por su bajo consumo energético y amplia cobertura.

### 3G — Tercera generación (años 2000)

La tercera generación trajo velocidades de datos significativamente mayores, permitiendo navegación web, videollamadas y streaming básico.

- **UMTS (Universal Mobile Telecommunications System):** El estándar 3G basado en la tecnología de acceso WCDMA (Wideband Code Division Multiple Access). Velocidades de hasta 384 kbps en las primeras implementaciones.
- **HSPA (High Speed Packet Access):** Una evolución de UMTS que incluye:
  - **HSDPA (High Speed Downlink Packet Access):** Velocidades de descarga de hasta 14.4 Mbps teóricos.
  - **HSUPA (High Speed Uplink Packet Access):** Velocidades de subida de hasta 5.76 Mbps teóricos.
- **HSPA+ (Evolved HSPA):** La versión mejorada que alcanza hasta 42 Mbps de descarga y 11.5 Mbps de subida en teoría. En la práctica, velocidades de 5-15 Mbps de descarga eran comunes. Algunos operadores lo comercializaron como "4G" aunque técnicamente no lo es.
- **TD-SCDMA (Time Division Synchronous Code Division Multiple Access):** Un estándar 3G desarrollado en China, utilizado principalmente por China Mobile. Menos extendido globalmente.

**Estado actual:** Al igual que 2G, las redes 3G están siendo progresivamente apagadas en muchos países. Estados Unidos ya ha desconectado la mayoría de sus redes 3G. En Europa y Asia, el proceso está en curso.

### 4G — Cuarta generación (años 2010)

La cuarta generación representó un salto cualitativo en velocidad y latencia, diseñada desde el inicio para datos en lugar de voz.

- **LTE (Long Term Evolution):** El estándar 4G más extendido. Técnicamente, las primeras versiones de LTE no cumplían los requisitos de la ITU para ser llamadas "4G" (que exigía 100 Mbps en movimiento y 1 Gbps estacionario), pero la ITU permitió el uso comercial del término.
  - Velocidades teóricas: hasta 150 Mbps de descarga y 50 Mbps de subida (categoría 4).
  - Velocidades reales: típicamente 10-50 Mbps de descarga.
  - Latencia: 30-50 ms (significativamente mejor que 3G).
- **LTE-Advanced (LTE-A):** La verdadera 4G según los estándares de la ITU.
  - Introduce la agregación de portadoras (Carrier Aggregation), que combina múltiples bandas de frecuencia simultáneamente.
  - Velocidades teóricas: hasta 1 Gbps de descarga (categoría 16) y 500 Mbps de subida.
  - Velocidades reales: 50-300 Mbps de descarga, dependiendo del operador y las condiciones.
  - MIMO avanzado (4x4 MIMO): utiliza múltiples antenas para aumentar el rendimiento.
- **LTE-Advanced Pro:** Una evolución que introduce tecnologías como MIMO masivo (hasta 64 antenas), LAA (Licensed Assisted Access) que usa espectro no licenciado (5 GHz), y velocidades teóricas de hasta 3 Gbps.

**Categorías de dispositivos LTE:**

| Categoría | Descarga máxima | Subida máxima | Notas |
|-----------|----------------|---------------|-------|
| Cat 1 | 10 Mbps | 5 Mbps | IoT básico |
| Cat 3 | 100 Mbps | 50 Mbps | Smartphones antiguos |
| Cat 4 | 150 Mbps | 50 Mbps | Smartphones comunes |
| Cat 6 | 300 Mbps | 50 Mbps | Con agregación de portadoras |
| Cat 9 | 450 Mbps | 50 Mbps | Gama alta |
| Cat 12 | 600 Mbps | 100 Mbps | Gama alta reciente |
| Cat 16 | 1 Gbps | 150 Mbps | LTE-Advanced |
| Cat 18 | 1.2 Gbps | 200 Mbps | LTE-Advanced Pro |

### 5G — Quinta generación (años 2020)

La quinta generación ofrece velocidades masivas, latencia ultra-baja y capacidad para conectar millones de dispositivos simultáneamente.

- **5G NR (New Radio):** El estándar de radio para 5G.
  - **Sub-6 GHz:** Utiliza frecuencias por debajo de 6 GHz (principalmente 3.5 GHz). Ofrece un buen equilibrio entre velocidad y cobertura. Velocidades típicas: 100-900 Mbps.
  - **mmWave (ondas milimétricas):** Utiliza frecuencias de 24-100 GHz. Velocidades extremas (hasta 10 Gbps teóricos, 1-4 Gbps reales) pero alcance muy limitado (pocos cientos de metros) y mala penetración en edificios.
- **5G NSA (Non-Standalone):** La primera implementación de 5G, que usa la infraestructura 4G existente como ancla. El teléfono se conecta simultáneamente a 4G y 5G.
- **5G SA (Standalone):** 5G puro, sin dependencia de 4G. Ofrece las ventajas completas de 5G, incluyendo network slicing y latencia ultra-baja (1 ms).

**Nota:** 4GForce se enfoca en LTE porque sigue siendo la red de datos más desplegada y confiable a nivel mundial. Incluso en áreas con 5G, la cobertura LTE suele ser más consistente.

---

## 2. Bandas de frecuencia

### ¿Qué es una banda de frecuencia?

Una banda de frecuencia es un rango específico del espectro electromagnético asignado para comunicaciones móviles. Cada operador tiene licencias para usar ciertas bandas en ciertos países. Las bandas se identifican por números estandarizados por la 3GPP (el organismo que define los estándares de telecomunicaciones móviles).

### Características de las bandas

Las bandas se dividen generalmente en dos categorías con propiedades físicas diferentes:

**Bandas bajas (por debajo de 1 GHz):**
- Mayor alcance (las ondas viajan más lejos)
- Mejor penetración en edificios y obstáculos
- Menor ancho de banda disponible (velocidades más bajas)
- Ideales para cobertura rural y en interiores
- Ejemplos: B5 (850 MHz), B8 (900 MHz), B12/B17 (700 MHz), B20 (800 MHz), B28 (700 MHz)

**Bandas altas (por encima de 1 GHz):**
- Menor alcance
- Peor penetración en edificios
- Mayor ancho de banda disponible (velocidades más altas)
- Ideales para áreas urbanas con alta densidad de usuarios
- Ejemplos: B1 (2100 MHz), B3 (1800 MHz), B7 (2600 MHz), B41 (2500 MHz)

### Bandas LTE más comunes a nivel mundial

#### FDD (Frequency Division Duplex)

En FDD, la subida y la bajada de datos usan frecuencias diferentes simultáneamente.

| Banda | Frecuencia | Regiones principales | Notas |
|-------|-----------|---------------------|-------|
| **B1** | 2100 MHz | Global (Europa, Asia, América Latina) | Una de las más extendidas a nivel mundial |
| **B2** | 1900 MHz | América (EE.UU., Canadá, América Latina) | PCS, muy común en las Américas |
| **B3** | 1800 MHz | Global (Europa, Asia, África) | La banda LTE más desplegada del mundo |
| **B4** | 1700/2100 MHz (AWS) | América (EE.UU., Canadá, México) | T-Mobile, AT&T |
| **B5** | 850 MHz | América, Asia-Pacífico | Banda baja con buena cobertura |
| **B7** | 2600 MHz | Global (Europa, América Latina, Asia) | Alta capacidad urbana |
| **B8** | 900 MHz | Europa, Asia, África | Excelente cobertura rural |
| **B12** | 700 MHz | EE.UU. (T-Mobile) | Banda baja crítica para cobertura interior |
| **B13** | 700 MHz | EE.UU. (Verizon) | Principal banda baja de Verizon |
| **B17** | 700 MHz | EE.UU. | Subconjunto de B12 |
| **B20** | 800 MHz | Europa | Banda baja principal en Europa |
| **B25** | 1900 MHz | EE.UU. (Sprint/T-Mobile) | Extensión de B2 |
| **B26** | 850 MHz | EE.UU., Japón | Extensión de B5 |
| **B28** | 700 MHz | Asia-Pacífico, América Latina | APT 700, cada vez más usada |
| **B66** | 1700/2100 MHz | EE.UU., Canadá | Extensión de B4 (AWS-3) |
| **B71** | 600 MHz | EE.UU. (T-Mobile) | Banda ultra-baja, excelente alcance |

#### TDD (Time Division Duplex)

En TDD, la subida y la bajada de datos comparten la misma frecuencia pero se alternan en el tiempo.

| Banda | Frecuencia | Regiones principales | Notas |
|-------|-----------|---------------------|-------|
| **B38** | 2600 MHz | Europa, Asia | Complemento TDD de B7 |
| **B39** | 1900 MHz | China | China Mobile |
| **B40** | 2300 MHz | Asia, India | Ampliamente usada en India |
| **B41** | 2500 MHz | Global | Sprint, China Mobile, India. Alta capacidad |
| **B42** | 3500 MHz | Europa | También usada para 5G NR |
| **B43** | 3700 MHz | Europa | También usada para 5G NR |

### Bandas por región

**Norteamérica (EE.UU. y Canadá):**
- Principales: B2, B4, B5, B7, B12, B13, B17, B25, B26, B41, B66, B71
- Si compras un teléfono para usar en EE.UU., asegúrate de que soporte B2, B4, B12/B13 como mínimo.

**Europa:**
- Principales: B1, B3, B7, B8, B20, B28, B38
- La combinación B3 + B7 + B20 cubre la mayoría de operadores europeos.

**América Latina:**
- Principales: B1, B2, B3, B4, B5, B7, B28
- Varía significativamente por país. B4 y B7 son comunes en México, Brasil usa mucho B7 y B28.

**Cuba y el Caribe:**
- ETECSA (Cuba) opera principalmente en B3 (1800 MHz) y B1 (2100 MHz) para LTE.
- En el Caribe en general: B2, B4, B7, B13 son comunes dependiendo del operador.

**Asia:**
- Principales: B1, B3, B5, B7, B8, B28, B38, B39, B40, B41
- China tiene un ecosistema particular con B39, B40 y B41 (TD-LTE).

**África:**
- Principales: B1, B3, B7, B8, B20, B28
- B8 y B3 son las más extendidas.

### ¿Por qué importan las bandas al comprar un teléfono?

Un teléfono solo puede conectarse a las bandas para las que tiene hardware de radio compatible. Si compras un teléfono diseñado para el mercado asiático y lo usas en Estados Unidos, es posible que no soporte las bandas locales y tengas poca o ninguna cobertura LTE.

**Recomendaciones:**
- Antes de comprar un teléfono importado, verifica qué bandas LTE soporta (esta información está en las especificaciones técnicas).
- Compara esas bandas con las que usa tu operador en tu país.
- Los teléfonos "globales" o "desbloqueados internacionales" suelen soportar más bandas.

---

## 3. Tipo de red preferida en Android

### ¿Qué es el tipo de red preferida?

El "tipo de red preferida" (Preferred Network Type) es una configuración interna de Android que le dice al módem del teléfono qué tecnologías de red puede usar y cuáles debe priorizar. Esta configuración determina a qué generación de red se conectará tu teléfono.

### Modos disponibles

Los modos disponibles varían según el fabricante, el modelo y el operador, pero los más comunes son:

| Modo | Descripción | Comportamiento |
|------|-------------|----------------|
| **LTE/WCDMA/GSM (auto)** | Modo automático completo | El teléfono elige la mejor red disponible. Prioriza LTE, luego 3G, luego 2G. Es el modo predeterminado. |
| **LTE/WCDMA** | 4G y 3G solamente | El teléfono usa LTE o 3G, pero nunca baja a 2G. |
| **LTE Only** | Solo 4G | El teléfono **solo** se conecta a LTE. No usa 3G ni 2G bajo ninguna circunstancia. |
| **WCDMA/GSM (auto)** | 3G y 2G solamente | El teléfono no usa LTE. Útil si LTE tiene problemas en tu zona. |
| **WCDMA Only** | Solo 3G | Solo usa UMTS/HSPA. No usa LTE ni 2G. |
| **GSM Only** | Solo 2G | Solo usa GSM/EDGE. Velocidades muy bajas pero máxima cobertura y ahorro de batería. |
| **LTE/TD-SCDMA/UMTS** | 4G/3G (variante con TD-SCDMA) | Común en teléfonos vendidos en China. Incluye soporte para TD-SCDMA. |
| **LTE/TD-SCDMA/WCDMA/GSM** | Todas las tecnologías | Modo automático que incluye TD-SCDMA. |

### ¿Cómo elige el teléfono la red?

En modo automático, el teléfono sigue estas reglas generales:

1. **Escanea** todas las bandas y tecnologías disponibles.
2. **Prioriza** la tecnología más alta (LTE > 3G > 2G).
3. **Evalúa** la calidad de la señal. Si LTE tiene señal muy débil, puede bajar a 3G.
4. **Considera** las instrucciones del operador. Las tarjetas SIM contienen configuraciones del operador que pueden influir en la selección.
5. **Hace handover** (traspaso) entre tecnologías cuando te mueves. Por ejemplo, si sales de una zona con cobertura LTE, el teléfono baja a 3G automáticamente.

### El problema del modo automático

En el modo automático, el teléfono puede tomar decisiones subóptimas:

- **Ping-pong entre redes:** En zonas con cobertura límite, el teléfono puede alternar constantemente entre LTE y 3G, causando desconexiones y lentitud.
- **Bajada innecesaria a 2G/3G:** Algunos operadores configuran sus redes para que el teléfono baje a 3G durante las llamadas de voz (Circuit-Switched Fallback o CSFB), y a veces el teléfono tarda en volver a LTE.
- **Congestión en 3G:** En áreas donde mucha gente está en 3G, la experiencia puede ser peor que si forzaras LTE, incluso con señal LTE más débil.

Es por esto que forzar LTE puede mejorar significativamente la experiencia en ciertos escenarios.

---

## 4. ¿Qué es forzar 4G?

### Definición

Forzar 4G significa configurar el teléfono para que **únicamente** se conecte a redes LTE, ignorando por completo las redes 2G y 3G. El teléfono no bajará a una tecnología inferior bajo ninguna circunstancia, ni siquiera si la señal LTE es débil.

### ¿Por qué forzar 4G?

**1. Velocidades de datos superiores**
LTE ofrece velocidades significativamente mayores que 3G. Si estás en una zona con cobertura LTE, forzar 4G garantiza que siempre obtendrás las mejores velocidades disponibles.

**2. Evitar la congestión de 3G**
En muchas áreas, las redes 3G están congestionadas porque comparten capacidad con muchos usuarios. La red LTE, al tener más capacidad y ancho de banda, suele estar menos congestionada.

**3. Estabilidad de conexión**
El cambio automático entre redes (handover) puede causar interrupciones breves. Si LTE está disponible de forma estable en tu zona, forzar 4G elimina estos cambios y proporciona una conexión más consistente.

**4. Menor latencia**
LTE tiene una latencia típica de 30-50 ms, mientras que 3G suele tener 70-200 ms. Para juegos en línea, videollamadas y navegación web, esta diferencia es notable.

**5. Evitar el downgrade forzado por el operador**
Algunos operadores configuran sus redes de forma agresiva para bajar dispositivos a 3G, especialmente durante llamadas (CSFB). Forzar LTE previene esto (aunque también previene las llamadas si no tienes VoLTE).

**6. Pruebas y diagnóstico**
Los técnicos de red y los usuarios avanzados usan el modo LTE Only para probar la cobertura y el rendimiento de la red LTE en una zona específica, sin la "interferencia" de las redes legacy.

### Lo que pierdes al forzar 4G

- **Llamadas de voz** (en la mayoría de teléfonos): Las llamadas telefónicas tradicionales usan tecnología de circuitos conmutados que solo está disponible en 2G y 3G. Sin VoLTE activado, tu teléfono no podrá hacer ni recibir llamadas.
- **SMS** (en algunos casos): Similar a las llamadas. Sin VoLTE/VoNR, los SMS pueden no funcionar.
- **Cobertura**: Si te mueves a una zona sin cobertura LTE, perderás la conexión por completo. En modo automático, el teléfono habría bajado a 3G o 2G.
- **Llamadas de emergencia**: En algunos teléfonos y redes, forzar LTE Only puede impedir las llamadas de emergencia (112, 911). Aunque muchos teléfonos tienen mecanismos de seguridad que permiten llamadas de emergencia independientemente de la configuración, no es garantizado.

---

## 5. VoLTE (Voice over LTE)

### ¿Qué es VoLTE?

VoLTE (Voz sobre LTE) es una tecnología que permite realizar llamadas de voz directamente a través de la red LTE, sin necesidad de bajar a 2G o 3G. La voz se transmite como datos IP, similar a una llamada de WhatsApp o Telegram, pero integrada a nivel de red.

### Ventajas de VoLTE

- **Calidad de voz superior:** VoLTE usa códecs de audio de banda ancha (AMR-WB o EVS), ofreciendo calidad de voz HD significativamente mejor que las llamadas 2G/3G.
- **Conexión más rápida:** Las llamadas VoLTE se establecen en 1-2 segundos, comparado con 5-8 segundos en llamadas 3G (que requieren CSFB).
- **Datos simultáneos:** Puedes navegar por internet a velocidad LTE mientras hablas por teléfono.
- **Menor consumo de batería:** No hay cambio de red (el cambio de LTE a 3G y vuelta consume energía extra del radio).
- **Compatibilidad con LTE Only:** Con VoLTE activado, puedes forzar LTE Only sin perder la capacidad de hacer y recibir llamadas y SMS.

### ¿Cómo saber si tienes VoLTE?

1. **Indicador en la barra de estado:** Cuando VoLTE está activo, muchos teléfonos muestran un ícono "VoLTE" o "HD" junto al indicador de señal.
2. **Ajustes del teléfono:** Ve a Ajustes → Red móvil → Llamadas VoLTE (o similar). Si la opción existe y está activada, tu teléfono soporta VoLTE.
3. **Durante una llamada:** Si al hacer una llamada el indicador de red sigue mostrando "4G" o "LTE" en lugar de bajar a "3G" o "H+", VoLTE está funcionando.
4. **Código de servicio:** En algunos teléfonos, marca `*#*#4636#*#*` → Phone Information y busca "VoLTE Provisioned" o similar.

### Requisitos para VoLTE

Para que VoLTE funcione, se necesitan **tres** condiciones simultáneamente:

1. **El teléfono debe soportar VoLTE:** No todos los teléfonos lo soportan. Los gama media y alta de los últimos 5-6 años generalmente sí.
2. **El operador debe tener VoLTE desplegado:** No todos los operadores ofrecen VoLTE, especialmente en países en desarrollo.
3. **El teléfono debe estar habilitado/certificado para VoLTE con ese operador específico:** Algunos operadores solo habilitan VoLTE en teléfonos que han certificado. Un teléfono que soporta VoLTE con un operador puede no soportarlo con otro.

### VoWiFi (Voice over WiFi)

Similar a VoLTE, VoWiFi permite hacer llamadas a través de WiFi cuando la cobertura celular es mala. Si tu operador soporta VoWiFi, puedes activarlo como complemento de VoLTE para garantizar cobertura de voz en interiores.

---

## 6. RadioInfo y el menú de ingeniería

### ¿Qué es RadioInfo?

RadioInfo es una actividad oculta dentro de la aplicación del sistema "Phone" de Android (com.android.phone). Proporciona acceso directo a información detallada sobre el módem de radio del teléfono y permite modificar configuraciones de red que normalmente no están disponibles en los ajustes regulares.

### Cómo acceder

Existen varias formas de acceder a RadioInfo:

1. **A través de 4GForce:** La app abre RadioInfo directamente.
2. **Código de marcación:** Abre la aplicación de Teléfono y marca `*#*#4636#*#*`. Esto abre el menú de pruebas, donde puedes seleccionar "Phone Information" para llegar a RadioInfo.
3. **ADB (para desarrolladores):** Desde una computadora con ADB:
   ```
   adb shell am start -n com.android.phone/.settings.RadioInfo
   ```
   o en versiones más antiguas:
   ```
   adb shell am start -n com.android.phone/.RadioInfo
   ```

### Información mostrada en RadioInfo

RadioInfo muestra una gran cantidad de información técnica:

- **IMEI:** El identificador único del módem celular.
- **Phone Number:** El número de teléfono asociado a la SIM (si está disponible).
- **Network:** El operador al que estás conectado actualmente.
- **Signal Strength:** La intensidad de la señal en dBm y ASU.
  - **dBm (decibelios-miliwatts):** Valores típicos:
    - LTE: -44 dBm (excelente) a -140 dBm (sin señal). Por encima de -80 dBm es bueno, por debajo de -100 dBm es débil.
    - 3G: -60 dBm (excelente) a -120 dBm (sin señal).
  - **ASU (Arbitrary Strength Unit):** Una escala que varía según la tecnología. Para LTE, ASU = RSRP + 140.
- **Service State:** Estado del servicio (In Service, Out of Service, Emergency Only).
- **Data State:** Estado de la conexión de datos (Connected, Disconnected, Suspended).
- **Data Network Type:** La tecnología de datos activa (LTE, HSPA+, UMTS, EDGE, etc.).
- **Voice Network Type:** La tecnología de voz activa.
- **Roaming:** Indica si estás en roaming (itinerancia).

### Set Preferred Network Type (Tipo de red preferida)

Esta es la opción más importante para forzar 4G. Es un menú desplegable que muestra todos los modos de red soportados por tu teléfono. Los valores más comunes y sus códigos internos son:

| Valor | Código RIL | Descripción |
|-------|-----------|-------------|
| LTE/WCDMA/GSM (PRL) | 9 | Automático con prioridad LTE |
| LTE/GSM/WCDMA | 9 | Igual que el anterior (nombre alternativo) |
| LTE Only | 11 | **Solo LTE — el que usa 4GForce** |
| LTE/WCDMA | 12 | LTE y 3G, sin 2G |
| WCDMA preferred | 0 | 3G preferido, con fallback a 2G |
| GSM Only | 1 | Solo 2G |
| WCDMA Only | 2 | Solo 3G |
| GSM/WCDMA (auto) | 3 | 3G y 2G automático |
| LTE/CDMA/EvDo/GSM/WCDMA | 10 | Automático global (incluye CDMA) |
| NR/LTE/WCDMA/GSM | 33 | 5G/4G/3G/2G automático |
| NR/LTE | 41 | 5G y 4G solamente |
| NR Only | 23 | Solo 5G |

**Nota importante:** Los códigos y opciones disponibles pueden variar significativamente entre fabricantes. Algunos teléfonos muestran todos los modos, otros solo los que el operador ha habilitado.

### Otras opciones útiles en RadioInfo

- **Toggle Radio:** Enciende y apaga el módulo de radio (equivalente al modo avión para la celular).
- **Enable/Disable Data:** Activa o desactiva la conexión de datos.
- **SMSC:** Permite ver y cambiar el centro de mensajes SMS.
- **Ping Test:** Realiza un ping para verificar la conectividad de datos.
- **Cell Info:** Muestra información detallada de la celda a la que estás conectado (Cell ID, LAC/TAC, frecuencia, etc.).

### El menú de pruebas (*#*#4636#*#*)

El código `*#*#4636#*#*` abre un menú más amplio que incluye:

1. **Phone Information:** La pantalla RadioInfo descrita arriba. Si el teléfono tiene dos SIMs, puede haber "Phone Information (SIM 1)" y "Phone Information (SIM 2)".
2. **Battery Information:** Información detallada de la batería (voltaje, temperatura, tecnología, estado de salud).
3. **Usage Statistics:** Estadísticas de uso de aplicaciones (tiempo de primer uso, último uso, veces lanzada).
4. **WiFi Information:** Información sobre la conexión WiFi actual.

**Nota:** No todos los teléfonos responden a este código. Algunos fabricantes (especialmente Samsung en algunas versiones de One UI) lo desactivan o lo redirigen a otro menú. En esos casos, 4GForce intenta abrir RadioInfo directamente a través de su componente interno.

---

## 7. Cómo funciona internamente

### Arquitectura de la pila de red en Android

La selección de red en Android involucra varias capas, desde la aplicación hasta el hardware de radio:

```
┌─────────────────────────────┐
│  Aplicación (4GForce)       │  ← Capa de usuario
├─────────────────────────────┤
│  Framework de Android       │  ← TelephonyManager, PhoneFactory
├─────────────────────────────┤
│  RIL Daemon (rild)          │  ← Capa de interfaz
├─────────────────────────────┤
│  Vendor RIL (libril)        │  ← Implementación del fabricante
├─────────────────────────────┤
│  Modem / Baseband           │  ← Hardware de radio
└─────────────────────────────┘
```

### RIL (Radio Interface Layer)

El RIL es una capa de abstracción fundamental en Android que actúa como puente entre el sistema operativo y el hardware de radio (módem/baseband). Su función es traducir las solicitudes de alto nivel del framework de Android en comandos específicos que entiende el hardware de radio.

**Componentes del RIL:**

1. **rild (RIL Daemon):** Un proceso del sistema que se ejecuta constantemente y maneja la comunicación entre el framework y el hardware.
2. **Vendor RIL Library:** Una biblioteca proporcionada por el fabricante del chipset (Qualcomm, MediaTek, Samsung, etc.) que implementa los comandos específicos del hardware.
3. **RIL Java (Telephony Framework):** La capa Java en el framework de Android que las aplicaciones y servicios del sistema usan para interactuar con la telefonía.

**Flujo de una solicitud de cambio de red:**

1. El usuario selecciona "LTE Only" en RadioInfo (o 4GForce abre RadioInfo).
2. RadioInfo llama a `TelephonyManager.setPreferredNetworkType()`.
3. El framework envía una solicitud `RIL_REQUEST_SET_PREFERRED_NETWORK_TYPE` al rild.
4. rild pasa la solicitud a la librería vendor RIL.
5. La librería vendor traduce esto en comandos AT o QMI (Qualcomm MSM Interface) para el módem.
6. El módem reconfigura su pila de protocolo de radio para solo buscar y conectarse a celdas LTE.
7. El módem confirma el cambio de vuelta a través de la cadena.

### El módem / Baseband

El módem (también llamado baseband processor) es un procesador separado dentro del teléfono dedicado exclusivamente a las comunicaciones celulares. Tiene su propio firmware, su propio sistema operativo en tiempo real (RTOS) y opera independientemente del procesador principal de aplicaciones.

**Fabricantes principales de módems:**
- **Qualcomm (Snapdragon Modem):** Los más extendidos, usados en la mayoría de teléfonos Android.
- **MediaTek:** Comunes en teléfonos de gama media y económica.
- **Samsung (Exynos Modem):** Usados en algunos Galaxy (especialmente en mercados fuera de EE.UU.).
- **HiSilicon (Balong):** Usados en teléfonos Huawei.

**Cuando cambias a LTE Only:**

1. El módem deja de escanear frecuencias 2G y 3G.
2. Solo mantiene las tablas de celdas vecinas LTE.
3. Si pierde la señal LTE, entra en estado "No Service" en lugar de buscar redes alternativas.
4. El ahorro de no escanear múltiples tecnologías puede mejorar ligeramente la batería en áreas con buena cobertura LTE.

### Persistencia de la configuración

Un detalle técnico importante: cuando cambias el tipo de red preferida a través de RadioInfo, este cambio se guarda en la configuración del sistema de Android (`Settings.Global.PREFERRED_NETWORK_MODE`). Sin embargo:

- Algunos teléfonos **persisten** el cambio incluso después de reiniciar.
- Otros teléfonos **revierten** al modo predeterminado del operador después de reiniciar.
- Actualizar el sistema operativo o la configuración del operador puede revertir el cambio.
- Extraer y volver a insertar la SIM puede revertir el cambio.

---

## 8. Casos de uso comunes

### Optimización de velocidad de datos

**Escenario:** Estás en una zona urbana con cobertura LTE estable pero notas que las velocidades son inconsistentes.

**Causa probable:** Tu teléfono está alternando entre LTE y 3G, o el operador está forzando CSFB (bajada a 3G para llamadas) y el teléfono tarda en volver a LTE.

**Solución:** Forzar LTE Only para mantener una conexión estable y velocidades consistentes. Asegúrate de tener VoLTE activo si necesitas recibir llamadas.

### Pruebas de cobertura LTE

**Escenario:** Eres técnico de red y necesitas verificar la cobertura LTE en una zona específica.

**Uso:** Forzar LTE Only y moverte por la zona observando la intensidad de señal (RSRP, RSRQ, SINR) en RadioInfo. Esto te da una imagen clara de la cobertura LTE sin que el teléfono enmascare problemas bajando a 3G.

### Evitar congestión de 3G

**Escenario:** Estás en un evento masivo (concierto, estadio) o en una zona con infraestructura de red limitada. La conexión de datos es extremadamente lenta.

**Causa probable:** La mayoría de los usuarios están conectados a 3G, congestionando esa red. La red LTE puede estar menos cargada porque no todos los teléfonos la soportan o porque el operador tiene más capacidad LTE.

**Solución:** Forzar LTE Only puede darte acceso a una red menos congestionada con mejores velocidades.

### Dispositivos IoT y routers 4G

**Escenario:** Tienes un router 4G portátil o un dispositivo IoT que solo necesita datos y no voz.

**Uso:** Forzar LTE Only es ideal para estos dispositivos ya que no necesitan hacer llamadas y te aseguras de que siempre usen la conexión más rápida disponible.

### Zonas rurales con señal mixta

**Escenario:** Vives en una zona rural donde hay señal LTE débil pero funcional, y señal 3G fuerte. Tu teléfono prefiere 3G por la señal más fuerte, pero la velocidad es muy baja.

**Solución:** Forzar LTE Only te mantiene en la red más rápida, aunque la señal sea más débil. Mientras el RSRP sea superior a -115 dBm aproximadamente, LTE seguirá funcionando.

### Speed testing y benchmarking

**Escenario:** Quieres medir el rendimiento real de la red LTE de tu operador.

**Uso:** Forzar LTE Only antes de ejecutar pruebas de velocidad (Speedtest, Fast.com) garantiza que los resultados reflejen exclusivamente el rendimiento LTE, sin interferencia de handovers o cambios de red durante la prueba.

### Ahorro de batería selectivo

**Escenario:** Estás en una zona con buena cobertura LTE y no necesitas llamadas (usas WhatsApp/Telegram para comunicarte).

**Uso:** Forzar LTE Only puede ahorrar un poco de batería al evitar que el módem escanee constantemente redes 2G y 3G.

---

## 9. Riesgos y precauciones

### Pérdida de llamadas y SMS

**Este es el riesgo más importante.** Sin VoLTE activado y funcionando, forzar LTE Only significa:

- **No puedes hacer llamadas telefónicas.** El teléfono no tiene acceso a las redes 2G/3G necesarias para las llamadas de circuito conmutado.
- **No puedes recibir llamadas.** Las llamadas entrantes no llegarán a tu teléfono.
- **Los SMS pueden no funcionar.** Los mensajes de texto tradicionales dependen de la infraestructura 2G/3G en la mayoría de las redes.

**Mitigación:**
- Verifica que VoLTE esté activo antes de forzar LTE Only.
- Si no tienes VoLTE, usa aplicaciones de mensajería (WhatsApp, Telegram, Signal) para comunicarte.
- Recuerda restaurar el modo automático cuando termines de necesitar LTE Only.

### Llamadas de emergencia

**Precaución crítica:** En LTE Only, las llamadas de emergencia (911, 112, etc.) pueden no funcionar en todos los teléfonos y redes.

- Muchos teléfonos tienen un mecanismo de seguridad que permite llamadas de emergencia independientemente de la configuración de red.
- Sin embargo, esto **no está garantizado** en todos los dispositivos.
- Si estás en una situación donde podrías necesitar llamar a emergencias, **no uses LTE Only**.

### Pérdida total de cobertura

En modo automático, si sales de una zona con cobertura LTE, tu teléfono baja automáticamente a 3G o 2G y mantiene la conexión. En LTE Only, perderás toda conexión de red.

**Mitigación:**
- No uses LTE Only si vas a viajar o moverte por zonas con cobertura variable.
- Especialmente peligroso en áreas rurales donde la cobertura LTE es irregular.

### Impacto en la batería

El impacto en la batería de LTE Only puede ir en ambas direcciones:

- **Mejor batería:** Si tienes buena señal LTE estable. El módem no gasta energía escaneando otras redes ni haciendo handovers.
- **Peor batería:** Si la señal LTE es débil o inestable. El módem consume más energía intentando mantener una conexión débil y buscando continuamente celdas LTE mejores.

### Comportamiento después de reiniciar

Algunos teléfonos mantienen la configuración de LTE Only después de reiniciar, otros la revierten al modo predeterminado. No asumas que volverá al modo automático automáticamente.

**Recomendación:** Si has forzado LTE Only, verifica manualmente después de reiniciar si la configuración se mantuvo o se revirtió.

### Actualizaciones del operador

Los operadores pueden enviar actualizaciones de configuración de red (carrier config updates) que restablecen el tipo de red preferida al valor predeterminado. Esto puede ocurrir sin previo aviso.

---

## 10. Preguntas frecuentes

### ¿Forzar 4G daña mi teléfono?

**No.** Cambiar el tipo de red preferida es una configuración de software completamente segura. No afecta al hardware de ninguna manera. Puedes cambiar entre modos tantas veces como quieras.

### ¿Es legal forzar 4G?

**Sí.** Estás cambiando una configuración de tu propio teléfono. No estás hackeando la red ni violando ningún acuerdo de servicio. La opción existe en Android precisamente para ser usada.

### ¿Por qué mi teléfono no tiene la opción "LTE Only"?

Posibles razones:

1. **El operador la ha ocultado:** Algunos operadores configuran el software del teléfono para ocultar opciones avanzadas de red. RadioInfo puede mostrar opciones que el menú de ajustes normal no muestra.
2. **El teléfono no soporta LTE:** Si es un teléfono muy antiguo, puede no tener hardware LTE.
3. **El fabricante la ha eliminado:** Algunos fabricantes eliminan la opción en sus capas de personalización.
4. **El código de marcación no funciona:** El código `*#*#4636#*#*` no funciona en todos los teléfonos. Intenta usar 4GForce que accede directamente al componente RadioInfo.

### ¿Puedo forzar 5G de la misma manera?

Sí, en teléfonos con 5G, RadioInfo generalmente incluye opciones como "NR Only" o "NR/LTE". Sin embargo, las opciones 5G son menos comunes y dependen mucho del fabricante y la versión de Android.

### ¿Por qué mi velocidad no mejora al forzar 4G?

Posibles causas:

1. **Ya estabas en LTE:** Si tu teléfono ya estaba conectado a LTE en modo automático, forzar LTE Only no cambiará nada.
2. **Congestión en LTE:** La red LTE en tu zona puede estar congestionada. Forzar LTE no soluciona esto.
3. **Limitación del plan:** Tu operador puede limitar la velocidad según tu plan de datos.
4. **Señal débil:** Una señal LTE débil (RSRP por debajo de -110 dBm) puede resultar en velocidades bajas.
5. **Limitación del dispositivo:** La categoría LTE de tu teléfono limita la velocidad máxima.

### ¿Cómo vuelvo al modo normal?

1. Abre 4GForce y pulsa el botón para abrir RadioInfo.
2. Busca "Set Preferred Network Type".
3. Selecciona "LTE/WCDMA/GSM (auto)" o similar (el modo que anotaste antes de cambiar).
4. El teléfono volverá al comportamiento automático inmediatamente.

### ¿Funciona con teléfonos dual SIM?

Sí, pero la configuración se aplica por SIM. En teléfonos dual SIM, RadioInfo puede mostrar información de cada SIM por separado ("Phone Information (SIM 1)" y "Phone Information (SIM 2)"). Debes cambiar la configuración en la SIM que quieres forzar a LTE.

### ¿Puedo usar 4GForce con una SIM de datos (solo datos)?

Sí, y de hecho es un uso ideal. Las SIM de datos no necesitan voz ni SMS, así que no pierdes ninguna funcionalidad al forzar LTE Only.

### ¿Por qué 4GForce no cambia la configuración directamente?

Android no permite que aplicaciones normales (sin permisos de sistema/root) cambien el tipo de red preferida programáticamente. Esto es una restricción de seguridad por diseño. La única forma de cambiarlo es a través de la interfaz del sistema (RadioInfo) o con permisos especiales que solo tienen las aplicaciones del sistema.

Por eso 4GForce actúa como un acceso directo: te lleva rápidamente al menú correcto donde puedes hacer el cambio manualmente con un toque.

### ¿Qué significan RSRP, RSRQ y SINR?

Estas son métricas técnicas de la calidad de señal LTE que puedes ver en RadioInfo:

- **RSRP (Reference Signal Received Power):** La potencia de la señal de referencia. Es la métrica principal de intensidad de señal.
  - Excelente: > -80 dBm
  - Buena: -80 a -90 dBm
  - Aceptable: -90 a -100 dBm
  - Débil: -100 a -110 dBm
  - Muy débil: < -110 dBm

- **RSRQ (Reference Signal Received Quality):** La calidad de la señal, que tiene en cuenta la interferencia.
  - Excelente: > -10 dB
  - Buena: -10 a -15 dB
  - Aceptable: -15 a -20 dB
  - Mala: < -20 dB

- **SINR (Signal to Interference plus Noise Ratio):** La relación señal-ruido. Indica qué tan limpia es la señal.
  - Excelente: > 20 dB
  - Buena: 13-20 dB
  - Aceptable: 0-13 dB
  - Mala: < 0 dB

---

*Documentación creada para la aplicación 4GForce. Para más información, visita el [repositorio en GitHub](https://github.com/albertolicea00/apk4gforce).*
