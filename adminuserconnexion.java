import java.util.Scanner;

public class adminuserconnexion {
    // Identifiants valides (simulation d'une base de données)
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Entrez votre nom d'utilisateur : ");
        String usernameInput = scanner.nextLine();
        
        System.out.print("Entrez votre mot de passe : ");
        String passwordInput = scanner.nextLine();
        
        // Vérification des identifiants
        if (authenticate(usernameInput, passwordInput)) {
            System.out.println("✅ Connexion réussie ! Bienvenue, " + USERNAME + ".");
        } else {
            System.out.println("❌ Identifiants incorrects. Veuillez réessayer.");
        }
        
        scanner.close() ;
    }
    
    // Méthode d'authentification
    private static boolean authenticate(String username, String password) {
        return USERNAME.equals(username) && PASSWORD.equals(password);
    }
}
/** Ajouter un Case et une boucle For pour pouvoir faire les options de connexion sur le site et faire une option de sauvegarde 
  en fichier.Txt + modification du ficher adminuserconnexion.java en connexion.java et deplacer le script ailleur. **/

