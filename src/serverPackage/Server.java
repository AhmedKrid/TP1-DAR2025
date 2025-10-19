package serverPackage;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serveurSocket = new ServerSocket(5000);
            System.out.println("Serveur en attente de connexion ");

            Socket socket = serveurSocket.accept();
            System.out.println("Client connecté");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = in.readLine();
            int x = Integer.parseInt(message);
            System.out.println("Nombre " + x);
            int resultat = x * 5;
            System.out.println("resultat : " + x + " * 5 = " + resultat);
            out.println(resultat);
            System.out.println("Résultat envoyé au client.");
            socket.close();
            serveurSocket.close();
            System.out.println("Connexion fermée.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
