package fr.univtln.spelerin;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Interpretation{
	private Map<String, Boolean> values;
}