public class GuessFeedback {
    private final char[] guess;
    private final int[] feedback;

    public GuessFeedback(char[] guess, String solution) {
        this.guess = guess;
        this.feedback = evaluateGuess(solution);
    }

    private int[] evaluateGuess(String solution) {
        int[] feedback = new int[5];
        boolean[] usedInSolution = new boolean[5];

        // Step 1: Mark correct letters in correct places
        for (int i = 0; i < 5; i++) {
            if (guess[i] == solution.charAt(i)) {
                feedback[i] = 2; // Correct position
                usedInSolution[i] = true;
            }
        }

        // Step 2: Mark letters in wrong places but present in solution
        for (int i = 0; i < 5; i++) {
            if (feedback[i] != 2 && solution.indexOf(guess[i]) >= 0) {
                for (int j = 0; j < 5; j++) {
                    if (!usedInSolution[j] && guess[i] == solution.charAt(j)) {
                        feedback[i] = 1; // Correct letter, wrong position
                        usedInSolution[j] = true;
                        break;
                    }
                }
            }
        }
        return feedback;
    }

    public int[] getFeedback() {
        return feedback;
    }

    public char[] getGuess() {
        return guess;
    }

    public boolean matchesSolution(String word) {
        for (int i = 0; i < 5; i++) {
            if ((feedback[i] == 2 && guess[i] != word.charAt(i)) ||
                    (feedback[i] == 1 && (word.indexOf(guess[i]) < 0 || guess[i] == word.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

}
