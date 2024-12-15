package org.example;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.HyperlinkEvent.EventType;
import java.awt.*;
import java.net.URI;

public class ResultDisplay extends JScrollPane {
    public ResultDisplay() {
        this.setPreferredSize(new Dimension(Data.WIDTH, Data.HEIGHT-5));
        this.setBorder(new LineBorder(Color.black, 2));
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        Data.EDITOR_PANE.setContentType("text/html");
        Data.EDITOR_PANE.setEditable(false);
        Data.EDITOR_PANE.setBackground(new Color(255, 255, 120));
        Data.EDITOR_PANE.addHyperlinkListener((e) -> {
            if (EventType.ACTIVATED.equals(e.getEventType())) {
                try {
                    URI uri = new URI(e.getDescription());
                    Desktop.getDesktop().browse(uri);
                } catch (Exception ignored) {
                }
            }

        });
        this.setViewportView(Data.EDITOR_PANE);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawRect(0, 0, Data.WIDTH -1, Data.HEIGHT-1);
    }
}

