import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameWindow extends JFrame {
    private final WordLoader wordLoader;
    private String solution;
    private JTextField guessInputField;

    private JButton endGameButton;
    private JButton submitButton;
    private JPanel gamePanel;
    private boolean isGameOver = false;
    private int wordLength = 5; // Default word length
    private List<GuessFeedback> guesses = new ArrayList<>();

    public GameWindow() {
        super("Word Guess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        setLayout(new BorderLayout());

        // Initialize wordLoader in a try-catch block
        WordLoader tempLoader;
        try {
            tempLoader = new WordLoader("words.txt");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading words. Please check words.txt file.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            tempLoader = null; // Set to null if loading fails to avoid uninitialized variable
        }
        wordLoader = tempLoader; // Assign the temporary loader to wordLoader

        // Proceed to show the menu screen only if wordLoader is initialized
        if (wordLoader != null) {
            showMenuScreen(); // Display the menu screen initially
        }
    }

    private void showMenuScreen() {
        // Clear any existing components
        getContentPane().removeAll();

        // Set the background color for the frame
        getContentPane().setBackground(new Color(50, 50, 50)); // Dark gray background

        // Create a panel for the title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(50, 50, 50)); // Match the background color
        JLabel titleLabel = new JLabel("Word Guess Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(255, 215, 0)); // Gold color for the title
        titlePanel.add(titleLabel);

        // Create a panel for buttons with grid layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(new Color(50, 50, 50)); // Match the background color

        // Create buttons with styling
        JButton playButton = createStyledButton("Play");
        JButton howToPlayButton = createStyledButton("How To Play");
        JButton creditsButton = createStyledButton("Credits");

        // Add action listeners for each button
        playButton.addActionListener(e -> showWordLengthSelectionScreen());
        howToPlayButton.addActionListener(e -> showHowToPlay());
        creditsButton.addActionListener(e -> showCredits());

        // Add buttons to button panel
        buttonPanel.add(playButton);
        buttonPanel.add(howToPlayButton);
        buttonPanel.add(creditsButton);

        // Add title and button panels to the frame
        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    // Helper method to create styled buttons with hover effects
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 130, 180)); // Steel blue background
        button.setFocusPainted(false); // Remove focus outline
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover

        // Hover effect using mouse listeners
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237)); // Lighter blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180)); // Original color
            }
        });

        return button;
    }




    private void showWordLengthSelectionScreen() {
        // Clear components
        getContentPane().removeAll();

        // Set background color for the frame
        getContentPane().setBackground(new Color(50, 50, 50)); // Dark gray background

        // Create word length selection panel
        JPanel lengthPanel = new JPanel();
        lengthPanel.setLayout(new BoxLayout(lengthPanel, BoxLayout.Y_AXIS));
        lengthPanel.setBackground(new Color(50, 50, 50)); // Match the background color

        // Title label
        JLabel promptLabel = new JLabel("Choose Word Length");
        promptLabel.setFont(new Font("Arial", Font.BOLD, 28));
        promptLabel.setForeground(new Color(255, 215, 0)); // Gold color
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Define the range of word lengths (e.g., 3 to 10)
        Integer[] wordLengths = {3, 4, 5, 6, 7, 8, 9, 10};
        JComboBox<Integer> lengthDropdown = new JComboBox<>(wordLengths);
        lengthDropdown.setSelectedItem(5); // Default selection (e.g., 5 letters)
        lengthDropdown.setFont(new Font("Arial", Font.PLAIN, 18));
        lengthDropdown.setForeground(Color.BLACK);
        lengthDropdown.setBackground(new Color(255, 255, 255)); // White background for dropdown
        lengthDropdown.setMaximumSize(new Dimension(200, 40));
        lengthDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Styled "Start Game" button
        JButton startButton = createStyledButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Action listener for the start button
        startButton.addActionListener(e -> {
            wordLength = (Integer) lengthDropdown.getSelectedItem(); // Get selected word length
            startGame(); // Start the game with the chosen word length
        });

        // Add components to the panel with spacing
        lengthPanel.add(Box.createVerticalStrut(50)); // Spacer at top
        lengthPanel.add(promptLabel);
        lengthPanel.add(Box.createVerticalStrut(20)); // Spacer between components
        lengthPanel.add(lengthDropdown);
        lengthPanel.add(Box.createVerticalStrut(20));
        lengthPanel.add(startButton);

        // Add the length selection panel to the frame
        add(lengthPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }



    private void showHowToPlay() {
        JOptionPane.showMessageDialog(this,
                "How to Play:\n" +
                        "1. Enter a word guess of the chosen length.\n" +
                        "2. Green: Correct letter in the correct position.\n" +
                        "3. Yellow: Correct letter, wrong position.\n" +
                        "4. Gray: Letter is not in the word.\n" +
                        "Guess the word within the allowed attempts!",
                "How To Play", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showCredits() {
        JOptionPane.showMessageDialog(this,
                "Word Guess Game\n" +
                        "Developed by Nischal Shrestha\n" +
                        "Inspired by Wordle",
                "Credits", JOptionPane.INFORMATION_MESSAGE);
    }

    private void startGame() {
        // Clear any existing components
        getContentPane().removeAll();
        guesses = new ArrayList<>();
        isGameOver = false;
        solution = wordLoader.getRandomWord(wordLength); // Get a word of chosen length

        // Create the main game panel to display guesses
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGameBoard(g);
            }
        };
        gamePanel.setBackground(new Color(30, 30, 30)); // Dark background for the game area
        gamePanel.setPreferredSize(new Dimension(800, 400));
        gamePanel.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2)); // Border for visual appeal

        add(gamePanel, BorderLayout.CENTER);

        // Create input panel with buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(50, 50, 50)); // Match the overall theme
        inputPanel.setLayout(new FlowLayout());

        // Style the input field for guesses
        guessInputField = new JTextField(wordLength);
        guessInputField.setFont(new Font("Arial", Font.PLAIN, 18));
        guessInputField.setPreferredSize(new Dimension(200, 40));

        // Style the "Submit Guess" button
        submitButton = createStyledButton("Submit Guess");
        submitButton.setPreferredSize(new Dimension(150, 40));
        submitButton.addActionListener(new SubmitGuessListener()); // Attach the listener here

        // Style the "Reset" button
        JButton resetButton = createStyledButton("Reset");
        resetButton.setPreferredSize(new Dimension(100, 40));
        resetButton.addActionListener(e -> resetGame()); // Reset game action

        // Style the "End Game" button
        endGameButton = createStyledButton("End Game");
        endGameButton.setPreferredSize(new Dimension(100, 40));
        endGameButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to end the game?",
                    "End Game", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                isGameOver = true;
                submitButton.setEnabled(false);  // Disable the submit button
                JOptionPane.showMessageDialog(this, "Game ended.");
                showMenuScreen(); // Return to the main menu, or use `dispose()` to close the window
            }
        });

        // Add components to the input panel
        inputPanel.add(new JLabel("Enter your guess: "));
        inputPanel.add(guessInputField);
        inputPanel.add(submitButton);
        inputPanel.add(resetButton);
        inputPanel.add(endGameButton);

        // Add the input panel to the frame
        add(inputPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }



    private void drawGameBoard(Graphics g) {
        int tileSize = 40;
        int tileSpacing = 10;
        int startY = 50;

        g.setFont(new Font("Arial", Font.BOLD, 24));
        for (int row = 0; row < guesses.size(); row++) {
            GuessFeedback guess = guesses.get(row);
            for (int col = 0; col < wordLength; col++) {
                int x = 100 + col * (tileSize + tileSpacing);
                int y = startY + row * (tileSize + tileSpacing);
                drawTile(g, x, y, guess.getGuess()[col], guess.getFeedback()[col], tileSize);
            }
        }
    }

    private void resetGame() {
        // Reset the game state
        guesses.clear();
        isGameOver = false;
        solution = wordLoader.getRandomWord(wordLength); // Generate a new solution word

        // Clear and enable input fields/buttons
        guessInputField.setText("");
        submitButton.setEnabled(true);

        // Refresh the game panel
        gamePanel.repaint();
    }

    private void drawTile(Graphics g, int x, int y, char letter, int result, int tileSize) {
        Color tileColor;
        switch (result) {
            case 2 -> tileColor = Color.GREEN;
            case 1 -> tileColor = Color.YELLOW;
            default -> tileColor = Color.GRAY;
        }

        g.setColor(tileColor);
        g.fillRect(x, y, tileSize, tileSize);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, tileSize, tileSize);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(Character.toUpperCase(letter)), x + tileSize / 3, y + (int) (tileSize / 1.5));
    }

    private void processGuess(String guessInput) {
        if (isGameOver) {
            return; // If the game is over, do nothing
        }

        guessInput = guessInput.toLowerCase();

        // Validate input length and check if it exists in the word list
        if (guessInput.length() != wordLength || !wordLoader.getWordList(wordLength).contains(guessInput)) {
            JOptionPane.showMessageDialog(this, "Invalid guess. Please enter a valid " + wordLength + "-letter word.");
            return;
        }

        // Generate feedback for the guess and update the game panel
        GuessFeedback feedback = new GuessFeedback(guessInput.toCharArray(), solution);
        guesses.add(feedback);
        gamePanel.repaint();

        // Check if the player guessed the word correctly
        boolean isCorrectGuess = Arrays.stream(feedback.getFeedback()).allMatch(i -> i == 2);
        if (isCorrectGuess) {
            JOptionPane.showMessageDialog(this, "Congratulations! You've guessed the word!");
            isGameOver = true; // Set the game over flag to true
            submitButton.setEnabled(false); // Disable the submit button to stop further input
            return;
        }

        // Check if the player has used all attempts
        if (guesses.size() >= 6) {
            JOptionPane.showMessageDialog(this, "Game over! The correct word was: " + solution);
            isGameOver = true; // Set the game over flag to true
            submitButton.setEnabled(false); // Disable the submit button to stop further input
        }
    }

    private class SubmitGuessListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String guess = guessInputField.getText().trim();
            processGuess(guess);
            guessInputField.setText(""); // Clear the input field after submitting
        }
    }
}
