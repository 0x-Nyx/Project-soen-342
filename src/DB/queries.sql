DROP TABLE IF EXISTS Auction_Expertise;
DROP TABLE IF EXISTS Auction;
DROP TABLE IF EXISTS AuctionHouse;
DROP TABLE IF EXISTS Request;
DROP TABLE IF EXISTS Service;
DROP TABLE IF EXISTS ObjectOfInterest;
DROP TABLE IF EXISTS Object;
DROP TABLE IF EXISTS Expert_Availability;
DROP TABLE IF EXISTS Expert_Expertises;
DROP TABLE IF EXISTS Availability;
DROP TABLE IF EXISTS Type_Expertise;
DROP TABLE IF EXISTS Expert;
DROP TABLE IF EXISTS Client;
DROP TABLE IF EXISTS Admin;
ALTER -- Admin
INSERT INTO Admin (Username, Password)
VALUES ('admin', 'adminpass');
-- Clients
INSERT INTO Client (
        Name_client,
        Affiliation,
        Email,
        Password,
        PhoneNumber,
        Text,
        Status
    )
VALUES (
        'Alice Smith',
        'University A',
        'alice@example.com',
        'pass123',
        '1234567890',
        'Interested in antique books.',
        'Active'
    ),
    (
        'Bob Johnson',
        'Company B',
        'bob@example.com',
        'bobpass',
        '0987654321',
        'Looking for vintage cars.',
        'Active'
    );
-- Experts
INSERT INTO Expert (
        Name_Expert,
        ContactAddress,
        City,
        LicenseNumber,
        Password
    )
VALUES (
        'Dr. John Doe',
        '123 Main St',
        'Montreal',
        'LIC123',
        'johnpass'
    ),
    (
        'Jane Roe',
        '456 Oak Ave',
        'Toronto',
        'LIC456',
        'janepass'
    );
-- Type Expertise
INSERT INTO Type_Expertise (Type)
VALUES ('Art'),
    ('Books'),
    ('Cars');
-- Expert Expertise
INSERT INTO Expert_Expertises
VALUES (1, 1),
    (1, 2),
    (2, 3);
-- Availability
INSERT INTO Availability (Day, StartTime, EndTime)
VALUES ('Monday', '09:00:00', '17:00:00'),
    ('Tuesday', '10:00:00', '16:00:00');
-- Expert Availability
INSERT INTO Expert_Availability
VALUES (1, 1),
    (2, 2);
-- Objects
INSERT INTO Object (Type, Name_object, IsOwned, IsAuctioned)
VALUES ('Car', '1969 Mustang', TRUE, FALSE),
    ('Book', 'Rare Encyclopedia', TRUE, TRUE);
-- Object of Interest
INSERT INTO ObjectOfInterest (object_name, object_type, client_id)
VALUES ('1969 Mustang', 'Car', 1),
    ('Rare Encyclopedia', 'Book', 2);
-- Services
INSERT INTO Service (Type)
VALUES ('Valuation'),
    ('Restoration');
-- Request
INSERT INTO Request (
        Client_ID,
        Expert_ID,
        Request_date,
        Request_start_time,
        Request_end_time,
        Request_type_ID,
        Status
    )
VALUES (
        1,
        1,
        '2025-04-07',
        '11:00:00',
        '12:00:00',
        1,
        'Pending'
    ),
    (
        2,
        2,
        '2025-04-07',
        '14:00:00',
        '15:00:00',
        2,
        'Accepted'
    );
-- Auction House
INSERT INTO AuctionHouse (Address, Name, City)
VALUES (
        '789 Gallery Rd',
        'Heritage Auctions',
        'Montreal'
    );
-- Auctions
INSERT INTO Auction (
        Begin_time,
        End_time,
        Date,
        Type,
        isViewing,
        AuctionHouse_ID
    )
VALUES (
        '10:00:00',
        '12:00:00',
        '2025-04-10',
        'Books',
        TRUE,
        1
    );
-- Auction Expertise
INSERT INTO Auction_Expertise
VALUES (1, 2);