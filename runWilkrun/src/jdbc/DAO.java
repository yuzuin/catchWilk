package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.gameDTO;
import DTO.infoDTO;
import DTO.p1DTO;
import DTO.p2DTO;

public class DAO {
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //드라이버정보. final : 고칠 수 없다는 뜻
	private final String DB_URL = "jdbc:mysql://localhost/catchWilk?&serverTimezone=UTC"; //serverTimezone은 그냥 쓰라해서 쓰는거
	private final String USER_NAME = "root"; //아이디
	private final String PASSWORD = "1111"; //암호
	
	private Connection conn=null; //DB와 연결하기 위한 객체
	private PreparedStatement pstmt = null; //쿼리문과 데이터를 ?,?,? 에 mapping 해주기 위한 객체이다. 
	private Statement stmt = null; //물음표 없을 때는 Statement, 물음표가 있으면 Prepared
	private ResultSet rs = null; //쿼리 결과인 튜플을 2차원 배열 형식처럼 가져오는 객체

	public DAO(){
		//초기 준비 작업
		try {
			Class.forName(JDBC_DRIVER); //클래스의 정보를 받아오는 클래스
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} //DB인식
	}
	
	private Connection getConn() {
		try{
			conn=DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
			System.out.println("연결성공");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn; //connection이라는 개체를 갖고와라 return해라
	}
	
	/* 플레이어1 인서트 */
	public void insert(p1DTO p1) {
		if(getConn()!=null) {
			try {
				String sql = "insert into player1(num,name,time,outcome,move,power,enemy) values(0,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,p1.getName());
				pstmt.setFloat(2, p1.getTime());
				pstmt.setString(3, p1.getOutcome());
				pstmt.setInt(4, p1.getMaxMove());
				pstmt.setInt(5, p1.getMaxPower());
				pstmt.setString(6, p1.getEnemy());
				int rs = pstmt.executeUpdate();
				System.out.println("P1 정보 "+rs+"건 입력 성공");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/* 플레이어2 인서트 */
	public void insert(p2DTO p2) {
		if(getConn()!=null) {
			try {
				String sql = "insert into player2(num,name,time,outcome,move,power,enemy) values(0,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,p2.getName());
				pstmt.setFloat(2, p2.getTime());
				pstmt.setString(3, p2.getOutcome());
				pstmt.setInt(4, p2.getMaxMove());
				pstmt.setInt(5, p2.getMaxPower());
				pstmt.setString(6, p2.getEnemy());
				int rs = pstmt.executeUpdate();
				System.out.println("P2 정보 "+rs+"건 입력 성공");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/* 게임정보 인서트 */
	public void insert(gameDTO game) {
		if(getConn()!=null) {
			try {
				String sql = "insert into games(num,p1,p2,playtime,running,winner) values(0,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,game.getP1());
				pstmt.setString(2,game.getP2());
				pstmt.setString(3,game.getPlayTime());
				pstmt.setFloat(4, game.getRunning());
				pstmt.setString(5, game.getWinner());
				int rs = pstmt.executeUpdate();
				System.out.println("게임 정보 "+rs+"건 입력 성공");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/* 시간 순위 */
	public ArrayList<gameDTO> timeRank(){
		ArrayList<gameDTO> tr = new ArrayList<>();
		if(getConn()!=null) {
			try {
				String sql = "select *, rank() over(order by running) as 순위 from games;";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql); //rs는 가져올 때 필요함.
				while(rs.next()) {
					gameDTO g = new gameDTO();
					g.setNum(rs.getInt("num"));
					g.setP1(rs.getString("p1"));
					g.setP2(rs.getString("p2"));
					g.setPlayTime(rs.getString("playtime"));
					g.setRunning(rs.getFloat("running"));
					g.setWinner(rs.getString("winner"));
					g.setTimeRank(rs.getString("순위"));
					tr.add(g);
				}
			} catch (Exception e) {
			}
		}
		return tr;
	}
	
	/* p1만 보기 */
	
	public ArrayList<p1DTO> p1List(){
		ArrayList<p1DTO> p1List = new ArrayList<>();
		if(getConn()!=null) {
			try {
				//1.	name이 중복되지 않게 저장
				String sql = "select distinct name from player1; ";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql); //rs는 가져올 때 필요함.
				while(rs.next()) {
					p1DTO p = new p1DTO();
					p.setName(rs.getString("name"));
					p1List.add(p);
				}
				
				//2.	이긴 횟수/진 횟수 set
				sql = "select count(*) from player1 where outcome='win' and name=?;";
				pstmt = conn.prepareStatement(sql);
				
				for(int i=0;i<p1List.size();i++) {
					pstmt.setString(1, p1List.get(i).getName());
					rs = pstmt.executeQuery(); //rs는 가져올 때 필요함.
					if(rs.next()) {	//	rs.next()가 있어야 제대로 동작한다
						int cntRs = rs.getInt("count(*)");
						p1List.get(i).setWinCnt(cntRs);
					}
				}
				
				sql = "select count(*) from player1 where outcome='lose' and name=?;";
				pstmt = conn.prepareStatement(sql);
				for(int i=0;i<p1List.size();i++) {
					pstmt.setString(1, p1List.get(i).getName());
					rs = pstmt.executeQuery(); //rs는 가져올 때 필요함.
					if(rs.next()) {
						p1List.get(i).setLoseCnt(rs.getInt("count(*)"));
					}
				}
				
				//3.	이기고, 가장 최소인 시간
				sql = "select min(time) from player1 where outcome='win' and name=?;";
				pstmt = conn.prepareStatement(sql);
				for(int i=0;i<p1List.size();i++) {
					pstmt.setString(1, p1List.get(i).getName());
					rs = pstmt.executeQuery(); //rs는 가져올 때 필요함.
					if(rs.next()) {
						p1List.get(i).setMinTime(rs.getFloat("min(time)"));
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return p1List;
	}
	
	/* p2만 보기 */
	
	public ArrayList<p2DTO> p2List(){
		ArrayList<p2DTO> p2List = new ArrayList<>();
		if(getConn()!=null) {
			try {
				//1.	name이 중복되지 않게 저장
				String sql = "select distinct name from player2; ";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql); //rs는 가져올 때 필요함.
				while(rs.next()) {
					p2DTO p = new p2DTO();
					p.setName(rs.getString("name"));
					p2List.add(p);
				}
				
				//2.	이긴 횟수/진 횟수 set
				sql = "select count(*) from player2 where outcome='win' and name=?;";
				pstmt = conn.prepareStatement(sql);
				
				for(int i=0;i<p2List.size();i++) {
					pstmt.setString(1, p2List.get(i).getName());
					rs = pstmt.executeQuery(); //rs는 가져올 때 필요함.
					if(rs.next()) {	//	rs.next()가 있어야 제대로 동작한다
						int cntRs = rs.getInt("count(*)");
						p2List.get(i).setWinCnt(cntRs);
					}
				}
				
				sql = "select count(*) from player2 where outcome='lose' and name=?;";
				pstmt = conn.prepareStatement(sql);
				for(int i=0;i<p2List.size();i++) {
					pstmt.setString(1, p2List.get(i).getName());
					rs = pstmt.executeQuery(); //rs는 가져올 때 필요함.
					if(rs.next()) {
						p2List.get(i).setLoseCnt(rs.getInt("count(*)"));
					}
				}
				
				//3.	이기고, 가장 최소인 시간
				sql = "select min(time) from player2 where outcome='win' and name=?;";
				pstmt = conn.prepareStatement(sql);
				for(int i=0;i<p2List.size();i++) {
					pstmt.setString(1, p2List.get(i).getName());
					rs = pstmt.executeQuery(); //rs는 가져올 때 필요함.
					if(rs.next()) {
						p2List.get(i).setMinTime(rs.getFloat("min(time)"));
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return p2List;
	}
	
	/*최대속도 구하기*/
	public infoDTO maxSpeed(){
		infoDTO i = new infoDTO();
		if(getConn()!=null) {
			try {
				String sql = "select name, max(move) from (select * from player1 union select * from player2)t;";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql); //rs는 가져올 때 필요함.
				while(rs.next()) {
					i.setName(rs.getString("name"));
					i.setMove(rs.getInt("max(move)"));
				}
			} catch (Exception e) {
			}
		}
		return i;
	}
	
	/*최대파워 구하기*/
	public infoDTO maxPower(){
		infoDTO i = new infoDTO();
		if(getConn()!=null) {
			try {
				String sql = "select name, max(power) from (select * from player1 union select * from player2)t;";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql); //rs는 가져올 때 필요함.
				while(rs.next()) {
					i.setName(rs.getString("name"));
					i.setPower(rs.getInt("max(power)"));
				}
			} catch (Exception e) {
			}
		}
		return i;
	}
	
	/*가장 많이 이긴 사람*/
	public infoDTO mostWin(){
		infoDTO i = new infoDTO();
		if(getConn()!=null) {
			try {
				String sql = "select name,time,max(ranking) from (select name,time,count(*) as ranking from (select * from player1 union select * from player2)t where outcome='win' group by name order by count(*) desc)t2;";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql); //rs는 가져올 때 필요함.
				if(rs.next()) {
					i.setName(rs.getString("name"));
					i.setWinCnt(rs.getInt("max(ranking)"));
					}
				sql = "select * from player1 where outcome='win' and name=?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, i.getName());
				rs = pstmt.executeQuery(); //rs는 가져올 때 필요함.
				int cnt = 0;
				while(rs.next()) {
					cnt++;
				}
				i.setWinAsB(cnt);
				
				sql = "select * from player2 where outcome='win' and name=?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, i.getName());
				rs = pstmt.executeQuery(); //rs는 가져올 때 필요함.
				cnt = 0;
				while(rs.next()) {
					cnt++;
				}
				i.setWinAsW(cnt);
			} catch (Exception e) {
			}
		}
		return i;
	}
	
	/*가장 많이 진 사람*/
	public infoDTO mostLose(){
		infoDTO i = new infoDTO();
		if(getConn()!=null) {
			try {
				String sql = "select name,time,max(ranking) from (select name,time,count(*) as ranking from (select * from player1 union select * from player2)t where outcome='lose' group by name order by count(*) desc)t2;";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql); //rs는 가져올 때 필요함.
				while(rs.next()) {
					i.setName(rs.getString("name"));
					i.setLoseCnt(rs.getInt("max(ranking)"));
				}
				
				sql = "select * from player1 where outcome='lose' and name=?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, i.getName());
				rs = pstmt.executeQuery(); //rs는 가져올 때 필요함.
				int cnt = 0;
				while(rs.next()) {
					cnt++;
				}
				i.setLoseAsB(cnt);
				sql = "select * from player2 where outcome='lose' and name=?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, i.getName());
				rs = pstmt.executeQuery(); //rs는 가져올 때 필요함.
				cnt = 0;
				while(rs.next()) {
					cnt++;
				}
				i.setLoseAsW(cnt);
			} catch (Exception e) {
			}
		}
		return i;
	}
	
	/*최속승리*/
	public infoDTO fastWin(){
		infoDTO i = new infoDTO();
		if(getConn()!=null) {
			try {
				String sql = "select name,min(time) from (select * from player1 union select * from player2 where outcome='win' order by time)t;";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql); //rs는 가져올 때 필요함.
				if(rs.next()) {
					i.setName(rs.getString("name"));
					i.setMinTime(rs.getFloat("time"));
				}
			} catch (Exception e) {
			}
		}
		return i;
	}
	
	/* 나의 이긴 속도 순위 */
	public infoDTO myRank(String n) {
		infoDTO i = new infoDTO();
		float diff = -1;
		if(getConn()!=null) {
			try {
				String sql = "select name, time from (select * from player1 union select * from player2)t where outcome = 'win' order by time;";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql); //rs는 가져올 때 필요함.
				int cnt = 0;
				while(rs.next()) {
					cnt++;
					if(rs.getString("name").equals(n)) {
						i.setTimeRank(cnt);
						i.setDiff(rs.getFloat("time")-diff);
						break;
					}
					diff = rs.getFloat("time");
				}
			} catch (Exception e) {
			}
		}
		return i;
		
	}
}
