package uk.ac.mmu.advprog.assessment.browser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WineDetail extends JPanel {

    private JLabel name = new JLabel("");
    private JLabel winery = new JLabel("");
    private JLabel type = new JLabel("");
    private JLabel country = new JLabel("");
    private JLabel blend = new JLabel("");
    private JPanel grapes = new JPanel();
    private JLabel abv = new JLabel("");
    private JLabel acidity = new JLabel("");
    private JLabel body = new JLabel("");
    private JPanel pairings = new JPanel();

    public WineDetail() {
        setPreferredSize(new Dimension(300, 600));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        CustomJPanel namePanel = new CustomJPanel(new JLabel("Name: "), name);
        add(namePanel.panel);

        CustomJPanel wineryPanel = new CustomJPanel(new JLabel("Winery: "), winery);
        add(wineryPanel.panel);

        CustomJPanel typePanel = new CustomJPanel(new JLabel("Type: "), type);
        add(typePanel.panel);

        CustomJPanel countryPanel = new CustomJPanel(new JLabel("Country: "), country);
        add(countryPanel.panel);

        CustomJPanel abvPanel = new CustomJPanel(new JLabel("ABV: "), abv);
        add(abvPanel.panel);

        CustomJPanel blendPanel = new CustomJPanel(new JLabel("Blend: "), blend);
        add(blendPanel.panel);

        CustomJPanel acidityPanel = new CustomJPanel(new JLabel("Acidity: "), acidity);
        add(acidityPanel.panel);

        CustomJPanel bodyPanel = new CustomJPanel(new JLabel("Body: "), body);
        add(bodyPanel.panel);

        CustomJPanel grapePanel = new CustomJPanel(new JLabel("Grape(s): "), grapes);
        add(grapePanel.panel);

        CustomJPanel pairingPanel = new CustomJPanel(new JLabel("Pairing(s): "), pairings);
        add(pairingPanel.panel);

    }

    public void setData(Wine selectedWine) {
        name.setText(selectedWine.name);
        winery.setText(selectedWine.winery);
        type.setText(selectedWine.type);
        country.setText(selectedWine.country);
        abv.setText(selectedWine.abv);
        blend.setText(selectedWine.blend);
        acidity.setText(selectedWine.acidity);
        body.setText(selectedWine.body);
        JPanel grapePanel = new JPanel();
        for(String grape: selectedWine.grapes) {
            JLabel pairingLabel = new JLabel(grape);
            grapePanel.add(pairingLabel);
        }
        grapes.add(grapePanel);

        pairings.removeAll();
        JPanel pairingPanel = new JPanel();
        for(String pairing: selectedWine.pairings) {
            JLabel pairingLabel = new JLabel(pairing);
            pairingPanel.add(pairingLabel);
        }
        pairings.add(pairingPanel);
    }

    public void clearData() {
        name.setText("");
        winery.setText("");
        type.setText("");
        country.setText("");
        abv.setText("");
        blend.setText("");
        acidity.setText("");
        body.setText("");
        grapes.removeAll();
        pairings.removeAll();
    }

}
