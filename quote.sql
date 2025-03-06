DROP DATABASE IF EXISTS Quote;
CREATE DATABASE Quote;
USE Quote;
DROP TABLE IF EXISTS Citation;


CREATE TABLE Citation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    contenu VARCHAR(200) NOT NULL
);

INSERT INTO Citation (id, contenu) VALUES
(1, "Que ton intérêt porte sur l'action seulement, jamais sur ses résultats"),
(2, "Pour ceux qui ont conquis l'esprit, l'esprit est leur ami. Pour ceux qui n'ont pas réussi à le faire, l'esprit agit comme un ennemi.")
