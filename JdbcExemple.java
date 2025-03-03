package tp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;

public class JdbcExemple {
    private static final String URL = "jdbc:mysql://localhost:3306/ma_base?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "test";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ Driver MySQL chargé avec succès !");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Erreur : Driver MySQL introuvable !");
            e.printStackTrace();
        }
    }

    // Méthode pour obtenir une connexion à la base de données
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("✅ Connexion à MySQL réussie !");
        return conn;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1️⃣ Mode Menu Simple");
            System.out.println("2️⃣ Mode Commande SQL");
            System.out.println("3️⃣ Menu MySQL");
            System.out.println("0️⃣ Quitter");
            System.out.print("👉 Choix : ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("❌ Entrée invalide ! Veuillez entrer un nombre.");
                scanner.next();
                continue;
            }
            
            int choix = scanner.nextInt();
            scanner.nextLine();
            
            switch (choix) {
                case 1 -> afficherMenuSimple(scanner);
                case 2 -> executerCommandeSQL(scanner);
                case 3 -> afficherMenuMySQL(scanner);
                case 0 -> {
                    System.out.println("👋 Au revoir !");
                    scanner.close();
                    return;
                }
                default -> System.out.println("❌ Choix invalide !");
            }
        }
    }

    public static void afficherMenuMySQL(Scanner scanner) {
        while (true) {
            System.out.println("\n===== MENU MySQL =====");
            System.out.println("1️⃣ Afficher la base de données");
            System.out.println("2️⃣ Sauvegarder les données de la base");
            System.out.println("3️⃣ Restaurer les données de la base");
            System.out.println("4️⃣ Supprimer les données de la base");
            System.out.println("5️⃣ Créer une base de données");
            System.out.println("6️⃣ Gestion des tables");
            System.out.println("0️⃣ Retour");
            System.out.print("👉 Choix : ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("❌ Entrée invalide !");
                scanner.next();
                continue;
            }
            
            int choix = scanner.nextInt();
            scanner.nextLine();
            
            switch (choix) {
                case 1 -> System.out.println("Fonction d'affichage de la base de données non implémentée.");
                case 2 -> {
                    System.out.print("📂 Chemin du fichier de sauvegarde : ");
                    String chemin = scanner.nextLine();
                    sauvegarderBaseDeDonnees(chemin);
                }
                case 3 -> {
                    System.out.print("📂 Chemin du fichier de restauration : ");
                    String chemin = scanner.nextLine();
                    restaurerBaseDeDonnees(chemin);
                }
                case 4 -> System.out.println("Fonction de suppression de la base de données non implémentée.");
                case 5 -> System.out.println("Fonction de création de la base de données non implémentée.");
                case 6 -> afficherMenuGestionTables(scanner);
                case 0 -> { return; }
                default -> System.out.println("❌ Choix invalide !");
            }
        }
    }

    public static void afficherMenuSimple(Scanner scanner) {
        while (true) {
            System.out.println("\n===== MENU SIMPLE =====");
            System.out.println("1️⃣ Initialiser la base de données");
            System.out.println("2️⃣ Sauvegarder les tables");
            System.out.println("3️⃣ Restaurer les tables");
            System.out.println("4️⃣ Gestion des tables");
            System.out.println("0️⃣ Retour");
            System.out.print("👉 Choix : ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("❌ Entrée invalide !");
                scanner.next();
                continue;
            }
            
            int choix = scanner.nextInt();
            scanner.nextLine();
            
            switch (choix) {
                case 1 -> initialiserBaseDeDonnees();
                case 2 -> {
                    System.out.print("📂 Chemin du fichier de sauvegarde : ");
                    String chemin = scanner.nextLine();
                    sauvegarderBaseDeDonnees(chemin);
                }
                case 3 -> {
                    System.out.print("📂 Chemin du fichier de restauration : ");
                    String chemin = scanner.nextLine();
                    restaurerBaseDeDonnees(chemin);
                }
                case 4 -> afficherMenuGestionTables(scanner);
                case 0 -> { return; }
                default -> System.out.println("❌ Choix invalide !");
            }
        }
    }

    public static void afficherMenuGestionTables(Scanner scanner) {
        while (true) {
            System.out.println("\n===== GESTION DES TABLES =====");
            System.out.println("1️⃣ Ajouter un utilisateur");
            System.out.println("2️⃣ Ajouter un contact");
            System.out.println("3️⃣ Afficher les utilisateurs");
            System.out.println("4️⃣ Afficher les contacts");
            System.out.println("5️⃣ Supprimer un utilisateur");
            System.out.println("6️⃣ Supprimer un contact");
            System.out.println("7️⃣ Mettre à jour un contact");
            System.out.println("0️⃣ Retour");
            System.out.print("👉 Choix : ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("❌ Entrée invalide !");
                scanner.next();
                continue;
            }
            
            int choix = scanner.nextInt();
            scanner.nextLine();
            
            switch (choix) {
                case 1 -> ajouterUtilisateur(scanner);
                case 2 -> ajouterContact(scanner);
                case 3 -> afficherUtilisateurs();
                case 4 -> afficherContacts();
                case 5 -> supprimerUtilisateur(scanner);
                case 6 -> supprimerContact(scanner);
                case 7 -> mettreAJourContact(scanner);
                case 0 -> { return; }
                default -> System.out.println("❌ Choix invalide !");
            }
        }
    }

    public static void initialiserBaseDeDonnees() {
        creerTableUtilisateurs();
        creerTableContacts();
    }

    public static void creerTableUtilisateurs() {
        String sql = "CREATE TABLE IF NOT EXISTS utilisateurs (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "nom VARCHAR(50) NOT NULL, " +
                     "email VARCHAR(100) NOT NULL UNIQUE, " +
                     "mot_de_passe VARCHAR(255) NOT NULL)";
        executerRequete(sql, "utilisateurs");
    }

    public static void creerTableContacts() {
        String sql = "CREATE TABLE IF NOT EXISTS contacts (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "nom VARCHAR(50) NOT NULL, " +
                     "prenom VARCHAR(50) NOT NULL, " +
                     "email VARCHAR(100) NOT NULL UNIQUE, " +
                     "telephone VARCHAR(15) NOT NULL)";
        executerRequete(sql, "contacts");
    }

    private static void executerRequete(String sql, String tableName) {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("✅ Table '" + tableName + "' vérifiée/créée avec succès !");
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la création de la table '" + tableName + "' : " + e.getMessage());
        }
    }

    public static void executerCommandeSQL(Scanner scanner) {
        System.out.println("Entrez une commande SQL (ou 'exit' pour quitter) :");
        while (true) {
            System.out.print("SQL> ");
            String sql = scanner.nextLine().trim();
            if (sql.equalsIgnoreCase("exit")) {
                break;
            }
            try (Connection conn = getConnection();
                 Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("✅ Commande exécutée avec succès !");
            } catch (SQLException e) {
                System.err.println("❌ Erreur SQL : " + e.getMessage());
            }
        }
    }

    public static void sauvegarderBaseDeDonnees(String cheminFichier) {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW TABLES");
             PrintWriter writer = new PrintWriter(new FileWriter(cheminFichier))) {
            while (rs.next()) {
                String table = rs.getString(1);
                writer.println("DROP TABLE IF EXISTS " + table + ";");
                try (ResultSet rsCreate = stmt.executeQuery("SHOW CREATE TABLE " + table)) {
                    if (rsCreate.next()) {
                        writer.println(rsCreate.getString(2) + ";");
                    }
                }
                try (ResultSet rsData = stmt.executeQuery("SELECT * FROM " + table)) {
                    ResultSetMetaData metaData = rsData.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    while (rsData.next()) {
                        StringBuilder values = new StringBuilder("INSERT INTO " + table + " VALUES (");
                        for (int i = 1; i <= columnCount; i++) {
                            String value = rsData.getString(i);
                            values.append(value == null ? "NULL" : "'" + value.replace("'", "\\'") + "'");
                            if (i < columnCount) values.append(", ");
                        }
                        values.append(");");
                        writer.println(values);
                    }
                }
            }
            System.out.println("✅ Sauvegarde effectuée avec succès dans " + cheminFichier);
        } catch (SQLException | IOException e) {
            System.err.println("❌ Erreur lors de la sauvegarde de la base de données : " + e.getMessage());
        }
    }

    public static void restaurerBaseDeDonnees(String cheminFichier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier));
             Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    stmt.execute(line);
                }
            }
            System.out.println("✅ Restauration effectuée avec succès depuis " + cheminFichier);
        } catch (SQLException | IOException e) {
            System.err.println("❌ Erreur lors de la restauration de la base de données : " + e.getMessage());
        }
    }

    public static void ajouterUtilisateur(Scanner scanner) {
        System.out.print("📝 Nom : ");
        String nom = scanner.nextLine();
        System.out.print("📧 Email : ");
        String email = scanner.nextLine();
        System.out.print("🔒 Mot de passe : ");
        String mdp = scanner.nextLine();
     
        
        // Hachage du mot de passe avec BCrypt
        String hashedPassword = BCrypt.hashpw(mdp, BCrypt.gensalt());
        String sql = "INSERT INTO utilisateurs (nom, email, mot_de_passe) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, email);
            pstmt.setString(3, hashedPassword);
            pstmt.executeUpdate();
            System.out.println("✅ Utilisateur ajouté avec succès !");
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }

    public static void ajouterContact(Scanner scanner) {
        System.out.print("📝 Nom : ");
        String nom = scanner.nextLine();
        System.out.print("📝 Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("📧 Email : ");
        String email = scanner.nextLine();
        System.out.print("📞 Téléphone : ");
        String telephone = scanner.nextLine();
        String sql = "INSERT INTO contacts (nom, prenom, email, telephone) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            pstmt.setString(3, email);
            pstmt.setString(4, telephone);
            pstmt.executeUpdate();
            System.out.println("✅ Contact ajouté avec succès !");
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'ajout du contact : " + e.getMessage());
        }
    }

    public static void afficherUtilisateurs() {
        String sql = "SELECT * FROM utilisateurs";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n📜 Liste des utilisateurs :");
            while (rs.next()) {
                System.out.println("🆔 ID: " + rs.getInt("id") + " | 👤 Nom: " + rs.getString("nom")
                        + " | 📧 Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'affichage des utilisateurs : " + e.getMessage());
        }
    }

    public static void afficherContacts() {
        String sql = "SELECT * FROM contacts";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n📜 Liste des contacts :");
            while (rs.next()) {
                System.out.println("🆔 ID: " + rs.getInt("id") + " | 👤 Nom: " + rs.getString("nom")
                        + " | 📧 Email: " + rs.getString("email") + " | 📞 Téléphone: " + rs.getString("telephone"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'affichage des contacts : " + e.getMessage());
        }
    }

    public static void supprimerUtilisateur(Scanner scanner) {
        System.out.print("🆔 ID de l'utilisateur à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        String sql = "DELETE FROM utilisateurs WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("✅ Utilisateur supprimé !");
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
        }
    }

    public static void supprimerContact(Scanner scanner) {
        System.out.print("🆔 ID du contact à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("✅ Contact supprimé !");
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la suppression du contact : " + e.getMessage());
        }
    }

    public static void mettreAJourContact(Scanner scanner) {
        System.out.print("🆔 ID du contact à mettre à jour : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("📞 Nouveau téléphone : ");
        String tel = scanner.nextLine();
        String sql = "UPDATE contacts SET telephone = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tel);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("✅ Contact mis à jour !");
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la mise à jour du contact : " + e.getMessage());
        }
    }
}


public class DatabaseBackup{

    public static void backupDatabase(String dbName, String user, String password, String backupFilePath) {
        // Commande mysqldump
        String command = String.format("mysqldump -u %s -p%s %s > %s", user, password, dbName, backupFilePath);

        try {
            // Exécuter la commande
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            System.out.println("Sauvegarde de la base de données effectuée avec succès dans le fichier : " + backupFilePath);
        } catch (Exception e) {
            System.err.println("Erreur lors de la sauvegarde de la base de données : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String dbName = "ma_base"; // Nom de votre base de données
        String user = "root"; // Nom d'utilisateur MySQL
        String password = "test"; // Mot de passe MySQL
        String backupFilePath = "sauvegarde.sql"; // Chemin du fichier de sauvegarde

        backupDatabase(dbName, user, password, backupFilePath);
    }
}
public class DatabaseDisplay {

    public static void displayDatabase(String dbName, String user, String password) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Établir la connexion
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, user, password);
            statement = connection.createStatement();

            // Exécuter une requête pour récupérer les données
            String query = "SELECT * FROM votre_table"; // Remplacez 'votre_table' par le nom de votre table
            resultSet = statement.executeQuery(query);

            // Afficher les données
            while (resultSet.next()) {
                // Supposons que votre table a deux colonnes : id et nom
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                System.out.println("ID: " + id + ", Nom: " + nom);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'affichage des données : " + e.getMessage());
        } finally {
            // Fermer les ressources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String dbName = "ma_base"; // Nom de votre base de données
        String user = "root"; // Nom d'utilisateur MySQL
        String password = "test"; // Mot de passe MySQL

        displayDatabase(dbName, user, password);
    }
}
