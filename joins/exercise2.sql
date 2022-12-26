CREATE SCHEMA cd;

CREATE TABLE cd.facilities
(
    facid integer                NOT NULL,
    name  character varying(100) NOT NULL,
    CONSTRAINT facilities_pk PRIMARY KEY (facid)
);

CREATE TABLE cd.bookings
(
    bookid    integer   NOT NULL,
    facid     integer   NOT NULL,
    starttime timestamp NOT NULL,
    CONSTRAINT bookings_pk PRIMARY KEY (bookid),
    CONSTRAINT fk_bookings_facid FOREIGN KEY (facid) REFERENCES cd.facilities (facid)
);

INSERT INTO cd.facilities(facid, name)
VALUES (0, 'Tennis Court 1'),
       (1, 'Tennis Court 2'),
       (2, 'Badminton Court'),
       (3, 'Table Tennis'),
       (4, 'Massage Room 1'),
       (5, 'Massage Room 2'),
       (6, 'Squash Court'),
       (7,'Snooker Table'),
       (8,'Pool Table');

INSERT INTO cd.bookings (bookid, facid, starttime)
VALUES (0, 0, '2012-09-18 09:00:00'),
       (1, 6, '2012-09-18 17:30:00'),
       (4, 1, '2012-09-19 09:30:00'),
       (5, 6, '2012-09-19 15:00:00'),
       (6, 0, '2012-09-19 12:00:00'),
       (7, 3, '2012-09-20 15:30:00'),
       (8, 0, '2012-09-21 11:30:00'),
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
       (23, 0, '2012-09-21 17:00:00'),
       (24, 1, '2012-09-21 08:00:00'),
       (25, 0, '2012-09-28 11:30:00'),
       (26, 6, '2012-09-28 09:30:00'),
       (27, 6, '2012-09-28 13:00:00'),
       (28, 4, '2012-09-29 16:00:00'),
       (29, 6, '2012-09-29 10:30:00'),
       (30, 3, '2012-09-29 13:30:00'),
       (31, 6, '2012-09-29 14:30:00'),
       (32, 1, '2012-09-21 17:30:00'),
       (33, 0, '2012-09-30 14:30:00');

SELECT cd.bookings.starttime as start, cd.facilities.name
FROM cd.bookings
         INNER JOIN cd.facilities ON cd.bookings.facid = cd.facilities.facid
WHERE cd.facilities.name LIKE '%Tennis Court%'
  AND (starttime BETWEEN '2012-09-21' AND '2012-09-22')
ORDER BY cd.bookings.starttime;