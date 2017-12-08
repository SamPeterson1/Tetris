package game;

public class Piece {
	int color;
	int[][] data;
	
	public Piece(int color, int[][] data) {
		this.color = color;
		for(int i = 0; i < 4; i ++) {
			for(int j = 0; j < 4; j ++) {
				if(data[i][j] == 1) {
					this.data[i][j] = color;
				}
				this.data[i][j] = 0;
			}
		}
	}
}
