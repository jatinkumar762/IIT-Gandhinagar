use e_commerce;

create table customer(
	cust_id int not null AUTO_INCREMENT,
    cust_name varchar(100) not null,
    cust_email varchar(100) not null,
    cust_contact varchar(100) not null,
	CHECK(cust_email like '%___@___%'),
    PRIMARY key (cust_id)
);

INSERT INTO customer (cust_name, cust_email, cust_contact) 
values 
( 'Sohan', 'jkl@yahoo.com', 'Ahemdabad', 'Ahemdabad', 'Gujrat', '5678');

INSERT INTO customer ( cust_name, cust_email, cust_contact) 
values 
	( 'Ajay', 'ajy@yahoo.com', 'Mumbai', 'Mumbai', 'Maharashtra', '3478'),
	( 'Lavish', 'lavi@outlook.com', 'Mumbai', 'Mumbai', 'Gujrat', '2678'),
	( 'Akanksha', 'akn@outlook.com', 'Delhi', 'Delhi', 'Delhi', '3456'),
	( 'Akshita', 'akki@yahoo.com', 'Delhi', 'Delhi', 'Delhi', '4897');
    
create table cust_address(
	cust_id int not null,
    city varchar(100) not null,
    state varchar(100) not null,
    foreign key (cust_id) REFERENCES customer(cust_id) on delete cascade
);
    
insert into cust_address (cust_id, city, state)
values
(1, 'Mumbai', 'Maharashtra'),
(1, 'Pune', 'Maharashtra');

insert into cust_address (cust_id, city, state)
values
(2, 'Delhi', 'Delhi'),
(3, 'Ajmer', 'Rajasthan'),
(4, 'Jaipur', 'Rajasthan'),
(5, 'Kota', 'Rajasthan'),
(6, 'Pune', 'Maharashtra'),
(7, 'Ahemdabad', 'Gujrat');

delete from customer where customer.cust_id = 7;

select * from customer;
select * from cust_address;


create table category(
	cat_id int not null AUTO_INCREMENT,
    cat_name varchar(100) not null,
    cat_description text not null,
    primary key (cat_id)
);

insert into category (cat_name, cat_description)
values
( 'Book', 'xxx'),
( 'Electronics', 'xxx'),
( 'Furniture', 'xxx');

select * from category;

drop table retailer;

create table retailer(
	ret_id int not null auto_increment,
    ret_name varchar(100) not null,
    ret_address varchar(100) not null,
    ret_city varchar(100) not null,
    ret_state varchar(100) not null,
    ret_email varchar(100) not null,
    ret_contact varchar(100) not null,
    CHECK (ret_email like '%__@___%'),
    primary key (ret_id)
);

select * from retailer;

insert into retailer (ret_name, ret_address, ret_city, ret_state, ret_email, ret_contact)
values 
    ( 'BigWorld', 'Bangalore', 'Bangalore', 'Karnatka', 'bw @gmail.com', '1234'),
    ( 'DASM', 'Bangalore', 'Bangalore', 'Karnatka', 'da@gmail.com', '6766'),
    ( 'GKP', 'Hyderabad', 'Hyderabad', 'Telangana', 'gkp@gmail.com', '9999'),
    ( 'Lepakshi', 'Mumbai', 'Mumbai', 'Maharashtra', 'le@gmail.com', '0871'),
    ( 'Peepul', 'Mumbai', 'Mumbai', 'Maharashtra', 'pee@gmail.com', '5723'),
    ( 'luxe', 'Pune', 'Pune', 'Maharashtra', 'lu@gmail.com', '6021'),
    ( 'Viya', 'Delhi', 'Delhi', 'Delhi', 'vy@gmail.com', '3581'),
    ( 'craft', 'Ahemdabad', 'Ahemdabad', 'Gujrat', 'cr@gmail.com', '1234');
    

create table brand (
	b_id int not null auto_increment,
    b_name varchar(100) not null,
    b_des text not null,
    primary key (b_id)
);

select * from brand;

insert into brand ( b_name, b_des)
values
	('Bookends', 'Book Company'),
    ('Brisk Books', 'Book Company'),
    ('Book Barn', 'Book Company');

insert into brand ( b_name, b_des)
values
	('Apple', 'Electronic Company'),
    ('Samsung', 'Electronic Company'),
    ('Lenovo', 'Electronic Company'),
    ('Dell', 'Electronic Company'),
    ('Hooker Furniture', 'Furniture Company'),
    ('Basset', 'Furniture Company'),
    ('Stanley', 'Furniture Company'),
    ('Broyhill', 'Furniture Company');
    
create table prod_details (
	p_id int not null auto_increment,
    p_name varchar(100) not null,
    p_price int not null,
    p_type varchar(100) not null,
    p_vndr_id int not null,
    p_desc varchar(100) not null,
    p_quantity int not null,
    p_add_dt date not null,
    p_vw_count int not null,
    p_vw_date date not null,
    p_cat_id int not null,
    p_b_id int not null,
    primary key (p_id),
    foreign key (p_cat_id) references category(cat_id) on delete cascade,
    foreign key (p_b_id) references brand (b_id) on delete cascade
);

insert into prod_details (p_name, p_price, p_type, p_vndr_id, p_desc, p_quantity, p_add_dt, p_vw_count, p_vw_date, p_cat_id, p_b_id)
values
	('Opening Space', 400,'book', 7, 'xxx', 3, '2015-10-1', 5, '2019-11-1', 1, 1);
    
select * from prod_details;

insert into prod_details (p_name, p_price, p_type, p_vndr_id, p_desc, p_quantity, p_add_dt, p_vw_count, p_vw_date, p_cat_id, p_b_id)
values
	('African Folktales', 900,'book', 7, 'xxx', 3, '2015-10-1', 15, '2019-11-1', 1, 3),
    ('Unchained Voice', 1500,'book', 6, 'xxx', 1, '2020-2-1', 25, '2019-10-1', 1, 3),
    ('Love Child', 2000,'book', 8, 'xxx', 3, '2019-10-1', 2, '2019-9-1', 1, 2),
    ('Oral Epics from Africa', 600,'book', 6, 'xxx', 8, '1995-10-1', 1, '2019-8-1', 1, 2);

select * from prod_details
where TIMESTAMPDIFF(MONTH, '2000-07-09', p_add_dt)>=0;

select ret_name as 'Name', ret_email as 'Email-id' from retailer where ret_city = 'Delhi'; 

create table book(
	book_id int not null auto_increment,
    book_p_id int not null,
    book_c_id int not null default 1,
    book_author varchar(100) not null,
    book_publ_date date not null,
    primary key (book_id),
    foreign key (book_p_id) references  prod_details(p_id) on delete cascade,
    foreign key (book_c_id) references  category(cat_id) on delete cascade
);

select * from book;

insert into book (book_p_id, book_author, book_publ_date)
values
	(1, 'Franz Kafka', '2007-01-01');
    
insert into book (book_p_id, book_author, book_publ_date)
values
	(2, 'Franz Kafka', '2017-11-01'),
    (3, 'Jaya Dev', '2018-11-01'),
    (4, 'Jayaprakash Narayan', '1997-11-01'),
    (5, 'G. B. Shaw', '2000-11-01');
 
 
select p_name as 'Book Name', book_publ_date as 'Published Date'
from prod_details, book 
where  prod_details.p_type = 'book' and  prod_details.p_id = book.book_p_id and TIMESTAMPDIFF(MONTH, '2001-01-01', book_publ_date)>=0;

select p_name as 'Book Name'
from prod_details, book 
where  prod_details.p_type = 'book' and  prod_details.p_id = book.book_p_id and book_author='Franz Kafka';

insert into prod_details (p_name, p_price, p_type, p_vndr_id, p_desc, p_quantity, p_add_dt, p_vw_count, p_vw_date, p_cat_id, p_b_id)
values
	('Wodden Side Table', 5000, 'furniture', 9, 'xxx', 2, '2015-09-01', 4, '2019-11-11', 3, 8),
    ('Shelves Shoe Cabinet', 10000, 'furniture', 9, 'xxx', 3, '2016-01-01', 6, '2019-12-12', 3, 9),
    ('Zigzag Corner Wall', 6000, 'furniture', 3, 'xxx', 1, '2017-12-12', 15, '2019-09-16', 3, 10),
    ('Foldeable Stool', 1000, 'furniture', 9, 'xxx', 2, '2018-01-19', 21, '2019-11-11', 3, 10),
    ('Bedside Tables', 2000, 'furniture', 3, 'xxx', 2, '2018-09-15', 11, '2019-11-06', 3, 11),
    ('Ward Robes', 8500, 'furniture', 4, 'xxx', 6, '2019-09-15', 9, '2019-12-06', 3, 11),
    ('Wash Stands', 6999, 'furniture', 4, 'xxx', 1, '2020-01-01', 23, '2019-10-05', 3, 8);

select p_name as 'Furniture Name'
from prod_details, retailer
where prod_details.p_cat_id = 3 and prod_details.p_vndr_id = retailer.ret_id and retailer.ret_name = 'IKea';

insert into prod_details (p_name, p_price, p_type, p_vndr_id, p_desc, p_quantity, p_add_dt, p_vw_count, p_vw_date, p_cat_id, p_b_id)
values
	('Apple iphone-x', 100000, 'Mobile', 1, 'xxx', 10, '2019-10-01', 5, '2019-11-11', 2, 4),
    ('Apple Tablet 7gen', 60000, 'Tablet', 1, 'xxx', 5, '2019-11-11', 9, '2019-12-01', 2, 4),
    ('Apple Tablet 6gen', 30000, 'Tablet', 2, 'xxx', 6, '2019-12-01', 110, '2020-01-01', 2, 4),
    ('Apple Laptop', 150000, 'Laptop', 2, 'xxx', 3, '2019-12-05', 150, '2020-02-02', 2, 4),
    ('Xioami Redmi', 15000, 'Mobile', 5, 'xxx', 4, '2020-01-01', 200, '2019-11-30', 2, 12),
    ('Xioami Speaker', 11000, 'Speaker', 6, 'xxx', 3, '2020-02-05', 6, '2020-01-01', 2, 12),
    ('Lenvo Laptop', 50000, 'Laptop', 5, 'xxx', 5, '2020-02-01', 80, '2020-01-30', 2, 6),
    ('Dell Laptop', 75000, 'Laptop', 1, 'xxx', 1, '2019-10-01', 9, '2020-02-05', 2, 7);
    
select p_name as 'Product Name', p_price as 'Product Price'
from prod_details
where p_cat_id = 2 and p_price between 10000 and 50000;

select p_name as 'Product Name', p_price as 'Product Price'
from prod_details
where p_cat_id = 2 and p_type = 'Laptop' 
order by p_price asc;

select p_name as 'Product Name', p_price as 'Product Price', b_name as 'Brand Name'
from prod_details, brand
where prod_details.p_b_id = brand.b_id and (p_name like '%Apple%' or p_name like '%Xioami%');
    
select p_name as 'Product Name', p_price*1.5 as 'Product Price', TIMESTAMPDIFF(MONTH, p_vw_date, CURDATE()) as 'Months Difference'
from prod_details
where p_vw_count < 10 and p_price > 5000 and TIMESTAMPDIFF(MONTH, p_vw_date, CURDATE())<3;
    
UPDATE prod_details 
SET 
    p_price = (p_price * 0.9)
WHERE
    p_vw_count < 10 AND p_price > 5000 AND TIMESTAMPDIFF(MONTH, p_vw_date, CURDATE()) < 3;

drop table if exists review;
	
create table review (
	rid int not null auto_increment,
    pid int not null,
    cid int default -1,
    cname varchar(60) not null default 'Anonymous',
    rating int not null,
    review text not null,
    primary key (rid),
    foreign key (pid) references prod_details(p_id) on delete cascade,
    foreign key (cid) references customer(cust_id) on delete set null
);

select * from review;

insert into review (pid, cid, cname, rating, review)
values
	(13,6,'Akanksha',1,'Bad'),
    (13,5,'Lavish',3,'Average'),
    (12,1,'Rakesh',4,'Good'),
    (10,3,'Sohan',5,'Excellent'),
    (16,5,'Lavish',5,'Excellent'),
    (8,6,'Akanksha',1,'Good');

delete from customer where cust_id = 6;    
    
update review
set
    cname = 'Anonymous'
where cid is null and cname is not null;

select * from review; 

select p_name as 'Product Name', p_price as 'Product Price', timestampdiff(day,p_add_dt,CURDATE()) as 'Added days before'
from prod_details
where timestampdiff(day,p_add_dt,CURDATE())<=10;

drop table if exists Diwali_Deals;

create table Diwali_Deals (
	p_id int not null auto_increment,
    p_name varchar(100) not null,
    p_price int not null,
    p_type varchar(100) not null,
    p_vndr_id int not null,
    p_desc varchar(100) not null,
    p_quantity int not null,
    p_add_dt date not null,
    p_vw_count int not null,
    p_vw_date date not null,
    p_cat_id int not null,
    p_b_id int not null,
    primary key (p_id),
    foreign key (p_cat_id) references category(cat_id) on delete cascade,
    foreign key (p_b_id) references brand (b_id) on delete cascade
);

insert into Diwali_Deals (p_name, p_price, p_type, p_vndr_id, p_desc, p_quantity, p_add_dt, p_vw_count, p_vw_date, p_cat_id, p_b_id)
select p_name, (p_price*.95), p_type, p_vndr_id, p_desc, p_quantity, p_add_dt, p_vw_count, p_vw_date, p_cat_id, p_b_id
from prod_details 
where timestampdiff(day,p_add_dt,CURDATE())<=90;
  
select * from Diwali_Deals;

drop table if exists carts;

create table carts (
	cart_id int not null auto_increment,
    cart_cust_id int not null,
    cart_add_date date not null,
    primary key (cart_id),
	foreign key (cart_cust_id) references customer (cust_id) on delete cascade
);

drop table if exists cart_details;
create table cart_details(
	cart_id int not null,
    cart_prd_id int not null,
    cart_prd_qunt int not null,
    foreign key (cart_id) references carts (cart_id) on delete cascade,
	foreign key (cart_prd_id) references prod_details (p_id) on delete cascade
);

insert into carts ( cart_cust_id, cart_add_date)
values
	(2, '2020-01-10'),
    (4, '2020-02-1'),
    (5, '2020-01-30');

insert into cart_details (cart_id, cart_prd_id, cart_prd_qunt)
values
	 (1,19,1),
	 (1,11,1),
	 (2,18,2),
	 (2,10,1),
	 (3,16,1);
     
select cust_name as 'Name', cust_email as 'Email-id', sum(cart_prd_qunt) as 'Quantity'
from customer, carts natural join cart_details
where customer.cust_id = carts.cart_cust_id
group by cust_id
having sum(cart_prd_qunt)<3;

select p_name as 'Product Name', p_price as 'Product Price', cart_prd_qunt as 'Quantity'
from prod_details,  carts natural join cart_details
where cart_cust_id = 2 and cart_prd_id = p_id;


drop table if exists orders;
create table orders (
	ord_id int not null auto_increment,
    ord_cust_id int not null,
    ord_add_date date not null,
    primary key (ord_id),
	foreign key (ord_cust_id) references customer (cust_id) on delete cascade
);

drop table if exists ord_details;

create table ord_details(
	ord_id int not null,
    ord_prd_id int not null,
    ord_prd_qunt int not null,
    foreign key (ord_id) references orders (ord_id) on delete cascade,
	foreign key (ord_prd_id) references prod_details (p_id) on delete cascade
);
   
insert into orders (ord_cust_id, ord_add_date)
values
	(2, '2020-01-10'),
    (2, '2020-02-10'),
    (1, '2020-01-10'),
    (1, '2020-02-10'),
    (1, '2020-01-19'),
    (1, '2019-12-31'),
    (5, '2020-01-30');

select * from orders;

insert into ord_details (ord_id, ord_prd_id, ord_prd_qunt)
values
	 (1,19,1),
	 (1,11,1),
	 (2,18,2),
	 (2,10,1),
	 (3,16,1),
     (4,5,1),
     (5,6,2),
     (6,1,1),
     (7,8,2),
     (7,9,1);

select p_name as 'Product Name', p_price as 'Product Price', p_quantity as 'Product Quantity' 
from prod_details
where p_id in (
		select ord_prd_id
        from ord_details as D
        natural join (
			select *
			from orders 
			where ord_cust_id = 1
			order by ord_add_date desc
            limit 3
        ) as O
);

select ord_id, MAX(ProductOrdered) as 'Max Ordered'
from (
		select ord_id, SUM(ord_prd_qunt) as 'ProductOrdered'
		from orders natural join ord_details
		group by ord_id 
    ) as bests;

select ret_name, ret_email
from retailer
where ret_id in 
	(select p_vndr_id 
	from orders natural join ord_details, prod_details
	where ord_cust_id = 1 and ord_prd_id = p_id);

select cust_name as 'Name', sum(TotalPurchase) as 'Total Amount' from customer natural join cust_address,
	(select ord_cust_id, sum(ord_prd_qunt*p_price) as 'TotalPurchase'
	from orders natural join ord_details, prod_details
	where ord_prd_id = p_id
	group by ord_id
	having sum(ord_prd_qunt*p_price) >= 5000 ) as P
where customer.cust_id = p.ord_cust_id and city = 'Mumbai'
group by cust_id;
 
insert into review (pid, cid, cname, rating, review)
values
    (5,1,'Rakesh',3,'Average'),
    (6,1,'Rakesh',4,'Good'),
    (1,1,'Rakesh',5,'Excellent');
    
    
    
    
select p_name as 'Product Name' , p_price as 'Product Price'
from orders natural join ord_details, prod_details, review
where ord_prd_id = p_id and ord_cust_id = 1 and p_id = review.pid and rating >=3
limit 10



    



