CREATE SCHEMA cd;

CREATE TABLE cd.facilities
(
    facid integer NOT NULL,
    CONSTRAINT facilities_pk PRIMARY KEY (facid)
);

CREATE TABLE cd.bookings
(
    bookid integer NOT NULL,
    facid  integer NOT NULL,
    slots  integer NOT NULL,
    CONSTRAINT bookings_pk PRIMARY KEY (bookid),
    CONSTRAINT fk_bookings_facid FOREIGN KEY (facid) REFERENCES cd.facilities (facid)
);


INSERT INTO cd.facilities(facid)
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

INSERT INTO cd.bookings (bookid, facid, slots)
VALUES (0, 0, 500),
       (1, 1, 600),
       (2, 4, 600),
       (3, 2, 700),
       (4, 0, 300),
       (5, 1, 600),
       (6, 6, 100),
       (7, 1, 100),
       (8, 5, 200),
       (9, 4, 400),
       (10, 0, 800),
       (11, 1, 100),
       (12, 8, 700),
       (13, 2, 100),
       (14, 0, 800),
       (15, 9, 500),
       (16, 4, 100),
       (17, 10, 110),
       (18, 3, 600),
       (19, 5, 200),
       (20, 3, 300);

SELECT facid, sum(slots) AS "Total Slots"
FROM cd.bookings
GROUP BY facid
HAVING sum(slots) > 1000
ORDER BY facid;