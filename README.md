# Poseidon

### Variables d'environements :
JASYPT_ENCRYPTOR_PASSWORD = poseidon
USERNAME = JBR

### Générer un token :
Pour accéder au URL il faut générer un Token.

1. Envoyer une requête POST à l'url : http://localhost8080/authenticate
Dans le body, envoyer cet ojet JSON :
{
  "username":"javainuse",
  "password":"password"
}

La réponse obtenue est un token.
Exemple :
{
  "token":"vzefvzv12345vez234VZefv"
}

2. Utiliser ce token pour chaque requête de l'API
