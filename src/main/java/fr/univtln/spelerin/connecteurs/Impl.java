package fr.univtln.spelerin.connecteurs;

import fr.univtln.spelerin.Formule;


public class Impl implements Formule{
	Formule pre;
	Formule post;

	public Impl(Formule pre, Formule post){
		this.pre = pre;
		this.post = post;
	}
	
	public static Formule impl(Formule pre, Formule post){
		return new Impl(pre, post);
	}

	@Override
	public String toString(){
		return "(" + pre.toString() + " -> " + post.toString() + ")";
	}
}