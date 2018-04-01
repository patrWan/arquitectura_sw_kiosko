dROP DATABASE bd_kiosko;
 
crear la base de datos bd_kiosko;
 
use bd_kiosko;
 
crear tabla tipo_producto (
id int not null auto_increment,
descripcion varchar (150),
clave principal (id)
);
 
crear tabla de producto (
id int not null auto_increment,
id_tipo int,
nombre varchar (150),
precio int,
stock int,
clave principal (id),
clave foránea (id_tipo) hace referencia a tipo_producto (id)
);
 
crear venta de mesa
id int not null auto_increment,
total int,
clave principal (id)
);
 
crear tabla boleta (
id int not null auto_increment,
id_producto int,
id_venta int,
cantidad int,
subtotal int,
fecha datetime,
clave principal (id),
clave externa (id_producto) hace referencia al producto (id),
clave foránea (id_venta) referencias venta (id)
);
 
 
SELECCIONE la ID FROM venta ORDEN POR ID DESC LIMIT 1;
SELECCIONE tipo_producto.descripcion 'Tipo Prodcuto', producto.nombre, producto.precio, producto.stock
DESDE tipo_producto, producto
DONDE tipo_producto.id = producto.id_tipo;
 
SELECCIONAR nombre, precio, stock FROM producto WHERE nombre LIKE '% c%';
 
ELIMINAR DE producto WHERE id = '1';
 
ACTUALIZACIÓN producto SET stock = stock - 2 WHERE id = 2;
 
SELECCIONE stock FROM producto WHERE id = 1;
 
seleccione * del producto;
seleccione * de tipo_producto;
seleccione * de venta;
seleccione * de boleta;

