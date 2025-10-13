package clientPackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		System.out.println("Je suis un client pas encore connecté...");
		try {
			Socket s = new Socket("localhost", 5000);
			System.out.println("connecté");
			System.out.println("saisir");
			Scanner input = new Scanner(System.in);
			int number = input.nextInt();
			OutputStream out = s.getOutputStream();
			out.write(number);

			InputStream in = s.getInputStream();
			int result = in.read();
			System.out.println("le resultas " + result);

		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

}
