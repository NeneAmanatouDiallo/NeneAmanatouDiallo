
public class Node<E> {

	private Node<E> previous;
	private E element;
	private Node<E> next;
	
	public Node() {
		this.previous = null;
		this.element = null;
		this.next = null;
	}
	
	public Node(E element) {
		this();
		this.element = element;
	}
	public Node(Node<E> newPrevious, E element, Node<E> newNext) {
		this.element = element;
		this.next = newNext;
		this.previous= newPrevious;
	}
	/*no suitable constructor found for Node(Node<String>,String,Node<String>)
			node = new Node<String>(newPrevious, new String("String X"), newNext);
			       ^
	 * */
	public Node<E> getPrevious() {
		return previous;
	}
	public void setPrevious(Node<E> previous) {
		this.previous = previous;
	}
	public E getElement() {
		return element;
	}
	public void setElement(E element) {
		this.element = element;
	}
	public Node<E> getNext() {
		return next;
	}
	public void setNext(Node<E> next) {
		this.next = next;
	}
}

