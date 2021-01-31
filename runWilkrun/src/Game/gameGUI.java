package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DTO.p1DTO;
import DTO.p2DTO;
import list.Ranking;

public class gameGUI extends JFrame implements ActionListener, KeyListener{
	
	//	이미지
	private Image background = new ImageIcon(this.getClass().getResource("../img/start.png")).getImage();
	private File startIcon = new File("./src/img/startBtn.png");
	
	//라벨, 텍스트필드, 콘테이너
	private JTextField input1P = new JTextField(); 
	private JTextField input2P = new JTextField();
	private JButton startBtn = new JButton(new ImageIcon(startIcon.getAbsolutePath()));
	private JPanel startPanel = new JPanel();
	
	public JPanel after = new JPanel();
	private JTextField search = new JTextField();
	private JButton searchBtn = new JButton("검색");
	
	//객체
	
	private printPlayers pp = null; // 게임 캔버스
	private GameRoom gr = null;
	private File file = new File("./src/sound/ingameBGM.wav");
	
	//조건
	boolean itemStart = false;
	boolean btn = false;
	boolean gameOn = false;
	
	//	sound
	private File startSnd = new File("./src/sound/startSound.wav");
	AudioInputStream ais;
	Clip clip;
	
	
	gameGUI(GameRoom gr,printPlayers pp){
		this.pp=pp;
		this.gr=gr;
		setFrame();
		startBGM();
		startFrame(); //	첫 시작 화면
//		this.requestFocus();
//		this.addKeyListener(new Key(gr,this)); // 이 컴포넌트가 이벤트를 받을 수 있도록 한다. 
	}


	private void setFrame() {
		this.setBounds(270,10,800,700);
		this.addLis();
		this.setUndecorated(true);
		this.getContentPane().setLayout(new java.awt.BorderLayout(0,0));
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}
	
	public void startFrame() {
		
		startPanel.setBackground(new Color(255,0,0,0));
		startPanel.setBounds(10, 30, 600, 600);
		this.add(startPanel, BorderLayout.CENTER);
		startPanel.setLayout(null);
		
		//	input이 들어있는 panel
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(200, 235, 400, 200);
		startPanel.add(panel_1);
		panel_1.setLayout(null);
		
		//	input 1P / 2P
		input1P.setBounds(160, 36, 190, 30);
		panel_1.add(input1P);
		input1P.setColumns(20);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 346, 300);
		startPanel.add(panel_2);
		panel_2.setLayout(null);
		
		input2P.setBounds(160, 108, 190, 30);
		panel_1.add(input2P);
		input2P.setColumns(20);
		addLis();
		
		//	겜시작 버튼
		startBtn.setBounds(365, 480, 80, 80);
		startPanel.setBackground(new Color(255,0,0,0));
		startPanel.setOpaque(false);
		startBtn.setBorderPainted(false);
		startBtn.setContentAreaFilled(false);
		startBtn.setFocusPainted(false);
		startBtn.setOpaque(false);
		startPanel.add(startBtn);
	}
	
	private void inGame() {
		// 3,2,1 세는 부분은 리페인트가 필요하니 printPlayers 에서 하는 것으로... 게임 먼저 하자..
		this.remove(startPanel);
		itemStart = true;
		this.add(pp,"Center");
		pp.requestFocus();	// 이 컴포넌트가 이벤트를 받을 수 있도록 한다. 
		pp.addKeyListener(new Key(gr,this,pp));  // pp가 이벤트를 받아야하므로. 중요!!!
		pp.ingameBGM();
		clip.stop();
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		g.drawImage(background, 0,0,null);
	}
	
	public void addLis() {
		startBtn.addActionListener(this);
		this.addKeyListener(this);
		input1P.addKeyListener(this);
		input2P.addKeyListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(startBtn)) {
			clickBtn();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar()=='w') {
			System.out.println("ㅋㅋㅋㅆㅣㅏㅂㄹ");
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// 	ESC 누를 시 종료
		if(e.getKeyCode()==27) {
	    	  System.exit(0);
	      }else if(e.getKeyCode()==13) { //	엔터키 겜종료
	    	  clickBtn();
		}else if(e.getKeyCode()==10) {
			clickBtn();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/*게임오버화면*/
	public void AfterGame(printOver po) {
		this.remove(pp);
		this.add(po,"Center");
		this.setVisible(true);
	}
	
	/*랭킹화면 */
	public void showRank(printOver po,Ranking rk) {
		this.remove(po);
//		after.setBounds(0,0,800,700);
//		search.setBounds(600, 600, 10, 20);
//		after.add(search);
//		searchBtn.setBounds(630, 600, 10, 5);
//		after.add(searchBtn);
//		after.add(rk);
//		after.setVisible(true);
		this.add(rk);
		this.setVisible(true);
	}
	private void startBGM() {
		try {
			if(ais==null) {
				ais = AudioSystem.getAudioInputStream(startSnd);
				clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void clickBtn() {
		// 닉네임을 받아서 1p, 2p 객체로 넘겨주기
					boolean p1_chk = false; // 이름이 입력되었는지 체크 
					boolean p2_chk = false;
					
					gr.p1 = new p1DTO();
					if(input1P.getText().equals("")) { 	//	텍스트필드에 아무것도 입력되어 있지 않으면 null이 아닌 ""
						System.out.println("플레이어1의 이름을 입력하세요.");
						JOptionPane.showMessageDialog(null, "플레이어1의 이름을 입력하세요.");
					}else {
						gr.p1.setName(input1P.getText());
						p1_chk=true;
					}
					
					gr.p2 = new p2DTO();
					if(input2P.getText().equals("")) {
						System.out.println("플레이어1의 이름을 입력하세요.");
						JOptionPane.showMessageDialog(null, "플레이어2의 이름을 입력하세요.");
					}else {
						gr.p2.setName(input2P.getText());
						p2_chk=true;
					}
					
					if(p1_chk&&p2_chk) {
						if(gameOn==false) {
							System.out.println("게임 시~~작!");
							gameOn=true;
							inGame(); //	겜시작
						}
					}
	}
}
