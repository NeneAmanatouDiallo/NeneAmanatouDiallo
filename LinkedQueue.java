
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class LinkedQueue<E> implements QueueInterface<E> {

	private int numElements;
	private Node<E> front;
	private Node<E> back;

	public LinkedQueue() {
		this.numElements = 0;
		this.front = null;
		this.back = null;
	}

	@Override
	public Iterator<E> iterator() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Vector<E> vector = new Vector(this.numElements);

		Node<E> curNode = this.front;
		while (curNode != null) {
			vector.add(curNode.getElement());
			curNode = curNode.getNext();
		}

		return new ElementIterator<E>(vector);
	}

	@Override
	public boolean isEmpty() {
		return (this.numElements == 0);
	}

	@Override
	public int size() {
		return this.numElements;
	}

	@Override
	public QueueInterface<E> copy() {
		// TODO Auto-generated method stub
		
		LinkedQueue<E>CopyList= new LinkedQueue<E>();
		Node<E> curNode= front;
		while (curNode!= null){
        	CopyList.enqueue(curNode.getElement());
            curNode= curNode.getNext();
		}
		
		
		return CopyList;
		//return this;
	}

	@Override
	public void enqueue(E element) throws IllegalStateException, NullPointerException {
		Node<E> newNode = new Node<E>(element);
		if (element == null) {
			throw new NullPointerException("Unable to enqueue an element that is null");
		} else if (this.numElements == 10000) {
			throw new IllegalStateException("ArrayBasedQueue already contains " + this.numElements +
					" elements, unable to add any more elements");
		}
		
		
		if (this.isEmpty()) {
			this.front = newNode;
			this.back = newNode;
		} else {
			this.back.setNext(newNode);
			newNode.setPrevious(this.back);
			this.back = newNode;
		}

		this.numElements++;
	}

	@Override
	public E peek() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.front.getElement();
		}
	}

	@Override
	public E dequeue() {
		if (this.isEmpty()) {
			return null;
		} else {
			E element = this.front.getElement();
			this.front = this.front.getNext();

			if (this.front == null) {
				this.back = null;
			} else {
				this.front.setPrevious(null);
			}

			this.numElements--;
			return element;
		}
	}

	@Override
	public E dequeue(int index) throws NoSuchElementException {
		if ((index < 0) || (index >= this.numElements)) {
			throw new NoSuchElementException(
					"Index = " + index + " is not a valid index for a queue with " + this.numElements + " in it.");
		} else {
			if (index == 0) {
				return dequeue();
			} else if (index == (this.numElements - 1)) {
				E element = this.back.getElement();
				this.back = this.back.getPrevious();
				this.back.setNext(null);
				this.numElements--;
				return element;
			} else {
				Node<E> curNode = this.front.getNext();
				int curIndex = 1;

				while (curIndex != index) {
					curNode = curNode.getNext();
					curIndex++;
				}

				E element = curNode.getElement();
				curNode.getPrevious().setNext(curNode.getNext());
				curNode.getNext().setPrevious(curNode.getPrevious());
				this.numElements--;
				return element;
			}
		}
	}

	@Override
	public void removeAll() {
		this.numElements = 0;
		this.front = null;
		this.back = null;
	}

	@Override
	public String toString() {
		String q = "LinkedQueue - numElements = " + this.numElements + "\n";
		Node<E> curNode = this.front;

		while (curNode != null) {
			q += curNode.getElement();
			if (curNode != this.back) {
				q += " <--> ";
			} else {
				q += "\n";
			}
			curNode = curNode.getNext();
		}

		if (this.isEmpty()) {
			q += "Front = null\n";
			q += "Back  = null\n\n\n";

		} else {
			q += "Front = " + this.front.getElement() + "\n";
			q += "Back  = " + this.back.getElement() + "\n\n\n";
		}

		return q;
	}

	/*public static void main(String[] args) {
		LinkedQueue<String> lq = new LinkedQueue<String>();
		String element;

		for (int i = 1; i <= 10; i++) {
			lq.enqueue("String " + i);
		}
		System.out.println(lq);

		for (int i = 1; i <= 3; i++) {
			element = lq.dequeue();
			System.out.println("Dequeued ELement = " + element);
		}
		System.out.println(lq);

		element = lq.peek();
		System.out.println("Peeked ELement = " + element);
		System.out.println(lq);

		element = lq.dequeue(0);
		System.out.println("Dequeued ELement = " + element);
		System.out.println(lq);

		element = lq.dequeue(5);
		System.out.println("Dequeued ELement = " + element);
		System.out.println(lq);

		element = lq.dequeue(2);
		System.out.println("Dequeued ELement = " + element);
		System.out.println(lq);

		System.out.println("Iterator");
		Iterator<String> iterator = lq.iterator();
		while (iterator.hasNext()) {
			element = iterator.next();
			System.out.println(element);
		}
		System.out.println("\n\n\n");
		
		lq.removeAll();
		System.out.println(lq);
	}
*/
}
