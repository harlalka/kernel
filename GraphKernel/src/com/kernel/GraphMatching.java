/**
 * Kernel for Graph
 */
package com.kernel;

import com.math.SquareMatrix;

/**
 * @author aashu
 *
 */

public class GraphMatching{
	static final double discount = 0.7;
	double[] discountFactor;
	static final int Kmax= 3;
	SquareMatrix A=null, B=null;
	double[][] degreeA, degreeB;
	int degree;
	/**
	 * Constructor
	 * @param A Square Matrix representing First Graph
	 * @param B Square MAtrix representing second Graph.
	 */
	public GraphMatching(SquareMatrix A, SquareMatrix B){
		this.A = A;
		this.B = B;
		int x = java.lang.Math.max(A.getArray().length, B.getArray().length);
		degree =x;
		degreeA = new double[Kmax][x];
		degreeB = new double[Kmax][x];
		calculateDegreeVector(1);
		calculateDegreeVector(2);
		discountFactor = new double[Kmax];
		discountFactor[0]=1;
		for(int i=1;i<Kmax;i++){
			discountFactor[i] = discountFactor[i-1]*discount;
		}
		makeHistogram();
	}
	
	/**
	 * 
	 * @param A Square matrix
	 * @return an array containing row sum i.e. ith element of array contains - counts no. of distinct +ve entries in a row ignoring diagonal entries and 
	 */
	private double[] rowSum(SquareMatrix A){
		int x = A.getArray().length;
		double result[] = new double[this.degree];
		for(int i=0;i<x;i++){
			for(int j=0;j<x;j++){
				if(i!=j && A.getArray()[i][j] >0)
					result[i]++;
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param X Square matrix
	 * @return Square matrix with all diagonal entries =0 and other entries equal to that of X
	 */
	private SquareMatrix diagonalZeroMatrix(SquareMatrix X){
		for(int i=0;i<X.getArray().length;i++){
			X.getArray()[i][i] = 0;
		}
		return X;		
	}
	/**
	 * sets the degreeA or degreeB double array. degree[i] is rowSum matrix of A^i 
	 * (where we take diagonal entries to be 0 every time to calculate A^i, i.e. zerodiagonalentries(A^(i-1)xA and then make diagonal entries 0 again))
	 * @param a integer 1-> First Graph is to be used, 2 -> Second Graph is to be used
	 */
	private void calculateDegreeVector(int a){
		SquareMatrix X;
		if (a==1) X = A;
		else X =B;
		for(int i=0;i<Kmax;i++){
			X = diagonalZeroMatrix(X);
			if(a==1) degreeA[i] = rowSum(X);
			else degreeB[i] = rowSum(X);
			X = X.times(X);
		}
	}
	
	/**
	 * Calculates histogram details of both graphs A and b.
	 * degree[i] vector contains ith-order-degree for each node.
	 */
	private void makeHistogram(){
		double[][] A1 = degreeA;
		double[][] B1 = degreeB;
		degreeA = new double[Kmax][degree];
		degreeB = new double[Kmax][degree];
		for(int i =0;i<Kmax;i++){
			for(int j=0;j<degree;j++){
				degreeA[i][(int)A1[i][j]]++;
				degreeB[i][(int)B1[i][j]]++;
			}
			double sumA =0, sumB=0;
			for(int j=0;j<degree;j++){
				sumA+=degreeA[i][j]*degreeA[i][j];
				sumB+=degreeB[i][j]*degreeB[i][j];
			}
			sumA = java.lang.Math.sqrt(sumA);
			sumB = java.lang.Math.sqrt(sumB);
			for(int j=0;j<degree;j++){
				degreeA[i][j]/=sumA;
				degreeB[i][j]/=sumB;
			}			
		}
	}
	/**
	 * Calculates dot product of degreeA and degreeB
	 * @return return  vector whose ith entry is doth product of degreeA[i] and degreeB[i]
	 */
	private double[] dotProduct(){
		double[] result = new double[Kmax];
		for(int i=0;i<Kmax;i++){
			for(int j=0;j<degree;j++){
				result[i]+=degreeA[i][j]*degreeB[i][j];
			}
		}
		return result;
	}
	
	/**
	 * Using histogram of every-order-degrees of graph and applying appropriate discounting factor, It calculates matching percentage. 
	 * @return Matching percentage of given two graphs/matrix
	 */
	public double matching(){
		double[] dotProduct = dotProduct();
		double sum =0, weight=0;
		for(int i=0;i<Kmax;i++){
			sum+=dotProduct[i]*discountFactor[i];
			weight+=discountFactor[i];
		}
		return sum/weight;
	}
	
}
