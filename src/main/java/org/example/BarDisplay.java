package org.example;

import javax.swing.*;
import java.awt.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BarDisplay extends JPanel {
    private String query;
    private final JTextArea textArea;

    public BarDisplay() {
        this.setPreferredSize(new Dimension(Data.WIDTH, Data.BAR_SIZE));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 6));
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        this.textArea = new JTextArea();
        this.textArea.setFont(new Font("Arial",Font.PLAIN, 17));
        this.textArea.setPreferredSize(new Dimension(Data.WIDTH -100, Data.BAR_SIZE/2));
        this.add(new JScrollPane(this.textArea));
        JButton search = new JButton("Search");
        this.add(search);
        search.addActionListener((e) -> {
            this.query = this.textArea.getText();
            if (!this.query.isEmpty()) {
                try {
                    this.query = ApiSearch.performSearch(URLEncoder.encode(this.query, StandardCharsets.UTF_8));
                    this.updateEditorPaneText();
                } catch (Exception var3) {
                }
            } else {
                System.out.println("Please enter a search query.");
            }

        });
    }

    private void updateEditorPaneText() {
        JEditorPane var10000 = Data.EDITOR_PANE;
        String var10001 = this.convertLinksToHtml();
        var10000.setText("<html>" + var10001.replaceAll("\n", "<br>") + "</html>");
        Data.EDITOR_PANE.setCaretPosition(0);
    }

    private String convertLinksToHtml() {
        Pattern pattern = Pattern.compile("(https?://[\\w-]+(\\.[\\w-]+)+(/[^\\s]*)?)");
        Matcher matcher = pattern.matcher(this.query);
        StringBuilder result = new StringBuilder();

        while(matcher.find()) {
            String var10002 = matcher.group();
            matcher.appendReplacement(result, "<a href=\"" + var10002 + "\">" + matcher.group() + "</a>");
        }

        matcher.appendTail(result);
        return result.toString();
    }
}
