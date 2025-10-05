package fr.univtln.spelerin;

import java.util.Map;
import java.util.Set;

import fr.univtln.spelerin.connecteurs.Not;
import fr.univtln.spelerin.connecteurs.And;
import fr.univtln.spelerin.connecteurs.Or;
import fr.univtln.spelerin.connecteurs.Impl;
import fr.univtln.spelerin.connecteurs.Equi;


public class App {
	public static void main(String[] args) {
		//tp1();
	
		tp2();
	}

	private static void tp1(){
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

	private static void tp2(){
		Formule fp = And.and(Impl.impl(Var.var("p"), Var.var("r")),
							And.and(Equi.equi(Var.var("q"), Var.var("s")),
									And.and(Equi.equi(Var.var("r"), Var.var("p")),
											Var.var("p"))));
		Deduction p = Deduction.deduction(Set.of(fp), Var.var("s"));
		System.out.println(p);
		System.out.println(p.isProved());

		Formule fp2 = And.and(Var.var("a"), Var.var("b"));
		Deduction p2 = Deduction.deduction(Set.of(fp2), Var.var("c"));
		System.out.println(p2);
		System.out.println(p2.isProved());

		//Beaucoup trop long de faire le parser LaTex sans aide expérieure
	}
}