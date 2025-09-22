package fr.univtln.spelerin.connecteurs;

import fr.univtln.spelerin.Formule;


public class And implements Formule{
	Formule pre;
	Formule post;

	public And(Formule pre, Formule post){
		this.pre = pre;
		this.post = post;
	}
	
	public static Formule and(Formule pre, Formule post){
		return new And(pre, post);
	}

	@Override
	public String toString(){
		return "(" + pre.toString() + " & " + post.toString() + ")";
	}
}