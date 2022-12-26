CREATE SCHEMA cd;

CREATE TABLE cd.facilities
(
    facid      integer                NOT NULL,
    CONSTRAINT facilities_pk PRIMARY KEY (facid)
);

CREATE TABLE cd.bookings
(
    bookid    integer   NOT NULL,
    facid     integer   NOT NULL,
    starttime timestamp NOT NULL,
    slots     integer   NOT NULL,
    CONSTRAINT bookings_pk PRIMARY KEY (bookid),
    CONSTRAINT fk_bookings_facid FOREIGN KEY (facid) REFERENCES cd.facilities (facid)
);

INSERT INTO cd.facilities (facid)
VALUES (0),
       (1),
       (2),
       (3),
       (4),
       (5),
       (6),
       (7),
       (8);

INSERT INTO cd.bookings (bookid, facid, starttime, slots)
VALUES (0, 0,'2012-09-14 09:15', 4),
       (1, 1, '2012-09-9 11:15:22', 5),
       (2, 4, '2012-09-10 10:45:25', 3),
       (3, 2, '2012-09-14 15:05:31', 8),
       (4, 0, '2012-09-14 14:45:32', 10),
       (5, 2, '2012-09-11 15:05:48', 4),
       (6, 4, '2012-09-14 15:05:57', 5),
       (7, 1, '2012-09-12 14:45:56', 7),
       (8, 3, '2012-09-14 10:45:12', 8),
       (9, 4,  '2012-09-16 10:45:00', 3),
       (10, 3, '2012-09-13 11:16:12', 2);

SELECT facid, sum(slots) AS "Total Slots"
FROM cd.bookings
WHERE cd.bookings.starttime >= '2012-09-01' AND cd.bookings.starttime < '2012-10-01'
GROUP BY facid
ORDER BY "Total Slots";