package com.vb.robot.mover.model;

/**
 * @author vivek.bharti
 *
 */
public class Board {
	
	int rows;
    int columns;

    /**
     * @param rows
     * @param columns
     */
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * @return
     */
    public int getRows() {
		return rows;
	}

	/**
	 * @param rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @return
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}
}
