package fr.univtln.spelerin.connecteurs;

import java.util.Set;

import fr.univtln.spelerin.Formule;
import fr.univtln.spelerin.Interpretation;
import fr.univtln.spelerin.Node;
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

	@Override
	public Set<Node> toChildNodes(){
		Node c1 = Node.ofFormules(Set.of(pre,post));
		Node c2 = Node.ofFormules(Set.of(Not.not(pre), Not.not(post)));
		return Set.of(c1,c2);
	}
}