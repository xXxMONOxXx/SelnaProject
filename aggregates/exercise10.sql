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
VALUES (0, 0, 0, 50),
       (1, 1, 1, 60),
       (2, 4, 2, 60),
       (3, 2, 3, 70),
       (4, 0, 0, 30),
       (5, 1, 2, 60),
       (6, 6, 0, 10),
       (7, 1, 2, 10),
       (8, 5, 1, 20),
       (9, 4, 2, 40),
       (10, 0, 1, 80),
       (11, 1, 0, 10),
       (12, 8, 2, 70),
       (13, 2, 0, 10),
       (14, 0, 3, 80),
       (15, 8, 0, 50),
       (16, 4, 3, 10),
       (17, 8, 0, 90),
       (18, 3, 2, 60),
       (19, 5, 0, 20),
       (20, 3, 1, 30);

SELECT name,
       revenue FROM(SELECT fac.name, sum(CASE
                                             WHEN memid = 0
                                                 THEN slots * fac.guestcost
                                             ELSE slots * membercost
    END) as revenue
                    FROM cd.bookings bks
                             JOIN cd.facilities fac
                                  ON bks.facid = fac.facid
                    GROUP BY fac.name) AS aggreg
WHERE revenue < 1000
ORDER BY revenue;
