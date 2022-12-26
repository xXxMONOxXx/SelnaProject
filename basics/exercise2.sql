CREATE SCHEMA cd;
CREATE TABLE cd.facilities
(
    facid              integer                NOT NULL,
    name               character varying(100) NOT NULL,
    membercost         numeric                NOT NULL,
    CONSTRAINT facilities_pk PRIMARY KEY (facid)
);

INSERT INTO cd.facilities (facid, name, membercost)
VALUES (0, 'Tennis Court 1', 5),
       (1, 'Tennis Court 2', 5),
       (2, 'Badminton Court', 0),
       (3, 'Table Tennis', 0),
       (4, 'Massage Room 1', 35),
       (5, 'Massage Room 2', 35),
       (6, 'Squash Court', 3.5),
       (7, 'Snooker Table', 0),
       (8, 'Pool Table', 0);
SELECT name, membercost
FROM cd.facilities;