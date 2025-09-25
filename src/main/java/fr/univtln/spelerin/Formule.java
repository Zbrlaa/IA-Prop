package fr.univtln.spelerin;

import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Formule{
	protected String name;
	protected Formule pre;
	protected Formule post;
	protected VarSet varset;

	public abstract boolean value(Interpretation i);
	
	public Map<Interpretation, Boolean> values(){
		Map<Interpretation, Boolean> values = new HashMap<>();
		varset.getInterpretations().forEach(i -> values.put(i, this.value(i)));
		return values;
	}

	public abstract Formule toNormalForm();
	public abstract Formule toCNF();
	public abstract Formule toDNF();

	public abstract String toHTML();
}