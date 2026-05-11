package uk.ac.mmu.advprog.assessment.browser;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomJPanel extends JPanel {

    public JPanel panel;

    public CustomJPanel(JLabel label, JTextArea value) {
        this.panel = new JPanel();
        JPanel valuePanel = new JPanel();
        value.setLineWrap(true);
        value.setSize(new Dimension(150, 40));
        value.setOpaque(false);
        value.setWrapStyleWord(true);
        value.setBorder(null);
        value.setMargin(new Insets(0, 0, 0, 0));
        value.setEditable(false);
        valuePanel.add(value);
        panel.add(label);
        panel.add(valuePanel);
        panel.setBorder(new EmptyBorder(0, 5, 0, 5));
        panel.setLayout(new GridLayout(2, 1));
    }

    public CustomJPanel(JLabel label, JPanel values) {
        this.panel = new JPanel();
        JPanel valuePanel = new JPanel();
        valuePanel.add(values);
        panel.add(label);
        panel.add(valuePanel);
        panel.setBorder(new EmptyBorder(5, 10, 5, 10));
        panel.setLayout(new GridLayout(2, 1));
    }
}
