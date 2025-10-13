
package serverPackage;

import java.net.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Server {

	public static void main(String[] args) {
		System.out.println("Je suis un serveur en attente la connexion d'un client ");
		try {
			ServerSocket serveur = new ServerSocket(5000);
			Socket Socket = serveur.accept();
			System.out.println("connecté");
			InputStream in = Socket.getInputStream();
			DataOutputStream out = new DataOutputStream(Socket.getOutputStream());
			int x = in.read();
			System.out.println("Serveur a reçu : " + x);
			int result = x * 5;
			out.writeInt(result);
			Socket.close();

		} catch (IOException e) {
			System.out.println(e.toString());
		}
		System.out.println("un client est connecté");

	}

}
