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

            boolean continuer = true;
            while (continuer) {
                String choixOpStr = in.readLine();
                String nb1Str = in.readLine();
                String nb2Str = in.readLine();

                if (choixOpStr == null || nb1Str == null || nb2Str == null) break;

                int choixOp = Integer.parseInt(choixOpStr);
                double nb1 = Double.parseDouble(nb1Str);
                double nb2 = Double.parseDouble(nb2Str);

                double resultat = 0;
                boolean valid = true;

                switch (choixOp) {
                    case 1: // Addition
                        resultat = nb1 + nb2;
                        break;
                    case 2: // Soustraction
                        resultat = nb1 - nb2;
                        break;
                    case 3: // Multiplication
                        resultat = nb1 * nb2;
                        break;
                    case 4: // Division
                        if (nb2 != 0) {
                            resultat = nb1 / nb2;
                        } else {
                            valid = false;
                        }
                        break;
                    default:
                        valid = false;
                }

                if (valid) {
                    out.println(resultat);
                } else {
                    out.println("Erreur : opération invalide ou division par zéro");
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