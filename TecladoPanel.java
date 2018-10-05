import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TecladoPanel extends JPanel {
	private static final String[] botoesLinha1 = { 
			"~", "1", "2", 
			"3", "4", "5", 
			"6", "7", "8", 
			"9", "0", "-", "+",
			"Backspace" };
private static final String[] botoesLinha2 = { "Tab", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]",
	"\\" };
private static final String[] botoesLinha3 = { "Caps", "A", "S", "D", "F", "G", "H", "J", "K", "L", ":", "\"",
	"Enter" };
private static final String[] botoesLinha4 = { "Shift", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "?", "^"};
private static final String[] botoesLinha5 = { "      SpaceBar      ", "<", "!", ">"};
public static int[] keyCodesLinha1 = {
		222,49, 50, 
		51, 52, 53, 
		54, 55, 56, 
		57, 48, 45, 61,
	8 };
	public JButton[] botoes1Linha;
	public JButton[] botoes2Linha;
	public JButton[] botoes3Linha;
	public JButton[] botoes4Linha;
	public JButton[] botoes5Linha;
	public JPanel panelLinha1;
	public JPanel panelLinha2;
	public JPanel panelLinha3;
	public JPanel panelLinha4;
	public JPanel panelLinha5;
	private GridBagLayout layout; 
	private GridBagConstraints constraints; 
	
	 private void addComponent( Component component,
		      int row, int column, int width, int height, int linha )
		   {
		      constraints.gridx = column; // set gridx
		      constraints.gridy = row; // set gridy
		      constraints.gridwidth = width; // set gridwidth
		      constraints.gridheight = height; // set gridheight
		      layout.setConstraints( component, constraints ); // set constraints
		      if(linha == 1) {
		    	  panelLinha1.add( component );   
		      }
		      if(linha == 2) {
		    	  panelLinha2.add(component);
		      }
		      if(linha == 3) {
		    	  panelLinha3.add(component);
		      }
		      if(linha == 4) {
		    	  panelLinha4.add(component);
		      }
		      if(linha == 5) {
		    	  panelLinha5.add(component);
		      }
		      // add component
		   } // end method addComponent

	public TecladoPanel() {
		setLayout(new GridLayout(5, 1));
		//Declaração array
		botoes1Linha = new JButton[botoesLinha1.length];
		botoes2Linha = new JButton[botoesLinha2.length];
		botoes3Linha = new JButton[botoesLinha3.length];
		//Instanciando botões
		botoes4Linha = new JButton[botoesLinha4.length];
		botoes5Linha = new JButton[botoesLinha5.length];
		for(int i = 0 ; i < botoes1Linha.length ; i++) {
			botoes1Linha[i] = new JButton(botoesLinha1[i]);
		}
		for(int i = 0 ; i < botoes2Linha.length ; i++) {
			botoes2Linha[i] = new JButton(botoesLinha2[i]);
		}
		for(int i = 0 ; i < botoes3Linha.length ; i++) {
			botoes3Linha[i] = new JButton(botoesLinha3[i]);
		}
		for(int i = 0 ; i < botoes4Linha.length ; i++) {
			botoes4Linha[i] = new JButton(botoesLinha4[i]);
		}
		for(int i = 0 ; i < botoes5Linha.length ; i++) {
			botoes5Linha[i] = new JButton(botoesLinha5[i]);
		}
		panelLinha1 = new JPanel();
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		panelLinha1.setLayout(layout);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0;
		constraints.insets = new Insets(0, 0, 0, 2);
		
		for(int i = 0 ; i < botoes1Linha.length ; i++) {
			if(i == botoes1Linha.length - 1) {
				 
				addComponent(botoes1Linha[i], 1, i, 1, 1, 1);
				
			}
			else {
				
				addComponent(botoes1Linha[i], 1, i, 1, 1, 1);
			
			}
			
			
		}
		add(panelLinha1);
		
		panelLinha2 = new JPanel();
		layout = new GridBagLayout();
		constraints =  new GridBagConstraints();
		panelLinha2.setLayout(layout);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0;
		constraints.insets = new Insets(0, 0, 0, 2);
		
		for(int i = 0 ; i < botoes2Linha.length; i++) {
			addComponent(botoes2Linha[i], 1, i, 1, 1, 2);
		}
		add(panelLinha2);
		
		panelLinha3 = new JPanel();
		layout = new GridBagLayout();
		constraints =  new GridBagConstraints();
		panelLinha3.setLayout(layout);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0;
		constraints.insets = new Insets(0, 0, 0, 2);
		
		for(int i = 0 ; i < botoes3Linha.length; i++) {
			addComponent(botoes3Linha[i], 1, i, 1, 1, 3);
		}
		add(panelLinha3);
		
		panelLinha4 = new JPanel();
		layout = new GridBagLayout();
		constraints =  new GridBagConstraints();
		panelLinha4.setLayout(layout);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0;
		constraints.insets = new Insets(0, 0, 0, 2);
		
		for(int i = 0 ; i < botoes4Linha.length; i++) {
			if(i == botoes4Linha.length-1) {
				constraints.insets = new Insets(0, 10, 0, 0);
			}
		
			addComponent(botoes4Linha[i], 1, i, 1, 1, 4);
		}
		add(panelLinha4);
		
		panelLinha5 = new JPanel();
		layout = new GridBagLayout();
		constraints =  new GridBagConstraints();
		panelLinha5.setLayout(layout);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0;
		constraints.insets = new Insets(0, 0, 0, 2);
		
		
			//addComponent(component, row, column, width, height, linha);
			JButton butInvisi = new JButton("");
			butInvisi.setVisible(false);
			addComponent(butInvisi, 1, 0, 1, 1, 5);
			constraints.insets = new Insets(0, 225, 0, 150);
			addComponent(botoes5Linha[0], 1, 5, 1, 1, 5);
			constraints.insets = new Insets(0, 0, 0, 2);
			addComponent(botoes5Linha[1], 1, 12, 1, 1, 5);
			addComponent(botoes5Linha[2], 1, 13, 1, 1, 5);
			addComponent(botoes5Linha[3], 1, 14, 1, 1, 5);
		add(panelLinha5);
	
	}
	
//	public static void main(String[] args) {
//		TecladoPanel app = new TecladoPanel();
//		app.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//		app.setSize( 750, 175 ); // set frame size
//		app.setVisible( true ); // display frame
//	}
}
