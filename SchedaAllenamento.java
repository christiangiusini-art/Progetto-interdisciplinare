

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SchedaAllenamento {
    private String nomeScheda;
    private String obiettivo;
    private List<Esercizio> esercizi;
    private Utente utente;

    // Max esercizi per scheda (pubblico per la GUI)
    public static final int MAX_ESERCIZI = 10;

    // 10 esercizi precaricati
    private static Esercizio[] eserciziDisponibili = new Esercizio[10];

    // Inizializzazione statica dei 10 esercizi
    static {
        eserciziDisponibili[0] = new Esercizio("Panca Piana", "Petto", 4, 10, 90);
        eserciziDisponibili[1] = new Esercizio("Squat", "Gambe", 4, 8, 120);
        eserciziDisponibili[2] = new Esercizio("Stacco da Terra", "Schiena", 4, 6, 150);
        eserciziDisponibili[3] = new Esercizio("Trazioni", "Dorsali", 3, 10, 90);
        eserciziDisponibili[4] = new Esercizio("Military Press", "Spalle", 3, 10, 90);
        eserciziDisponibili[5] = new Esercizio("Curl Bicipiti", "Braccia", 3, 12, 60);
        eserciziDisponibili[6] = new Esercizio("French Press", "Tricipiti", 3, 12, 60);
        eserciziDisponibili[7] = new Esercizio("Leg Press", "Gambe", 4, 12, 90);
        eserciziDisponibili[8] = new Esercizio("Crunch", "Addominali", 3, 20, 45);
        eserciziDisponibili[9] = new Esercizio("Affondi", "Gambe", 3, 12, 60);
    }

    // Costruttore di default
    public SchedaAllenamento() {
        this.esercizi = new ArrayList<>();
    }

    // Costruttore parametrizzato
    public SchedaAllenamento(String nomeScheda, String obiettivo, Utente utente) {
        this.nomeScheda = nomeScheda;
        this.obiettivo = obiettivo;
        this.utente = utente;
        this.esercizi = new ArrayList<>();
    }

    // Getters e Setters
    public String getNomeScheda() { return nomeScheda; }
    public void setNomeScheda(String nomeScheda) { this.nomeScheda = nomeScheda; }
    public String getObiettivo() { return obiettivo; }
    public void setObiettivo(String obiettivo) { this.obiettivo = obiettivo; }
    
    // Ritorna la lista di esercizi (usato dalla GUI)
    public List<Esercizio> getEsercizi() { return esercizi; }
    
    public int getNumeroEsercizi() { return esercizi.size(); }
    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }
    public static Esercizio[] getEserciziDisponibili() { return eserciziDisponibili; }

    // Aggiunge un esercizio alla lista (ritorna boolean per la GUI)
    public boolean aggiungiEsercizio(Esercizio es) {
        if (esercizi.size() < MAX_ESERCIZI) {
            esercizi.add(es);
            return true;
        }
        return false;
    }

    // Rimuove un esercizio per indice
    public void rimuoviEsercizio(int indice) {
        if (indice >= 0 && indice < esercizi.size()) {
            esercizi.remove(indice);
        }
    }

    // Svuota la scheda (usato dalla GUI)
    public void svuota() {
        esercizi.clear();
        this.nomeScheda = null;
        this.obiettivo = null;
    }

    // Visualizza esercizi disponibili
    public static void mostraEserciziDisponibili() {
        System.out.println("\n========== ESERCIZI DISPONIBILI ==========");
        for (int i = 0; i < eserciziDisponibili.length; i++) {
            System.out.println((i + 1) + ". " + eserciziDisponibili[i]);
        }
        System.out.println("==========================================\n");
    }

    // Creazione scheda
    public void creaScheda(Scanner scanner) {
        System.out.println("\n========== CREAZIONE NUOVA SCHEDA ==========");
        
        scanner.nextLine();
        
        System.out.print("Inserisci il nome della scheda: ");
        this.nomeScheda = scanner.nextLine();

        System.out.println("Scegli l'obiettivo:");
        System.out.println("1. Massa Muscolare");
        System.out.println("2. Dimagrimento");
        System.out.println("3. Tonificazione");
        System.out.println("4. Forza");
        System.out.print("Scelta: ");
        
        int sceltaObiettivo = scanner.nextInt();
        switch (sceltaObiettivo) {
            case 1: this.obiettivo = "Massa Muscolare"; break;
            case 2: this.obiettivo = "Dimagrimento"; break;
            case 3: this.obiettivo = "Tonificazione"; break;
            case 4: this.obiettivo = "Forza"; break;
            default: this.obiettivo = "Generale"; break;
        }

        mostraEserciziDisponibili();
        
        String continua;
        do {
            System.out.print("Inserisci il numero dell'esercizio da aggiungere (1-10): ");
            int scelta = scanner.nextInt();
            
            if (scelta >= 1 && scelta <= 10) {
                Esercizio esercizioScelto = eserciziDisponibili[scelta - 1];
                
                System.out.print("Vuoi personalizzare serie e ripetizioni? (s/n): ");
                scanner.nextLine();
                String personalizza = scanner.nextLine();
                
                if (personalizza.equalsIgnoreCase("s")) {
                    System.out.print("Numero di serie: ");
                    int serie = scanner.nextInt();
                    System.out.print("Numero di ripetizioni: ");
                    int ripetizioni = scanner.nextInt();
                    System.out.print("Tempo di recupero (secondi): ");
                    int recupero = scanner.nextInt();
                    
                    Esercizio esercizioPersonalizzato = new Esercizio(
                        esercizioScelto.getNome(),
                        esercizioScelto.getGruppoMuscolare(),
                        serie,
                        ripetizioni,
                        recupero
                    );
                    aggiungiEsercizio(esercizioPersonalizzato);
                } else {
                    aggiungiEsercizio(esercizioScelto);
                }
                
                System.out.println("Esercizio aggiunto!");
            } else {
                System.out.println("Scelta non valida!");
            }
            
            System.out.print("Vuoi aggiungere un altro esercizio? (s/n): ");
            scanner.nextLine();
            continua = scanner.nextLine();
            
        } while (continua.equalsIgnoreCase("s"));
        
        System.out.println("\nScheda creata con successo!");
    }

    // Visualizzazione scheda
    public void visualizzaScheda() {
        if (this.nomeScheda == null || esercizi.isEmpty()) {
            System.out.println("\nNessuna scheda presente. Crea prima una scheda!");
            return;
        }
        
        System.out.println("\n==============================================");
        System.out.println("         SCHEDA ALLENAMENTO                   ");
        System.out.println("==============================================");
        System.out.println("Nome: " + nomeScheda);
        System.out.println("Obiettivo: " + obiettivo);
        if (utente != null) {
            System.out.println("Utente: " + utente.getNome() + " " + utente.getCognome());
        }
        System.out.println("----------------------------------------------");
        System.out.println("ESERCIZI:");
        
        for (int i = 0; i < esercizi.size(); i++) {
            System.out.println((i + 1) + ". " + esercizi.get(i));
        }
        
        System.out.println("==============================================\n");
    }

    // Modifica scheda
    public void modificaScheda(Scanner scanner) {
        if (this.nomeScheda == null || esercizi.isEmpty()) {
            System.out.println("\nNessuna scheda presente. Crea prima una scheda!");
            return;
        }
        
        int scelta;
        do {
            System.out.println("\n========== MODIFICA SCHEDA ==========");
            System.out.println("1. Modifica nome scheda");
            System.out.println("2. Modifica obiettivo");
            System.out.println("3. Aggiungi esercizio");
            System.out.println("4. Rimuovi esercizio");
            System.out.println("5. Modifica esercizio esistente");
            System.out.println("0. Torna al menu principale");
            System.out.print("Scelta: ");
            scelta = scanner.nextInt();
            
            switch (scelta) {
                case 1:
                    scanner.nextLine();
                    System.out.print("Nuovo nome della scheda: ");
                    this.nomeScheda = scanner.nextLine();
                    System.out.println("Nome modificato!");
                    break;
                    
                case 2:
                    System.out.println("Nuovo obiettivo:");
                    System.out.println("1. Massa Muscolare");
                    System.out.println("2. Dimagrimento");
                    System.out.println("3. Tonificazione");
                    System.out.println("4. Forza");
                    System.out.print("Scelta: ");
                    int sceltaObiettivo = scanner.nextInt();
                    switch (sceltaObiettivo) {
                        case 1: this.obiettivo = "Massa Muscolare"; break;
                        case 2: this.obiettivo = "Dimagrimento"; break;
                        case 3: this.obiettivo = "Tonificazione"; break;
                        case 4: this.obiettivo = "Forza"; break;
                    }
                    System.out.println("Obiettivo modificato!");
                    break;
                    
                case 3:
                    mostraEserciziDisponibili();
                    System.out.print("Numero esercizio da aggiungere: ");
                    int nuovoEs = scanner.nextInt();
                    if (nuovoEs >= 1 && nuovoEs <= 10) {
                        aggiungiEsercizio(eserciziDisponibili[nuovoEs - 1]);
                        System.out.println("Esercizio aggiunto!");
                    }
                    break;
                    
                case 4:
                    visualizzaScheda();
                    System.out.print("Numero esercizio da rimuovere: ");
                    int rimuovi = scanner.nextInt();
                    if (rimuovi >= 1 && rimuovi <= esercizi.size()) {
                        rimuoviEsercizio(rimuovi - 1);
                        System.out.println("Esercizio rimosso!");
                    }
                    break;
                    
                case 5:
                    visualizzaScheda();
                    System.out.print("Numero esercizio da modificare: ");
                    int modifica = scanner.nextInt();
                    if (modifica >= 1 && modifica <= esercizi.size()) {
                        Esercizio es = esercizi.get(modifica - 1);
                        System.out.print("Nuove serie: ");
                        es.setSerie(scanner.nextInt());
                        System.out.print("Nuove ripetizioni: ");
                        es.setRipetizioni(scanner.nextInt());
                        System.out.print("Nuovo tempo recupero: ");
                        es.setTempoRecupero(scanner.nextInt());
                        System.out.println("Esercizio modificato!");
                    }
                    break;
            }
        } while (scelta != 0);
    }

    // Eliminazione scheda
    public void eliminaScheda() {
        svuota();
        this.utente = null;
        System.out.println("\nScheda eliminata con successo!");
    }

    @Override
    public String toString() {
        return "SchedaAllenamento [nomeScheda=" + nomeScheda + ", obiettivo=" + obiettivo + 
               ", numeroEsercizi=" + esercizi.size() + "]";
    }
}
