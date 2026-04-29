package progetto;

import java.util.Objects;
import java.util.Scanner;

public class Palestra {

    // ==================== COSTI ====================
    public static double costoAnnuale = 300;
    public static double costoMensile = 35;

    // ==================== ORARI SETTIMANALI ====================
    // 0 = Lun, 1 = Mar, ..., 6 = Dom
    private static int[] orarioAperturaSettimana = {7, 7, 7, 7, 7, 9, 9};
    private static int[] orarioChiusuraSettimana = {22, 22, 22, 22, 22, 18, 12};

    private static final String[] giorni = {
        "Lunedì", "Martedì", "Mercoledì",
        "Giovedì", "Venerdì", "Sabato", "Domenica"
    };

    // ==================== DATI PALESTRA ====================
    private String nomePalestra = "Iron Paradise Gym";
    private String indirizzo = "Via Roma 123, Milano";

    private String tipoAbbonamento;
    private int valutazione;
    private Utente utente;

    // ==================== SCHEDE ====================
    private SchedaAllenamento[] schedeAllenamento;
    private int numeroSchede;
    private static final int MAX_SCHEDE = 10;

    // ==================== COSTRUTTORE ====================
    public Palestra() {
        this.schedeAllenamento = new SchedaAllenamento[MAX_SCHEDE];
        this.numeroSchede = 0;
    }

    // ==================== GETTER ====================
    public String getNomePalestra() { return nomePalestra; }
    public String getIndirizzo() { return indirizzo; }
    public static double getCostoAnnuale() { return costoAnnuale; }
    public static double getCostoMensile() { return costoMensile; }
    public String getTipoAbbonamento() { return tipoAbbonamento; }
    public int getValutazione() { return valutazione; }
    public Utente getUtente() { return utente; }
    public int getNumeroSchede() { return numeroSchede; }

    public void setUtente(Utente utente) { this.utente = utente; }

    // ==================== INSERIMENTO DATI ====================
    public void inserisciDati(Scanner tastiera) {

        System.out.println("\n======================================");
        System.out.println("    INSERIMENTO DATI PALESTRA         ");
        System.out.println("======================================");

        tastiera.nextLine();

        // Nome e indirizzo fissi
        System.out.println("Nome palestra: " + nomePalestra);
        System.out.println("Indirizzo: " + indirizzo);

        int scelta;

        do {
            System.out.println("\nScegli il tipo di abbonamento:");
            System.out.println("1. Mensile (EUR " + costoMensile + ")");
            System.out.println("2. Annuale (EUR " + costoAnnuale + ")");
            System.out.print("Scelta: ");
            scelta = tastiera.nextInt();
        } while (scelta != 1 && scelta != 2);

        tipoAbbonamento = (scelta == 1) ? "mensile" : "annuale";

        do {
            System.out.print("Inserisci la valutazione (1-5): ");
            valutazione = tastiera.nextInt();
        } while (valutazione < 1 || valutazione > 5);

        System.out.println("\nDati palestra inseriti!");
    }

    // ==================== SCONTI ANNUALI/MENSILE ====================
    public static double scontoCostoAnnuale(double costo, int eta) {
        if (eta < 18) return costo * 0.70;
        if (eta >= 65) return costo * 0.80;
        return costo;
    }

    public static double scontoCostoMensile(double costo, int eta) {
        if (eta < 18) return costo * 0.70;
        if (eta >= 65) return costo * 0.80;
        return costo;
    }

    public double calcolaCostoFinale() {
        if (utente == null) return 0;

        if (tipoAbbonamento.equals("mensile")) {
            return scontoCostoMensile(costoMensile, utente.getEta());
        }
        return scontoCostoAnnuale(costoAnnuale, utente.getEta());
    }

    // ==================== ORARI ====================
    public static String getOrarioGiorno(int giorno) {
        if (giorno < 0 || giorno > 6) return "Giorno non valido";

        return giorni[giorno] + ": " +
               orarioAperturaSettimana[giorno] + ":00 - " +
               orarioChiusuraSettimana[giorno] + ":00";
    }

    public static boolean isPalestraAperta(int giorno, int ora) {
        if (giorno < 0 || giorno > 6) return false;

        return ora >= orarioAperturaSettimana[giorno] &&
               ora < orarioChiusuraSettimana[giorno];
    }

  public static String disponibilitaPalestra(int giorno, int ora) {
    if (giorno < 0 || giorno > 6) return "Giorno non valido";

    String giornoStr = giorni[giorno];
    int apertura = orarioAperturaSettimana[giorno];
    int chiusura = orarioChiusuraSettimana[giorno];

    if (ora >= apertura && ora < chiusura) {
        return "APERTA\n" +
               "Giorno: " + giornoStr + "\n" +
               "Orario: " + apertura + ":00 - " + chiusura + ":00";
    } else {
        return "CHIUSA\n" +
               "Giorno: " + giornoStr + "\n" +
               "Orario: " + apertura + ":00 - " + chiusura + ":00";
    }
}

    // ==================== STAMPA ORARI ====================
 public void mostraOrariSettimanali() {
    System.out.println("\n╔══════════════════════════════════════╗");
    System.out.println("║         ORARI SETTIMANALI            ║");
    System.out.println("╠══════════════════════════════════════╣");

    for (int i = 0; i < 7; i++) {
        String giorno = String.format("%-10s", giorni[i]);
        String orario = orarioAperturaSettimana[i] + ":00 - " +
                        orarioChiusuraSettimana[i] + ":00";

        System.out.println("║ " + giorno + " → " + orario + "        ║");
    }

    System.out.println("╚══════════════════════════════════════╝");
}
    // ==================== SCHEDE ALLENAMENTO ====================
    public void aggiungiScheda(SchedaAllenamento scheda) {
        if (numeroSchede < MAX_SCHEDE) {
            schedeAllenamento[numeroSchede++] = scheda;
            System.out.println("Scheda aggiunta!");
        } else {
            System.out.println("Limite raggiunto!");
        }
    }

    public void rimuoviScheda(int indice) {
        if (indice >= 0 && indice < numeroSchede) {
            for (int i = indice; i < numeroSchede - 1; i++) {
                schedeAllenamento[i] = schedeAllenamento[i + 1];
            }
            schedeAllenamento[--numeroSchede] = null;
            System.out.println("Scheda rimossa!");
        }
    }

    // ==================== INFO PALESTRA ====================
    public void mostraInfoPalestra() {
        System.out.println("\n===== INFO PALESTRA =====");
        System.out.println("Nome: " + nomePalestra);
        System.out.println("Indirizzo: " + indirizzo);
        System.out.println("Valutazione: " + valutazione + "/5");

        mostraOrariSettimanali();

        System.out.println("Abbonamento: " + tipoAbbonamento);
        System.out.println("Costo finale: EUR " + String.format("%.2f", calcolaCostoFinale()));
    }

    // ==================== OVERRIDE ====================
    @Override
    public String toString() {
        return nomePalestra + " - " + indirizzo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Palestra)) return false;

        Palestra p = (Palestra) obj;
        return Objects.equals(nomePalestra, p.nomePalestra) &&
               Objects.equals(indirizzo, p.indirizzo);
    }
}
