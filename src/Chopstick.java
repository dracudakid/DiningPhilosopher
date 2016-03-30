import java.util.concurrent.locks.ReentrantLock;

public class Chopstick extends ReentrantLock{
	// position of Chopstick;
	public static final int FREE = 0;
	public static final int IS_USING = 1;
	int x, y;
	int id;
	int state = FREE;
	public Chopstick(int id) {
		this.id = id;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setState(int state){
		this.state = state;
	}
	
	public int getState() {
		return state;
	}
	
	@Override
	public boolean tryLock() {
		boolean result = super.tryLock();
		if(result == true) this.setState(IS_USING);
		return result;
	}
	
	@Override
	public void lock() {
		super.lock();
		this.setState(IS_USING);
		
	}
	
	@Override
	public void unlock() {
		super.unlock();
		this.setState(FREE);
		
	}

	public int getId() {
		return id;
	}
	
}
