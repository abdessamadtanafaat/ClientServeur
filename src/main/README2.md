~~~~Resume :  D'exercice 2 :~~~~ 
Afficher un message de bienvenue :

Le serveur doit envoyer un message de bienvenue dès qu’un client se connecte.
Afficher la date et l’heure :

Utiliser la classe Date de Java pour obtenir la date et l’heure actuelles et les envoyer au client.
Demander une phrase :

Le serveur doit demander une phrase au client, et le client répond via son terminal ou programme client.
Convertir la phrase en majuscule :

Après réception de la phrase du client, le serveur convertit cette phrase en majuscules et la renvoie.
Recommencer si la phrase est différente de « bye » :

Si le client ne dit pas « bye », le serveur continue de lui demander d’entrer une nouvelle phrase.


Qustions / reponses : 

e : les client telnet et java se connecter au serveur mais le serveur ne gerer qu'un seul client a la fois. 
preuve ? : voir image 2 
solution : creer un thread pour chaque connexion . voir la classe ServeurMultiClient. 
voir image 3 

pour se connecter aux serveurs de nos camarades : 
1 Connaitre l'adresse IP de vos camarades.
2 Remplacer localhost dans le programme client ou Telnet par l'adresse IP de vos camarades.
3 Exécuter le programme client pour vous connecter à leur serveur.


