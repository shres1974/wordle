import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GameWindow window = new GameWindow();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension(800, 600));
        window.pack();
        window.setVisible(true);
    }

}
