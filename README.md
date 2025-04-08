# ✈️ Flight Helper – Smart Flight Tracking Android App

**Flight Helper** is a modern and intuitive Android application built using **Jetpack Compose**. It allows users to track real-time flight information simply by entering a flight number. The app features a sleek UI and auto-refreshes data every **2 minutes** to keep you updated on the go.

---

## 🌟 Features

- 🔍 **Flight Lookup:** Search flights by number and get real-time data.
- 🛰️ **Live Tracking:** Monitor a flight’s live location minute-by-minute.
- 🧠 **Smart Delay Insights:** View average journey times and delay patterns.
- 🗃️ **Local Data Storage:** Store historical flight data using Room database.
- ⚙️ **Background Updates:** Automatic background data collection for analytics.

---

## 🌿 Project Structure

### `main` Branch – Core Functionality  
The main branch provides the essential features of Flight Helper, including flight lookup and live tracking.

### 🛫 `flight-tracker` – Real-Time Journey Tracker  
> Track a friend’s or loved one’s flight in real-time.

- Input flight number.
- Receive live location updates every minute.
- Designed for personal travel tracking use cases.

### 📊 `flight-duration-delay-analytics` – Delay Analytics & Recommendations  
> Predict travel time based on real data.

- Collects data from 3 flights per day per route.
- Stores in a **Room database** locally.
- Computes average journey time, including delays.
- Ideal for generating future travel time estimates.

---

## 📦 Installation

1. **Clone the repository**  
   ```bash
   git clone https://github.com/Guneet-Pal-Singh/Flight_Helper
