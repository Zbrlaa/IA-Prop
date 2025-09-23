package fr.univtln.spelerin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

	
	public List<Interpretation> getInterpretations(){
		List<String> varList = new ArrayList<>(vars);
		int size = varList.size();
		int nb = 1 << size;

		List<Interpretation> interpretations = new ArrayList<>();
		for(int i=0; i<nb; i++){
			Map<String, Boolean> map = new HashMap<>();
			for(int j=0; j<size; j++){
				int b = i>>j&1;
				map.put(varList.get(j), b==1);
			}
			interpretations.add(new Interpretation(map));
		}

		return interpretations;
	}
}