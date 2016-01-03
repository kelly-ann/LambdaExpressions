package org.kelly_ann;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionalVsImperative {
	
	public static void main(String[] args) {
		// this example will sort names in 2 different ways:
		// 1. Imperatively (i.e. using for loops)
		// 2. Functionally (i.e. using lambda expressions and aggregation operators)
		
		// Req: given a list of names of US Presidential candidates - sort them alphabetically
		// and then print to screen the names of the Republican candidates
		// However, if the name "Donald Trump" appears in the list of candidates then his name should 
		// always appear first, no matter the alphabetical ordering. (LOL  :-D)
		
		// Step 1: Create a list of candidate names
		List<String> candidateNames = new ArrayList<>();
		candidateNames.add("Hillary Clinton");
		candidateNames.add("Jeb Bush");
		candidateNames.add("Marco Rubio");
		candidateNames.add("Donald Trump");
		candidateNames.add("Bernie Sanders");
		
		// Step 2: Create a map with the candidates party affiliations
		Map<String, String> partyAffiliations = new HashMap<>();
		partyAffiliations.put("Hillary Clinton", "D");
		partyAffiliations.put("Jeb Bush", "R");
		partyAffiliations.put("Marco Rubio", "R");
		partyAffiliations.put("Donald Trump", "R");
		partyAffiliations.put("Bernie Sanders", "D");
		
		// Step 3: Implement the Imperative solution.  Will be done in a separate method so we have 2
		// methods -- one for each solution.
		System.out.println("Imperative solution:");
		imperativeSolution(candidateNames, partyAffiliations);
		
		// Step 4: Implement the same code using the Functional solution.
		System.out.println("\nFunctional solution:");
		functionalSolution(candidateNames, partyAffiliations);
		
		// Done!
		System.out.println("\nDone. Same results in 2 different ways!");
		
	}
	
	public static void imperativeSolution(List<String> candidateNames, Map<String, String> partyAffiliations) {
		
		// 3.a sort the array list so that it has the order we want
		// we do this via an anonymous inner class
		// note: the fact the a the anonymous inner class only has 1 method should be a sign to us that
		// we can use a lambda expression here
		Collections.sort(candidateNames, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				// set up the compare to view any string as
				// "Donald Trump" is greater than
				if(s1.equals("Donald Trump") && !s2.equals("Donald Trump"))
					return -1; // s1 less than (i.e. the 0 index which means it's first in the sort)
				else if(!s1.equals("Donald Trump") && s2.equals("Donald Trump"))
					return 1; // s1 greater than (therefore, it will be AFTER "Donald Trump" in the list
				else
					return s1.compareTo(s2); // s1 equals "Donald Trump"
			}
		} );
		
		// 3.b use a for loop to walkthough the list of candidate, test if they are Republican, and 
		// if so, print a message to screen
		for (String oneCandidate : candidateNames) {
			if(partyAffiliations.get(oneCandidate).equals("R")) {
				System.out.println(oneCandidate + "(" + partyAffiliations.get(oneCandidate) + ")");
			}		
		}
		
		
		
	}

	
	public static void functionalSolution(List<String> candidateNames, Map<String, String> partyAffiliations) {
		
		// Step 4.a sort the array list in the order we want -- BUT do this with a lambda
		// rather than an anonymous function
		// the general form of the lambda function is:
		// (param1, param2) -> {logic of the lambda goes here};
		Collections.sort(candidateNames, (s1, s2) -> {
			if(s1.equals("Donald Trump") && !s2.equals("Donald Trump"))
				return -1; // s1 less than 
			else if(!s1.equals("Donald Trump") && s2.equals("Donald Trump"))
				return 1; // s1 greater than
			else
				return s1.compareTo(s2); // s1 equals "Donald Trump"
		});
		
		// Step 4.b: Use a set of chained operations (aggregated operations) to test if the candidates
		// are Republicans or Democrats
		// note: lambda functions are especially powerful when the output of one is fed as
		// input into the next.
		candidateNames.stream()
						.filter(candidateName -> partyAffiliations.get(candidateName).equals("R"))
						.map(republicanCandidate -> republicanCandidate + "(" + partyAffiliations.get(republicanCandidate) + ")")
						.forEach(republicanCandidate -> System.out.println(republicanCandidate));
		
		// the above says:
		// convert the candidateNames list of objects to a stream, then FILTER out the candidates w/ the
		// "R" (Republican).  once those are filtered out, take the remaining output (stream) and
		// MAP (i.e. save)  each republicanCandidate variable to the string "Name (Party)" (e.g. "Marco(R)")
		// and finally FOR EACH republicanCandidate variable that you got out of the map's output stream
		// print it to the console.
	}
	
}
