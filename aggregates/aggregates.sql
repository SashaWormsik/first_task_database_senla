--
-- Aggregation
--

-- Count the number of facilities
SELECT COUNT (*) FROM cd.facilities

-- Count the number of expensive facilities
SELECT COUNT (*) 
FROM cd.facilities
WHERE guestcost >= 10

-- Count the number of recommendations each member makes
SELECT recommendedby, 
	COUNT (*) 
FROM cd.members
WHERE recommendedby IS NOT null
GROUP BY recommendedby
ORDER BY recommendedby

-- List the total slots booked per facility
SELECT facid, 
	SUM (slots) AS TotalSlots 
FROM cd.bookings
GROUP BY facid
ORDER BY facid

-- List the total slots booked per facility in a given month
SELECT facid, 
	SUM (slots) AS TotalSlots 
FROM cd.bookings
WHERE starttime BETWEEN '2012-09-01 00:00:00' AND '2012-09-30 23:59:59'
GROUP BY facid
ORDER BY SUM (slots)

-- List the total slots booked per facility per month
SELECT facid, 
	EXTRACT (month FROM starttime) AS month, 
	SUM (slots) AS TotalSlots
FROM cd.bookings
WHERE EXTRACT (year FROM starttime) = 2012
GROUP BY facid, month
ORDER BY facid, month

-- Find the count of members who have made at least one booking
SELECT COUNT (DISTINCT memid) FROM cd.bookings

-- List facilities with more than 1000 slots booked
SELECT facid, 
	SUM (slots) AS TotalSlots 
FROM cd.bookings
GROUP BY facid
HAVING SUM (slots) > 1000
ORDER BY facid

-- Find the total revenue of each facility
SELECT name, 
	SUM (slots * CASE
					WHEN memid = 0 
					THEN f.guestcost 
					ELSE f.membercost 
		 		END) AS revenue 
FROM cd.bookings b
INNER JOIN cd.facilities f ON f.facid = b.facid
GROUP BY f.name 
ORDER BY revenue

-- Find facilities with a total revenue less than 1000
SELECT name, 
	revenue 
FROM 
	(SELECT name, 
	 	SUM (slots * CASE 
			WHEN memid = 0 
			THEN f.guestcost 
			ELSE f.membercost 
		END) AS revenue 
    FROM cd.bookings b
    INNER JOIN cd.facilities f ON f.facid = b.facid
    GROUP BY f.name) AS TempTable
WHERE revenue < 1000
ORDER BY revenue

-- Output the facility id that has the highest number of slots booked
SELECT facid, 
	SUM (slots) AS TotalSlots 
FROM cd.bookings
GROUP BY facid
ORDER BY TotalSlots DESC
LIMIT 1

-- List the total slots booked per facility per month, part 2
SELECT facid, 
	EXTRACT(month FROM starttime) AS month, 
	SUM(slots) AS slots
FROM cd.bookings
WHERE starttime BETWEEN '2012-01-01 00:00:00' AND '2012-12-31 23:59:59'
GROUP BY rollup(facid, month)
ORDER BY facid, month

-- List the total hours booked per named facility
SELECT f.facid, 
	f.name,
	TRIM(TO_CHAR(SUM(bks.slots)/2.0, '9999999999990D99')) AS TotalHours
FROM cd.bookings b
INNER JOIN cd.facilities f ON f.facid = b.facid
GROUP BY f.facid, f.name
ORDER BY f.facid

-- List each member's first booking after September 1st 2012
SELECT m.surname, 
	m.firstname, 
	m.memid, 
	MIN (b.starttime) AS starttime
FROM cd.bookings b
JOIN cd.members m ON m.memid = b.memid
WHERE starttime >= '2012-09-01'
GROUP BY m.surname, m.firstname, m.memid
ORDER BY m.memid

-- Produce a list of member names, with each row containing the total member count
SELECT COUNT(*) OVER(), 
	firstname,
	surname 
FROM cd.members
ORDER BY joindate 

-- Produce a numbered list of members
SELECT row_number() OVER(ORDER BY joindate), 
	firstname,
	surname 
FROM cd.members
ORDER BY joindate 

-- Output the facility id that has the highest number of slots booked, again
SELECT facid, 
	total 
FROM 
	(SELECT facid, 
	 	SUM(slots) total, 
	 	RANK () OVER (ORDER BY SUM(slots) DESC) AS rank
    FROM cd.bookings
	GROUP BY facid) AS ranked
WHERE rank = 1 

-- Rank members by (rounded) hours used
SELECT firstname, 
	surname,
	((SUM(b.slots)+10)/20)*10 AS hours,
	RANK() OVER (ORDER BY ((SUM(b.slots)+10)/20)*10 DESC) AS rank
FROM cd.bookings b
JOIN cd.members m ON b.memid = m.memid
GROUP BY m.memid
ORDER BY rank, surname, firstname  

-- Find the top three revenue generating facilities
SELECT name, 
	rank 
FROM 
	(SELECT f.name AS name,
	 	RANK() OVER (ORDER BY SUM (
		    CASE 
				WHEN memid = 0 
		    	THEN slots * f.guestcost
				ELSE slots * membercost
			END) 
		DESC) AS rank
	FROM cd.bookings b
	JOIN cd.facilities f ON b.facid = f.facid
	GROUP BY f.name) AS temp
WHERE rank <= 3
ORDER BY rank

-- Classify facilities by value
SELECT name, 
    CASE 
	    WHEN temp=1 THEN 'high'
		WHEN temp=2 THEN 'average'
		ELSE 'low'
	END revenue
FROM 
	(SELECT f.name AS name, NTILE(3) OVER (
		ORDER BY SUM(
	       CASE 
				WHEN memid = 0 
				THEN slots * f.guestcost 
				ELSE slots * membercost
		   END) 
	   DESC) AS temp
    FROM cd.bookings b
    JOIN cd.facilities f ON b.facid = f.facid
	GROUP BY f.name) AS tabtemp
ORDER BY temp, name

-- Calculate the payback time for each facility
SELECT 	f.name AS name, 
	f.initialoutlay/((SUM(
    	CASE
			WHEN memid = 0 
			THEN slots * f.guestcost
			ELSE slots * membercost
		END)/3) - f.monthlymaintenance) AS months
FROM cd.bookings b
JOIN cd.facilities f ON b.facid = f.facid
GROUP BY f.facid
ORDER BY name

-- Calculate a rolling average of total revenue
SELECT date, 
	revenue FROM 
		(SELECT datetem.date AS date, 
		 	AVG(rdata.rev) OVER (ORDER BY datetem.date ROWS 14 PRECEDING) AS revenue
		FROM 
			(SELECT CAST(GENERATE_SERIES(timestamp '2012-07-15', '2012-08-31','1 day') AS date)AS date) AS datetem
		LEFT OUTER JOIN	(
			SELECT CAST(b.starttime AS date) AS date,
				SUM (
					CASE
						WHEN memid = 0 
						THEN slots * f.guestcost
						ELSE slots * membercost
					END) AS rev
			FROM cd.bookings b
			JOIN cd.facilities f ON b.facid = f.facid
			GROUP BY cast(b.starttime AS date)
		) AS rdata ON datetem.date = rdata.date
	) AS temp
WHERE date >= '2012-08-01'
ORDER BY date