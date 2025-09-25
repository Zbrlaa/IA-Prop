package fr.univtln.spelerin;

import fr.univtln.spelerin.connecteurs.Not;

import java.util.Map;

import fr.univtln.spelerin.connecteurs.And;
import fr.univtln.spelerin.connecteurs.Or;
import fr.univtln.spelerin.connecteurs.Impl;
import fr.univtln.spelerin.connecteurs.Equi;


/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		Formule f = Equi.equi(And.and(Or.or(Not.not(Var.var("a")), Var.var("b")), Impl.impl(Var.var("c"), Var.var("d"))), Or.or(Var.var("a"), Var.var("d")));
		System.out.println("F: " + f);
		System.out.println("Vars: " + f.getVarset());

		Interpretation i = new Interpretation(Map.of("a", true, "b", false, "c", true, "d", false));
		System.out.println("I: " + i);
		System.out.println("F(I): " + f.value(i));

		System.out.println("All F(I): " + f.values());
		
		System.out.println("Normal forme: " + f.toNormalForm());
	}
}