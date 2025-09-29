package fr.univtln.spelerin;

import java.util.Set;

import fr.univtln.spelerin.connecteurs.And;
import fr.univtln.spelerin.connecteurs.Not;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor()
public class Deduction{
	private Formule pre;
	private Formule post;
	
	public static Deduction deduction(Formule pre, Formule post){
		return new Deduction(pre, post);
	}

	@Override
	public String toString(){
		return pre + " ⊢ " + post;
	}

	public Formule toFormule(){
		return And.and(pre, Not.not(post));
	}

	//Méthode Tableaux
	public boolean isProved(){
		Formule f = this.toFormule();
		Node root = Node.ofFormule(f);
		
		Set<Node> nodesToTreat = Set.of(root);
		boolean verif = true;
		while(verif){
			for(Node n : nodesToTreat){
				
			}
		}

		return false;
	}
}
