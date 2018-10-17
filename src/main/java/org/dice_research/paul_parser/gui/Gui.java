package org.dice_research.paul_parser.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.dice_research.paul_parser.CommandLineInterface;
import org.dice_research.paul_parser.CsvPrinter;
import org.dice_research.paul_parser.PaulParser;
import org.dice_research.paul_parser.StudentContainer;

public class Gui {

	private JFrame frame;
	private JTextField textTypes;
	private JTextField textDemiliter;

	private JButton btnGo;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	public void update() {
		SwingUtilities.updateComponentTreeUI(frame);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Types");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNewLabel.setBounds(12, 12, 66, 15);
		frame.getContentPane().add(lblNewLabel);

		textTypes = new JTextField();
		textTypes.setForeground(Color.WHITE);
		textTypes.setBackground(new Color(112, 128, 144));
		textTypes.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textTypes.setBounds(126, 10, 460, 19);
		textTypes.setColumns(10);
		textTypes.setBorder(null);
		frame.getContentPane().add(textTypes);

		JLabel lblSeparator = new JLabel("Demiliter");
		lblSeparator.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblSeparator.setBounds(12, 39, 66, 15);
		frame.getContentPane().add(lblSeparator);

		textDemiliter = new JTextField();
		textDemiliter.setForeground(Color.WHITE);
		textDemiliter.setBackground(new Color(112, 128, 144));
		textDemiliter.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textDemiliter.setBounds(126, 41, 460, 19);
		textDemiliter.setColumns(10);
		textDemiliter.setBorder(null);
		frame.getContentPane().add(textDemiliter);

		textArea = new JTextArea();
		textArea.setForeground(new Color(255, 255, 255));
		textArea.setBackground(new Color(112, 128, 144));
		textArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(12, 73, 574, 249);
		frame.getContentPane().add(scrollPane);

		btnGo = new JButton("Go");
		btnGo.setForeground(Color.WHITE);
		btnGo.setBackground(new Color(105, 105, 105));
		btnGo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnGo.setBounds(12, 334, 574, 25);
		frame.getContentPane().add(btnGo);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// All types
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : CommandLineInterface.TYPES.keySet()) {
			stringBuilder.append(key);
		}
		textTypes.setText(stringBuilder.toString());

		// Demiliter
		textDemiliter.setText("\\t");

		// Text
		stringBuilder = new StringBuilder();
		stringBuilder.append("Types:");
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append(CommandLineInterface.getTypesInfo());
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("Example:");
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("No.	Matric. no.	Name	First name	Adviser	Class	Module reference"
				+ "	Obligation level	Uni-mail	Subjects	Aimed degree	Subject semesters");
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("Accepted");
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("1	7654321	Smith	Adam			M.007.4211 Project Group	Voluntary"
				+ "	adamsmith@mail.uni-paderborn.de		Master of Science	1");
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("2	6754321	Doe	John			M.007.4211 Project Group	Voluntary"
				+ "	johndoe@mail.uni-paderborn.de		Master of Science	42");
		textArea.setText(stringBuilder.toString());
		textArea.setCaretPosition(0);

		// Button
		btnGo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<StudentContainer> students = new LinkedList<StudentContainer>();
				try {
					students = new PaulParser().parse(textArea.getText()).getStudents();
				} catch (IOException ioe) {
					System.err.println("Error: " + ioe.getMessage());
					textArea.append(System.lineSeparator() + "Error: " + ioe.getMessage());
					return;
				}

				StringBuilder stringBuilder = new StringBuilder();
				CsvPrinter csvPrinter = new CsvPrinter(students, textTypes.getText());
				String demiliter = textDemiliter.getText();
				if (demiliter.isEmpty()) {
					csvPrinter.setDemiliter('\0');
				} else if (demiliter.startsWith("\\t")) {
					csvPrinter.setDemiliter('\t');
				} else {
					csvPrinter.setDemiliter(demiliter.charAt(0));
				}

				try {
					csvPrinter.print(stringBuilder);
				} catch (IOException ioe) {
					System.err.println("Error: " + ioe.getMessage());
					textArea.append(System.lineSeparator() + "Error: " + ioe.getMessage());
					return;
				}

				textArea.setText(stringBuilder.toString());
			}
		});

	}
}
