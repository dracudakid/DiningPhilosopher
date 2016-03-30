import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TableView extends JPanel implements Runnable{

	BufferedImage imgTable, imgChop, imgThink, imgHungry, imgEating;
	BufferedImage imgChop0, imgChop1, imgChop2, imgChop3, imgChop4;
	
	Philosopher[] philosophers;
	
	
	public TableView(Philosopher[] phis) {
		this.philosophers = phis;
		setBackground(Color.WHITE);
		
		try {
			imgTable = ImageIO.read(new File("/home/rayleighs/workspace/DiningPhilosophers/images/table.png"));
			imgChop = ImageIO.read(new File("/home/rayleighs/workspace/DiningPhilosophers/images/chopstick.png"));
			imgChop0 = ImageIO.read(new File("/home/rayleighs/workspace/DiningPhilosophers/images/chopstick0.png"));
			imgChop1 = ImageIO.read(new File("/home/rayleighs/workspace/DiningPhilosophers/images/chopstick1.png"));
			imgChop2 = ImageIO.read(new File("/home/rayleighs/workspace/DiningPhilosophers/images/chopstick2.png"));
			imgChop3 = ImageIO.read(new File("/home/rayleighs/workspace/DiningPhilosophers/images/chopstick3.png"));
			imgChop4 = ImageIO.read(new File("/home/rayleighs/workspace/DiningPhilosophers/images/chopstick4.png"));
			
			imgThink = ImageIO.read(new File("/home/rayleighs/workspace/DiningPhilosophers/images/thinking2.png"));
			imgHungry = ImageIO.read(new File("/home/rayleighs/workspace/DiningPhilosophers/images/hungry2.png"));
			imgEating = ImageIO.read(new File("/home/rayleighs/workspace/DiningPhilosophers/images/eating2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		g.drawImage(imgTable, 0, 0, null);
		for(int i=0; i<philosophers.length;i++){
			drawFace(g, philosophers[i]);
			drawFaceOnTable(g, philosophers[i]);
			setChopPositionOnTable(g, philosophers[i]);
			drawChopsOnTable(g, philosophers[i].getLeftChopstick());
		}
	}
	private void drawChopsOnTable(Graphics g, Chopstick chop){
		switch(chop.getId()){
		case 0:
			g.drawImage(imgChop0, chop.getX(), chop.getY(), 100, 100, null);
			break;
		case 1:
			g.drawImage(imgChop1, chop.getX(), chop.getY(), 100, 100, null);
			break;
		case 2:
			g.drawImage(imgChop2, chop.getX(), chop.getY(), 100, 100, null);
			break;
		case 3:
			g.drawImage(imgChop3, chop.getX(), chop.getY(), 100, 100, null);
			break;
		case 4:
			g.drawImage(imgChop4, chop.getX(), chop.getY(), 100, 100, null);
			break;
		}
	}
	
	/*
	private void drawChopsOnTable(Graphics g, Chopstick chop) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform at;
		AffineTransformOp op ;
		float scale = 0.15f;
		if(chop.getState() != Chopstick.IS_USING)
		switch (chop.getId()) {
		case 0:
			at = AffineTransform.getRotateInstance(Math.toRadians(135), imgChop.getWidth()*scale, imgChop.getHeight()*scale/2);
			at.scale(scale, scale);
			op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			g2d.drawImage(op.filter(imgChop, null), chop.getX(), chop.getY(), null);
			break;
		case 1:
			at = AffineTransform.getRotateInstance(Math.toRadians(45), imgChop.getWidth()*scale, imgChop.getHeight()*scale/2);
			at.scale(scale, scale);
			op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			g2d.drawImage(op.filter(imgChop, null), chop.getX(), chop.getY(), null);
			break;
		case 2:
			at = AffineTransform.getRotateInstance(Math.toRadians(0), imgChop.getWidth()*scale, imgChop.getHeight()*scale/2);
			at.scale(scale, scale);
			op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			g2d.drawImage(op.filter(imgChop, null), chop.getX(), chop.getY(), null);
			break;
		case 3:
			at = AffineTransform.getRotateInstance(Math.toRadians(135), imgChop.getWidth()*scale, imgChop.getHeight()*scale/2);
			at.scale(scale, scale);
			op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			g2d.drawImage(op.filter(imgChop, null), chop.getX(), chop.getY(), null);
			break;
		case 4:
			at = AffineTransform.getRotateInstance(Math.toRadians(45), imgChop.getWidth()*scale, imgChop.getHeight()*scale/2);
			at.scale(scale, scale);
			op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			g2d.drawImage(op.filter(imgChop, null), chop.getX(), chop.getY(), null);
			break;
		default:
			break;
		}
//		g.drawImage(imgChop, chop.getX(), chop.getY(), 20, 100, null);
	}
	 */
	private void setChopPositionOnTable(Graphics g, Philosopher p){
		Chopstick left = p.getLeftChopstick();
		Chopstick right = p.getRightChopstick();
		switch(p.getId()){
		case 0:
			if(p.getState() == Philosopher.EATING){
				left.setPosition(235, 75);
				right.setPosition(285, 75);
			}
			else if(left.getState() != Chopstick.IS_USING){
				// 180, 140
				left.setPosition(180, 140);
			}
			break;
		case 1:
			if(p.getState() == Philosopher.EATING){
				left.setPosition(80, 225);
				right.setPosition(90, 170);
			}
			else if(left.getState() != Chopstick.IS_USING){
				// 110, 360
				left.setPosition(140, 290);
			}
			break;
		case 2:
			if(p.getState() == Philosopher.EATING){
				left.setPosition(170, 400);
				right.setPosition(130, 380);
			}
			else if(left.getState() != Chopstick.IS_USING){
				// 300, 500
				left.setPosition(260, 375);
			}
			break;
		case 3:
			if(p.getState() == Philosopher.EATING){
				left.setPosition(385, 380);
				right.setPosition(350, 400);
			}
			else if(left.getState() != Chopstick.IS_USING){
				// 500, 360
				left.setPosition(390, 285);
			}
			break;
		case 4:
			if(p.getState() == Philosopher.EATING){
				left.setPosition(430, 160);
				right.setPosition(440, 215);
			}
			else if(left.getState() != Chopstick.IS_USING){
				// 420, 140
				left.setPosition(355, 145);
			}
			break;
		}
	}
	private void drawFaceOnTable(Graphics g, Philosopher p){
		int state = p.getState();
		g.setFont(new Font("serif", Font.BOLD, 30));
		switch (p.getId()) {
		case 0:
			// 280, 60
//			g.drawImage(imgThink, 250, 0, 100, 100, null);
			drawStateImage(g, state, 230, 10);
			g.drawString("0", 330, 30);
			break;
		case 1:
//			g.drawImage(imgHungry, 20, 150, 100, 100, null);
			//60, 230
			drawStateImage(g, state, 10, 170);
			g.drawString("1", 0, 180);
			break;
		case 2:
//			g.drawImage(imgEating, 50, 400, 120, 100, null);
			// 160, 500
			drawStateImage(g, state, 110, 450);
			g.drawString("2", 90, 480);
			break;
		case 3:
//			g.drawImage(imgEating, 450, 400, 100, 100,	null);
			// 460, 500
			drawStateImage(g, state, 410, 450);
			g.drawString("3", 510, 470);
			break;
		case 4:
//			g.drawImage(imgHungry, 480, 150, 100, 100, null);
			// 540, 230
			drawStateImage(g, state, 490, 170);
			g.drawString("4", 490, 170);
		default:
			break;
		}
	}
	private void drawFace(Graphics g, Philosopher p){
		g.setColor(Color.BLACK);
		g.setFont(new Font("Serif", Font.ITALIC, 20));
		int left = 600;
		int state = p.getState();
		switch (p.getId()) {
		case 0:
			drawStateImage(g, state, left, 0);
			g.setColor(Color.BLACK);
			g.drawString("Philospher 0", left+100, 20);
			
			drawStateString(g, state, left + 100, 50);
			g.setColor(Color.BLACK);
			g.drawString("No of meal: "+p.getNoOfMeal(), 700, 80);
			break;
		case 1:
			drawStateImage(g, state, left, 120);
			g.setColor(Color.BLACK);
			g.drawString("Philospher 1", left+100, 140);
			
			drawStateString(g, state, left + 100, 170);
			g.setColor(Color.BLACK);
			g.drawString("No of meal: "+p.getNoOfMeal(), 700, 200);
			break;
		case 2:
			drawStateImage(g, state, left, 240);
			g.setColor(Color.BLACK);
			g.drawString("Philospher 2", left+100, 260);
			
			drawStateString(g, state, left + 100, 290);
			g.setColor(Color.BLACK);
			g.drawString("No of meal: "+p.getNoOfMeal(), 700, 320);
			break;
		case 3:
			drawStateImage(g, state, left, 360);
			g.setColor(Color.BLACK);
			g.drawString("Philospher 3", left+100, 380);
			
			drawStateString(g, state, left + 100, 410);
			g.setColor(Color.BLACK);
			g.drawString("No of meal: "+p.getNoOfMeal(), 700, 440);
			break;
		case 4:
			drawStateImage(g, state, left, 480);
			g.setColor(Color.BLACK);
			g.drawString("Philospher 4", left+100, 500);
			
			drawStateString(g, state, left+100, 530);
			g.setColor(Color.BLACK);
			g.drawString("No of meal: "+p.getNoOfMeal(), 700, 560);
			break;
		default:
			break;
		}
		
	}
	private void drawStateImage(Graphics g, int state, int x, int y){
		if(state == Philosopher.EATING){
			g.drawImage(imgEating, x, y, 100, 100, null);
		}
		else if (state == Philosopher.THINKING){
			g.drawImage(imgThink, x, y, 100, 100, null);
		} else {
			g.drawImage(imgHungry, x, y, 100, 100, null);
		}
	}
	
	private void drawStateString(Graphics g, int state, int x, int y){
		if(state == Philosopher.EATING){
			g.setColor(Color.GREEN);
			g.drawString("Eating", x, y );
		}
		else if (state == Philosopher.THINKING){
			g.setColor(Color.BLUE);
			g.drawString("Thinking", x, y);
		} else {
			g.setColor(Color.GREEN);
			g.drawString("Hungry", x, y);
		}
	}
	@Override
	public void run() {
		while(true){
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
