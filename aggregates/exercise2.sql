CREATE SCHEMA cd;

CREATE TABLE cd.facilities
(
    facid     integer NOT NULL,
    guestcost numeric NOT NULL,
    CONSTRAINT facilities_pk PRIMARY KEY (facid)
);

INSERT INTO cd.facilities (facid, guestcost)
VALUES (0, 25),
       (1, 25),
       (2, 15.5),
       (3, 5),
       (4, 80),
       (5, 80),
       (6, 17.5),
       (7, 5),
       (8, 5);

SELECT COUNT(*)
FROM cd.facilities
WHERE guestcost > 9;