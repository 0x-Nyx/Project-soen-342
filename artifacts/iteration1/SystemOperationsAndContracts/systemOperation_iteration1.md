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
