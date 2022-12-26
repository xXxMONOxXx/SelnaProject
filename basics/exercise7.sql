CREATE SCHEMA cd;
CREATE TABLE cd.facilities
(
    facid              integer                NOT NULL,
    name               character varying(100) NOT NULL,
    monthlymaintenance numeric                NOT NULL,
    CONSTRAINT facilities_pk PRIMARY KEY (facid)
);

INSERT INTO cd.facilities (facid, name, monthlymaintenance)
VALUES (0, 'Tennis Court 1', 200),
       (1, 'Tennis Court 2', 200),
       (2, 'Badminton Court', 50),
       (3, 'Table Tennis', 10),
       (4, 'Massage Room 1', 3000),
       (5, 'Massage Room 2', 3000),
       (6, 'Squash Court', 80),
       (7, 'Snooker Table', 15),
       (8, 'Pool Table', 15);
SELECT name,
       CASE
           WHEN monthlymaintenance > 100 THEN 'expensive'
           ELSE 'cheap'
           END AS cost
FROM cd.facilities;