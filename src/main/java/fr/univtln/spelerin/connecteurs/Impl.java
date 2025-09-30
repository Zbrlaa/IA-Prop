package fr.univtln.spelerin.connecteurs;

import java.util.Set;

import fr.univtln.spelerin.Formule;
import fr.univtln.spelerin.Interpretation;
import fr.univtln.spelerin.Node;
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
		return !pre.value(i) || post.value(i);
	}

	@Override
	public Formule toNormalForm(){
		return Or.or(Not.not(pre), post).toNormalForm();
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
		return "(" + pre.toHTML() + " &rightarrow; " + post.toHTML() + ")";
	}

	@Override
	public Set<Node> toChildNodes(){
		Node c1 = Node.ofFormules(Set.of(Not.not(pre)));
		Node c2 = Node.ofFormules(Set.of(post));
		return Set.of(c1,c2);
	}
}