## Traffic Data Analyser  

### Description  
A Java program to analyse traffic data collected in half-hour intervals. The program processes a dataset of timestamps and vehicle counts to produce various insights, such as daily totals, peak traffic periods, and the quietest 90-minute windows.  

### ✨ Features  
This program provides analysis for the following use cases:

**Total Cars Counted:**  
Calculates the total number of vehicles recorded in the dataset.

**Daily Car Count:**  
Aggregates vehicle counts by date.

**Top 3 Peak Periods:**  
Identifies the top three half-hour periods with the highest vehicle counts.

**Quietest 90-Minute Period:**  
Finds the 90-minute window (3 consecutive half-hour entries) with the fewest cars.  

### 🔧 How It Works
* FileReader.java – Reads and structures the data into maps
* DataAnalyser.java – Performs all data analysis tasks
* Main.java – Coordinates everything and prints results to console

### ▶️ How to Run  
* Clone or Download this repository. 
* Open in IntelliJ IDEA (or any IDE that supports Java). 
* Ensure the data.txt file is placed in the src/main/resources/ directory. 
* Run the Main.java class.

### ⚙️ Requirements  
* Java 8 or higher
* Java-compatible IDE

### 📚 Notes
* The timestamp format strictly follows ISO 8601 (yyyy-MM-ddTHH:mm:ss) with no timezone.
