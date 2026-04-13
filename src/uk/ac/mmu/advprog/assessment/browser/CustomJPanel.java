package uk.ac.mmu.advprog.assessment.browser;

import javax.swing.*;
import java.awt.*;

public class CustomJPanel extends JPanel {

    public JPanel panel;

    public CustomJPanel(JLabel label, JLabel value) {
        {
            this.panel = new JPanel();
            JPanel labelPanel = new JPanel();
            JPanel valuePanel = new JPanel();
            labelPanel.add(label);
            valuePanel.add(value);
            panel.add(labelPanel);
            panel.add(valuePanel);
            panel.setLayout(new GridLayout(2, 1));
        }
    }
}
