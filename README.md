# 🛒 E-Commerce Application – Spring Boot & Vue 3

Moderne **E-Commerce Webanwendung** mit **Spring Boot (Java 21, Reactive)** im Backend und **Vue 3 + TypeScript** im Frontend.  
Der Fokus liegt auf **skalierbarer Architektur**, **Full-Text-Suche** und **klarer Trennung von Domänen**.

---

<img width="1468" height="829" alt="Bildschirmfoto 2026-02-05 um 12 04 43" src="https://github.com/user-attachments/assets/c7b12cb1-1ed4-4644-a1dd-1ea7059a0d3a" />

<img width="1383" height="828" alt="Bildschirmfoto 2026-02-05 um 12 05 05" src="https://github.com/user-attachments/assets/c2113f76-3fd3-4b36-b65e-4b445a86c3a0" />

<img width="1435" height="788" alt="Bildschirmfoto 2026-02-05 um 12 05 37" src="https://github.com/user-attachments/assets/7af043c8-92ea-4724-b589-d5648c2968b1" />

<img width="1089" height="875" alt="Bildschirmfoto 2026-02-05 um 12 05 56" src="https://github.com/user-attachments/assets/9431b851-8dcf-40f8-a28d-dd822807fe05" />

<img width="996" height="777" alt="Bildschirmfoto 2026-02-05 um 12 08 13" src="https://github.com/user-attachments/assets/5813e602-f190-4289-b224-980f3016e0d5" />

<img width="1442" height="740" alt="Bildschirmfoto 2026-02-05 um 12 09 07" src="https://github.com/user-attachments/assets/bc504c8d-bbb5-4082-b4d7-71bd342f5679" />







## 🧩 Tech Stack

### Backend
- Java 21
- Spring Boot (WebFlux / Reactive)
- Spring Data MongoDB
- Spring Data Elasticsearch
- Spring Data Redis
- Docker & Docker Compose

### Frontend
- Vue 3
- TypeScript
- Vue Router
- Axios
- Bootstrap

---

## 🏗️ Architektur


Frontend (Vue 3)
↓
Spring Boot API (Reactive)
↓
| MongoDB | Elasticsearch | Redis |


### Datenbanken
- **MongoDB** → Hauptdatenbank (Produkte, Accounts, Bestellungen)
- **Elasticsearch** → Full-Text-Suche (ohne Login)
- **Redis** → Temporärer Warenkorb (TTL)

---

## 📦 Domänen

### Produkt
- Produktdetails
- Bilder
- Kategorien
- Preise
- Volltextsuche (Elasticsearch)

### Warenkorb
- Bestellungen
- Versand
- Zahlung
- Lager
- Gast- & User-Warenkorb (Redis)

### Account
- Profil
- Bestellverwaltung
- Favoriten

> Die Architektur ist **erweiterbar** – neue Domänen können einfach ergänzt werden.

---

## ⚙️ Backend – Setup

### Dependencies
- Spring Boot DevTools
- Lombok
- Spring WebFlux
- Spring Data MongoDB (Reactive)
- Spring Data Elasticsearch
- Spring Data Redis

### Projektstruktur (Beispiel: Produkt-Domäne)

/produkt
├─ controller/
├─ entity/
├─ model/
├─ repo/
└─ service/


✔️ **Entities werden nicht direkt zurückgegeben**  
✔️ Kommunikation über Response-Modelle (`ProduktResponse`)

---

## 🐳 Docker Setup

MongoDB, Elasticsearch und Redis laufen über Docker Compose.

```bash
cd src/main/resources
docker-compose up -d



🧪 Demo-Daten

Beim Start der Anwendung werden automatisch 20 Beispielprodukte erzeugt.

StartDemo/
 └─ ProduktDemoData.java


🎨 Frontend – Setup
Installation

npm install -g @vue/cli
vue create webui
cd webui
yarn serve


Frontend läuft unter:

http://localhost:8080

🔗 API-Kommunikation (Axios)

yarn add axios vue-axios

main.js

import { createApp } from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'

axios.defaults.baseURL = 'http://localhost:8080'
createApp(App).use(VueAxios, axios).mount('#app')

🧭 Routing

Produktliste

Produktdetailsvue add router

🛠️ Vue Best Practices
Zweck	Vue Feature
State	data()
Berechnete Werte	computed
Logik	methods
Listen	v-for
🧾 Produktmodell (Kurz)

id

name

description

image

price

moneySymbol (Enum)

category

features

seller

available

freeDelivery

deliveryIn

✔️ Preise in separater Collection
✔️ Lieferzeit & Versandkosten per Service berechnet
✔️ Suche über Elasticsearch

🗃️ File Storage (MinIO / S3)

Produktbilder werden über S3-kompatiblen Storage (MinIO) gespeichert.

application.properties
minio.s3.access-key=root
minio.s3.secret-key=secret
minio.s3.endpoint=http://localhost:9000
