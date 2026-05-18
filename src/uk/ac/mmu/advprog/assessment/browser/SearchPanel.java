package uk.ac.mmu.advprog.assessment.browser;

import uk.ac.mmu.advprog.assessment.shared.Queries;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchPanel extends JPanel {
    Queries queries = new Queries("jdbc:sqlite:data/winetime.db", false);

    private final EmptyBorder border = new EmptyBorder(5, 10, 5, 10);

    private final JTextField name = new JTextField(12);

    private final JTextField winery = new JTextField(12);

    private final ArrayList<String> availableTypes = queries.getTypes();
    private final String[] types = availableTypes.toArray(new String[availableTypes.size()]);
    private final JComboBox<String> type = new JComboBox<>(types);

    private final ArrayList<String> availableCountries = queries.getCountries();
    private final String[] countries = availableCountries.toArray(new String[availableCountries.size()]);
    private final JComboBox<String> country = new JComboBox<>(countries);

    private final ArrayList<String> availableBlends = queries.getBlends();
    private final String[] blends = availableBlends.toArray(new String[availableBlends.size()]);
    private final JComboBox<String> blend = new JComboBox<>(blends);

    private final JTextField grape = new JTextField(12);

    private final JSlider abv = new JSlider(0, 50);

    private final JSlider minRating = new JSlider(0, 5);

    private final JFormattedTextField minNumberRatings = new JFormattedTextField(java.text.NumberFormat.getIntegerInstance());
    String[] acidities = {"Any",
            "High",
            "Medium",
            "Low"
    };
    private final JComboBox<String> acidity = new JComboBox<>(acidities);

    private final ArrayList<String> availableBodys = queries.getBodys();
    private final String[] bodys = availableBodys.toArray(new String[availableBodys.size()]);
    private final JComboBox<String> body = new JComboBox<>(bodys);


    public SearchPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(43, 43, 43));
        setPreferredSize(new Dimension(320, 600));
        setMaximumSize(new Dimension(320, 600));

        JPanel searchPanel = new JPanel();

        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setBackground(new Color(43, 43, 43));

        searchPanel.add(getWineNamePanel());
        searchPanel.add(getWineryPanel());
        searchPanel.add(getTypePanel());
        searchPanel.add(getCountryPanel());
        searchPanel.add(getBlendPanel());
        searchPanel.add(getGrapePanel());
        searchPanel.add(getAbvPanel());
        searchPanel.add(getAcidityPanel());
        searchPanel.add(getBodyPanel());
        searchPanel.add(getMinRatingPanel());
        searchPanel.add(getMinRatingsPanel());

        add(searchPanel, BorderLayout.EAST);

    }

    /**
     *
     * @param labelValue label for search field
     * @param scroller allow for more height to fit JSlider
     * @return
     */
    private JPanel getPanel(String labelValue, boolean scroller) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(290, scroller ? 100 : 70));
        JLabel label = new JLabel(labelValue);
        label.setForeground(ColorUIResource.WHITE);
        panel.setLayout(new GridLayout(2, 1));
        panel.setOpaque(false);
        panel.setBorder(border);
        panel.add(label);

        return panel;
    }

    private JPanel getWineNamePanel() {
        JPanel panel = getPanel("Name ", false);
        name.setBackground(new Color(70, 72, 74));
        name.setForeground(ColorUIResource.WHITE);
        panel.add(name);
        return panel;
    }

    private JPanel getWineryPanel() {
        JPanel panel = getPanel("Winery Name", false);
        winery.setBackground(new Color(70, 72, 74));
        winery.setForeground(ColorUIResource.WHITE);
        panel.add(winery);
        return panel;
    }

    private JPanel getTypePanel() {
        JPanel panel = getPanel("Type", false);
        type.setOpaque(false);
        panel.add(type);

        return panel;
    }

    private JPanel getCountryPanel() {
        JPanel panel = getPanel("Country of Origin", false);
        panel.add(country);

        return panel;
    }

    private JPanel getBlendPanel() {
        JPanel panel = getPanel("Blend", false);
        panel.add(blend);

        return panel;
    }

    private JPanel getGrapePanel() {
        JPanel panel = getPanel("Grape", false);
        grape.setBackground(new Color(70, 72, 74));
        grape.setForeground(Color.WHITE);
        panel.add(grape);

        return panel;
    }

    private JPanel getAbvPanel() {
        JPanel panel = getPanel("Min ABV", true);

        abv.setValue(0);
        abv.setForeground(Color.WHITE);
        abv.setMajorTickSpacing(10);
        abv.setMinorTickSpacing(1);
        abv.setPaintTicks(true);
        abv.setPaintLabels(true);
        panel.add(abv);

        return panel;
    }

    private JPanel getAcidityPanel() {
        JPanel panel = getPanel("Acidity", false);
        panel.add(acidity);

        return panel;
    }

    private JPanel getBodyPanel() {
        JPanel panel = getPanel("Body", false);

        panel.add(body);

        return panel;
    }

    private JPanel getMinRatingPanel() {
        JPanel panel = getPanel("Min average Rating", true);

        minRating.setValue(0);
        minRating.setForeground(Color.WHITE);
        minRating.setMajorTickSpacing(1);
        minRating.setPaintTicks(true);
        minRating.setPaintLabels(true);
        panel.add(minRating);

        return panel;
    }

    private JPanel getMinRatingsPanel() {
        JPanel panel = getPanel("Min number of ratings ", false);
        minNumberRatings.setBackground(new Color(70, 72, 74));
        minNumberRatings.setForeground(ColorUIResource.WHITE);
        panel.add(minNumberRatings);
        
        return panel;
    }

    public void resetSearch() {
        name.setText("");
        winery.setText("");
        type.setSelectedIndex(0);
        country.setSelectedIndex(0);
        blend.setSelectedIndex(0);
        grape.setText("");
        abv.setValue(0);
        acidity.setSelectedIndex(0);
        body.setSelectedIndex(0);
    }

    /**
     *
     * @return QueryBuilder - contains all search criteria
     */
    public QueryBuilder getQuery() {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setName(name.getText());
        queryBuilder.setWineryName(winery.getText());
        queryBuilder.setGrape(grape.getText());
        queryBuilder.setAbv(abv.getValue());
        queryBuilder.setType(type.getSelectedItem().toString());
        queryBuilder.setCountry(country.getSelectedItem().toString());
        queryBuilder.setBlend(blend.getSelectedItem().toString());
        queryBuilder.setAcidity(acidity.getSelectedItem().toString());
        queryBuilder.setBody(body.getSelectedItem().toString());
        queryBuilder.setMinAverageRating(minRating.getValue());
        queryBuilder.setMinRatings(Integer.parseInt(minNumberRatings.getText().replace(",", "")));
        return queryBuilder;
    }

    /**
     * checks is minimum search criteria is met
     * @return boolean
     */
    public boolean validateSearch() {
        if (name.getText().isEmpty() && winery.getText().isEmpty() && grape.getText().isEmpty() && type.getSelectedItem().toString().equals("Any") && country.getSelectedItem().toString().equals("Any") && blend.getSelectedItem().toString().equals("Any") && acidity.getSelectedItem().toString().equals("Any") && body.getSelectedItem().toString().equals("Any")) {
            return false;
        }

        return true;
    }

}