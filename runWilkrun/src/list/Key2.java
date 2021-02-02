package list;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Game.gameGUI;
import Game.printOver;

public class Key2 extends Thread implements KeyListener{
	
	private Ranking rk = null;
	private printOver po = null;
	private gameGUI gg = null;
	
	public Key2(Ranking rk,printOver po,gameGUI gg) {
		this.rk=rk;
		this.po=po;
		this.gg=gg;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==13) {
			gg.enterSound();
			gg.showRank(po, rk);
		}else if(e.getKeyCode()==10) {
			gg.enterSound();
			gg.showRank(po, rk);
		}else if(e.getKeyCode()==27) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
