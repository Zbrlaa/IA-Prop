package fr.univtln.spelerin;

import java.util.HashSet;
import java.util.Set;

import fr.univtln.spelerin.connecteurs.Or;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Node{
	private Set<Formule> formules = new HashSet<>();
	private Set<Formule> allFormules = new HashSet<>(); //pour vérif fermeture
	private Node enfants1;
	private Node enfants2;
	private boolean isClosed = false;

	public Node(Formule formule){
		formules.add(formule);
		allFormules.add(formule);
	}

	public static Node ofFormule(Formule formule){
		return new Node(formule);
	}


	@Override
	public String toString() {
		return formules + " : [" + enfants1 + ", " + enfants2 + "]";
	}

	public void verify(){
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

	public void applyRules(){
		
	}
}