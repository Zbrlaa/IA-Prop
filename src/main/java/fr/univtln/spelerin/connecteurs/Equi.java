package fr.univtln.spelerin.connecteurs;

import fr.univtln.spelerin.Formule;
import fr.univtln.spelerin.VarSet;
import lombok.Getter;


@Getter
public class Equi extends Formule{
	private Equi(Formule pre, Formule post, VarSet varset){
		super(pre, post, varset);
	}
	
	public static Formule equi(Formule pre, Formule post){
		VarSet varset = pre.getVarset().union(post.getVarset());
		return new Equi(pre, post, varset);
	}

	@Override
	public String toString(){
		return "(" + pre + " <-> " + post + ")";
	}
}