//Marco Austria
//project 1 redo

public class SparseMatrix implements SparseInterface {

	LinkedList list = new LinkedList();

	/*
	 * Should clear the matrix of all entries (make all entries 0)
	 */
	public void clear() {
		list.clear();
	}

	/* Sets size of the matrix. Number of rows, number of columns. */
	public void setSize(int numRows, int numCols) {
		list.setSize(numRows, numCols);
	}

	public int getNumRows() {
		return list.getRows();
	}

	public int getNumCols() {
		return list.getCols();
	}

	/*
	 * Adds an element to the row and column passed as arguments (overwrites if
	 * element is already present at that position). Throws an error if row/column
	 * combination is out of bounds. Checks to see if element has a value of zero
	 * before creating
	 */
	public void addElement(int row, int col, int data) {

		if (row < 0 || col < 0 || row > list.getRows() || col > list.getCols()) {

			throw new RuntimeException("SparseMatrix.addElement(): row and/or column is out of bounds.");

		}

		list.add(row, col, data);

	}

	/*
	 * Remove (make 0) the element at the specified row and column. Throws an error
	 * if row/column combination is out of bounds.
	 */
	public void removeElement(int row, int col) {

		if (row < 0 || col < 0 || row > list.getRows() || col > list.getCols()) {

			throw new RuntimeException("SparseMatrix.removeElement(): row and/or column is out of bounds.");

		}

		list.remove(row, col);

	}

	/*
	 * Return the element at the specified row and column Throws an error if
	 * row/column combination is out of bounds.
	 */
	public int getElement(int row, int col) {
		if (row < 0 || col < 0 || row > list.getRows() || col > list.getCols()) {

			throw new RuntimeException("SparseMatrix.getElement(): row and/or column is out of bounds.");

		}

		return list.getElement(row, col);
	}

	/*
	 * Should return the nonzero elements of your sparse matrix as a string. The
	 * String should be k lines, where k is the number of nonzero elements. Each
	 * line should be in the format "row column data" where row and column are the
	 * "coordinate" of the data and all are separated by spaces. An empty matrix
	 * should return an empty string. The print should be from left to right and
	 * from top to bottom (like reading a book) i.e. the matrix 3 0 1 0 2 0 0 0 4
	 * Should print as: 0 0 3 0 2 1 1. 1 2 2. 2 4
	 */
	public String toString() {

		return list.toString();

	}

	/* takes another matrix as input and returns the sum of the two matrices */
	/* return NULL if sizes incompatible */
	public SparseInterface addMatrices(SparseInterface matrixToAdd) {

		if (getNumCols() != matrixToAdd.getNumCols() || getNumRows() != matrixToAdd.getNumRows()) {
			return null;
		}

		int numCols = getNumCols();
		int numRows = getNumRows();

		SparseInterface returnMatrix = new SparseMatrix();
		returnMatrix.setSize(numRows, numCols);

		int listLength = numRows * numCols;
		int rowCount = 0;
		int colCount = 0;

		for (int i = 0; i < listLength; i++) {

			returnMatrix.addElement(rowCount, colCount,
					getElement(rowCount, colCount) + matrixToAdd.getElement(rowCount, colCount));

			++colCount;

			// last column, change row, reset col
			if (colCount == numCols) {
				colCount = 0;
				rowCount++;
			}

		}

		return returnMatrix;
	}

	/* takes another matrix as input and returns the product of the two matrices */
	/* return NULL if sizes incompatible */
	public SparseInterface multiplyMatrices(SparseInterface matrixToMultiply) {

		if (getNumCols() != matrixToMultiply.getNumRows()) {
			return null;
		}

		int numRows1 = getNumRows();
		int numCols1 = getNumCols();
		int numCols2 = matrixToMultiply.getNumCols();

		SparseInterface returnMatrix = new SparseMatrix();
		returnMatrix.setSize(numRows1, numCols2);

		for (int i = 0; i < numRows1; i++) {
			for (int j = 0; j < numCols2; j++) {
				int sum = 0;
				for (int k = 0; k < numCols1; k++) {
					sum += getElement(i, k) * matrixToMultiply.getElement(k, j);
				}
				returnMatrix.addElement(i, j, sum);
			} // j
		} // i

		return returnMatrix;
	}

}
