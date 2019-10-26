import json

property_datasetf = 'property_dataset.json'
availability_datasetf = 'availability_dataset.json'
lease_datasetf = 'lease_dataset.json'
sales_datasetf = 'sales_dataset.json'

# desired_data = 'PropertyID'
# desiredID = '542081'
indexOfID = 0
propertyID = []
finalDict = []

with open(property_datasetf, 'r') as property_dataset:
    property_dataset_dict = json.load(property_dataset)
    
with open(availability_datasetf, 'r') as availability_dataset:
    availability_dataset_dict = json.load(availability_dataset)
    
with open(lease_datasetf, 'r') as lease_dataset:
    lease_dataset_dict = json.load(lease_dataset)
    
with open(sales_datasetf, 'r') as sales_dataset:
    sales_dataset_dict = json.load(sales_dataset)


def getID(desiredID):
    for item in property_dataset_dict:
        propertyID = item['PropertyID']
        if propertyID == str(desiredID):
            break
    return item


def getYear(ID):
    bldID = getID(ID)
    return bldID['Build Year']


def getClass(ID):
    bldID = getID(ID)
    return bldID['Bldg Class']


def getType(ID):
    bldID = getID(ID)
    return bldID['Property Type']


def getSize(ID):
    bldID = getID(ID)
    return bldID['Bldg Size']


def getLeasingCompany(ID):
    bldID = getID(ID)
    return bldID['Leasing Company']


def getPrimaryOwner(ID):
    bldID = getID(ID)
    return bldID['Primary Owner']


def isPrime(ID):
    bldID = getID(ID)
    return bldID['Prime?']


def getNumOfFloors(ID):
    bldID = getID(ID)
    return bldID['Stories']


def getAddress(ID):
    bldID = getID(ID)
    return  bldID['Address']


def getMisc(ID, misc_field=''): # you can search any field now.
    bldID = getID(ID)
    return bldID[misc_field]
