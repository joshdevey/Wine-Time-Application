package uk.ac.mmu.advprog.assessment.browser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class CustomJPanel extends JPanel {

    public JPanel panel;

    public CustomJPanel(JLabel label, JLabel value) {
        this.panel = new JPanel();
        JPanel labelPanel = new JPanel();
        JPanel valuePanel = new JPanel();
        labelPanel.add(label);
        valuePanel.add(value);
        panel.add(labelPanel);
        panel.add(valuePanel);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(2, 1));
    }

    public CustomJPanel(JLabel label, JPanel values) {
        this.panel = new JPanel();
        JPanel labelPanel = new JPanel();
        JPanel valuePanel = new JPanel();
        labelPanel.add(label);
        valuePanel.add(values);
        panel.add(labelPanel);
        panel.add(valuePanel);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(2, 1));
    }
}
