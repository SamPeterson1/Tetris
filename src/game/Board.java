package game;

import java.util.Arrays;

public class Board {
	int[][] board = new int[22][10];
	int[][] staticBoard = new int[22][10];
	float speed = 1;
	int score = 0;
	TetrisCanvas canvas;
	public int[][] getBoard() {
		return board;
	}
	
	public Board(TetrisCanvas canvas) {
		this.canvas = canvas;
	}
	
	public int[][] addPiece(Piece piece, int[][] board) {
		int[][] data;
		data = piece.getData();
		int w = board[0].length;
		int l = board.length;
		int x = piece.getX();
		int y = piece.getY();
		int width = piece.getWidth();
		int length = piece.getLength();
		for(int i = y; i < y + length; i ++) {
			for(int j = x; j < x + width; j ++) {
				if(i < l & j < w & data[i - y][j - x] != 0) {
					board[i][j] = data[i - y][j - x];
				}
			}
		}
		return board;
	}
	
	public int getSpeed(int gameLoopSpeed) {
		if(score >= 5) {
			this.speed = 0.7f;
		}
		if(score >= 10) {
			this.speed = 0.5f;
		}
		if(score >= 20) {
			this.speed = 0.25f;
		}
		if(score >= 50) {
			this.speed = 0.1f;
		}
		if(score >= 100) {
			this.speed = 0.05f;
		}
		return (int) (this.speed * 1000/gameLoopSpeed);
	}
	
	public void checkForTetris() {
		for(int i = 0; i < 22; i ++) {
			int completeSections = 0;
			for(int j = 0 ; j < 10; j ++) {
				if(staticBoard[i][j] != 0) {
					completeSections ++;
				}
			}
			if(completeSections == 10) {
				System.out.println("HII" + (22 - i));
				this.moveDown(22 -i); 
				this.dropColumn(i);
				score ++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				canvas.draw();
			}
		}
	}
	
	public void moveDown(int startRow) {
		int[][] backupBoard = new int[22][10];
		for(int i = 0; i < 22; i ++) {
			backupBoard[i] = Arrays.copyOf(staticBoard[i], staticBoard.length);
		}
		for(int i = 22 - startRow; i > 0 ; i --) {
			for(int j = 0; j < 10; j ++) {
				if(i != startRow) {
					staticBoard[i][j] = backupBoard[i - 1][j];
				} else {
					staticBoard[i][j] = 0; 
				}
			}
		}
	}
	
	public void dropColumn(int j) {
		for(int l = 0; l < 10; l ++) {
			while(this.getGap(l) != 0) {
				int[][] backupBoard = new int[22][10];
				for(int i = 0; i < 22; i ++) {
					backupBoard[i] = Arrays.copyOf(staticBoard[i], staticBoard.length);
				}
				int startRow = this.getGap(l);
				if(this.getGap(l) > j) {
					break;
				}
				for(int i = startRow; i > 0 ; i --) {
					if(i != 22 - startRow) {
						System.out.println("hi");
						staticBoard[i][l] = backupBoard[i - 1][l];
					}
				}
			}
		}
	}
	
	public int getGap(int column) {
		boolean searchForGap = false;
		for(int i = 0; i < 22; i ++) {
			if(staticBoard[i][column] != 0) {
				searchForGap = true;
			}
			if(searchForGap) {
				if(staticBoard[i][column] == 0) {
					return i;
				}
			}
		}
		return 0;
	}
	
	public void print(int[][] data) {
		for(int i = 0; i < data.length; i ++) {
			System.out.println("");
			for(int j = 0; j < data[1].length; j ++) {
				System.out.print(data[i][j]);
			}
		}
		System.out.println(" ");
	}
	
	
	public void reset() {
		board = new int[22][10];
	}
	
	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	public boolean updatePieceBoard(Piece piece, boolean moveDown) {
		int length = piece.getLength();
		board = new int[22][10];
		board = this.addPiece(piece, board);
		if(!(piece.getY() >= 22 - length) & !overlaps(piece, piece.getX(), piece.getY() + 1) & moveDown) {
			piece.setY(piece.getY() + 1);
			board = new int[22][10];
			board = this.addPiece(piece, board);
			return true;
		} else if(moveDown){
			this.addPiece(piece, staticBoard);
			return false;
		}
		return true;
	}
	
	public int[][] getStaticBoard() {
		return this.staticBoard;
	}
	
	public boolean overlaps(Piece piece, int moveX, int moveY) {
		int[][] testBoard = new int[22][10];
		int x = piece.getX();
		int y = piece.getY();
		piece.setX(moveX);
		piece.setY(moveY);
		this.addPiece(piece, testBoard);
		for(int i = 0; i < 22; i ++) {
			for(int j = 0; j < 10; j ++) {
				if(testBoard[i][j] != 0 & staticBoard[i][j] != 0) {
					piece.setX(x);
					piece.setY(y);
					return true;
				}
			}
		}
		piece.setX(x);
		piece.setY(y);
		return false;
	}
}
