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
	public String toString() {
		return formules + " : " + childs;
	}

	public void verifyIfClosed(){
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
		for(Formule f : formules){
			if(f.getName().equals("not") && !(f.getPost() instanceof Var) || !(f.getPost() instanceof Var))
				return false;
		}
		return true;
	}
}