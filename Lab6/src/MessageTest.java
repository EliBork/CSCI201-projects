

import java.util.concurrent.*;

public class MessageTest
{
	public static void main(String [] args)
	{
		MessageQueue q = new MessageQueue();
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.execute(new Messenger(q));
		executorService.execute(new Subscriber(q));
		executorService.shutdown();
		
	}
}
