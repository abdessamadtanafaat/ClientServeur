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

Modifier code de client : 
Dans le code du client,l’adresse IP du serveur est utilisée lors de la connexion
(par exemple, utilisez Socket socket = new Socket("192.168.1.2", 12345);).


f: 
application serveur multi-client : 

java org.systemeReparti.client.Client3
java org.systemeReparti.server.Server
