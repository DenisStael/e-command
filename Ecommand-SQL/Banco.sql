CREATE SEQUENCE Usuario_seq;

CREATE TABLE Usuario (
  CodUsuario INT NOT NULL DEFAULT NEXTVAL ('Usuario_seq'),
  Senha VARCHAR(45) NOT NULL,
  Nome VARCHAR(45) NOT NULL,
  PRIMARY KEY (CodUsuario))
;

CREATE SEQUENCE Mesa_seq;

CREATE TABLE Mesa (
  idMesa INT NOT NULL DEFAULT NEXTVAL ('Mesa_seq'),
  QtdCadeiras INT NOT NULL,
  PRIMARY KEY (idMesa))
;

CREATE TABLE Garcom (
  Usuario_CodUsuario INT NOT NULL,
  PRIMARY KEY (Usuario_CodUsuario),
  CONSTRAINT fk_Garcom_Usuario1
    FOREIGN KEY (Usuario_CodUsuario)
    REFERENCES Usuario (CodUsuario)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
;

CREATE TABLE Cozinheiro (
  Usuario_CodUsuario INT NOT NULL,
  PRIMARY KEY (Usuario_CodUsuario),
  CONSTRAINT fk_Cozinheiro_Usuario1
    FOREIGN KEY (Usuario_CodUsuario)
    REFERENCES Usuario (CodUsuario)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
;

CREATE TABLE Gerente (
  Usuario_CodUsuario INT NOT NULL,
  PRIMARY KEY (Usuario_CodUsuario),
  CONSTRAINT fk_Gerente_Usuario1
    FOREIGN KEY (Usuario_CodUsuario)
    REFERENCES Usuario (CodUsuario)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
;

CREATE SEQUENCE Produto_seq;

CREATE TABLE Produto (
  CodProduto INT NOT NULL DEFAULT NEXTVAL ('Produto_seq'),
  Nome VARCHAR(45) NOT NULL,
  Descricao VARCHAR(200) NOT NULL,
  Quantidade INT NOT NULL,
  PRIMARY KEY (CodProduto) 
);

CREATE TABLE Opcional (
  CodOpcional INT NOT NULL,
  Preco DECIMAL(10,2) NOT NULL,
  cardapio BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (CodOpcional),
  FOREIGN KEY (CodOpcional) 
  REFERENCES Produto (CodProduto)
  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Complemento (
  CodComplemento INT NOT NULL,
  Preco DECIMAL(10,2) NOT NULL,
  cardapio BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (CodComplemento),
  FOREIGN KEY (CodComplemento) 
  REFERENCES Produto (CodProduto)
  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Bebida (
  CodBebida INT NOT NULL,
  Preco DECIMAL(10,2) NOT NULL,
  cardapio BOOLEAN DEFAULT FALSE,
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
  PRIMARY KEY (CodPrato)
);

CREATE TABLE PratoProdutos (
  CodPrato INT NOT NULL,
  CodProduto INT NOT NULL,
  PRIMARY KEY (CodPrato, CodProduto),
  FOREIGN KEY (CodPrato) REFERENCES Prato (CodPrato) 
  ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (CodProduto) REFERENCES Produto (CodProduto) 
  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Pedido (
  Mesa_idMesa INT NOT NULL,
  Complemento_CodComplemento INT NOT NULL,
  Prato_CodPrato1 INT NOT NULL,
  Bebida_CodBebida INT NOT NULL,
  Opcional_CodOpcional INT NOT NULL,
  Garcom_Usuario_CodUsuario INT NOT NULL,
  Cozinheiro_Usuario_CodUsuario INT NOT NULL,
  PRIMARY KEY (Mesa_idMesa, Garcom_Usuario_CodUsuario)
 ,
  CONSTRAINT fk_Mesa_has_Prato_Mesa1
    FOREIGN KEY (Mesa_idMesa)
    REFERENCES Mesa (idMesa)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Mesa_has_Prato_Complemento1
    FOREIGN KEY (Complemento_CodComplemento)
    REFERENCES Complemento (CodComplemento)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Mesa_has_Prato_Prato1
    FOREIGN KEY (Prato_CodPrato1)
    REFERENCES Prato (CodPrato)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Mesa_has_Prato_Bebida1
    FOREIGN KEY (Bebida_CodBebida)
    REFERENCES Bebida (CodBebida)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Mesa_has_Prato_Opcional1
    FOREIGN KEY (Opcional_CodOpcional)
    REFERENCES Opcional (CodOpcional)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Pedido_Garcom1
    FOREIGN KEY (Garcom_Usuario_CodUsuario)
    REFERENCES Garcom (Usuario_CodUsuario)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Pedido_Cozinheiro1
    FOREIGN KEY (Cozinheiro_Usuario_CodUsuario)
    REFERENCES Cozinheiro (Usuario_CodUsuario)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
 ;

CREATE INDEX fk_Mesa_has_Prato_Mesa1_idx ON Pedido (Mesa_idMesa);
CREATE INDEX fk_Mesa_has_Prato_Complemento1_idx ON Pedido (Complemento_CodComplemento);
CREATE INDEX fk_Mesa_has_Prato_Prato1_idx ON Pedido (Prato_CodPrato1);
CREATE INDEX fk_Mesa_has_Prato_Bebida1_idx ON Pedido (Bebida_CodBebida);
CREATE INDEX fk_Mesa_has_Prato_Opcional1_idx ON Pedido (Opcional_CodOpcional);
CREATE INDEX fk_Pedido_Garcom1_idx ON Pedido (Garcom_Usuario_CodUsuario);
CREATE INDEX fk_Pedido_Cozinheiro1_idx ON Pedido (Cozinheiro_Usuario_CodUsuario);