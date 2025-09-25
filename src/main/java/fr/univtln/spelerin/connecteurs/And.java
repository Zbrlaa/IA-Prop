package fr.univtln.spelerin.connecteurs;

import fr.univtln.spelerin.Formule;
import fr.univtln.spelerin.Interpretation;
import fr.univtln.spelerin.VarSet;
import lombok.Getter;


@Getter
public class And extends Formule{
	private And(Formule pre, Formule post, VarSet varset){
		super("and", pre, post, varset);
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
		return pre.value(i) && post.value(i);
	}

	@Override
	public Formule toNormalForm(){
		return and(pre.toNormalForm(), post.toNormalForm());
	}

	@Override
	public Formule toCNF(){
		Formule preNF = pre.toNormalForm();
		Formule postNF = post.toNormalForm();

		return and(preNF.toCNF(), postNF.toCNF());
	}

	@Override
	public Formule toDNF(){
		//Maxi redondance car j'appelle toDNF partout mais au moins on loupe rien
		Formule preNF = pre.toNormalForm().toDNF();
		Formule postNF = post.toNormalForm().toDNF();

		if(preNF.getName().equals("or")){
			return Or.or(And.and(postNF, preNF.getPre()).toDNF(),
						And.and(postNF, preNF.getPost()).toDNF()).toDNF();
		}
		else if(postNF.getName().equals("or")){
			return Or.or(And.and(preNF, postNF.getPre()).toDNF(),
						And.and(preNF, postNF.getPost()).toDNF()).toDNF();
		}
		return and(preNF.toDNF(), postNF.toDNF());
	}

	@Override
	public String toHTML(){
		return "(" + pre.toHTML() + " &wedge; " + post.toHTML() + ")";
	}
}