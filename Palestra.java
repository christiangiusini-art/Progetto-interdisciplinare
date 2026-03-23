package esercizi;

import java.util.Objects;
import java.util.Scanner;

public class Palestra {
	/*inserisci gli attributi della classe Palestra*/
	public Static double costoAnnuale=300;
 	public Static double costoMensile=35;
	private String tipoAbbonamento; // "mensile" o "annuale"
	private int valutazione;/*1 a 5*/
	private int orario;/*dalle 7 alle 19*/
	/*passo un oggetto della classe Utente come attributo*/
	private Utente utente;
	
	/*creo il costruttore di default*/
	public Palestra() {
		
	}
	
	/*creo il costruttore parametrizzato */
	public Palestra(double cA,double cM,int valut,int orario,Utente u) {
		this.costoAnnuale=cA;
		this.costoMensile=cM;
		this.valutazione=valut;
		this.orario=orario;
		this.utente=u;
	}
	/*creo il metodo get per ottenere i valori degli attributi*/
	/*creo il metodo set per impostare i valori degli attributi*/
	public double getCostoAnnuale() {
		return costoAnnuale;
	}

	public void setCostoAnnuale(double costoAnnuale) {
		this.costoAnnuale = costoAnnuale;
	}

	public double getCostoMensile() {
		return costoMensile;
	}
	public String getTipoAbbonamento() {
    return tipoAbbonamento;
}

public void setTipoAbbonamento(String tipoAbbonamento) {
    this.tipoAbbonamento = tipoAbbonamento;
}

	public void setCostoMensile(double costoMensile) {
		this.costoMensile = costoMensile;
	}

	public int getValutazione() {
		return valutazione;
	}

	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}

	public int getOrario() {
		return orario;
	}

	public void setOrario(int orario) {
		this.orario = orario;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	/*creo il metodo toString che ritorna una stringa contenente i valori degli attributi della classe*/
	public String toString() {
		return "Palestra [costoAnnuale=" + costoAnnuale + ", costoMensile=" + costoMensile + ", valutazione="
				+ valutazione + ", orario=" + orario + ", utente=" + utente + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Palestra other = (Palestra) obj;
		return Double.doubleToLongBits(costoAnnuale) == Double.doubleToLongBits(other.costoAnnuale)
				&& Double.doubleToLongBits(costoMensile) == Double.doubleToLongBits(other.costoMensile)
				&& orario == other.orario && Objects.equals(utente, other.utente) && valutazione == other.valutazione;
	}
	
	/*creo il metodo inserisci dati per far inserire i valori agli attributi*/
	public void inserisciDati(Scanner tastiera) {
int scelta;
System.out.println(" INSERIMENTO DATI PALESTRA ");
do {
    System.out.println("Scegli il tipo di abbonamento:");
    System.out.println("1. Mensile");
    System.out.println("2. Annuale");
    System.out.print(" Scelta: ");

    scelta = tastiera.nextInt();

    if (scelta != 1 && scelta != 2) {
        System.out.println(" Scelta non valida! Riprova.");
    }

} while (scelta != 1 && scelta != 2);

// assegno il valore
if (scelta == 1) {
    this.tipoAbbonamento = "mensile";
} else {
    this.tipoAbbonamento = "annuale";
}
	System.out.println("inserisci la valutazione della palestra: ");
	this.valutazione=tastiera.nextInt();
	System.out.println("inserisci l'orario della palestra: ");
	this.orario=tastiera.nextInt();
	this.utente = new Utente();
    System.out.println(" INSERIMENTO DATI UTENTE ");
    this.utente.inserisciDati(tastiera);
}
	
	}
	
	/*metodo che applica lo sconto sul costo annuale solo se l'eta e inferiore di 18*/
	public static double scontoCostoAnnuale(double costoAnnuale, int eta) {
    if (eta < 18) {
        return costoAnnuale * 0.7; // 30% di sconto
    } else {
        return costoAnnuale;
    }
}
	
	/*metodo che applica lo sconto sul costo mensile solo se l'eta e inferiore di 18*/

	public static double scontoCostoMensile(double costoMensile, int eta) {
    if (eta < 18) {
        return costoMensile * 0.7;
    } else {
        return costoMensile;
    }
}
	
	/*metodo che dice all'utente se la palestra e aperta nell'orario in cui vuole allenarsi*/

	public String disponibilitaPalestra() {
	    
	    if (this.orario < 7 || this.orario > 19) {
	        return "non puoi allenarti, la palestra e chiusa";
	    } else {
	        return "puoi allenarti, la palestra e aperta";
	    }
	}
	public String creaSchedaAllenamento(double peso, double altezza) {
    double bmi = calcolaBMI(peso, altezza);

    String dati;
    dati="Peso: " + peso + " kg\n";
    dati="Altezza: " + altezza + " cm\n";
     dati= "BMI: " + bmi;

    if (bmi < 18.5) {
        return    " OBIETTIVO: AUMENTO MASSA\n";
        		  "- 3 allenamenti a settimana\n";
       			  "- Esercizi: panca, squat, stacchi\n";
       			  "- Riposo tra le serie lungo\n";
    } 
    else if (bmi < 25) {
       return " OBIETTIVO: MANTENIMENTO\n";
	          "- 3-4 allenamenti a settimana\n";
        	  "- Mix pesi + cardio\n";
    } 
    else {
      return  " OBIETTIVO: DIMAGRIMENTO\n";
        	  "- 4-5 allenamenti a settimana\n";
        	  "- Cardio + circuito\n";
    	}

	}
		
}
	

