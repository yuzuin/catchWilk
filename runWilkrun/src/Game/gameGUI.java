package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DTO.p1DTO;
import DTO.p2DTO;

public class gameGUI extends JFrame implements ActionListener{
	
	//라벨, 텍스트필드
	private JLabel title = new JLabel("도망쳐 윌크!!");
	private JTextField input1P = new JTextField(); ;
	private JTextField input2P = new JTextField();
	private JButton startBtn = new JButton("게임 시작");
	private JPanel startPanel = new JPanel();
	private JLabel scondLabel = new JLabel(); //초세기
	
	//객체
	
	private printPlayers pp = null; // 게임 캔버스
	private GameRoom gr = null;
	private Key key = null;
	
	//조건
	boolean itemStart = false;
	
	gameGUI(GameRoom gr,printPlayers pp){
		this.pp=pp;
		this.gr=gr;
		addLis();
		setFrame();
		startFrame(); //	첫 시작 화면
//		this.requestFocus();
//		this.addKeyListener(new Key(gr,this)); // 이 컴포넌트가 이벤트를 받을 수 있도록 한다. 
	}


	private void setFrame() {
		this.setBounds(270,10,800,700);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBackground(new Color(64, 64, 64));
		title.setForeground(Color.RED);
		title.setFont(new Font("한컴바탕", Font.BOLD, 31));
		this.add(title, BorderLayout.NORTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}
	
	public void startFrame() {
		
		startPanel.setBackground(SystemColor.textHighlight);
		this.add(startPanel, BorderLayout.CENTER);
		startPanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(219, 201, 346, 24);
		startPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel player1Lbl = new JLabel("플레이어1의 이름 ");
		player1Lbl.setBounds(0, 0, 170, 24);
		panel_1.add(player1Lbl);
		player1Lbl.setFont(new Font("굴림체", Font.PLAIN, 20));
		
		
		input1P.setBounds(175, 1, 171, 21);
		panel_1.add(input1P);
		input1P.setColumns(15);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(219, 300, 346, 25);
		startPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel player1Lbl_2 = new JLabel("플레이어2의 이름");
		player1Lbl_2.setBounds(0, 0, 160, 24);
		panel_2.add(player1Lbl_2);
		player1Lbl_2.setFont(new Font("굴림체", Font.PLAIN, 20));
		
		input2P.setBounds(175, 4, 171, 21);
		panel_2.add(input2P);
		input2P.setColumns(15);
		
		
		startBtn.setFont(new Font("11롯데마트드림Bold", Font.PLAIN, 32));
		startBtn.setBounds(293, 441, 197, 80);
		startPanel.add(startBtn);
	}
	
	private void inGame() {
		// 3,2,1 세는 부분은 리페인트가 필요하니 printPlayers 에서 하는 것으로... 게임 먼저 하자..
		itemStart = true;
		this.remove(startPanel);
		this.add(pp,"Center");
		pp.requestFocus();	// 이 컴포넌트가 이벤트를 받을 수 있도록 한다. 
		pp.addKeyListener(new Key(gr,this,pp));  // pp가 이벤트를 받아야하므로. 중요!!!
		this.remove(title);
		this.setVisible(true);
	}
	
//	public void cntSecond(String cnt) {
//		scondLabel.setText(cnt);
//		scondLabel.setForeground(UIManager.getColor("TextArea.selectionBackground"));
//		scondLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		scondLabel.setFont(new Font("HY백송B", Font.BOLD | Font.ITALIC, 99));
//		this.add(scondLabel, BorderLayout.CENTER);
//		this.setVisible(true); //	비저블-트루를 넣어야 panel이 지워지고, 새로운 화면이 보임.
//	}
	
	public void addLis() {
		startBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(startBtn)) {
			// 닉네임을 받아서 1p, 2p 객체로 넘겨주기
			boolean p1_chk = false; // 이름이 입력되었는지 체크 
			boolean p2_chk = false;
			
			gr.p1 = new p1DTO();
			if(input1P.getText().equals("")) { 	//	텍스트필드에 아무것도 입력되어 있지 않으면 null이 아닌 ""
				System.out.println("플레이어1의 이름을 입력하세요.");
			}else {
				gr.p1.setName(input1P.getText());
				p1_chk=true;
			}
			
			gr.p2 = new p2DTO();
			if(input2P.getText().equals("")) {
				System.out.println("플레이어1의 이름을 입력하세요.");
			}else {
				gr.p2.setName(input2P.getText());
				p2_chk=true;
			}
			
			if(p1_chk&&p2_chk) {
				System.out.println("게임 시~~작!");
//				key = new Key(gr);
				inGame(); //	겜시작
			}
		}
	}
}
