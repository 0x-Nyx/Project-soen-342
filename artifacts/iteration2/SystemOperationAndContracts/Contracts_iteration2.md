# Contracts

## Contract for the use-case of the Client

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

## Contract CO2: ChangeInfo()

**Operation:** changeInfo(info: String, clientID: int)

**Cross Reference:** Use Case: Manage Client

**Preconditions:**

- The client is logged in correctly
- The information of the client is correct
- The personal information provided has been updated and differs from the previously submitted information.

**Postconditions:**

- Update the information of `client`

## Contract CO3: loginClient()

**Operation:** loginClient(email: String, password: String)

**Cross Reference:** Use Case: Manage Client

**Preconditions:**

- The admin has accepted this client
- The information of the client is correct

**Postconditions:**

- the client enters the system

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

## Contract CO2: deleteObjectInterest()

**Operation:** deleteObjectInterest(ObjectID: int, clientID: int)

**Cross Reference:** Use Case: Manage Object of Interest

**Preconditions:**

- The client is logged in successful
- The ID of the object is correct
- The object is in the object of interest of the client

**Postconditions:**

- `object` is deleted from the ObjectInterest Catalog for this client

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
