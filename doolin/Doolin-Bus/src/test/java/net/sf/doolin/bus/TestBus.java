/*
 * Created on Nov 7, 2006
 */
package net.sf.doolin.bus;

import java.util.Collection;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.bus.Priority;
import net.sf.doolin.bus.Subscriber;

import junit.framework.TestCase;

/**
 * Unit tests for the bus.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TestBus.java,v 1.2 2007/07/31 15:33:12 guinnessman Exp $
 */
public class TestBus extends TestCase {

	/**
	 * Tests existency of the default bus.
	 */
	public void testBus() {
		Bus bus = Bus.get();
		assertNotNull(bus);
	}

	/**
	 * Tests the subscription.
	 */
	public void testSubscription() {
		Bus bus = Bus.get();

		// Subscribe
		TestSubscriber subscriber = new TestSubscriber("A");
		bus.subscribe(TestMessage.class, subscriber);
		assertEquals(0, subscriber.getCount());

		// State of the bus
		Collection<Subscriber<TestMessage>> subscribers = bus.getSubscribers(TestMessage.class);
		assertNotNull(subscribers);
		assertEquals(1, subscribers.size());
		assertEquals(subscriber, subscribers.iterator().next());
	}

	/**
	 * Tests the simple publication.
	 */
	public void testSinglePublication() {
		Bus bus = Bus.get();

		// Subscribe
		TestSubscriber subscriber = new TestSubscriber("A");
		bus.subscribe(TestMessage.class, subscriber);
		assertEquals(0, subscriber.getCount());

		// Publication
		TestMessage message = new TestMessage();
		bus.publish(message);

		// Tests the status
		assertEquals(1, subscriber.getCount());
		assertEquals("A", message.getStatus());
	}

	/**
	 * Tests the multiple publication.
	 */
	public void testMultiplePublication() {
		Bus bus = Bus.get();

		// Subscribe A
		TestSubscriber subscriberA = new TestSubscriber("A");
		bus.subscribe(TestMessage.class, subscriberA);
		assertEquals(0, subscriberA.getCount());

		// Subscribe B
		TestSubscriber subscriberB = new TestSubscriber("B");
		bus.subscribe(TestMessage.class, subscriberB);
		assertEquals(0, subscriberB.getCount());

		// Publication
		TestMessage message = new TestMessage();
		bus.publish(message);

		// Tests the status
		assertEquals(1, subscriberA.getCount());
		assertEquals(1, subscriberB.getCount());
	}

	/**
	 * Tests the ordered publication.
	 */
	public void testOrderedPublication() {
		Bus bus = Bus.get();

		/*
		 * A is received the message before B because its priority is higher.
		 */

		// Subscribe A
		TestSubscriber subscriberA = new TestSubscriber("A");
		bus.subscribe(TestMessage.class, subscriberA, Priority.HIGH);
		assertEquals(0, subscriberA.getCount());

		// Subscribe B
		TestSubscriber subscriberB = new TestSubscriber("B");
		bus.subscribe(TestMessage.class, subscriberB, Priority.NORMAL);
		assertEquals(0, subscriberB.getCount());

		// Publication
		TestMessage message = new TestMessage();
		bus.publish(message);

		// Tests the status
		assertEquals(1, subscriberA.getCount());
		assertEquals(1, subscriberB.getCount());
		assertEquals("B", message.getStatus());
	}

	private final class TestSubscriber implements Subscriber<TestMessage> {
		private String status;

		private int count = 0;

		/**
		 * @param status
		 */
		public TestSubscriber(String status) {
			this.status = status;
		}

		public void receive(TestMessage message) {
			count++;
			message.setStatus(status);
		}

		/**
		 * @return Count of received messages
		 */
		public int getCount() {
			return count;
		}
	}

	/**
	 * @author Damien Coraboeuf
	 * @version $Id: TestBus.java,v 1.2 2007/07/31 15:33:12 guinnessman Exp $
	 */
	public static class TestMessage {
		private String status;

		/**
		 * @return Status
		 */
		public String getStatus() {
			return status;
		}

		/**
		 * @param status
		 */
		public void setStatus(String status) {
			this.status = status;
		}
	}

}
