package esercizi;


import java.util.Objects;
import java.util.Scanner;

public class Utente {
	/*inserisci gli attributi della classe Palestra*/
	    private String nome;
	    private String cognome;
	    private int eta;
	    private String username;
	    private String email;
	    private int numTelefono;

	    /*creo il costruttore di default*/
	    public Utente(){

	    }

		/*creo il costruttore parametrizzato */
	    public Utente(String n,String c,String u,String e,int num){
	       		this.nome = n;
	               this.cognome=c;
	               this.username=u;
	               this.email=e;
	               this.numTelefono=num;

	    }

		/*creo il costruttore di copia che permette di copiare i valori di un oggetto creato all'interno di un altro */
	    public Utente(Utente u){
	               this.nome = u.nome;
	               this.cognome=u.cognome;
	               this.username=u.username;
	               this.email=u.email;
	               this.numTelefono=u.numTelefono;
	    }

	    
	    /*creo il metodo get per ottenere i valori degli attributi*/
		/*creo il metodo set per impostare i valori degli attributi*/

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCognome() {
			return cognome;
		}

		public void setCognome(String cognome) {
			this.cognome = cognome;
		}

		public int getEta() {
			return eta;
		}

		public void setEta(int eta) {
			this.eta = eta;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public int getNumTelefono() {
			return numTelefono;
		}

		public void setNumTelefono(int numTelefono) {
			this.numTelefono = numTelefono;
		}

		public void mostraInformazioni() {

	        System.out.println(" Informazioni Utente ");

	        System.out.println("Nome: " + nome);

	        System.out.println("Cognome: " + cognome);

	        System.out.println("Età: " + eta);

	        System.out.println("Email: " + email);

	        System.out.println("Username: " + username);

	        System.out.println("Telefono: " + numTelefono);
		}

		/*creo il metodo toString che ritorna una stringa contenente i valori degli attributi della classe*/
		
		public String toString() {
			return "Utente [nome=" + nome + ", cognome=" + cognome + ", eta=" + eta + ", username=" + username + ", email="
					+ email + ", numTelefono=" + numTelefono + "]";
		}

		
		
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Utente other = (Utente) obj;
			return Objects.equals(cognome, other.cognome) && Objects.equals(email, other.email) && eta == other.eta
					&& Objects.equals(nome, other.nome) && numTelefono == other.numTelefono
					&& Objects.equals(username, other.username);
		}

		/*creo il metodo inserisci dati per far inserire i valori agli attributi*/
		public void inserisciDati(Scanner tastiera) {
			System.out.println("inserisci il nome: ");
			this.nome=tastiera.next();
			System.out.println("inserisci il cognome: ");
			this.cognome=tastiera.next();
			System.out.println("inserisci l'eta: ");
			this.eta=tastiera.nextInt();
			System.out.println("inserisci l'username: ");
			this.username=tastiera.next();
			System.out.println("inserisci la tua email: ");
			this.email=tastiera.next();
			System.out.println("inserisci il numero di telefono: ");
			this.numTelefono=tastiera.nextInt();
		}
	    
	    
		
	    
	    
	    }

