// SchedaAllenamento.java - Versione aggiornata


import java.util.Scanner;

public class SchedaAllenamento{ 
   private String nomeScheda;
    private String obiettivo;
    private Esercizio[] esercizi;
    private int numeroEsercizi;
    private Utente utente;

    private static Esercizio[] eserciziDisponibili = new Esercizio[10];
    private static final int MAX_ESERCIZI = 10;

    static {
        // Inizializzazione esercizi precaricati
        eserciziDisponibili[0] = new Esercizio("Panca Piana", "Petto", 4, 10, 90);
        eserciziDisponibili[1] = new Esercizio("Squat", "Gambe", 4, 12, 120);
        eserciziDisponibili[2] = new Esercizio("Stacco da Terra", "Schiena", 3, 8, 120);
        eserciziDisponibili[3] = new Esercizio("Lento Avanti", "Spalle", 3, 10, 60);
        eserciziDisponibili[4] = new Esercizio("Curl Bicipiti", "Bicipiti", 3, 12, 45);
        eserciziDisponibili[5] = new Esercizio("French Press", "Tricipiti", 3, 12, 45);
        eserciziDisponibili[6] = new Esercizio("Trazioni", "Schiena", 3, 8, 90);
        eserciziDisponibili[7] = new Esercizio("Leg Press", "Gambe", 4, 12, 90);
        eserciziDisponibili[8] = new Esercizio("Croci", "Petto", 3, 12, 60);
        eserciziDisponibili[9] = new Esercizio("Alzate Laterali", "Spalle", 3, 15, 45);
    }

    public SchedaAllenamento() {
        this.esercizi = new Esercizio[MAX_ESERCIZI];
        this.numeroEsercizi = 0;
    }

    public SchedaAllenamento(String nomeScheda, String obiettivo, Utente utente) {
        this.nomeScheda = nomeScheda;
        this.obiettivo = obiettivo;
        this.utente = utente;
        this.esercizi = new Esercizio[MAX_ESERCIZI];
        this.numeroEsercizi = 0;
    }

    // Getters e Setters
    public String getNomeScheda() { return nomeScheda; }
    public void setNomeScheda(String nomeScheda) { this.nomeScheda = nomeScheda; }
    public String getObiettivo() { return obiettivo; }
    public void setObiettivo(String obiettivo) { this.obiettivo = obiettivo; }
    public Esercizio[] getEsercizi() { return esercizi; }
    public int getNumeroEsercizi() { return numeroEsercizi; }
    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }
    public static Esercizio[] getEserciziDisponibili() { return eserciziDisponibili; }

    public boolean aggiungiEsercizio(Esercizio es) {
        if (numeroEsercizi < MAX_ESERCIZI) {
            esercizi[numeroEsercizi] = es;
            numeroEsercizi++;
            return true;
        }
        return false;
    }

    public void rimuoviEsercizio(int indice) {
        if (indice >= 0 && indice < numeroEsercizi) {
            for (int i = indice; i < numeroEsercizi - 1; i++) {
                esercizi[i] = esercizi[i + 1];
            }
            esercizi[numeroEsercizi - 1] = null;
            numeroEsercizi--;
        }
    }

    public void eliminaScheda() {
        this.nomeScheda = null;
        this.obiettivo = null;
        for (int i = 0; i < numeroEsercizi; i++) {
            esercizi[i] = null;
        }
        this.numeroEsercizi = 0;
        this.utente = null;
    }

    @Override
    public String toString() {
        return "SchedaAllenamento [nomeScheda=" + nomeScheda + ", obiettivo=" + obiettivo + 
               ", numeroEsercizi=" + numeroEsercizi + "]";
    }
}
