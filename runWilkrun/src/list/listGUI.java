package list;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/* 랭킹화면 gui */
public class listGUI extends JFrame implements KeyListener{
	
	//	리스트
	private timeRank tr = null;

	public listGUI(){
		tr = new timeRank();
		setFrame();
		setTimeRank();
	}

	private void setFrame() {
		this.addKeyListener(this);	// 키 쓰려면 
		this.setBounds(720,130,350,550);
		this.setUndecorated(true);
		this.getContentPane().setLayout(new java.awt.BorderLayout(0,0));
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}
	
	private void setTimeRank(){
		this.add(tr,"Center");
		this.setVisible(true);
	}
	
	private void setP1List(){
		this.add(tr,"Center");
		this.setVisible(true);
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
//	 	ESC 누를 시 종료
		if(e.getKeyCode()==27) {
	    	  System.exit(0);
	      }
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
