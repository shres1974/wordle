import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordLoader {
    private final Map<Integer, List<String>> wordsByLength = new HashMap<>();

    public WordLoader(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String word;
            while ((word = br.readLine()) != null) {
                word = word.trim().toLowerCase();
                int length = word.length();
                if (length >= 3 && length <= 10) { // Only store words of length 3 to 10
                    wordsByLength.computeIfAbsent(length, k -> new ArrayList<>()).add(word);
                }
            }
        }
    }

    // Method to return a random word of a specified length
    public String getRandomWord(int length) {
        List<String> words = wordsByLength.get(length);
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("No words of length " + length + " found.");
        }
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    // Returns a list of all words of the specified length for validation purposes
    public List<String> getWordList(int length) {
        return wordsByLength.getOrDefault(length, new ArrayList<>());
    }
}
