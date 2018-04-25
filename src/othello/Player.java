package othello;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Player extends JFrame{

	private static String rivalid;
	public static int timeselect=0,genreselect=0,time_flag=0,gamemode_flag=0,stage_flag=0,time_limit=0;
	private static final int BLACK = 1,WHITE=2;

	public Player(){

	}

	public String Receive_Rivalname(DataInputStream dis,Othello me,boolean myturn,int my_color,JLabel info_of_me,JLabel info_of_rival,String id,int score,int rivalscore){
		try {
			rivalid = dis.readUTF();
		    System.out.println("Rival ID : " + rivalid);

		    if(my_color==BLACK){
				info_of_me.setText("<html>"+"<p>"+"     "+id+"<br>"+"     :"+me.getScore(myturn)+"  Score: "+score+"<p>"+"<html>");
				info_of_rival.setText("<html>"+"<p>"+"     "+rivalid+"<br>"+"     :"+me.getScore(!myturn)+"  Score: "+rivalscore+"<p>"+"<html>");
			}else{
				info_of_me.setText("<html>"+"<p>"+"     "+id+"<br>"+"     :"+me.getScore(myturn)+"  Score: "+score+"<p>"+"<html>");
				info_of_rival.setText("<html>"+"<p>"+"     "+rivalid+"<br>"+"     :"+me.getScore(!myturn)+"  Score: "+rivalscore+"<p>"+"<html>");
			}
		} catch (Exception e) {e.printStackTrace();}
		return rivalid;
	}

	public String Get_RivalID(){
		return rivalid;
	}

	public static void Select(JLabel rule,JButton decide,JButton r1,JButton r2,JButton r3,JButton r4,JButton r5,JButton r6,JButton r7,JButton r8){
	    Border noBorder = new LineBorder(Color.WHITE, 0);
		r1.setBackground(new Color(47,85,151));r1.setBorder(noBorder);rule.add(r1);r1.setBounds(13, 25, 423, 93);r1.setActionCommand("r1");
	    r2.setBackground(new Color(47,85,151));r2.setBorder(noBorder);rule.add(r2);r2.setBounds(13, 124, 423, 93);r2.setActionCommand("r2");
	    r3.setBackground(new Color(47,85,151));r3.setBorder(noBorder);rule.add(r3);r3.setBounds(13, 223, 423, 93);r3.setActionCommand("r3");
	    r4.setBackground(new Color(47,85,151));r4.setBorder(noBorder);rule.add(r4);r4.setBounds(461, 25, 423, 93);r4.setActionCommand("r4");
	    r5.setBackground(new Color(47,85,151));r5.setBorder(noBorder);rule.add(r5);r5.setBounds(461, 124, 423, 93);r5.setActionCommand("r5");
	    r6.setBackground(new Color(47,85,151));r6.setBorder(noBorder);rule.add(r6);r6.setBounds(461, 223, 423, 93);r6.setActionCommand("r6");
	    r7.setBackground(new Color(51,63,80));r7.setBorder(noBorder);rule.add(r7);r7.setBounds(40, 375, 385, 290);r7.setActionCommand("r7");
	    r8.setBackground(new Color(51,63,80));r8.setBorder(noBorder);rule.add(r8);r8.setBounds(478, 375, 385, 290);r8.setActionCommand("r8");

	    r1.setIcon(new ImageIcon("30sec.png"));
        r2.setIcon(new ImageIcon("1min.png"));
        r3.setIcon(new ImageIcon("2min.png"));
        r4.setIcon(new ImageIcon("5min.png"));
        r5.setIcon(new ImageIcon("10min.png"));
        r6.setIcon(new ImageIcon("15min.png"));
        r7.setIcon(new ImageIcon("gamemode_othello_normal.png"));
        r8.setIcon(new ImageIcon("gamemode_othello_score.png"));

	    r1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    	    	if(time_flag == 0){
	            	r1.setEnabled(true);
	            	r2.setEnabled(false);
	            	r3.setEnabled(false);
	            	r4.setEnabled(false);
	            	r5.setEnabled(false);
	            	r6.setEnabled(false);
	            	timeselect=1;
	            	time_flag = 1;
	            	gamemode_flag++;
	            	time_limit = 30;
	            	if(gamemode_flag == 2)decide.setEnabled(true);
    	    	}else{
    	    		r1.setEnabled(true);
	            	r2.setEnabled(true);
	            	r3.setEnabled(true);
	            	r4.setEnabled(true);
	            	r5.setEnabled(true);
	            	r6.setEnabled(true);
	            	time_flag = 0;
	            	gamemode_flag--;
	            	if(gamemode_flag != 2)decide.setEnabled(false);
    	    	}
            }
    	});

	    r2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    	    	if(time_flag == 0){
	            	r1.setEnabled(false);
	            	r2.setEnabled(true);
	            	r3.setEnabled(false);
	            	r4.setEnabled(false);
	            	r5.setEnabled(false);
	            	r6.setEnabled(false);
	            	timeselect=2;
	            	time_flag = 1;
	            	gamemode_flag++;
	            	time_limit = 60;
	            	if(gamemode_flag == 2)decide.setEnabled(true);
    	    	}else{
    	    		r1.setEnabled(true);
	            	r2.setEnabled(true);
	            	r3.setEnabled(true);
	            	r4.setEnabled(true);
	            	r5.setEnabled(true);
	            	r6.setEnabled(true);
	            	time_flag = 0;
	            	gamemode_flag--;
	            	if(gamemode_flag != 2)decide.setEnabled(false);
    	    	}
            }
    	});

	    r3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    	    	if(time_flag == 0){
	            	r1.setEnabled(false);
	            	r2.setEnabled(false);
	            	r3.setEnabled(true);
	            	r4.setEnabled(false);
	            	r5.setEnabled(false);
	            	r6.setEnabled(false);
	            	timeselect=3;
	            	time_flag = 1;
	            	gamemode_flag++;
	            	time_limit = 120;
	            	if(gamemode_flag == 2)decide.setEnabled(true);
    	    	}else{
    	    		r1.setEnabled(true);
	            	r2.setEnabled(true);
	            	r3.setEnabled(true);
	            	r4.setEnabled(true);
	            	r5.setEnabled(true);
	            	r6.setEnabled(true);
	            	time_flag = 0;
	            	gamemode_flag--;
	            	if(gamemode_flag != 2)decide.setEnabled(false);
    	    	}
            }
    	});

	    r4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    	    	if(time_flag == 0){
	            	r1.setEnabled(false);
	            	r2.setEnabled(false);
	            	r3.setEnabled(false);
	            	r4.setEnabled(true);
	            	r5.setEnabled(false);
	            	r6.setEnabled(false);
	            	timeselect=4;
	            	time_flag = 1;
	            	gamemode_flag++;
	            	time_limit = 300;
	            	if(gamemode_flag == 2)decide.setEnabled(true);
    	    	}else{
    	    		r1.setEnabled(true);
	            	r2.setEnabled(true);
	            	r3.setEnabled(true);
	            	r4.setEnabled(true);
	            	r5.setEnabled(true);
	            	r6.setEnabled(true);
	            	time_flag = 0;
	            	gamemode_flag--;
	            	if(gamemode_flag != 2)decide.setEnabled(false);
    	    	}
            }
    	});

	    r5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    	    	if(time_flag == 0){
	            	r1.setEnabled(false);
	            	r2.setEnabled(false);
	            	r3.setEnabled(false);
	            	r4.setEnabled(false);
	            	r5.setEnabled(true);
	            	r6.setEnabled(false);
	            	timeselect=5;
	            	time_flag = 1;
	            	gamemode_flag++;
	            	time_limit = 600;
	            	if(gamemode_flag == 2)decide.setEnabled(true);
    	    	}else{
    	    		r1.setEnabled(true);
	            	r2.setEnabled(true);
	            	r3.setEnabled(true);
	            	r4.setEnabled(true);
	            	r5.setEnabled(true);
	            	r6.setEnabled(true);
	            	time_flag = 0;
	            	gamemode_flag--;
	            	if(gamemode_flag != 2)decide.setEnabled(false);
    	    	}
            }
    	});

	    r6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    	    	if(time_flag == 0){
	            	r1.setEnabled(false);
	            	r2.setEnabled(false);
	            	r3.setEnabled(false);
	            	r4.setEnabled(false);
	            	r5.setEnabled(false);
	            	r6.setEnabled(true);
	            	timeselect=6;
	            	time_flag = 1;
	            	gamemode_flag++;
	            	time_limit = 900;
	            	if(gamemode_flag == 2)decide.setEnabled(true);
    	    	}else{
    	    		r1.setEnabled(true);
	            	r2.setEnabled(true);
	            	r3.setEnabled(true);
	            	r4.setEnabled(true);
	            	r5.setEnabled(true);
	            	r6.setEnabled(true);
	            	time_flag = 0;
	            	gamemode_flag--;
	            	if(gamemode_flag != 2)decide.setEnabled(false);
    	    	}
            }
    	});

	   r7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    	    	if(stage_flag == 0){
	            	r7.setEnabled(true);
	            	r8.setEnabled(false);
	            	stage_flag = 1;
	            	gamemode_flag++;
	            	genreselect=1;
	            	if(gamemode_flag == 2)decide.setEnabled(true);
    	    	}else{
    	    		r7.setEnabled(true);
	            	r8.setEnabled(true);
	            	stage_flag = 0;
	            	gamemode_flag--;
	            	if(gamemode_flag != 2)decide.setEnabled(false);
    	    	}
            }
    	});

	    r8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    	    	if(stage_flag == 0){
	            	r7.setEnabled(false);
	            	r8.setEnabled(true);
	            	stage_flag = 1;
	            	gamemode_flag++;
	            	genreselect=2;
	            	if(gamemode_flag == 2)decide.setEnabled(true);
    	    	}else{
    	    		r7.setEnabled(true);
	            	r8.setEnabled(true);
	            	stage_flag = 0;
	            	gamemode_flag--;
	            	if(gamemode_flag != 2)decide.setEnabled(false);
    	    	}
            }
    	});

	}

	public static int Get_Time(){
		return time_limit;
	}

	public int Get_Select_Time(){
		return timeselect;
	}

	public static int Get_Genre(){
		return genreselect;
	}

	public static void Send_Rematch(){

	}

}
