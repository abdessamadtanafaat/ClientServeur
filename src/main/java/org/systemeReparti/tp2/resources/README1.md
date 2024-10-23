A) 
Execution de serveur montre que le serveur se lance et ecoute sur le port 8089 
mais aucun interaction directe , le serveur est en attente de connexions clients. 
tout en fonctionnant en mode non bloquant. 
le serveur ici fonctionner sans se bloquer et peut gerer plusieurs clients simultanee. 

B) 
le serveur accepte la connexion du client. 
le client envoie un message au serveur alors dans ce cas, le serveur repondre avec 
bonjour Mr Client.


C) 
dans le cas de plusieurs connexions : 
le serveur accepte les connexions de chacun des clients
traite les messages sans interruption.
traitement simultanee en mode non-bloqueant grace a "Selector"

D)
oui serveur , multi clients car : 
gestion plusieurs connexions de clients en meme temps
grace a l'utilisation des Socket NIO non-bloquants et l'api Selector.
un seul thread gere plusieurs clients simultanement en mode non bloqueants.
contraire de serveurs sockets bloqueants : c'ad necessite un thread par client. 

E)
points forts de cela : 

NIO non bloquants vs sockets bloquantes : 

un seul thread : gerer plusieus connexions simultanees . 
contraire a un modele bloquants : chaque connexion necessite son propre thread .
dont consomation plus de ressources memoire et processeur. 

- serveur ne se bloque pas lors de l'attente de donnees d'un client .
- dans un modele bloquant, le client si ne repond pas , le thread associe serait bloquee, d'ou ralentir le traitment global . 

scalabilite : serveur gerer un grand nombre de connexions simultanees sans avoir creer un thread pour chaque connexion.
traitment rapide car le serveur ne bloque pas les operations de lecture et ecriture.
