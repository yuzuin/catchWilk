package list;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import DTO.infoDTO;
import DTO.p1DTO;
import Game.printPlayers;
import jdbc.DAO;

/* 랭킹 보는 버튼 출력 화면 */
public class Ranking extends Canvas{
	
	private DAO db = null;
	private printPlayers pp = null;
	private infoDTO info = null;
	private p1DTO p = null;
	
	
	//	글씨
	private Font font2 = new Font("Gulim",Font.PLAIN,20);
	private int width;
	private int height;
	private FontMetrics metrics = null;
	
	// 이미지
	private Graphics buffg; // 더블버퍼링
	private Image bimg = null;
	Image ranks = new ImageIcon(this.getClass().getResource("../img/ranking.png")).getImage();
	
	public Ranking(printPlayers pp){
		this.pp=pp;
		this.db=pp.db;
		System.out.println("출력");
		repaint();
	}
	
	
	public void update(Graphics g) {
		buffg.drawImage(ranks, 0, 0, null);
		metrics = g.getFontMetrics(font2);
		setSpeed();
		setPower();
		setMostWin();
		setMostLose();
		setFastWin();
		setMyRank();
		g.drawImage(bimg,0,0,this);
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
	
	public void setSpeed() {
		info=db.maxSpeed();
		buffg.setFont(font2);
		String move = info.getName()+", ("+String.valueOf(info.getMove())+"s)";
		width = metrics.stringWidth(move);
		buffg.drawString(move, 400/2-width/2, 180);
		
	}
	public void setPower() {
		info=db.maxPower();
		buffg.setFont(font2);
		String power = info.getName()+", ("+String.valueOf(info.getPower())+"p)";
		width = metrics.stringWidth(power);
		buffg.drawString(power, 1200/2-width/2, 180);
		
	}
	
	public void setMostWin() {
		info=db.mostWin();
		buffg.setFont(font2);
		String win = info.getName()+", "+info.getWinCnt()+"회 승리 ("+info.getWinAsB()+"/"+info.getWinAsW()+")";
		width = metrics.stringWidth(win);
		buffg.drawString(win, 400/2-width/2, 413);
	}
	
	public void setMostLose() {
		info=db.mostLose();
		buffg.setFont(font2);
		String lose = info.getName()+", "+info.getLoseCnt()+"회 패배 ("+info.getLoseAsB()+"/"+info.getLoseAsW()+")";
		width = metrics.stringWidth(lose);
		buffg.drawString(lose, 1200/2-width/2, 413);
	}
	
	public void setFastWin() {
		info=db.fastWin();
		buffg.setFont(font2);
		String fast = info.getName()+", "+info.getMinTime()+"s";
		width = metrics.stringWidth(fast);
		buffg.drawString(fast, 400/2-width/2, 637);
	}
	
	public void setMyRank() {
		if(pp.getB().getOutcome().equals("win")) {
			info = db.myRank(pp.getB().getName());
			buffg.setFont(font2);
			String rank = pp.getB().getName()+"의 순위 : "+info.getTimeRank()+"위";
			width = metrics.stringWidth(rank);
			String rank2 = "앞 순위와 "+info.getDiff()+"s 차이";
			buffg.drawString(rank, 1200/2-width/2, 587);
			buffg.drawString(rank2, 1200/2-width/2, 637);
			
		}else if(pp.getB().getOutcome().equals("lose")) {
			info = db.myRank(pp.getB().getEnemy());
			buffg.setFont(font2);
			String rank = pp.getB().getEnemy()+"의 순위 : "+info.getTimeRank()+"위";
			width = metrics.stringWidth(rank);
			String rank2 = "앞 순위와 "+info.getDiff()+"s 차이";
			buffg.drawString(rank, 1090/2-width/2, 567);
			buffg.drawString(rank2, 1090/2-width/2, 617);
		}
	}
}
