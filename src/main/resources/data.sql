INSERT INTO CATEGORIA(categoria) VALUES('Automotivos');
INSERT INTO CATEGORIA(categoria) VALUES('Imóveis');
INSERT INTO CATEGORIA(categoria) VALUES('Eletrônicos');
INSERT INTO CATEGORIA(categoria) VALUES('Alimentos');

INSERT INTO CLIENTE (nome,email,rua,cidade,bairro,cep,estado,senha,admin) VALUES ('João Paulo Lira da Silva', 'joao.p.lira@gmail.com','Rua Abel Justino de Macedo','São Caitano','Centro','55130-000','PE','$2a$10$olgUyS1r4i2tPyZduBa4GuyWS//JlRM493MuXyF1DIHdONMzQIrCS',1);
INSERT INTO CLIENTE (nome,email,rua,cidade,bairro,cep,estado,senha,admin) VALUES ('Elaine Alves da Silva', 'elaine@gmail.com','Rua Abel Justino de Macedo','São Caitano','Centro','55130-000','PE','$2a$10$olgUyS1r4i2tPyZduBa4GuyWS//JlRM493MuXyF1DIHdONMzQIrCS',0);
INSERT INTO CLIENTE (nome,email,rua,cidade,bairro,cep,estado,senha,admin) VALUES ('Heitor ALvesLira da Silva', 'heitor@gmail.com','Rua Abel Justino de Macedo','São Caitano','Centro','55130-000','PE','$2a$10$olgUyS1r4i2tPyZduBa4GuyWS//JlRM493MuXyF1DIHdONMzQIrCS',0);
INSERT INTO CLIENTE (nome,email,rua,cidade,bairro,cep,estado,senha,admin) VALUES ('Edlaine Alves da Silva', 'edlaine@gmail.com','Rua Abel Justino de Macedo','São Caitano','Centro','55130-000','PE','$2a$10$olgUyS1r4i2tPyZduBa4GuyWS//JlRM493MuXyF1DIHdONMzQIrCS',0);


INSERT INTO PRODUTO (categoria_id,produto,preco,quantidade,descricao) VALUES (4,'Banana',3.00,10,'Banana Maça');
INSERT INTO PRODUTO (categoria_id,produto,preco,quantidade,descricao) VALUES (4,'Uva',5.00,50,'Uva Verde');
INSERT INTO PRODUTO (categoria_id,produto,preco,quantidade,descricao) VALUES (4,'Uva',6.00,60,'Uva Preta');
INSERT INTO PRODUTO (categoria_id,produto,preco,quantidade,descricao) VALUES (4,'Laranja',1.00,100,'Laranja Cravo');

INSERT INTO PRODUTO (categoria_id,produto,preco,quantidade,descricao) VALUES (1,'Celta',1500.00,3,'Celta Branco 2014');
INSERT INTO PRODUTO (categoria_id,produto,preco,quantidade,descricao) VALUES (1,'Gol G4',1800.00,2,'Gol Prata 2013');

INSERT INTO PRODUTO (categoria_id,produto,preco,quantidade,descricao) VALUES (2,'Casa',100000.00,1,'Casa com 2 quartos e 1 banheiro');
INSERT INTO PRODUTO (categoria_id,produto,preco,quantidade,descricao) VALUES (2,'Casa',120000.00,2,'Casa com 3 quartos e 1 banheiro');
INSERT INTO PRODUTO (categoria_id,produto,preco,quantidade,descricao) VALUES (2,'Apartamento',200000.00,1,'Ap com 3 quartos e 2 banheiro');

INSERT INTO PRODUTO (categoria_id,produto,preco,quantidade,descricao) VALUES (3,'Notbook',1500.00,1,'Dell Dual Core');
INSERT INTO PRODUTO (categoria_id,produto,preco,quantidade,descricao) VALUES (3,'PC Gamer',7000.00,2,'PG Gamer I7 16Gb 1Tb SSD');
INSERT INTO PRODUTO (categoria_id,produto,preco,quantidade,descricao) VALUES (3,'Geladeira',3000.00,1,'Geladeira consul frost free');



