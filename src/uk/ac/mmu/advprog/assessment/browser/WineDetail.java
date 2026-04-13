package uk.ac.mmu.advprog.assessment.browser;

import javax.swing.*;
import java.awt.*;

public class WineDetail extends JPanel {

    private JLabel name = new JLabel("");
    private JLabel winery = new JLabel("");
    private JLabel type = new JLabel("");
    private JLabel country = new JLabel("");
    private JLabel blend = new JLabel("");
    private JLabel grape = new JLabel("");
    private JLabel abv = new JLabel("");
    private JLabel acidity = new JLabel("");
    private JLabel body = new JLabel("");

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

    public void setData(Wine selectedWine) {
        name.setText(selectedWine.name);
        winery.setText(selectedWine.winery);
        type.setText(selectedWine.type);
        country.setText(selectedWine.country);
        abv.setText(selectedWine.aBV);
    }

    public void clearData() {
        name.setText("");
        winery.setText("");
        type.setText("");
        country.setText("");
        abv.setText("");
    }
}
