package Game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class count extends Canvas{

	private boolean go = true;
	private gameGUI gg = null;
	
	private Graphics buffg; // 더블버퍼링
	private Image bimg = null;
	
	private Image three = new ImageIcon(this.getClass().getResource("../img/3.png")).getImage();
	private Image two = new ImageIcon(this.getClass().getResource("../img/2.png")).getImage();
	private Image one = new ImageIcon(this.getClass().getResource("../img/1.png")).getImage();
	private Image GO = new ImageIcon(this.getClass().getResource("../img/GO.png")).getImage();
	
	
	count(gameGUI gg){
		this.gg=gg;
		System.out.println("카운트1");
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(;;) {
					if(go) {
						repaint();
					}else {
						System.out.println("인게임");
						gg.inGame();
						break;
					}
				}
			}
		}).start();
		
	}
	
	public void update(Graphics g) {
		try {
			for(int i=0;i<1;i++) {
				System.out.println("카운트2");
				buffg.drawImage(three, 0, 0, null);
				g.drawImage(bimg, 0,0,this);
				Thread.sleep(700);
				buffg.clearRect(0, 0, 800, 700);
				buffg.drawImage(two, 0, 0, null);
				g.drawImage(bimg, 0,0,this);
				Thread.sleep(700);
				buffg.clearRect(0, 0, 800, 700);
				buffg.drawImage(one, 0, 0, null);
				g.drawImage(bimg, 0,0,this);
				Thread.sleep(700);
				buffg.clearRect(0, 0, 800, 700);
				buffg.drawImage(GO, 0, 0, null);
				g.drawImage(bimg, 0,0,this);
				go=false;
				System.out.println(go);
				Thread.sleep(700);
				buffg.clearRect(0, 0, 800, 700);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		if(buffg == null) {
			bimg = createImage(800,700);
			if(bimg == null) {
				System.out.println("더블버퍼링 적용 실패");
			}else {
				buffg = bimg.getGraphics();
			}
			update(g);
		}
	}
	public void cntOne() {
		buffg.drawImage(one, 0, 0, null);
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void cntTwo() {
		buffg.drawImage(two, 0, 0, null);
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void cntThree() {
		buffg.drawImage(three, 0, 0, null);
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void cntGo() {
		buffg.drawImage(GO, 0, 0, null);
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
