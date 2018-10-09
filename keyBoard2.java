
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class keyBoard2 extends JFrame {
	private static final String[] frasesDefinidas = { "frase de teste 1", // Frase 0
			"frase de teste 2", // Frase 1
			"frase de TESTE 3", // Frase 2
			"FraSe dE teste4" }; // Frase 3
	private JTextField textField;
	// setas, espaço
	private GridBagConstraints constraints;
	private JLabel topLabel;
	private JLabel topLabel2;
	// private enterListener keyListener;
	private ObjectOutputStream output;
	// Gui do teclado e cor original do botao
	public TecladoPanel TecladoPanel = new TecladoPanel();
	public Color buttonOriginal = TecladoPanel.botoes1Linha[0].getBackground();
	private ObjectInputStream input;
	private JMenuBar menuBar;
	private JMenu historico;
	private JMenuItem verHistorico;
	private JMenuItem limparHistorico;
	private String historicoLido;
	private JTextArea textArea;
	private int charCount; 
	private int erros;
	private int acertos;

	public keyBoard2() {
		setLayout(new GridBagLayout());
		textField = new JTextField(returnRandomFrase());
		constraints = new GridBagConstraints();
		
		File file = new File("historico.ser");
		if (!file.exists()) {
			openOutputFile();
			closeOutputFile();
		}
		openInputFile();
		readInputFile();
		closeInputFile();
		if (historicoLido == null) {
			historicoLido = "";
		}
		
		// INSTRUÇOES DE CIMA
		constraints.fill = GridBagConstraints.HORIZONTAL;
		topLabel = new JLabel(
				"Type some text using your keyboard. The keys you press will be highlighted and the text will be displayed.");
		addComponent(topLabel, 0, 0, 0, 0);
		topLabel2 = new JLabel("Note: Clicking the buttons with your mouse will not perform any action");
		addComponent(topLabel2, 0, 1, 0, 0);

		// MENU HISTORICO
		menuBar = new JMenuBar();
		historico = new JMenu("Histórico");
		verHistorico = new JMenuItem("Ver histórico");
		verHistorico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openInputFile();
				readInputFile();
				closeInputFile();
				JOptionPane.showMessageDialog(null, "Histórico: \n" + historicoLido, "Histórico",
						JOptionPane.PLAIN_MESSAGE);
			}

		});
		
		limparHistorico = new JMenuItem("Limpar histórico");
		limparHistorico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				historicoLido = "";
				openOutputFile();
				writeText();
				closeOutputFile();
			}
		});

		setJMenuBar(menuBar);
		menuBar.add(historico);
		historico.add(verHistorico);
		historico.add(limparHistorico);

		// TEXT AREA
		textArea = new JTextArea("", 10, 10);
		textArea.setLineWrap(true);
		constraints.fill = GridBagConstraints.BOTH;
		textArea.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// Colorir Botoes
			
				try {
					keyBoard2.isKeyCodeInVirtualKeyboard(e.getKeyCode(), TecladoPanel.KeyCodesDoTecladoVirtual);
					int linha = findLinha(e.getKeyCode());
					int posOfKeyCode = findPosOfKeyCodeInLinha(e.getKeyCode(), linha);
					paintButtoninLinhaandPos(linha, posOfKeyCode);
				} catch (notInVirtualKeyboard ex) {
					System.err.println(ex.getMessage() + "e o metodo paint nao foi executado");
				}
				
				// Marcar no historico
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					JOptionPane.showMessageDialog(null, "Acertos: " + acertos + "\nErros: " + erros, "Total", JOptionPane.PLAIN_MESSAGE);
					charCount = 0;
					
					
					openOutputFile();
					writeText();
					closeOutputFile();
					textArea.setText(null);
					
				}

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				
				
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					
					charCount = 0;
					erros = 0;
					acertos = 0;
					textField.setText(returnRandomFrase());
					textArea.setText(textArea.getText().replaceAll("\n", ""));
					
				}
				// Descolorir Botoes
				try {
					keyBoard2.isKeyCodeInVirtualKeyboard(arg0.getKeyCode(), TecladoPanel.KeyCodesDoTecladoVirtual);
					int linha = findLinha(arg0.getKeyCode());
					int posOfKeyCode = findPosOfKeyCodeInLinha(arg0.getKeyCode(), linha);
					UnpaintButtoninLinhaandPos(linha, posOfKeyCode);
				} catch (notInVirtualKeyboard ex) {
					System.err.println(ex.getMessage() + "e o metodo unpaint nao foi executado");
				}

			}

			@Override
			public void keyTyped(KeyEvent e) {
				Character charCorrespondente = textField.getText().charAt(charCount);
				if(charCorrespondente == e.getKeyChar()) {
					acertos++;
					charCount++;
				}
				else {
				
						erros++;
					
					
				}
				
			}
		});
		addComponent(textArea, 0, 3, 0, 10);
		constraints.insets = new Insets(10, 0, 0, 0);
		addComponent(textField, 0, 4, 0, 0);
		addComponent(TecladoPanel, 0, 5, 0, 0);
	}

	private static boolean isKeyCodeInVirtualKeyboard(int KeyCode, int[] keyCodeArray) throws notInVirtualKeyboard {
		for (int i = 0; i < keyCodeArray.length; i++) {
			if (KeyCode == keyCodeArray[i]) {
				return true;
			}
		}
		throw new notInVirtualKeyboard("A tecla nao esta no teclado virtaul");
	}
	// Metodos para gerar frases e checa-las
		private String returnRandomFrase() {
			Random gerador = new Random();
			return frasesDefinidas[gerador.nextInt(frasesDefinidas.length)];
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

	// READ SERIALIZABLE
	// Open File
	public void openInputFile() {
		try {
			input = new ObjectInputStream(new FileInputStream("historico.ser"));
		} catch (IOException e) {
			System.err.println("Error opening file");
		}
	}

	// Read File
	public void readInputFile() {
		try {
			historicoLido = input.readObject().toString();
		} catch (EOFException e) {
			return;
		} catch (IOException e) {
			System.err.println("Error reading file");
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFound");
		}
	}

	// Close File
	public void closeInputFile() {
		try {
			if (input != null) {
				input.close();
			}
		} catch (IOException e) {
			System.err.println("Error closing file");
			System.exit(1);
		}
	}

	// WRITE SERIALIZABLE
	// Open file
	public void openOutputFile() {
		try {
			output = new ObjectOutputStream(new FileOutputStream("historico.ser"));
		} catch (IOException ioException) {
			System.err.println("Error opening file.");
		}
	}

	// Write to file
	public void writeText() {
		try {
			historicoLido +=  textArea.getText() + "\n";
			output.writeObject(historicoLido);
		} catch (IOException ioException) {
			System.err.println("Error writing to file.");
			return;
		}
	}

	// Close file
	public void closeOutputFile() {
		try {
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
		keyBoard2 board = new keyBoard2();
		board.setSize(1000, 700);
		board.setVisible(true);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
