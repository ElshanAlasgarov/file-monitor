# 📊 File Monitor & Trade Data Viewer

This project is a **JavaFX-based Trade Data Viewer** that monitors a directory (`input/`) for new trade data files (`.csv`, `.txt`, `.xml`).  
When a new file is added, it is automatically processed and displayed in the GUI **without duplicates**.

---

## 🚀 Features
✔ **Monitors the `input/` folder for new trade data files**  
✔ **Supports `.csv`, `.txt`, `.xml` formats**  
✔ **Automatically adds new data to the GUI**  
✔ **Prevents duplicate file processing**  
✔ **Real-time updates using JavaFX**

---

## 🛠️ Installation & Setup

##  Configuration

The config.properties file is used to configure the project. You can change the following settings in the file:

# Directory path
input.directory=/path/to/your/directory

# Directory monitoring interval (in milliseconds)
monitor.interval=5000

### 1️⃣ **Clone the Repository**
```sh
git clone https://github.com/ElshanAlasgarov/file-monitor.git
cd file-monitor
