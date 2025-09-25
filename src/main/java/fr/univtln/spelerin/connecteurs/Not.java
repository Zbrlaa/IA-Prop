package fr.univtln.spelerin.connecteurs;

import fr.univtln.spelerin.Formule;
import fr.univtln.spelerin.Interpretation;
import fr.univtln.spelerin.VarSet;
import lombok.Getter;


@Getter
public class Not extends Formule{
	private Not(Formule post, VarSet varset){
		super("not", null, post, varset);
	}
	
	public static Formule not(Formule post){
		VarSet varset = post.getVarset();
		return new Not(post, varset);
	}

	@Override
	public String toString(){
		return "!" + post;
	}

	public boolean value(Interpretation i){
		return !post.value(i);
	}

	@Override
	public Formule toNormalForm(){
		Formule postNF = post.toNormalForm();

		if (postNF.getName().equals("not")){ //!!a <=> a
			return postNF.getPost().toNormalForm();
		}
		else if(postNF.getName().equals("and")){ //!(a&b) <=> !a|!b
			return Or.or(not(postNF.getPre()), not(postNF.getPost())).toNormalForm();
		}
		else if(postNF.getName().equals("or")){ //!(a|b) <=> !a&!b
			return And.and(not(postNF.getPre()),not(postNF.getPost())).toNormalForm();
		}
		return not(postNF);
	}
}