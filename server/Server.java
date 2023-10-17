
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // Crée un serveur socket écoutant sur le port 1234
            ServerSocket serverSocket = new ServerSocket(1234);

            // Affiche un message pour indiquer que le serveur est en attente de connexions
            System.out.println("J'attends un client");

            // Attend la connexion d'un client et crée un socket pour communiquer avec lui
            Socket socket = serverSocket.accept();

            // Affiche un message pour indiquer que le client est connecté
            System.out.println("Un client est connecté");

            // Configure les flux d'entrée et de sortie pour la communication avec le client
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // Déclaration des variables pour les opérandes et le résultat
            int op1, op2, res = 0;
            String operation;

            // Crée un lecteur de flux d'entrée pour lire les données envoyées par le client
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            // Lit les opérandes et l'opération envoyés par le client
            op1 = Integer.parseInt(br.readLine());
            op2 = Integer.parseInt(br.readLine());
            operation = br.readLine();

            // Effectue le calcul en fonction de l'opération
            switch (operation) {
                case "+":
                    res = op1 + op2;
                    break;
                case "-":
                    res = op1 - op2;
                    break;
                case "/":
                    res = op1 / op2;
                    break;
                case "*":
                    res = op1 * op2;
                    break;
            }
            
            System.out.println(res);
            // Crée un écrivain de flux de sortie pour envoyer la réponse au client
            PrintWriter pw = new PrintWriter(os, true);
            pw.println(res);

            // Ferme le socket et le serveur socket
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            // En cas d'erreur, affiche la trace de l'exception
            e.printStackTrace();
        }
    }
}