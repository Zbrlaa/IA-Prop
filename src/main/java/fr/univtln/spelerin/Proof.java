package fr.univtln.spelerin;

import fr.univtln.spelerin.connecteurs.And;
import fr.univtln.spelerin.connecteurs.Not;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor()
public class Proof{
	private Formule pre;
	private Formule post;
	
	public static Proof proof(Formule pre, Formule post){
		return new Proof(pre, post);
	}

	@Override
	public String toString(){
		return pre + " ⊢ " + post;
	}

	public Formule toFormule(){
		return And.and(pre, Not.not(post));
	}

	//Méthode Tableaux
	public boolean valueByTab(){
		Formule f = this.toFormule();
		Node root = Node.ofFormule(f);
		System.out.println(root);
		return false;
	}
}
