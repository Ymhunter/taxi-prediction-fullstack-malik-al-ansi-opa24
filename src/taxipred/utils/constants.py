from importlib.resources import files
import os
TAXI_CSV_PATH = files("taxipred").joinpath("data/taxi_trip_pricing.csv")

# DATA_PATH = Path(__file__).parents[1] / "data"
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))  # src/taxipred
MODEL_PATH = os.path.join(BASE_DIR, "data", "random_forest_model.sav")