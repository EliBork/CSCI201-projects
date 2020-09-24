

import java.util.concurrent.*;

public class Subscriber extends Thread
{
	MessageQueue q;
	Util u;
	public Subscriber(MessageQueue q) {
		u = new Util();
		this.q = q;
	}
	
	@Override
	public void run() {
		int i = 0;
		while(i < 20) {
			String s = q.getMessage();
			if(s.compareTo("") != 0) {
				i++;
				String dateString = u.getDate();
				u.print(dateString + "Subscriber - read '" + s + "'");
			}
			
			long l = ThreadLocalRandom.current().nextLong(1000);
			try {
				sleep(l);
			} catch(InterruptedException e){
				continue;
			}
		}
	}
}
