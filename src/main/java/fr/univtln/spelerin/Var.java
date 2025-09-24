package fr.univtln.spelerin;
import java.util.Set;

import lombok.Getter;
import lombok.val;


@Getter
public class Var extends Formule{
	private Var(String name, VarSet varset){
		super(name, null, null, varset);
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

	@Override
	public Formule toNormalForm() {
		return this;
	}
}