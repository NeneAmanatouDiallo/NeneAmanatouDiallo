import java.util.Iterator;
import java.util.Vector;


public class ElementIterator<E> implements Iterator<E> {

	Vector<E> elements;
	
	public ElementIterator(Vector<E> elements) {
		this.elements = elements;
	}
	
	
	@Override
	public boolean hasNext() {
		return !elements.isEmpty();
	}

	@Override
	public E next() {
		return elements.remove(0);
	}
	@Override
	public void remove() {
		
	}
	
	

}

