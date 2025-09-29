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

		Set<Node> nodesToAffect = Set.of(root); //Noeuds qu'on étend (dont on affecte les enfants)
		Set<Node> nodesToTreat = Set.of(root); //Noeuds qu'on traite (dont on regarde la formule) ! Faudrait un ordre : File"
		boolean toDo = true; //Pour gerer la terminaison
		boolean result; //Pour le resultat de l'algo
		//Tant qu'on a des noeufs à traiter ou qu'une branche reste ouverte
		while(toDo){
			//Faire des verifs là :

			//Pour tout les noeuds à traiter
			for(Node nTT : nodesToTreat){
				//Pour toutes les formules du noeud
				for(Formule form : nTT.getFormules()){
					//Creer methode sur formule qui renvoies les noeuds enfants que ça crée : Set<Node> childs = f.toChildNodes()
					//Pour tout les noeuds toAffect
					for(Node nTA : nodesToAffect){
						//On étend le noeud na.extend(childs) : ajoute les enfants, étend allFormules et change formules
						//Verif des enfants :
							//Si noeud fini et pas close -> on fini tout, toDo et result false
							//Si noeud fermé -> on l'oublis juste
							//Sinon, il devient a affecté et traité
						
						//Important : voir si on étend bien les branches où on est et pas les voisines !!
					}
				
				}
			}
		}
		return false;
	}
}