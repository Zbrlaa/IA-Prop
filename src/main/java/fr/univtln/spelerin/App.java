package fr.univtln.spelerin;

import fr.univtln.spelerin.connecteurs.*;


/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		Formule f = Equi.equi(And.and(Or.or(Not.not(Var.var("a")), Var.var("b")), Impl.impl(Var.var("c"), Var.var("d"))), Or.or(Var.var("a"), Var.var("d")));
		System.out.println("F: " + f);
	}
}