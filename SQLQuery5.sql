Create Database ECommerce
-------------------------------------------------------
Create Table Customer (
	id int primary key IDENTITY(1,1),
	firstName VARCHAR(255) not null, 
	lastName VARCHAR(255) not null, 
	email VARCHAR(255) not null unique,
	address VARCHAR(255) not null,
	phoneNumber VARCHAR(255) not null unique,
)

Insert Into Customer (firstName, lastName, email, address, phoneNumber) 
Values ('Saif', 'Alkurdi', 's.kurdi@outlook.sa', 'Tlaá Ali', '0796055236')

Select * From Customer

-------------------------------------------------------
Create Table Category (
	id int primary key Identity,
	name VARCHAR(255) not null unique
)

Insert into Category values ('phone')
Insert into Category values ('labtop')
Insert into Category values ('headphones')

Select * From Category

-------------------------------------------------------
Create Table Product (
	id int primary key Identity,
	name VARCHAR(255) not null, 
	SKU VARCHAR(255) not null unique, 
	description VARCHAR(255), 
	price float not null,
	stock int not null,
	categoryId int not null,
	Foreign key (categoryId) References Category(id)
)

Insert into Product Values ('iPhone 8', 'SDFS578S57', 'This phone was released in 2019', 781, 2570 , 1)

SELECT * FROM 
Product INNER JOIN Category ON Product.id = Product.categoryId

Select * From 
Product INNER JOIN Category ON Product.id = Product.categoryId
Where Product.categoryId = 1

-------------------------------------------------------
Create Table Cart (
	id int primary key Identity,
	quantity int,
	customerID int,
	productID int,
	Foreign key (customerID) References Customer(id),
	Foreign key (productID) References Product(id)
)

Insert into Cart Values (2, 2, 1)
Insert into Cart Values (5, 2, 1)

Select * From
Cart Inner join Customer On Cart.customerID = Customer.id
Inner join Product On Cart.productID = Product.id

-------------------------------------------------------

Create Table Wishlist (
	id int primary key Identity,
	customerID int,
	productID int,
	Foreign key (customerID) References Customer(id),
	Foreign key (productID) References Product(id)
)

insert into Wishlist values (2, 1)


Select * From
wishlist Inner join Customer On Wishlist.customerID = Customer.id
Inner join Product On Wishlist.productID = Product.id

-------------------------------------------------------

Create Table OrderDetails (
	id int primary key identity,
	total decimal not null,
	customerId int, 
	Foreign key (customerId) References Customer(id)
)

Insert Into OrderDetails Values (751, 2)

-------------------------------------------------------

Create Table OrderItems (
	id int primary key identity,
	quantity int not null,
	prodcutId int, 
	orderDetailsId int,
	Foreign key (prodcutId) References Product(id),
	Foreign key (orderDetailsId) References OrderDetails(id)
)


-------------------------------------------------------

Create Table Invoice (
	id int primary key identity,
	orderDetailsId int,
	Foreign key (orderDetailsId) References OrderDetails(id)
)


-------------------------------------------------------

SELECT *
FROM 
Customer INNER JOIN Cart ON Customer.id = Cart.customerID
INNER JOIN Product ON Cart.productID = Product.id
INNER JOIN Category ON Product.categoryId = Category.id
Inner Join Wishlist On Wishlist.customerID = Customer.id

Select * From Customer
Select * From Product
Select * From Cart
Select * From Category
Select * From Wishlist
Select * From OrderDetails
Select * From OrderItems
Select * From Invoice

-------------------------------------------------------
Drop table Customer
Drop table Product
Drop table Cart
Drop table Category 
Drop table Wishlist
Drop table OrderDetails
Drop table OrderItems
Drop table Invoice

