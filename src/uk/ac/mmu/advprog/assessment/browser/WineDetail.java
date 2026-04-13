package uk.ac.mmu.advprog.assessment.browser;

import javax.swing.*;
import java.awt.*;

public class WineDetail extends JPanel {

    private JLabel name = new JLabel("Name: ");
    private JLabel winery = new JLabel("Winery: ");
    private JLabel type = new JLabel("Type: ");
    private JLabel country = new JLabel("Country: ");
    private JLabel blend = new JLabel("Blend: ");
    private JLabel grape = new JLabel("Grapes: ");
    private JLabel abv = new JLabel("ABV: ");
    private JLabel acidity = new JLabel("Acidity: ");
    private JLabel body = new JLabel("Body: ");

    public WineDetail() {
        setPreferredSize(new Dimension(300, 600));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(name);
        add(winery);
        add(type);
        add(country);
        add(blend);
        add(grape);
        add(abv);
        add(acidity);
        add(body);
    }
}
