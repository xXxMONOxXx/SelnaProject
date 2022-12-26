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

INSERT INTO cd.members (memid)
VALUES (0),
       (1),
       (2),
       (3),
       (4),
       (5),
       (6),
       (7),
       (8),
       (9),
       (10);

INSERT INTO cd.bookings (bookid, facid, memid, slots)
VALUES (0, 0, 6, 4),
       (1, 1, 2, 5),
       (2, 4, 5, 3),
       (3, 2, 8, 8),
       (4, 0, 10, 10),
       (5, 2, 0, 4),
       (6, 4, 4, 5),
       (7, 1, 0, 7),
       (8, 3, 7, 8),
       (9, 4, 4, 3),
       (10, 3, 2, 2);


SELECT name, rank
FROM (
         SELECT fac.name       as name,
                RANK() OVER (ORDER BY SUM(CASE
                                              WHEN memid = 0
                                                  THEN slots * fac.guestcost
                                              ELSE slots * membercost
                    END) DESC) AS rank
         FROM cd.bookings bks
                  JOIN cd.facilities fac
                       ON bks.facid = fac.facid
         GROUP BY fac.name) AS subque
WHERE rank <= 3
ORDER BY rank;