package DTO;

public class gameDTO {
	/* 게임 정보 DTO */
	private int num = -1;
	private String p1 = null;
	private String p2 = null;
	private String playTime = null;
	private float running = 1;
	private String winner = null;
	private String timeRank = null;
	
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTimeRank() {
		return timeRank;
	}
	public void setTimeRank(String timeRank) {
		this.timeRank = timeRank;
	}
	public String getP1() {
		return p1;
	}
	public void setP1(String p1) {
		this.p1 = p1;
	}
	public String getP2() {
		return p2;
	}
	public void setP2(String p2) {
		this.p2 = p2;
	}
	public String getPlayTime() {
		return playTime;
	}
	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}
	public float getRunning() {
		return running;
	}
	public void setRunning(float running) {
		this.running = running;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	
}
