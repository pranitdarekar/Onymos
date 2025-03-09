# 📈 Stock Trading Engine

A real-time stock trading engine that matches Buy and Sell orders efficiently while handling concurrency. This system executes stock transactions based on predefined matching criteria.

---

## 🚀 Features
- Efficient **Buy-Sell order matching**.
- Supports **1,024 tickers (stocks)**.
- **Lock-free data structures** for high performance.
- **Multithreading support** for real-world trading conditions.
- **O(n) time complexity** for order matching.

---

## 🛠️ Installation & Setup

### **Prerequisites**
Ensure you have:
- **Java 17+** installed (`java -version` to check).
- **Git** installed (`git --version` to check).

### **Clone the Repository**
```sh
git clone https://github.com/pranitdarekar/Onymos.git
cd Onymos
javac src/Main.java
java Main

