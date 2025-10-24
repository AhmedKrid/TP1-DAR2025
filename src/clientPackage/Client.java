package clientPackage;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import object.Operation;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connecté au serveur.");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Scanner sc = new Scanner(System.in);
            System.out.print("Entrez le premier nombre : ");
            double op1 = sc.nextDouble();

            System.out.print("Entrez l'opérateur (+, -, *, /) : ");
            char operateur = sc.next().charAt(0);

            System.out.print("Entrez le deuxième nombre : ");
            double op2 = sc.nextDouble();

            // Création de l’objet
            Operation operation = new Operation(op1, op2, operateur);

            // Envoi au serveur
            oos.writeObject(operation);

            // Lecture du résultat
            double resultat = (double) ois.readObject();
            System.out.println("Résultat reçu du serveur : " + resultat);

            // Fermeture
            sc.close();
            ois.close();
            oos.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
