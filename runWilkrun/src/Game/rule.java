package Game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class rule extends Canvas{
	
	private gameGUI gg = null;
	private Image howto = new ImageIcon(this.getClass().getResource("../img/howto.png")).getImage();
	
	rule(gameGUI gg){
		this.gg=gg;
		this.addMouseListener(new MyMouseListener());
		this.addKeyListener(new MyKeyListener());
		this.setFocusable(true);
		this.requestFocus();
		repaint();
	}
	
	public void update(Graphics g) {
		
	}
	public void paint(Graphics g) {
		g.clearRect(0, 0, 800, 700);
		g.drawImage(howto,0,0,null);
		update(g);
	}
	class MyMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			gg.showCnt();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	class MyKeyListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode()==10) {
				gg.showCnt();
			}else if(e.getKeyCode()==13) {
				gg.showCnt();
			}else if(e.getKeyCode()==27) {
				System.exit(0);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
