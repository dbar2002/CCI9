// 8.8 Permutations with Dups: Write a method to compute all permutations of a
// string whose characters are not necessarily unique.
// The list of permutations should not have duplicates.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationsWithDups {

    public static List<String> getPermutations(String str) {
        List<String> permutations = new ArrayList<>();
        getPermutations("", str, permutations);
        return permutations;
    }

    private static void getPermutations(String prefix, String remaining, List<String> permutations) {
        int length = remaining.length();

        if (length == 0) {
            permutations.add(prefix);
            return;
        }

        Set<Character> usedCharacters = new HashSet<>();
        for (int i = 0; i < length; i++) {
            char currentChar = remaining.charAt(i);

            // Skip duplicate characters to avoid duplicates in permutations
            if (usedCharacters.contains(currentChar)) {
                continue;
            }

            usedCharacters.add(currentChar);

            String newPrefix = prefix + currentChar;
            String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
            getPermutations(newPrefix, newRemaining, permutations);
        }
    }

    public static void main(String[] args) {
        String input = "aab";
        List<String> permutations = getPermutations(input);

        for (String permutation : permutations) {
            System.out.println(permutation);
        }
    }
}
