-- Supprimer et recréer la base de données
DROP DATABASE IF EXISTS Conversation;
CREATE DATABASE Conversation;
USE Conversation;

-- Supprimer la table si elle existe
DROP TABLE IF EXISTS conversation;

-- Créer la table conversation
CREATE TABLE conversation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    message VARCHAR(200) NOT NULL,
    reponse VARCHAR(200) NOT NULL,
    timestamp DATETIME NOT NULL
);