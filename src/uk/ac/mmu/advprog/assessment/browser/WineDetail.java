package uk.ac.mmu.advprog.assessment.browser;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

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
    private JTextArea website = new JTextArea("");
    private JLabel region = new JLabel("");
    private JLabel pairings = new JLabel("");
    private JPanel ratings = new JPanel();


    public WineDetail() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Title
        name.setFont(new Font("SansSerif", Font.BOLD, 28));
        mainPanel.add(name, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        JPanel wineryWinePanel = new JPanel(new GridLayout(1, 2));
        content.setBorder(new EmptyBorder(15, 15, 15, 15));
        content.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JPanel wineryInfoPanel = new JPanel(new GridLayout(4, 2));
        wineryInfoPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel wineryPanel = new JPanel();
        wineryPanel.add(winery);
        wineryInfoPanel.add(wineryPanel);

        JPanel regionPanel = new JPanel();
        regionPanel.add(region);
        wineryInfoPanel.add(regionPanel);

        JPanel countryPanel = new JPanel();
        countryPanel.add(country);
        wineryInfoPanel.add(countryPanel);

        JPanel websitePanel = new JPanel();
        website.setEditable(false);
        website.setOpaque(false);
        website.setLineWrap(true);
        website.setPreferredSize(new Dimension(200, 50));
        website.setMinimumSize(new Dimension(200, 50));
        websitePanel.add(website);
        wineryInfoPanel.add(websitePanel);

        JPanel wineInfoPanel = new JPanel(new GridLayout(4, 2));
        wineInfoPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel typePanel = new JPanel();
        typePanel.add(type);
        wineInfoPanel.add(typePanel);

        JPanel abvPanel = new JPanel();
        abvPanel.add(abv);
        wineInfoPanel.add(abvPanel);

        JPanel Acidity = new JPanel();
        Acidity.add(acidity);
        wineInfoPanel.add(Acidity);

        JPanel bodyPanel = new JPanel();
        bodyPanel.add(body);
        wineInfoPanel.add(bodyPanel);

        wineryWinePanel.add(wineryInfoPanel);
        wineryWinePanel.add(wineInfoPanel);

        content.add(wineryWinePanel);

        JPanel grapesPanel = new JPanel(new GridLayout(2, 1));
        grapesPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel grapesLabel = new JLabel("Grapes");
        grapesLabel.setFont(new Font("SansSerif", Font.BOLD, 28));

        grapesPanel.add(grapesLabel);

        grapesPanel.add(grapes);

        content.add(grapesPanel);

        JPanel pairingPanel = new JPanel(new GridLayout(2, 1));
        pairingPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel pairingLabel = new JLabel("Pairings");
        pairingLabel.setFont(new Font("SansSerif", Font.BOLD, 28));

        pairingPanel.add(pairingLabel);
        pairingPanel.add(pairings);

        content.add(pairingPanel);

        mainPanel.add(content, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(ratings);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollPane.setPreferredSize(new Dimension(200, 400));

        mainPanel.add(scrollPane, BorderLayout.EAST);

        add(mainPanel);

    }

    /**
     *
     * @param year
     * @param stars
     * @param ratings
     * @return
     */
    private static JPanel createRatingRow(int year, float stars, int ratings) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        String vintageYear = year != -1 ? String.valueOf(year) : "N/V";

        JLabel vintage = new JLabel(vintageYear + "  ");
        vintage.setHorizontalTextPosition(SwingConstants.RIGHT);
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

    /**
     *
     * @param selectedWine
     */
    public void setData(SelectedWine selectedWine) {
        name.setText(selectedWine.name);
        winery.setText("Winery: " + selectedWine.winery);
        type.setText("Type: "+ selectedWine.type);
        country.setText("Country: " + selectedWine.country);
        abv.setText("ABV: " + selectedWine.abv + "%");
        blend.setText("Blend: " + selectedWine.blend);
        acidity.setText("Acidity: " + selectedWine.acidity);
        body.setText("Body: " + selectedWine.body);
        website.setText(selectedWine.website);
        region.setText("Region: " + selectedWine.region);

        StringBuilder grapeString = new StringBuilder();
        for(String grape: selectedWine.grapes) {
            grapeString.append(grape).append(", ");
        }
        grapes.setText(grapeString.toString());

        StringBuilder pairingString = new StringBuilder();

        for(String pairing: selectedWine.pairings) {
           pairingString.append(pairing).append(", ");
        }
        pairings.setText(pairingString.toString());

        ratings.removeAll();
        JPanel ratingsPanel = new JPanel();
        ratingsPanel.setLayout(new BoxLayout(ratingsPanel, BoxLayout.Y_AXIS));
        ratingsPanel.add(new JLabel("Vintage Ratings"));

        ArrayList<Rating> uniqueRatings = new ArrayList<>();

        for(int vintage: selectedWine.vintages) {

            Rating vintageRating = new Rating(vintage, 0, 0);

            uniqueRatings.add(vintageRating);

        }

        for(Rating uniqueRating:  uniqueRatings) {
            for(Rating rating: selectedWine.ratings) {
                if(uniqueRating.getVintage() == rating.getVintage()) {
                    uniqueRating.setRating(rating.getRating());
                    uniqueRating.setRatingCount(rating.getRatingCount());
                }
            }

            ratingsPanel.add(createRatingRow(uniqueRating.getVintage(), uniqueRating.getRating(), uniqueRating.getRatingCount()));

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
        website.setText("");
        region.setText("");
        grapes.setText("");
        pairings.setText("");
        ratings.removeAll();
    }

}
