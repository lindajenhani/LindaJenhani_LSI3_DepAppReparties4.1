package tp41dar;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    // Définit un port constant sur lequel le serveur va écouter.
    private static final int PORT = 1234;
    // Crée un tableau de bytes pour stocker les données reçues.
    private static byte[] buffer = new byte[1024];

    public static void main(String[] args) throws IOException {
        // Crée un objet DatagramSocket lié au port 1234 pour écouter les paquets UDP entrants.
        DatagramSocket socket = new DatagramSocket(1234);

        while (true) {
            // Crée un DatagramPacket pour recevoir les données, avec le tableau buffer.
            DatagramPacket usernamepacket = new DatagramPacket(buffer, buffer.length);
            // Attend la réception d'un paquet UDP et le stocke dans usernamepacket.
            socket.receive(usernamepacket);

            // Convertit les données reçues en une chaîne de caractères (nom d'utilisateur).
            String username = new String(usernamepacket.getData(), 0, usernamepacket.getLength());
            // Affiche l'adresse IP de l'expéditeur et le nom d'utilisateur reçu.
            System.out.println(usernamepacket.getAddress() + " : " + username);

            // Crée un message de bienvenue en ajoutant "Bienvenue " devant le nom d'utilisateur.
            username = "Bienvenue " + username;
            // Crée un nouveau DatagramPacket pour envoyer le message de bienvenue.
            // Le message est converti en bytes, et l'adresse IP de l'expéditeur est obtenue à partir de usernamepacket.
            DatagramPacket msgToSend = new DatagramPacket(username.getBytes(), username.length(), usernamepacket.getAddress(), usernamepacket.getPort());
            // Envoie le message de bienvenue à l'expéditeur.
            socket.send(msgToSend);
        }
    }
}

