CREATE SCHEMA cd;
CREATE TABLE cd.members
(
    memid   integer                NOT NULL,
    surname character varying(200) NOT NULL,
    CONSTRAINT members_pk PRIMARY KEY (memid)
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

SELECT DISTINCT surname
FROM cd.members
ORDER BY surname
LIMIT 10;