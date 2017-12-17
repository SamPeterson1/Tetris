package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Predictor {
	Piece piece;
	Piece piece2;
	
	ArrayList<Piece> pieces = new ArrayList<Piece>();
	int[][] prediction = new int[22][10];
	int[][] staticb = new int[22][10];
	//test
	
	public void updatePrediction(Piece piece, TetrisCanvas canvas, Board b) {
		int y = piece.getY();
		int iter = 0;
		int repetitions = 0;
		while(!b.overlaps(piece, piece.getX(), piece.getY() + 1) & !(piece.getY() + piece.getLength() == 22)) {
			piece.setY(piece.getY() + 1);
			if(repetitions > 30) {
				break;
			}
			if(b.getBoard().equals(new int[22][10])) {
				break;
			}
			System.out.println("HI");
			repetitions ++;
		}
		prediction = new int[22][10];
		prediction = b.addPiece(piece, prediction);
		piece.setY(y);
	}
	
	public int[][] getPrediction(){
		return this.prediction;
	}
	
	public Predictor() {
		int[][] data = {
				{1,1,0,0},
				{1,1,0,0},
				{0,0,0,0},
				{0,0,0,0},
			};
			addPiece(1, data, 5, 2, 2);
			int[][] data1 = {
				{1,0,0,0},
				{1,0,0,0},
				{1,0,0,0},
				{1,0,0,0},
			};
			addPiece(2, data1, 6, 1, 4);
			int[][] data2 = {
				{0,1,1,0},
				{1,1,0,0},
				{0,0,0,0},
				{0,0,0,0},
			};
			addPiece(3, data2, 7, 3, 2);
			int[][] data3 = {
				{1,1,0,0},
				{0,1,1,0},
				{0,0,0,0},
				{0,0,0,0},
			};
			addPiece(4, data3, 8, 3, 2);
			int[][] data4 = {
				{1,0,0,0},
				{1,0,0,0},
				{1,1,0,0},
				{0,0,0,0},
			};
			addPiece(5, data4, 9, 2, 3);
			int[][] data5 = {
				{0,1,0,0},
				{0,1,0,0},
				{1,1,0,0},
				{0,0,0,0},
			};
			addPiece(6, data5, 10, 2, 3);
			int[][] data6 = {
				{1,1,1,0},
				{0,1,0,0},
				{0,0,0,0},
				{0,0,0,0},
			};
			addPiece(7, data6, 11, 3, 2);
			
			piece = this.randomizePeice(System.currentTimeMillis());
			piece2 = this.randomizePeice(new Random(System.currentTimeMillis()).nextInt((10000 - 5) + 1) + 5);
		}
		
		public void addPiece(int color, int[][] data, int ID, int width, int length) {
			pieces.add(new Piece(color, data, ID, width, length));
		}
		
		public Piece nextPiece() {
			Piece retVal = piece.clone();
			piece = piece2.clone();
			piece2 = this.randomizePeice(System.currentTimeMillis());
			return retVal;
		}
	
		public Piece randomizePeice(long l) {
			int i = new Random(l).nextInt((11 - 5) + 1) + 5;
			for(Piece piece: pieces) {
				if(piece.getID() == i) {
					piece.center();
					return piece;
				}
			}
			return null;
		}
		
		public Piece getNextPiece(boolean first) {
			if(first) {
				piece.setLeft();
				return piece;
			}
			return piece2;
		}
		
		public int[][] getNextPiece(int j, TetrisCanvas canvas) {
			Board b = new Board(canvas);
			b.reset();
			piece.setLeft();
			piece2.setLeft();
			if(j == 0) {
				return b.addPiece(piece, new int[4][4]);
			}
			return b.addPiece(piece2, new int[4][4]);
		}
}
