package fr.univtln.spelerin;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Formule{
    protected Formule pre;
	protected Formule post;
    protected VarSet varset;

    //public boolean value(Interpretation i);
}