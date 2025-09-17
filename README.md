🚖 Taxi Fare Prediction – Fullstack Project

This project is a fullstack machine learning application that predicts taxi fares based on trip details. It consists of:

📊 ML Model (Random Forest Regressor) – trained on historical taxi trip data.

⚡ Backend (FastAPI) – serves predictions via REST API.

📱 Frontend (Android App) – lets users input trip details and get fare predictions.

📂 Project Structure
taxi-prediction-fullstack/
│── backend/ (FastAPI API)
│   ├── src/taxipred/backend/api.py      # FastAPI app
│   ├── src/taxipred/backend/data_processing.py
│   ├── src/taxipred/utils/constants.py  # Paths & configs
│   ├── model.pkl                        # Trained RandomForest model
│   └── taxi_trip_pricing.csv            # Training dataset
│
│── app/ (Android frontend)
│   ├── MainActivity.kt                  # Main UI logic
│   ├── Utilities/ApiClient.kt           # Retrofit client
│   ├── Utilities/TaxiApi.kt             # API interface
│   ├── Commen/InputData.kt              # Request model
│   ├── Commen/PredictionResponse.kt     # Response model
│   └── res/layout/activity_main.xml     # UI layout
│
└── README.md


https://github.com/user-attachments/assets/89890d9d-f8f6-434d-83e2-bb00f0cea192

