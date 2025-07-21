# Agra Voyager  Agra Voyager 

Welcome to **Agra Voyager**, a smart desktop tour planner designed to help tourists discover the real Agra, beyond the usual monuments. This application creates personalized, intelligent itineraries based on user interests, travel pace, and dates, ensuring a unique and hassle-free experience.

![Agra Voyager Screenshot](https://github.com/vp007-dev/Agra-Voyager/blob/main/Capture.PNG)

## ✨ Features

-   **Smart Itinerary Generation:** Creates a day-by-day tour plan based on your unique preferences.
-   **Interest-Based Planning:** Choose from categories like History, Food, Shopping, Gardens, and more to get a plan that truly interests you.
-   **Customizable Pace:** Select a relaxed, moderate, or packed schedule to match your travel style.
-   **Intelligent Scheduling:** Automatically considers closed days (like the Taj Mahal on Fridays) to avoid disappointment.
-   **External JSON Database:** All attraction data is loaded from an external `attractions.json` file, making it incredibly easy to update and expand the list of places.
-   **Modern, Themed UI:** A beautiful and intuitive user interface built with JavaFX and a custom "Serene Sky" theme.

## 💻 Technology Stack

-   **Core:** Java 17
-   **Framework:** JavaFX 21
-   **Build Tool:** Apache Maven
-   **Data Handling:** Jackson (for JSON parsing)

## 🚀 How to Run

To get the Agra Voyager application running on your local machine, follow these steps:

**Prerequisites:**
-   Java Development Kit (JDK) 17 or newer.
-   Apache Maven.
-   An IDE like IntelliJ IDEA or Eclipse.

**Setup:**
1.  **Clone the repository:**
    ```bash
    git clone <your-repository-url>
    ```
2.  **Open the project** in your favorite IDE.
3.  **Set up the database:**
    -   Find the `attractions.json` file in the project.
    -   Move this file to your user home directory (e.g., `C:\Users\YourUsername\` on Windows). This is where the application will look for it.
4.  **Build the project:** Your IDE should automatically detect the `pom.xml` and download the required Maven dependencies. If not, trigger a manual Maven reload/sync.
5.  **Run the application:** Locate the `AgraVoyagerApp.java` file and run its `main` method.

---

##  Agra's Hidden Gems: Contribute to Our Database! (For Agra Locals)

This project's heart is its data, and the best data comes from the people who know the city best: **you!**

If you are a resident of Agra, you can help us build the most authentic and comprehensive guide to our city. We are looking for the hidden gems that tourists often miss.

**How to Contribute:**
1.  Click the link below to open our contribution form.
2.  Fill in the details about your favorite local spot—be it a food joint, a unique shop, a park, or anything else.
3.  That's it! We will review the submissions and add them to the app's database.

Your contribution will help thousands of visitors experience the true culture and beauty of Agra.

➡️ **[Click Here to Add a Hidden Gem to Agra Voyager](https://forms.gle/pN5ary3RA5buHy8f7)**

---

## 💡 Future Ideas

-   [ ] Integrate a mapping API to show routes between attractions.
-   [ ] Add a user rating and review system for each place.
-   [ ] Implement multi-language support.
-   [ ] Create a feature to export the final itinerary as a PDF.

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
