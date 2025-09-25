package fr.univtln.spelerin;

import fr.univtln.spelerin.connecteurs.Not;
import java.util.Map;

import fr.univtln.spelerin.connecteurs.And;
import fr.univtln.spelerin.connecteurs.Or;
import fr.univtln.spelerin.connecteurs.Impl;
import fr.univtln.spelerin.connecteurs.Equi;


public class App {
	public static void main(String[] args) {
		Formule f = Equi.equi(And.and(Or.or(Not.not(Var.var("a")),
											Var.var("b")),
									Impl.impl(Var.var("c"),
											Var.var("d"))),
							Or.or(Var.var("a"),
								Var.var("d")));

		System.out.println("F : " + f);
		System.out.println("Vars : " + f.getVarset());

		Interpretation i = new Interpretation(Map.of("a", true, "b", false, "c", true, "d", false));
		System.out.println("I : " + i);
		System.out.println("F(I) : " + f.value(i));

		System.out.println("All F(I) : " + f.values());
		
		System.out.println("Forme normale : " + f.toNormalForm());
		System.out.println("CNF : " + f.toCNF());
		System.out.println("DNF : " + f.toDNF());

		System.out.println("\nTest sur plus petite formule :");
		Formule f2 = And.and(Not.not(Or.or(Var.var("p"),
											Not.not(Var.var("q")))),
							Impl.impl(Var.var("s"),
									Var.var("t")));

		System.out.println("F2 : " + f2);
		System.out.println("HTML : " + f2.toHTML());
		System.out.println("CNF : " + f2.toCNF());
		System.out.println("DNF : " + f2.toDNF());

	
		//Conclusion !!
		//Affichage et interpretations reussis
		//NormalForm, CNF et DNF correctes
		//Pas sous forme minimale (présence de tautologies et doublons) mais pas requis et demanderait trop de temps
		//Affichage HTML : je pense que c'est bon, pas d'exemple avec correction pour vérifier
	}
}