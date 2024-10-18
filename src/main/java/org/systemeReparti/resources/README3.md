~~~~Resume :  D'exercice 3 :~~~~ 
d) Tester cette application sur deux machines reliées par réseaux
les deux machines (client et serveur) sont sur le même réseau local (LAN)
Modifier le code du serveur:

Dans Serveur3.java,
modifier le code pour écouter sur l'adresse IP de la machine
(exemple, serverSocket = new ServerSocket(12345, 50, InetAddress.getByName("192.168.1.2"));
où 192.168.1.2 est l'adresse IP de la machine serveur).
autre methode :laissez-le par défaut (new ServerSocket(PORT)), ce qui liera le serveur à toutes les interfaces disponibles.
puis demarrage de serveur : 
java main.java.org.systemeReparti.server.Serveur3

java main.java.org.systemeReparti.server.Serveur3MultiClient
Modifier code de client : 
Dans le code du client,l’adresse IP du serveur est utilisée lors de la connexion
(par exemple, utilisez Socket socket = new Socket("192.168.1.2", 12345);).


f: 
application serveur multi-client : 

java org.systemeReparti.client.Client3
java org.systemeReparti.server.Server




l'objectif de tp 2 est de faire les methodes non blocanntes , car les methodes sont  blockants : 
readLine() , et println() , writeObject, ReadObject, 

solution JavaNio :  java ajouter led cannaeux et les buffers , c'est a dire java.Nio . 

Gerer plusierus client  : selecteur  son role de noter les requests , et chaqued canal 

en effet : NIO : non-blocking i/o  voir la shema de cours , s'appeler Selector Model 
selector  : note les evenements . : evenement d'accptation .
objet de class selector et d'enregister les evenemant comme l'exemple de restaurant . 
est de pas rester bloquee . 
chef cuisinier est le tread et le serveur et le selector . 
les canneaux .
serverur ne restee pas bloquee pour le client 1.

loop event : boucle pour verifier s'il ya des evenement dans le logicielle 
framework tres connu utilise cela c'est nodeJS.

etapes : (Solution : )
creer le selector . qui lier plusieures canneaux commme un multiplexeur . 
creer la socket serveur .  (server socket channel car on travail avec nio a la place de io ) 
bind : Associer ce serveur a un IP et port . 
enregistrer l'evenetment de sever socket (la methode accept ) et les methodes register . 
pour ne pas etre bloquantes. 
wait for the event  avedc selector.select () . 
client ready to accept .
read te client message (client.register , selector.Selector)
close la connection. 

-------------------------------------------------------------------


gestion des buffer : lire block par block c'est ca l'avatage de buffer . 
creer buffer vide. (methode allocation.)
buffer comporte trois indexes positon (voir shema de cours) 
les methodes flip et clear . 

