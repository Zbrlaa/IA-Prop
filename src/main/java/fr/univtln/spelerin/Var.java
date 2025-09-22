package fr.univtln.spelerin;


public class Var implements Formule{
	//Je mets String et pas char pour pouvoir mettre une annotation genre a1
	private String valeur;

	public Var(String valeur){
		this.valeur = valeur;
	}

	public static Formule var(String valeur){
		return new Var(valeur);
	}

	@Override
	public String toString() {
		return valeur;
	}
}