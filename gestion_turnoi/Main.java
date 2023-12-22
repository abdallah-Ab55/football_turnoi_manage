import java.util.*;

public class Main {
    private static List<String> equipes = new ArrayList<>(
            Arrays.asList("Real Madrid", "Chealsea", "Barcelone", "Napoli", "Juvantus", "AC Milan"));
    private static List<String> heuresPossibles = new ArrayList<>(Arrays.asList("16:00", "16:30", "17:00", "19:00"));
    private static List<String> stades = Arrays.asList(" Aliens arena", "Maracana", "Camp-nou", "Bernabio");
    private static Map<String, List<String>> occupationStades = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (String stade : stades) {
            occupationStades.put(stade, new ArrayList<>());
        }
        System.out.println("Etes-vous un utilisateur ou un administrateur ? (entrez 'user' ou 'administrateur') :");
        String role = scanner.nextLine();

        if (role.equalsIgnoreCase("user")) {
            System.out.println("1. Se connecter");
            System.out.println("2. liste des equipes");
            System.out.println("3. Voir liste des Stades");
            System.out.println("4. Afficher la liste des matchs d'aujourd'hui");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.println("Connexion en tant qu'utilisateur :");
                    boolean loggedIn = false;
                    String username = "";
                    int pin = 0;

                    while (!loggedIn) {
                        System.out.println("Veuillez entrer votre nom :");
                        username = scanner.nextLine();

                        System.out
                                .println("Veuillez entrer votre code PIN (n'importe quel nombre pour se connecter) :");
                        try {
                            pin = scanner.nextInt();
                            scanner.nextLine();
                            loggedIn = true;
                            System.out.println("Connexion reussie !");
                        } catch (InputMismatchException e) {
                            System.out.println("Code PIN invalide. Veuillez entrer un nombre.");
                            scanner.nextLine();
                        }
                    }

                    // Après la connexion réussie, rediriger directement à la partie choix
                    afficherChoixUtilisateur(scanner);
                    break;
                case 2:
                    afficherEquipes();
                    break;
                case 3:
                    afficherStades();
                    break;
                case 4:
                    afficherListeMatchs();
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } else if (role.equalsIgnoreCase("administrateur")) {
            System.out.println("1. Gerer les heures de match");
            System.out.println("2. Gerer les équipes");

            int choix = scanner.nextInt();
            scanner.nextLine(); // pour consommer la nouvelle ligne après nextInt()

            switch (choix) {
                case 1:
                    gererHeuresMatch();
                    break;

                case 2:
                    gestionEquipes(scanner);
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } else {
            System.out.println("Role non reconnu.");
        }

        scanner.close();
    }

    public static void afficherEquipes() {
        System.out.println("Liste des Equipes :");
        for (String equipe : equipes) {
            System.out.println(equipe);
        }
    }

    public static void afficherStades() {
        System.out.println("Liste des stades :");
        for (String stade : stades) {
            System.out.println(stade);
        }
    }

    public static void afficherChoixUtilisateur(Scanner scanner) {
        // Afficher les choix pour l'utilisateur après la connexion
        System.out.println("1. Voir les heures de match");
        // Ajoutez d'autres options ici selon les besoins

        int choix = scanner.nextInt();
        scanner.nextLine(); // pour consommer la nouvelle ligne après nextInt()

        switch (choix) {
            case 1:
                System.out.println("Heures de match disponibles :");
                for (String heure : heuresPossibles) {
                    System.out.println(heure);
                }
                break;
            // Ajoutez d'autres cas ici selon les besoins
            default:
                System.out.println("Choix invalide.");
        }
    }

    public static void gestionEquipes(Scanner scanner) {
        boolean continuer = true;

        while (continuer) {
            System.out.println("1. Ajouter une Equipe");
            System.out.println("2. Supprimer une Equipe");
            System.out.println("3. Modifier une Equipe");
            System.out.println("4. Quitter");

            int action = scanner.nextInt();
            scanner.nextLine(); // consommer la nouvelle ligne après nextInt()

            switch (action) {
                case 1:
                    System.out.println("Entrez le nom de la nouvelle Equipe :");
                    String nouvelleEquipe = scanner.nextLine();
                    ajouterEquipe(nouvelleEquipe);
                    System.out.println("Equipe ajoutee avec succes : " + nouvelleEquipe);
                    continuer = demanderContinuation(scanner);
                    break;
                case 2:
                    System.out.println("Entrez le nom de l'equipe a supprimer :");
                    String equipeASupprimer = scanner.nextLine();
                    supprimerEquipe(equipeASupprimer);
                    System.out.println("Équipe supprimée avec succès : " + equipeASupprimer);
                    continuer = demanderContinuation(scanner);
                    break;
                case 3:
                    System.out.println("Entrez l'ancien nom de l'equipe :");
                    String ancienNomEquipe = scanner.nextLine();
                    System.out.println("Entrez le nouveau nom de l'equipe :");
                    String nouveauNomEquipe = scanner.nextLine();
                    modifierEquipe(ancienNomEquipe, nouveauNomEquipe);
                    System.out.println(
                            "Nom de l'equipe modifie avec succes : " + ancienNomEquipe + " -> " + nouveauNomEquipe);
                    continuer = demanderContinuation(scanner);
                    break;
                case 4:
                    continuer = scanner.hasNextLine();
                    break;
                default:
                    System.out.println("Action invalide.");
            }
        }
    }

    public static boolean demanderContinuation(Scanner scanner) {
        System.out.println("Voulez-vous quitter (entrez 'q') ou continuer (entrez 'c') ?");
        String reponse = scanner.nextLine();
        return reponse.equalsIgnoreCase("c");
    }

    public static void ajouterEquipe(String nouvelleEquipe) {
        equipes.add(nouvelleEquipe);
    }

    public static void supprimerEquipe(String equipeASupprimer) {
        equipes.remove(equipeASupprimer);
    }

    public static void modifierEquipe(String ancienNomEquipe, String nouveauNomEquipe) {
        if (equipes.contains(ancienNomEquipe)) {
            int index = equipes.indexOf(ancienNomEquipe);
            equipes.set(index, nouveauNomEquipe);
        } else {
            System.out.println("L'equipe specifiee n'existe pas dans le systeme.");
        }
    }

    public static void gererHeuresMatch() {
        System.out.println("1. Ajouter une heure de match");
        System.out.println("2. Supprimer une heure de match");
        System.out.println("3. Modifier une heure de match");
        System.out.println("4. Quitter");

        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            int action = scanner.nextInt();
            scanner.nextLine(); // consommer la nouvelle ligne après nextInt()

            switch (action) {
                case 1:
                    System.out.println("Entrez une nouvelle heure de match (au format HH:mm) :");
                    String nouvelleHeure = scanner.nextLine();
                    ajouterHeureMatch(nouvelleHeure);
                    System.out.println("Heure de match ajoutee avec succes : " + nouvelleHeure);

                    String choix;
                    do {
                        System.out.println("Voulez-vous ajouter une autre heure de match ? (oui/non) :");
                        choix = scanner.nextLine();
                    } while (!choix.equalsIgnoreCase("oui") && !choix.equalsIgnoreCase("non"));

                    if (choix.equalsIgnoreCase("non")) {
                        continuer = false;
                    }
                    break;
                case 2:
                    System.out.println("Entrez l'heure de match à supprimer :");
                    String heureASupprimer = scanner.nextLine();
                    supprimerHeureMatch(heureASupprimer);
                    System.out.println("Heure de match supprimee avec succes : " + heureASupprimer);
                    break;
                case 3:
                    System.out.println("Entrez l'ancienne heure de match :");
                    String ancienneHeure = scanner.nextLine();
                    System.out.println("Entrez la nouvelle heure de match :");
                    String nouvelleHeureModifiee = scanner.nextLine();
                    modifierHeureMatch(ancienneHeure, nouvelleHeureModifiee);
                    System.out.println(
                            "Heure de match modifiee avec succes : " + ancienneHeure + " -> " + nouvelleHeureModifiee);
                    break;
                case 4:
                    continuer = false;
                    break;
                default:
                    System.out.println("Action invalide.");
            }
        }

        scanner.close();
    }

    public static void afficherListeMatchs() {
        System.out.println("Liste aleatoire de matchs :");
        Random random = new Random();
        Set<String> equipesJouees = new HashSet<>();

        for (int i = 1; i <= 5; i++) { // Afficher 5 matchs aléatoires
            String equipe1;
            String equipe2;
            String stade;
            String heure;

            do {
                equipe1 = equipes.get(random.nextInt(equipes.size()));
                equipe2 = equipes.get(random.nextInt(equipes.size()));
            } while (equipe1.equals(equipe2) || equipesJouees.contains(equipe1) || equipesJouees.contains(equipe2));

            equipesJouees.add(equipe1);
            equipesJouees.add(equipe2);

            // Sélection d'un stade et d'une heure disponibles
            boolean matchValide = false;
            do {
                stade = stades.get(random.nextInt(stades.size()));
                heure = heuresPossibles.get(random.nextInt(heuresPossibles.size()));

                if (!occupationStades.get(stade).contains(heure)) {
                    matchValide = true;
                    occupationStades.get(stade).add(heure); // Marquer le stade comme occupé à cette heure
                }
            } while (!matchValide);

            System.out.println("Match " + i + " entre " + equipe1 + " et " + equipe2 + " à " + heure + " au " + stade);
        }
    }

    public static void ajouterHeureMatch(String nouvelleHeure) {
        heuresPossibles.add(nouvelleHeure);
    }

    public static void supprimerHeureMatch(String heureASupprimer) {
        if (heuresPossibles.contains(heureASupprimer)) {
            heuresPossibles.remove(heureASupprimer);
        } else {
            System.out.println("L'heure de match specifiee n'existe pas dans le systeme.");
        }
    }

    public static void modifierHeureMatch(String ancienneHeure, String nouvelleHeure) {
        if (heuresPossibles.contains(ancienneHeure)) {
            int index = heuresPossibles.indexOf(ancienneHeure);
            heuresPossibles.set(index, nouvelleHeure);
        } else {
            System.out.println("L'heure de match specifiee n'existe pas dans le systeme.");
        }
    }
}
