# 📂 Universal File Converter - Professional Edition

**A robust, containerized file conversion API built with Java Spring Boot.**

This application allows users to convert files between various formats (PDF, DOCX, Images) through a clean, modern web interface. It relies on a scalable **Factory Pattern** architecture to easily manage different conversion strategies.

---

## 🚀 Live Demo

You can try the application directly in your browser without installing anything:

👉 **[Access Live Application](https://file-converter-production-30dd.up.railway.app/)**

---


## ⚠️ Usage Limits

**Operational Constraints:**
To ensure optimal performance and server stability, please adhere to the following limits:
* **Maximum File Size:** 1 GB per upload.
* **Batch Processing:** Maximum of 15 files per upload request.

**Please Read Carefully:**

This software is provided as a "Many-to-Many" file conversion proof-of-concept.
* **Conversion Quality:** While standard files convert well, complex documents (e.g., advanced formatting in Word, specific layers in PDF) may not render perfectly.
* **Format Support:** Not all combinations of input/output formats are guaranteed to work or produce the desired output.
* **Data Safety:** This application processes files temporarily. However, **do not upload sensitive or private data** (passwords, banking info, personal IDs) to the public demo server.

**THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.**


---

## ✨ Features

* **Document Conversion:**
    * DOCX to PDF
    * PDF to DOCX
* **Image Conversion:**
    * Images (JPG, PNG, BMP, GIF, TIFF) to PDF
    * PDF to Images
    * Format to Format (e.g., JPG to PNG)
    * HEIC Support (requires ImageMagick)
* **Architecture:**
    * Clean **Service-Controller** separation.
    * **Factory Pattern**  for dynamic converter selection.
    * **Dockerized** for easy deployment.

---

## 🛠️ Installation & Setup

You can run this project on your own computer using either Docker (recommended) or Java directly.

### Prerequisites
* **Git** installed.
* **Docker** (Recommended) OR **Java JDK 17+** and **Maven**.

### Option 1: Running with Docker

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/yassineyandouzi/Universal-File-Converter.git](https://github.com/yassineyandouzi/Universal-File-Converter.git)
    cd Universal-File-Converter
    ```

2.  **Build the Docker Image:**
    ```bash
    docker build -t file-converter .
    ```

3.  **Run the Container:**
    ```bash
    docker run -p 8080:8080 file-converter
    ```

4.  **Access the app:**
    Open your browser and go to `http://localhost:8080`

### Option 2: Running with Java/Maven

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/yassineyandouzi/Universal-File-Converter.git](https://github.com/yassineyandouzi/Universal-File-Converter.git)
    cd Universal-File-Converter
    ```

2.  **Compile and Run:**
    * **Windows (PowerShell):**
        ```powershell
        .\mvnw.cmd spring-boot:run
        ```
    * **Mac/Linux:**
        ```bash
        ./mvnw spring-boot:run
        ```

3.  **Access the app:**
    Open `http://localhost:8080`

---

## 📄 License

This project is licensed under the **GNU General Public License **.

You are free to:
* Use this code for private or commercial use.
* Modify the code.
* Distribute copies.

**Please Read Carefully:**

This software is provided as a "Many-to-Many" file conversion proof-of-concept.
* **Conversion Quality:** While standard files convert well, complex documents (e.g., advanced formatting in Word, specific layers in PDF) may not render perfectly.
* **Format Support:** Not all combinations of input/output formats are guaranteed to work or produce the desired output.
* **Data Safety:** This application processes files temporarily. However, **do not upload sensitive or private data** (passwords, banking info, personal IDs) to the public demo server.

---

**Developed by Yassine Yandouzi**
