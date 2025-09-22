package fr.univtln.spelerin.connecteurs;

import fr.univtln.spelerin.Formule;


public class Not implements Formule{
	Formule post;

	public Not(Formule post){
		this.post = post;
	}
	
	public static Formule not(Formule post){
		return new Not(post);
	}

	@Override
	public String toString(){
		return "!" + post.toString();
	}
}