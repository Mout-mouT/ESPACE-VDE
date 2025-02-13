package vde;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestionnaireContact gestionnaire = new GestionnaireContact(); // Charger les contacts existants

        while (true) {
            System.out.println("\n===== Gestionnaire de Contacts =====");
            System.out.println("1. Ajouter un contact");
            System.out.println("2. Afficher tous les contacts");
            System.out.println("3. Rechercher un contact par nom");
            System.out.println("4. Modifier un contact");
            System.out.println("5. Supprimer un contact");
            System.out.println("6. Quitter");
            System.out.print("Choisissez une option : ");

            // Vérification de l'entrée utilisateur
            int choix;
            try {
                choix = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide !");
                continue;
            }

            switch (choix) {
                case 1:
                    ajouterContact(scanner, gestionnaire);
                    break;
                case 2:
                    gestionnaire.afficherContacts();
                    break;
                case 3:
                    rechercherContact(scanner, gestionnaire);
                    break;
                case 4:
                    modifierContact(scanner, gestionnaire);
                    break;
                case 5:
                    supprimerContact(scanner, gestionnaire);
                    break;
                case 6:
                    System.out.println("Programme terminé. Au revoir !");
                    scanner.close();
                    return;
                default:
                    System.out.println("Option invalide. Veuillez choisir une option entre 1 et 6.");
            }
        }
    }

    // 🟢 Méthode pour ajouter un contact
    private static void ajouterContact(Scanner scanner, GestionnaireContact gestionnaire) {
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine();

        Contact contact = new Contact(nom, prenom, email, telephone);
        gestionnaire.ajouterContact(contact);
    }

    // 🔍 Méthode pour rechercher un contact
    private static void rechercherContact(Scanner scanner, GestionnaireContact gestionnaire) {
        System.out.print("Nom du contact à rechercher : ");
        String nomRecherche = scanner.nextLine();
        Contact contactTrouve = gestionnaire.rechercherContact(nomRecherche);

        if (contactTrouve != null) {
            System.out.println("Contact trouvé : " + contactTrouve);
        } else {
            System.out.println("Aucun contact trouvé avec ce nom.");
        }
    }

    // ✏️ Méthode pour modifier un contact
    private static void modifierContact(Scanner scanner, GestionnaireContact gestionnaire) {
        System.out.print("Nom du contact à modifier : ");
        String nomModif = scanner.nextLine();
        System.out.print("Nouveau prénom : ");
        String nouveauPrenom = scanner.nextLine();
        System.out.print("Nouvel email : ");
        String nouvelEmail = scanner.nextLine();
        System.out.print("Nouveau téléphone : ");
        String nouveauTelephone = scanner.nextLine();

        gestionnaire.modifierContact(nomModif, nouveauPrenom, nouvelEmail, nouveauTelephone);
    }

    // 🗑️ Méthode pour supprimer un contact
    private static void supprimerContact(Scanner scanner, GestionnaireContact gestionnaire) {
        System.out.print("Nom du contact à supprimer : ");
        String nomSuppr = scanner.nextLine();
        gestionnaire.supprimerContact(nomSuppr);
    }
}
