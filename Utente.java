package progetto;

import java.util.Objects;
import java.util.Scanner;

public class Utente {
    private String nome;
    private String cognome;
    private int eta;
    private String username;
    private String email;
    private String telefono;
    private double peso;
    private double altezza;
    
    // === NUOVO: ABBIAMENTO ===
    private String tipoAbbonamento = "Nessuno"; // "Mensile", "Annuale", "Nessuno"
    private boolean abbonamentoAttivo = false;

    public Utente() {}

    public Utente(String n, String c, String u, String e, String t, double p, double a) {
        this.nome = n;
        this.cognome = c;
        this.username = u;
        this.email = e;
        this.telefono = t;
        this.altezza = a;
        this.peso = p;
    }

    public Utente(Utente u) {
        this.nome = u.nome;
        this.cognome = u.cognome;
        this.username = u.username;
        this.email = u.email;
        this.telefono = u.telefono;
        this.altezza = u.altezza;
        this.peso = u.peso;
        this.eta = u.eta;
        this.tipoAbbonamento = u.tipoAbbonamento;
        this.abbonamentoAttivo = u.abbonamentoAttivo;
    }

    public String getNomeCompleto() {
        return nome + " " + cognome;
    }

    // Getters / Setters Base
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public int getEta() { return eta; }
    public void setEta(int eta) { this.eta = eta; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public double getAltezza() { return altezza; }
    public void setAltezza(double altezza) { this.altezza = altezza; }

    // === METODI ABBONAMENTO ===
    public String getTipoAbbonamento() { return tipoAbbonamento; }
    public void setTipoAbbonamento(String tipo) { 
        this.tipoAbbonamento = tipo; 
        if (!tipo.equals("Nessuno")) {
            this.abbonamentoAttivo = true;
        } else {
            this.abbonamentoAttivo = false;
        }
    }

    public boolean isAbbonamentoAttivo() { return abbonamentoAttivo; }
    public void setAbbonamentoAttivo(boolean attivo) { this.abbonamentoAttivo = attivo; }

    // Visualizza informazioni console
    public void mostraInformazioni() {
        System.out.println("=== Informazioni Utente ===");
        System.out.println("Nome: " + nome);
        System.out.println("Cognome: " + cognome);
        System.out.println("Età: " + eta);
        System.out.println("Email: " + email);
        System.out.println("Username: " + username);
        System.out.println("Telefono: " + telefono);
        System.out.println("Altezza: " + altezza);
        System.out.println("Peso: " + peso);
        System.out.println("Abbonamento: " + tipoAbbonamento);
        System.out.println("Attivo: " + abbonamentoAttivo);
    }

    public void inserisciDati(Scanner tastiera) {
        System.out.print("Inserisci il nome: ");
        this.nome = tastiera.next();
        System.out.print("Inserisci il cognome: ");
        this.cognome = tastiera.next();
        System.out.print("Inserisci l'età: ");
        this.eta = tastiera.nextInt();
        System.out.print("Inserisci l'username: ");
        this.username = tastiera.next();
        System.out.print("Inserisci la tua email: ");
        this.email = tastiera.next();
        System.out.print("Inserisci il numero di telefono: ");
        this.telefono = tastiera.next();
        System.out.print("Inserisci l'altezza: ");
        this.altezza = tastiera.nextDouble();
        System.out.print("Inserisci il peso: ");
        this.peso = tastiera.nextDouble();
    }

    @Override
    public String toString() {
        return "Utente [nome=" + nome + ", cognome=" + cognome + ", eta=" + eta + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Utente)) return false;
        Utente other = (Utente) obj;
        return Objects.equals(username, other.username) && Objects.equals(email, other.email);
    }
}
