CREATE SCHEMA cd;

CREATE TABLE cd.members
(
    memid integer NOT NULL,
    CONSTRAINT members_pk PRIMARY KEY (memid)
);

CREATE TABLE cd.bookings
(
    bookid integer NOT NULL,
    memid  integer NOT NULL,
    CONSTRAINT bookings_pk PRIMARY KEY (bookid),
    CONSTRAINT fk_bookings_memid FOREIGN KEY (memid) REFERENCES cd.members (memid)
);


INSERT INTO cd.members (memid)
VALUES (0),
       (1),
       (2),
       (3),
       (4),
       (5),
       (6),
       (7),
       (8),
       (9),
       (10);

INSERT INTO cd.bookings (bookid, memid)
VALUES (0, 0),
       (1, 1),
       (2, 4),
       (3, 2),
       (4, 0),
       (5, 2),
       (6, 4),
       (7, 1),
       (8, 3),
       (9, 4),
       (10, 3);

SELECT COUNT(*)
FROM cd.members
WHERE (SELECT COUNT(*) FROM cd.bookings WHERE cd.members.memid = cd.bookings.memid) > 0;
