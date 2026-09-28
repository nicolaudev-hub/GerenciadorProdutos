CREATE DATABASE IF NOT EXISTS DBprodutos
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE DBprodutos;

CREATE TABLE IF NOT EXISTS produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    preco DOUBLE,
    quantidade INT,
    categoria VARCHAR(255)
);