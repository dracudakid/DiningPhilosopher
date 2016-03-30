
public class DiningPhilosopher {
	Chopstick[] chopsticks = new Chopstick[5];
	Philosopher[] philosophers = new Philosopher[5];
	
	
	
	public DiningPhilosopher() {
		
		for(int i = 0; i<5 ;i++){
			chopsticks[i] = new Chopstick(i);
		}
		philosophers[0] = new Philosopher(0, chopsticks[0], chopsticks[4]);
		philosophers[1] = new Philosopher(1, chopsticks[1], chopsticks[0]);
		philosophers[2] = new Philosopher(2, chopsticks[2], chopsticks[1]);
		philosophers[3] = new Philosopher(3, chopsticks[3], chopsticks[2]);
		philosophers[4] = new Philosopher(4, chopsticks[4], chopsticks[3]);
		
		new View(philosophers);
		for(int i=0; i<philosophers.length; i++){
			new Thread(philosophers[i]).start();
		}
	}



	public static void main(String[] args) {
		new DiningPhilosopher();
	}
}
