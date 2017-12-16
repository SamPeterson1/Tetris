package game;

import java.util.Arrays;

public class TetrisMain {
	
	
	public static void main(String args[]) {
		TetrisEventQueue queue = new TetrisEventQueue();
		TetrisEvent event = null;
		TetrisFrame frame = new TetrisFrame();
		TetrisCanvas canvas = new TetrisCanvas();
		Board board = new Board(canvas);
		Predictor p = new Predictor();
		canvas.setBoard(board);
		frame.addCanvas(canvas);
		canvas.addEventQueue(queue);
		Piece active = p.nextPiece();
		canvas.setPredictor(p);
		int i = 0;
		while(true) {
			if(queue.isEventToProcess()) {
				event = queue.getEvent();
				if(event.getType() == TetrisEvent.EVENT_KEY_PRESS & (event.getKeyChar() == 'a' | event.getKeyChar() == 'k')) {
					if(!board.overlaps(active, active.getX() - 1, active.getY())) {
						active.setX(active.getX() - 1);
					}
				} else if(event.getType() == TetrisEvent.EVENT_KEY_PRESS & (event.getKeyChar() == 'd' | event.getKeyChar() == ';')) {
					if(!board.overlaps(active, active.getX() + 1, active.getY())) {
						active.setX(active.getX() + 1);
					}
				} else if(event.getType() == TetrisEvent.EVENT_KEY_PRESS & (event.getKeyChar() == 'z' | event.getKeyChar() == ',')) {
					if(!board.overlaps(active, active.getX(), active.getY() + 1)) {
						active.setY(active.getY() + 1);
					}
				} else if(event.getType() == TetrisEvent.EVENT_KEY_PRESS & (event.getKeyChar() == 'q' |event.getKeyChar() == 'i')) {
					active.rotateCW();
					if(board.overlaps(active, active.getX(), active.getY())) {
						active.rotateCCW();
						board.reset();
						board.setBoard(board.addPiece(active, board.getBoard()));
					} else {
						board.reset();
						board.setBoard(board.addPiece(active, board.getBoard()));
					}
				} else if(event.getType() == TetrisEvent.EVENT_KEY_PRESS & (event.getKeyChar() == 'w' | event.getKeyChar() == 'o')) {
					active.rotateCCW();
					if(board.overlaps(active, active.getX(), active.getY())) {
						active.rotateCW();
						board.reset();
						board.setBoard(board.addPiece(active, board.getBoard()));
					} else {
						board.reset();
						board.setBoard(board.addPiece(active, board.getBoard()));
					}
				} else if(event.getType() == TetrisEvent.EVENT_KEY_PRESS & (event.getKeyChar() == 's' | event.getKeyChar() == 'l')) {
					int repetitions = 0;
					while(!board.overlaps(active, active.getX(), active.getY() + 1) & !(active.getY() + active.getLength() == 21)) {
						active.setY(active.getY() + 1);
						if(repetitions > 30) {
							break;
						}
						if(board.getBoard().equals(new int[22][10])) {
							break;
						}
						System.out.println("HI");
						repetitions ++;
					}
				}
			}
			System.out.println(board.getGap(0));
			System.out.println("");
			board.checkForTetris();
			if(!board.updatePieceBoard(active, false)) {
				active.reset();
				active = p.nextPiece();
				if(board.overlaps(active, active.getX(), active.getY())) {
					break;
				}
			}
			canvas.draw();
			i ++;
			if(i == board.getSpeed(10)) {
				i = 0;
				if(!board.updatePieceBoard(active, true)) {
					active.reset();
					active = p.nextPiece();
					if(board.overlaps(active, active.getX(), active.getY())) {
						break;
					}
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("BREAK");
	}
	public static void print(int[][] data) {
		for(int i = 0; i < data.length; i ++) {
			System.out.println("");
			for(int j = 0; j < data[1].length; j ++) {
				System.out.print(data[i][j]);
			}
		}
		System.out.println(" ");
	}
	
}
