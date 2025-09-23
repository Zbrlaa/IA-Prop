package fr.univtln.spelerin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = lombok.AccessLevel.PUBLIC)
@Getter
public class VarSet{
	private Set<String> vars;

	public VarSet union(VarSet varset){
		Set<String> union = new HashSet<>(this.getVars());
		union.addAll(varset.getVars());
		return new VarSet(union);
	}

	@Override
	public String toString() {
		return vars.toString();
	}
}