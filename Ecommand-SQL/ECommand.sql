DROP TABLE IF EXISTS Usuário ;

CREATE SEQUENCE Usuário_seq;

CREATE TABLE IF NOT EXISTS Usuário (
  CodUsuário INT NOT NULL DEFAULT NEXTVAL ('Usuário_seq'),
  PRIMARY KEY (CodUsuário),
  CONSTRAINT CodUsuário_UNIQUE UNIQUE  (CodUsuário ASC) VISIBLE
);


DROP TABLE IF EXISTS Mesa ;

CREATE SEQUENCE Mesa_seq;

CREATE TABLE IF NOT EXISTS Mesa (
  idMesa INT NOT NULL DEFAULT NEXTVAL ('Mesa_seq'),
  QtdCadeiras INT NOT NULL,
  CONSTRAINT idMesa_UNIQUE UNIQUE  (idMesa ASC) VISIBLE,
  PRIMARY KEY (idMesa)
);


DROP TABLE IF EXISTS Garçom ;

CREATE TABLE IF NOT EXISTS Garçom (
  Nome VARCHAR(45) NOT NULL,
  RG INT NOT NULL,
  CPF INT NOT NULL,
  Idade INT NOT NULL,
  Endereço VARCHAR(50) NOT NULL,
  Telefone INT NOT NULL,
  Usuário_CodUsuário INT NOT NULL,
  CONSTRAINT CPF_UNIQUE UNIQUE  (CPF ASC) VISIBLE,
  PRIMARY KEY (Usuário_CodUsuário),
  CONSTRAINT fk_Garçom_Usuário1
    FOREIGN KEY (Usuário_CodUsuário)
    REFERENCES `Usuário` (CodUsuário)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


DROP TABLE IF EXISTS Cozinheiro ;

CREATE TABLE IF NOT EXISTS Cozinheiro (
  Nome VARCHAR(45) NOT NULL,
  RG INT NOT NULL,
  CPF INT NOT NULL,
  Idade INT NOT NULL,
  Endereço VARCHAR(50) NOT NULL,
  Telefone INT NOT NULL,
  Usuário_CodUsuário INT NOT NULL,
  CONSTRAINT CPF_UNIQUE UNIQUE  (CPF ASC) VISIBLE,
  PRIMARY KEY (Usuário_CodUsuário),
  CONSTRAINT fk_Cozinheiro_Usuário1
    FOREIGN KEY (Usuário_CodUsuário)
    REFERENCES `Usuário` (CodUsuário)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


DROP TABLE IF EXISTS Gerente ;

CREATE TABLE IF NOT EXISTS Gerente (
  Nome VARCHAR(45) NOT NULL,
  RG INT NOT NULL,
  CPF INT NOT NULL,
  Idade INT NOT NULL,
  Endereço VARCHAR(50) NOT NULL,
  Telefone INT NOT NULL,
  Usuário_CodUsuário INT NOT NULL,
  CONSTRAINT CPF_UNIQUE UNIQUE  (CPF ASC) VISIBLE,
  PRIMARY KEY (Usuário_CodUsuário),
  CONSTRAINT fk_Gerente_Usuário1
    FOREIGN KEY (Usuário_CodUsuário)
    REFERENCES `Usuário` (CodUsuário)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


DROP TABLE IF EXISTS Opcional ;

CREATE SEQUENCE Opcional_seq;

CREATE TABLE IF NOT EXISTS Opcional (
  CodOpcional INT NOT NULL DEFAULT NEXTVAL ('Opcional_seq'),
  Nome VARCHAR(45) NOT NULL,
  Descrição VARCHAR(200) NOT NULL,
  Quantidade INT NOT NULL,
  PRIMARY KEY (CodOpcional),
  CONSTRAINT CodOpcional_UNIQUE UNIQUE  (CodOpcional ASC) VISIBLE
);


DROP TABLE IF EXISTS Complemento ;

CREATE SEQUENCE Complemento_seq;

CREATE TABLE IF NOT EXISTS Complemento (
  CodComplemento INT NOT NULL DEFAULT NEXTVAL ('Complemento_seq'),
  Nome VARCHAR(45) NOT NULL,
  Descrição VARCHAR(200) NOT NULL,
  Quantidade INT NOT NULL,
  PRIMARY KEY (CodComplemento),
  CONSTRAINT CodComplemento_UNIQUE UNIQUE  (CodComplemento ASC) VISIBLE
);


DROP TABLE IF EXISTS Prato ;

CREATE SEQUENCE Prato_seq;

CREATE TABLE IF NOT EXISTS Prato (
  CodPrato INT NOT NULL DEFAULT NEXTVAL ('Prato_seq'),
  Nome VARCHAR(45) NOT NULL,
  Descrição VARCHAR(200) NOT NULL,
  Quantidade INT NOT NULL,
  PRIMARY KEY (CodPrato),
  CONSTRAINT CodPrato_UNIQUE UNIQUE  (CodPrato ASC) VISIBLE
);


DROP TABLE IF EXISTS Bebida ;

CREATE TABLE IF NOT EXISTS Bebida (
  CodBebida INT NOT NULL,
  Nome VARCHAR(45) NOT NULL,
  Descrição VARCHAR(200) NOT NULL,
  Quantidade INT NOT NULL,
  PRIMARY KEY (CodBebida)
);


DROP TABLE IF EXISTS Pedido ;

CREATE TABLE IF NOT EXISTS Pedido (
  Mesa_idMesa INT NOT NULL,
  Complemento_CodComplemento INT NOT NULL,
  Prato_CodPrato1 INT NOT NULL,
  Bebida_CodBebida INT NOT NULL,
  Opcional_CodOpcional INT NOT NULL,
  Garçom_Usuário_CodUsuário INT NOT NULL,
  Cozinheiro_Usuário_CodUsuário INT NOT NULL,
  PRIMARY KEY (Mesa_idMesa, Garçom_Usuário_CodUsuário)
  CREATE INDEX fk_Mesa_has_Prato_Mesa1_idx ON Pedido (Mesa_idMesa ASC) VISIBLE,
  INDEX `fk_Mesa_has_Prato_Complemento1_idx` (Complemento_CodComplemento ASC) VISIBLE,
  INDEX `fk_Mesa_has_Prato_Prato1_idx` (Prato_CodPrato1 ASC) VISIBLE,
  INDEX `fk_Mesa_has_Prato_Bebida1_idx` (Bebida_CodBebida ASC) VISIBLE,
  INDEX `fk_Mesa_has_Prato_Opcional1_idx` (Opcional_CodOpcional ASC) VISIBLE,
  INDEX `fk_Pedido_Garçom1_idx` (Garçom_Usuário_CodUsuário ASC) VISIBLE,
  INDEX `fk_Pedido_Cozinheiro1_idx` (Cozinheiro_Usuário_CodUsuário ASC) VISIBLE,
  CONSTRAINT fk_Mesa_has_Prato_Mesa1
    FOREIGN KEY (Mesa_idMesa)
    REFERENCES `Mesa` (idMesa)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Mesa_has_Prato_Complemento1
    FOREIGN KEY (Complemento_CodComplemento)
    REFERENCES `Complemento` (CodComplemento)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Mesa_has_Prato_Prato1
    FOREIGN KEY (Prato_CodPrato1)
    REFERENCES `Prato` (CodPrato)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Mesa_has_Prato_Bebida1
    FOREIGN KEY (Bebida_CodBebida)
    REFERENCES `Bebida` (CodBebida)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Mesa_has_Prato_Opcional1
    FOREIGN KEY (Opcional_CodOpcional)
    REFERENCES `Opcional` (CodOpcional)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Pedido_Garçom1
    FOREIGN KEY (Garçom_Usuário_CodUsuário)
    REFERENCES `Garçom` (Usuário_CodUsuário)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Pedido_Cozinheiro1
    FOREIGN KEY (Cozinheiro_Usuário_CodUsuário)
    REFERENCES `Cozinheiro` (Usuário_CodUsuário)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
