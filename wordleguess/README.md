# Word Guess Game
CREATED BY NISCHAL SHRESTHA


This is a Java-based Word Guess Game inspired by Wordle, where the player must guess a randomly selected word of a specific length (between 3 and 10 letters). The game provides feedback for each guess, highlighting letters that are correct and in the correct position, correct but in the wrong position, or incorrect. The player has a limited number of attempts to guess the correct word.

## Features

- **Word Length Selection**: Players can choose word lengths between 3 and 10 letters.
- **Random Word Generation**: Words are randomly selected from `words.txt`, a text file that includes a dictionary of possible words.
- **Dynamic Guess Validation**: The game checks that the guess matches the selected word length and provides feedback for each attempt.
- **Feedback System**: Each guessed letter is color-coded to indicate correctness:
    - **Green**: Correct letter in the correct position.
    - **Yellow**: Correct letter but in the wrong position.
    - **Gray**: Incorrect letter.
- **Game Controls**:
    - **Submit Guess**: Submit each guess for feedback.
    - **Reset Game**: Restart the game with a new random word.
    - **End Game**: Return to the main menu at any time.
- **Graphical User Interface (GUI)**: An intuitive GUI built using Java Swing.

## Installation

### Prerequisites
- Java Development Kit (JDK) 8 or later.
- A text editor or an IDE like IntelliJ IDEA or Eclipse.

### Setup
1. Clone or download the repository.
2. Place `words.txt` in the project’s root directory, containing words from 3 to 10 letters on separate lines.(Default one is provided!)

## Running the Game

### From the Command Line

1. Open a terminal or command prompt.
2. Navigate to the project directory:
   ```bash
   cd /path/to/wordleguess/src
3. Compile the Java Files:
```bash
javac Main.java GameWindow.java GuessFeedback.java WordLoader.java
```
4. Run the program:
```bash
java Main
```
## How to Play

1. **Select Word Length**: Choose a word length from the options provided on the initial screen.
2. **Guess the Word**: Type your guess in the input field and click "Submit Guess."
    - Each guess will display feedback with color-coded letters.
3. **Win or Lose**: Try to guess the word within six attempts.
    - If you guess the word correctly, a "Congratulations" message will appear.
    - If you use all attempts without guessing the word, the game will reveal the correct answer.
4. **Reset or End Game**:
    - Use the "Reset" button to start a new game with the same word length.
    - Use the "End Game" button to return to the main menu.

## Project Structure

- **`GameWindow.java`**: Main game window class that manages the GUI, game logic, and user interactions.
- **`WordLoader.java`**: Loads words from `words.txt` and filters them by length.
- **`GuessFeedback.java`**: (if used) Handles feedback logic for each guess, such as indicating which letters are correct, misplaced, or incorrect.

## Example of `words.txt`

Ensure `words.txt` includes words of various lengths (3 to 10 letters). Each word should be on a new line:

```plaintext
cat
tree
house
plane
friend
giraffe
elephant
...


MIT License

Copyright (c) 2024 Nischal Shrestha

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Created by Nischal Shrestha.
