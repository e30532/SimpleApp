package simple.kafka;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class Hello {

	@Outgoing("test")
	public Message<String> source() {
		String theMsg = "Hello " + new SimpleDateFormat("MMM dd,yyyy HH:mm:ss").format(new Date(System.currentTimeMillis()));
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Message.of(theMsg);
    }
	
	@Incoming("test2")
	@Outgoing("test3")
	public Message<String> consume(Message<String> message) {
		System.out.println(message.getPayload());
		return Message.of(message.getPayload().toUpperCase());
	}
	
	@Incoming("test3")
	public CompletionStage<?> consume2(Message<String> message) {
		System.out.println(message.getPayload());
		return message.ack();
	}

}
