# System Operation

## Register a new Client

- registerNewClient()
- signUpClient(email: String, password: String, affiliation: String, text: String, name: String, phone: String)
- requestNewAccount(email: String, password: String, affiliation: String, text: String, name: String, phone: String)

## Login Client

- loginClient()
- loginClient(email: String, password: String)

## Update Personal Information of the Client

- updateInfo()
- changeInfo(info: String, clientID: int)

## Adding Object of Interest

- addObject()
- addObjectInterest(name: String, type: String, clientID: int)
- addObjectInterest(name: String, type: String, clientID: int)

## Delete Object of Interest

- deleteObject()
- deleteObjectInterest(ObjectID: int, clientID: int)
- deleteObjectInterest(ObjectID: int, clientID: int)

## Client search an Auction

- searchAuction()
- searchAuction(time: Time, location: String, day: Date, type: String)

## Viewing the object of interest as a Client

- showObjectInterest()
- showObjectInterest(clientID: int)

## Client Request a new service

- requestNewService()
- makeRequestService(clientID: int , typeService: String, day: String, startTime: String, endTime: String )
- isAvailableExpert()
