🌦️ Weather App
A simple Android weather application built using Java and Android Studio. It fetches real-time weather data using the OpenWeatherMap API and displays basic weather information for a searched city.

📱 Features
Search for any city worldwide

Displays:

Temperature

Feels Like

Minimum & Maximum Temperature

Humidity

Pressure

Built with Android Studio and Java

Uses AsyncTask for background network calls

🔧 Technologies Used
Java

Android SDK

OpenWeatherMap API

AsyncTask for API requests

HttpURLConnection for network operations

JSON parsing with org.json

🌐 API Used
This app uses the OpenWeatherMap API to get weather data.

Example API Call:

https://api.openweathermap.org/data/2.5/weather?q=London&appid=YOUR_API_KEY
Make sure to replace YOUR_API_KEY with your personal API key from OpenWeatherMap.

🚀 How to Run
Clone the repository:

git clone https://github.com/your-username/weather-app.git
Open in Android Studio.

Replace the API key in MainActivity.java:

url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=YOUR_API_KEY";
Run the app on an emulator or physical device.
