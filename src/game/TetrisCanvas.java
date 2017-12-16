package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class TetrisCanvas extends Canvas{
	private static final long serialVersionUID = -6828443604112305057L;
	public TetrisCanvas() {
		this.setBackground(Color.GRAY);
	}
	Board board = new Board(this);
	Predictor p;
	public void draw() {
		int[][] board1 = board.getBoard();
		int[][] board2 = board.getStaticBoard();
		BufferedImage image;
		image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g;
		g = image.getGraphics();
		Font large = new Font("Helvectica", Font.BOLD, 100);
		Font small = new Font("Helvectica", Font.BOLD, 25);
		drawMatrix(g, 0, 0, board1, board2, true);
		g.setColor(Color.BLACK);
		g.setFont(large);
		g.drawString("TETRIS", 550, 300);
		Integer i = board.score;
		g.fillRect(550, 20, 200, 110);
		g.setColor(Color.BLACK);
		g.fillRect(550, 400, 208, 200);
		g.fillRect(550, 800, 208, 200);
		drawMatrix(g, 550,400, p.getNextPiece(0, this),  p.getNextPiece(0, this), false);
		drawMatrix(g, 550,800, p.getNextPiece(1, this),  p.getNextPiece(1, this), false);
		g.setColor(Color.BLACK);
		g.setFont(small);
		g.drawString("Next Piece", 760, 500);
		g.drawString("Coming Piece", 760, 900);
		g.setColor(Color.RED);
		g.setFont(large);
		g.drawString(i.toString(), 550, 107);
		g.dispose();
		g = this.getGraphics();
		g.drawImage(image, 0, 0, null); 
	}
	public void addEventQueue(TetrisEventQueue queue)  {

		this.addKeyListener(queue);
		this.addMouseListener(queue);
		this.addMouseMotionListener(queue);

		return;
	}
	
	public void drawMatrix(Graphics g, int x, int y, int[][] board1, int[][] board2, boolean doBlack) {
		for(int i = 0; i < board1.length; i ++) {
			for(int j = 0; j < board1[0].length; j ++) {
				boolean drewBlack = false;
				if(board1[i][j] == 1 | board2[i][j] == 1) {
					g.setColor(Color.YELLOW);
				}
				else if(board1[i][j] == 2 | board2[i][j] == 2) {
					g.setColor(Color.RED);
				}
				else if(board1[i][j] == 3 | board2[i][j] == 3) {
					g.setColor(Color.BLUE);
				}
				else if(board1[i][j] == 4 | board2[i][j] == 4) {
					g.setColor(Color.decode("#FF6F00"));
				}
				else if(board1[i][j] == 5 | board2[i][j] == 5) {
					g.setColor(Color.GREEN);
				}
				else if(board1[i][j] == 6 | board2[i][j] == 6) {
					g.setColor(Color.decode("#e600e6"));
				} else if(board1[i][j] == 7 | board2[i][j] == 7){
					g.setColor(Color.CYAN);
				} else if(doBlack){
					g.setColor(Color.BLACK);
				} else {
					g.setColor(Color.BLACK);
					g.fillRect(j * 52 + x, i * 52 + y, 52, 52);
					drewBlack = true;
				}
				if((doBlack) | (!doBlack & !drewBlack)) {
					g.fillRoundRect(j * 52 + x, i * 52 + y, 50, 50, 10, 10);
				}
			}
		}
	}
	
	public void setPredictor(Predictor p) {
		this.p = p;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	/*
	╭━━━━╮               
	╰┃ ┣▇━▇                
	 ┃ ┃  ╰━▅╮ 
	 ╰┳╯ ╰━━┳╯        
	  ╰╮ ┳━━╯          
	 ▕▔▋ ╰╮╭━╮   
	╱▔╲▋╰━┻┻╮╲╱▔▔▔╲ 
	▏  ▔▔▔▔▔▔▔  O O┃ 
	╲╱▔╲▂▂▂▂╱▔╲▂▂▂╱
	 ▏╳▕▇▇▕ ▏╳▕▇▇▕
	 ╲▂╱╲▂╱ ╲▂╱╲﻿
	*/
}
