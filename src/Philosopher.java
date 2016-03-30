import java.util.Random;
import java.util.concurrent.locks.Lock;

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
	private int state;
	
	public static final int THINKING = 1;
	public static final int EATING = 2;
	public static final int HUNGRY = 3;
	
	Random rand = new Random();
	private int noOfMeal = 0;
	
	public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
		this.id = id;
		this.leftChopstick = leftChopstick;
		this.rightChopstick = rightChopstick;
	}


	@Override
	public void run() {
		try{
			while(true){
				think();
				pickUpChopsticks(leftChopstick, rightChopstick);
				eat();
				putDownChopsticks(leftChopstick, rightChopstick);
				
			}
		}catch(InterruptedException e){
			System.out.println("Triết gia "+id+" đã chết đói.");
		}
		
	}
	
	public void think() throws InterruptedException{
		state = THINKING;
		System.out.println(ANSI_YELLOW+"Triết gia " + id + " đang suy nghĩ" + ANSI_RESET);
		Thread.sleep(rand.nextInt(2000)+1000);
		state = HUNGRY;
	}
	
	public void pickUpChopsticks(Chopstick leftChop, Chopstick rightChop) throws InterruptedException{
		System.out.println(ANSI_RED+"Triết gia "+ id+ " đang đói!!!" + ANSI_RESET);
		while(true){
			boolean lc, rc;
			lc = leftChop.tryLock();
			rc = rightChop.tryLock();
			if(lc && rc){
				System.out.println("Triết gia "+ id + " có đủ 2 đũa");
				break;
			}
			else if(lc){
				System.out.println("Triết gia "+id+" có đũa TRÁI nhưng đũa PHẢI bận  ");
				leftChop.unlock();
				System.out.println("Triết gia "+id+" đặt đũa TRÁI xuống");
			}
			else if(rc){
				System.out.println("Triết gia "+id+" có đũa phải nhưng đũa TRÁI bận ");
				rightChop.unlock();
				System.out.println("Triết gia "+id+" đặt đũa PHẢI xuống");
			}
			Thread.sleep(rand.nextInt(2000)+1000);
		}
	}
	
	public void eat() throws InterruptedException {
		noOfMeal = noOfMeal + 1;
		state = EATING;
		System.out.println(ANSI_GREEN + "Triết gia "+id+" đang ăn." + ANSI_RESET);
		leftChopstick.setState(Chopstick.IS_USING);
		rightChopstick.setState(Chopstick.IS_USING);
		Thread.sleep(rand.nextInt(2000)+1000);
	}
	
	public void putDownChopsticks(Lock leftChop, Lock rightChop){
		leftChop.unlock();
		rightChop.unlock();
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
	
	
}
