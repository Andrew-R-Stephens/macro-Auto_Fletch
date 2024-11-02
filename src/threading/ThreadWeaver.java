package threading;
import java.util.ArrayList;

public class ThreadWeaver {

	private ArrayList<Thread> threads = new ArrayList<Thread>();
	
	public int createThread(Runnable r) throws InterruptedException {
		
		threads.add(new Thread(r));
		threads.get(threads.size()-1).start();
		
		return threads.size()-1;
		
	}
	
	public boolean destroyThread(int id) {
		
		threads.get(id).interrupt();
		
		return true;
		
	}

	public boolean destroyAllThreads(int threadID) {
		
		for(Thread t: threads)
			t.interrupt();
		
		return true;
		
	}
	
}
