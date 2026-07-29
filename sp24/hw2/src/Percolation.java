import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.lang.reflect.WildcardType;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufFull;
    private boolean[][] grid;
    private int row, col;
    private int numberOfOpened;
    private int virtualTop, virtualBottom;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        row = col = N;
        grid = new boolean[row][col];
        uf = new WeightedQuickUnionUF(row * col + 2);
        ufFull = new WeightedQuickUnionUF(row * col + 1);
        numberOfOpened = 0;
        virtualTop = N * N;
        virtualBottom = N * N + 1;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || row >= this.row || col < 0 || col >= this.col) {
            throw new IndexOutOfBoundsException();
        }
        int currIndex = index(row, col);
        if (!isOpen(row, col)) {
            if (row == 0) {
                uf.union(virtualTop, currIndex);
                ufFull.union(virtualTop, currIndex);
            }
            if (row == this.row - 1) {
                uf.union(virtualBottom, currIndex);
            }
            grid[row][col] = true;
            openHelper(row, col, row - 1, col);
            openHelper(row, col, row + 1, col);
            openHelper(row, col, row, col - 1);
            openHelper(row, col, row, col + 1);
            numberOfOpened++;
        }
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || row >= this.row || col < 0 || col >= this.col) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || row >= this.row || col < 0 || col >= this.col) {
            throw new IndexOutOfBoundsException();
        }
        int currIndex = index(row, col);
        return isOpen(row, col) && ufFull.connected(virtualTop, currIndex);
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return numberOfOpened;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return uf.connected(virtualTop, virtualBottom);
    }

    private int index(int row, int col) {
        return row * this.col + col;
    }

    private void openHelper(int currRow, int currCol, int nearRow, int nearCol) {
        if (nearRow >= 0 && nearRow < this.row && nearCol >= 0 && nearCol < this.col) {
            int currIndex = index(currRow, currCol);
            int nearIndex = index(nearRow, nearCol);
            if (isOpen(nearRow, nearCol)) {
                uf.union(currIndex, nearIndex);
                ufFull.union(currIndex, nearIndex);
            }
        }
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
