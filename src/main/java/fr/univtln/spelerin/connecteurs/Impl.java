package fr.univtln.spelerin.connecteurs;

import fr.univtln.spelerin.Formule;
import fr.univtln.spelerin.Interpretation;
import fr.univtln.spelerin.VarSet;
import lombok.Getter;


@Getter
public class Impl extends Formule{
	private Impl(Formule pre, Formule post, VarSet varset){
		super("impl", pre, post, varset);
	}
	
	public static Formule impl(Formule pre, Formule post){
		VarSet varset = pre.getVarset().union(post.getVarset());
		return new Impl(pre, post, varset);
	}

	@Override
	public String toString(){
		return "(" + pre + " -> " + post + ")";
	}

	public boolean value(Interpretation i){
		//a->b <=> !a|b
		boolean value = !pre.value(i) || post.value(i);
		return value;
	}

	@Override
	public Formule toNormalForm(){
		return Or.or(Not.not(pre), post).toNormalForm();
	}
}