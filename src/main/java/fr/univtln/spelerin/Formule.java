package fr.univtln.spelerin;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Formule{
	protected Formule pre;
	protected Formule post;
	protected VarSet varset;

	public abstract boolean value(Interpretation i);
}