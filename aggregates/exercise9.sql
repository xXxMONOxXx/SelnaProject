CREATE SCHEMA cd;

CREATE TABLE cd.facilities
(
    facid      integer                NOT NULL,
    name       character varying(100) NOT NULL,
    membercost numeric                NOT NULL,
    guestcost  numeric                NOT NULL,
    CONSTRAINT facilities_pk PRIMARY KEY (facid)
);


CREATE TABLE cd.members
(
    memid integer NOT NULL,
        CONSTRAINT members_pk PRIMARY KEY (memid)
);

CREATE TABLE cd.bookings
(
    bookid integer NOT NULL,
    facid  integer NOT NULL,
    memid  integer NOT NULL,
    slots  integer NOT NULL,
    CONSTRAINT bookings_pk PRIMARY KEY (bookid),
    CONSTRAINT fk_bookings_facid FOREIGN KEY (facid) REFERENCES cd.facilities (facid),
    CONSTRAINT fk_bookings_memid FOREIGN KEY (memid) REFERENCES cd.members (memid)
);

INSERT INTO cd.facilities (facid, name, membercost, guestcost)
VALUES (0, 'Tennis Court 1', 5, 25),
       (1, 'Tennis Court 2', 5, 25),
       (2, 'Badminton Court', 0, 15.5),
       (3, 'Table Tennis', 0, 5),
       (4, 'Massage Room 1', 35, 80),
       (5, 'Massage Room 2', 35, 80),
       (6, 'Squash Court', 3.5, 17.5),
       (7, 'Snooker Table', 0, 5),
       (8, 'Pool Table', 0, 5);

INSERT INTO cd.members(memid)
VALUES (0),
       (1),
       (2),
       (3);

INSERT INTO cd.bookings (bookid, facid, memid, slots)
VALUES (0, 0, 0, 500),
       (1, 1, 1, 600),
       (2, 4, 2, 600),
       (3, 2, 3, 700),
       (4, 0, 0, 300),
       (5, 1, 2, 600),
       (6, 6, 0, 100),
       (7, 1, 2, 100),
       (8, 5, 1, 200),
       (9, 4, 2, 400),
       (10, 0, 1, 800),
       (11, 1, 0, 100),
       (12, 8, 2, 700),
       (13, 2, 0, 100),
       (14, 0, 3, 800),
       (15, 8, 0, 500),
       (16, 4, 3, 100),
       (17, 8, 0, 900),
       (18, 3, 2, 600),
       (19, 5, 0, 200),
       (20, 3, 1, 300);

SELECT facid, sum(slots) AS "Total Slots"
FROM cd.bookings
GROUP BY facid
HAVING sum(slots) > 1000
ORDER BY facid;
