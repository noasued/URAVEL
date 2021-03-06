DROP TABLE REVIEW;
DROP SEQUENCE POSTNOSEQ;

CREATE SEQUENCE POSTNOSEQ NOCACHE;

CREATE TABLE REVIEW(
	POSTNO NUMBER NOT NULL,
	USERNO NUMBER,
	POSTDATE DATE NOT NULL,
	TRAVELNO NUMBER,
	TITLE VARCHAR2(1000) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	CONSTRAINT REVIEW_PK PRIMARY KEY(POSTNO)
    );
    
ALTER TABLE REVIEW ADD (FILENAME VARCHAR2(200));
ALTER TABLE REVIEW ADD (FILESIZE NUMBER);
ALTER TABLE REVIEW ADD (LOCALNAME VARCHAR2(20));
ALTER TABLE REVIEW ADD (THEMENAME VARCHAR2(20));

ALTER TABLE REVIEW ADD CONSTRAINT REVIEW_FK FOREIGN KEY(USERNO) REFERENCES MEMBER(USERNO) ON DELETE CASCADE;
ALTER TABLE REVIEW ADD CONSTRAINT REVIEW_FK1 FOREIGN KEY(TRAVELNO) REFERENCES TRAVEL(TRAVELNO) ON DELETE CASCADE;

ALTER TABLE REVIEW ADD CONSTRAINT REVIEW_FK2 FOREIGN KEY(LOCALNAME) REFERENCES LOCATION(LOCALNAME);
ALTER TABLE REVIEW ADD CONSTRAINT REVIEW_FK3 FOREIGN KEY(THEMENAME) REFERENCES THEME(THEMENAME);


SELECT * FROM REVIEW ORDER BY POSTNO;
