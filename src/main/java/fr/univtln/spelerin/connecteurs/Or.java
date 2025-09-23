package fr.univtln.spelerin.connecteurs;

import fr.univtln.spelerin.Formule;
import fr.univtln.spelerin.Interpretation;
import fr.univtln.spelerin.VarSet;
import lombok.Getter;


@Getter
public class Or extends Formule{
	private Or(Formule pre, Formule post, VarSet varset){
		super(pre, post, varset);
	}
	
	public static Formule or(Formule pre, Formule post){
		VarSet varset = pre.getVarset().union(post.getVarset());
		return new Or(pre, post, varset);
	}

	@Override
	public String toString(){
		return "(" + pre + " | " + post + ")";
	}

	public boolean value(Interpretation i){
		boolean value = pre.value(i) || post.value(i);
		return value;
	}
}