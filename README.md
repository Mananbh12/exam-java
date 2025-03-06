Ce projet est composé de deux applications Spring Boot qui fonctionnent ensemble :

API : Une API qui fournit des citations inspirantes aléatoires stockées dans une base de données.

Application principale : Une interface de conversation où les employés peuvent écrire leurs problèmes et recevoir des réponses inspirantes via l'API. 
Les conversations sont également enregistrées pour consultation ultérieure.

Le projet est organisé en deux branches :

main : Contient l'application principale, qui tourne sur le port 8081.

api : Contient l'API, qui tourne sur le port 8080.
Les deux applications communiquent via des appels HTTP, en utilisant RestTemplate pour respecter les bonnes pratiques d'une architecture orientée services.

Prérequis
Avant de lancer le projet, assurez-vous d'avoir les outils suivants installés :

Java : Version 11 ou supérieure.
Maven : Pour gérer les dépendances et compiler les projets.
Git : Pour cloner le dépôt et naviguer entre les branches.
Une base de données 

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


Lancement de l'API (branche api)
L'API fournit des citations inspirantes via un endpoint unique. Voici comment la lancer :

Basculez sur la branche api :
git checkout api

Compilez et lancez l'API avec Maven :
mvn clean install
mvn spring-boot:run

L'API sera disponible sur http://localhost:8081.

Endpoint principal :

GET /getQuote : Retourne une citation inspirante aléatoire depuis la base de données.
Configuration de la base de données :

Par défaut, l'API utilise H2 en mémoire. Vérifiez ou modifiez la configuration dans src/main/resources/application.properties en fonction de votre BDD
Sinon gardez la comme telle pour utiliser les données actuelle (voir quote.sql)
Ne pas oublier de lancer votre BDD

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Lancement de l'application principale (branche main)
L'application principale est une interface de conversation qui appelle l'API pour fournir des réponses inspirantes. Voici comment la lancer :

Basculez sur la branche main :
git checkout main


Compilez et lancez l'application avec Maven :
mvn clean install
mvn spring-boot:run

L'application sera disponible sur http://localhost:8081.

Fonctionnalités :

Page de conversation : Accessible via http://localhost:8081/conversation. Les utilisateurs saisissent leur nom et leur message, et reçoivent une citation inspirante en réponse, récupérée depuis l'API.

Page d'administration : Une page spéciale (ex. http://localhost:8081/admin) affiche la liste des utilisateurs ayant posté des messages et permet de consulter leurs conversations enregistrées.
Les conversations (nom, message, réponse, date) sont stockées dans une base de données (conversation.sql) qu'il faudra également lancer.

Communication avec l'API :

L'application utilise RestTemplate pour appeler l'endpoint GET /getQuote de l'API sur http://localhost:8080/getQuote.
Configuration de la base de données :

Comme pour l'API, H2 en mémoire est utilisé par défaut. Vérifiez ou modifiez src/main/resources/application.properties 

Utilisation

Lancez d'abord l'API (branche api) sur le port 8081. (en tapant http://localhost:8081/getQuote vous pourrez obtenir une citation au hasard depuis la BDD)
Lancez ensuite l'application principale (branche main) sur le port 8080.
Ouvrez un navigateur et accédez à http://localhost:8080 pour utiliser la page de conversation.
Pour consulter les conversations enregistrées, accédez à la page d'administration (ex. http://localhost:8080/admin).







