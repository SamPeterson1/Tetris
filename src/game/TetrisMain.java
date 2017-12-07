package game;

public class TetrisMain {
	
	
	public static void main(String args[]) {
		TetrisEventQueue queue = new TetrisEventQueue();
		TetrisEvent event = null;
		TetrisFrame frame = new TetrisFrame();
		TetrisCanvas canvas = new TetrisCanvas();
		frame.addCanvas(canvas);
		canvas.addEventQueue(queue);
		while(true) {
			if(queue.isEventToProcess()) {
				event = queue.getEvent();
			}
			canvas.draw();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
