# Contracts

## Contract CO1: SignUpClient

**Operation:** signUpClient(email: String, password: String, affiliation: String, text: String, name: String, phone: String)

**Cross Reference:** Use Case: Manage Client

**Preconditions:**

- The client doesn’t already have an account
- The email is unique
- The password is correct
- The information are correctly entered

**Postconditions:**

- The new client object `client` is created
- `client` is initialized with the attributes
- `client` is associated with Client
- `client` is associated with ClientCatalog
- `client` statues is pending, waiting for admin to accept
- Notification to the admin for the new Client

## Contract CO3: loginClient()

**Operation:** loginClient(email: String, password: String)

**Cross Reference:** Use Case: Manage Client

**Preconditions:**

- The admin has accepted this client
- The information of the client is correct

**Postconditions:**

- the client enters the system

## Contract CO1: acceptClient

**Operation:** signUpClient(email: String, password: String, affiliation: String, text: String, name: String, phone: String)

**Cross Reference:** Use Case: Manage Client

**Preconditions:**

- The client doesn’t already have an account
- The email is unique
- The password is correct
- The information are correctly entered

**Postconditions:**

- The new client object `client` is created
- `client` is initialized with the attributes
- `client` is associated with Client
- `client` is associated with ClientCatalog
- `client` statues is pending, waiting for admin to accept
- Notification to the admin for the new Client

## Contract CO1: showObjectInterest

**Operation:** showObjectInterest(clientID: int)

**Cross Reference:** Use Case: Manage Object of Interest

**Preconditions:**

- The client is logged in successful
- The admin pre-selected the objects that are showned to the client

**Postconditions:**

- return all the object interests

## Contract CO1: MakeRequestService

**Operation:** makeRequestService(clientID: int , typeService: String, day: String, startTime: String, endTime: String )

**Cross Reference:** Use Case: Manage Request Service

**Preconditions:**

- The client is logged in successful
- The service doesn't exist for this time slot on this day for the `client`.
- The right type of service
- The auction visit is done when the auction house is available
- The entered information is correct

**Postconditions:**

- The new object ServiceRequest `service` is created
- Notification the expert of `service`
- Initialize `service` with the information
- Associate `service` with the ServiceCatalog

## Contract CO1: addObjectInterest()

**Operation:** addObjectInterest(name: String, type: String, clientID: int)

**Cross Reference:** Use Case: Manage Object of Interest

**Preconditions:**

- The client is logged in successful
- The name of the object is owned by the institution
- The type of the object is owned by the institution
- The object is not in the object of interest of the client

**Postconditions:**

- `object` is associated in the ObjectInterest Catalog for this client

## Contract CO1: searchAuction

**Operation:** searchAuction(time: Time, location: String, day: Date, type: String)

**Cross Reference:** Use Case: Manage Auction

**Preconditions:**

- The client is logged in successful
- The auction exists during the period
- The entered information is correct
- The auction exist for this type

**Postconditions:**

- Return all auctions that meet the criteria.

## Contract CO1: addObject

**Operation:** addObject(isOwned: Boolean, details: String, isAuctioned: Boolean)

**Cross Reference:** Use Case: Manage Object

**Preconditions:**

- The admin is logged in correctly
- The information of the object is correct

**Postconditions:**

- The new Object object `object` is created
- `object` is associate in the Object Catalog
- Initialize `object` with the attribute

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

## Contract CO6 addAuctionHouse

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

## Contract CO1: showObjectInterestExpert

**Operation:** showObjectInterest(clientID: int)

**Cross Reference:** Use Case: Manage Object of Interest

**Preconditions:**

- The client is logged in successful
- The admin pre-selected the objects that are showned to the client

**Postconditions:**

- return all the object interests

## Contract CO1: acceptService

**Operation:** signUpClient(email: String, password: String, affiliation: String, text: String, name: String, phone: String)

**Cross Reference:** Use Case: Manage Client

**Preconditions:**

- The client doesn’t already have an account
- The email is unique
- The password is correct
- The information are correctly entered

**Postconditions:**

- The new client object `client` is created
- `client` is initialized with the attributes
- `client` is associated with Client
- `client` is associated with ClientCatalog
- `client` statues is pending, waiting for admin to accept
- Notification to the admin for the new Client
