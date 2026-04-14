package uk.ac.mmu.advprog.assessment.browser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomJPanel extends JPanel {

    public JPanel panel;

    public CustomJPanel(JLabel label, JTextArea value) {
        this.panel = new JPanel();
        JPanel valuePanel = new JPanel();
        value.setLineWrap(true);
        value.setSize(new Dimension(250, 100));
        value.setOpaque(false);
        valuePanel.add(value);
        panel.add(label);
        panel.add(valuePanel);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(2, 1));
    }

    public CustomJPanel(JLabel label, JPanel values) {
        this.panel = new JPanel();
        JPanel valuePanel = new JPanel();
        valuePanel.add(values);
        panel.add(label);
        panel.add(valuePanel);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(2, 1));
    }
}
