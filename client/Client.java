package client;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Crée un socket client et le connecte au serveur sur "localhost" et le port 1234
            Socket socket = new Socket("localhost", 1234);

            // Affiche un message pour indiquer que le client est connecté au serveur
            System.out.println("Je suis connecté");

         // les 2 methode getInputStream() et getOutputStream()sont 2 metode d'echange des donnes avec le server
    		InputStream is = socket.getInputStream();
    		OutputStream os = socket.getOutputStream();
    		String nb1;
    		String nb2;
    		String operation;
    		Scanner scanner = new Scanner(System.in);
    		System.out.println("Donner un nombre 1 : ");
    		nb1 = scanner.nextLine();
    		PrintWriter pw = new PrintWriter(os,true);
    		pw.println(nb1);
    		System.out.println("Donner un nombre 2 : ");
    		nb2 = scanner.nextLine();
    		pw.println(nb2);

            // Boucle pour s'assurer que l'opération saisie est valide (+, -, /, *)
            do {
                System.out.println("Donner l'opération (+, -, /, *) : ");
                operation = scanner.next();
            } while (!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/"));
            pw.println(operation);
            
            // Crée un lecteur de flux d'entrée pour lire la réponse du serveur
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            // Lit la réponse du serveur et affiche le résultat du calcul
            System.out.println(nb1 + " " + operation + " " + nb2 + " = " + br.readLine());

            // Ferme le socket client et le scanner
            socket.close();
            scanner.close();
        } catch (Exception e) {
            // En cas d'erreur, affiche la trace de l'exception
            e.printStackTrace();
        }
    }
}