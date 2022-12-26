CREATE SCHEMA cd;
CREATE TABLE cd.facilities
(
    facid              integer                NOT NULL,
    name               character varying(100) NOT NULL,
    membercost         numeric                NOT NULL,
    monthlymaintenance numeric                NOT NULL,
    CONSTRAINT facilities_pk PRIMARY KEY (facid)
);

INSERT INTO cd.facilities (facid, name, membercost, monthlymaintenance)
VALUES (0, 'Tennis Court 1', 5, 200),
       (1, 'Tennis Court 2', 5, 200),
       (2, 'Badminton Court', 0, 50),
       (3, 'Table Tennis', 0, 10),
       (4, 'Massage Room 1', 35, 3000),
       (5, 'Massage Room 2', 35, 3000),
       (6, 'Squash Court', 3.5,  80),
       (7, 'Snooker Table', 0, 15),
       (8, 'Pool Table', 0, 15);
SELECT facid, name, membercost, monthlymaintenance
FROM cd.facilities
WHERE monthlymaintenance/50 > membercost AND membercost > 0;