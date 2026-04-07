package progetto;

import java.util.Objects;
import java.util.Scanner;

public class Palestra {
    // Attributi statici
    public static double costoAnnuale = 300;
    public static double costoMensile = 35;
    public static int orarioApertura = 7;
    public static int orarioChiusura = 22;
    
    // Attributi istanza
    private String nomePalestra;
    private String indirizzo;
    private String tipoAbbonamento;
    private int valutazione;
    private Utente utente;
    
    // Gestione schede - array semplice
    private SchedaAllenamento[] schedeAllenamento;
    private int numeroSchede;
    private static final int MAX_SCHEDE = 10;
    
    // Costruttore di default
    public Palestra() {
        this.schedeAllenamento = new SchedaAllenamento[MAX_SCHEDE];
        this.numeroSchede = 0;
    }
    
    // Costruttore parametrizzato
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
    
    // Getters e Setters
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
    public void setValutazione(int valutazione) { 
        if (valutazione >= 1 && valutazione <= 5) this.valutazione = valutazione; 
    }
    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }
    public SchedaAllenamento[] getSchedeAllenamento() { return schedeAllenamento; }
    public int getNumeroSchede() { return numeroSchede; }

    // Inserimento dati
    public void inserisciDati(Scanner tastiera) {
        System.out.println("\n======================================");
        System.out.println("    INSERIMENTO DATI PALESTRA         ");
        System.out.println("======================================");
        
        tastiera.nextLine();
        
        System.out.print("Inserisci il nome della palestra: ");
        this.nomePalestra = tastiera.nextLine();
        
        System.out.print("Inserisci l'indirizzo: ");
        this.indirizzo = tastiera.nextLine();
        
        int scelta;
        do {
            System.out.println("\nScegli il tipo di abbonamento:");
            System.out.println("1. Mensile (EUR " + costoMensile + ")");
            System.out.println("2. Annuale (EUR " + costoAnnuale + ")");
            System.out.print("Scelta: ");
            scelta = tastiera.nextInt();
        } while (scelta != 1 && scelta != 2);

        this.tipoAbbonamento = (scelta == 1) ? "mensile" : "annuale";
        
        do {
            System.out.print("Inserisci la valutazione della palestra (1-5): ");
            this.valutazione = tastiera.nextInt();
        } while (valutazione < 1 || valutazione > 5);
        
       
        System.out.println("\nDati palestra inseriti con successo!");
    }

    // Metodi sconti
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
    
    public double calcolaCostoFinale() {
        if (utente == null) return 0;
        if (tipoAbbonamento.equals("mensile")) {
            return scontoCostoMensile(costoMensile, utente.getEta());
        }
        return scontoCostoAnnuale(costoAnnuale, utente.getEta());
    }

    // Disponibilita
    public static String disponibilitaPalestra(int orarioRichiesto) {
        if (orarioRichiesto < orarioApertura || orarioRichiesto >= orarioChiusura) {
            return "La palestra e chiusa. Orario: " + orarioApertura + ":00 - " + orarioChiusura + ":00";
        }
        return "La palestra e aperta! Puoi allenarti.";
    }
    
    public static boolean isPalestraAperta(int ora) {
        return ora >= orarioApertura && ora < orarioChiusura;
    }

    // Gestione schede con array semplice
    public void aggiungiScheda(SchedaAllenamento scheda) {
        if (numeroSchede < MAX_SCHEDE) {
            schedeAllenamento[numeroSchede] = scheda;
            numeroSchede++;
            System.out.println("Scheda '" + scheda.getNomeScheda() + "' aggiunta!");
        } else {
            System.out.println("Limite schede raggiunto! Max " + MAX_SCHEDE);
        }
    }
    
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
    
    public SchedaAllenamento getScheda(int indice) {
        if (indice >= 0 && indice < numeroSchede) {
            return schedeAllenamento[indice];
        }
        return null;
    }

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
