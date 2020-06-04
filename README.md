# Poseidon

### Documentation :
Lien de la documentation : 
https://web.postman.co/collections/10064600-ae7690cb-8786-4523-a70e-440e89afe9d7?version=latest&workspace=ac282a14-d2ab-4bfd-874b-e409eddb7a04#f984a6df-aa60-44ce-84b1-611dc0796fe3

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

Dans le header, mettre les informations suivantes :

KEY : Authorization

VALUE : Bearer "< mettre ici le token >"


