/**
 * Kernel for Nodes of same graph.
 */
package com.kernel;

import com.math.SquareMatrix;
/**
 * @author aashu
 *
 */

public class NodeMatching {
	private SquareMatrix matrix;
	private final double LAMBDA = 0.85;
	private final int MAX_K = 10;
	
	/**
	 * Constructs a NodeMatching whose Adjacency matrix is 'matrix'
	 * @param matrix Square Matrix
	 */
	public NodeMatching(SquareMatrix matrix){
		this.matrix = matrix;
	}	
	
	/**
	 * Calculate Kernel and return kernel matrix. (i,j)th entry of kernel matrix has value K(Ni, Nj) where Ni is node i and Nj is node j.
	 * @return Kernel Matrix
	 */
	public SquareMatrix calculateKernel(){
		int rows = this.matrix.getN();
		int columns = this.matrix.getN();
		SquareMatrix result= new SquareMatrix(rows);
		int factorial = 1;
		SquareMatrix A = SquareMatrix.identity(rows);
		double lambda_power_k=1;
		for(int k=0;k<MAX_K;k++){			
			if(k!=0) {
				factorial = factorial*k;
				lambda_power_k=lambda_power_k*this.LAMBDA;
				A = A.times(this.getMatrix());
			}			
			double factor = lambda_power_k/factorial;
			for(int i=0;i<rows;i++){
				for(int j =0;j<columns;j++){
					result.setArray(i, j, result.getArray()[i][j] + factor* A.getArray()[i][j]);
				}			
			}
		}		
		double max = result.getArray()[0][0];
		for(int i=1;i<rows;i++){
			if(result.getArray()[i][i]>max) max =result.getArray()[i][i]; 
		}
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				result.getArray()[i][j]/=max;
			}
		}
		for(int i=0;i<rows;i++){
			result.getArray()[i][i] =1;
		}
		return result;
	}
	
	/**
	 * 
	 * @return Adjacency Matrix
	 */
	public SquareMatrix getMatrix() {
		return this.matrix;
	}
	
	/**
	 * updates the Adjacency matrix
	 * @param matrix new Square matrix
	 */
	public void setMatrix(SquareMatrix matrix) {
		this.matrix = matrix;
	}	
}
