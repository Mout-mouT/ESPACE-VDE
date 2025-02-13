import java.util.Scanner;
import java.util.HashMap;

public class créalog {
    // Simulation d'une base de données
    private static HashMap<String, String> users = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n1. Créer un compte");
            System.out.println("2. Se connecter");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne
            
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
                    return;
                default:
                    System.out.println("❌ Option invalide. Veuillez réessayer.");
            }
        }
    }

    // Méthode pour créer un compte
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
        System.out.println("✅ Compte créé avec succès !");
    }
    
    // Méthode pour se connecter
    private static boolean seConnecter(Scanner scanner) {
        for (int i = 0; i < 3; i++) { // Permet 3 tentatives
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
    
    // Méthode d'authentification
    private static boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
