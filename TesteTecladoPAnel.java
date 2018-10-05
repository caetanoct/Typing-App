import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class TesteTecladoPAnel extends JFrame {
	public TecladoPanel TecladoPanel = new TecladoPanel();
	public Color buttonOriginal = TecladoPanel.botoes1Linha[0].getBackground();
	public TesteTecladoPAnel() {
		setLayout(new GridLayout(2, 1));
		JTextArea textArea = new JTextArea();
		textArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				int linha = findLinha(e.getKeyCode());
				int posOfKeyCode = findPosOfKeyCodeInLinha(e.getKeyCode(), linha);
				UnpaintButtoninLinhaandPos(linha,posOfKeyCode);		
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int linha = findLinha(e.getKeyCode());
				int posOfKeyCode = findPosOfKeyCodeInLinha(e.getKeyCode(), linha);
				paintButtoninLinhaandPos(linha, posOfKeyCode);
				
			}
		});
		add(textArea);
		add(TecladoPanel);
	}

	public static void main(String[] args) {
		TesteTecladoPAnel a = new TesteTecladoPAnel();
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.setSize(750, 175); // set frame size
		a.setVisible(true); // display frame
	}

	private int findLinha(int KeyCode) {
		for (int i = 0; i < TecladoPanel.keyCodesLinha1.length; i++) {
			if (TecladoPanel.keyCodesLinha1[i] == KeyCode) {
				return 1;
			}
		}
		return 0;
	}

	private int findPosOfKeyCodeInLinha(int KeyCode, int linha) {
		if (linha == 1) {
			for (int i = 0; i < TecladoPanel.keyCodesLinha1.length; i++) {
				if (TecladoPanel.keyCodesLinha1[i] == KeyCode) {
					return i;
				}
			}
		}
		return -1;
	}

	private void paintButtoninLinhaandPos(int linha, int pos) {
		if (linha == 1) {
			TecladoPanel.botoes1Linha[pos].setBackground(Color.GREEN);
		}
	}
	private void UnpaintButtoninLinhaandPos(int linha, int pos) {
		if (linha == 1) {
			TecladoPanel.botoes1Linha[pos].setBackground(buttonOriginal);
		}
	}

	
}
