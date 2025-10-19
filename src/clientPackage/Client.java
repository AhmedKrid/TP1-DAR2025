package clientPackage;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("✅ Connecté au serveur.");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);
            System.out.print("Entrez un entier : ");
            int x = sc.nextInt();
            out.println(x);
            System.out.println("Nombre envoyé au serveur : " + x);
            String resultat = in.readLine();
            System.out.println("Résultat reçu du serveur : " + resultat);
            socket.close();
            sc.close();
            System.out.println("Connexion fermée.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
