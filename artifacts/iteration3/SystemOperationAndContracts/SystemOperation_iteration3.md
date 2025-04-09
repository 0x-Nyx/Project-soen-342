# System Operation

## Adding object

- addObject()
- addObject(name, type, ownedByInstitution, auctioned)
- createObject(name, type, ownedByInstitution, auctioned)

## Viewing the object of interest as a Client

- showObjectinterest()
- showObjectinterest(clientID)

## Viewing the object of interest as an Expert

- showObjectInterest()
- showObjectinterest(expertD)
- fetchObjectinterest(expertD, clientID)

## Register a new Client

- registerNewClient()
- signUpClient(name, affiliation, email, password, phoneNumber, text, status)
- createAccountPending(name, affiliation, email, password, phoneNumber, text, status)
- requestNewAccount(name, affiliation, email, password, phoneNumber, text, status)

## Client Request a new service

- requestNewService()
- requestService(clientId, chosenDate, chosenStartTime, chosenEndTime)
- createRequestService(clientId, chosenDate, chosenStartTime, chosenEndTime)
- acceptServiceExpert()

## The expert registers a new availability

- registerAvailabilityExpert()
- addAvailability(day, startTime, endTime, expertId)
- updateAvailability(day, startTime, endTime, expertId)

## The admin adds a new availability for an Expert

- registerAvailabilityAdmin()
- addAvailability(day, startTime, endTime, expertId)
- updateAvailability(day, startTime, endTime, expertId)

## The admin adds a new expert

- registerNewExpert()
- addExpert(name, contactAdress, city, licenseNumber, password, expertiseId)
- createExpert(name, contactAdress, city, licenseNumber, password, AreaOfExpertise)

## Register a new Client

- registerNewClient()
- signUpClient(email, password, affiliation, text, name, phone)
- requestNewAccount(email, password, affiliation, text, name, phone)

## Login Client

- loginClient()
- loginClient(email, password)

## Adding Object of Interest

- addObjectInterest()
- addObjectInterest(name, type, clientID)
- addObjectInterest(name, type, clientID)

## Client search an Auction

- searchAuction()
- searchAuction(date, time, type, city)

## Viewing the object of interest as a Client

- showObjectInterest()
- showObjectInterest(clientID)

## Client Request a new service

- requestNewService()
- requestService(clientId, chosenDate, chosenStartTime, chosenEndTime, requestId)
- createRequestService(clientId, chosenDate, chosenStartTime, chosenEndTime, requestId)
- acceptServiceExpert()

## Admin adds a new Auction

- addAuction()
- creationAucton(stratTime, endTime, date, type, auctionHouseId, expertisesId, isViewing)
- creationAucton(startTime, endTime, date, type, auctionHouseId, expertisesId, isViewing)

## Admin adds a new Auction House

- addAuctionHouse()
- addAuctionHouse(name, adress, city)
- creationAuctionHouse(name, adress, city)

## Expert accepts a new Service from a client

- acceptService()
- viewServicePending()
- acceptService(requestId)
- acceptServiceRequest(requestId, expertId)

## Admin accepts a new Client in the System

- acceptClient()
- pendingClient()
- acceptClient(clientId)
- update(clientId)
