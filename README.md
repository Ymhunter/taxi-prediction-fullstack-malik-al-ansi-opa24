🚖 Taxi Fare Prediction – Fullstack Project

This project is a fullstack machine learning application that predicts taxi fares based on trip details. It consists of:

📊 ML Model (Random Forest Regressor) – trained on historical taxi trip data.

⚡ Backend (FastAPI) – serves predictions via REST API.

📱 Frontend (Android App) – lets users input trip details and get fare predictions.

taxi-prediction-fullstack/
│
├── backend/                     # FastAPI + ML model
│   ├── src/taxipred/
│   │   ├── backend/
│   │   │   ├── api.py           # FastAPI routes (/taxi, /predict)
│   │   │   ├── data_processing.py
│   │   ├── utils/
│   │   │   └── constants.py     # Paths & configs
│   │
│   ├── model.pkl                # Trained RandomForest model
│   ├── taxi_trip_pricing.csv    # Training dataset
│   └── requirements.txt         # Backend dependencies
│
├── app/                         # Android frontend
│   ├── src/main/java/com/example/taxi_prediction_android/
│   │   ├── MainActivity.kt
│   │   ├── Utilities/
│   │   │   ├── ApiClient.kt     # Retrofit setup
│   │   │   └── TaxiApi.kt       # API interface
│   │   ├── Commen/
│   │   │   ├── InputData.kt     # Request model
│   │   │   ├── PredictionResponse.kt # Response model
│   │   │   └── TaxiTrip.kt      # Dataset model
│   │
│   ├── res/layout/
│   │   └── activity_main.xml    # Android UI layout
│   ├── build.gradle.kts         # App-level Gradle config
│   └── settings.gradle.kts      # Project Gradle config
│
└── README.md


https://github.com/user-attachments/assets/89890d9d-f8f6-434d-83e2-bb00f0cea192

