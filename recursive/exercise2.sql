CREATE SCHEMA cd;

CREATE TABLE cd.members
(
    memid         integer                NOT NULL,
    surname       character varying(200) NOT NULL,
    firstname     character varying(200) NOT NULL,
    recommendedby integer,
    CONSTRAINT members_pk PRIMARY KEY (memid),
    CONSTRAINT fk_members_recommendedby FOREIGN KEY (recommendedby)
        REFERENCES cd.members (memid) ON DELETE SET NULL
);

INSERT INTO cd.members (memid, surname, firstname, recommendedby)
VALUES (0, 'GUEST', 'GUEST', NULL),
       (1, 'Smith', 'Darren', NULL),
       (2, 'Smith', 'Tracy', NULL),
       (3, 'Rownam', 'Tim', NULL),
       (4, 'Joplette', 'Janice', 1),
       (5, 'Butters', 'Gerald', 1),
       (6, 'Tracy', 'Burton', NULL),
       (7, 'Dare', 'Nancy', 4),
       (8, 'Boothe', 'Tim', 3),
       (9, 'Stibbons', 'Ponder', 6),
       (10, 'Owen', 'Charles', 1),
       (11, 'Jones', 'David', 4),
       (12, 'Baker', 'Anne', 9),
       (13, 'Farrell', 'Jemima', NULL),
       (14, 'Smith', 'Jack', 1),
       (15, 'Bader', 'Florence', 9),
       (16, 'Baker', 'Timothy', 13),
       (17, 'Pinker', 'David', 13),
       (20, 'Genting', 'Matthew', 5),
       (21, 'Mackenzie', 'Anna', 1),
       (22, 'Coplin', 'Joan', 16),
       (24, 'Sarwin', 'Ramnaresh', 15),
       (26, 'Jones', 'Douglas', 11),
       (27, 'Rumney', 'Henrietta', 20),
       (28, 'Farrell', 'David', NULL),
       (29, 'Worthington-Smyth', 'Henry', 2),
       (30, 'Purview', 'Millicent', 2),
       (33, 'Tupperware', 'Hyacinth', NULL),
       (35, 'Hunt', 'John', 30),
       (36, 'Crumpet', 'Erica', 2),
       (37, 'Smith', 'Darren', NULL);

WITH RECURSIVE recommendeds (memid) as (
    SELECT memid FROM cd.members WHERE recommendedby = 1
    UNION ALL
    SELECT mem.memid
    FROM recommendeds rec
             JOIN cd.members mem
                  ON mem.recommendedby = rec.memid)
SELECT rec.memid, mem.firstname, mem.surname
FROM recommendeds rec
         JOIN cd.members mem
              ON rec.memid = mem.memid
ORDER BY memid;