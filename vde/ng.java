package vde;

import java.util.ArrayList;
import java.util.List;

public class ng {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("hello");

        // Créer un gestionnaire de contacts
        GestionContact gestionnaire = new GestionContact();

        // Ajouter des contacts
        gestionnaire.ajouterContact(new Contact("Dupont", "Jean", "jean.dupont@example.com", "0123456789"));
        gestionnaire.ajouterContact(new Contact("Martin", "Sophie", "sophie.martin@example.com", "0987654321"));

        // Afficher tous les contacts
        gestionnaire.afficherContacts();
    }

    static class Contact {
        private String nom;
        private String prenom;
        private String email;
        private String telephone;

        // Constructeur
        public Contact(String nom, String prenom, String email, String telephone) {
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.telephone = telephone;
        }

        // Getters et setters
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

        // Méthode toString() pour une représentation textuelle
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

    static class GestionContact {
        private List<Contact> contacts;

        // Constructeur
        public GestionContact() {
            contacts = new ArrayList<>();
        }

        // Méthode pour ajouter un contact
        public void ajouterContact(Contact contact) {
            contacts.add(contact);
        }

        // Méthode pour afficher tous les contacts
        public void afficherContacts() {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }
}
