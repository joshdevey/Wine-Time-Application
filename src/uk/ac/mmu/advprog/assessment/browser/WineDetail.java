package uk.ac.mmu.advprog.assessment.browser;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WineDetail extends JPanel {

    private JLabel name = new JLabel("");
    private JLabel winery = new JLabel("");
    private JLabel type = new JLabel("");
    private JLabel country = new JLabel("");
    private JLabel blend = new JLabel("");
    private JLabel grapes = new JLabel("");
    private JLabel abv = new JLabel("");
    private JLabel acidity = new JLabel("");
    private JLabel body = new JLabel("");
    private JLabel pairings = new JLabel("");
    private JPanel ratings = new JPanel();


    public WineDetail() {
        setPreferredSize(new Dimension(400, 600));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Title
        name.setFont(new Font("SansSerif", Font.BOLD, 28));
        mainPanel.add(name, BorderLayout.NORTH);

        // Content panel
        JPanel content = new JPanel(new GridLayout(5, 2));
        content.setBorder(new EmptyBorder(15, 15, 15, 15));
        content.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JPanel typePanel = new JPanel();
        typePanel.setLayout(new GridLayout(1, 2));
        typePanel.add(new JLabel("Type"));
        typePanel.add(type);
        content.add(typePanel);

        JPanel wineryPanel = new JPanel();
        wineryPanel.setLayout(new GridLayout(1, 2));
        wineryPanel.add(new JLabel("Winery"));
        wineryPanel.add(winery);
        content.add(wineryPanel);

        JPanel CountryPanel = new JPanel();
        CountryPanel.setLayout(new GridLayout(1, 2));
        CountryPanel.add(new JLabel("Country"));
        CountryPanel.add(country);
        content.add(CountryPanel);

        JPanel abvPanel = new JPanel();
        abvPanel.setLayout(new GridLayout(1, 2));
        abvPanel.add(new JLabel("Abv"));
        abvPanel.add(abv);
        content.add(abvPanel);

        JPanel Acidity = new JPanel();
        Acidity.setLayout(new GridLayout(1, 2));
        Acidity.add(new JLabel("Acidity"));
        Acidity.add(acidity);
        content.add(Acidity);

        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(1, 2));
        bodyPanel.add(new JLabel("Body"));
        bodyPanel.add(body);
        content.add(bodyPanel);

        content.add(new JLabel("Grapes"));
        content.add(grapes);

        content.add(new JLabel("Pairings"));
        content.add(pairings);

        mainPanel.add(content, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(ratings);
        scrollPane.setPreferredSize(new Dimension(200, 300));

        mainPanel.add(scrollPane, BorderLayout.EAST);

        add(mainPanel);

    }

    private static JPanel createRatingRow(int year, float stars, int ratings) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel vintage = new JLabel(year + "  ");
        vintage.setFont(new Font("SansSerif", Font.BOLD, 10));
        panel.add(vintage);

        for (int i = 0; i < 5; i++) {
            JLabel star = new JLabel("*");
            if(i < stars) {
                star.setForeground(new Color(250, 108, 14));
                star.setFont(new Font("SansSerif", Font.BOLD, 10));
            }
            panel.add(star);
        }

        JLabel numberOfRatings = new JLabel("(" + ratings + ")");
        numberOfRatings.setFont(new Font("SansSerif", Font.BOLD, 10));

        panel.add(numberOfRatings);

        return panel;
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

        StringBuilder grapeString = new StringBuilder("|");
        for(String grape: selectedWine.grapes) {
            grapeString.append(grape).append(" | ");
        }
        grapes.setText(grapeString.toString());

        StringBuilder pairingString = new StringBuilder("|");

        for(String pairing: selectedWine.pairings) {
           pairingString.append(pairing).append(" | ");
        }
        pairings.setText(pairingString.toString());

        ratings.removeAll();
        JPanel ratingsPanel = new JPanel();
        ratingsPanel.setLayout(new BoxLayout(ratingsPanel, BoxLayout.Y_AXIS));
        ratingsPanel.add(new JLabel("Ratings"));
        for(Rating rating: selectedWine.ratings) {
            ratingsPanel.add(createRatingRow(rating.getVintage(), rating.getRating(), rating.getRatingCount()));
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
        grapes.setText("");
        pairings.setText("");
        ratings.removeAll();
    }

}
