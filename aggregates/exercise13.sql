CREATE SCHEMA cd;

CREATE TABLE cd.facilities
(
    facid      integer                NOT NULL,
    name       character varying(100) NOT NULL,
    CONSTRAINT facilities_pk PRIMARY KEY (facid)
);

CREATE TABLE cd.bookings
(
    bookid    integer   NOT NULL,
    facid     integer   NOT NULL,
    slots     integer   NOT NULL,
    CONSTRAINT bookings_pk PRIMARY KEY (bookid),
    CONSTRAINT fk_bookings_facid FOREIGN KEY (facid) REFERENCES cd.facilities (facid)
);

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

INSERT INTO cd.bookings (bookid, facid,slots)
VALUES (0,8, 50),
       (1, 1, 60),
       (2, 2, 60),
       (3, 3, 70),
       (4, 7, 30),
       (5, 2, 60),
       (6, 0, 10),
       (7, 6, 10),
       (8,  1, 20),
       (9,  2, 40),
       (10,  1, 80),
       (11,  5, 10),
       (12, 2, 70),
       (13,  0, 10),
       (14,  3, 80),
       (15,  0, 50),
       (16, 3, 10),
       (17, 0, 90),
       (18,  2, 60),
       (19, 0, 20),
       (20,  1, 30);

SELECT fac.facid, fac.name, CAST (sum(bks.slots/2.0)AS DECIMAL(5, 2)) AS "Total Hours"
FROM cd.bookings bks
         JOIN cd.facilities fac ON fac.facid = bks.facid
GROUP BY fac.facid, fac.name
ORDER BY fac.facid;
