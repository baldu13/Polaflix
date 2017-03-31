USE polaflix;

#Tipos de series
INSERT INTO tipo_serie(id,nombre,precio_cap) VALUES(3,'Gold',1.50);
INSERT INTO tipo_serie(id,nombre,precio_cap) VALUES(2,'Silver',0.75);
INSERT INTO tipo_serie(id,nombre,precio_cap) VALUES(1,'Estandar',0.50);

#Series
INSERT INTO Serie(id,descripcion,nombre,tipo_id) VALUES(1,'Profesor de química de un instituto con cancer que se pasa a hacer metanfetamina azul con un ex-estudiante suyo','Breaking Bad',1);
INSERT INTO Serie(id,descripcion,nombre,tipo_id) VALUES(2,'Zombies, zombies y más zombies','The Walking Dead',3);
INSERT INTO Serie(id,descripcion,nombre,tipo_id) VALUES(3,'Un cuaderno con el que se puede matar a gente escribiendo su nombre en él','Death Note',2);
INSERT INTO Serie(id,descripcion,nombre,tipo_id) VALUES(4,'Aventuras de un niño con su abuelo científico, muy extraño todo','Rick & Morty',1);
INSERT INTO Serie(id,descripcion,nombre,tipo_id) VALUES(5,'Conflictos de drogas visto desde el lado de traficantes y fuerzas del orden','The Wire: Bajo Escucha',3);

### Breaking Bad ###
#Temporadas
INSERT INTO Temporada(id,num_temp) VALUES(1,1);
INSERT INTO serie_temporadas(serie_id,temporadas_id) VALUES (1,1);
INSERT INTO Temporada(id,num_temp) VALUES(2,2);
INSERT INTO serie_temporadas(serie_id,temporadas_id) VALUES (1,2);
INSERT INTO Temporada(id,num_temp) VALUES(3,3);
INSERT INTO serie_temporadas(serie_id,temporadas_id) VALUES (1,3);
INSERT INTO Temporada(id,num_temp) VALUES(4,4);
INSERT INTO serie_temporadas(serie_id,temporadas_id) VALUES (1,4);
INSERT INTO Temporada(id,num_temp) VALUES(5,5);
INSERT INTO serie_temporadas(serie_id,temporadas_id) VALUES (1,5);
#Capitulos
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (1,'Primer episodio de la Primera temporada de la serie de televisión dramática Breaking Bad.',1,'Piloto');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (1,1);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (2,'Segundo episodio de la Primera temporada de la serie de televisión dramática Breaking Bad.',2,'El gato está en la bolsa...');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (1,2);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (3,'Tercer episodio de la Primera temporada de la serie de televisión dramática Breaking Bad.',3,'...y la bolsa en el río');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (1,3);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (4,'Cuarto episodio de la primera temporada de la serie de televisión dramática Breaking Bad.',4,'El hombre del cáncer');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (1,4);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (5,'Quinto episodio de la primera temporada de la serie de televisión dramática Breaking Bad.',5,'Materia gris');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (1,5);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (6,'Sexto episodio de la primera temporada de la serie de televisión dramática Breaking Bad.',6,'Loco puñado de nada');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (1,6);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (7,'Séptimo episodio y el final de la primera temporada de la serie de televisión dramática Breaking Bad.',7,'Un tratado de no violencia');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (1,7);

INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (8,'Primer episodio de la segunda temporada y octavo en general de la serie de televisión dramática Breaking Bad.',1,'Siete treinta y siete');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (2,8);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (9,'Segundo episodio de la segunda temporada de la serie de televisión dramática Breaking Bad.',2,'A la parrilla');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (2,9);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (10,'Tercer episodio de la segunda temporada de la serie de televisión dramática Breaking Bad.',3,'Picado por una abeja muerta');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (2,10);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (11,'Cuarto episodio de la segunda temporada de la serie de televisión dramática Breaking Bad.',4,'Cae');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (2,11);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (12,'Quinto episodio de la segunda temporada de la serie de televisión dramática Breaking Bad.',5,'Fractura');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (2,12);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (13,'Sexto episodio de la segunda temporada de la serie de televisión dramática Breaking Bad.',6,'Peekaboo');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (2,13);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (14,'Septimo episodio de la segunda temporada de la serie de televisión dramática Breaking Bad.',7,'Negro y azul');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (2,14);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (15,'Octavo episodio de la segunda temporada de la serie de televisión dramática Breaking Bad.',8,'Better Call Saul');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (2,15);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (16,'Noveno episodio de la segunda temporada de la serie de televisión dramática Breaking Bad.',9,'Cuatro días fuera');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (2,16);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (17,'Décimo episodio de la segunda temporada de la serie de televisión dramática Breaking Bad.',10,'Sobre');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (2,17);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (18,'Undécimo episodio de la segunda temporada de la serie de televisión dramática Breaking Bad.',11,'Mandala');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (2,18);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (19,'Duodécimo episodio de la segunda temporada de la serie de televisión dramática Breaking Bad.',12,'Fénix');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (2,19);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (20,'Treceavo episodio de la segunda temporadal de la serie de televisión dramática Breaking Bad.',13,'ABQ');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (2,20);

INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (21,'Primer episodio de la tercera temporada de la serie de televisión dramática Breaking Bad.',1,'No más');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (3,21);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (22,'Segundo episodio de la tercera temporada de la serie de televisión dramática Breaking Bad.',2,'No más');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (3,22);
#Actores y creadores
INSERT INTO Artista(id,nombre,apellido,tipo) VALUES(1,'Vince','Gilligan','creador');
INSERT INTO serie_creadores(serie_id,creador_id) VALUES(1,1);
INSERT INTO Artista(id,nombre,apellido,tipo) VALUES(2,'Bryan','Cranston','actor');
INSERT INTO serie_actores(serie_id,actor_id) VALUES(1,2);
INSERT INTO Artista(id,nombre,apellido,tipo) VALUES(3,'Aaron','Paul','actor');
INSERT INTO serie_actores(serie_id,actor_id) VALUES(1,3);


### The Walking Dead ###
#Temporadas
INSERT INTO Temporada(id,num_temp) VALUES(6,1);
INSERT INTO serie_temporadas(serie_id,temporadas_id) VALUES (2,6);
INSERT INTO Temporada(id,num_temp) VALUES(7,2);
INSERT INTO serie_temporadas(serie_id,temporadas_id) VALUES (2,7);
#Capitulos
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (23,'Primer episodio de la Primera temporada de la serie de televisión dramática The Walking Dead.',1,'Los días transcurridos');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (6,23);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (24,'Segundo episodio de la Primera temporada de la serie de televisión dramática The Walking Dead.',2,'Tripas');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (6,24);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (25,'Tercer episodio de la Primera temporada de la serie de televisión dramática The Walking Dead.',3,'Díselo a las ranas');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (6,25);

INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (26,'Primer episodio de la Segunda temporada de la serie de televisión dramática The Walking Dead.',1,'Lo que queda por delante');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (7,26);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (27,'Segundo episodio de la Segunda temporada de la serie de televisión dramática The Walking Dead.',2,'Sangría');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (7,27);
INSERT INTO Capitulo(id,descripcion,num_cap,titulo) VALUES (28,'Tercer episodio de la Segunda temporada de la serie de televisión dramática The Walking Dead.',3,'Guarda la última');
INSERT INTO temporada_capitulos(temporada_id,capitulos_id) VALUES (7,28);
#Actores y creadores
INSERT INTO Artista(id,nombre,apellido,tipo) VALUES(4,'Frank','Darabont','creador');
INSERT INTO serie_creadores(serie_id,creador_id) VALUES(2,4);
INSERT INTO Artista(id,nombre,apellido,tipo) VALUES(5,'Andrew','Lincoln','actor');
INSERT INTO serie_actores(serie_id,actor_id) VALUES(2,5);


### Death Note ###
#Temporadas

#Capitulos

#Artistas
	 

#Usuarios
INSERT INTO Usuario(id,tipo,alias,cuenta_bancaria,password) VALUES (1,'CuotaFija','pepeflow','ES3211122233344455566677','pepe23');
INSERT INTO Usuario(id,tipo,alias,cuenta_bancaria,password) VALUES (2,'Standard','gryphus','ES99988877766655544433','elpipero');

