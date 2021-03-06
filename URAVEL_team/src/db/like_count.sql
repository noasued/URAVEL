DROP TABLE LIKE_COUNT;

CREATE TABLE LIKE_COUNT(
	USERNO NUMBER,
	TRAVELNO NUMBER
);

ALTER TABLE LIKE_COUNT 
ADD FOREIGN KEY (USERNO) 
REFERENCES MEMBER (USERNO)
ON DELETE CASCADE;

ALTER TABLE LIKE_COUNT 
ADD FOREIGN KEY (TRAVELNO) 
REFERENCES TRAVEL (TRAVELNO)
ON DELETE CASCADE;

SELECT * FROM LIKE_COUNT;

ALTER TABLE LIKE_COUNT DROP COLUMN LIKE_COUNT;
