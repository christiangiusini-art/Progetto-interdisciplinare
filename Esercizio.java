package progetto;

public class Esercizio {
    private String nome;
    private String gruppoMuscolare;
    private int serie;
    private int ripetizioni;
    private int tempoRecupero; // in secondi

    // Costruttore di default
    public Esercizio() {
    }

    // Costruttore parametrizzato
    public Esercizio(String nome, String gruppoMuscolare, int serie, int ripetizioni, int tempoRecupero) {
        this.nome = nome;
        this.gruppoMuscolare = gruppoMuscolare;
        this.serie = serie;
        this.ripetizioni = ripetizioni;
        this.tempoRecupero = tempoRecupero;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGruppoMuscolare() {
        return gruppoMuscolare;
    }

    public void setGruppoMuscolare(String gruppoMuscolare) {
        this.gruppoMuscolare = gruppoMuscolare;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getRipetizioni() {
        return ripetizioni;
    }

    public void setRipetizioni(int ripetizioni) {
        this.ripetizioni = ripetizioni;
    }

    public int getTempoRecupero() {
        return tempoRecupero;
    }

    public void setTempoRecupero(int tempoRecupero) {
        this.tempoRecupero = tempoRecupero;
    }

    @Override
    public String toString() {
        return nome + " (" + gruppoMuscolare + ") - " + serie + "x" + ripetizioni + " | Recupero: " + tempoRecupero + "s";
    }
}
