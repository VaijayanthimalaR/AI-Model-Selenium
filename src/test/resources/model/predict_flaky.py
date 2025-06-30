import sys

# Get duration from command line
duration = float(sys.argv[1])
print(f"🔍 Received duration: {duration} seconds")

# Load model & make prediction (assuming model expects [duration] as input)
import pickle
import numpy as np

with open("src/test/resources/model/flaky_test_model.pkl", "rb") as f:
    model = pickle.load(f)

X = np.array([[duration]])
prediction = model.predict(X)

if prediction[0] == 1:
    print("Flaky")
else:
    print("Not Flaky")
with open("src/test/resources/model/flaky_report.html", "w", encoding="utf-8") as report:
    report.write("<html><head><title>Flaky Test Report</title></head><body>")
    report.write("<h2>🧠 Flaky Test Prediction</h2>")
    report.write(f"<p><strong>Test Name:</strong> LoginTest</p>")
    report.write(f"<p><strong>Predicted as Flaky:</strong> {'Yes' if prediction[0] == 1 else 'No'}</p>")
    report.write("</body></html>")
