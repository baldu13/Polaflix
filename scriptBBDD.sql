USE polaflix;

#Tipos de series
INSERT INTO tipo_serie(id,nombre,precio_cap) VALUES(3,'Gold',1.50);
INSERT INTO tipo_serie(id,nombre,precio_cap) VALUES(2,'Silver',0.75);
INSERT INTO tipo_serie(id,nombre,precio_cap) VALUES(1,'Estandar',0.50);

#Series
INSERT INTO Serie(id,descripcion,nombre,tipo_id) VALUES(1,'Un asesino cabreado porque matan a su perro','John Wick',1);
INSERT INTO Serie(id,descripcion,nombre,tipo_id) VALUES(2,'Zombies, zombies y más zombies','The Walking Dead',3);
INSERT INTO Serie(id,descripcion,nombre,tipo_id) VALUES(3,'Un libro con el que se puede matar a gente escribiendo su nombre en él','Death Note',2);

#Usuarios
INSERT INTO Usuario(id,tipo,alias,cuenta_bancaria,password) VALUES (1,'CuotaFija','pepeflow','IBAN321241232','pepe23');
INSERT INTO Usuario(id,tipo,alias,cuenta_bancaria,password) VALUES (2,'Standard','gryphus','IBAN32666666','elpipero');