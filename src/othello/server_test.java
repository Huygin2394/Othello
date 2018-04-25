package othello;

import java.net.*;
import java.io.*;

//ã‚¹ãƒ¬ãƒƒãƒ‰éƒ¨ï¼ˆå�„ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«å¿œã�˜ã�¦ï¼‰
class Clientconnect extends Thread {
	private int clientnumber;//ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�®ç•ªå�·
	private Socket soc;
	private BufferedReader In;
	private PrintWriter Out;
	private InputStreamReader put;
	private String nickname;//æŽ¥ç¶šè€…ã�®å��å‰�
	private String time;
	private String type;
	private int time1;//æ™‚é–“é�¸æŠž
	private int type1;//ã‚ªã‚»ãƒ­ã�®é�¸æŠž
	private String bombflag;//ãƒœãƒ ç‰ˆã�®ã‚ªã‚»ãƒ­ã‚’ã‚„ã‚‹ã�“ã�¨ã�«æ±ºã�¾ã�£ã�Ÿã‚‰ï¼‘ã‚’ç¤ºã�™ã€‚é€šå¸¸ç‰ˆã�®ã‚ªã‚»ãƒ­ã�®æ™‚ã�¯ï¼‘ã‚’ç¤ºã�™ã€‚
	private int bomb [][];//ãƒœãƒ ã�®ä½�ç½®ã‚’ç¤ºã�™é…�åˆ—
	private String chat;//ãƒ�ãƒ£ãƒƒãƒˆã�®å¤‰æ•°
	private int banmen [];//ç›¤é�¢ã�®æƒ…å ±ã‚’ç¤ºã�™é…�åˆ—
	private boolean giveup;//æŠ•äº†ã�®é�¸æŠž
	private double d;//æŠ•äº†ã�Œå�¯æ±ºã�•ã‚Œã�Ÿæ™‚å†�æˆ¦é�¸æŠžã�®å‰�ã�«ã��ã‚‹flag
	private boolean again;//å†�æˆ¦ã�®é�¸æŠž
	private boolean turn;//å…ˆæ‰‹å¾Œæ‰‹ã�®åˆ¤æ–­
	public Clientconnect(int n, Socket i, InputStreamReader isr, BufferedReader in, PrintWriter out) {
		clientnumber = n;
		soc = i;
		put = isr;
		In = in;
		Out = out;
	}

	public void run() {
		try {
			InputStream is=soc.getInputStream();
			ObjectInputStream inobj = new ObjectInputStream(is);
			ObjectOutputStream outobj = new ObjectOutputStream(soc.getOutputStream());
			
			nickname = In.readLine();//æœ€åˆ�ã�«é€�ã‚‰ã‚Œã�¦ã��ã‚‹ãƒ‹ãƒƒã‚¯ãƒ�ãƒ¼ãƒ 
			server_test.Sendnickname(nickname,clientnumber);//ã‚µãƒ¼ãƒ�ã�«æ�¥ã�Ÿãƒ¡ãƒƒã‚»ãƒ¼ã‚¸ã�¯æŽ¥ç¶šã�—ã�¦ã�„ã‚‹ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é…�ã‚‹
				if(clientnumber==1){
					turn=true;
					server_test.Sendturn(turn, clientnumber);
				}else if(clientnumber==2){
					turn=false;
					server_test.Sendturn(turn, clientnumber);
				}
			time= In.readLine();
			time1 = Integer.parseInt(time);
			server_test.Sendtime(time1,clientnumber);//ã‚µãƒ¼ãƒ�ã�«æ�¥ã�Ÿæ™‚é–“é�¸æŠžã�¯æŽ¥ç¶šã�—ã�¦ã�„ã‚‹ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é…�ã‚‹
			type = In.readLine();
			type1 = Integer.parseInt(type);
			server_test.Sendtime(type1,clientnumber);//ã‚µãƒ¼ãƒ�ã�«æ�¥ã�Ÿã‚ªã‚»ãƒ­ã�®é�¸æŠžã�¯æŽ¥ç¶šã�—ã�¦ã�„ã‚‹ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é…�ã‚‹
			while(true){
				int [][] bomb =new int [8][8];
				bomb=(int [][])inobj.readObject();
					if(bomb==null){
						break;
					}else{
						server_test.Sendbombplace(bomb,clientnumber);
					}
			}
			while (true) {//ç„¡é™�ãƒ«ãƒ¼ãƒ—ã�§ï¼Œã‚½ã‚±ãƒƒãƒˆã�¸ã�®å…¥åŠ›ç¢ºèª�ã�™ã‚‹
				String str1 = In.readLine();
				d=0;
				
				d= Double.parseDouble(str1);//æ¯Žå›ždoubleåž‹ã�«å¤‰æ›´ã�—ã�¦å€¤ã�Œï¼‘ï¼Žï¼•ã�§ã�ªã�„ã�‹ç¢ºã�‹ã‚�ã‚‹
				Boolean wb=new Boolean(str1);//æ–‡å­—åˆ—ã�ŒTRUE,trueã�§trueã€�ã��ã‚Œä»¥å¤–ã�§ã�¯falseã�®å€¤ã�®ã‚ªãƒ–ã‚¸ã‚§ã‚¯ãƒˆã�Œã�§ã��ã‚‹
				if(str1.startsWith("chat:")){//é€�ã‚‰ã‚Œã�¦ã��ã‚‹ã�®ã�Œãƒ�ãƒ£ãƒƒãƒˆã�ªã�®ã�‹æ“�ä½œæƒ…å ±ã�ªã�®ã�‹ã�®åˆ¤åˆ¥
					StringBuilder sb1 = new StringBuilder(str1);
					sb1.delete(0, 4);
					chat = str1;
					System.out.println("No."+clientnumber+"("+nickname+") ã�‹ã‚‰ã�®ãƒ¡ãƒƒã‚»ãƒ¼ã‚¸: "+chat);
					if (chat != null) {//ã�“ã�®ã‚½ã‚±ãƒƒãƒˆï¼ˆãƒ�ãƒƒãƒ•ã‚¡ï¼‰ã�«å…¥åŠ›ã�Œã�‚ã‚‹ã�‹ã‚’ãƒ�ã‚§ãƒƒã‚¯
						server_test.Sendmessage(chat,clientnumber);//ã‚µãƒ¼ãƒ�ã�«æ�¥ã�Ÿãƒ¡ãƒƒã‚»ãƒ¼ã‚¸ã�¯æŽ¥ç¶šã�—ã�¦ã�„ã‚‹ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é…�ã‚‹
					}
				}else if(wb==true){//æŠ•äº†ã�Œé�¸æŠžã�•ã‚Œã�Ÿã�¨ã��ã�®ifæ–‡
					
					server_test.Sendgiveup(giveup,clientnumber);//ã‚µãƒ¼ãƒ�ã�«æ�¥ã�ŸæŠ•äº†ã�®é�¸æŠžã‚’æŽ¥ç¶šã�—ã�¦ã�„ã‚‹ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é…�ã‚‹
					wb=false;
					
				}else if(d==1.5){//å†�æˆ¦é�¸æŠžã�«ã�¤ã�„ã�¦é€�ä¿¡ã�™ã‚‹ã€‚d=1.5ã�®æ™‚ã�¯å†�æˆ¦é�¸æŠžã�Œyes
					again=true;
					server_test.Sendagain(again,clientnumber);//ã‚µãƒ¼ãƒ�ã�«æ�¥ã�Ÿå†�æˆ¦ã�®é�¸æŠžã‚’æŽ¥ç¶šã�—ã�¦ã�„ã‚‹ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é…�ã‚‹
						
				}else if(d==2.5){//å†�æˆ¦é�¸æŠžã�«ã�¤ã�„ã�¦é€�ä¿¡ã�™ã‚‹ã€‚d=2.5ã�®æ™‚ã�¯å†�æˆ¦é�¸æŠžã�Œno
					
					again=false;
					server_test.Sendagain(again,clientnumber);//ã‚µãƒ¼ãƒ�ã�«æ�¥ã�Ÿå†�æˆ¦ã�®é�¸æŠžã‚’æŽ¥ç¶šã�—ã�¦ã�„ã‚‹ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é…�ã‚‹
				
				}else{//æ“�ä½œæƒ…å ±ã�Œé€�ã‚‰ã‚Œã�¦ã��ã�Ÿã�¨ã��ã�®ifæ–‡

					int [] othello =new int [2];
					othello=(int [])inobj.readObject();
					server_test.Sendothello(othello,clientnumber);
					
				}
			}
		} catch (Exception e) {//ã�›ã�¤ã�‰ã��ã�Œåˆ‡æ–­ã�•ã‚Œã�Ÿæ™‚
			System.out.println("client No."+clientnumber+"("+nickname+")ã�Œåˆ‡æ–­ã�—ã�¾ã�—ã�Ÿã€‚");
			server_test.SetFlag(clientnumber, false);//æŽ¥ç¶šã�Œåˆ‡ã‚Œã�Ÿã�®ã�§ã��ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�®ã�¯æŽ¥ç¶šçŠ¶æ³�ã‚’åˆ¤åˆ¥ã�™ã‚‹ãƒ•ãƒ©ã‚°ã‚’ä¸‹ã�’ã‚‹
		}
	}
}

class server_test{
	
	private static Socket[] coming;//ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã‚’å�—ã�‘ä»˜ã�‘ã‚‹ã�Ÿã‚�ã�®ã‚½ã‚±ãƒƒãƒˆ
	private static boolean[] connectflag;//æŽ¥ç¶šä¸­ã�‹ã�©ã�†ã�‹ã�®ãƒ•ãƒ©ã‚°
	private static BufferedReader[] in;//ãƒ�ãƒƒãƒ•ã‚¡ãƒªãƒ³ã‚°ã�«ã‚ˆã‚‹ãƒ†ã‚­ã‚¹ãƒˆèª­ã�¿è¾¼ã�¿ç”¨ã�®é…�åˆ—
	private static InputStreamReader[] input;//å…¥åŠ›ã‚¹ãƒˆãƒªãƒ¼ãƒ ç”¨ã�®é…�åˆ—
	private static PrintWriter[] out;//å‡ºåŠ›ã‚¹ãƒˆãƒªãƒ¼ãƒ ç”¨ã�®é…�åˆ—
	private static Clientconnect[] ClientThread;//ã‚¹ãƒ¬ãƒƒãƒ‰ç”¨ã�®é…�åˆ—
	private static int member;//æŽ¥ç¶šã�—ã�¦ã�„ã‚‹ãƒ¡ãƒ³ãƒ�ãƒ¼ã�®æ•°

	public static void Sendnickname(String nickname,int n){
		//é€�ã‚‰ã‚Œã�Ÿæ�¥ã�Ÿãƒ‹ãƒƒã‚¯ãƒ�ãƒ¼ãƒ ã‚’ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é…�ã‚‹
		if(n==1){
			if(connectflag[1]&&connectflag[2] == true){
				out[2].println(nickname);
				out[2].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+2+"ã�«"+nickname+"ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}else if(n==2){
			if(connectflag[1]&&connectflag[2] == true){
				out[1].println(nickname);
				out[1].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+1+"ã�«"+nickname+"ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}
	}
	public static void Sendtime(int time1,int n){
		//é€�ã‚‰ã‚Œã�Ÿæ�¥ã�Ÿæ™‚é–“é�¸æŠžã�®çµ�æžœã‚’ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é…�ã‚‹
		if(n==1){
			if(connectflag[1]&&connectflag[2] == true){
				out[2].println(time1);
				out[2].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+2+"ã�«"+time1+"ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}else if(n==2){
			if(connectflag[1]&&connectflag[2] == true){
				out[1].println(time1);
				out[1].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+1+"ã�«"+time1+"ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}
	}
	public static void Sendtype(int type1,int n){
		//é€�ã‚‰ã‚Œã�Ÿæ�¥ã�Ÿãƒ¡ãƒƒã‚»ãƒ¼ã‚¸ã‚’ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é…�ã‚‹
		if(n==1){
			if(connectflag[1]&&connectflag[2] == true){
				out[2].println(type1);
				out[2].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+2+"ã�«"+type1+"ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}else if(n==2){
			if(connectflag[1]&&connectflag[2] == true){
				out[1].println(type1);
				out[1].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+1+"ã�«"+type1+"ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}
	}
	public static void Sendmessage(String chat,int n){
		//é€�ã‚‰ã‚Œã�Ÿæ�¥ã�Ÿãƒ¡ãƒƒã‚»ãƒ¼ã‚¸ã‚’ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é…�ã‚‹
		if(n==1){
			if(connectflag[1]&&connectflag[2] == true){
				out[2].println(chat);
				out[2].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+2+"ã�«ãƒ¡ãƒƒã‚»ãƒ¼ã‚¸ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}else if(n==2){
			if(connectflag[1]&&connectflag[2] == true){
				out[1].println(chat);
				out[1].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+1+"ã�«ãƒ¡ãƒƒã‚»ãƒ¼ã‚¸ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}
	}
	public static void Sendagain(boolean again,int n){
		//é€�ã‚‰ã‚Œã�Ÿæ�¥ã�Ÿå†�æˆ¦é�¸æŠžã�®çµ�æžœã‚’ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é…�ã‚‹
		if(n==1){
			if(connectflag[1]&&connectflag[2] == true){
				out[2].println(again);
				out[2].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+2+"ã�«å†�æˆ¦é�¸æŠž"+again+"ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}else if(n==2){
			if(connectflag[1]&&connectflag[2] == true){
				out[1].println(again);
				out[1].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+1+"ã�«å†�æˆ¦é�¸æŠž"+again+"ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}
	}
	public static void Sendgiveup(boolean giveup,int n){
		//é€�ã‚‰ã‚Œã�Ÿæ�¥ã�ŸæŠ•äº†é�¸æŠžã�®çµ�æžœã‚’ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é…�ã‚‹
		if(n==1){
			if(connectflag[1]&&connectflag[2] == true){
				out[2].println(giveup);
				out[2].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+2+"ã�«æŠ•äº†é�¸æŠž"+giveup+"ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}else if(n==2){
			if(connectflag[1]&&connectflag[2] == true){
				out[1].println(giveup);
				out[1].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+1+"ã�«æŠ•äº†é�¸æŠž"+giveup+"ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}
	}
	public static void Sendturn(boolean turn,int n){
		//å…ˆæ‰‹ã�‹å¾Œæ‰‹ã�‹ã‚’ã��ã�†ã�—ã‚“ã�™ã‚‹
		if(n==1){
			if(connectflag[1]&&connectflag[2] == true){
				out[1].println(turn);
				out[1].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+1+"ã�«"+turn+"(å…ˆæ‰‹)ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}else if(n==2){
			if(connectflag[1]&&connectflag[2] == true){
				out[2].println(turn);
				out[2].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+2+"ã�«å†�æˆ¦é�¸æŠž"+turn+"(å¾Œæ‰‹)ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}
	}
	public static void Sendbombplace(int bomb[][],int n){
		if(n==1){
			if(connectflag[1]&&connectflag[2] == true){
				out[2].println(bomb);
				out[2].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+1+"ã�«ãƒœãƒ ã�®ä½�ç½®"+bomb+"ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}else if(n==2){
			if(connectflag[1]&&connectflag[2] == true){
				out[1].println(bomb);
				out[1].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+2+"ã�«ãƒœãƒ ã�®ä½�ç½®"+bomb+"ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}
	}
	public static void Sendothello(int othello[],int n){//æ“�ä½œæƒ…å ±ã‚’ç›¸æ‰‹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹ã€‚
		if(n==1){
			if(connectflag[1]&&connectflag[2] == true){
				out[2].println(othello);
				out[2].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+1+"ã�«æ“�ä½œæƒ…å ±"+othello+"ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}else if(n==2){
			if(connectflag[1]&&connectflag[2] == true){
				out[1].println(othello);
				out[1].flush();//ãƒ�ãƒƒãƒ•ã‚¡ã�®å…¨ã�¦ã�®ãƒ‡ãƒ¼ã‚¿ã‚’ã�™ã��ã�«ã‚‚ã�†ä¸€æ–¹ã�®ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�«é€�ä¿¡ã�™ã‚‹
				System.out.println("client No."+2+"ã�«ãƒœãƒ ã�®ä½�ç½®"+othello+"ã‚’é€�ä¿¡ã�—ã�¾ã�—ã�Ÿã€‚");
			}
		}
	}
	//ãƒ•ãƒ©ã‚°ã�®è¨­å®šã‚’è¡Œã�†
	public static void SetFlag(int n, boolean v){
		connectflag[n] = v;
	}
	
	//mainãƒ—ãƒ­ã‚°ãƒ©ãƒ 
	public static void main(String[] args) {
		//å¿…è¦�ã�ªé…�åˆ—ã‚’ç¢ºä¿�ã�™ã‚‹
		coming = new Socket[2];
		connectflag = new boolean[2];//ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã�®æŽ¥ç¶šçŠ¶æ³�
		ClientThread = new Clientconnect[2];
		input = new InputStreamReader[2];
		in = new BufferedReader[2];
		out = new PrintWriter[2];//ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆäºŒã�¤åˆ†ã�®æ›¸ã��è¾¼ã�¿ã‚„èª­ã�¿å�–ã‚Šé…�åˆ—
		member = 0;//æœ€åˆ�ã�¯æŽ¥ç¶šã�—ã�¦ã�„ã�ªã�„ã�®ã�§ãƒ¡ãƒ³ãƒ�ãƒ¼æ•°ã�¯ï¼�
		int n = 1;//å…ˆã�«æŽ¥ç¶šã�—ã�Ÿã�»ã�†ã�Œï¼‘ã�§å¾Œã�§æŽ¥ç¶šã�—ã�Ÿã�»ã�†ã�Œï¼’
		try {
			
			System.out.println("ã‚µãƒ¼ãƒ�ã�Œç«‹ã�¡ä¸Šã�Œã‚Šã�¾ã�—ã�Ÿ");
			ServerSocket server = new ServerSocket(10000);//10000ç•ªãƒ�ãƒ¼ãƒˆã‚’åˆ©ç”¨ã�™ã‚‹
			while (true) {
				coming[n] = server.accept();
				connectflag[n] = true;
				System.out.println("client No." + n +"ã‚’å�—ã�‘ä»˜ã�‘ã�¾ã�—ã�Ÿ");
				input[n] = new InputStreamReader(coming[n].getInputStream());//å¿…è¦�ã�ªå…¥å‡ºåŠ›ã‚¹ãƒˆãƒªãƒ¼ãƒ ã‚’ä½œæˆ�ã�™ã‚‹
				in[n] = new BufferedReader(input[n]);
				out[n] = new PrintWriter(coming[n].getOutputStream(), true);
				ClientThread[n] = new Clientconnect(n, coming[n], input[n], in[n], out[n]);//å¿…è¦�ã�ªå€¤ã‚’æ¸¡ã�—ã‚¹ãƒ¬ãƒƒãƒ‰ã‚’ä½œæˆ�
				ClientThread[n] .start();//ã‚¹ãƒ¬ãƒƒãƒ‰ã‚’é–‹å§‹ã�™ã‚‹
				member = n;//ãƒ¡ãƒ³ãƒ�ãƒ¼ã�®æ•°ã‚’æ›´æ–°ã�™ã‚‹
				n++;
			}
		} catch (Exception e) {
			System.err.println("ã‚½ã‚±ãƒƒãƒˆä½œæˆ�æ™‚ã�«ã‚¨ãƒ©ãƒ¼ã�Œç™ºç”Ÿã�—ã�¾ã�—ã�Ÿ: " + e);
		}
	}
}
