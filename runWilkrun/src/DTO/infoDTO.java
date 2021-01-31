package DTO;

public class infoDTO {
	private String name = null;
	private int move = 0;
	private int power = 0;
	private float time = -1;
	private int winCnt = 0;
	private int loseCnt = 0;
	private int winAsB = 0;
	private int winAsW = 0;
	private int loseAsB=0;
	private int loseAsW=0;
	private float minTime = 0;
	private float diff = 0;
	private int timeRank = -1;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMove() {
		return move;
	}
	public void setMove(int move) {
		this.move = move;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	public int getWinCnt() {
		return winCnt;
	}
	public void setWinCnt(int winCnt) {
		this.winCnt = winCnt;
	}
	public int getLoseCnt() {
		return loseCnt;
	}
	public void setLoseCnt(int loseCnt) {
		this.loseCnt = loseCnt;
	}
	public int getWinAsB() {
		return winAsB;
	}
	public void setWinAsB(int winAsB) {
		this.winAsB = winAsB;
	}
	public int getWinAsW() {
		return winAsW;
	}
	public void setWinAsW(int winAsW) {
		this.winAsW = winAsW;
	}
	public int getLoseAsB() {
		return loseAsB;
	}
	public void setLoseAsB(int loseAsB) {
		this.loseAsB = loseAsB;
	}
	public int getLoseAsW() {
		return loseAsW;
	}
	public void setLoseAsW(int loseAsW) {
		this.loseAsW = loseAsW;
	}
	public float getMinTime() {
		return minTime;
	}
	public void setMinTime(float minTime) {
		this.minTime = minTime;
	}
	public float getDiff() {
		return diff;
	}
	public void setDiff(float diff) {
		this.diff = diff;
	}
	public int getTimeRank() {
		return timeRank;
	}
	public void setTimeRank(int timeRank) {
		this.timeRank = timeRank;
	}
	
	
	
}
