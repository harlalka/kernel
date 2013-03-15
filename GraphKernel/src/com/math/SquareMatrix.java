/**
 * Class for Square matrix containing operations on Square Matrix.
 */
package com.math;

/**
 * @author aashu
 *
 */

public class SquareMatrix {
	private double A[][];
	private int N;
	/**
	 * Constructor - Constructs a n x n square matrix.
	 * @param n Number of Rows
	 */
	public SquareMatrix(int n){
		A = new double[n][n];
		N=n;
	}
	
	/**
	 * Copy Constructor
	 * @param A Square Matrix
	 */
    private SquareMatrix(SquareMatrix matrix) { 
    	this(matrix.getArray()); 
    }
	
    /**
     * Create Square Matrix with array A
     * @param A Array storing values of Matrix
     */
    public SquareMatrix(double[][] A) {
        N = A[0].length;
        this.A = new double[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                    this.A[i][j] = A[i][j];
    }
	
    /**
     * 
     * @return Array storing the matrix
     */
	public double[][] getArray(){
		return A;
	}
	
	/**
	 * Sets the (i,j) entry of Array to val
	 * @param i ith position in the array starting from 0
	 * @param j jth position in the array starting from 0
	 * @param val value which we want to update
	 */
	public void setArray(int i, int j, double val){
		this.A[i][j] = val;
	}
	
	/**
	 * Updates the Matrix with values that of A
	 * @param A New Array storing the matrix
	 */
	public void setArray(double[][] A){
		this.A = A;
	}
	
	/**
	 * returns number of columns/rows
	 * @return Number of Rows/Columns
	 */
	public int getN() {
		return N;
	}
	/**
	 * updates the number of rows
	 * @param n Number of rows
	 */
	public void setN(int n) {
		N = n;
	}
	
	/**
	 * returns determinant of square matrix
	 * @return determinant of square matrix
	 */
	public double determinant(){
		return determinant(this.A, N);
	}
	
	private double determinant(double[][] A, int N){		
		double res=0;
		if(N == 1)	res = A[0][0];
		else if (N == 2){
			res = A[0][0]*A[1][1] - A[1][0]*A[0][1];
		}
		else{
			res=0;
			for(int j1=0;j1<N;j1++){
				double m[][] = new double[N-1][];
				for(int k=0;k<(N-1);k++)  m[k] = new double[N-1];
				for(int i=1;i<N;i++){
					int j2=0;
					for(int j=0;j<N;j++){
					  if(j == j1)  continue;
					  m[i-1][j2] = A[i][j];
					  j2++;
					}
				}
				 res += Math.pow(-1.0,1.0+j1+1.0)* A[0][j1] * determinant(m,N-1);		
			}
		}
		return res;
		}

	/**
	 * return NxN identity matrix
	 * @param N Number of rows
	 * @return N x N Identity Matrix
	 */
    public static SquareMatrix identity(int N) {
        SquareMatrix I = new SquareMatrix(N);
        for (int i = 0; i < N; i++)
            I.setArray(i, i, 1);
        return I;
    }
    
    /**
     * Create Another Square matrix whose all entries are being multiplied by constant a.
     * @param a multiplier
     * @return Another Square Matrix whose all entries are being multiplies by constant a
     */
    public SquareMatrix multiplyByConstant(double a){
    	SquareMatrix result = new SquareMatrix(this.A.length);
    	for(int i =0;i<this.A.length;i++){
    		for(int j=0;j<this.A[0].length;j++){
    			result.setArray(i,j, a*this.A[i][j]);
    		}
    	}
    	return result;
    }
    
    /**
     * Swap rows i and j
     * @param i ith row
     * @param j jth row
     */
    public void swap(int i, int j) {
        double[] temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /**
     * 
     * @return transpose of the matrix
     */
    public SquareMatrix transpose() {
        SquareMatrix matrix = new SquareMatrix(N);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                matrix.setArray(j,i, this.A[i][j]);
        return matrix;
    }
    
    /**
     * 
     * @param B Square Matrix
     * @return Another Matrix whose entries are sum of invoking matrix and B
     */
    public SquareMatrix plus(SquareMatrix B) {
        if (B.getN() != N) throw new RuntimeException("Illegal matrix dimensions.");
        SquareMatrix C = new SquareMatrix(N);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                C.setArray(i,j , A[i][j] + B.getArray()[i][j]);
        return C;
    }


    /**
     * 
     * @param B Square Matrix
     * @return Another Matrix whose entries are difference of invoking matrix and B i.e. invoking - B
     */
    public SquareMatrix minus(SquareMatrix B) {
        if (B.getN() != N) throw new RuntimeException("Illegal matrix dimensions.");
        SquareMatrix C = new SquareMatrix(N);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                C.setArray(i,j, A[i][j] - B.getArray()[i][j]);
        return C;
    }
    
    /**
     * Checks whether invoking matrix equals B 
     * @param B Square Matrix
     * @return true if invoking matrix equals B, returns false otherwise
     */
    public boolean eq(SquareMatrix B) {
        if (B.getN() != N) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (A[i][j] != B.getArray()[i][j]) return false;
        return true;
    }
    
    /**
     * 
     * @param B Square Matrix
     * @return Another Matrix which is product of two matrix (invoking matrix and B)
     */
    public SquareMatrix times(SquareMatrix B) {
        if (N != B.getN()) throw new RuntimeException("Illegal matrix dimensions.");
        SquareMatrix C = new SquareMatrix(B.getN());
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                   C.setArray(i, j, C.getArray()[i][j] + (A[i][k] * B.getArray()[k][j]));
        return C;
    }

    /**
     * Print matrix to Standard Output
     */
    public void show() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) 
                System.out.printf("%1.2f ", A[i][j]);
                //{System.out.print(A[i][j]);System.out.print(" ");}
            System.out.println();
        }
    }
	
    /**
     * 
     * @param matrix Given Square Matrix
     * @param excluding_row row no. which is to be excluded (starts from 0)
     * @param excluding_col column no. which is to be excluded (starts from 0)
     * @return Square matrix which is same as given matrix but it excludes given row number and column number.
     */
    public static SquareMatrix createSubMatrix(SquareMatrix matrix, int excluding_row, int excluding_col) {
        SquareMatrix mat = new SquareMatrix(matrix.getN()-1);
        int r = -1;
        for (int i=0;i<matrix.getN();i++) {
            if (i==excluding_row)
                continue;
                r++;
                int c = -1;
            for (int j=0;j<matrix.getN();j++) {
                if (j==excluding_col)
                    continue;
                c++;
                mat.setArray(r,c, matrix.getArray()[i][j]);
            }
        }
        return mat;
    }
    
    /**
     * signum of x
     * @param x integer whose signum is to be determined
     * @return if x>0, return 1, else if x=0 return 0, else return -1
     */
    public static int sign(int x){
    	if(x==0) return 0;
    	else if(x>0) return 1;
    	else return -1;
    	
    }
    /**
     * 
     * @param i integer
     * @return 1 if i is divisible by 2, else return -1
     */
    public static int changeSign(int i){
    	if(i%2==0) return 1;
    	else return -1;
    }
    
    /**
     * 
     * @param matrix Square Matrix whose cofactor is to be determined
     * @return cofactor of matrix
     */
    public static SquareMatrix cofactor(SquareMatrix matrix) {
        SquareMatrix mat = new SquareMatrix(matrix.getN());
        for (int i=0;i<matrix.getN();i++) {
            for (int j=0; j<matrix.getN();j++) {
                mat.setArray(i,j, changeSign(i+j) * createSubMatrix(matrix, i, j).determinant());
            }
        }        
        return mat;
    }
    /**
     * 
     * @param matrix square matrix
     * @return inverse of matrix
     */
    public static SquareMatrix inverse(SquareMatrix matrix) {
        return (cofactor(matrix).transpose().multiplyByConstant(1.0/matrix.determinant()));
    }
	
    /**
     * 
     * @return a diagonal matrix whose (i,i) entry is sum of ith row
     */
	public SquareMatrix degree(){
		SquareMatrix matrix = new SquareMatrix(this.N);
		for(int i=0;i<this.N;i++){
			int di=0;
			for(int j =0; j<this.N;j++){
				di+=this.A[i][j];
				matrix.setArray(i,j, 0);
			}
			matrix.A[i][i] = di;		
		}
		return matrix;
	}
   
	/**
	 * 
	 * @return stochastic matrix of invoking matrix
	 */
	public SquareMatrix stochastic(){
		SquareMatrix matrix ;
		SquareMatrix D = this.degree();
		SquareMatrix inverseD = inverse(D);
		matrix = inverseD.times(this);
		return matrix;
	}

	/**
	 * 
	 * @param k integer
	 * @return square matrix i.e. (invoking matrix) raise to power k
	 */
	public SquareMatrix power(int k){
		SquareMatrix matrix = this;
		for(int i=0;i<k-1;i++){
			matrix = matrix.times(this);
		}
		return matrix;
	}

	/**
	 * 
	 * @return Laplacian of invoking matrix i.e. Degree Matrix of invoking matrix - invoking matrix
	 */
	public SquareMatrix laplacian(){
		SquareMatrix laplacian = new SquareMatrix(degree());
		return laplacian.minus(this);
	}

}
