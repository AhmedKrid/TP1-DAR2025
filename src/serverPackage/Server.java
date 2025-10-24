package serverPackage;

import java.io.*;
import java.net.*;
import object.Operation;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Serveur en attente de connexion...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connecté !");

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            // Réception de l’objet
            Operation op = (Operation) ois.readObject();
            System.out.println("Objet reçu : " + op.getOp1() + " " + op.getOperateur() + " " + op.getOp2());

            // Traitement
            double resultat = 0;
            switch (op.getOperateur()) {
                case '+': resultat = op.getOp1() + op.getOp2(); break;
                case '-': resultat = op.getOp1() - op.getOp2(); break;
                case '*': resultat = op.getOp1() * op.getOp2(); break;
                case '/': 
                    if (op.getOp2() != 0)
                        resultat = op.getOp1() / op.getOp2();
                    else
                        throw new ArithmeticException("Division par zéro !");
                    break;
                default: 
                    System.out.println("Opérateur non reconnu !");
                    break;
            }

            // Envoi du résultat
            oos.writeObject(resultat);
            System.out.println("Résultat envoyé au client : " + resultat);

            // Fermeture
            ois.close();
            oos.close();
            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
