Create database EmployeeManagement;
Use EmployeeManagement;
create table employee(
name varchar(100),
dateOfBirth varchar(100),
email varchar(100),
address varchar(100)
);
 
 Insert Into employee Values('Pooja','25-07-1993','pooja@gmail.com','sirohi');
 Insert Into employee Values('Kiran','11-09-1993','kiran@gmail.com','sirohi');
  Insert Into employee Values('Ashvini','11-10-1993','ashvini@gmail.com','sirohi');
  Insert Into employee Values('Hema','05-10-1993','hema@gmail.com','sirohi');  
  
  Select * From employee where name='Pooja';