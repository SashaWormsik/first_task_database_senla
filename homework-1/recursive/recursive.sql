-- 
--Recursive Queries 
--

--Find the upward recommendation chain for member ID 27
WITH RECURSIVE recommenders(recommender) AS 
    (SELECT recommendedby 
	FROM cd.members
	WHERE memid = 27
	UNION ALL
	SELECT m.recommendedby 
	FROM recommenders r
	JOIN cd.members m ON m.memid = r.recommender)
SELECT r.recommender, 
	m.firstname, 
	m.surname 
FROM recommenders r
JOIN cd.members m ON r.recommender = m.memid
ORDER BY memid DESC 

-- Find the downward recommendation chain for member ID 1
WITH RECURSIVE recommendeds(memid) AS 
    (SELECT memid 
	FROM cd.members 
	WHERE recommendedby = 1
	UNION ALL
	SELECT m.memid 
	FROM recommendeds r
	JOIN cd.members m ON r.memid = m.recommendedby)
SELECT r.memid, 
	m.firstname, 
	m.surname 
FROM recommendeds r
JOIN cd.members m ON r.memid = m.memid
ORDER BY memid

-- Produce a CTE that can return the upward recommendation chain for any member
WITH RECURSIVE recommenders(recommender, member) AS (
	SELECT recommendedby, memid 
	FROM cd.members
	UNION ALL
	SELECT m.recommendedby, r.member 
	FROM recommenders r
	JOIN cd.members m ON m.memid = r.recommender
)
SELECT r.member AS member, 
	r.recommender, 
	m.firstname, 
	m.surname FROM recommenders r
JOIN cd.members m ON r.recommender = m.memid
WHERE r.member = 22 OR r.member = 12
ORDER BY r.member ASC, 
		r.recommender DESC  