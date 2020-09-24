

import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.LongStream;

public class Messenger extends Thread
{
	MessageQueue q;
	Util u;
	public Messenger(MessageQueue q) {
		this.q = q;
		u = new Util();
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 20; i++) {
			String messageString = "Message #" + i;
			q.addMessage(messageString);
			String dateString = u.getDate();
			u.print(dateString + "Messenger - insert '" + messageString + "'");
			long l = ThreadLocalRandom.current().nextLong(1000);
			
			try {
				sleep(l);
			} catch(InterruptedException e){
				continue;
			}
			
		}
		
	}
}
