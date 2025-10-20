package serverPackage;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 5000;

        try {
            InetAddress ip = InetAddress.getLocalHost();
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(ip, port));

            System.out.println("Serveur en attente de connexion sur " + ip.getHostAddress() + ":" + port);
            Socket socket = serverSocket.accept();
            System.out.println("Client connecté : " + socket.getInetAddress().getHostAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String operation;
            while ((operation = in.readLine()) != null) {
                operation = operation.trim();
                if (operation.equalsIgnoreCase("exit")) {
                    break;
                }

                try {
                    // Séparation de l'opération : nombre opérateur nombre
                    String[] tokens = operation.split(" ");
                    if (tokens.length != 3) throw new Exception("Format invalide");

                    double nb1 = Double.parseDouble(tokens[0]);
                    String operateur = tokens[1];
                    double nb2 = Double.parseDouble(tokens[2]);

                    double resultat = 0;
                    switch (operateur) {
                        case "+": resultat = nb1 + nb2; break;
                        case "-": resultat = nb1 - nb2; break;
                        case "*": resultat = nb1 * nb2; break;
                        case "/": 
                            if (nb2 == 0) throw new Exception("Division par zéro");
                            resultat = nb1 / nb2; break;
                        default: throw new Exception("Opérateur invalide");
                    }

                    out.println("Résultat = " + resultat);

                } catch (Exception e) {
                    out.println("Erreur : opération invalide (Format attendu : nombre opérateur nombre)");
                }
            }

            socket.close();
            serverSocket.close();
            System.out.println("Connexion fermée.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}