package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class TetrisCanvas extends Canvas{
	private static final long serialVersionUID = -6828443604112305057L;
	public TetrisCanvas() {
		this.setBackground(Color.GRAY);
	}
	Board board = new Board();
	public void draw() {
		int[][] board1 = {
				{1,2,3,4,5,6,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
		};
		BufferedImage image;
		image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g;
		g = image.getGraphics();
		for(int i = 0; i < 22; i ++) {
			for(int j = 0; j < 10; j ++) {
				switch(board1[i][j]) {
				case 1:
					g.setColor(Color.YELLOW);
					break;
				case 2:
					g.setColor(Color.RED);
					break;
				case 3:
					g.setColor(Color.BLUE);
					break;
				case 4: 
					g.setColor(Color.decode("#FF6F00"));
					break;
				case 5:
					g.setColor(Color.GREEN);
					break;
				case 6:
					g.setColor(Color.decode("#e600e6"));
					break;
				default:
					g.setColor(Color.BLACK);
				}
				g.fillRoundRect(j * 52, i * 52, 50, 50, 10, 10);
			}
		}
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
