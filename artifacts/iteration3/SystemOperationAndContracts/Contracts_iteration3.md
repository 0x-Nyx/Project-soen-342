# Contracts

# Use-Case Manage Client

## Contract CO1: signUpClient

**Operation:** signUpClient(name: String, affiliation: String, email: String, password: String, phoneNumber: String, text: String, status: String)

**Cross Reference:** Use Case: Manage Client

**Preconditions:**

- The client doesn’t already have an account
- The email is unique
- The password is correct
- The information are correctly entered

**Postconditions:**

- The new client object `client` is created (Instance Creation)
- `client` is initialized with the attributes (Modification of attributes)
- `client` is associated with Client (Formation of an association)
- `client` statues is pending, waiting for admin to accept (Modification of attributes)
- The `client` object is passed to the Mapper, which coordinates with the TDG to persist it into the database

## Contract CO3: loginClient()

**Operation:** loginClient(email: String, password: String)

**Cross Reference:** Use Case: Manage Client

**Preconditions:**

- The admin has accepted this client
- The email and the password are correct

**Postconditions:**

- The client enters the system

## Contract CO4: acceptClient

**Operation:** acceptClient(clientId)

**Cross Reference:** Use Case: Manage Client

**Preconditions:**

- The clientId is corrected
- The Instance of the Client, `client`, exists

**Postconditions:**

- `client` is initaliazed with 'accepted' as status (Modification of attribute)

# Use-Case Manage Expert

## Contract CO1: addExpert

**Operation:** addExpert(name: String, contactAdress: String, city: String, licenseNumber: String, password: String, expertiseId: List<Integer>)

**Cross Reference:** Use Case: Manage Expert

**Preconditions:**

- Admin is logged in successful
- The expert doesn’t exist already
- The information of the expert is correct

**Postconditions:**

- The new object Expert `expert` is created (Instance creation)
- Initialize `expert` with the name, contactAdress, city, licenseNumber, password, expertiseId (Modification of attributes)
- The `expert` object is passed to the Mapper, which coordinates with the TDG to persist it into the database

## Contract CO2: addAvailability

**Operation:** addAvailability(startTime: String, endTime: String, day: String, expertId: String)

**Cross Reference:** Use Case: Manage Expert

**Preconditions:**

- The expert is logged in successful
- The start and end time given are correct
- the day given is correct

**Postconditions:**

- The new Availability object `availability` is created (Instance Creation)
- `availability` is initialized with the attributes (Modification of attributes)
- `availability` is associated with Expert (Formation of an association)
- The `availability` object is passed to the Mapper, which coordinates with the TDG to persist it into the database

# Use-Case Manage Object

## Contract CO1: addObject

**Operation:** addObject(name: String, type: String, ownedByInstitution: Boolean, auctioned: Boolean)

**Cross Reference:** Use Case: Manage Object

**Preconditions:**

- The admin is logged in correctly
- The information of the object is correct

**Postconditions:**

- The new Object object `object` is created (Instance creation)
- Initialize `object` with the attribute (Modification of attributes)
- The `object` object is passed to the Mapper, which coordinates with the TDG to persist it into the database

# Use-Case Manage Object of Interest

    ## Contract CO1: fetchObjectinterest

**Operation:** fetchObjectinterest(expertID , clientID)

**Cross Reference:** Use Case: Manage Object of Interest

**Preconditions:**

- The expert is logged in successfully
- The admin pre-selected the objects that are showned to the client
- The client has selected the object of interest
- The client and expert ID are correct

**Postconditions:**

- Return all the object interests

  ## Contract CO2: showObjectInterest

**Operation:** showObjectInterest(clientID: int)

**Cross Reference:** Use Case: Manage Object of Interest

**Preconditions:**

- The client is logged in successfully
- The admin pre-selected the objects that are showned to the client

**Postconditions:**

- Return all the object interests

  ## Contract CO3: addObjectInterest()

**Operation:** addObjectInterest(name: String, type: String, clientID: int)

**Cross Reference:** Use Case: Manage Object of Interest

**Preconditions:**

- The client is logged in successful
- The object is not in the object of interest of the client

**Postconditions:**

- The new object ObjectOfInterest `object` is created (Instance creation)
- Initialize `object` with the attributes (Modification of attributes)
- Associate `object` with Client (Formation of association)
- The `object` object is passed to the Mapper, which coordinates with the TDG to persist it into the database

# Use-Case Manage Auction House

## Contract CO1 addAuctionHouse

**Operation:** addAuctionHouse(name: String, adress: String, city: String)

**Cross Reference:** Use Case: Manage Auction House

**Preconditions:**

- Admin is logged in successfully
- The auction house doesn't exist already

**Postconditions:**

- The new object AuctionHouse `auctionHouse` is created (Instance Creation)
- Initialize `auctionHouse` with the attributes (Modification of attributes)
- The `auctionHouse` object is passed to the Mapper, which coordinates with the TDG to persist it into the database

# Use-Case Manage Auction

## Contract CO1: addAuction

**Operation:** addAuction(beginTime: Time, endTime: Time, date: Date, type: String, auctionHouseId: int, expertisesId: List<Integer>, isViewing: Boolean)

**Cross Reference:** Use Case: Manage Auction

**Preconditions:**

- Admin is logged in successful
- The auction house exists
- The auction doesn't exist already
- The expertise exists
- The location and time slot are not taken for this auction's speciality
- The auction is only one day

**Postconditions:**

- The new object Auction `auction` is created (Instance Creation)
- Initialize `auction` with the attributes (Modification of attributes)
- Associate `auction` with the Auction House (Formation of an association)
- The `auction` object is passed to the Mapper, which coordinates with the TDG to persist it into the database

## Contract CO2: searchAuction

**Operation:** searchAuction(time: Time, city: String, day: Date, type: String)

**Cross Reference:** Use Case: Manage Auction

**Preconditions:**

- The client is logged in successful
- The auction exists during the period
- The entered information is correct
- The auction exist for this type

**Postconditions:**

- Return all auctions that meet the criteria.

# Use-Case Manage Service Request

## Contract CO1: acceptService

**Operation:** acceptServiceRequest(requestId: int, expertId: int)

**Cross Reference:** Use Case: Manage Service Request

**Preconditions:**

- The client is logged in succesfully
- The client and expert ID exists
- The instance Service, `service`, exists
- The expert doesn't have a service during this time

**Postconditions:**

- `service` is initialiazed with an expertID (Modification of attributes)

## Contract CO2: MakeRequestService

**Operation:** createRequestService(clientID: int , serviceTypeId: int, chosenDate: String, chosenStartTime: String, chosenEndTime: String )

**Cross Reference:** Use Case: Manage Service Request

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
- The `service` object is passed to the Mapper, which coordinates with the TDG to persist it into the database
