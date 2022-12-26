CREATE SCHEMA cd;

CREATE TABLE cd.bookings
(
    bookid integer NOT NULL,
    facid  integer NOT NULL,
    slots  integer NOT NULL,
    CONSTRAINT bookings_pk PRIMARY KEY (bookid)
);

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
       (9, 1, 2),
       (10, 3, 2);

SELECT facid, total
FROM (
         SELECT facid, SUM(slots) total, RANK() OVER (ORDER BY SUM(slots) DESC) rank
         FROM cd.bookings
         GROUP BY facid) AS ranked
WHERE rank = 1;