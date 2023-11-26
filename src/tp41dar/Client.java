package tp41dar;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    private static final int PORT = 1234;  // Définit le numéro de port utilisé pour la communication.
    private static byte[] buffer = new byte[1024];  // Crée un tampon pour stocker les données reçues.

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();  // Crée un socket UDP pour la communication.
        Scanner scan = new Scanner(System.in);  // Crée un objet Scanner pour lire l'entrée de l'utilisateur.
        String userName = scan.nextLine();  // Lit la chaîne de caractères saisie par l'utilisateur depuis la console.
        DatagramPacket dataToSend = new DatagramPacket(userName.getBytes(), userName.length(), InetAddress.getByName("localhost"), PORT);
        // Crée un paquet Datagram contenant les données à envoyer, l'adresse IP du serveur ("localhost") et le port.
        socket.send(dataToSend);  // Envoie le paquet de données au serveur.
        DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);  // Crée un paquet Datagram pour recevoir les données du serveur.
        socket.receive(receivedPacket);  // Attend et reçoit le paquet de données du serveur.
        System.out.println("Serveur: " + new String(receivedPacket.getData(), 0, receivedPacket.getLength()));
        // Affiche la réponse du serveur extraite du paquet reçu sur la console.
    }
}






