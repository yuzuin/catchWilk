package list;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import DTO.gameDTO;
import DTO.p1DTO;
import jdbc.DAO;

public class timeRank extends Canvas{
	
	//	이미지
	private Graphics buffg; // 더블버퍼링
	private Image bimg = null;
	
	
	//	리스트
	private ArrayList<gameDTO> games = null;
	private ArrayList<p1DTO> players = null;
	private DAO dao = new DAO();
	
	public timeRank(){
//		showTime();
		showP1();
		repaint();
	}
	
	public void update(Graphics g) {
		int y = 30;
//		for(int i = 0;i<games.size();i++) {
//			System.out.println("시간:"+games.get(i).getRunning());
//			String time = "시간:"+games.get(i).getRunning();
//			buffg.drawString(time, 100, y);
//			y=y+12;
//		}
		
		for(int i = 0;i<players.size();i++) {
			String time = "닉네임 : "+players.get(i).getName()+" 이긴횟수:"+players.get(i).getWinCnt()+" 진횟수:"+players.get(i).getLoseCnt()+" 최소시간:"+players.get(i).getMinTime();
			buffg.drawString(time, 10, y);
			y=y+12;
		}
		g.drawImage(bimg, 0,0,this);
	}
	
	//	더블버퍼링
	public void paint(Graphics g) {
		if(buffg == null) {
			bimg = createImage(350,550);
			if(bimg == null) {
				System.out.println("더블버퍼링 적용 실패");
			}else {
				buffg = bimg.getGraphics();
			}
			update(g);
		}
	}
	
	public void showTime() {
		games = dao.timeRank();
		
	}
	
	public void showP1() {
		players = dao.p1List();
	}
}
