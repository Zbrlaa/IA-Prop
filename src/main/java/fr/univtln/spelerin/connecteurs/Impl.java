package fr.univtln.spelerin.connecteurs;

import fr.univtln.spelerin.Formule;
import fr.univtln.spelerin.VarSet;
import lombok.Getter;


@Getter
public class Impl extends Formule{
	private Impl(Formule pre, Formule post, VarSet varset){
		super(pre, post, varset);
	}
	
	public static Formule impl(Formule pre, Formule post){
		VarSet varset = pre.getVarset().union(post.getVarset());
		return new Impl(pre, post, varset);
	}

	@Override
	public String toString(){
		return "(" + pre + " -> " + post + ")";
	}
}