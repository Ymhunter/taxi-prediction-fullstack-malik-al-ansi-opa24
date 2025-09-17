import streamlit as st
import requests

API_URL = "http://127.0.0.1:8000/predict/"

st.set_page_config(page_title="🚖 Taxi Fare Predictor", layout="centered")
st.title("Taxi Fare Prediction")
st.write("Enter trip details and get the predicted price, or click an example below.")

# === Define example trips ===
examples = {
    "Morning commute (short)": {
        "trip_distance": 5.0,
        "passenger_count": 1,
        "base_fare": 3.5,
        "per_km_rate": 0.75,
        "per_minute_rate": 0.25,
        "trip_duration": 15.0,
        "time_of_day": "Morning",
        "day_of_week": "Weekday",
        "traffic_conditions": "Medium",
        "weather": "Clear",
    },
    "Evening rush hour (long)": {
        "trip_distance": 20.0,
        "passenger_count": 2,
        "base_fare": 3.5,
        "per_km_rate": 0.80,
        "per_minute_rate": 0.30,
        "trip_duration": 50.0,
        "time_of_day": "Evening",
        "day_of_week": "Weekday",
        "traffic_conditions": "High",
        "weather": "Rainy",
    },
    "Weekend leisure ride": {
        "trip_distance": 12.0,
        "passenger_count": 3,
        "base_fare": 3.0,
        "per_km_rate": 0.70,
        "per_minute_rate": 0.20,
        "trip_duration": 30.0,
        "time_of_day": "Afternoon",
        "day_of_week": "Weekend",
        "traffic_conditions": "Low",
        "weather": "Clear",
    },
}

choice = st.selectbox("🔹 Load an example trip:", ["-- None --"] + list(examples.keys()))

if choice != "-- None --":
    st.session_state.update(examples[choice])

trip_distance = st.number_input("Trip Distance (km)", min_value=0.0, step=0.1,
                                key="trip_distance")
passenger_count = st.number_input("Passenger Count", min_value=1, step=1,
                                  key="passenger_count")
base_fare = st.number_input("Base Fare ($)", min_value=0.0, step=0.1,
                            key="base_fare")
per_km_rate = st.number_input("Per Km Rate ($)", min_value=0.0, step=0.1,
                              key="per_km_rate")
per_minute_rate = st.number_input("Per Minute Rate ($)", min_value=0.0, step=0.1,
                                  key="per_minute_rate")
trip_duration = st.number_input("Trip Duration (minutes)", min_value=0.0, step=0.1,
                                key="trip_duration")

time_of_day = st.selectbox("Time of Day", ["Morning", "Afternoon", "Evening", "Night"],
                           key="time_of_day")
day_of_week = st.selectbox("Day of Week", ["Weekday", "Weekend"],
                           key="day_of_week")
traffic_conditions = st.selectbox("Traffic Conditions", ["Low", "Medium", "High"],
                                  key="traffic_conditions")
weather = st.selectbox("Weather", ["Clear", "Rainy", "Snowy"],
                       key="weather")

def encode_input():
    return [
        trip_distance,
        passenger_count,
        base_fare,
        per_km_rate,
        per_minute_rate,
        trip_duration,
        1.0 if time_of_day == "Morning" else 0.0,
        1.0 if time_of_day == "Afternoon" else 0.0,
        1.0 if time_of_day == "Evening" else 0.0,
        1.0 if time_of_day == "Night" else 0.0,
        1.0 if day_of_week == "Weekday" else 0.0,
        1.0 if day_of_week == "Weekend" else 0.0,
        1.0 if traffic_conditions == "Low" else 0.0,
        1.0 if traffic_conditions == "Medium" else 0.0,
        1.0 if traffic_conditions == "High" else 0.0,
        1.0 if weather == "Clear" else 0.0,
        1.0 if weather == "Rainy" else 0.0,
        1.0 if weather == "Snowy" else 0.0
    ]

if st.button("Predict Fare"):
    input_data = {"features": [encode_input()]}
    response = requests.post(API_URL, json=input_data)
    if response.status_code == 200:
        prediction = response.json()["predictions"][0]
        st.success(f"💰 Estimated Trip Price: **${prediction:.2f}**")
    else:
     st.error("Error: " + response.text)

