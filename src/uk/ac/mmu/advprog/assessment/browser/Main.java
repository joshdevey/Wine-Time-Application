package uk.ac.mmu.advprog.assessment.browser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Demo program for JFrame");
		JLabel labelQuestion = new JLabel("How much water should I drink?");
		JLabel labelWeight = new JLabel("My weight (kg):");
		JTextField fieldWeight = new JTextField(5);
		JButton buttonTellMe = new JButton("Tell Me");

		f.setLayout(new FlowLayout());

		f.add(labelQuestion);
		f.add(labelWeight);
		f.add(fieldWeight);
		f.add(buttonTellMe);

		f.setSize(240, 150);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public void displayFrame() {
		JFrame f = new JFrame("browser");
		Container container = f.getContentPane();
		container.setLayout(new FlowLayout());

		JTextField name = new JTextField();
		JButton search = new JButton();

		container.add(name);
		container.add(search);

		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(container, "search");
			}
		});

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}

}
