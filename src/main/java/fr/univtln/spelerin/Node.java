package fr.univtln.spelerin;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Node{
	private Set<Formule> formules = new HashSet<>();
	private Set<Formule> allFormules = new HashSet<>(); //pour vérif fermeture
	private Set<Node> childs;
	private boolean isClosed = false;

	private Node(Set<Formule> formules){
		this.formules.addAll(formules);
		allFormules.addAll(formules);
	}

	public static Node ofFormules(Set<Formule> formules){
		return new Node(formules);
	}


	@Override
	public String toString(){
		return formules.toString();
	}

	public void verifyIfClosed(){
		//Branche closede si on a une formule et sa négation dans allFormules
		allFormules.forEach(f1 -> {
			if(f1.getName().equals("not")){
				allFormules.forEach(f2 -> {
					if(f1.getPost().equals(f2))
						isClosed = true;
				});
			}
		});
	}

	public boolean isFinished(){
		//Noeud finished si toutes les formules sont atomiques (plus étendables)
		for(Formule f : formules){
			if (f instanceof Var) continue;

			if (f.getName().equals("not") && f.getPost() instanceof Var) continue;

			return false;
		}

	return true;
	}

	public Set<Node> getDeeperChilds(){
		if (this.getChilds() == null || this.getChilds().isEmpty()) {
			return new HashSet<>(Set.of(this));
		}

		Set<Node> deeperChilds = new HashSet<>(this.getChilds());

		while (!deeperChilds.isEmpty()){
			Set<Node> nextChilds = new HashSet<>();
			for(Node child : deeperChilds){
				if(child.getChilds() != null && !child.getChilds().isEmpty()){
					nextChilds.addAll(child.getChilds());
				}
			}

			//On s'arrete quand les enfants sont vides
			if (!nextChilds.isEmpty()) {
				deeperChilds = nextChilds;
			} else {
				break;
			}
		}

		return deeperChilds;
	}

	public void extend(Set<Node> childs){
		childs.forEach(c -> c.allFormules.addAll(this.getAllFormules()));
		this.childs = childs;
	}
}