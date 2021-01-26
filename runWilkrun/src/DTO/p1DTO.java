package DTO;

public class p1DTO {
	private String name = null;
	private int x = 400;
	private int y = 350;
	private int move = 6;
	private int power = 50; //기본 체력
	
	public p1DTO(){
		//	1초마다 체력이 떨어짐 구현
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
						running();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	/* 속도 , 체력 */
	public int getPower() {
		return power;
	}
	
	public int getSpeed() {
		return move;
	}
	
	public void plusPower() {
		power=power+10;
	}
	
	public void minusPower() {
		power = power-10;
	}
	
	public void plusMove() {
		move=move+3;
	}
	
	public void minusMove() {
		if(move>=0) {
		move=move-3;
		}
	}
	
	public void running() {
		power=power-1;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	// ------------- 방향키로 움직임
	public void goUp() {
		if(y>0) {
			y=y-move;
		}
	}
	public void goDown() {
		if(y<600) {
			y=y+move;
		}
	}
	public void goLeft() {
		if(x>0) {
			x=x-move;
		}
	}
	public void goRight() {
		if(x<770) {
			x=x+move;
		}
	}
}
