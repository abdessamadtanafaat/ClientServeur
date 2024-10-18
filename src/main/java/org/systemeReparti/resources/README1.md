~~~~Resume :  D'exercice 1 :~~~~ 

Serveur :

Le serveur est initialisé sur le port 7878.
Il attend qu'un client se connecte en utilisant accept().
Une fois le client connecté, il lit un message envoyé par ce dernier et renvoie une réponse.
Client :

Le client se connecte au serveur en utilisant l'adresse et le port définis.
Il envoie un message au serveur et attend une réponse.

Questions Reponses:

convertit le code source Java en bytecode exécutable par la JVM (Java Virtual Machine).
puis taper : java org.systemeReparti.server.Serveur et java org.systemeReparti.client.Client
il se peut que vous deviez activer Telnet via le panneau de configuration (Programmes > Activer ou désactiver des fonctionnalités Windows > Cochez "Client Telnet").
java org.systemeReparti.client.Client2

Dégager les points faibles de ce serveur ? : 

Probleme : Gestion d'une seule connexion.
Pourquoi ? : severSocket.accept() bloque l'execution, un seul client qui peut se connecter
tant que session actual n'est pas terminee.
Solution : utilisation des threads pour gerer plusierus connexions simultanement. 

probleme : manque de securite et verification. 
pourquoi? : affichage de contenu sans verification et sans authentification des utilisateurs .
Solution : mecanismes d'authentification  et de chiffremment via SSL et TLS. 

Probleme : pas de gestion d'etat entre les connexions client 
pourquoi ? : traitement de chaque client de maniere independante , aucun informations est conservee entre 2 connexions. 
Solution : gestion d'etat avec identifiants de session ou stockage des donnes pour chaque client . 

probleme : fermeture incorrecte des ressources en cas d'erreur .
pourquoi ? : les flux et sockets sont fermes manuellment  avec close() , des ressources peut rester ouverts ,
donc provoquer des fuites . 
solution : utilisation de try-with-resources pour garantir la fermeture automatique des ressources en cas d'erreur. 


selon la seance de tp :
probleme de methodes blocckantes dans le code , la solution est de faire java.nio

