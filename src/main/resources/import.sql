/*Creación de asesores*/
INSERT INTO asesores (nombre, especialidad) VALUES ('Asesor1', 'Especialidad1');
INSERT INTO asesores (nombre, especialidad) VALUES ('Asesor2', 'Especialidad1');
INSERT INTO asesores (nombre, especialidad) VALUES ('Asesor3', 'Especialidad2');
INSERT INTO asesores (nombre, especialidad) VALUES ('Asesor4', 'Especialidad2');
INSERT INTO asesores (nombre, especialidad) VALUES ('Asesor5', 'Especialidad3');
INSERT INTO asesores (nombre, especialidad) VALUES ('Asesor6', 'Especialidad3');
INSERT INTO asesores (nombre, especialidad) VALUES ('Asesor7', 'Especialidad4');

/*Creación de clientes*/
INSERT INTO clientes (nombre, ciudad, direccion, telefono) VALUES ('Cliente1', 'Ciudad1', 'Direccion1', 3205205524);
INSERT INTO clientes (nombre, ciudad, direccion, telefono) VALUES ('Cliente2', 'Ciudad2', 'Direccion2', 3205205521);
INSERT INTO clientes (nombre, ciudad, direccion, telefono) VALUES ('Cliente3', 'Ciudad3', 'Direccion3', 3205205522);
INSERT INTO clientes (nombre, ciudad, direccion, telefono) VALUES ('Cliente4', 'Ciudad4', 'Direccion4', 3205205523);
INSERT INTO clientes (nombre, ciudad, direccion, telefono) VALUES ('Cliente5', 'Ciudad5', 'Direccion5', 3205205524);
INSERT INTO clientes (nombre, ciudad, direccion, telefono) VALUES ('Cliente6', 'Ciudad6', 'Direccion6', 3205205525);
INSERT INTO clientes (nombre, ciudad, direccion, telefono) VALUES ('Cliente7', 'Ciudad7', 'Direccion7', 3205205526);
INSERT INTO clientes (nombre, ciudad, direccion, telefono) VALUES ('Cliente8', 'Ciudad8', 'Direccion8', 3205205527);
INSERT INTO clientes (nombre, ciudad, direccion, telefono) VALUES ('Cliente9', 'Ciudad9', 'Direccion9', 3205205528);
INSERT INTO clientes (nombre, ciudad, direccion, telefono) VALUES ('Cliente10', 'Ciudad10', 'Direccion10', 3235205524);

/*--------------------Creación de tarjetas--------------------------*/

/*Tarjetas para el cliente 1*/
INSERT INTO tarjetas (ccv, numero, tipo, cliente_id) VALUES (111, '1234567812345678', 'Credito', 1);
INSERT INTO tarjetas (ccv, numero, tipo, cliente_id) VALUES (112, '2234567812345678', 'Debito', 1);
INSERT INTO tarjetas (ccv, numero, tipo, cliente_id) VALUES (113, '3234567812345678', 'Credito', 1);

/*Tarjetas para el cliente 2*/
INSERT INTO tarjetas (ccv, numero, tipo, cliente_id) VALUES (114, '4234567812345678', 'Credito', 2);

/*Tarjetas para el cliente 3*/
INSERT INTO tarjetas (ccv, numero, tipo, cliente_id) VALUES (115, '5234567812345678', 'Credito', 3);
INSERT INTO tarjetas (ccv, numero, tipo, cliente_id) VALUES (116, '6234567812345678', 'Credito', 3);

/*Tarjetas para el cliente 4*/
INSERT INTO tarjetas (ccv, numero, tipo, cliente_id) VALUES (117, '7234567812345678', 'Credito', 4);

/*Tarjetas para el cliente 5*/
INSERT INTO tarjetas (ccv, numero, tipo, cliente_id) VALUES (118, '8234567812345678', 'Debito', 5);

/*Tarjetas para el cliente 6*/
INSERT INTO tarjetas (ccv, numero, tipo, cliente_id) VALUES (119, '9234567812345678', 'Debito', 6);

/*Tarjetas para el cliente 7*/
INSERT INTO tarjetas (ccv, numero, tipo, cliente_id) VALUES (121, '1234567812345671', 'Debito', 7);

/*Tarjetas para el cliente 8*/
INSERT INTO tarjetas (ccv, numero, tipo, cliente_id) VALUES (131, '1234567812345672', 'Debito', 8);

/*Tarjetas para el cliente 9*/
INSERT INTO tarjetas (ccv, numero, tipo, cliente_id) VALUES (141, '1234567812345673', 'Debito', 9);
INSERT INTO tarjetas (ccv, numero, tipo, cliente_id) VALUES (151, '1234567812345674', 'Credito', 9);
INSERT INTO tarjetas (ccv, numero, tipo, cliente_id) VALUES (161, '1234567812345675', 'Credito', 9);

/*---------------------Creación de consumos-------------------------*/

/*Consumos para la tarjeta 1*/
INSERT INTO consumos (descripcion, fecha, monto, tarjeta_id) VALUES ('Compras establecimiento1', '2017-12-12', 540000, 1);
INSERT INTO consumos (descripcion, fecha, monto, tarjeta_id) VALUES ('Compras establecimiento2', '2018-12-12', 40000, 1);
INSERT INTO consumos (descripcion, fecha, monto, tarjeta_id) VALUES ('Compras establecimiento3', '2017-11-12', 50000, 1);
INSERT INTO consumos (descripcion, fecha, monto, tarjeta_id) VALUES ('Compras establecimiento4', '2017-12-24', 54000, 1);
INSERT INTO consumos (descripcion, fecha, monto, tarjeta_id) VALUES ('Compras establecimiento1', '2017-12-15', 2000000, 1);

/*Consumos para la tarjeta 2*/
INSERT INTO consumos (descripcion, fecha, monto, tarjeta_id) VALUES ('Compras establecimiento1', '2017-12-12', 540000, 2);

/*Consumos para la tarjeta 5*/
INSERT INTO consumos (descripcion, fecha, monto, tarjeta_id) VALUES ('Compras establecimiento3', '2017-11-12', 50000, 5);
INSERT INTO consumos (descripcion, fecha, monto, tarjeta_id) VALUES ('Compras establecimiento4', '2017-12-24', 54000, 5);
INSERT INTO consumos (descripcion, fecha, monto, tarjeta_id) VALUES ('Compras establecimiento1', '2017-12-15', 2000000, 5);

/*Consumos para la tarjeta 9*/
INSERT INTO consumos (descripcion, fecha, monto, tarjeta_id) VALUES ('Compras establecimiento4', '2017-12-24', 54000, 9);
INSERT INTO consumos (descripcion, fecha, monto, tarjeta_id) VALUES ('Compras establecimiento1', '2017-12-15', 2000000, 9);
