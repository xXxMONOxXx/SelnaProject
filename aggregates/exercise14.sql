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
       (5, 'Butters', 'Gerald'),
       (6, 'Tracy', 'Burton'),
       (7, 'Dare', 'Nancy'),
       (8, 'Boothe', 'Tim'),
       (9, 'Stibbons', 'Ponder'),
       (10, 'Owen', 'Charles');

INSERT INTO cd.bookings (bookid, memid, starttime)
VALUES (0, 6, '2012-09-14 09:15'),
       (1, 2, '2012-09-9 11:15:22'),
       (2, 5, '2012-09-10 10:45:25'),
       (3, 8, '2012-09-14 15:05:31'),
       (4, 10, '2012-09-14 14:45:32'),
       (5, 0, '2012-09-11 15:05:48'),
       (6, 4, '2012-09-14 15:05:57'),
       (7, 0, '2012-09-12 14:45:56'),
       (8, 7, '2012-09-14 10:45:12'),
       (9, 4, '2012-09-16 10:45:00'),
       (10, 2, '2012-09-13 11:16:12'),
       (11, 6, '2012-09-14 09:15'),
       (12, 2, '2012-09-9 11:15:22'),
       (13, 5, '2012-09-10 10:45:25'),
       (14, 8, '2012-09-14 15:05:31'),
       (15, 1, '2012-09-14 14:45:32'),
       (16, 3, '2012-09-11 15:05:48'),
       (17, 4, '2012-09-14 15:05:57'),
       (18, 0, '2012-09-12 14:45:56'),
       (19, 7, '2012-09-14 10:45:12'),
       (20, 4, '2012-09-16 10:45:00'),
       (21, 2, '2012-09-13 11:16:12');


SELECT surname, firstname, mem.memid, min(bks.starttime) AS starttime
FROM cd.members mem
         JOIN cd.bookings bks
              ON bks.memid = mem.memid
WHERE starttime > '2012-09-01'
GROUP BY (surname, firstname, mem.memid)
ORDER BY memid;