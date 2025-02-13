package vde;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class gerstionfichiertxte {
    private List<Contact> contacts; // Liste de contacts
    private String fichierContacts = "contacts.txt"; // Nom du fichier texte

    // Constructeur
    public gerstionfichiertxte() {
        this.contacts = new ArrayList<>();
        chargerContacts(); // Charger les contacts du fichier au démarrage
    }

    // Méthode pour ajouter un contact
    public void ajouterContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact ajouté avec succès !");
        enregistrerContacts(); // Enregistrer les contacts dans le fichier après l'ajout
    }

    // Méthode pour afficher tous les contacts
    public void afficherContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Aucun contact enregistré.");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    // Méthode pour rechercher un contact par nom
    public Contact rechercherContact(String nom) {
        for (Contact contact : contacts) {
            if (contact.getNom().equalsIgnoreCase(nom)) {
                return contact;
            }
        }
        return null; // Retourne null si aucun contact trouvé
    }

    // Méthode pour modifier un contact existant
    public boolean modifierContact(String nom, String nouveauPrenom, String nouvelEmail, String nouveauTelephone) {
        Contact contact = rechercherContact(nom);
        if (contact != null) {
            contact.setPrenom(nouveauPrenom);
            contact.setEmail(nouvelEmail);
            contact.setTelephone(nouveauTelephone);
            System.out.println("Contact modifié avec succès !");
            enregistrerContacts(); // Enregistrer les contacts dans le fichier après la modification
            return true;
        } else {
            System.out.println("Contact non trouvé !");
            return false;
        }
    }

    // Méthode pour supprimer un contact par nom
    public boolean supprimerContact(String nom) {
        Contact contact = rechercherContact(nom);
        if (contact != null) {
            contacts.remove(contact);
            System.out.println("Contact supprimé avec succès !");
            enregistrerContacts(); // Enregistrer les contacts dans le fichier après la suppression
            return true;
        } else {
            System.out.println("Contact non trouvé !");
            return false;
        }
    }

    // Méthode pour enregistrer les contacts dans le fichier
    private void enregistrerContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierContacts))) {
            for (Contact contact : contacts) {
                writer.write(contact.getNom() + "," + contact.getPrenom() + "," + contact.getEmail() + "," + contact.getTelephone());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'enregistrement des contacts : " + e.getMessage());
        }
    }

    // Méthode pour charger les contacts depuis le fichier
    private void chargerContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fichierContacts))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] infosContact = ligne.split(",");
                if (infosContact.length == 4) {
                    Contact contact = new Contact(infosContact[0], infosContact[1], infosContact[2], infosContact[3]);
                    contacts.add(contact);
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des contacts : " + e.getMessage());
        }
    }

    // Programme principal pour tester les fonctionnalités
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        gerstionfichiertxte gestionnaire = new gerstionfichiertxte();

        while (true) {
            System.out.println("\nMenu :");
            System.out.println("1. Ajouter un contact");
            System.out.println("2. Afficher tous les contacts");
            System.out.println("3. Rechercher un contact par nom");
            System.out.println("4. Modifier un contact");
            System.out.println("5. Supprimer un contact");
            System.out.println("6. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Email : ");
                    String email = scanner.nextLine();
                    System.out.print("Téléphone : ");
                    String telephone = scanner.nextLine();
                    gestionnaire.ajouterContact(new Contact(nom, prenom, email, telephone));
                    break;
                case 2:
                    gestionnaire.afficherContacts();
                    break;
                case 3:
                    System.out.print("Nom du contact à rechercher : ");
                    String nomRecherche = scanner.nextLine();
                    Contact contactTrouve = gestionnaire.rechercherContact(nomRecherche);
                    if (contactTrouve != null) {
                        System.out.println("Contact trouvé : " + contactTrouve);
                    } else {
                        System.out.println("Contact non trouvé.");
                    }
                    break;
                case 4:
                    System.out.print("Nom du contact à modifier : ");
                    String nomModifier = scanner.nextLine();
                    System.out.print("Nouveau prénom : ");
                    String nouveauPrenom = scanner.nextLine();
                    System.out.print("Nouvel email : ");
                    String nouvelEmail = scanner.nextLine();
                    System.out.print("Nouveau téléphone : ");
                    String nouveauTelephone = scanner.nextLine();
                    gestionnaire.modifierContact(nomModifier, nouveauPrenom, nouvelEmail, nouveauTelephone);
                    break;
                case 5:
                    System.out.print("Nom du contact à supprimer : ");
                    String nomSupprimer = scanner.nextLine();
                    gestionnaire.supprimerContact(nomSupprimer);
                    break;
                case 6:
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;
                default:
                    System.out.println("Option invalide.");
            }
        }
    }
}

// Classe Contact (tu peux la garder dans un fichier séparé si tu veux)
class Econtacts {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    public Econtacts(String nom, String prenom, String email, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Contact{" +
               "nom='" + nom + '\'' +
               ", prenom='" + prenom + '\'' +
               ", email='" + email + '\'' +
               ", telephone='" + telephone + '\'' +
               '}';
    }
}
