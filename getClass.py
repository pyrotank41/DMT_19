import json

filename = 'property_dataset.json'
desired_data = 'PropertyID'
desired_data2 = 'Bldg Class'
desiredID = '542081'
indexOfID = 0
propertyID = []

class Test(object):
    def __init__(self, data):
        self.__dict__ = json.loads(data)

with open(filename, 'r') as f:
    json_dict = json.load(f)

for item in json_dict:
    propertyID = item[desired_data]
    if propertyID == desiredID:
        break

# print(item)

print (item[desired_data2])


# print(propertyID)
# print(item)

# for property in propertyID:
#     print(property['Bldg Class'])

