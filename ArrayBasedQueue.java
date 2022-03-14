import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class ArrayBasedQueue<E> implements QueueInterface<E> {

	private final int DEFAULT_ARRAY_SIZE = 10_000;
	
	private Object[] array;
	private int front;
	private int back;
	private int numElements;
	
	public ArrayBasedQueue() {
		this.array = new Object[this.DEFAULT_ARRAY_SIZE];
		this.front = -1;
		this.back = -1;
		this.numElements = 0;
	}
	
	public ArrayBasedQueue(int queueSize) {
		this();
		if (queueSize > 0) {
			this.array = new Object[queueSize];
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Iterator<E> iterator() {
		Vector<E> elements = new Vector<E>(this.numElements);
		
		for ( int i = this.front, count = 1 ; count <= this.numElements ; i = this.increment(i), count++ ) {
			elements.add((E) this.array[i]);
		}

		return new ElementIterator<E>(elements);
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
		//ArrayBasedList<E> CopyList = new ArrayBasedList<E>();
		ArrayBasedQueue<E> CopyList = new ArrayBasedQueue<E>();
		//ArrayBasedQueue<E> CopyListFinal = new ArrayBasedQueue<E>();
		if(numElements!=0) {
			for(int i=0;i<array.length;i++) {
				@SuppressWarnings("unchecked")
				E numbre= (E) array[i];
				CopyList.enqueue(numbre);
			}
			
		}
		return CopyList;
	}

	@Override
	public void enqueue(E element) throws IllegalStateException, NullPointerException {
		if (element == null) {
			throw new NullPointerException("Unable to enqueue an element that is null");
		} else if (this.numElements == this.array.length) {
			throw new IllegalStateException("ArrayBasedQueue already contains " + this.numElements +
					" elements, unable to add any more elements");
		}

		if (this.isEmpty()) {
			this.array[0] = element;
			this.front = 0;
			this.back = 0;
		} else {
			this.back = this.increment(this.back); 
			this.array[this.back] = element;
		}
		
		this.numElements++;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E peek() {
		if (this.isEmpty()) {
			return null;
		}
		
		E peekedElement = (E) this.array[this.front];
		
		return peekedElement;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public E dequeue() {
		if (this.isEmpty()) {
			return null;
		}
		
		E dequeuedElement = (E) this.array[this.front];
		this.front = increment(this.front);
		
		this.numElements--;
		return dequeuedElement;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E dequeue(int index) throws NoSuchElementException {
		if ((index < 0) || (index >= this.numElements)) {
			throw new NoSuchElementException("ArrayBasedQueue only contains " + this.numElements +
					" elements, index = " + index + " is not a valid index");
		}
		
		int indexToRemove = findIndex(index);
		E dequeuedELement = (E) this.array[indexToRemove];

		while (indexToRemove != this.back) {
			int nextIndex = increment(indexToRemove);
			this.array[indexToRemove] = this.array[nextIndex];
			indexToRemove = nextIndex;
		}
		
		this.back = decrement(this.back);
		
		this.numElements--;
		return dequeuedELement;
	}

	@Override
	public void removeAll() {
		this.array = new Object[this.array.length];
		this.front = -1;
		this.back = -1;
		this.numElements = 0;
	}
	
	@Override
	public String toString() {
		String s = "numElements = " + this.numElements + "\n";
		s += "array = [";
		int num = 1;
		int index = this.front;
		while (num <= this.numElements) {
			s += this.array[index];
			if (num != this.numElements) {
				s += ", ";
			}
			index = increment(index);
			num++;
		}
		s += "]\n";
		s += "Front = " + this.front + "\n";
		s += "Back = " + this.back + "\n\n\n";
		
		return s;
	}

	private int increment(int index) {
		return ((index + 1) % this.array.length);
	}
	
	private int findIndex(int index) {
		return (this.front + index) % this.array.length;
	}
	 
	private int decrement(int index) {
		if (index == 0) {
			return this.array.length - 1;
		} else {
			return index - 1;
		}
	}
	
	
	/*public static void main(String[] args) {
		ArrayBasedQueue<String> abq = new ArrayBasedQueue<String>(12);
		
		for ( int i = 1 ; i < 11 ; i++ ) {
			abq.enqueue("String " + i);
		}
		
		System.out.println(abq);
		
		String element;
		
		element = abq.dequeue();
		
		System.out.println("Dequeued Element = " + element + "\n\n");
		
		System.out.println(abq);
		
		element = abq.dequeue();
		
		System.out.println("Dequeued Element = " + element + "\n\n");
		
		System.out.println(abq);
		
		element = abq.dequeue();
		
		System.out.println("Dequeued Element = " + element + "\n\n");
		
		System.out.println(abq);
		
		for ( int i = 11 ; i <= 15 ; i++ ) {
			abq.enqueue("String " + i);
		}
		
		System.out.println(abq);
		
		try {
			abq.enqueue("String 16");
		} catch (Exception e) {
			System.out.println(e.getMessage() + "\n\n");
		}
		
		element = abq.peek();

		System.out.println("Peeked Element = " + element + "\n\n");
		
		System.out.println(abq);

		
		element = abq.dequeue(4);

		System.out.println("Dequeued Element = " + element + "\n\n");
		
		System.out.println(abq);
		
		
		System.out.println("Iterator");
		
		Iterator<String> iterator = abq.iterator();
		
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		
		ArrayBasedQueue<Integer> abqi = new ArrayBasedQueue<Integer>();
		for ( int i = 1 ; i <= 20 ; i++ ) {
			//abqi.enqueue(new Integer(i));
		}
		
		
		System.out.println("Iterator for Integer");
		
		Iterator<Integer> iteratori = abqi.iterator();
		
		while (iteratori.hasNext()) {
			Integer i = iteratori.next();
			System.out.println(i);
			i += 1;
		}
	}
	*/
	
}

