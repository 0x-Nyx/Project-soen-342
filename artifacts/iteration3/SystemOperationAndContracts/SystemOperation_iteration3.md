# System Operation

# Admin action

## Admin adds a new Auction

- addAuction()
- creationAucton(startTime, endTime, date, type, auctionHouseId, expertisesId, isViewing)
- creationAucton(startTime, endTime, date, type, auctionHouseId, expertisesId, isViewing)

## Admin adds a new Auction House

- addAuctionHouse()
- addAuctionHouse(name, adress, city)
- creationAuctionHouse(name, adress, city)

## Adding object

- addObject()
- addObject(name, type, ownedByInstitution, auctioned)
- createObject(name, type, ownedByInstitution, auctioned)

## The admin adds a new availability for an Expert

- registerAvailabilityAdmin()
- addAvailability(day, startTime, endTime, expertId)
- updateAvailability(day, startTime, endTime, expertId)

## The admin adds a new expert

- registerNewExpert()
- addExpert(name, contactAdress, city, licenseNumber, password, expertiseId)
- createExpert(name, contactAdress, city, licenseNumber, password, AreaOfExpertise)

## Admin accepts a new Client in the System

- acceptClient()
- pendingClient()
- acceptClient(clientId)
- update(clientId)

# Client actions

## Register a new Client

- registerNewClient()
- signUpClient(name, affiliation, email, password, phoneNumber, text, status)
- createAccountPending(name, affiliation, email, password, phoneNumber, text, status)
- requestNewAccount(name, affiliation, email, password, phoneNumber, text, status)

## Login Client

- loginClient()
- loginClient(email, password)

## Client Request a new service

- requestNewService()
- requestService(clientId, serviceTypeId, chosenDate, chosenStartTime, chosenEndTime)
- createRequestService(clientId, serviceTypeId, chosenDate, chosenStartTime, chosenEndTime)
- acceptServiceExpert()

## Adding Object of Interest

- addObjectInterest()
- addObjectInterest(name, type, clientID)
- addObjectInterest(name, type, clientID)

## Viewing the object of interest as a Client

- showObjectinterest()
- showObjectinterest(clientID)

## Client search an Auction

- searchAuction()
- searchAuction(date, time, type, city)

# Expert actions

## Viewing the object of interest as an Expert

- showObjectInterest()
- showObjectinterest(expertID)
- fetchObjectinterest(expertID, clientID)

## The expert registers a new availability

- registerAvailabilityExpert()
- addAvailability(day, startTime, endTime, expertId)
- updateAvailability(day, startTime, endTime, expertId)

## Expert accepts a new Service from a client

- acceptService()
- viewServicePending()
- acceptService(requestId)
- acceptServiceRequest(requestId, expertId)
