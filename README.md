```markdown
# 📚 Projet de Gestion de Bibliothèque

Une application de gestion de bibliothèque développée en **Java** avec **JavaFX** pour l’interface graphique et **MySQL** pour la persistance des données via **JDBC**.

---

## 🛠️ Fonctionnalités principales

### ✅ Côté Lecteur
- Affichage des livres disponibles.
- Envoi d'une demande d'emprunt.
- Visualisation des livres empruntés.

### ✅ Côté Bibliothécaire
- Ajout de nouveaux livres (Roman, Biographie, Science-Fiction, Magazine).
- Suppression de livres (avec sécurité si livre emprunté).
- Validation ou refus des demandes d’emprunt.
- Enregistrement du retour de livres.
- Visualisation des livres disponibles et empruntés.
- Application de sanctions.

### ✅ Côté Admin
- Gestion des bibliothécaires.
- Gestion avancée des livres.

---



---

## 🗃️ Base de données MySQL

### 📌 Tables principales :

- `livres` : stocke les livres avec leur disponibilité et type.
- `romans`, `biographies`, `science_fiction`, `magazines` : sous-tables spécifiques.
- `lecteurs`, `bibliothecaires`, `admins` : utilisateurs.
- `emprunts` : enregistrements des prêts.
- `demandes_emprunt` : demandes en attente, acceptées ou refusées.

### 🔗 Contraintes :

- Clé étrangère `emprunts.id_livre` → `livres.id`
- Clé étrangère `demandes_emprunt.id_livre` → `livres.id`
- Clé étrangère `demandes_emprunt.id_lecteur` → `lecteurs.id`

---

## 🧪 Technologies utilisées

- ☕ Java 17+
- 🎨 JavaFX (FXML)
- 🗃️ MySQL 8+
- 🔌 JDBC
- 🛠️ Gson (pour certains traitements JSON éventuels)

---

## 🚀 Lancement du projet

1. Assurez-vous que **MySQL** est installé et que la base `bibliotheque` est configurée avec les tables nécessaires.
2. Renseignez les informations de connexion à la base dans `DBConnection.java`.
3. Compilez et exécutez `Main.java` dans votre IDE (IntelliJ ou Eclipse).
4. L'interface JavaFX s’ouvrira avec les différentes fonctionnalités disponibles selon le rôle (lecteur, bibliothécaire, admin).

---

## ⚠️ Notes importantes

- Un livre ne peut pas être supprimé s’il est référencé dans la table `emprunts`.
- La suppression d’un livre entraîne aussi la suppression de ses demandes d’emprunt associées.
- L’historique des emprunts peut être conservé ou supprimé selon le paramétrage (`ON DELETE CASCADE`).

---



## 👨‍💻 Auteur

Développement réalisé par [joel / eliel].

---


