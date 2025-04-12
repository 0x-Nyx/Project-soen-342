CREATE TABLE Client (
    Client_ID INT AUTO_INCREMENT PRIMARY KEY,
    Name_client VARCHAR(255),
    Affiliation VARCHAR(255),
    Email VARCHAR(255) UNIQUE,
    Password VARCHAR(255),
    PhoneNumber VARCHAR(20),
    Text TEXT,
    Status VARCHAR(50)
);
CREATE TABLE Expert (
    Expert_ID INT AUTO_INCREMENT PRIMARY KEY,
    Name_Expert VARCHAR(255),
    ContactAddress TEXT,
    City VARCHAR(255),
    LicenseNumber VARCHAR(100) UNIQUE,
    Password VARCHAR(255)
);
CREATE TABLE Type_Expertise (
    Expertise_ID INT AUTO_INCREMENT PRIMARY KEY,
    Type VARCHAR(255)
);
CREATE TABLE Availability (
    Availability_ID INT AUTO_INCREMENT PRIMARY KEY,
    Day VARCHAR(50),
    StartTime TIME,
    EndTime TIME
);
CREATE TABLE Expert_Expertises (
    Expert_ID INT,
    Expertise_ID INT,
    PRIMARY KEY (Expert_ID, Expertise_ID),
    FOREIGN KEY (Expert_ID) REFERENCES Expert(Expert_ID) ON DELETE CASCADE,
    FOREIGN KEY (Expertise_ID) REFERENCES Type_Expertise(Expertise_ID) ON DELETE CASCADE
);
CREATE TABLE Expert_Availability (
    Expert_ID INT,
    Availability_ID INT,
    PRIMARY KEY (Expert_ID, Availability_ID),
    FOREIGN KEY (Expert_ID) REFERENCES Expert(Expert_ID) ON DELETE CASCADE,
    FOREIGN KEY (Availability_ID) REFERENCES Availability(Availability_ID) ON DELETE CASCADE
);
CREATE TABLE Object (
    Object_ID INT AUTO_INCREMENT PRIMARY KEY,
    Type VARCHAR(255),
    Name_object VARCHAR(255),
    IsOwned BOOLEAN,
    IsAuctioned BOOLEAN
);
CREATE TABLE ObjectOfInterest (
    id INT AUTO_INCREMENT PRIMARY KEY,
    object_name VARCHAR(100),
    object_type VARCHAR(100) NOT NULL,
    client_id INT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES Client(Client_ID) ON DELETE CASCADE
);
CREATE TABLE Service (
    Service_ID INT AUTO_INCREMENT PRIMARY KEY,
    Type VARCHAR(255)
);
CREATE TABLE Request (
    Request_ID INT AUTO_INCREMENT PRIMARY KEY,
    Client_ID INT,
    Expert_ID INT,
    Request_date DATE,
    Request_start_time TIME,
    Request_end_time TIME,
    Request_type_ID INT,
    Status VARCHAR(50),
    FOREIGN KEY (Client_ID) REFERENCES Client(Client_ID),
    FOREIGN KEY (Expert_ID) REFERENCES Expert(Expert_ID),
    FOREIGN KEY (Request_type_ID) REFERENCES Service(Service_ID)
);
CREATE TABLE AuctionHouse (
    AuctionHouse_ID INT AUTO_INCREMENT PRIMARY KEY,
    Address TEXT,
    Name VARCHAR(255),
    City VARCHAR(255)
);
CREATE TABLE Auction (
    Auction_ID INT AUTO_INCREMENT PRIMARY KEY,
    Begin_time TIME,
    End_time TIME,
    Date DATE,
    Type VARCHAR(255),
    isViewing BOOLEAN DEFAULT FALSE,
    AuctionHouse_ID INT,
    FOREIGN KEY (AuctionHouse_ID) REFERENCES AuctionHouse(AuctionHouse_ID)
);
CREATE TABLE Auction_Expertise (
    Auction_ID INT,
    Expertise_ID INT,
    PRIMARY KEY (Auction_ID, Expertise_ID),
    FOREIGN KEY (Auction_ID) REFERENCES Auction(Auction_ID) ON DELETE CASCADE,
    FOREIGN KEY (Expertise_ID) REFERENCES Type_Expertise(Expertise_ID) ON DELETE CASCADE
);
CREATE TABLE Admin (
    Admin_ID INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(255) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL
);