package fr.univtln.spelerin;
import java.util.Collections;
import java.util.Set;

import lombok.Getter;


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
		return i.getValues().get(name);
	}

	@Override
	public Formule toNormalForm() {
		return this;
	}

	@Override
	public Formule toCNF(){
		return this;
	}

	@Override
	public Formule toDNF(){
		return this;
	}

	@Override
	public String toHTML(){
		return name;
	}

	@Override
	public Set<Node> toChildNodes(){
		return Collections.emptySet();
	}
}