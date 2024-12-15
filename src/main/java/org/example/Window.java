

package org.example;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window() {
        this.setTitle("Google Search");
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel display = new JPanel();
        display.setLayout(new FlowLayout());
        display.setBackground(Color.cyan);
        display.setPreferredSize(new Dimension(Data.WIDTH, Data.HEIGHT+50));
        display.add(new BarDisplay());
        display.add(new ResultDisplay());
        this.add(display);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

