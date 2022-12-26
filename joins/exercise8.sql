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
    memid     integer                NOT NULL,
    surname   character varying(200) NOT NULL,
    firstname character varying(200) NOT NULL,
    CONSTRAINT members_pk PRIMARY KEY (memid)
);

CREATE TABLE cd.bookings
(
    bookid    integer   NOT NULL,
    facid     integer   NOT NULL,
    memid     integer   NOT NULL,
    starttime timestamp NOT NULL,
    slots     integer   NOT NULL,
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

INSERT INTO cd.bookings (bookid, facid, memid, starttime, slots)
VALUES (0, 0, 6, '2012-09-14 09:15', 4),
       (1, 1, 2, '2012-09-9 11:15:22', 5),
       (2, 4, 5, '2012-09-10 10:45:25', 3),
       (3, 2, 8, '2012-09-14 15:05:31', 8),
       (4, 0, 10, '2012-09-14 14:45:32', 10),
       (5, 2, 0, '2012-09-11 15:05:48', 4),
       (6, 4, 4, '2012-09-14 15:05:57', 5),
       (7, 1, 0, '2012-09-12 14:45:56', 7),
       (8, 3, 7, '2012-09-14 10:45:12', 8),
       (9, 4, 4, '2012-09-16 10:45:00', 3),
       (10, 3, 2, '2012-09-13 11:16:12', 2);

SELECT *
FROM (
         SELECT cd.members.firstname || ' ' || cd.members.surname AS member,
                cd.facilities.name                                AS facility,
                CASE
                    WHEN cd.bookings.memid = 0
                        THEN cd.facilities.guestcost * cd.bookings.slots
                    ELSE cd.facilities.membercost * cd.bookings.slots
                    END                                           AS cost
         FROM cd.bookings
                  JOIN cd.facilities ON cd.bookings.facid = cd.facilities.facid
             AND cd.bookings.starttime >= '2012-09-14' AND cd.bookings.starttime < '2012-09-15'
                  JOIN cd.members ON cd.members.memid = cd.bookings.memid
     ) sub
WHERE sub.cost > 30
ORDER BY sub.cost DESC
