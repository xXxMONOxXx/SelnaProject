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

INSERT INTO cd.bookings (bookid, facid, slots)
VALUES (0, 0, 4),
       (1, 1, 5),
       (2, 4, 3),
       (3, 2, 8),
       (4, 0, 10),
       (5, 2, 4),
       (6, 4, 5),
       (7, 1, 7),
       (8, 3, 8),
       (9, 4, 3),
       (10, 3, 2);

SELECT facid, sum(slots) AS "Total Slots"
FROM cd.bookings
GROUP BY facid
ORDER BY facid;
