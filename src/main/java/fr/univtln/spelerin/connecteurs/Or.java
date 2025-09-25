package fr.univtln.spelerin.connecteurs;

import fr.univtln.spelerin.Formule;
import fr.univtln.spelerin.Interpretation;
import fr.univtln.spelerin.VarSet;
import lombok.Getter;


@Getter
public class Or extends Formule{
	private Or(Formule pre, Formule post, VarSet varset){
		super("or", pre, post, varset);
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
		return pre.value(i) || post.value(i);
	}

	@Override
	public Formule toNormalForm(){
		return or(pre.toNormalForm(), post.toNormalForm());
	}

	@Override
	public Formule toCNF(){
		//Maxi redondance car j'appelle toCNF partout mais au moins on loupe rien
		Formule preNF = pre.toNormalForm().toCNF();
		Formule postNF = post.toNormalForm().toCNF();

		if(preNF.getName().equals("and")){
			return And.and(Or.or(postNF, preNF.getPre()).toCNF(),
							Or.or(postNF, preNF.getPost()).toCNF()).toCNF();
		}
		else if(postNF.getName().equals("and")){
			return And.and(Or.or(preNF, postNF.getPre()).toCNF(),
							Or.or(preNF, postNF.getPost()).toCNF()).toCNF();
		}
		return or(preNF.toCNF(), postNF.toCNF());
	}

	@Override
	public Formule toDNF(){
		Formule preNF = pre.toNormalForm();
		Formule postNF = post.toNormalForm();

		return or(preNF.toDNF(), postNF.toDNF());
	}

	@Override
	public String toHTML(){
		return "(" + pre.toHTML() + " &vee; " + post.toHTML() + ")";
	}
}