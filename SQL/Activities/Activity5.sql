REM   Script: Activity4
REM   Activity4

--Alter -Activity 5
update salesman set grade =200 where SALESMAN_CITY='Rome';
update salesman set grade =300 where SALESMAN_NAME='James Hoog';
update salesman set SALESMAN_NAME ='Pierre' where SALESMAN_NAME='McLyon';
select * from salesman;