
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
	public JTextArea text;
	// private enterListener keyListener;
	private ObjectOutputStream output;
	private Test teste;
	// Gui do teclado e cor original do botao
	public TecladoPanel TecladoPanel = new TecladoPanel();
	public Color buttonOriginal = TecladoPanel.botoes1Linha[0].getBackground();
	Scanner input = new Scanner(System.in);

	public keyBoard() {
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
		JTextArea textArea = new JTextArea(10, 10);
		textArea.setLineWrap(true);
		constraints.fill = GridBagConstraints.BOTH;
		textArea.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// Colorir Botoes
				try {
					keyBoard.isKeyCodeInVirtualKeyboard(e.getKeyCode(), TecladoPanel.KeyCodesDoTecladoVirtual);
					int linha = findLinha(e.getKeyCode());
					int posOfKeyCode = findPosOfKeyCodeInLinha(e.getKeyCode(), linha);
					paintButtoninLinhaandPos(linha, posOfKeyCode);
				}
				catch(notInVirtualKeyboard ex) {
					System.err.println(ex.getMessage() + "e o metodo paint nao foi executado");
				}
			
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
				try {
					keyBoard.isKeyCodeInVirtualKeyboard(arg0.getKeyCode(), TecladoPanel.KeyCodesDoTecladoVirtual);
					int linha = findLinha(arg0.getKeyCode());
					int posOfKeyCode = findPosOfKeyCodeInLinha(arg0.getKeyCode(), linha);
					UnpaintButtoninLinhaandPos(linha, posOfKeyCode);	
				}
				catch(notInVirtualKeyboard ex) {
					System.err.println(ex.getMessage() + "e o metodo unpaint nao foi executado");
				}
				
			}

			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
		addComponent(textArea, 0, 3, 0, 10);
		constraints.insets = new Insets(10, 0, 0, 0);
		addComponent(TecladoPanel, 0, 4, 0, 0);
	}
	private static boolean isKeyCodeInVirtualKeyboard(int KeyCode, int[] keyCodeArray) throws notInVirtualKeyboard {
		for(int i = 0; i < keyCodeArray.length ; i++) {
			if(KeyCode == keyCodeArray[i]) {
				return true;
			}
		}
		throw new notInVirtualKeyboard("A tecla nao esta no teclado virtaul");
	}
	// Métodos para pintar o botão
	private int findLinha(int KeyCode) {

		for (int i = 0; i < 4; i++) {
			if (TecladoPanel.keyCodesLinha5[i] == KeyCode) {
				return 5;
			} else {
				if (KeyCode == TecladoPanel.keyCodesLinha4[i]) {
					return 4;
				} else {
					if (KeyCode == TecladoPanel.keyCodesLinha3[i]) {
						return 3;
					} else {
						if (KeyCode == TecladoPanel.keyCodesLinha2[i]) {
							return 2;
						} else {
							if (KeyCode == TecladoPanel.keyCodesLinha1[i]) {
								return 1;
							}
						}

					}
				}
			}
		}
		for (int i = 4; i < 12; i++) {

			if (KeyCode == TecladoPanel.keyCodesLinha4[i]) {
				return 4;
			} else {
				if (KeyCode == TecladoPanel.keyCodesLinha3[i]) {
					return 3;
				} else {
					if (KeyCode == TecladoPanel.keyCodesLinha2[i]) {
						return 2;
					} else {
						if (KeyCode == TecladoPanel.keyCodesLinha1[i]) {
							return 1;
						}
					}

				}
			}

		}
		for (int i = 12; i < 13; i++) {
			if (KeyCode == TecladoPanel.keyCodesLinha3[i]) {
				return 3;
			} else {
				if (KeyCode == TecladoPanel.keyCodesLinha2[i]) {
					return 2;
				} else {
					if (KeyCode == TecladoPanel.keyCodesLinha1[i]) {
						return 1;
					}
				}

			}

		}
		for (int i = 13; i < 14; i++) {

			if (KeyCode == TecladoPanel.keyCodesLinha2[i]) {
				return 2;
			} else {
				if (KeyCode == TecladoPanel.keyCodesLinha1[i]) {
					return 1;
				}
			}

		}
		return 0;
	}

	private int findPosOfKeyCodeInLinha(int KeyCode, int linha) {
		if (linha == 0) {
			System.out.println("ops");
		} else {
			if (linha == 1) {
				for (int i = 0; i < TecladoPanel.keyCodesLinha1.length; i++) {
					if (TecladoPanel.keyCodesLinha1[i] == KeyCode) {
						return i;
					}
				}
			}
			if (linha == 2) {
				for (int i = 0; i < TecladoPanel.keyCodesLinha2.length; i++) {
					if (TecladoPanel.keyCodesLinha2[i] == KeyCode) {
						return i;
					}
				}
			}
			if (linha == 3) {
				for (int i = 0; i < TecladoPanel.keyCodesLinha3.length; i++) {
					if (TecladoPanel.keyCodesLinha3[i] == KeyCode) {
						return i;
					}
				}
			}
			if (linha == 4) {
				for (int i = 0; i < TecladoPanel.keyCodesLinha4.length; i++) {
					if (TecladoPanel.keyCodesLinha4[i] == KeyCode) {
						return i;
					}
				}
			}
			if (linha == 5) {
				for (int i = 0; i < TecladoPanel.keyCodesLinha5.length; i++) {
					if (TecladoPanel.keyCodesLinha5[i] == KeyCode) {
						return i;
					}
				}
			}
		}

		return -1;
	}

	private void paintButtoninLinhaandPos(int linha, int pos) {
		if (pos == -1) {
			
		} else {
			if (linha == 1) {
				TecladoPanel.botoes1Linha[pos].setBackground(Color.GREEN);
			}
			if (linha == 2) {
				TecladoPanel.botoes2Linha[pos].setBackground(Color.GREEN);
			}
			if (linha == 3) {
				TecladoPanel.botoes3Linha[pos].setBackground(Color.GREEN);
			}
			if (linha == 4) {
				TecladoPanel.botoes4Linha[pos].setBackground(Color.GREEN);
			}
			if (linha == 5) {
				TecladoPanel.botoes5Linha[pos].setBackground(Color.GREEN);
			}
		}

	}

	private void UnpaintButtoninLinhaandPos(int linha, int pos) {
		if (pos == -1) {
		
		} else {
			if (linha == 1) {
				TecladoPanel.botoes1Linha[pos].setBackground(buttonOriginal);
			}
			if (linha == 2) {
				TecladoPanel.botoes2Linha[pos].setBackground(buttonOriginal);
			}
			if (linha == 3) {
				TecladoPanel.botoes3Linha[pos].setBackground(buttonOriginal);
			}
			if (linha == 4) {
				TecladoPanel.botoes4Linha[pos].setBackground(buttonOriginal);
			}
			if (linha == 5) {
				TecladoPanel.botoes5Linha[pos].setBackground(buttonOriginal);
			}

		}
	}
	// Fim métodos para pintar botoes

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
