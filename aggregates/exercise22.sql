CREATE SCHEMA cd;

CREATE TABLE cd.facilities
(
    facid      integer NOT NULL,
    membercost numeric NOT NULL,
    guestcost  numeric NOT NULL,
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
    slots     integer   NOT NULL,
    starttime timestamp NOT NULL,
    CONSTRAINT bookings_pk PRIMARY KEY (bookid),
    CONSTRAINT fk_bookings_facid FOREIGN KEY (facid) REFERENCES cd.facilities (facid),
    CONSTRAINT fk_bookings_memid FOREIGN KEY (memid) REFERENCES cd.members (memid)
);

INSERT INTO cd.facilities (facid, membercost, guestcost)
VALUES (0, 5, 25),
       (1, 5, 25),
       (2, 0, 15.5),
       (3, 0, 5),
       (4, 35, 80),
       (5, 35, 80),
       (6, 3.5, 17.5),
       (7, 0, 5),
       (8, 0, 5);

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

INSERT INTO cd.bookings (bookid, facid, memid, slots, starttime)
VALUES (0, 0, 0, 5, '2013-08-12 08:30:00'),
       (1, 1, 1, 6, '2012-08-23 08:30:00'),
       (2, 4, 2, 6, '2012-08-24 08:30:00'),
       (3, 2, 3, 7, '2012-08-22 08:30:00'),
       (4, 0, 0, 3, '2013-08-11 08:30:00'),
       (5, 1, 2, 6, '2012-08-24 08:30:00'),
       (6, 6, 0, 1, '2012-08-16 08:30:00'),
       (7, 1, 2, 1, '2012-06-25 08:30:00'),
       (8, 5, 1, 2, '2013-01-15 08:30:00'),
       (9, 4, 2, 4, '2012-03-06 08:30:00'),
       (10, 0, 1, 8, '2012-08-01 08:30:00'),
       (11, 1, 0, 1, '2012-08-05 08:30:00'),
       (12, 8, 2, 7, '2012-11-06 08:30:00'),
       (13, 2, 0, 1, '2013-09-07 08:30:00'),
       (14, 0, 3, 8, '2012-08-02 08:30:00'),
       (15, 8, 0, 5, '2012-09-07 08:30:00'),
       (16, 4, 3, 1, '2013-08-02 08:30:00'),
       (17, 8, 0, 9, '2012-09-07 08:30:00'),
       (18, 3, 2, 6, '2012-08-04 08:30:00'),
       (19, 5, 0, 2, '2012-04-12 08:30:00'),
       (20, 3, 1, 3, '2012-02-01 08:30:00');


SELECT dategen.date,
       (SELECT SUM(CASE
                       WHEN memid = 0
                           THEN slots * fac.guestcost
                       ELSE slots * fac.membercost
           END) as rev
        FROM cd.bookings bks
                 JOIN cd.facilities fac
                      ON bks.facid = fac.facid
        WHERE bks.starttime > dategen.date - INTERVAL '14 days'
          AND bks.starttime < dategen.date + INTERVAL '1 day') / 15 AS revenue
FROM (SELECT CAST(GENERATE_SERIES(TIMESTAMP '2012-08-01', '2012-08-31', '1 day')
                 AS DATE) AS DATE) AS dategen
ORDER BY dategen.date;
