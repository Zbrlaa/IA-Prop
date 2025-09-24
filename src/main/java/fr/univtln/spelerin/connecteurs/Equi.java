package fr.univtln.spelerin.connecteurs;

import fr.univtln.spelerin.Formule;
import fr.univtln.spelerin.Interpretation;
import fr.univtln.spelerin.VarSet;
import lombok.Getter;


@Getter
public class Equi extends Formule{
	private Equi(Formule pre, Formule post, VarSet varset){
		super("equi", pre, post, varset);
	}
	
	public static Formule equi(Formule pre, Formule post){
		VarSet varset = pre.getVarset().union(post.getVarset());
		return new Equi(pre, post, varset);
	}

	@Override
	public String toString(){
		return "(" + pre + " <-> " + post + ")";
	}

	public boolean value(Interpretation i){
		boolean value = pre.value(i) == post.value(i);
		return value;
	}

	@Override
	public Formule toNormalForm(){
		Formule preNF = pre.toNormalForm();
		Formule postNF = post.toNormalForm();
		return And.and(Or.or(Not.not(preNF), postNF), Or.or(Not.not(postNF), preNF)).toNormalForm();
	}
}