# Contracts
## Contract for the Viewing the object of interest 

## Contract CO1: addObject

**Operation:** addObject(isOwned: Boolean, details: String, isAuctioned: Boolean)

**Cross Reference:** Use Case: Manage Object

**Preconditions:**

- The admin is logged in correctly
- The information of the object is correct

**Postconditions:**

- The new Object object `object` is created
- `object` is associate in the Object Catalog 
- Initialize  `object` with the attribute

## Contract CO2: showObjectInterest

**Operation:** showObjectInterest(details)

**Cross Reference:** Use Case: Viewing the object of interest

**Preconditions:**

- The client is logged in successful
- The admin pre-selected the objects that are showned to the client

**Postconditions:**

- return all the object interests

## Other contracts

## Contract CO1: SignUpClient

**Operation:** signUpClient(username: String, password: String, affiliation: String, contatInfo: String, text: String)

**Cross Reference:** Use Case: Sign up Client

**Preconditions:**

- The client doesn’t already have an account
- The username is unique

**Postconditions:**

- The new client object `client` is created
- `client` statues is pending, waiting for admin to accept
- Notification to the admin for the new request

## Contract CO2: requestService

**Operation:** requestService(username: String , typeService: String)

**Cross Reference:** Use Case: Request Service

**Preconditions:**

- The client is logged in successful
- The service doesn't exist
- The information of the service is correct

**Postconditions:**

- The new object ServiceRequest `service` is created
- Notification the expert of `service`
- Initialize `service` with the information
- Associate `service` with the ServiceCatalog

## Contract CO3: addAvailability

**Operation:** addAvailability(time: Time, city: String, typeService: String)

**Cross Reference:** Use Case: Availability Expert

**Preconditions:**

- The expert is logged in successful
- The time given is correct
- The city given is correct
- The service given is correct

**Postconditions:**

- Update the availability of the expert

## Contract CO4: addExpert

**Operation:** addExpert(name: String, contactAdress: String, licenseNumber: String, AreaOf Expertise: String)

**Cross Reference:** Use Case: Manage Expert

**Preconditions:**

- Admin is logged in successful
- The expert doesn’t exist already
- The information of the expert is correct

**Postconditions:**

- The new object Expert `expert` is created
- Initialize `expert` with the time, city and typeService
- Associate `expert` with the ExpertCatalog

## Contract CO5: addAuction

**Operation:** addAuction(time: Time, location: String, date: Date, speciality: String)

**Cross Reference:** Use Case: Manage Auction

**Preconditions:**

- Admin is logged in successful
- The auction house exists
- The auction doesn't exist already
- The location and time slot are not taken for this auction's speciality

**Postconditions:**

- The new object Auction `auction` is created
- Initialize `auction` with the time, the location, the date and the speciality
- Associate `auction` with the AuctionCatalog
- Associate `auction` with the Auction House
