package org.dice_research.paul_parser.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class WindowBuilderEditor {

	private JFrame frame;
	private JTextField textTypes;
	private JTextField textDemiliter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowBuilderEditor window = new WindowBuilderEditor();
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
	public WindowBuilderEditor() {
		initialize();
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
		textTypes.setText("xxx");
		textTypes.setBounds(126, 10, 460, 19);
		frame.getContentPane().add(textTypes);
		textTypes.setColumns(10);
		
		JLabel lblSeparator = new JLabel("Demiliter");
		lblSeparator.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblSeparator.setBounds(12, 39, 66, 15);
		frame.getContentPane().add(lblSeparator);
		
		textDemiliter = new JTextField();
		textDemiliter.setForeground(Color.WHITE);
		textDemiliter.setBackground(new Color(112, 128, 144));
		textDemiliter.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textDemiliter.setText(",");
		textDemiliter.setBounds(126, 41, 460, 19);
		frame.getContentPane().add(textDemiliter);
		textDemiliter.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setForeground(new Color(255, 255, 255));
		textArea.setBackground(new Color(112, 128, 144));
		textArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textArea.setBounds(12, 73, 574, 249);
		frame.getContentPane().add(textArea);
		
		JButton btnGo = new JButton("Go");
		btnGo.setForeground(Color.WHITE);
		btnGo.setBackground(new Color(105, 105, 105));
		btnGo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnGo.setBounds(12, 334, 574, 25);
		frame.getContentPane().add(btnGo);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
