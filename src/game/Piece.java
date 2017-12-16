package game;

import java.util.Arrays;

public class Piece {
	private int color;
	private int[][] data = new int[4][4];
	private int ID;
	private int x = 0;
	private int y = 0;
	private int length;
	private int width;
	
	public Piece(int color, int[][] data, int ID, int width, int length) {
		this.color = color;
		for(int i = 0; i < 4; i ++) {
			for(int j = 0; j < 4; j ++) {
				if(data[i][j] == 1) {
					this.data[i][j] = color;
				} else {
					this.data[i][j] = 0;
				}
			}
		}
		this.ID = ID;
		x = 5 - ((int)(width/2));
		y = 0;
		this.width = width;
		this.length = length;
	}
	
	public void setLeft() {
		x = 0;
	}
	
	public void center() {
		x = 5 - ((int)(width/2));
	}
	
	public Piece clone() {
		Piece retVal;
		retVal = new Piece(color, new int[4][4], ID, width, length);
		retVal.setData(this.data);
		return retVal;
	}
	
	public void setData(int[][] data) {
		for(int i = 0; i < data.length; i ++) {
			this.data[i] = Arrays.copyOf(data[i], data[i].length);
		}
	}
	
	public void reset() {
		x = 5 - (int)(width/2);
		y = 0;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public boolean isEmptyColumn() {
		return (data[0][0] != 0 | data[1][0] != 0 | data[2][0] != 0 | data[3][0] != 0);
	}
	public boolean isEmptyRow() {
		return (data[0][0] != 0 | data[0][1] != 0 | data[0][2] != 0 | data[0][3] != 0);
	}
	
	public int getX() {
		return x;
	}
	
	public void rotateCW() {
		data = this.rotateCW(data);
		int w = this.length;
		int l = this.width;
		this.width = w;
		this.length = l;
		while(!this.isEmptyColumn()) {
			for(int i = 0; i < 4; i ++) {
				int first = data[i][0];
				data[i][0] = data[i][1];
				data[i][1] = data[i][2];
				data[i][2] = data[i][3];
				data[i][3] = first;
			}
		}
		while(!this.isEmptyRow()) {
			for(int i = 0; i < 4; i ++) {
				int first = data[0][i];
				data[0][i] = data[1][i];
				data[1][i] = data[2][i];
				data[2][i] = data[3][i];
				data[3][i] = first;
			}
		}
	}
	
	public void rotateCCW() {
		rotateCW();
		rotateCW();
		rotateCW();
	}
	
	public int[][] rotateCW(int[][] mat) {
	    final int M = mat.length;
	    final int N = mat[0].length;
	    int[][] ret = new int[N][M];
	    for (int r = 0; r < M; r++) {
	        for (int c = 0; c < N; c++) {
	            ret[c][M-1-r] = mat[r][c];
	        }
	    }
	    return ret;
	}
	
	public int[][] rotateCCW(int[][] mat) {
		final int M = mat.length;
	    final int N = mat[0].length;
	    int[][] ret = new int[N][M];
		for(int i = 0; i < 3; i ++) {
		    for (int r = 0; r < M; r++) {
		        for (int c = 0; c < N; c++) {
		            ret[c][M-1-r] = mat[r][c];
		        }
		    }
		    mat = ret;
		}
	    return ret;
	}

	
	public void setX(int x) {
		if(x > 10 - width) {
			this.x = 10 - width;
		} else if(x < 0) {
			this.x = 0;
		} else {
			this.x = x;
		}
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if(y > 22 - length) {
			this.y = 22 - length;
		} else if(y < 0) {
			this.y = 0;
		} else {
			this.y = y;
		}
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		for(int i = 0; i < 4; i ++) {
			for(int j = 0; j < 4; j ++) {
				if(data[i][j] == this.color) {
					this.data[i][j] = color;
				} else {
					this.data[i][j] = 0;
				}
			}
		}
		this.color = color;
	}

	public int[][] getData() {
		int[][] retVal = new int[4][4];
		for(int i = 0; i < 4; i ++) {
			retVal[i] = Arrays.copyOf(data[i], data[i].length);
		}
		return retVal;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	
	
}
