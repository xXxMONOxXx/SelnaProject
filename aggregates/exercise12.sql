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

INSERT INTO cd.members(memid)
VALUES (0),
       (1),
       (2),
       (3);

INSERT INTO cd.bookings (bookid, facid, memid, slots, starttime)
VALUES (0, 0, 0, 50, '2013-01-12 08:30:00'),
       (1, 1, 1, 60, '2012-02-23 08:30:00'),
       (2, 4, 2, 60, '2012-01-24 08:30:00'),
       (3, 2, 3, 70, '2012-05-22 08:30:00'),
       (4, 0, 0, 30, '2013-01-11 08:30:00'),
       (5, 1, 2, 60, '2012-03-24 08:30:00'),
       (6, 6, 0, 10, '2012-01-16 08:30:00'),
       (7, 1, 2, 10, '2012-06-25 08:30:00'),
       (8, 5, 1, 20, '2013-01-15 08:30:00'),
       (9, 4, 2, 40, '2012-03-06 08:30:00'),
       (10, 0, 1, 80, '2012-03-04 08:30:00'),
       (11, 1, 0, 10, '2012-08-05 08:30:00'),
       (12, 8, 2, 70, '2012-11-06 08:30:00'),
       (13, 2, 0, 10, '2013-09-07 08:30:00'),
       (14, 0, 3, 80, '2012-12-02 08:30:00'),
       (15, 8, 0, 50, '2012-09-07 08:30:00'),
       (16, 4, 3, 10, '2013-01-02 08:30:00'),
       (17, 8, 0, 90, '2012-09-07 08:30:00'),
       (18, 3, 2, 60, '2012-01-04 08:30:00'),
       (19, 5, 0, 20, '2012-04-12 08:30:00'),
       (20, 3, 1, 30, '2012-02-05 08:30:00');

SELECT facid, EXTRACT(MONTH FROM starttime) AS month, sum(slots) AS "Total Slots"
FROM cd.bookings
WHERE cd.bookings.starttime >= '2012-01-01'
  AND cd.bookings.starttime < '2013-01-01'
GROUP BY ROLLUP (facid, month)
ORDER BY facid, month;