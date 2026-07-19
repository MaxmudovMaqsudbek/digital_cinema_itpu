import requests
import json

url = "http://localhost:8082/seats"
headers = {"Content-Type": "application/json"}
data = {
  "hallId": 14,
  "priceCategoryId": 2,
  "row": 3,
  "number": 7,
  "status": "ACTIVE",
  "isAvailable": True,
  "comment": "Front row center seat"
}

response = requests.post(url, headers=headers, json=data)
print(f"Status Code: {response.status_code}")
print(f"Response: {response.text}")
