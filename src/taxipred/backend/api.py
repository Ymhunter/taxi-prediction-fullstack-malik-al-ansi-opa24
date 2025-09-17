from fastapi import FastAPI
from pydantic import BaseModel
from typing import List
from taxipred.backend.data_processing import TaxiData
import pickle
from taxipred.utils.constants import MODEL_PATH

app = FastAPI()

model = pickle.load(open(MODEL_PATH, "rb"))
taxi_data = TaxiData()

class InputData(BaseModel):
    features: List[List[float]]

@app.get("/taxi/")
async def read_taxi_data():
    """Return the raw taxi trip dataset as JSON records."""
    return taxi_data.to_json()

@app.post("/predict/")
def predict(data: InputData):
    """Predict taxi trip price(s) given input features."""
    prediction = model.predict(data.features).tolist()
    return {"predictions": prediction}
