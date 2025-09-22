package fr.univtln.spelerin;

import fr.univtln.spelerin.connecteurs.Not;
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
		System.out.println(f);
	}
}