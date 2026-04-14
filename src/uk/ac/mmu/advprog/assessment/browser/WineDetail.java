package uk.ac.mmu.advprog.assessment.browser;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WineDetail extends JPanel {

    private JTextArea name = new JTextArea("");
    private JTextArea winery = new JTextArea("");
    private JTextArea type = new JTextArea("");
    private JTextArea country = new JTextArea("");
    private JTextArea blend = new JTextArea("");
    private JPanel grapes = new JPanel();
    private JTextArea abv = new JTextArea("");
    private JTextArea acidity = new JTextArea("");
    private JTextArea body = new JTextArea("");
    private JPanel pairings = new JPanel();

    public WineDetail() {
        setPreferredSize(new Dimension(300, 600));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        CustomJPanel namePanel = new CustomJPanel(new JLabel("Name"), name);
        add(namePanel.panel);

        CustomJPanel wineryPanel = new CustomJPanel(new JLabel("Winery"), winery);
        add(wineryPanel.panel);

        CustomJPanel typePanel = new CustomJPanel(new JLabel("Type"), type);
        add(typePanel.panel);

        CustomJPanel countryPanel = new CustomJPanel(new JLabel("Country"), country);
        add(countryPanel.panel);

        CustomJPanel abvPanel = new CustomJPanel(new JLabel("ABV"), abv);
        add(abvPanel.panel);

        CustomJPanel blendPanel = new CustomJPanel(new JLabel("Blend"), blend);
        add(blendPanel.panel);

        CustomJPanel acidityPanel = new CustomJPanel(new JLabel("Acidity"), acidity);
        add(acidityPanel.panel);

        CustomJPanel bodyPanel = new CustomJPanel(new JLabel("Body"), body);
        add(bodyPanel.panel);

        CustomJPanel grapePanel = new CustomJPanel(new JLabel("Grape(s)"), grapes);
        add(grapePanel.panel);

        CustomJPanel pairingPanel = new CustomJPanel(new JLabel("Pairing(s)"), pairings);
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
        grapes.removeAll();
        JPanel grapePanel = new JPanel();
        for(String grape: selectedWine.grapes) {
            JPanel labelContainer = new JPanel();
            JLabel grapeLabel = new JLabel(grape);
            Border blackline = BorderFactory.createLineBorder(new Color(250, 108, 14));
            labelContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
            labelContainer.setBorder(blackline);
            labelContainer.add(grapeLabel);
            grapePanel.add(labelContainer);
        }
        grapes.add(grapePanel);

        pairings.removeAll();
        JPanel pairingPanel = new JPanel();
        for(String pairing: selectedWine.pairings) {
            JPanel labelContainer = new JPanel();
            JLabel pairingLabel = new JLabel(pairing);
            Border blackline = BorderFactory.createLineBorder(new Color(250, 108, 14));
            labelContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
            labelContainer.setBorder(blackline);
            labelContainer.add(pairingLabel);
            pairingPanel.add(labelContainer);
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
