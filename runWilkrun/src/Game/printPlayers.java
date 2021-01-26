package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import DTO.itemDTO;
import DTO.p1DTO;
import DTO.p2DTO;

public class printPlayers extends Canvas{
	
	private GameRoom gr = null;
	private p1DTO bread = null;
	private p2DTO wilk = null;
	private int pSize = 30; 	// player Size
	
	//	아이템
	private ArrayList<itemDTO> iList = null;
	private Random r = new Random();
	private int p1X;
	private int p1Y;
	private int p2X;
	private int p2Y;
	
	// 이미지
	private Graphics buffg; // 더블버퍼링
	private Image bimg = null;
	private Image breadImg = new ImageIcon(this.getClass().getResource("../img/bread_30x30.png")).getImage();
	private Image wilkImg = new ImageIcon(this.getClass().getResource("../img/wilk_30x30.png")).getImage();
	private Image butterImg = new ImageIcon(this.getClass().getResource("../img/butter_50x68.png")).getImage();
	
	printPlayers(GameRoom gr){
//		System.out.println(this.getClass().getResource("../img/bread_30x30.png"));
		this.gr=gr;
		this.setBackground(Color.pink);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(30);
						repaint();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public void update(Graphics g) {
		buffg.clearRect(0,00,800,700);
		init();
		buffg.drawImage(breadImg, bread.getX(), bread.getY(),null);
		buffg.drawImage(wilkImg, wilk.getX(), wilk.getY(),null);
		if(iList!=null) {
			printItems();
		}
		printPower();
		g.drawImage(bimg, 0,0,this);
	}
	public void init() { 
		this.bread = gr.p1;
		this.wilk = gr.p2;
		this.iList=gr.itemList;
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
	
	public void printItems() {
		if(iList!=null) {
			for(int i=0;i<iList.size();i++) {
				if(iList.get(i).getName().equals("speedup")) {
					buffg.drawImage(butterImg, iList.get(i).getX(), iList.get(i).getY(),null);
				}else {
					buffg.drawString(iList.get(i).getName(), iList.get(i).getX(), iList.get(i).getY());
				}
				p1X = gr.p1.getX();
				p1Y = gr.p1.getY();
				p2X = gr.p2.getX();
				p2Y = gr.p2.getY();
				int iX = iList.get(i).getX();
				int iY = iList.get(i).getY();
				
				//	템먹기 구현
				if(iList!=null) {
					if(eat(p1X,iX,p1Y,iY,50,68)) {
						if(iList.get(i).getName().equals("speedup")) {
							gr.p1.plusMove();
							iList.remove(i);
						}else if(iList.get(i).getName().equals("speeddown")) {
							gr.p1.minusMove();
							iList.remove(i);
						}else if(iList.get(i).getName().equals("powerup")) {
							gr.p1.plusPower();
							iList.remove(i);
						}else if(iList.get(i).getName().equals("powerdown")) {
							gr.p1.minusPower();
							iList.remove(i);
						}
					}
				}
				if(iList!=null) {
					if(eat(p2X,iX,p2Y,iY,50,68)) {
						if(iList.get(i).getName().equals("speedup")) {
							gr.p2.plusMove();
							iList.remove(i);
						}else if(iList.get(i).getName().equals("speeddown")) {
							gr.p2.minusMove();
							iList.remove(i);
						}else if(iList.get(i).getName().equals("powerup")) {
							gr.p2.plusPower();
							iList.remove(i);
						}else if(iList.get(i).getName().equals("powerdown")) {
							gr.p2.minusPower();
							iList.remove(i);
						}
					}
				}
			}
		}
	}
	
	//	4개의 모서리로 다 먹을 수 있게..
	public boolean eat(int px,int ix,int py,int iy,int iWidth,int iHeight) {
		if((px<=ix+iWidth&&px>=ix)&&(py<=iy+iHeight&&py>=iy)) {
			return true;
		}else if((px+pSize<=ix+iWidth&&px+pSize>=ix)&&(py<=iy+iHeight&&py>=iy)) {
			return true;
		}else if((px+pSize<=ix+iWidth&&px+pSize>=ix)&&(py+pSize<=iy+iHeight&&py+pSize>=iy)) {
			return true;
		}else if((px<=ix+iWidth&&px>=ix)&&(py+pSize<=iy+iHeight&&py+pSize>=iy)) {
			return true;
		}
		return false;
	}
	
	
	public void printPower() {
		//	파워, 스피드 출력
		String info1 = "1P\n"+gr.p1.getPower()+" 파워!\n"+gr.p1.getSpeed()+" 스피드!";
		buffg.drawString(info1, 20, 580);
		
		String info2 = "2P\n"+gr.p2.getPower()+" 파워!\n"+gr.p2.getSpeed()+" 스피드!";
		buffg.drawString(info2, 680, 580);
	}
	
}
