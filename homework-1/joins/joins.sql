-- 
-- JOINS AND SUBQUERIES
--

-- Retrieve the start times of members' bookings
SELECT starttime 
FROM cd.bookings
JOIN cd.members ON cd.members.memid = cd.bookings.memid
WHERE LOWER (firstname) = 'david' 
	AND LOWER (surname) = 'farrell'

-- Work out the start times of bookings for tennis courts
SELECT cd.bookings.starttime AS start,
	cd.facilities.name AS name 
FROM cd.bookings 
JOIN cd.facilities ON cd.bookings.facid = cd.facilities.facid
WHERE LOWER (name) LIKE ('tennis%')
	AND cd.bookings.starttime BETWEEN '2012-09-21 00:00:00' AND '2012-09-21 23:59:59'
ORDER BY start

-- Produce a list of all members who have recommended another member
SELECT DISTINCT r.firstname AS firstname, 
	r.surname AS surname 
FROM cd.members m
JOIN cd.members r ON r.memid=m.recommendedby
ORDER BY surname, firstname

-- Produce a list of all members, along with their recommender
SELECT m.firstname AS memfname,
	m.surname AS memsname,
	r.firstname AS recfname, 
	r.surname AS recsname
FROM cd.members m
LEFT JOIN cd.members r ON r.memid = m.recommendedby
ORDER BY memsname, memfname, recsname, recfname

-- Produce a list of all members who have used a tennis court
SELECT DISTINCT m.firstname || ' ' || m.surname AS member, 
	f.name AS facility
FROM cd.members m 
JOIN cd.bookings b ON m.memid = b.memid
JOIN cd.facilities f ON b.facid = f.facid
WHERE LOWER (f.name) LIKE ('tennis%')
ORDER BY member, facility

-- Produce a list of costly bookings
SELECT  m.firstname || ' ' || m.surname AS member, 
	f.name AS facility, 
	CASE 
		WHEN m.memid = 0
		THEN b.slots*f.guestcost
		ELSE b.slots*f.membercost
	END AS cost
FROM cd.members m                
JOIN cd.bookings b ON m.memid = b.memid
JOIN cd.facilities f ON b.facid = f.facid
WHERE b.starttime 
	BETWEEN '2012-09-14 00:00:00' AND '2012-09-14 23:59:59'
    AND ((m.memid = 0 AND b.slots*f.guestcost > 30)
		OR (m.memid != 0 AND b.slots*f.membercost > 30))
ORDER BY cost DESC

-- Produce a list of all members, along with their recommender, using no joins.
SELECT DISTINCT m.firstname || ' ' ||  m.surname as member,
	(SELECT r.firstname || ' ' || r.surname as recommender 
	FROM cd.members r 
	WHERE r.memid = m.recommendedby)
FROM cd.members m
ORDER BY member

-- Produce a list of costly bookings, using a subquery
SELECT member, 
	facility, 
	cost 
FROM 
    (SELECT m.firstname || ' ' || m.surname as member, 
	 	f.name AS facility,
		CASE
			WHEN m.memid = 0 
            THEN b.slots*f.guestcost
			ELSE b.slots*f.membercost
		END AS cost
	FROM cd.members m
	JOIN cd.bookings b ON m.memid = b.memid
	JOIN cd.facilities f ON b.facid = f.facid
	WHERE b.starttime BETWEEN '2012-09-14 00:00:00' AND '2012-09-14 23:59:59') AS bookings
WHERE cost > 30
ORDER BY cost DESC
