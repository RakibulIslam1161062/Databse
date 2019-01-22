
public class DataBlock {
	public int [] a;
	public int predCl;
	public int cl;
	public boolean testCase;
	
	
	
	public DataBlock(int []x,int cl) {
		
		this.predCl=0;
		this.cl=cl;
		this.testCase=false;
		a=new int [x.length];
		for (int i=0; i<x.length; i++)
	        a[i] = x[i];
		// TODO Auto-generated constructor stub
	}
	
	
	

	
}