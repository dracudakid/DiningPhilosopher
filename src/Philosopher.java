import java.awt.Color;
import java.util.Random;
import java.util.concurrent.locks.Lock;

import javax.jws.soap.SOAPBinding.Style;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Philosopher implements Runnable {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	private int id;
	private Chopstick leftChopstick;
	private Chopstick rightChopstick;
	private int state = THINKING;
	
	public static final int THINKING = 1;
	public static final int EATING = 2;
	public static final int HUNGRY = 3;
	
	
	
	Random rand = new Random();
	private int noOfMeal = 0;
	private static StyledDocument doc;
	SimpleAttributeSet style = new SimpleAttributeSet();
	public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
		this.id = id;
		this.leftChopstick = leftChopstick;
		this.rightChopstick = rightChopstick;
	}


	@Override
	public void run() {
		try{
			while(true){
				
				try {
					think();
					pickUpChopsticks(leftChopstick, rightChopstick);
					eat();
					putDownChopsticks(leftChopstick, rightChopstick);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				System.out.println(doc.toString());
				
			}
		}catch(InterruptedException e){
			System.out.println("Triết gia "+id+" đã chết đói.");
		}
		
	}
	
	public void think() throws InterruptedException, BadLocationException{
		state = THINKING;
		StyleConstants.setForeground(style, Color.BLUE);
		doc.insertString(doc.getLength(), "Triết gia " + id + " đang suy nghĩ\n", style);
		System.out.println("Triết gia " + id + " đang suy nghĩ");
		Thread.sleep(rand.nextInt(2000)+1000);
		state = HUNGRY;
	}
	
	public void pickUpChopsticks(Chopstick leftChop, Chopstick rightChop) throws InterruptedException, BadLocationException{
		StyleConstants.setForeground(style, Color.ORANGE);
		doc.insertString(doc.getLength(), "Triết gia " + id + " đang đói\n", style);
		System.out.println("Triết gia "+ id+ " đang đói!!!");
		while(true){
			boolean lc, rc;
			lc = leftChop.tryLock();
			rc = rightChop.tryLock();
			if(lc && rc){
				doc.insertString(doc.getLength(), "Triết gia " + id + " có đủ 2 đũa\n", null);
				System.out.println("Triết gia "+ id + " có đủ 2 đũa");
				break;
			}
			else if(lc){
				doc.insertString(doc.getLength(), "Triết gia " + id + " có đũa TRÁI nhưng đũa PHẢI bận  \n", null);
				System.out.println("Triết gia "+id+" có đũa TRÁI nhưng đũa PHẢI bận  ");
				leftChop.unlock();
				doc.insertString(doc.getLength(), "Triết gia " + id + " đặt đũa TRÁI xuống  \n", null);
				System.out.println("Triết gia "+id+" đặt đũa TRÁI xuống");
			}
			else if(rc){
				doc.insertString(doc.getLength(), "Triết gia " + id + " có đũa PHẢI nhưng đũa TRÁI bận  \n", null);
				System.out.println("Triết gia "+id+" có đũa phải nhưng đũa TRÁI bận ");
				rightChop.unlock();
				doc.insertString(doc.getLength(), "Triết gia " + id + " đặt đũa PHẢI xuống  \n", null);
				System.out.println("Triết gia "+id+" đặt đũa PHẢI xuống");
			}
			Thread.sleep(rand.nextInt(2000)+1000);
		}
	}
	
	public void eat() throws InterruptedException, BadLocationException {
		noOfMeal = noOfMeal + 1;
		state = EATING;
		StyleConstants.setForeground(style, Color.GREEN);
		doc.insertString(doc.getLength(), "Triết gia " + id + " đang ăn\n", style);
		System.out.println("Triết gia "+id+" đang ăn.");
		leftChopstick.setState(Chopstick.IS_USING);
		rightChopstick.setState(Chopstick.IS_USING);
		Thread.sleep(rand.nextInt(2000)+1000);
	}
	
	public void putDownChopsticks(Lock leftChop, Lock rightChop) throws BadLocationException{
		leftChop.unlock();
		rightChop.unlock();
		doc.insertString(doc.getLength(), "Triết gia " + id + " đặt 2 đũa xuống\n", null);
		System.out.println("Triết gia "+id+" đặt 2 đũa xuống");
		state = THINKING;
	}


	public int getState() {
		return state;
	}


	public int getId() {
		return id;
	}
	
	public int getNoOfMeal(){
		return noOfMeal;
	}


	public Chopstick getLeftChopstick() {
		return leftChopstick;
	}


	public Chopstick getRightChopstick() {
		return rightChopstick;
	}
	
	public static StyledDocument getDoc() {
		return doc;
	}


	public static void setDoc(StyledDocument doc) {
		Philosopher.doc = doc;
	}
	
	
}
