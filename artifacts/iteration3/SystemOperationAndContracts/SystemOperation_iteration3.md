# System Operation

## Adding object

- addObject()
- addObject(isOwned, details, isAuctioned)
- createObject(isOwned, details, isAuctioned)

## Viewing the object of interest as a Client

- showObjectInterest()
- showObjectInterest(details)
- get(i)
- endShowOnjectInterest()

## Viewing the object of interest as an Expert

- showObjectInterest()
- get(i)
- endShowOnjectInterest()

## Register a new Client

- registerNewClient()
  signUpClient(username, password, affiliation, contatInfo, text)
- createAccountPending(username, password, affiliation, contatInfo, text)
- requestNewAccount(username, password, affiliation, contatInfo, text)

## Client Request a new service

- requestNewService()
- requestService(username,typeService)
- createRequestService(username, typeService)
- IsAvailableExpert()

## The expert registers a new availability

- registerAvailability()
- updateAvailability(time, city, typeService)

## The admin adds a new expert

- registerNewExpert()
- addExpert(name, contactAdress, licenseNumber, AreaOf Expertise)
- createExpert(name, contactAdress, licenseNumber, AreaOf Expertise)

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
