package bot.interfaces;

public class BotInterface {
	
	public long pause(long exact, boolean isTest) throws InterruptedException {
		
		if(exact < 1)
			exact = 1;
		
		if(!isTest)
			Thread.sleep(exact);
		
		return exact;
		
	}
	
}
