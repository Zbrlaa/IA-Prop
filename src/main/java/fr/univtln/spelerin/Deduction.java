package fr.univtln.spelerin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import fr.univtln.spelerin.connecteurs.And;
import fr.univtln.spelerin.connecteurs.Not;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor()
public class Deduction{
	private Set<Formule> pre;
	private Formule post;
	
	public static Deduction deduction(Set<Formule> pre, Formule post){
		return new Deduction(pre, post);
	}

	@Override
	public String toString(){
		return pre + " ⊢ " + post;
	}

	public Set<Formule> toFormules(){
		Set<Formule> formules = new HashSet<>(pre);
		formules.add(Not.not(post));

		return formules;
	}

	//Méthode Tableaux
	public boolean isProved(){
		//Ca peut etre optimisé et fait plus joliment mais ça m'a déjà pris énormément de temps & j'ai pas envie de tout cassé

		Set<Formule> formules = this.toFormules();
		Node root = Node.ofFormules(formules);

		Set<Node> nodesToExtend; //Noeuds qu'on étend (auquel on affecte les enfants)
		Queue<Node> nodesToTreat = new LinkedList<>(List.of(root)); //Noeuds qu'on traite (dont on regarde la formule), List pour avoir un ordre

		//Tant qu'on a des noeufs à traiter
		while(!nodesToTreat.isEmpty()){
			Node currentNode = nodesToTreat.poll();
			// System.out.println("Traitement du noeud courant : " + currentNode);
			nodesToExtend = currentNode.getDeeperChilds();

			//Pour toutes les formules du noeud
			for(Formule nTTForms : currentNode.getFormules()){
				// System.out.println("Noeuds à étendre : " + nodesToExtend);
				// System.out.println("Traitement formule : " + nTTForms);

				Set<Node> nextToExpend = new HashSet<>();
				
				//Recuperer les noeuds enfants de la formule
				Set<Node> childs = nTTForms.toChildNodes();
				// System.out.println("Noeuds enfants : " + childs);

				if(!childs.isEmpty()){
					for(Node nTE : nodesToExtend){
						// System.out.println("Extension de : " + nTE);
						//On étend le noeud : ajoute les enfants
						nTE.extend(childs);
						
						for(Node c : nTE.getChilds()){
							// System.out.println("Traitement nouvel enfant : " + c);
							//On l'ajoute aux noeuds à traiter
							nodesToTreat.add(c);

							c.verifyIfClosed();
							if(!c.isClosed()){
								// System.out.println("Ajout à nextToExpend");
								nextToExpend.add(c);
							}
						}
					}
				}
				if(!nextToExpend.isEmpty())
					nodesToExtend = nextToExpend;
			}
		}

		// System.out.println("Tout les noeuds ont été traités");
		for(Node finBranche : root.getDeeperChilds()){
			finBranche.verifyIfClosed();
			if(!finBranche.isClosed())
				return false; //Au moins une branche est ouverte
		}
		return true; //Toutes les branches sont fermées
	}
}