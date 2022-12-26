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
    bookid    integer   NOT NULL,
    memid     integer   NOT NULL,
    starttime timestamp NOT NULL,
    CONSTRAINT bookings_pk PRIMARY KEY (bookid),
    CONSTRAINT fk_bookings_memid FOREIGN KEY (memid) REFERENCES cd.members (memid)
);

INSERT INTO cd.members (memid, surname, firstname)
VALUES (0, 'GUEST', 'GUEST'),
       (1, 'Smith', 'Darren'),
       (2, 'Smith', 'Tracy'),
       (3, 'Rownam', 'Tim'),
       (4, 'Joplette', 'Janice'),
       (5, 'Kolokol', 'David'),
       (6, 'Farrell', 'David');

INSERT INTO cd.bookings (bookid, memid, starttime)
VALUES (0, 0, '2012-09-18 09:00:00'),
       (1, 6, '2012-09-18 17:30:00'),
       (4, 1, '2012-09-19 09:30:00'),
       (5, 6, '2012-09-19 15:00:00'),
       (6, 0, '2012-09-19 12:00:00'),
       (7, 3, '2012-09-20 15:30:00'),
       (8, 0, '2012-09-20 11:30:00'),
       (9, 6, '2012-09-20 14:00:00'),
       (10, 6, '2012-09-21 10:30:00'),
       (11, 0, '2012-09-21 14:00:00'),
       (12, 1, '2012-09-22 08:30:00'),
       (13, 6, '2012-09-22 17:00:00'),
       (14, 2, '2012-09-23 08:30:00'),
       (15, 4, '2012-09-23 17:30:00'),
       (16, 6, '2012-09-23 19:00:00'),
       (17, 5, '2012-09-24 08:00:00'),
       (18, 0, '2012-09-24 16:30:00'),
       (19, 6, '2012-09-24 12:30:00'),
       (20, 5, '2012-09-25 15:30:00'),
       (21, 6, '2012-09-25 17:00:00'),
       (22, 2, '2012-09-26 13:00:00'),
       (23, 6, '2012-09-26 17:00:00'),
       (24, 1, '2012-09-27 08:00:00'),
       (25, 0, '2012-09-28 11:30:00'),
       (26, 6, '2012-09-28 09:30:00'),
       (27, 6, '2012-09-28 13:00:00'),
       (28, 4, '2012-09-29 16:00:00'),
       (29, 6, '2012-09-29 10:30:00'),
       (30, 3, '2012-09-29 13:30:00'),
       (31, 6, '2012-09-29 14:30:00'),
       (32, 1, '2012-09-29 17:30:00'),
       (33, 0, '2012-09-30 14:30:00');

SELECT starttime
FROM cd.bookings
         INNER JOIN cd.members
                    ON cd.members.memid = cd.bookings.memid
WHERE cd.members.firstname = 'David'
  AND cd.members.surname = 'Farrell';
