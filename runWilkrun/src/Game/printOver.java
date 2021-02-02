package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

import DTO.p1DTO;
import DTO.p2DTO;

/* 게임 오버 화면 클래스 */
public class printOver extends Canvas {
	
	private GameRoom gr = null;
	private p1DTO bread = null;
	private p2DTO wilk = null;
	private printPlayers pp = null;
	private gameGUI gg = null;
	
	// 이미지
	private Graphics buffg; // 더블버퍼링
	private Image bimg = null;
	private Image winlose = new ImageIcon(this.getClass().getResource("../img/win-lose.png")).getImage();
	private Image losewin = new ImageIcon(this.getClass().getResource("../img/lose-win.png")).getImage();
	private Image draw = new ImageIcon(this.getClass().getResource("../img/draw.png")).getImage();
	
	//	bgm
	private File winSound = new File("./src/sound/winSound.wav");
	private File rankSound = new File("./src/sound/rankingSound.wav");
	private AudioInputStream winAis;
	private Clip winClip;
	private AudioInputStream rankAis;
	private Clip rankClip;
	
	// 폰트
	private Font font1 = new Font("Dotum",Font.PLAIN,18);
	private Font font2 = new Font("Gulim",Font.PLAIN,20);
	
	printOver(GameRoom gr,printPlayers pp){
		System.out.println("프린트오버");
		this.requestFocus();
		bread = gr.p1;
		wilk = gr.p2;
		this.pp=pp;
		repaint();
		System.out.println("리페인트완");
	}
	
	public void update(Graphics g) {
		buffg.setFont(font1);
		buffg.clearRect(0, 0, 800, 700);
		if(bread.getOutcome().equals("win")) {
			buffg.drawImage(winlose, 0, 0, null);
			winSound();
		}else if(wilk.getOutcome().equals("win")) {
			buffg.drawImage(losewin, 0, 0, null);
			winSound();
		}else {
			buffg.drawImage(draw, 0, 0, null);
			winSound();
		}
		String time = "걸린 시간 : "+String.valueOf(bread.getTime())+"초!";
		buffg.setColor(Color.white);
		buffg.drawString(time, 310, 675);
		setInfo();
		g.drawImage(bimg,0,0,this);
		rankSound();
	}
	
	//	더블버퍼링
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
	
	
	public void winSound() {
		try {
			winAis = AudioSystem.getAudioInputStream(winSound);
			winClip = AudioSystem.getClip();
			winClip.open(winAis);
			winClip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rankSound() {
		try {
			rankAis = AudioSystem.getAudioInputStream(rankSound);
			rankClip = AudioSystem.getClip();
			rankClip.open(rankAis);
			rankClip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setInfo() {
		buffg.setFont(font2);
		buffg.setColor(Color.black);
		String Name = bread.getName();
		String MaxMove = String.valueOf(bread.getMaxMove());
		String MaxPower = String.valueOf(bread.getMaxPower());
		
		buffg.drawString(Name, 173, 490);
		buffg.drawString(MaxMove, 190, 555);
		buffg.drawString(MaxPower, 190, 615);
		
		Name = wilk.getName();
		MaxMove = String.valueOf(wilk.getMaxMove());
		MaxPower = String.valueOf(wilk.getMaxPower());
		
		buffg.drawString(Name, 550, 496);
		buffg.drawString(MaxMove, 570, 556);
		buffg.drawString(MaxPower,570, 616);
	}
	
	public void getGG(gameGUI gg) {
		this.gg=gg;
	}

}
