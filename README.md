# Pré-requis :
- Node.js v20.17.0 ou +
- Angular CLI v18.2.15 ou +
- JDK 22 ou +
- Maven
- PostgreSQL

# Installation 
1. Importer le repository en local
2. Dans le projet "pocmessagerie", lancer la commande
```bash
mvn install
```
4. Dans le projet "pocmessagerie-front", lancer la commande 
```bash
npm install
```
6. Dans votre base de données PostgreSQL locale, nommée "postgres", exécuter le fichier "Script création BDD PoC" pour crééer la table "messages"

# Exécution
1. Dans le projet "pocmessagerie", lancer la commande
```bash
mvn spring-boot:run
```
3.  Dans le projet "pocmessagerie-front", lancer la commande
```bash
ng serve
```
5.  Vous rendre sur l'url http://localhost:4200/
