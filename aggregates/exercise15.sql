CREATE SCHEMA cd;

CREATE TABLE cd.members
(
    memid     integer                NOT NULL,
    surname   character varying(200) NOT NULL,
    firstname character varying(200) NOT NULL,
    CONSTRAINT members_pk PRIMARY KEY (memid)
);

INSERT INTO cd.members (memid, surname, firstname)
VALUES (0, 'GUEST', 'GUEST'),
       (1, 'Smith', 'Darren'),
       (2, 'Smith', 'Tracy'),
       (3, 'Rownam', 'Tim'),
       (4, 'Joplette', 'Janice'),
       (5, 'Butters', 'Gerald'),
       (6, 'Tracy', 'Burton'),
       (7, 'Dare', 'Nancy'),
       (8, 'Boothe', 'Tim'),
       (9, 'Stibbons', 'Ponder'),
       (10, 'Owen', 'Charles');



SELECT (SELECT COUNT(*) FROM cd.members) as count, firstname, surname
FROM cd.members;