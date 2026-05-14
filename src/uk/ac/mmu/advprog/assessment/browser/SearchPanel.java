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

    private final EmptyBorder border = new EmptyBorder(5, 5, 5, 5);

    private JTextField name = new JTextField(12);

    private JTextField winery = new JTextField(12);

    private ArrayList<String> availableTypes = queries.getTypes();
    private String[] types = availableTypes.toArray(new String[availableTypes.size()]);
    private JComboBox<String> type = new JComboBox<>(types);

    private ArrayList<String> availableCountries = queries.getCountries();
    private String[] countries = availableCountries.toArray(new String[availableCountries.size()]);
    private JComboBox<String> country = new JComboBox<>(countries);

    private ArrayList<String> availableBlends = queries.getBlends();
    private String[] blends = availableBlends.toArray(new String[availableBlends.size()]);
    private JComboBox<String> blend = new JComboBox<>(blends);

    private JTextField grape = new JTextField(12);

    private JSlider abv = new JSlider(0, 50);

    private JSlider minRating = new JSlider(0, 5);

    private JFormattedTextField minNumberRatings = new JFormattedTextField(java.text.NumberFormat.getIntegerInstance());
    String[] acidities = {"Any",
            "High",
            "Medium",
            "Low"
    };
    private JComboBox<String> acidity = new JComboBox<>(acidities);

    private ArrayList<String> availableBodys = queries.getBodys();
    private String[] bodys = availableBodys.toArray(new String[availableBodys.size()]);
    private JComboBox<String> body = new JComboBox<>(bodys);


    public SearchPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(43, 43, 43));
        setPreferredSize(new Dimension(300, 600));
        setMaximumSize(new Dimension(300, 600));

        add(getWineNamePanel());
        add(getWineryPanel());
        add(getTypePanel());
        add(getCountryPanel());
        add(getBlendPanel());
        add(getGrapePanel());
        add(getAbvPanel());
        add(getAcidityPanel());
        add(getBodyPanel());
        add(getMinRatingPanel());
        add(getMinRatingsPanel());

        add(getClearButton());
    }

    private JPanel getClearButton() {
        JPanel buttonContainer = new JPanel(new FlowLayout());
        JButton clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(250, 108, 14));
        clearButton.setForeground(Color.WHITE);
        clearButton.setBorderPainted(false);
        clearButton.setFocusPainted(false);
        clearButton.setOpaque(true);

        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetSearch();
            }
        });

        buttonContainer.setOpaque(false);
        buttonContainer.add(clearButton);

        return buttonContainer;
    }

    private JPanel getPanel(String labelValue) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(labelValue);
        label.setForeground(ColorUIResource.WHITE);
        panel.setLayout(new GridLayout(2, 1));
        panel.setOpaque(false);
        panel.setBorder(border);
        panel.add(label);

        return panel;
    }

    private JPanel getWineNamePanel() {
        JPanel panel = getPanel("Name ");
        name.setBackground(new Color(70, 72, 74));
        name.setForeground(ColorUIResource.WHITE);
        panel.add(name);
        return panel;
    }

    private JPanel getWineryPanel() {
        JPanel panel = getPanel("Winery Name");
        winery.setBackground(new Color(70, 72, 74));
        winery.setForeground(ColorUIResource.WHITE);
        panel.add(winery);
        return panel;
    }

    private JPanel getTypePanel() {
        JPanel panel = getPanel("Type");
        type.setOpaque(false);
        panel.add(type);

        return panel;
    }

    private JPanel getCountryPanel() {
        JPanel panel = getPanel("Country of Origin");
        panel.add(country);

        return panel;
    }

    private JPanel getBlendPanel() {
        JPanel panel = getPanel("Blend");
        panel.add(blend);

        return panel;
    }

    private JPanel getGrapePanel() {
        JPanel panel = getPanel("Grape");
        grape.setBackground(new Color(70, 72, 74));
        grape.setForeground(Color.WHITE);
        panel.add(grape);

        return panel;
    }

    private JPanel getAbvPanel() {
        JPanel panel = getPanel("Min ABV");

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
        JPanel panel = getPanel("Acidity");
        panel.add(acidity);

        return panel;
    }

    private JPanel getBodyPanel() {
        JPanel panel = getPanel("Body");

        panel.add(body);

        return panel;
    }

    private JPanel getMinRatingPanel() {
        JPanel panel = getPanel("Min average Rating");

        minRating.setValue(0);
        minRating.setForeground(Color.WHITE);
        minRating.setMajorTickSpacing(1);
        minRating.setPaintTicks(true);
        minRating.setPaintLabels(true);
        panel.add(minRating);

        return panel;
    }

    private JPanel getMinRatingsPanel() {
        JPanel panel = getPanel("Min number of ratings ");
        minNumberRatings.setBackground(new Color(70, 72, 74));
        minNumberRatings.setForeground(ColorUIResource.WHITE);
        panel.add(minNumberRatings);
        
        return panel;
    }

    private void resetSearch() {
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
        queryBuilder.setMinRatings(minNumberRatings.getText());

        return queryBuilder;
    }

    public boolean validateSearch() {
        if (name.getText().isEmpty() && winery.getText().isEmpty() && grape.getText().isEmpty() && type.getSelectedItem().toString().equals("Any") && country.getSelectedItem().toString().equals("Any") && blend.getSelectedItem().toString().equals("Any") && acidity.getSelectedItem().toString().equals("Any") && body.getSelectedItem().toString().equals("Any")) {
            return false;
        }

        return true;
    }

}