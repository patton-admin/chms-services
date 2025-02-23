insert into USER_TBL (USER_FIRSTNAME, USER_ID,USER_LASTNAME,USER_PASSWORD,USER_PHONE_PRIMARY,created_date,last_updated_date) 
VALUES ('Jay','jay123', 'jay', 'jay123', '101010',sysdate(),sysdate());
insert into USER_TBL ( USER_FIRSTNAME, USER_ID,USER_LASTNAME,USER_PASSWORD,USER_PHONE_PRIMARY,created_date,last_updated_date) 
VALUES ('hay','hay123', 'hay', 'hay123', '101010',sysdate(),sysdate());    
insert into USER_TBL ( USER_FIRSTNAME, USER_ID,USER_LASTNAME,USER_PASSWORD,USER_PHONE_PRIMARY,created_date,last_updated_date)
 VALUES ('bay','bay123', 'bay', 'bay123', '101010',sysdate(),sysdate());    
insert into USER_TBL ( USER_FIRSTNAME, USER_ID,USER_LASTNAME,USER_PASSWORD,USER_PHONE_PRIMARY,created_date,last_updated_date) 
VALUES ('eay','eay123', 'eay', 'eay123', '101010',sysdate(),sysdate());   
insert into USER_TBL ( USER_FIRSTNAME, USER_ID,USER_LASTNAME,USER_PASSWORD,USER_PHONE_PRIMARY,created_date,last_updated_date) 
VALUES ('may','may123', 'may', 'may123', '101010',sysdate(),sysdate());        


insert into Bucket_tbl (BUCKET_ID, BUCKET_LONGDESCRIPTION, BUCKET_SHORTDESCRIPTION, BUCKET_NAME, BUCKET_OWNER, CREATED_DATE, LAST_UPDATED_DATE)
VALUES (2001, 'Jays Bucket', 'New Bucke1', 'JAY-BUCKET', 'Jayaraman', sysdate(), sysdate());
insert into Bucket_tbl (BUCKET_ID, BUCKET_LONGDESCRIPTION, BUCKET_SHORTDESCRIPTION, BUCKET_NAME, BUCKET_OWNER, CREATED_DATE, LAST_UPDATED_DATE)
VALUES (2002, 'Mays Bucket', 'New Bucket2', 'MAY-BUCKET', 'Mayaraman', sysdate(), sysdate());
insert into Bucket_tbl (BUCKET_ID, BUCKET_LONGDESCRIPTION, BUCKET_SHORTDESCRIPTION, BUCKET_NAME, BUCKET_OWNER, CREATED_DATE, LAST_UPDATED_DATE)
VALUES (2003, 'Fays Bucket', 'New Bucket3', 'FAY-BUCKET', 'Fayaraman', sysdate(), sysdate());
insert into Bucket_tbl (BUCKET_ID, BUCKET_LONGDESCRIPTION, BUCKET_SHORTDESCRIPTION, BUCKET_NAME, BUCKET_OWNER, CREATED_DATE, LAST_UPDATED_DATE)
VALUES (2004, 'Hays Bucket', 'New Bucket4', 'HAY-BUCKET', 'Hayaraman', sysdate(), sysdate());
insert into Bucket_tbl (BUCKET_ID, BUCKET_LONGDESCRIPTION, BUCKET_SHORTDESCRIPTION, BUCKET_NAME, BUCKET_OWNER, CREATED_DATE, LAST_UPDATED_DATE)
VALUES (2005, 'Pays Bucket', 'New Bucket5', 'PAY-BUCKET', 'Payaraman', sysdate(), sysdate());


insert into LEAD_TBL (ID, LEAD_ID, created_date, last_updated_date, LEAD_ADDRESS, LEAD_FIRSTNAME, LEAD_LASTNAME, LEAD_PHONENO, LEAD_EMAIL_PRIMARY,
LEAD_EMAIL_SECONDARY,BUCKET_BUCKET_ID) VALUES (3001, 'Manya@123',sysdate(), sysdate(), 'ManyaDun', 'Manya', 'lst', '1211', 'manya@gmail.com','mandyala',2001);
insert into LEAD_TBL (ID, LEAD_ID, created_date, last_updated_date, LEAD_ADDRESS, LEAD_FIRSTNAME, LEAD_LASTNAME, LEAD_PHONENO, LEAD_EMAIL_PRIMARY,
LEAD_EMAIL_SECONDARY,BUCKET_BUCKET_ID) VALUES (3002, 'Danya@123',sysdate(), sysdate(), 'DanyaDun', 'Danya', 'lst', '1211', 'danya@gmail.com','dandyala',2001);
insert into LEAD_TBL (ID, LEAD_ID, created_date, last_updated_date, LEAD_ADDRESS, LEAD_FIRSTNAME, LEAD_LASTNAME, LEAD_PHONENO, LEAD_EMAIL_PRIMARY,
LEAD_EMAIL_SECONDARY,BUCKET_BUCKET_ID) VALUES (3003, 'Hanya@123',sysdate(), sysdate(), 'HanyaDun', 'Hanya', 'lst', '1211', 'hanya@gmail.com','handyala',2003);
insert into LEAD_TBL (ID, LEAD_ID, created_date, last_updated_date, LEAD_ADDRESS, LEAD_FIRSTNAME, LEAD_LASTNAME, LEAD_PHONENO, LEAD_EMAIL_PRIMARY,
LEAD_EMAIL_SECONDARY,BUCKET_BUCKET_ID) VALUES (3004, 'Janya@123',sysdate(), sysdate(), 'JanyaDun', 'Janya', 'lst', '1211', 'janya@gmail.com','jandyala',2003);
insert into LEAD_TBL (ID, LEAD_ID, created_date, last_updated_date, LEAD_ADDRESS, LEAD_FIRSTNAME, LEAD_LASTNAME, LEAD_PHONENO, LEAD_EMAIL_PRIMARY,
LEAD_EMAIL_SECONDARY,BUCKET_BUCKET_ID) VALUES (3005, 'Panya@123',sysdate(), sysdate(), 'PanyaDun', 'Panya', 'lst', '1211', 'panya@gmail.com','pandyala',2004);


