package fr.univtln.spelerin.connecteurs;

import fr.univtln.spelerin.Formule;
import fr.univtln.spelerin.VarSet;
import lombok.Getter;


@Getter
public class Not extends Formule{
	private Not(Formule post, VarSet varset){
		super(null, post, varset);
	}
	
	public static Formule not(Formule post){
		VarSet varset = post.getVarset();
		return new Not(post, varset);
	}

	@Override
	public String toString(){
		return "!" + post;
	}
}