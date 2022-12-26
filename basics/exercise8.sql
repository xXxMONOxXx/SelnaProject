CREATE SCHEMA cd;
CREATE TABLE cd.members
(
    memid     integer                NOT NULL,
    surname   character varying(200) NOT NULL,
    firstname character varying(200) NOT NULL,
    joindate  timestamp              NOT NULL,
    CONSTRAINT members_pk PRIMARY KEY (memid)
);

INSERT INTO cd.members (memid, surname, firstname, joindate)
VALUES (0, 'GUEST', 'GUEST', '2012-07-01 00:00:00'),
       (1, 'Smith', 'Darren', '2012-07-02 12:02:05'),
       (2, 'Smith', 'Tracy', '2012-07-02 12:08:23'),
       (3, 'Rownam', 'Tim', '2012-07-03 09:32:15'),
       (4, 'Joplette', 'Janice', '2012-07-03 10:25:05'),
       (5, 'Butters', 'Gerald', '2012-07-09 10:44:09'),
       (6, 'Tracy', 'Burton', '2012-07-15 08:52:55'),
       (7, 'Dare', 'Nancy', '2012-07-25 08:59:12'),
       (8, 'Boothe', 'Tim', '2012-07-25 16:02:35'),
       (9, 'Stibbons', 'Ponder', '2012-07-25 17:09:05'),
       (10, 'Owen', 'Charles', '2012-08-03 19:42:37'),
       (11, 'Jones', 'David', '2012-08-06 16:32:55'),
       (12, 'Baker', 'Anne', '2012-08-10 14:23:22'),
       (13, 'Farrell', 'Jemima', '2012-08-10 14:28:01'),
       (14, 'Smith', 'Jack', '2012-08-10 16:22:05'),
       (15, 'Bader', 'Florence', '2012-08-10 17:52:03'),
       (16, 'Baker', 'Timothy', '2012-08-15 10:34:25'),
       (17, 'Pinker', 'David', '2012-08-16 11:32:47'),
       (20, 'Genting', 'Matthew', '2012-08-19 14:55:55'),
       (21, 'Mackenzie', 'Anna', '2012-08-26 09:32:05'),
       (22, 'Coplin', 'Joan', '2012-08-29 08:32:41'),
       (24, 'Sarwin', 'Ramnaresh', '2012-09-01 08:44:42'),
       (26, 'Jones', 'Douglas', '2012-09-02 18:43:05'),
       (27, 'Rumney', 'Henrietta', '2012-09-05 08:42:35'),
       (28, 'Farrell', 'David', '2012-09-15 08:22:05'),
       (29, 'Worthington-Smyth', 'Henry', '2012-09-17 12:27:15'),
       (30, 'Purview', 'Millicent', '2012-09-18 19:04:01'),
       (33, 'Tupperware', 'Hyacinth', '2012-09-18 19:32:05'),
       (35, 'Hunt', 'John', '2012-09-19 11:32:45'),
       (36, 'Crumpet', 'Erica', '2012-09-22 08:36:38'),
       (37, 'Smith', 'Darren', '2012-09-26 18:08:45');

SELECT memid, surname, firstname, joindate
FROM cd.members
WHERE joindate > '2012-09-01 00:00:00';