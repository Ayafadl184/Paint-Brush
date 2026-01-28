
package paintbrush;

import java.awt.Color;
import javax.swing.JFrame;

public class Paintbrush {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        Panel Panel = new Panel();
        f.setContentPane(Panel);
        f.setSize(800, 700);
        f.setTitle("Paint Brush App");
        f.setVisible(true);
        Panel.setBackground(Color.WHITE);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
    }
}














































