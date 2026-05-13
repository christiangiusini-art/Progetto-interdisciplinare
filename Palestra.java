


import java.util.ArrayList;

/**
 * Classe che rappresenta la palestra con il catalogo esercizi
 */
public class Palestra {
    private String nome;
    private String indirizzo;
    private double costoMensile;
    private double costoAnnuale;
    private double scontoUnder18;
    private double scontoOver65;
    private ArrayList<Esercizio> catalogoEsercizi;
    private String[] orariSettimanali;

    public Palestra() {
        this.nome = "LA PALESTRA ASR";
        this.indirizzo = "Via Roma 123, 00100 Roma";
        this.costoMensile = 50.0;
        this.costoAnnuale = 480.0;
        this.scontoUnder18 = 20.0; // percentuale
        this.scontoOver65 = 30.0; // percentuale
        this.catalogoEsercizi = new ArrayList<>();
        this.orariSettimanali = new String[] {
            "Lunedi: 06:00 - 22:00",
            "Martedi: 06:00 - 22:00",
            "Mercoledi: 06:00 - 22:00",
            "Giovedi: 06:00 - 22:00",
            "Venerdi: 06:00 - 22:00",
            "Sabato: 08:00 - 20:00",
            "Domenica: 09:00 - 14:00"
        };
        
    }

   
    public double calcolaCosto(int eta, boolean annuale) {
        double costo = annuale ? costoAnnuale : costoMensile;

        if (eta < 18) {
            costo = costo * (1 - scontoUnder18 / 100);
        } else if (eta >= 65) {
            costo = costo * (1 - scontoOver65 / 100);
        }

        return costo;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public double getCostoMensile() {
        return costoMensile;
    }

    public double getCostoAnnuale() {
        return costoAnnuale;
    }

    public double getScontoUnder18() {
        return scontoUnder18;
    }

    public double getScontoOver65() {
        return scontoOver65;
    }

    public ArrayList<Esercizio> getCatalogoEsercizi() {
        return catalogoEsercizi;
    }

    public Esercizio getEsercizio(int index) {
        if (index >= 0 && index < catalogoEsercizi.size()) {
            return catalogoEsercizi.get(index);
        }
        return null;
    }

    public String[] getOrariSettimanali() {
        return orariSettimanali;
    }
}
