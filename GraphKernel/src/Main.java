import java.io.BufferedReader;
import java.io.FileReader;
import com.math.SquareMatrix;
import com.kernel.GraphMatching;
//import com.kernel.NodeMatching;



public class Main {
	
	public static String readFile(String filename){
		String everything="";
    try {
    		BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }
        everything = sb.toString();
        br.close();
    } catch(Exception e){
    	System.out.println(e);
    }	
    return everything;
	}
	
	public static void main(String[] args){
	/*FOR GRAPH KERNEL READING FROM FILE*/
	
		String filename1="test/TS1.txt", filename2="test/TS2.txt", filename3="test/Z1.txt";
	
	/*	try{BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Give name of file : ");
    filename1 = reader.readLine();
    System.out.println("Give name of file : ");
    filename2 = reader.readLine();
    System.out.println("Give name of file : ");
    filename3 = reader.readLine();
    }catch(Exception e){
    	System.out.println(e);
    }*/
	
		String string1 = readFile(filename1);
		String string2 = readFile(filename2);
		String string3 = readFile(filename3);
		
		if(string1=="" || string2=="" || string3=="") return;
		
		int n1 = (int) Math.sqrt((double)string1.length()/2);
		int n2 = (int)Math.sqrt((double)string2.length()/2);
		int n3 = (int)Math.sqrt((double)string3.length()/2);
		double[][] A1,B1,C1;
		A1 = new double[n1][n1];
		B1 = new double[n2][n2];
		C1 = new double[n3][n3];	
		
		for(int i=0; i< n1; i++){
				for(int j=0; j<2*n1;j+=2){
					A1[i][j/2] = (double)string1.charAt(i*2*n1 + j) - 48;
				}
		}
		
		for(int i=0; i< n2; i++){
				for(int j=0; j<2*n2;j+=2){
					B1[i][j/2] = (double)string2.charAt(i*2*n2 + j) - 48;
				}
		}
		
		for(int i=0; i< n3; i++){
				for(int j=0; j<2*n3;j+=2){
					C1[i][j/2] = (double)string3.charAt(i*2*n3 + j) - 48;
				}
		}
		
		SquareMatrix A = new SquareMatrix(A1);
		SquareMatrix B = new SquareMatrix(B1);
		SquareMatrix C = new SquareMatrix(C1);
		
		GraphMatching g = new GraphMatching(A,B);
		GraphMatching g2 = new GraphMatching(A,C);
		GraphMatching g3 = new GraphMatching(B,C);
		double result = g.matching();
		double result2 = g2.matching();
		double result3 = g3.matching();
		System.out.println(" A and B :  " + result + " A and C :  " + result2 + " B and C :  " + result3);
	
	/*-----------------------------------------------------------------------------------------------------------------*/
	
	/*FOR GRAPH KERNEL , DATA GIVEN MANUALLY*/
		/*
		double[][] A1,B1,C1;
		A1 = new double[6][6];
		B1 = new double[6][6];
		C1 = new double[6][6];
		A1[0][1]=A1[1][0]=A1[1][2]=A1[1][5]=A1[2][1]=A1[2][3]=A1[2][5]=A1[3][2]=A1[3][4]=A1[3][5]=
				A1[4][3]=A1[4][5]=A1[5][1]=A1[5][2]=A1[5][3]=A1[5][4]=1;
		
		B1[0][1]=B1[1][0]=B1[1][2]=B1[1][4]=B1[1][5]=B1[2][1]=B1[2][3]=B1[2][5]=B1[3][2]=B1[3][4]=
				B1[4][1]=B1[4][3]=B1[4][5]=B1[5][1]=B1[5][2]=B1[5][4]=1;
		
		C1[0][5] = C1[1][5] = C1[2][5]=C1[3][4]=C1[3][5]=C1[4][3]=C1[4][5]=C1[5][0]=C1[5][1]=C1[5][2]=
				C1[5][3]=C1[5][4]=1;
		//A~ B, but A !~ C
		
		SquareMatrix A = new SquareMatrix(A1);
		SquareMatrix B = new SquareMatrix(B1);
		SquareMatrix C = new SquareMatrix(C1);
		
		System.out.println("Matrix A :");
		A.show();
		System.out.println("Matrix B :");
		B.show();
		System.out.println("Matrix C :");
		C.show();
		
		GraphMatching g = new GraphMatching(A,B);
		GraphMatching g2 = new GraphMatching(A,C);
		GraphMatching g3 = new GraphMatching(B,C);
		double result = g.matching();
		double result2 = g2.matching();
		double result3 = g3.matching();
		System.out.println(" A and B :  " + result + " A and C :  " + result2 + " B and C :  " + result3);
		*/
	
	/*-------------------------------------------------------------------------------------------------------------------*/
		
	/*FOR NODE KERNEL , DATA GIVEN MANUALLY*/	
		/*double A[][] = new double[4][4];
		A[0][1]=A[0][3]=A[1][0]=A[1][2]=A[1][3]=A[2][1]=A[2][3]=A[3][0]=A[3][1]=A[3][2]=1;
		SquareMatrix M = new SquareMatrix(A);
		NodeMatching kernel = new NodeMatching(M.laplacian());
		SquareMatrix resultKernel = kernel.calculateKernel();
		resultKernel.show();
		*/
		
	/*FOR NODE KERNEL , READING FROM FILE*/	
	/*
	String filename = "Z1.txt";
	String string = readFile(filename);	
	if(string=="") return;
	int n = (int) Math.sqrt((double)string.length()/2);
	//System.out.println(n);
	double[][] A = new double[n][n];
	for(int i=0; i< n; i++){
		for(int j=0; j<2*n;j+=2){
			A[i][j/2] = (double)string.charAt(i*2*n + j) - 48;
		}
	}
	SquareMatrix M = new SquareMatrix(A);
	NodeMatching kernel = new NodeMatching(M);
	SquareMatrix resultKernel = kernel.calculateKernel();
	resultKernel.show();
		*/
	}		
}