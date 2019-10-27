import json

property_datasetf = 'property_dataset.json'
availability_datasetf = 'availability_dataset.json'
lease_datasetf = 'lease_dataset.json'
sales_datasetf = 'sales_dataset.json'

indexOfID = 0
propertyID = []
iter1 = ['PropertyID','Bldg Name','Address', 'City', 'State','Market','Submarket','Micromarket' ,'Bldg Class','Build Year', 'Bldg Size', 'Stories','Property Type', 'Leasing Company', 'Management Company', 'Prime?', 'Subway Service' , 'Walk Score', 'Transit Score'
         ,'Crime Grade', 'Expansion Potential']
iter2 = ['Sales Price','Buyer','Seller']  #sales dataset
try:
    with open(property_datasetf, 'r') as property_dataset:
        property_dataset_dict = json.load(property_dataset)

    with open(availability_datasetf, 'r') as availability_dataset:
        availability_dataset_dict = json.load(availability_dataset)

    with open(lease_datasetf, 'r') as lease_dataset:
        lease_dataset_dict = json.load(lease_dataset)

    with open(sales_datasetf, 'r') as sales_dataset:
        sales_dataset_dict = json.load(sales_dataset)


    def getID(desiredID, fileName = 1):
        theFile = ''
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
                return item
        return {'-1':-1}
        # return -1

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


    def getOpenFloors(ID):
        list = {}
        for item in availability_dataset_dict:
            propertyID = item['PropertyID']
            # print(item['Is Available'])
            if propertyID == str(ID):
                if item['Is Available'] == 'Yes':
                    list[item['Floors'].replace(',',' ')] = (item['Rent High']+'#').replace(',',' ')
        return list


    def getMisc(ID, misc_field='', file = 1):
        bldID = getID(ID, file)
        return bldID[misc_field].replace(',',' ')

    def tenantInfo(ID):
        list={}
        count=0
        for item in lease_dataset_dict:
            if str(ID) == item['PropertyID']:
                list[item['Tenant'].replace(',',' ')] = {item['Lease Size'].replace(',',' '): item['Floor(s)'].replace(',',' ')}
                count = count+1
        return list


    def populateJsonFile(ID):
        finalDict1=({z:getMisc(ID, misc_field=z, file=1) for z in iter1})
        finalDict2=({y:getMisc(ID, misc_field=y, file=4) for y in iter2})

        finalDict2.update(tenantInfo(ID))

        finalDict2.update(getOpenFloors(ID))

        finalDict1.update(finalDict2)
        finalDict1 = json.dumps(finalDict1)
        with open('response.json', 'w') as file:
            file.write(finalDict1)

        file.close()
        property_dataset.close()
        availability_dataset.close()
        lease_dataset.close()
        sales_dataset.close()
        return finalDict1

    def getFromName(Name):
        for item in property_dataset_dict:
            if (item['Bldg Name']).lower() == Name.lower():
                break
        return populateJsonFile(item['PropertyID'])


    def getFromAdress(Address):
        for item in property_dataset_dict:
            if item['Address'].lower() == Address.lower():
                # print(item['Address'].lower())
                break
        return populateJsonFile( item['PropertyID'])

    # print(getFromAdress('225 Wacker Dr'))
    populateJsonFile('4613')
    # tenantInfo('4613')


except Exception as e:
    with open('response.json', 'w') as file:
        file.write("ERROR")
        file.close()