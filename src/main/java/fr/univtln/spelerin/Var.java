package fr.univtln.spelerin;
import java.util.Set;

import lombok.Getter;
import lombok.val;


@Getter
public class Var extends Formule{
	//Je mets String et pas char pour pouvoir mettre une annotation genre a1
	private String name;

	private Var(String name, VarSet varset){
		super();
		this.name = name;
		this.varset = varset;
	}

	public static Formule var(String name){
		VarSet varset = new VarSet(Set.of(name));
		return new Var(name, varset);
	}

	@Override
	public String toString() {
		return name;
	}

	public boolean value(Interpretation i){
		boolean value = i.getValues().get(name);
		return value;
	}
}