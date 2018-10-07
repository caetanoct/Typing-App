import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class getKeyCodes extends JFrame{
	public ArrayList<Integer> keyCodes = new ArrayList<>();
	boolean a = true;
	public getKeyCodes() {
		super("merda");
		JTextArea inputText = new JTextArea();
		inputText.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
				if(e.getKeyChar() == '0') {
					System.out.println(keyCodes.toString());
				}
				
				keyCodes.add(e.getKeyCode());
				
			}
		});
		add(inputText);
	}
	public static void main(String[] args) {
	getKeyCodes app = new getKeyCodes();
	app.setSize(100, 100);
	app.setVisible(true);
	app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		
	}

}
