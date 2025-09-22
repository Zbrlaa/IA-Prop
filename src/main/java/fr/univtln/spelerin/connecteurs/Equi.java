package fr.univtln.spelerin.connecteurs;

import fr.univtln.spelerin.Formule;


public class Equi implements Formule{
	Formule pre;
	Formule post;

	public Equi(Formule pre, Formule post){
		this.pre = pre;
		this.post = post;
	}
	
	public static Formule equi(Formule pre, Formule post){
		return new Equi(pre, post);
	}

	@Override
	public String toString(){
		return "(" + pre.toString() + " <-> " + post.toString() + ")";
	}
}