package Game;

import java.util.ArrayList;
import java.util.Random;

import DTO.itemDTO;
import DTO.p1DTO;
import DTO.p2DTO;

public class GameRoom {
	
	// 이 관계가 너무 어려워..ㅠㅠㅠ
	private printPlayers pp = null;
	private gameGUI gg = null;
	
	
	//	플레이어 객체
	p1DTO p1 = null;
	p2DTO p2 = null;
	
	//	아이템
	private Random r = new Random();
	private String[] items = {"powerup","speedup","powerdown","speeddown"};
	ArrayList<itemDTO> itemList = new ArrayList<>();
	
	GameRoom(){
		init();
		gameGo();
	}
	
	private void init() {
		pp = new printPlayers(this);
		gg = new gameGUI(this,pp);
	}
	
	private void gameGo() {
		//	템
		while(true) {
			System.out.println(gg.itemStart);
			boolean start = gg.itemStart;
			try {
				if(start) {	//	게임 시작도 전에 아이템 생성 방지
					Thread.sleep(2000);
					int k = r.nextInt(items.length);
					itemDTO item = new itemDTO();
					item.setName(items[k]);
					item.setX(r.nextInt(770));
					item.setY(r.nextInt(650));
					itemList.add(item);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(itemList.size()>=10) {
				itemList.remove(0);
			}
		}
	}
}
