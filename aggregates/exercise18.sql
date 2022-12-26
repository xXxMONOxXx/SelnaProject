CREATE SCHEMA cd;

CREATE TABLE cd.members
(
    memid     integer                NOT NULL,
    surname   character varying(200) NOT NULL,
    firstname character varying(200) NOT NULL,
    CONSTRAINT members_pk PRIMARY KEY (memid)
);

CREATE TABLE cd.bookings
(
    bookid integer NOT NULL,
    facid  integer NOT NULL,
    slots  integer NOT NULL,
    memid  integer NOT NULL,
    CONSTRAINT bookings_pk PRIMARY KEY (bookid),
    CONSTRAINT fk_bookings_memid FOREIGN KEY (memid) REFERENCES cd.members (memid)
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

INSERT INTO cd.bookings (bookid, facid, memid, slots)
VALUES (0, 0, 4, 10),
       (1, 1, 5, 20),
       (2, 4, 3, 50),
       (3, 2, 8, 80),
       (4, 0, 10, 16),
       (5, 2, 4, 65),
       (6, 4, 5, 30),
       (7, 1, 7, 60),
       (8, 3, 8, 70),
       (9, 4, 3, 40),
       (10, 3, 2, 73),
       (11, 4, 7, 40),
       (12, 3, 3, 60);

SELECT firstname,
       surname,
       ((SUM(bks.slots) + 10) / 20) * 10                             AS hours,
       RANK() OVER (ORDER BY ((SUM(bks.slots) + 10) / 20) * 10 DESC) AS rank
FROM cd.bookings bks
         JOIN cd.members mem
              ON bks.memid = mem.memid
GROUP BY mem.memid, surname, firstname
ORDER BY rank, surname, firstname;