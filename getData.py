import json
import sys

property_datasetf = 'property_dataset.json'
availability_datasetf = 'availability_dataset.json'
lease_datasetf = 'lease_dataset.json'
sales_datasetf = 'sales_dataset.json'

indexOfID = 0
propertyID = []
iter1 = ['PropertyID','Bldg Name','Address', 'City','State' ,'Bldg Class','Build Year', 'Bldg Size', 'Stories','Property Type', 'Leasing Company', 'Primary Owner','Prime?', 'Subway Service' , 'Walk Score', 'Transit Score'
         ,'Crime Grade', 'Expansion Potential']
iter2 = ['Sales Price','Buyer','Seller']  #sales dataset
#iter3 = ['First Year Rent p']# rent info

with open(property_datasetf, 'r') as property_dataset:
    property_dataset_dict = json.load(property_dataset)
    
with open(availability_datasetf, 'r') as availability_dataset:
    availability_dataset_dict = json.load(availability_dataset)
    
with open(lease_datasetf, 'r') as lease_dataset:
    lease_dataset_dict = json.load(lease_dataset)
    
with open(sales_datasetf, 'r') as sales_dataset:
    sales_dataset_dict = json.load(sales_dataset)


def getFromName(Name):
    for item in property_dataset_dict:
        if item['Bldg Name'] == Name:
            break
    return item['PropertyID']


def getFromAdress(Address):
    for item in property_dataset_dict:
        if item['Address'] == str(Address):
            break
    return item['PropertyID']


def getID(desiredID, fileName):
    if fileName == 1:
        theFile = property_dataset_dict
    if fileName == 2:
        theFile = availability_dataset_dict
    if fileName == 3:
        theFile =lease_dataset_dict
    if fileName == 4:
        theFile =sales_dataset_dict

    for item in theFile:
        propertyID = item['PropertyID']
        if propertyID == str(desiredID):
            break
    return item


def getYear(ID):
    bldID = getID(ID,1)
    return bldID['Build Year']


def getClass(ID):
    bldID = getID(ID,1)
    return bldID['Bldg Class']


def getType(ID):
    bldID = getID(ID,1)
    return bldID['Property Type']


def getSize(ID):
    bldID = getID(ID,1)
    return bldID['Bldg Size']


def getLeasingCompany(ID):
    bldID = getID(ID,1)
    return bldID['Leasing Company']


def getPrimaryOwner(ID):
    bldID = getID(ID,1)
    return bldID['Primary Owner']


def isPrime(ID):
    bldID = getID(ID, 1)
    return bldID['Prime?']


def getNumOfFloors(ID):
    bldID = getID(ID, 1)
    return bldID['Stories']


def getAddress(ID):
    bldID = getID(ID, 1)
    return  bldID['Address']

# #"First Year Rent p":{"s":{"f":{"":""}}}
# def getFirstYRent(bldID):
#     print(bldID['First Year Rent p'])
#     # p = bldID['First Year Rent p']
#     # s = p['s']
#     # f = s['f']
#     # price = f['']
#     # print(price)
#     return False
#     # return s


def getMisc(ID, misc_field='', file = 1): # you can search any field now.
    bldID = getID(ID, file)
    return bldID[misc_field]

x=''

finalDict1=({z:getMisc(x, misc_field=z, file= 1) for z in iter1})
finalDict2=({y:getMisc(x, misc_field=y, file=4) for y in iter2})

finalDict1.update(finalDict2)
finalDict1 = json.dumps(finalDict1)
# print(getFirstYRent(x))

with open('response.json', 'w') as file:
    file.write(finalDict1)

file.close()
property_dataset.close()
availability_dataset.close()
lease_dataset.close()
sales_dataset.close()
