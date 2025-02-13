package com.test.hello;

import java.io.*;
import java.util.*;

public class sauvegardecompte {
    private static final String FILE_NAME = "users.txt";
    private static HashMap<String, String> users = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        chargerUtilisateurs();
        
        while (true) {
            System.out.println("\n1. Créer un compte");
            System.out.println("2. Se connecter");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choix) {
                case 1:
                    creerCompte(scanner);
                    break;
                case 2:
                    if (seConnecter(scanner)) {
                        System.out.println("✅ Connexion réussie !");
                        return;
                    }
                    break;
                case 3:
                    System.out.println("👋 Au revoir !");
                    scanner.close();
                    sauvegarderUtilisateurs();
                    return;
                default:
                    System.out.println("❌ Option invalide. Veuillez réessayer.");
            }
        }
    }

    private static void creerCompte(Scanner scanner) {
        System.out.print("Entrez un nom d'utilisateur : ");
        String username = scanner.nextLine();
        
        if (users.containsKey(username)) {
            System.out.println("⚠️ Ce nom d'utilisateur existe déjà. Essayez-en un autre.");
            return;
        }
        
        System.out.print("Entrez un mot de passe : ");
        String password = scanner.nextLine();
        users.put(username, password);
        sauvegarderUtilisateurs();
        System.out.println("✅ Compte créé avec succès !");
    }
    
    private static boolean seConnecter(Scanner scanner) {
        for (int i = 0; i < 3; i++) {
            System.out.print("Entrez votre nom d'utilisateur : ");
            String username = scanner.nextLine();
            
            System.out.print("Entrez votre mot de passe : ");
            String password = scanner.nextLine();
            
            if (authenticate(username, password)) {
                return true;
            } else {
                System.out.println("❌ Identifiants incorrects. Tentative " + (i + 1) + "/3");
            }
        }
        System.out.println("⛔ Trop de tentatives échouées. Retour au menu principal.");
        return false;
    }
    
    private static boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
    
    private static void sauvegarderUtilisateurs() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Erreur lors de la sauvegarde des utilisateurs : " + e.getMessage());
        }
    }
    
    private static void chargerUtilisateurs() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Erreur lors du chargement des utilisateurs : " + e.getMessage());
        }
    }
}
