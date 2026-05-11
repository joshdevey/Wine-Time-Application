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
    private JPanel ratings = new JPanel();


    public WineDetail() {
        setPreferredSize(new Dimension(400, 600));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel nameType = new JPanel();
        nameType.setLayout(new GridLayout(1, 2));
        Border blackline = BorderFactory.createLineBorder(new Color(250, 108, 14));
        nameType.setBorder(blackline);

        CustomJPanel namePanel = new CustomJPanel(new JLabel("Name"), name);
        nameType.add(namePanel.panel);

        CustomJPanel typePanel = new CustomJPanel(new JLabel("Type"), type);
        nameType.add(typePanel.panel);

        add(nameType);

        JPanel wineryCountry = new JPanel();
        wineryCountry.setLayout(new GridLayout(1, 2));

        CustomJPanel wineryPanel = new CustomJPanel(new JLabel("Winery"), winery);
        wineryCountry.add(wineryPanel.panel);

        CustomJPanel countryPanel = new CustomJPanel(new JLabel("Country"), country);
        wineryCountry.add(countryPanel.panel);

        add(wineryCountry);

        JPanel abvBlend = new JPanel();
        abvBlend.setLayout(new GridLayout(1, 2));

        CustomJPanel abvPanel = new CustomJPanel(new JLabel("ABV"), abv);
        abvBlend.add(abvPanel.panel);

        CustomJPanel blendPanel = new CustomJPanel(new JLabel("Blend"), blend);
        abvBlend.add(blendPanel.panel);

        add(abvBlend);

        JPanel acidityBody = new JPanel();
        acidityBody.setLayout(new GridLayout(1, 2));

        CustomJPanel acidityPanel = new CustomJPanel(new JLabel("Acidity"), acidity);
        acidityBody.add(acidityPanel.panel);

        add(acidityBody);

        CustomJPanel bodyPanel = new CustomJPanel(new JLabel("Body"), body);
        acidityBody.add(bodyPanel.panel);

        CustomJPanel grapePanel = new CustomJPanel(new JLabel("Grape(s)"), grapes);
        grapePanel.setPreferredSize(new Dimension(400, 200));
        add(grapePanel.panel);

        CustomJPanel pairingPanel = new CustomJPanel(new JLabel("Pairing(s)"), pairings);
        pairingPanel.setPreferredSize(new Dimension(400, 200));
        add(pairingPanel.panel);

        CustomJPanel ratingsPanel = new CustomJPanel(new JLabel("Ratings"), ratings);
        ratingsPanel.setPreferredSize(new Dimension(400, 200));
        add(ratingsPanel.panel);

    }

    public void setData(SelectedWine selectedWine) {
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

        grapePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        for(String grape: selectedWine.grapes) {
            JPanel labelContainer = new JPanel();
            JLabel grapeLabel = new JLabel(grape);
            labelContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
            labelContainer.add(grapeLabel);
            grapePanel.add(labelContainer);
        }
        grapes.add(grapePanel);

        pairings.removeAll();
        JPanel pairingPanel = new JPanel();

        pairingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        for(String pairing: selectedWine.pairings) {
            JPanel labelContainer = new JPanel();
            JLabel pairingLabel = new JLabel(pairing);
            labelContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
            labelContainer.add(pairingLabel);
            pairingPanel.add(labelContainer);
        }
        pairings.add(pairingPanel);

        ratings.removeAll();
        JPanel ratingsPanel = new JPanel();
        ratingsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ratingsPanel.setPreferredSize(new Dimension(400, 200));
        for(Rating rating: selectedWine.ratings) {
            JPanel labelContainer = new JPanel();
            JLabel ratingLabel = new JLabel(String.valueOf(rating.getVintage()));
            labelContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
            labelContainer.add(ratingLabel);
            ratingsPanel.add(labelContainer);
        }

        ratings.add(ratingsPanel);
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
        ratings.removeAll();
    }

}
