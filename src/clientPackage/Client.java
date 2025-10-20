package clientPackage;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Entrez l'adresse IP du serveur : ");
        String ipServeur = sc.nextLine();
        int port = 5000;

        try {
            Socket socket = new Socket(ipServeur, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Connecté au serveur !");
            String operation;

            while (true) {
                System.out.print("Entrez une opération (ex: 55 * 25) ou 'exit' pour quitter : ");
                operation = sc.nextLine();
                out.println(operation);

                if (operation.equalsIgnoreCase("exit")) {
                    break;
                }

                String resultat = in.readLine();
                System.out.println("Réponse du serveur -> " + resultat);
            }

            socket.close();
            sc.close();
            System.out.println("Client terminé.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
