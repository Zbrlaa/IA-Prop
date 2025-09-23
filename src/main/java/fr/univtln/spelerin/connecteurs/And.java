package fr.univtln.spelerin.connecteurs;

import fr.univtln.spelerin.Formule;
import fr.univtln.spelerin.Interpretation;
import fr.univtln.spelerin.VarSet;
import lombok.Getter;


@Getter
public class And extends Formule{
	private And(Formule pre, Formule post, VarSet varset){
		super(pre, post, varset);
	}

	public static Formule and(Formule pre, Formule post){
		VarSet varset = pre.getVarset().union(post.getVarset());
		return new And(pre, post, varset);
	}

	@Override
	public String toString(){
		return "(" + pre + " & " + post + ")";
	}

	public boolean value(Interpretation i){
		boolean value = pre.value(i) && post.value(i);
		return value;
	}
}