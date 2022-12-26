CREATE SCHEMA cd;
CREATE TABLE cd.members
(
    memid   integer                NOT NULL,
    surname character varying(200) NOT NULL,
    CONSTRAINT members_pk PRIMARY KEY (memid)
);

CREATE TABLE cd.facilities
(
    facid integer NOT NULL,
    name character varying(100) NOT NULL,
    CONSTRAINT facilities_pk PRIMARY KEY (facid)
);

INSERT INTO cd.members (memid, surname)
VALUES (0, 'GUEST'),
       (1, 'Smith'),
       (2, 'Smith'),
       (3, 'Rownam'),
       (4, 'Joplette'),
       (5, 'Butters'),
       (6, 'Tracy'),
       (7, 'Dare'),
       (8, 'Boothe'),
       (9, 'Stibbons'),
       (10, 'Owen'),
       (11, 'Jones'),
       (12, 'Baker'),
       (13, 'Farrell'),
       (14, 'Smith'),
       (15, 'Bader'),
       (16, 'Baker'),
       (17, 'Pinker'),
       (20, 'Genting'),
       (21, 'Mackenzie'),
       (22, 'Coplin'),
       (24, 'Sarwin'),
       (26, 'Jones'),
       (27, 'Rumney'),
       (28, 'Farrell'),
       (29, 'Worthington-Smyth'),
       (30, 'Purview'),
       (33, 'Tupperware'),
       (35, 'Hunt'),
       (36, 'Crumpet'),
       (37, 'Smith');

INSERT INTO cd.facilities (facid, name)
VALUES (0, 'Tennis Court 1'),
       (1, 'Tennis Court 2'),
       (2, 'Badminton Court'),
       (3, 'Table Tennis'),
       (4, 'Massage Room 1'),
       (5, 'Massage Room 2'),
       (6, 'Squash Court'),
       (7, 'Snooker Table'),
       (8, 'Pool Table');

SELECT surname
FROM cd.members
UNION
SELECT name
FROM cd.facilities;