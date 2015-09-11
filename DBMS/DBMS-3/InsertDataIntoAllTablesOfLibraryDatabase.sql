-- Query to use library database
USE Library;
-- Query to insert data into Members table
INSERT INTO Members VALUES ( 1001, 'raju','sector-8','sector-10','m','Faculty'),
                        ( 1002, 'rani','malviyanager','sitapura','f','Student'),
                       ( 1003, 'ram','jagatpura','mansroavar','m','Student'),
                        ( 1004, 'rajshree','india gate','goner','f','Faculty'),
                        ( 1005, 'rajshree','india gate','goner','f','Student'),
                        ( 1006, 'rajshree','india gate','goner','f','Other');
 -- Query to display data of Members table                      
select* from Members;                     
-- Query to insert data into Publishers table                    
INSERT INTO Publishers VALUES (501,'Tata'),
                               (502,'New world'),
							   (503,'big rock');
-- Query to display data of Publishers table       
select* from Publishers;
-- Query to insert data into Subjects table
INSERT INTO Subjects VALUES (51,'arts'),
                             (52,'science'),
							 (53,'agriculture');
-- Query to display data of Subjects table       
select* from Subjects;
-- Query to insert data into Title table
INSERT INTO Title VALUES (201,'love of Arts', 51,501),
                         (202,'science world', 51,501),
                        (203,'agriculture for you', 52,502),
                         (204,'agriculture for you', 52,502);
-- Query to display data of Title table       
select* from Title;
-- Query to insert data into Books table
INSERT INTO Books VALUES (101,201, '2013-01-01',500,'available'),
                           (102,202, '2014-01-01',600,'available'),
						             (103,201, '2015-01-01',700,'available'),
                            (104,204, '2015-01-01',700,'available');	
-- Query to display data of Books table       
select* from Books;
-- Query to insert data into Books_Issue table
INSERT INTO Books_Issue (accessionNo,memberId) VALUES (101,1001),
                                                    ( 102,1001),
							                                      ( 101,1003);
-- Query to insert data into Books_Issue table
INSERT INTO Books_Issue VALUES ('2013-01-01',101,1001,'2013-11-16'),('2013-01-02',102,1001,'2013-09-16');
-- Query to display data of Books_Issue table       
select* from Books_Issue;
-- Query to insert data into Books_Return table
INSERT INTO Books_Return VALUES (curdate(),101,1001,DATE_SUB(curdate(),INTERVAL 15 day)),
                                  (curdate(),102,1001,DATE_SUB(curdate(),INTERVAL 15 day)),
								  (curdate(),101,1003,DATE_SUB(curdate(),INTERVAL 15 day));
-- Query to display data of Books_Return table       
select* from Books_Return;
-- Query to insert data into Authors table
INSERT INTO Authors VALUES ( 1, 'pooja'),
                           ( 2, 'manu'),
                           ( 3, 'dheeraj');
-- Query to display data of Authors table       
select* from Authors;
-- Query to insert data into Title_Author table
INSERT INTO Title_Author VALUES (201,1),
                                 (202,1),
								                 (201,3);
-- Query to display data of Title_Author table       			
select* from Title_Author;






























