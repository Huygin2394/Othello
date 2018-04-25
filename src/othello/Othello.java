package othello;

public class Othello {
	// 各定義
	private static final int NUM = 8;
	private static final int EMPTY = 0;
	private static final int BLACK = 1;
	private static final int WHITE = 2;
	private static final int ABLE = 3;
	private static final int DIREC = 8;
	private static final int[] d_x = {-1,-1,-1, 0, 0, 1, 1, 1};
	private static final int[] d_y = {-1, 0, 1,-1, 1,-1, 0, 1};
	final int my_color,o_color;
	private int[][] board = new int[NUM+2][NUM+2];
	private int[][] bomb = new int[NUM][NUM];
	private int black_num = 0,white_num = 0;
	public int combo = 0,kk=0;

	Othello(int[][] board,boolean turn) {

		// 盤面の初期化
		for (int i=0;i<NUM;i++) {
			for (int j=0;j<NUM;j++) {
				board[i][j] = EMPTY;
				bomb[i][j] = EMPTY;
			}
		}
		board[3][3] = BLACK;
		board[3][4] = WHITE;
		board[4][3] = WHITE;
		board[4][4] = BLACK;

		// ターンの取得
		if (turn) {
			my_color = BLACK;
			o_color = WHITE;
		} else {
			my_color = WHITE;
			o_color = BLACK;
		}

		black_num = 2;
		white_num = 2;

	}

	// ボムの位置を反映
	int[][] setBomb( int [][] bomber,int row, int column ) {
		if(bomber[row][column] == 3){
			bomber[row][column] = 0;
			client2.bombcount+=2;
		}else{
			bomber[row][column]=bomber[row][column]+3;
		}
		return bomber;
	}

	// 駒を置けるマスを探索するメソッド、相手の操作後の最新の盤面を引数に入力する
	int[][] isAbleToBePut(int[][] board) {
		// 置けるマスの探索
		for ( int i=0;i<NUM;i++ ) {
			for ( int j=0;j<NUM;j++ ) {
				if ( board[i][j] == my_color ) {
					for ( int d=0;d<DIREC;d++ ) {
						int x = i;
						int y = j;
						boolean wall = false;
						boolean able = false;

						do {
							if ( x+d_x[d]>-1 && y+d_y[d]>-1 && x+d_x[d]<8 && y+d_y[d]<8 ) {
								x = x + d_x[d];
								y = y + d_y[d];
							} else {
								wall = true;
							}
							if ( board[x][y] == o_color && !wall ) {
								able = true;
							}
						} while ( board[x][y] == o_color && !wall );

						if ( board[x][y] == EMPTY && able ) {
							board[x][y] = ABLE;
						}
					}
				}
			}


		}

		return board;
	}

	// 操作の反映結果を返すメソッド
	int[][] reflectAction(int[] put,int[][] board, int my_color ) {
		int o_color;
		if(my_color == 1){
			o_color = 2;
		}else{
			o_color = 1;
		}
		int[][] result = new int[NUM+2][NUM+2];
		for ( int i=0;i<NUM;i++ ) {
			for ( int j=0;j<NUM;j++ ) {
				result[i][j] = board[i][j];//前の盤面読み取り
				if ( result[i][j] == ABLE ) {
					result[i][j] = EMPTY;//おけるマス初期化
				}
			}
		}

		combo = 0;
		board[put[0]][put[1]] = my_color;
		result[put[0]][put[1]] = my_color*10;

		if(my_color == 1){
			black_num++;
		}else{
			white_num++;
		}

		for ( int d=0;d<DIREC;d++ ) {
			int x = put[0];
			int y = put[1];
			int n = 0;
			boolean wall = false;
			boolean able = false;

			do {
				if ( x+d_x[d]>-1 && y+d_y[d]>-1 && x+d_x[d]<8 && y+d_y[d]<8 ) {
					x = x + d_x[d];
					y = y + d_y[d];
					n++;
				} else {
					wall = true;
				}

				if ( board[x][y] == o_color && !wall ) {
					able = true;
				}
			} while ( board[x][y] == o_color && !wall );
			// 相手の駒が続いた先に自分の駒があった場合に、相手の駒を逆転
			if ( board[x][y] == my_color && able && my_color == 1) {
				black_num+=(n-1);
				white_num-=(n-1);
				while ( n > 0 ) {
					x = x - d_x[d];
					y = y - d_y[d];
					n--;
					board[x][y] = my_color;
					result[x][y] = my_color*10 + n;
					combo++;
				}
			}else if(board[x][y] == my_color && able && my_color == 2){//付け加えた
				white_num += (n-1);
				black_num-=(n-1);
				while ( n > 0 ) {
					x = x - d_x[d];
					y = y - d_y[d];
					n--;
					board[x][y] = my_color;
					result[x][y] = my_color*10 + n;
					combo++;
				}
			}
			n=0;
		}

		return result;
	}

	int getScore(boolean turn) {
		if (turn==true){
			return black_num;
		}else{
			return white_num;
		}
	}

	// パスを判定するメソッド、置く場所がない時はtrueを返す
	boolean judgePass(int[][]board) {
		boolean flag = true;
		for (int i=0;i<NUM;i++) {
			for (int j=0;j<NUM;j++) {
				if (board[i][j] == ABLE) {
					flag = false;
				}
			}
		}
		return flag;
	}

	// 対局終了を判定するメソッド
	boolean judgeEnd(int[][] board) {
		boolean flag = false;
		int f1=0,f2=0,f3=0;
		for (int i=0;i<NUM;i++) {
			for (int j=0;j<NUM;j++) {
				if (board[i][j] == BLACK) {
					f1++;
				}else if (board[i][j] == WHITE){
					f2++;
				}else if (board[i][j] == EMPTY){
					f3++;
				}
			}
		}
		if(f1==0||f2==0||f3==0)flag=true;

		if (flag) {
			System.out.println("終了");
		}
		return flag;
	}

}
