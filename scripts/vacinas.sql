CREATE TABLE `entra21`.`vacinas` (
  `pais_origem` VARCHAR(300) NOT NULL,
  `estagio_pesquisa` INT NOT NULL,
  `data_inicio_pesquisa` DATE NOT NULL,
  `nome_responsavel` VARCHAR(300) NOT NULL);

ALTER TABLE `entra21`.`vacinas` 
ADD COLUMN `id` INT NOT NULL AUTO_INCREMENT AFTER `nome_responsavel`,
ADD PRIMARY KEY (`id`);
;

CREATE TABLE `entra21`.`pesquisador` (
  `id_Pesquisador` INT NOT NULL AUTO_INCREMENT,
  `nome_Pesquisador` VARCHAR(255) NOT NULL,
  `cpf_Pesquisador` VARCHAR(11) NOT NULL,
  `matricula_Pesquisador` VARCHAR(45) NOT NULL,
  `dataDeNascimento` DATE NOT NULL,
  PRIMARY KEY (`id_Pesquisador`));

  ALTER TABLE `entra21`.`vacinas` 
ADD COLUMN `idResponsavel_Vacina` INT NOT NULL AFTER `id`,
ADD INDEX `idResponsavel_Vacina_idx` (`idResponsavel_Vacina` ASC) VISIBLE;
;

ALTER TABLE `entra21`.`vacinas` 
ADD CONSTRAINT `idResponsavel_Vacina`
  FOREIGN KEY (`idResponsavel_Vacina`)
  REFERENCES `entra21`.`pesquisador` (`id_Pesquisador`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;