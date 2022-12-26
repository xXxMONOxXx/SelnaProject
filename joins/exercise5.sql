CREATE SCHEMA cd;

CREATE TABLE cd.facilities
(
    facid integer                NOT NULL,
    name  character varying(100) NOT NULL,
    CONSTRAINT facilities_pk PRIMARY KEY (facid)
);

CREATE TABLE cd.members
(
    memid     integer                NOT NULL,
    surname   character varying(200) NOT NULL,
    firstname character varying(200) NOT NULL,
    CONSTRAINT members_pk PRIMARY KEY (memid)
);

CREATE TABLE cd.bookings
(
    bookid integer NOT NULL,
    facid  integer NOT NULL,
    memid  integer NOT NULL,
    CONSTRAINT bookings_pk PRIMARY KEY (bookid),
    CONSTRAINT fk_bookings_facid FOREIGN KEY (facid) REFERENCES cd.facilities (facid),
    CONSTRAINT fk_bookings_memid FOREIGN KEY (memid) REFERENCES cd.members (memid)
);

INSERT INTO cd.facilities (facid, name)
VALUES (0, 'Tennis Court 1'),
       (1, 'Tennis Court 2'),
       (2, 'Boxing'),
       (3, 'Training house'),
       (4, 'Swimming pool');

INSERT INTO cd.members (memid, surname, firstname)
VALUES (0, 'GUEST', 'GUEST'),
       (1, 'Smith', 'Darren'),
       (2, 'Smith', 'Tracy'),
       (3, 'Rownam', 'Tim'),
       (4, 'Joplette', 'Janice'),
       (5, 'Butters', 'Gerald'),
       (6, 'Tracy', 'Burton'),
       (7, 'Dare', 'Nancy'),
       (8, 'Boothe', 'Tim'),
       (9, 'Stibbons', 'Ponder'),
       (10, 'Owen', 'Charles');

INSERT INTO cd.bookings (bookid, facid, memid)
VALUES (0, 0, 6),
       (1, 1, 2),
       (2, 4, 5),
       (3, 2, 8),
       (4, 0, 10),
       (5, 2, 0),
       (6, 4, 4),
       (7, 1, 0),
       (8, 3, 7),
       (9, 4, 4),
       (10, 3, 2);

SELECT DISTINCT concat(firstname, ' ', surname) as member, cd.facilities.name as facility
FROM cd.members
         JOIN cd.bookings
              ON cd.members.memid = cd.bookings.memid
         JOIN cd.facilities
              ON cd.bookings.facid = cd.facilities.facid AND cd.facilities.name LIKE '%Tennis Court%'
ORDER BY member, facility;
