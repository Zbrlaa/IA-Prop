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
		return pre.value(i) == post.value(i);
	}

	@Override
	public Formule toNormalForm(){
		return And.and(Or.or(Not.not(pre), post),
					Or.or(Not.not(post), pre)).toNormalForm();
	}

	@Override
	public Formule toCNF(){
		return this.toNormalForm().toCNF();
	}

	@Override
	public Formule toDNF(){
		return this.toNormalForm().toDNF();
	}

	@Override
	public String toHTML(){
		return "(" + pre.toHTML() + " &leftrightarrow; " + post.toHTML() + ")";
	}
}