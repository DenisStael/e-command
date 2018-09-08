CREATE TABLE prato(
	cod_prato SERIAL, 
	nome VARCHAR(250) NOT NULL,
	status_disponibilidade BIT,
	preco DECIMAL(10,2),
	descricao VARCHAR(250),
	CONSTRAINT PK_PRATO PRIMARY KEY (cod_prato)
);

CREATE TABLE produto(
	cod_produto SERIAL,
	nome VARCHAR(250),
	quantidade INTEGER,
	descricao VARCHAR(250),
	CONSTRAINT PK_PROD PRIMARY KEY (cod_produto)
);

CREATE TABLE funcionario(
	cod_funcionario SERIAL,
	nome VARCHAR(250),
	tipo VARCHAR(20),
	senha VARCHAR(35),
	CONSTRAINT PK_FUNC PRIMARY KEY (cod_funcionario)
);

CREATE TABLE mesa(
	numero_mesa SERIAL,
	status_ocupacao BIT,
	CONSTRAINT PK_MESA PRIMARY KEY (numero_mesa)
);

CREATE TABLE pedido(
	cod_pedido SERIAL,
	status_pedido BIT,
	numero_mesa SERIAL,
	cod_funcionario SERIAL,
	CONSTRAINT PK_PEDIDO PRIMARY KEY (cod_pedido),
	CONSTRAINT FK_MESA_PED FOREIGN KEY (numero_mesa) REFERENCES mesa(numero_mesa) ON UPDATE CASCADE ON DELETE NO ACTION,
	CONSTRAINT FK_FUNC_PED FOREIGN KEY (cod_funcionario) REFERENCES funcionario(cod_funcionario) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE produto_prato(
	cod_produto SERIAL,
	cod_prato SERIAL,
	CONSTRAINT PK_PROD_PRATO PRIMARY KEY (cod_prato, cod_produto),
	CONSTRAINT FK_PROD_PRATO FOREIGN KEY (cod_produto) REFERENCES produto(cod_produto) ON UPDATE CASCADE ON DELETE NO ACTION,
	CONSTRAINT FK_PRATO_PROD FOREIGN KEY (cod_prato) REFERENCES prato(cod_prato) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE pedido_prato(
	cod_pedido SERIAL,
	cod_prato SERIAL,
	CONSTRAINT PK_PED_PRATO PRIMARY KEY (cod_pedido, cod_prato),
	CONSTRAINT FK_PED_PRATO FOREIGN KEY (cod_pedido) REFERENCES pedido(cod_pedido) ON UPDATE CASCADE ON DELETE NO ACTION,
	CONSTRAINT FK_PRATO_PED FOREIGN KEY (cod_prato) REFERENCES prato(cod_prato) ON UPDATE CASCADE ON DELETE NO ACTION
);

