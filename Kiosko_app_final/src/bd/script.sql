DROP DATABASE bd_kiosko;

create database bd_kiosko;

use bd_kiosko;

create table tipo_producto(
id int not null auto_increment,
descripcion varchar(150),
primary key (id)
);

create table producto(
id int not null auto_increment,
id_tipo int,
nombre varchar(150),
precio int,
stock int,
primary key (id),
foreign key (id_tipo) references tipo_producto(id)
);

create table venta(
id int not null auto_increment,
total int,
primary key (id)
);

create table boleta(
id int not null auto_increment,
id_producto int,
id_venta int,
cantidad int,
subtotal int,
fecha datetime,
primary key(id),
foreign key (id_producto) references producto(id),
foreign key (id_venta)references venta(id)
);


SELECT id FROM venta ORDER BY id DESC LIMIT 1;
SELECT tipo_producto.descripcion 'Tipo Prodcuto', producto.nombre, producto.precio, producto.stock 
FROM tipo_producto, producto
WHERE tipo_producto.id = producto.id_tipo;

SELECT nombre,precio, stock FROM producto WHERE nombre LIKE '%c%';

DELETE FROM producto WHERE id = '1';

UPDATE producto SET stock = stock - 2 WHERE id = 2;

SELECT stock FROM producto WHERE id = 1;

select * from producto;
select * from tipo_producto;
select * from venta;
select * from boleta;