package othello;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class client2 extends JFrame implements ActionListener , TextListener{

	private static final long serialVersionUID = 1L;
	private static final int NUM_CELL = 64;
	static boolean flag=true,turn1,turn21=true;
	static Socket s;
	int row1 ,col1,row2, col2, h = 0,rrow1,rrow2,rcol1,rcol2,bakuhatsucount=8878;
	JButton sound,login,close,decidebomb,decide,skip2,menu,r1,r2,r3,r4,r5,r6,r7,r8,r9,send,again,backhome,backw,onemorew,backl,onemorel,backd,onemored;
	JTextField t1;
	JTextArea teach_rule;
	JLabel home,rule,match,bombset,time;
	static TextField black_t;
	static JLabel info_of_me;
	static JLabel info_of_rival;
	JLabel menushow,bakuhatsu;;
	static JLabel passshow;
	static JLabel winshow,loseshow,drawshow,com3,com4,com5,com6;
	JLabel bg;
	JLabel bombcounter;
	static JTextArea chat;
	JTextArea messenger_box;
	JScrollPane scroll;
	String server;
	static String id;
	static String scoretext;
	String BGM_1,BGM_2,BGM_3,BGM_4,BGM_51,BGM_52,BGM_53,BGM_54,BGM_6W,BGM_6L,BGM_6D;
	int port;
	static int k=0;
	static int my_color,aite_color;
	static int count;
	static int minutes;
	static int seconds;
	int checktime=0;
	int checkgenre=0;
	static int timeleft;
	static int bombcount=2;
	static int score = 200;
	static int e_time = 0;
	static int bomb = 0,combo3=1000,combo4=1000,combo5=1000,combo6=1000,passcount=1000;
	static int comb_v = 0;
	static int time_score = 0;
	static int combo_score = 0;
	static OutputStream os;
	static DataOutputStream dos;
	static InputStream is;
	static DataInputStream dis;
	static AudioClip Au1;
	AudioClip Au2;
	AudioClip Au3;
	static AudioClip Au4;
	static AudioClip Au5;
	AudioClip Au52;
	AudioClip Au53;
	AudioClip Au54;
	AudioClip Au6w;
	AudioClip Au6l;
	AudioClip Au6d;
	JPanel s_panel,bomb_panel;
	private static final int NUM = 8;
	private static final int ABLE = 3;
	private static final int BLACK = 1;
	private static final int WHITE = 2;
	JButton[][] button = new JButton[NUM][NUM];
	JButton[][] bbutton = new JButton[NUM][NUM];
	ImageIcon white = new ImageIcon("white1.png");
	ImageIcon black = new ImageIcon("black1.png");
	ImageIcon komaok = new ImageIcon("komaok.png");
	ImageIcon icon = new ImageIcon("koma.png");
	static int[][] board = new int[NUM+2][NUM+2];
	static Othello me;
	ObjectInputStream ios;
	ObjectOutputStream oos;
	ImageIcon turnblack = new ImageIcon("turn_to_black.gif"),turnwhite = new ImageIcon("turn_to_white.gif");
	static JLayeredPane game;
	static int bombrow,bombcol,time_limit;
	int[][] BombSet = new int[NUM][NUM],RivalBombSet = new int[NUM][NUM];
	int[] put,rput;
	static int rivalscore=200;
	static String rivalname="NULL";
	static boolean ran_able = true,click=false,end = false,rival_pass = false,me_pass = false,p_flag = true,myturn,play_f = true;
	static client2 c;
	static Player ore;
	static int[] a_row = new int[NUM_CELL],a_column = new int[NUM_CELL];
	static int ran_put,pass=0;

//	MyThread mythread;

	public client2(String title,String a){
//Constructor
		super(title);
		server = a;
		port = 10001;
		ore = new Player();

//Card Home
		System.out.println("start");
		home=new JLabel(new ImageIcon("home.png"));
	    add(home);
	    home.setLayout(null);
	    Border noBorder = new LineBorder(Color.WHITE, 0);
	    login=new JButton();login.setBackground(new Color(38,38,38));login.setBorder(noBorder);
	    close=new JButton();close.setBackground(new Color(38,38,38));close.setBorder(noBorder);
	    t1=new JTextField("");
	    BGM_1 = new String("Opening_Night.wav");
	    BGM_2 = new String("Cataclysmic_Molten_Core.wav");
	    BGM_3 = new String("Les_Toreadors_from_Carmen_by_Bizet.wav");
	    BGM_4 = new String("Laconic_Granny.wav");
	    BGM_51 = new String("The_Simplest.wav");
	    BGM_52 = new String("Cheating_Juarez.wav");
	    BGM_53 = new String("Play_Song.wav");
	    BGM_54 = new String("Universal.wav");
	    BGM_6W = new String("Gold_Rush.wav");
	    BGM_6L = new String("Hero_Theme.wav");
	    BGM_6D = new String("Cryin_In_My_Beer.wav");
		Au1=Applet.newAudioClip(getClass().getResource(BGM_1));
		Au2=Applet.newAudioClip(getClass().getResource(BGM_2));
		Au3=Applet.newAudioClip(getClass().getResource(BGM_3));
		Au4=Applet.newAudioClip(getClass().getResource(BGM_4));
		Au5=Applet.newAudioClip(getClass().getResource(BGM_51));
		Au4=Applet.newAudioClip(getClass().getResource(BGM_52));
		Au4=Applet.newAudioClip(getClass().getResource(BGM_53));
		Au4=Applet.newAudioClip(getClass().getResource(BGM_54));
		Au4=Applet.newAudioClip(getClass().getResource(BGM_6W));
		Au4=Applet.newAudioClip(getClass().getResource(BGM_6L));
		Au4=Applet.newAudioClip(getClass().getResource(BGM_6D));

        playSound(Au1);

	    login.setIcon(new ImageIcon("startbutton.png"));
	    close.setIcon(new ImageIcon("exitbutton.png"));

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,720);
        setResizable(false);
        setVisible(true);

	    login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(!t1.getText().equals("")){
            		stopSound(Au1);
                	playSound(Au2);
                    remove(home);
                    add(rule);
                    repaint();revalidate();
                    id = new String (t1.getText());
                    try {
    					s = new Socket(server,port);
    					is = s.getInputStream();
    					dis = new DataInputStream(is);
    					Send_ID();
    				} catch (IOException e1) {
    					e1.printStackTrace();
    				}
            	}
            }
        });

	    close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

	    home.add(t1);
	    home.add(login);
	    home.add(close);
	    t1.setBounds(88, 362, 310, 40);
	    login.setBounds(140, 440, 209, 60);
	    close.setBounds(140, 559, 209, 60);

//Card Rule

	    rule=new JLabel(new ImageIcon("rule.png"));
	    rule.setLayout(null);
	    decide= new JButton("");rule.add(decide);decide.setBounds(1100, 550, 134, 80);decide.setEnabled(false);decide.setIcon(new ImageIcon("decide.png"));
	    r1= new JButton("");r2= new JButton("");r3= new JButton("");r4= new JButton("");r5= new JButton("");r6= new JButton("");r7= new JButton("");r8= new JButton("");
	    ore.Select(rule, decide, r1, r2, r3, r4, r5, r6, r7, r8);

	    decide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	stopSound(Au2);
            	Send_Selection();
            	Receive_Signal_ToGame();
            	rivalname = ore.Receive_Rivalname(dis, me, myturn, my_color, info_of_me, info_of_rival, id, score, rivalscore);
            	if(ore.Get_Genre() == 1){
            		playSound(Au4);

            		remove(rule);add(game);flag=false;Countdown();
                    play_f = true;
            		if(my_color==BLACK){
            			//time_limit=ore.Get_Select_Time();
                    	//rivalname=ore.Get_RivalID();
            			info_of_me.setText("<html>"+"<p>"+"     "+id+"<br>"+"     :"+me.getScore(myturn)+"<p>"+"<html>");
            			info_of_rival.setText("<html>"+"<p>"+"     "+rivalname+"<br>"+"     :"+me.getScore(!myturn)+"<p>"+"<html>");
            		}else{
            			//time_limit=ore.Get_Select_Time();
                    	//rivalname=ore.Get_RivalID();
            			info_of_me.setText("<html>"+"<p>"+"     "+id+"<br>"+"     :"+me.getScore(myturn)+"<p>"+"<html>");
            			info_of_rival.setText("<html>"+"<p>"+"     "+rivalname+"<br>"+"     :"+me.getScore(!myturn)+"<p>"+"<html>");
            		}

            	    //time_limit=ore.Get_Select_Time();
                	//rivalname=ore.Get_RivalID();
            	}
            	else if(ore.Get_Genre() == 2){remove(rule);add(bombset);playSound(Au3);}
                repaint();revalidate();
        	    Change_Board(board);
            }
        });

//Card Matching

	    match=new JLabel(new ImageIcon("match.png"));
	    match.setLayout(null);
	    JLabel loading = new JLabel(new ImageIcon("loading.gif"));
	    loading.setBounds(470,150,300,300);

//Card Bomb Setting

		//Bomb banmen
	    bomb_panel = new JPanel();
	    bomb_panel.setLayout(new GridLayout(8,8,0,0));
		bomb_panel.setBounds(437,138,420,420);

		for ( int i=0;i<NUM;i++ ) {
			for ( int j=0;j<NUM;j++ ) {
				BombSet[i][j] = 0;
				bbutton[i][j] = new JButton(icon);
				bbutton[i][j].setSize(30,30);
				bbutton[i][j].setMargin(new Insets(0, 0, 0, 0));
				bbutton[i][j].setActionCommand(String.valueOf(100+i*100+j*10));
				bbutton[i][j].addActionListener(this);
				bbutton[i][j].setEnabled(true);
				bomb_panel.add(bbutton[i][j]);
			}
		}
		BombSet[3][3] = BLACK;
		BombSet[3][4] = WHITE;
		BombSet[4][3] = WHITE;
		BombSet[4][4] = BLACK;
		Change_Bomb_Board(BombSet);

		bombcounter = new JLabel();bombcounter.setBounds(335, 180, 70, 70);bombcounter.setFont(new Font("Serif", Font.BOLD, 80));
		bombcounter.setText(String.valueOf(bombcount ));
	    bombset=new JLabel(new ImageIcon("bomb_setting.png"));
	    bombset.setLayout(null);
	    decidebomb= new JButton("");decidebomb.setEnabled(false);
	    bombset.add(decidebomb);
	    bombset.add(bomb_panel);
	    bombset.add(bombcounter);
	    decidebomb.setBounds(950, 550, 134, 80);decidebomb.setIcon(new ImageIcon("decide.png"));
	    decidebomb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Send_Bomb(row1,col1,row2,col2);
            	stopSound(Au3);
            	playSound(Au4);
                remove(bombset);
                add(game);

                score = 200;
                rivalscore = 200;

                if(my_color==BLACK){
        			info_of_me.setText("<html>"+"<p>"+"     "+id+"<br>"+"     :"+me.getScore(myturn)+"  Score: "+score+"<p>"+"<html>");
        			info_of_rival.setText("<html>"+"<p>"+"     "+rivalname+"<br>"+"     :"+me.getScore(!myturn)+"  Score: "+rivalscore+"<p>"+"<html>");
        		}else{
        			info_of_me.setText("<html>"+"<p>"+"     "+id+"<br>"+"     :"+me.getScore(myturn)+"  Score: "+score+"<p>"+"<html>");
        			info_of_rival.setText("<html>"+"<p>"+"     "+rivalname+"<br>"+"     :"+me.getScore(!myturn)+"  Score: "+rivalscore+"<p>"+"<html>");
        		}
                Countdown();
                repaint();revalidate();
                flag=false;
                play_f = true;

            }
        });

//Card Game

	    Border empty = BorderFactory.createEmptyBorder();
	    chat = new JTextArea();chat.setLineWrap(true);chat.setWrapStyleWord(true);
	    teach_rule = new JTextArea("ルール説明\n 普通のオセロに加えてスコアで勝敗を決めるオセロです。\n ～スコア詳細～\n ・獲得駒数　1個につき100点\n ・タイムボーナス(1手につき)\n　　 0～4秒　20点\n 　　5～9秒　16点\n 　　10～14秒　12点\n 　　15～19秒　8点\n 　　20～24秒　4点\n 　　25～29秒　2点\n 　　30秒～　0点\n ・コンボボーナス\n 　　0～2コンボ　0点\n 　　3コンボ　10点\n 　　4コンボ　20点\n 　　5コンボ　30点\n 　　6コンボ以上　100点\n ・ボム\n 　　1個踏むと　-500点");teach_rule.setLineWrap(true);teach_rule.setWrapStyleWord(true);teach_rule.setEditable(false);
	    messenger_box = new JTextArea();messenger_box.setBackground(new Color(136,159,174));messenger_box.setEnabled(false);messenger_box.setLineWrap(true);messenger_box.setWrapStyleWord(true);
	    scroll = new JScrollPane(messenger_box);scroll.setBackground(new Color(136,159,174));scroll.setBorder(empty);
	    send = new JButton("");
	    menu = new JButton("");
	    time = new JLabel("");
	    again = new JButton("");again.setBackground(new Color(140,161,209));again.setMargin(new Insets(0, 0, 0, 0));again.setBorder(noBorder);
	    backw = new JButton("");
	    onemorew = new JButton("");
	    backl = new JButton("");
	    onemorel = new JButton("");
	    backd = new JButton("");
	    onemored = new JButton("");
	    backhome = new JButton("");backhome.setBackground(new Color(140,161,209));backhome.setBorder(noBorder);
	    info_of_me=new JLabel("");
	    info_of_rival=new JLabel("");


	    //Banmen
	    s_panel = new JPanel();
		s_panel.setLayout(new GridLayout(8,8,0,0));

		for ( int i=0;i<NUM;i++ ) {
			for ( int j=0;j<NUM;j++ ) {
				button[i][j] = new JButton(icon);
				button[i][j].setSize(30,30);
				button[i][j].setMargin(new Insets(0, 0, 0, 0));
				button[i][j].setActionCommand(String.valueOf(i*10+j));
				button[i][j].addActionListener(this);
				button[i][j].setEnabled(true);
				s_panel.add(button[i][j]);
			}
		}

		com3=new JLabel(new ImageIcon("combo3.gif"));com3.setBounds(350,125,550,450);
		com4=new JLabel(new ImageIcon("combo4.gif"));com4.setBounds(300,125,700,400);
		com5=new JLabel(new ImageIcon("combo5.gif"));com5.setBounds(100,50,1000,600);
		com6=new JLabel(new ImageIcon("combo6.gif"));com6.setBounds(300,100,663,512);
		bakuhatsu=new JLabel(new ImageIcon("bomb.gif"));
		menushow=new JLabel(new ImageIcon("Menu.png"));
		passshow=new JLabel(new ImageIcon("Pass.png"));
		winshow=new JLabel(new ImageIcon("Win.png"));
		loseshow=new JLabel(new ImageIcon("Lose.png"));
		drawshow=new JLabel(new ImageIcon("Draw.png"));
	    game= new JLayeredPane();
	    bg = new JLabel(new ImageIcon("game.png"));
	    game.setLayout(null);
	    game.add(bg,new Integer(0));
	    game.add(menu,new Integer(1));
	    game.add(chat,new Integer(2));
	    game.add(send,new Integer(3));
	    game.add(scroll,new Integer(4));
		game.add(s_panel,new Integer(5));
		game.add(time,new Integer(6));
		game.add(info_of_me,new Integer(7));
		game.add(info_of_rival,new Integer(8));
		game.add(teach_rule,new Integer(9));
		menushow.add(again);
		menushow.add(backhome);
		winshow.add(backw);
		winshow.add(onemorew);
		loseshow.add(backl);
		loseshow.add(onemorel);
		drawshow.add(backd);
		drawshow.add(onemored);
		bg.setBounds(0,0, 1280, 720);
		time.setBounds(750,580, 150, 100);
		scroll.setBounds(180, 142, 172, 250);
	    chat.setBounds(171, 455, 132, 106);chat.setBackground(new Color(175,191,210));
	    teach_rule.setBounds(932, 190, 170, 338);teach_rule.setBackground(new Color(136,156,176));
	    send.setBounds(303, 515 , 56 , 39);send.setMargin(new Insets(5,5,5,5));send.setIcon(new ImageIcon("send_button.png"));
	    menu.setBounds(920, 580, 190, 71);menu.setIcon(new ImageIcon("menubutton.png"));
	    again.setBounds(373, 60, 215, 64);again.setIcon(new ImageIcon("again.png"));

	    backw.setBounds(220, 130, 215, 64);backw.setIcon(new ImageIcon("Back_Home.png"));backw.setBackground(new Color(140,161,209));backw.setBorder(noBorder);
	    onemorew.setBounds(536, 130, 215, 64);onemorew.setIcon(new ImageIcon("Play_Again.png"));onemorew.setBackground(new Color(140,161,209));onemorew.setBorder(noBorder);
	    backl.setBounds(220, 130, 215, 64);backl.setIcon(new ImageIcon("Back_Home.png"));backl.setBackground(new Color(140,161,209));backl.setBorder(noBorder);
	    onemorel.setBounds(536, 130, 215, 64);onemorel.setIcon(new ImageIcon("Play_Again.png"));onemorel.setBackground(new Color(140,161,209));onemorel.setBorder(noBorder);
	    backd.setBounds(220, 130, 215, 64);backd.setIcon(new ImageIcon("Back_Home.png"));backd.setBackground(new Color(140,161,209));backd.setBorder(noBorder);
	    onemored.setBounds(536, 130, 215, 64);onemored.setIcon(new ImageIcon("Play_Again.png"));onemored.setBackground(new Color(140,161,209));onemored.setBorder(noBorder);
	    backhome.setBounds(373, 145, 215, 64);backhome.setIcon(new ImageIcon("backhome.png"));
	    bakuhatsu.setBounds(0,0,1280,720);
	    s_panel.setBounds(437,138,420,420);
	    info_of_me.setBounds(248,561,250,100);
		info_of_me.setFont(new Font("Serif", Font.BOLD, 20));info_of_me.setForeground(Color.WHITE);
		info_of_rival.setBounds(847,15,250,100);
		info_of_rival.setFont(new Font("Serif", Font.BOLD, 20));info_of_rival.setForeground(Color.WHITE);
		menushow.setBounds(146,214, 1000, 271);
		passshow.setBounds(146,214, 1000, 271);
		winshow.setBounds(146,214, 1000, 271);
		loseshow.setBounds(146,214, 1000, 271);
		drawshow.setBounds(146,214, 1000, 271);

		time.setFont(new Font("Serif", Font.BOLD, 30));time.setForeground(Color.WHITE);
		send.addActionListener(new ActionListener() {
			String s;
            public void actionPerformed(ActionEvent e) {
            	s = chat.getText();
            	messenger_box.insert(id+": "+s+"\n",messenger_box.getCaretPosition());
                Send_Chat(s);
            	chat.setText("");
            }
        });

		backhome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	game.remove(menushow);
            	repaint();
            }
        });

		backw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	try {
					s.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

            	flag = true;
            	play_f = false;
            	p_flag = false;
            	game.remove(winshow);
            	ore.timeselect=0;ore.genreselect=0;ore.time_flag=0;ore.gamemode_flag=0;ore.stage_flag=0;ore.time_limit=0;count = 0;
            	r1.setEnabled(true);
            	r2.setEnabled(true);
            	r3.setEnabled(true);
            	r4.setEnabled(true);
            	r5.setEnabled(true);
            	r6.setEnabled(true);
            	r7.setEnabled(true);
            	r8.setEnabled(true);
            	decide.setEnabled(false);

            	for ( int i=0;i<NUM;i++ ) {
        			for ( int j=0;j<NUM;j++ ) {
        				BombSet[i][j] = 0;
        			}
        		}
        		BombSet[3][3] = BLACK;
        		BombSet[3][4] = WHITE;
        		BombSet[4][3] = WHITE;
        		BombSet[4][4] = BLACK;
        		Change_Bomb_Board(BombSet);
        		bombcount=2;
        		decidebomb.setEnabled(false);
        		bombcounter.setText(String.valueOf(bombcount));

            	remove(game);
            	add(home);
            	stopSound(Au4);
            	playSound(Au1);
                repaint();revalidate();
            }
        });

		onemorew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
            		s.close();
					s = new Socket(server,port);
					is = s.getInputStream();
					dis = new DataInputStream(is);
					os = s.getOutputStream();
					dos = new DataOutputStream(os);
				} catch (IOException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}

            	Send_ID();

            	for ( int i=0;i<NUM;i++ ) {
        			for ( int j=0;j<NUM;j++ ) {
        				board[i][j] = 0;
        			}
        		}
        		board[3][3] = BLACK;
        		board[3][4] = WHITE;
        		board[4][3] = WHITE;
        		board[4][4] = BLACK;

            	flag = true;
            	play_f = false;
            	p_flag = false;
            	game.remove(winshow);
            	ore.timeselect=0;ore.genreselect=0;ore.time_flag=0;ore.gamemode_flag=0;ore.stage_flag=0;ore.time_limit=0;count = 0;
            	r1.setEnabled(true);
            	r2.setEnabled(true);
            	r3.setEnabled(true);
            	r4.setEnabled(true);
            	r5.setEnabled(true);
            	r6.setEnabled(true);
            	r7.setEnabled(true);
            	r8.setEnabled(true);
            	decide.setEnabled(false);

            	for ( int i=0;i<NUM;i++ ) {
        			for ( int j=0;j<NUM;j++ ) {
        				BombSet[i][j] = 0;
        			}
        		}
        		BombSet[3][3] = BLACK;
        		BombSet[3][4] = WHITE;
        		BombSet[4][3] = WHITE;
        		BombSet[4][4] = BLACK;
        		Change_Bomb_Board(BombSet);
        		bombcount=2;
        		decidebomb.setEnabled(false);
        		bombcounter.setText(String.valueOf(bombcount));

            	remove(game);
            	add(rule);
            	stopSound(Au4);
            	playSound(Au2);
                repaint();revalidate();
            }
        });

		backl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	try {
					s.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

            	flag = true;
            	play_f = false;
            	p_flag = false;
            	game.remove(loseshow);
            	ore.timeselect=0;ore.genreselect=0;ore.time_flag=0;ore.gamemode_flag=0;ore.stage_flag=0;ore.time_limit=0;count = 0;
            	r1.setEnabled(true);
            	r2.setEnabled(true);
            	r3.setEnabled(true);
            	r4.setEnabled(true);
            	r5.setEnabled(true);
            	r6.setEnabled(true);
            	r7.setEnabled(true);
            	r8.setEnabled(true);
            	decide.setEnabled(false);

            	for ( int i=0;i<NUM;i++ ) {
        			for ( int j=0;j<NUM;j++ ) {
        				BombSet[i][j] = 0;
        			}
        		}
        		BombSet[3][3] = BLACK;
        		BombSet[3][4] = WHITE;
        		BombSet[4][3] = WHITE;
        		BombSet[4][4] = BLACK;
        		Change_Bomb_Board(BombSet);
        		bombcount=2;
        		decidebomb.setEnabled(false);
        		bombcounter.setText(String.valueOf(bombcount));

            	remove(game);
            	add(home);
            	stopSound(Au4);
            	playSound(Au1);
                repaint();revalidate();
            }
        });

		onemorel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	try {
            		s.close();
					s = new Socket(server,port);
					is = s.getInputStream();
					dis = new DataInputStream(is);
					os = s.getOutputStream();
					dos = new DataOutputStream(os);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

            	Send_ID();

            	flag = true;
            	play_f = false;
            	p_flag = false;
            	game.remove(loseshow);
            	ore.timeselect=0;ore.genreselect=0;ore.time_flag=0;ore.gamemode_flag=0;ore.stage_flag=0;ore.time_limit=0;count = 0;
            	r1.setEnabled(true);
            	r2.setEnabled(true);
            	r3.setEnabled(true);
            	r4.setEnabled(true);
            	r5.setEnabled(true);
            	r6.setEnabled(true);
            	r7.setEnabled(true);
            	r8.setEnabled(true);
            	decide.setEnabled(false);

            	for ( int i=0;i<NUM;i++ ) {
        			for ( int j=0;j<NUM;j++ ) {
        				BombSet[i][j] = 0;
        			}
        		}
        		BombSet[3][3] = BLACK;
        		BombSet[3][4] = WHITE;
        		BombSet[4][3] = WHITE;
        		BombSet[4][4] = BLACK;
        		Change_Bomb_Board(BombSet);
        		bombcount=2;
        		decidebomb.setEnabled(false);
        		bombcounter.setText(String.valueOf(bombcount));

            	remove(game);
            	add(rule);
            	stopSound(Au4);
            	playSound(Au2);
                repaint();revalidate();
            }
        });

		backd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					s.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

            	flag = true;
            	play_f = false;
            	p_flag = false;
            	game.remove(drawshow);
            	ore.timeselect=0;ore.genreselect=0;ore.time_flag=0;ore.gamemode_flag=0;ore.stage_flag=0;ore.time_limit=0;count = 0;
            	r1.setEnabled(true);
            	r2.setEnabled(true);
            	r3.setEnabled(true);
            	r4.setEnabled(true);
            	r5.setEnabled(true);
            	r6.setEnabled(true);
            	r7.setEnabled(true);
            	r8.setEnabled(true);
            	decide.setEnabled(false);

            	for ( int i=0;i<NUM;i++ ) {
        			for ( int j=0;j<NUM;j++ ) {
        				BombSet[i][j] = 0;
        			}
        		}
        		BombSet[3][3] = BLACK;
        		BombSet[3][4] = WHITE;
        		BombSet[4][3] = WHITE;
        		BombSet[4][4] = BLACK;
        		Change_Bomb_Board(BombSet);
        		bombcount=2;
        		decidebomb.setEnabled(false);
        		bombcounter.setText(String.valueOf(bombcount));

            	remove(game);
            	add(home);
            	stopSound(Au4);
            	playSound(Au1);
                repaint();revalidate();
            }
        });

		onemored.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
            		s.close();
					s = new Socket(server,port);
					is = s.getInputStream();
					dis = new DataInputStream(is);
					os = s.getOutputStream();
					dos = new DataOutputStream(os);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

            	Send_ID();

            	flag = true;
            	play_f = false;
            	p_flag = false;
            	game.remove(winshow);
            	ore.timeselect=0;ore.genreselect=0;ore.time_flag=0;ore.gamemode_flag=0;ore.stage_flag=0;ore.time_limit=0;count = 0;
            	r1.setEnabled(true);
            	r2.setEnabled(true);
            	r3.setEnabled(true);
            	r4.setEnabled(true);
            	r5.setEnabled(true);
            	r6.setEnabled(true);
            	r7.setEnabled(true);
            	r8.setEnabled(true);
            	decide.setEnabled(false);

            	for ( int i=0;i<NUM;i++ ) {
        			for ( int j=0;j<NUM;j++ ) {
        				BombSet[i][j] = 0;
        			}
        		}
        		BombSet[3][3] = BLACK;
        		BombSet[3][4] = WHITE;
        		BombSet[4][3] = WHITE;
        		BombSet[4][4] = BLACK;
        		Change_Bomb_Board(BombSet);
        		bombcount=2;
        		decidebomb.setEnabled(false);
        		bombcounter.setText(String.valueOf(bombcount));

            	remove(game);
            	add(rule);
            	stopSound(Au4);
            	playSound(Au2);
                repaint();revalidate();
            }
        });

		again.addActionListener(new ActionListener() { // 降参
            public void actionPerformed(ActionEvent e) {

            	System.out.println("降参");
            	try {
					dos.writeInt(-535353);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            	game.add(loseshow,new Integer(10));
            	game.remove(menushow);

            }
        });

	    menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.add(menushow,new Integer(10));
                repaint();revalidate();
            }
        });
	}

	public static void Send_Score(){
//		Send messenger to chat box
		try {

			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			dos.writeInt(score);
			dos.flush();
			//dos.close();
			} catch (Exception e) {
			e.printStackTrace();}
	}

	public void Send_ID(){
//		Send messenger to chat box
		try {
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			dos.writeUTF(id);
			dos.flush();
		} catch (Exception e) {
			e.printStackTrace();}
	}

	public void Send_Selection(){
		//Send rule selection to server and wait
		try {
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			dos.writeInt(ore.Get_Select_Time());
			dos.writeInt(ore.Get_Genre());
			dos.flush();
		} catch (Exception e) {e.printStackTrace();}
	}

	public static void Receive_Signal_ToGame(){
		//Receive signal of playing and go to play
		try {
			myturn = dis.readBoolean();
			System.out.println("turn:" + myturn);
			me = new Othello(board,myturn);
			if(myturn){
				my_color=BLACK;
				aite_color=WHITE;
			}else{
				my_color=WHITE;
				aite_color=BLACK;
			}
		} catch (Exception e) {e.printStackTrace();}
	}

	public static void Renew_Score(){
		if(ore.genreselect==2){
			if(my_color==BLACK){
				info_of_me.setText("<html>"+"<p>"+"     "+id+"<br>"+"     :"+me.getScore(myturn)+"  Score: "+score+"<p>"+"<html>");
				info_of_rival.setText("<html>"+"<p>"+"     "+rivalname+"<br>"+"     :"+me.getScore(!myturn)+"  Score: "+rivalscore+"<p>"+"<html>");
			}else{
				info_of_me.setText("<html>"+"<p>"+"     "+id+"<br>"+"     :"+me.getScore(myturn)+"  Score: "+score+"<p>"+"<html>");
				info_of_rival.setText("<html>"+"<p>"+"     "+rivalname+"<br>"+"     :"+me.getScore(!myturn)+"  Score: "+rivalscore+"<p>"+"<html>");
			}
		}else{
			if(my_color==BLACK){
				info_of_me.setText("<html>"+"<p>"+"     "+id+"<br>"+"     :"+me.getScore(myturn)+"<p>"+"<html>");
				info_of_rival.setText("<html>"+"<p>"+"     "+rivalname+"<br>"+"     :"+me.getScore(!myturn)+"<p>"+"<html>");
			}else{
				info_of_me.setText("<html>"+"<p>"+"     "+id+"<br>"+"     :"+me.getScore(myturn)+"<p>"+"<html>");
				info_of_rival.setText("<html>"+"<p>"+"     "+rivalname+"<br>"+"     :"+me.getScore(!myturn)+"<p>"+"<html>");
			}
		}
	}

	public static void Receive_Rivalname(){
		//Receive signal of playing and go to play��U����U�����f
		try {
			rivalname = dis.readUTF();
		    System.out.println("�ΐ�Ҏ����@�F" + rivalname);
		    black_t.setText("a");

		    if(ore.genreselect==2){
			    if(my_color==BLACK){
					info_of_me.setText("<html>"+"<p>"+"     "+id+"<br>"+"     :"+me.getScore(myturn)+"  Score: "+score+"<p>"+"<html>");
					info_of_rival.setText("<html>"+"<p>"+"     "+rivalname+"<br>"+"     :"+me.getScore(!myturn)+"  Score: "+rivalscore+"<p>"+"<html>");
				}else{
					info_of_me.setText("<html>"+"<p>"+"     "+id+"<br>"+"     :"+me.getScore(myturn)+"  Score: "+score+"<p>"+"<html>");
					info_of_rival.setText("<html>"+"<p>"+"     "+rivalname+"<br>"+"     :"+me.getScore(!myturn)+"  Score: "+rivalscore+"<p>"+"<html>");
				}
			}else{
				if(my_color==BLACK){
					info_of_me.setText("<html>"+"<p>"+"     "+id+"<br>"+"     :"+me.getScore(myturn)+"<p>"+"<html>");
					info_of_rival.setText("<html>"+"<p>"+"     "+rivalname+"<br>"+"     :"+me.getScore(!myturn)+"<p>"+"<html>");
				}else{
					info_of_me.setText("<html>"+"<p>"+"     "+id+"<br>"+"     :"+me.getScore(myturn)+"<p>"+"<html>");
					info_of_rival.setText("<html>"+"<p>"+"     "+rivalname+"<br>"+"     :"+me.getScore(!myturn)+"<p>"+"<html>");
				}
			}
			//dis.close();
		} catch (Exception e) {e.printStackTrace();}
	}

	public void Send_Bomb(int row1,int col1,int row2,int col2){
		//Send othello's bomb information
		try {
			dos.writeInt(row1);
			dos.writeInt(col1);
			dos.writeInt(row2);
			dos.writeInt(col2);
			dos.flush();
			System.out.println("������"+row1+" "+col1+" "+row2 +" "+col2);
			Receive_Bomb();
		} catch (Exception e) {e.printStackTrace();}
	}

	public void Receive_Bomb(){
		//Receive othello's bomb information
		try {
			rrow1=dis.readInt();
			rcol1=dis.readInt();
			rrow2=dis.readInt();
			rcol2=dis.readInt();
			System.out.println("�������"+rrow1+" "+rcol1+" "+rrow2+" "+rcol2);
		} catch (Exception e) {e.printStackTrace();}
	}

	public static void Send_Play(int x, int y){
		//Send othello's play information
		try {
			//Socket s =new Socket(server,port);
			dos = new DataOutputStream(s.getOutputStream());
		    dos.writeInt(x);
		    dos.writeInt(y);
		    dos.writeBoolean(me.judgeEnd(board));
		} catch (Exception e) {e.printStackTrace();}
	}

	public void Send_Chat(String str){
		//Send messenger to chat box
		try {
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			dos.writeInt(114514);
			dos.writeUTF("chat:"+id+": "+str);
			dos.flush();
		} catch (Exception e) {e.printStackTrace();}
	}

	public void Receive_Chat(){
		//Receive rivel's messenger and add to chat box
		try {
			System.out.println("受け取ったで");
			messenger_box.insert(dis.readUTF()+"\n",messenger_box.getCaretPosition());
		} catch (Exception e) {e.printStackTrace();}
	}

	public void actionPerformed( ActionEvent ae ) {
		put = new int[2];
		String cmd = ae.getActionCommand();
		int idm = Integer.parseInt(cmd);
		put[0] = idm / 10;
		put[1] = idm % 10;

		if((idm<100)&&(click)&&(board[put[0]][put[1]]==3)){
			int kk=0;
			turn1=false;turn21=false;
			timeleft = time_limit-count ;

			for(int i=0;i<NUM;i++){
				for(int j=0;j<NUM;j++){
					if(board[i][j]==aite_color)kk++;
				}
			}
			board = me.reflectAction(put,board,my_color);
			for(int i=0;i<NUM;i++){
				for(int j=0;j<NUM;j++){
					if(board[i][j]==aite_color)kk--;
				}
			}

			rivalscore=rivalscore-100*kk;
			c.Change_Board(board);
			if(ore.Get_Genre()==2){Put_In_Bomb(put,rrow1,rcol1,rrow2,rcol2);}
			Score_Calculate(turn1,turn21);
			turn21=true;
			Send_Score();
			Send_Play(put[0],put[1]);
			p_flag = false;
			if(me.judgeEnd(board)==true){Win_Lose();}
			if(ore.Get_Select_Time()==1){
				time_limit=30;
				count=0;
			}else if(ore.Get_Select_Time()==2){
				time_limit=60;
				count=0;
			}else if(ore.Get_Select_Time()==3){
				time_limit=120;
				count=0;
			}
			click=false;

		}else if(idm>=100&&idm!=340&&idm!=330&&idm!=440&&idm!=430){
			if(bombcount == 0){
				bombrow = (idm-100)/100;
				bombcol = ((idm-100)%100)/10;
				if(BombSet[bombrow][bombcol] == 3){
					decidebomb.setEnabled(false);
					Change_Bomb_Board(me.setBomb(BombSet,bombrow, bombcol));
					bombcount--;
				}
			}else if(bombcount > 0){
				bombcount--;
				if(bombcount==1){
					bombrow = (idm-100)/100;
					bombcol = ((idm-100)%100)/10;
					Change_Bomb_Board(me.setBomb(BombSet,bombrow, bombcol));
				}else if(bombcount==0){
					bombrow = (idm-100)/100;
					bombcol = ((idm-100)%100)/10;
					Change_Bomb_Board(me.setBomb(BombSet,bombrow, bombcol));
				}
			}bombcounter.setText(String.valueOf(bombcount));

			if(bombcount == 0)
			decidebomb.setEnabled(true);
			}

		for ( int i=0;i<NUM;i++ ) {
			for ( int j=0;j<NUM;j++ ) {
				if((BombSet[i][j]==3)&&(h==0)){row1=i;col1=j;h++;}
				else if((BombSet[i][j]==3)&&(h==1)){row2=i;col2=j;}
			}
		}
	}

	public void Change_Bomb_Board(int[][] board){
		//Update bomb board info
		for ( int i=0;i<NUM;i++ ) {
			for ( int j=0;j<NUM;j++ ) {
				if (board[i][j]==3) {
					bbutton[i][j].setIcon(new ImageIcon("bomb.png"));
				}else if(board[i][j]==WHITE){
					bbutton[i][j].setIcon(white);
				}else if(board[i][j]==BLACK){
					bbutton[i][j].setIcon(black);
				}else if(board[i][j]==0){
					bbutton[i][j].setIcon(icon);
				}
			}
		}
		repaint();
	}

	public void Change_Board(int[][] board){
		//Update othello board info
		for ( int i=0;i<NUM;i++ ) {
			for ( int j=0;j<NUM;j++ ) {
				if (board[i][j]==1) {
					button[i][j].setIcon(black);
				}else if(board[i][j]==2){
					button[i][j].setIcon(white);
				}else if(board[i][j]==3){
					button[i][j].setIcon(komaok);
				}else if((board[i][j]>=10&&board[i][j]<=19)){
					board[i][j] = 1;
					button[i][j].setIcon(black);
				}else if((board[i][j]>=20&&board[i][j]<=29)){
					board[i][j] = 2;
					button[i][j].setIcon(white);
				}else if(board[i][j]==0){
					button[i][j].setIcon(icon);
				}
			}
		}
		repaint();
	}

	public void Countdown(){
		//Send rematch request
		if(ore.Get_Select_Time()==1){
			time_limit=30;
		}else if(ore.Get_Select_Time()==2){
			time_limit=60;
		}else if(ore.Get_Select_Time()==3){
			time_limit=120;
		}else if(ore.Get_Select_Time()==4){
			time_limit=300;
		}else if(ore.Get_Select_Time()==5){
			time_limit=600;
		}else if(ore.Get_Select_Time()==6){
			time_limit=900;
		}

		new Timer(1000, new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 if(play_f == false){
	        		 ((Timer) e.getSource()).stop();
	        	 }else if (count < time_limit) {
	            	ran_able = true;
	            	bakuhatsucount++;combo3++;combo4++;combo5++;combo6++;passcount++;
	            	if(passcount==1){game.remove(passshow);repaint();}
	            	if(bakuhatsucount==5){game.remove(bakuhatsu);repaint();}
	            	if(combo3==3){game.remove(com3);repaint();}
	            	if(combo4==3){game.remove(com4);repaint();}
	            	if(combo5==3){game.remove(com5);repaint();}
	            	if(combo6==3){game.remove(com6);repaint();}
	            	count++;
	            	minutes=(time_limit-count)/60;
	            	seconds=(time_limit-count)%60;
	            	String text = minutes+" : "+seconds+" left";
	            	time.setText(text);
	            } else {
	               ((Timer) e.getSource()).stop();
	            }
	         }
	     }).start();
	 }

	public void Random_Putting(){
		//Send rematch request
	}

	public void Score_Calculate(boolean turn11,boolean turn21){
		//Calculate Score
		score = 0;
		e_time = count;
		comb_v = me.combo;
		if(0 <= e_time && e_time < 5 && !turn11){
			time_score += 20;
		}else if(5 <= e_time && e_time < 10&& !turn11){
			time_score += 16;
		}else if(10 <= e_time && e_time < 15&& !turn11){
			time_score += 12;
		}else if(15 <= e_time && e_time < 20&& !turn11){
			time_score += 8;
		}else if(20 <= e_time && e_time < 25&& !turn11){
			time_score += 4;
		}else if(25 <= e_time && e_time < 30&& !turn11){
			time_score += 2;
		}else{
			time_score += 0;
		}

		if(0 <= comb_v && comb_v <= 2 && !turn21){
			combo_score += 0;
		}else if(comb_v == 3 && !turn21){
			combo_score += 10;combo3=0;game.add(com3, new Integer(10));
		}else if(comb_v == 4 && !turn21){
			combo_score += 20;combo4=0;game.add(com4, new Integer(10));
		}else if(comb_v == 5 && !turn21){
			combo_score += 30;combo5=0;game.add(com5, new Integer(10));
		}else if(comb_v >= 6 && !turn21){
			combo_score += 100;combo6=0;game.add(com6, new Integer(10));
		}

		if((ore.Get_Genre()==1)&&(ore.Get_Select_Time()<=3)){score = me.getScore(myturn)*100;}
		else if((ore.Get_Genre()==1)&&(ore.Get_Select_Time()>3)){score = me.getScore(myturn)*100;}
		else if((ore.Get_Genre()==2)&&(ore.Get_Select_Time()<=3)){score = me.getScore(myturn)*100 - bomb*500 + time_score + combo_score;}
		else if((ore.Get_Genre()==2)&&(ore.Get_Select_Time()>3)){score = me.getScore(myturn)*100 - bomb*500 + combo_score;}
		scoretext = String.valueOf(score);
		Renew_Score();
	}

	public static void Win_Lose(){
		//Show win lose result
		if(score<rivalscore)game.add(loseshow,new Integer(10));
		else if(score==rivalscore)game.add(drawshow,new Integer(10));
		else if(score>rivalscore)game.add(winshow,new Integer(10));
	}

	public void Send_Pass(){

	}

	public static void playSound(AudioClip name) {
		//Play music
	    name.loop();
	}

	public static void stopSound(AudioClip name) {
		//Stop music
		name.stop();
	}

	public void Show(int [][] board){
	//Show board info
		System.out.println("=====================================");
		for ( int i=0;i<NUM;i++ ) {
			System.out.print(i + "  ");
			for ( int j=0;j<NUM;j++ ) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void Put_In_Bomb(int [] put,int rrow1,int rcol1,int rrow2,int rcol2){
		//Check if you put the chess in the bomb of rival
		if(((put[0]==rrow1)&&(put[1]==rcol1))||((put[0]==rrow2)&&(put[1]==rcol2))){
			bomb++;
			game.add(bakuhatsu,new Integer(10));
         	bakuhatsucount=0;
		}
	}

	// 置けるマスを探索して、その中から任意のマスを選ぶメソッド、戻り値：左上から何番目の置ける場所か
	void selectRandom() {
		int num_able = 0;

		// 置ける場所の探索
		for (int i=0;i<NUM;i++) {
			for (int j=0;j<NUM;j++) {
				if (board[i][j] == ABLE) {
					a_row[num_able] = i;
					a_column[num_able] = j;
					num_able++;
				}
			}
		}
		// 乱数を生成して、その値に応じた置けるマスを選ぶ
		Random rng = new Random();
		ran_put = rng.nextInt(num_able);

	}

	public static void main(String[] args) {
		int[] put = new int[2];
		c = new client2("othello", args[0]);

		while(true){//v37で追加ここ以下を無限ループすることで何度も戦いなおせるように。
			while (flag) {
				System.out.print("");
			}
			if(play_f){
				if (!myturn) {
					try {
						do{
							rivalscore = dis.readInt();
							if(rivalscore == 777777){
								game.add(winshow,new Integer(10));
							}else if(rivalscore==114514){
								c.Receive_Chat();
							}else{
								put[0] = dis.readInt();
								put[1] = dis.readInt();
								turn1=true;click=true;
								board = me.reflectAction(put,board,aite_color);
								c.Change_Board(board);
								c.Score_Calculate(turn1,turn21);
								turn1=false;
								if(ore.Get_Select_Time()==1){
									time_limit=30;
									count=0;
								}else if(ore.Get_Select_Time()==2){
									time_limit=60;
									count=0;
								}else if(ore.Get_Select_Time()==3){
									time_limit=120;
									count=0;
								}
							}
						}while(rivalscore == 114514);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{click=true;}
			}

			while (play_f) {//v37で追加これで無限ループ待機を抜ける

				// お互いに置くマスがなくなったら、ループから抜ける

				if(me.judgeEnd(board)){

				}else{

					me.isAbleToBePut(board);
					c.Show(board);
					c.Change_Board(board);

					if (me.judgePass(board)) { // パスの動作
					put[0]=9;put[1]=9;
					Send_Score();
					Send_Play(put[0],put[1]);
					click=false;
					if(ore.Get_Select_Time()==1){
						time_limit=30;
						count=0;
					}else if(ore.Get_Select_Time()==2){
						time_limit=60;
						count=0;
					}else if(ore.Get_Select_Time()==3){
						time_limit=120;
						count=0;
					}
					p_flag=false;
					game.add(passshow,new Integer(10));
					passcount=0;
				}

				while (p_flag) { // 自手中
					System.out.print("");
					// 時間切れ
					if (count >= time_limit) {
						if (ore.Get_Select_Time() >=1 && ore.Get_Select_Time() <= 3) { // ターンごとの制限時間
							if (ran_able) {
								c.selectRandom();
								System.out.println("乱数の生成 ： " + ran_put + "個目のおけるマスに駒を置く");
								put[0] = a_row[ran_put];
								put[1] = a_column[ran_put];
								board = me.reflectAction(put,board,my_color);
								c.Show(board);
								c.Change_Board(board);
								turn1=true;
								c.Score_Calculate(turn1,turn21);
								turn1=false;
								c.Send_Score();
								c.Send_Play(put[0],put[1]);
								ran_able = false;
								p_flag = false;

								if(ore.Get_Select_Time()==1){
									time_limit=30;
									count=0;
								}else if(ore.Get_Select_Time()==2){
									time_limit=60;
									count=0;
								}else if(ore.Get_Select_Time()==3){
									time_limit=120;
									count=0;
								}
							}
						} else { // ゲーム全体の制限時間
							game.add(loseshow,new Integer(10));

							try {
								dos.writeInt(-666666);
								System.out.println("時間切れ");
							} catch (IOException e) {
								e.printStackTrace();
							}
							p_flag = false;
							end = true;
						}
					}
				}
				p_flag = true;

				//相手のターンの行動を取得
				if(play_f){
					if(rivalscore != 777777){
						try {
							do{
								rivalscore = dis.readInt();
								if(rivalscore == 777777){
									game.add(winshow,new Integer(10));
								}else if(rivalscore==114514){
									c.Receive_Chat();

								}else{
									put[0] = dis.readInt();
									put[1] = dis.readInt();

									if(put[0]==9&&put[1]==9){
										if(ore.Get_Select_Time()==1){
											time_limit=30;
											count=0;
										}else if(ore.Get_Select_Time()==2){
											time_limit=60;
											count=0;
										}else if(ore.Get_Select_Time()==3){
											time_limit=120;
											count=0;
										}
										click=true;
									}else{
										System.out.println(rivalscore + " " + put[0] + " " +put[1]);
										board = me.reflectAction(put,board,aite_color);
										c.Show(board);
										c.Change_Board(board);
										turn1=true;
										c.Score_Calculate(turn1,turn21);
										turn1=false;
										if(me.judgeEnd(board)==true){Win_Lose();break;}
										click=true;
										if(ore.Get_Select_Time()==1){
											time_limit=30;
											count=0;
										}else if(ore.Get_Select_Time()==2){
											time_limit=60;
											count=0;
										}else if(ore.Get_Select_Time()==3){
											time_limit=120;
											count=0;
										}
									}
								}
							}while(rivalscore == 114514);
						} catch (IOException e) {
								e.printStackTrace();
						}
					}
				}
				}
			}
		}
	}

		public void textValueChanged(TextEvent arg0) {
			System.out.println("aaaa");
		    stopSound(Au3);
	    	playSound(Au4);
	    	remove(match);
	        add(bombset);
	        repaint();revalidate();

		}

}
