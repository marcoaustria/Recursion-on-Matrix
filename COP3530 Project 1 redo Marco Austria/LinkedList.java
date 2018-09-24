// Marco Austria project 1 redo

public class LinkedList {

	private Node head;
	private Node tail;
	private int numRows;
	private int numCols;

	LinkedList() {

		head = null;
		tail = null;
		numRows = 0;
		numCols = 0;

	}

	public void add(int row, int col, int value) {

		// if empty list
		if (head == null && value != 0) {
			Node newNode = new Node(row, col, value);
			newNode.next = head;
			head = newNode;
			tail = newNode;
		}

		else if (head == null && value == 0)
			;
		// do nothing

		// if add to head
		else if ((head.row > row || (head.row == row && head.col > col)) && value != 0) {
			Node newNode = new Node(row, col, value);
			newNode.next = head;
			head = newNode;
		}

		else {

			Node curr = head;
			while (true) {

				// if match
				if (curr.row == row && curr.col == col) {

					if (value == 0) {

						remove(row, col);
						break;
					}

					curr.value = value;

					break;
				}

				if (value == 0)
					break;

				// if there are element(s) in same row, add after
				if (curr.row == row) {
					if (curr.col < col && (curr.next == null || curr.next.col > col || curr.next.row > row)) {
						Node newNode = new Node(row, col, value);
						newNode.next = curr.next;
						curr.next = newNode;
						if (newNode.next == null) {
							tail = newNode;
						}
						break;
					}
				}

				// if none with same row
				if (curr.next != null && curr.next.row > row) {
					Node newNode = new Node(row, col, value);
					newNode.next = curr.next;
					curr.next = newNode;
					break;
				}

				// add to end of list
				if (curr.next == null) {
					tail.next = new Node(row, col, value);
					tail = tail.next;
					break;
				}

				else
					curr = curr.next;
			} // while

		} // else

	} // function

	public void remove(int row, int col) {

		if (isEmpty()) {
			throw new RuntimeException("LinkedList.remove(): list is empty.");
		}

		Node curr = head;
		while (curr != null) {

			// if head
			if (curr.row == row && curr.col == col) {
				head = curr.next;
				break;
			}

			// if in middle of list
			if (curr.next.row == row && curr.next.col == col) {
				curr.next = curr.next.next;
				break;
			}

			// if not found
			if (curr.next == null) {
				throw new RuntimeException("LinkedList.remove(): element does not exist.");
			}

			curr = curr.next;
		}

	}

	public int getElement(int row, int col) {

		// empty list
		if (head == null)
			return 0;

		Node curr = head;
		while (curr != null) {

			if (curr.row == row && curr.col == col) {
				return curr.value;
			}

			// if not found
			if (curr.next == null) {
				return 0;
			}

			curr = curr.next;
		}

		// error
		return -100000;
	}

	public void clear() {
		head = null;
		tail = null;
		numRows = 0;
		numCols = 0;

	}

	public boolean isEmpty() {

		return (head == null) ? true : false;

	}

	public void setSize(int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
	}

	public int getRows() {
		return numRows;
	}

	public int getCols() {
		return numCols;
	}

	public String toString() {
		String returnString = "";

		if (isEmpty()) {
			return returnString;
		}

		Node curr = head;

		while (curr != null) {

			returnString += curr.row + " " + curr.col + " " + curr.value + "\n";

			curr = curr.next;
		}

		return returnString;
	}

}
