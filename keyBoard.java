package keyBoardGUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.*;

public class keyBoard extends JFrame {
	private static final String[] botoesLinha1 = { "~", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "+",
			"Backspace" };
	private static final String[] botoesLinha2 = { "Tab", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]",
			"\\" };
	private static final String[] botoesLinha3 = { "Caps", "A", "S", "D", "F", "G", "H", "J", "K", "L", ":", "\"",
			"Enter" };
	private static final String[] botoesLinha4 = { "Shift", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "?" };
	// setas, espaço
	private GridBagConstraints constraints;
	private JLabel topLabel;
	private JLabel topLabel2;
	private JTextArea text;
	// private enterListener keyListener;
	private ObjectOutputStream output;
	private Test teste;

	Scanner input = new Scanner(System.in);
	
	public keyBoard() {
		super("Typing Application");
		setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		// keyListener = new enterListener();

		// INSTRUÇOES DE CIMA
		constraints.fill = GridBagConstraints.HORIZONTAL;
		topLabel = new JLabel(
				"Type some text using your keyboard. The keys you press will be highlighted and the text will be displayed.");
		addComponent(topLabel, 0, 0, 0, 0);
		topLabel2 = new JLabel("Note: Clicking the buttons with your mouse will not perform any action");
		addComponent(topLabel2, 0, 1, 0, 0);

		// TEXT AREA
		JTextArea textArea = new JTextArea("adaksjdfajsdnf", 10, 10);
		textArea.setLineWrap(true);
		constraints.fill = GridBagConstraints.BOTH;
		textArea.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// Colorir Botoes
				if (e.getKeyCode() == 10) {
					System.out.println("Enter pressionado");
					openFile();
					writeText();
					closeFile(); // <-- NullPointerException
				}

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// Descolorir Botoes

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// System.out.println(e.getKeyCode());
			}
		});
		addComponent(textArea, 0, 3, 0, 10);

	}

	public void openFile() {
		// Open file
		try {
			// String fileName = JOptionPane.showInputDialog("Digite o nome do arquivo que
			// deseja abrir(com .ser)");
			output = new ObjectOutputStream(new FileOutputStream("teste.ser"));
		} catch (IOException ioException) {
			System.err.println("Error opening file.");
		}
	}

	public void writeText() {
		// Write to file
		while (input.hasNext()) {
			try {
			int num = input.nextInt();
			String s = input.nextLine();
			teste = new Test(num, s);
			output.writeObject(teste); // <-- NullPointerException
		} catch (IOException ioException) {
			System.err.println("Error writing to file.");
			return;
		}
			}
	}

	public void closeFile() {
		// Close file
		try

		{
			if (output != null) {
				output.close();
			}
		} catch (IOException ioException) {
			System.err.println("Error closing file.");
			System.exit(1);
		}
	}

	// ADD COMPONENT
	public void addComponent(JComponent component, int column, int row, int sizeX, int sizeY) {
		constraints.gridy = row;
		constraints.gridx = column;
		constraints.weightx = sizeX;
		constraints.weighty = sizeY;
		add(component, constraints);
	}

	// MAIN
	public static void main(String[] args) {
		keyBoard board = new keyBoard();
		board.setSize(1000, 700);
		board.setVisible(true);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		board.openFile();
//		board.writeText();
//		board.closeFile();

	}

	private class Test {
		private int numero;
		private String texto;

		public Test(int numero, String text) {
			this.numero = numero;
			texto = text;
		}
		public void setNumero(int num) {
			this.numero = num;
		}
		public void setTexto(String t) {
			this.texto = t;
		}
	}
}
