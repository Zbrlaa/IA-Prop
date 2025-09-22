package fr.univtln.spelerin.connecteurs;

import fr.univtln.spelerin.Formule;


public class Or implements Formule{
	Formule pre;
	Formule post;

	public Or(Formule pre, Formule post){
		this.pre = pre;
		this.post = post;
	}
	
	public static Formule or(Formule pre, Formule post){
		return new Or(pre, post);
	}

	@Override
	public String toString(){
		return "(" + pre.toString() + " | " + post.toString() + ")";
	}
}