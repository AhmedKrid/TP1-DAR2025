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

            boolean continuer = true;
            while (continuer) {
                System.out.println("Sélectionnez l'opération :");
                System.out.println("1 - Addition");
                System.out.println("2 - Soustraction");
                System.out.println("3 - Multiplication");
                System.out.println("4 - Division");
                System.out.println("0 - Quitter");
                System.out.print("Entrez votre choix : ");
                int choix = sc.nextInt();

                if (choix == 0) {
                    continuer = false;
                    break;
                }

                System.out.print("Entrez le premier nombre : ");
                double nb1 = sc.nextDouble();
                System.out.print("Entrez le deuxième nombre : ");
                double nb2 = sc.nextDouble();

                out.println(choix);
                out.println(nb1);
                out.println(nb2);

                String resultat = in.readLine();
                System.out.println("Résultat reçu du serveur : " + resultat);
                System.out.println();
            }

            socket.close();
            sc.close();
            System.out.println("Connexion terminée.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
