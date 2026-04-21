package progetto;

import java.util.Objects;
import java.util.Scanner;

public class Palestra {

    // ==================== ATTRIBUTI STATICI ====================
    // Costi e orari validi per tutte le palestre (condivisi)
    public static double costoAnnuale = 300;
    public static double costoMensile = 35;
    public static int orarioApertura = 7;
    public static int orarioChiusura = 22;
    
    // ==================== ATTRIBUTI  ====================
    // Nome e indirizzo predefiniti 
    private String nomePalestra = "Iron Paradise Gym";
    private String indirizzo = "Via Roma 123, Milano";
    
    private String tipoAbbonamento; // mensile o annuale
    private int valutazione;        // valore da 1 a 5
    private Utente utente;          // utente associato
    
    // ==================== GESTIONE SCHEDE ====================
    // Array per memorizzare le schede di allenamento
    private SchedaAllenamento[] schedeAllenamento;
    private int numeroSchede;
    private static final int MAX_SCHEDE = 10; // limite massimo
    
    // ==================== COSTRUTTORE DEFAULT ====================
    public Palestra() {
        this.schedeAllenamento = new SchedaAllenamento[MAX_SCHEDE];
        this.numeroSchede = 0;
    }
    
    // ==================== COSTRUTTORE PARAMETRIZZATO ====================
    public Palestra(String nomePalestra, String indirizzo, double cA, double cM, 
                    int valut, int orarioAp, int orarioCh, Utente u) {
        this.nomePalestra = nomePalestra;
        this.indirizzo = indirizzo;
        Palestra.costoAnnuale = cA;
        Palestra.costoMensile = cM;
        this.valutazione = valut;
        Palestra.orarioApertura = orarioAp;
        Palestra.orarioChiusura = orarioCh;
        this.utente = u;
        this.schedeAllenamento = new SchedaAllenamento[MAX_SCHEDE];
        this.numeroSchede = 0;
    }
    
    // ==================== GETTER E SETTER ====================
    public String getNomePalestra() { return nomePalestra; }
    public void setNomePalestra(String nomePalestra) { this.nomePalestra = nomePalestra; }

    public String getIndirizzo() { return indirizzo; }
    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }

    public static double getCostoAnnuale() { return costoAnnuale; }
    public static void setCostoAnnuale(double costo) { Palestra.costoAnnuale = costo; }

    public static double getCostoMensile() { return costoMensile; }
    public static void setCostoMensile(double costo) { Palestra.costoMensile = costo; }

    public static int getOrarioApertura() { return orarioApertura; }
    public static void setOrarioApertura(int orario) { Palestra.orarioApertura = orario; }

    public static int getOrarioChiusura() { return orarioChiusura; }
    public static void setOrarioChiusura(int orario) { Palestra.orarioChiusura = orario; }

    public String getTipoAbbonamento() { return tipoAbbonamento; }
    public void setTipoAbbonamento(String tipo) { this.tipoAbbonamento = tipo; }

    public int getValutazione() { return valutazione; }

    // Controllo validità valutazione (1-5)
    public void setValutazione(int valutazione) { 
        if (valutazione >= 1 && valutazione <= 5) 
            this.valutazione = valutazione; 
    }

    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }

    public SchedaAllenamento[] getSchedeAllenamento() { return schedeAllenamento; }
    public int getNumeroSchede() { return numeroSchede; }

    // ==================== INSERIMENTO DATI ====================
    // L'utente NON inserisce nome e indirizzo (già predefiniti)
    public void inserisciDati(Scanner tastiera) {

        System.out.println("\n======================================");
        System.out.println("    INSERIMENTO DATI PALESTRA         ");
        System.out.println("======================================");
        
        tastiera.nextLine();
        
        // Visualizzazione dati fissi
        System.out.println("Nome palestra: " + nomePalestra);
        System.out.println("Indirizzo: " + indirizzo); 
        
        int scelta;

        // Scelta tipo abbonamento
        do {
            System.out.println("\nScegli il tipo di abbonamento:");
            System.out.println("1. Mensile (EUR " + costoMensile + ")");
            System.out.println("2. Annuale (EUR " + costoAnnuale + ")");
            System.out.print("Scelta: ");
            scelta = tastiera.nextInt();
        } while (scelta != 1 && scelta != 2);

        this.tipoAbbonamento = (scelta == 1) ? "mensile" : "annuale";
        
        // Inserimento valutazione 
        do {
            System.out.print("Inserisci la valutazione della palestra (1-5): ");
            this.valutazione = tastiera.nextInt();
        } while (valutazione < 1 || valutazione > 5);

        System.out.println("\nDati palestra inseriti con successo!");
    }

    // ==================== METODI SCONTO ====================
    // Sconto per età (minorenni e anziani)
    public static double scontoCostoAnnuale(double costo, int eta) {
        if (eta < 18) return costo * 0.70;
        else if (eta >= 65) return costo * 0.80;
        return costo;
    }
    
    public static double scontoCostoMensile(double costo, int eta) {
        if (eta < 18) return costo * 0.70;
        else if (eta >= 65) return costo * 0.80;
        return costo;
    }
    
    // Calcolo costo finale in base all'abbonamento
    public double calcolaCostoFinale() {
        if (utente == null) return 0;

        if (tipoAbbonamento.equals("mensile")) {
            return scontoCostoMensile(costoMensile, utente.getEta());
        }
        return scontoCostoAnnuale(costoAnnuale, utente.getEta());
    }

    // ==================== DISPONIBILITÀ PALESTRA ====================
    public static String disponibilitaPalestra(int orarioRichiesto) {
        if (orarioRichiesto < orarioApertura || orarioRichiesto >= orarioChiusura) {
            return "La palestra e chiusa. Orario: " + orarioApertura + ":00 - " + orarioChiusura + ":00";
        }
        return "La palestra e aperta! Puoi allenarti.";
    }
    
    public static boolean isPalestraAperta(int ora) {
        return ora >= orarioApertura && ora < orarioChiusura;
    }

    // ==================== GESTIONE SCHEDE ====================
    // Aggiunge una nuova scheda se c'è spazio
    public void aggiungiScheda(SchedaAllenamento scheda) {
        if (numeroSchede < MAX_SCHEDE) {
            schedeAllenamento[numeroSchede] = scheda;
            numeroSchede++;
            System.out.println("Scheda '" + scheda.getNomeScheda() + "' aggiunta!");
        } else {
            System.out.println("Limite schede raggiunto! Max " + MAX_SCHEDE);
        }
    }
    
    // Rimuove una scheda 
    public void rimuoviScheda(int indice) {
        if (indice >= 0 && indice < numeroSchede) {
            String nome = schedeAllenamento[indice].getNomeScheda();

            for (int i = indice; i < numeroSchede - 1; i++) {
                schedeAllenamento[i] = schedeAllenamento[i + 1];
            }

            schedeAllenamento[numeroSchede - 1] = null;
            numeroSchede--;

            System.out.println("Scheda '" + nome + "' rimossa!");
        } else {
            System.out.println("Indice non valido!");
        }
    }
    
    // Mostra tutte le schede presenti
    public void visualizzaTutteSchede() {
        if (numeroSchede == 0) {
            System.out.println("\nNessuna scheda presente.");
            return;
        }
        
        System.out.println("\n======================================");
        System.out.println("       ELENCO SCHEDE ALLENAMENTO      ");
        System.out.println("======================================");
        
        for (int i = 0; i < numeroSchede; i++) {
            System.out.println((i + 1) + ". " + schedeAllenamento[i].getNomeScheda() + 
                             " - Obiettivo: " + schedeAllenamento[i].getObiettivo() + 
                             " - Esercizi: " + schedeAllenamento[i].getNumeroEsercizi());
        }
    }
    
    // Restituisce una scheda specifica
    public SchedaAllenamento getScheda(int indice) {
        if (indice >= 0 && indice < numeroSchede) {
            return schedeAllenamento[indice];
        }
        return null;
    }

    // ==================== INFO PALESTRA ====================
    public void mostraInfoPalestra() {
        System.out.println("\n======================================");
        System.out.println("         INFO PALESTRA                ");
        System.out.println("======================================");
        System.out.println("Nome: " + nomePalestra);
        System.out.println("Indirizzo: " + indirizzo);
        System.out.println("Orario: " + orarioApertura + ":00 - " + orarioChiusura + ":00");
        System.out.println("Valutazione: " + valutazione + "/5");
        System.out.println("--------------------------------------");
        System.out.println("Abbonamento: " + tipoAbbonamento.toUpperCase());
        System.out.println("Costo base: EUR " + (tipoAbbonamento.equals("mensile") ? costoMensile : costoAnnuale));

        if (utente != null) {
            System.out.println("Costo con sconto: EUR " + String.format("%.2f", calcolaCostoFinale()));
        }

        System.out.println("Schede create: " + numeroSchede);
        System.out.println("======================================");
    }

    
    @Override
    public String toString() {
        return "Palestra [nome=" + nomePalestra + ", indirizzo=" + indirizzo + 
               ", abbonamento=" + tipoAbbonamento + ", valutazione=" + valutazione + 
               ", orario=" + orarioApertura + "-" + orarioChiusura + 
               ", schede=" + numeroSchede + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Palestra other = (Palestra) obj;

        return Objects.equals(nomePalestra, other.nomePalestra) && 
               Objects.equals(indirizzo, other.indirizzo) &&
               valutazione == other.valutazione;
    }
}
