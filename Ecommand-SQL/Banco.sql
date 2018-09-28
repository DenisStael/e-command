CREATE SEQUENCE Usuario_seq;

CREATE TABLE Usuario (
  CodUsuario INT NOT NULL DEFAULT NEXTVAL ('Usuario_seq'),
  Senha VARCHAR(45) NOT NULL,
  Nome VARCHAR(45) NOT NULL,
  tipo VARCHAR(15) NOT NULL CHECK (tipo IN ('Gerente','Garçom','Cozinheiro')),
  PRIMARY KEY (CodUsuario)
);

CREATE TABLE Mesa (
  idMesa INT NOT NULL,
  QtdCadeiras INT NOT NULL,
  PRIMARY KEY (idMesa)
);

CREATE SEQUENCE Produto_seq;

CREATE TABLE Produto (
  CodProduto INT NOT NULL DEFAULT NEXTVAL ('Produto_seq'),
  Nome VARCHAR(45) NOT NULL,
  Descricao VARCHAR(200) NOT NULL,
  Quantidade DECIMAL(10,3) NOT NULL,
  medida VARCHAR(2) NOT NULL CHECK (medida IN ('Kg','Lt','Un')),
  PRIMARY KEY (CodProduto) 
);

CREATE TABLE Bebida (
  CodBebida INT NOT NULL,
  Preco DECIMAL(10,2) NOT NULL,
  cardapio BOOLEAN DEFAULT FALSE,
  Quantidade INT DEFAULT 0,
  PRIMARY KEY (CodBebida),
  FOREIGN KEY (CodBebida) 
  REFERENCES Produto (CodProduto)
  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE SEQUENCE Prato_seq;

CREATE TABLE Prato (
  CodPrato INT NOT NULL DEFAULT NEXTVAL ('Prato_seq'),
  Nome VARCHAR(45) NOT NULL,
  Descricao VARCHAR(200) NOT NULL,
  Preco DECIMAL(10,2) NOT NULL,
  cardapio BOOLEAN DEFAULT FALSE,
  Quantidade INT DEFAULT 0,
  PRIMARY KEY (CodPrato)
);


CREATE SEQUENCE Pedido_seq;

CREATE TABLE Pedido (
  CodPedido INT NOT NULL DEFAULT NEXTVAL ('Pedido_seq'),
  Mesa_idMesa INT NOT NULL,
  Garcom_Usuario_CodUsuario INT,
  Cozinheiro_Usuario_CodUsuario INT,
  Observacao VARCHAR(200),
  PRIMARY KEY (CodPedido),
  CONSTRAINT fk_Mesa_has_Prato_Mesa1
    FOREIGN KEY (Mesa_idMesa)
    REFERENCES Mesa (idMesa)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Pedido_Garcom1
    FOREIGN KEY (Garcom_Usuario_CodUsuario)
    REFERENCES Usuario (CodUsuario)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Pedido_Cozinheiro1
    FOREIGN KEY (Cozinheiro_Usuario_CodUsuario)
    REFERENCES Usuario (CodUsuario)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE INDEX fk_Mesa_has_Prato_Mesa1_idx ON Pedido (Mesa_idMesa);
CREATE INDEX fk_Pedido_Garcom1_idx ON Pedido (Garcom_Usuario_CodUsuario);
CREATE INDEX fk_Pedido_Cozinheiro1_idx ON Pedido (Cozinheiro_Usuario_CodUsuario);

CREATE TABLE PedidoPrato(
  CodPrato INT NOT NULL,
  CodPedido INT NOT NULL,
  PRIMARY KEY (CodPrato,CodPedido),
  FOREIGN KEY (CodPrato) REFERENCES Prato (CodPrato)
  ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (CodPedido) REFERENCES Pedido (CodPedido)
  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE PedidoBebida(
  CodBebida INT NOT NULL,
  CodPedido INT NOT NULL,
  PRIMARY KEY (CodBebida,CodPedido),
  FOREIGN KEY (CodBebida) REFERENCES Bebida (CodBebida)
  ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (CodPedido) REFERENCES Pedido (CodPedido)
  ON DELETE CASCADE ON UPDATE CASCADE
);
