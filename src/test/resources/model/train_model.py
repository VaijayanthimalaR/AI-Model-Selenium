# src/test/resources/model/train_model.py
import pickle
from sklearn.tree import DecisionTreeClassifier
import numpy as np

# Dummy training data
X = np.array([[5.6], [7.2], [2.3], [6.0]])
y = ['flaky', 'not_flaky', 'flaky', 'not_flaky']

model = DecisionTreeClassifier()
model.fit(X, y)

# Save model
with open("src/test/resources/model/flaky_test_model.pkl", "wb") as f:
    pickle.dump(model, f)
print("✅ Model trained and saved successfully")
