import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Nearest {
		
	public static int j=0;
	public static void main(String args[]) throws IOException
	{
		
		
		int totalData=0,k=0,parcent=0,bound, tp,fn,fp,tn;
		double precision,recall,f1Score;
		DataBlock[] Db= new DataBlock[1110];
		
		Db=dataReader();
		totalData=j;
		bound=totalData/10;
		
		parcent=bound;
		int [] num= new int[parcent];
		
		shuffle(Db,num,parcent,totalData);
		knnAlgo(Db,num,bound,totalData);
		double accouracy=accouracyCheck(Db,num,bound,totalData);
			    
	    tp=truePos(Db,num,bound);
	    fn=falseNeg(Db,num,bound);
	    fp=falsePos(Db,num,bound);
	    tn=trueNeg(Db,num,bound);
	    
	    precision=getPrecision(tp,fp);
	    recall=getRecall(tp,fn);
	    f1Score=getScore(precision,recall);
	    
	    System.out.println(" Validity " +accouracy+" ");
	    System.out.println(" precision " +precision+" "+"\n"+" Recall "+ recall+ "\n"+ " F1 Score "+f1Score);
	    
	}
	
	
	public static double getPrecision(int tp,int fp)
	{
		return ((double)tp/(tp+fp));
	}
	
	
	public static double getRecall(int tp,int fn)
	{
		return ((double)tp/(tp+fn));
	}
	
	
	public static double getScore(double precision,double recall )
	{
		return (2*precision*recall)/(precision+recall);
	}
	
	
	public static int truePos(DataBlock[] Db,int [] num,int bound)
	{
		int tp=0;
		for(int i=0;i<bound;i++) if(Db[num[i]].cl==Db[num[i]].predCl && Db[num[i]].cl==1) tp++;
		return tp;
	}
	
	
	public static int trueNeg(DataBlock[] Db,int [] num,int bound)
	{
		int tn=0;
		for(int i=0;i<bound;i++) if(Db[num[i]].cl==Db[num[i]].predCl  && Db[num[i]].cl==2) tn++;
		return tn;
	}
	
	
	public static int falseNeg(DataBlock[] Db,int [] num,int bound)
	{
		int fn=0;
		for(int i=0;i<bound;i++) if(Db[num[i]].cl!=Db[num[i]].predCl  && Db[num[i]].cl==1) fn++;
		return fn;
	}
	
	
	public static int falsePos(DataBlock[] Db,int [] num,int bound)
	{
		int fp=0;
		for(int i=0;i<bound;i++) if(Db[num[i]].cl!=Db[num[i]].predCl  && Db[num[i]].cl==2) fp++;
		return fp;
	}
	
	
	public static double root(DataBlock x, DataBlock y)
	{
		double val=0.0;
		for(int jj=0;jj<x.a.length;jj++)
		{
			val+=(x.a[jj]-y.a[jj])*(x.a[jj]-y.a[jj]);
		}
		return Math.sqrt(val);
	}
	
	
	public static int classFinder( double[] x,double y,int totalData)
	{
		int l = 0;
		for(int i=0;i<totalData;i++) {
			
			if(x[i]==y) {
				l= i;
				break;
			}
		}
		return l;
	}
	
	
	public static double accouracyCheck(DataBlock[] Db,int[] num,int bound,int totalData)
	{
		int predClass=0;
		for(int v=0;v<bound;v++)
			if(Db[num[v]].cl==Db[num[v]].predCl) predClass++;
			//System.out.println(Db[num[v]].cl+"  vs pred "+Db[num[v]].predCl );
		
		
		//System.out.println("predClass "+predClass);
		double validity=0.0;
		//System.out.println(predClass+"  "+bound+"  ");
		
		validity=((double)predClass/bound)*100.0;
		return validity;
	}
	
	
	
	public static void knnAlgo(DataBlock[] Db,int[] num,int bound,int totalData )
	{
		double[] distanceValue=new double[totalData] ;
		double[] distanceValue2=new double[totalData];
		double[] topDistanceValue= new double[3];
		
		//System.out.println("here j= "+j + "j- bouund=   "+ (j-parcent));
		for(int i=0;i<bound;i++) {
			for(int m=0;m<totalData;m++)
			{
				if(Db[m].testCase==false) {
					distanceValue[m]=root(Db[num[i]],Db[m]);
					distanceValue2[m]=distanceValue[m];
				}
				else 
				{
					distanceValue[m]=99999999;
					distanceValue2[m]=9999999;
				}
			}
			/*for(int v=0;v<j;v++)
				System.out.println(rootValue2[v]);
			*/
			Arrays.sort(distanceValue);
			//System.out.println(Arrays.toString(rootValue));
			
			int[] vote =new int[3];
			int k1=0,k2=0,k3=0;
			//System.out.println();
			
			k1=classFinder(distanceValue2,distanceValue[0],totalData);
			k2=classFinder(distanceValue2,distanceValue[1],totalData);
			k3=classFinder(distanceValue2,distanceValue[2],totalData);
			//System.out.println(k1+" kk "+k2+" "+k3);
			//*/
			//System.out.println(k1+" kk "+k2+" "+k3+" "+i+" ");
			vote[0]=Db[k1].cl;
			vote[1]=Db[k2].cl;
			vote[2]=Db[k3].cl;
			
			//System.out.println("lolo" +vote[0]+"  "+vote[1]+"  "+vote[2]);
			int for1=0,for2=0;
			for(int p=0;p<3;p++) if(vote[p]==1) for1++; else for2++;
			
			if(for1>for2) Db[num[i]].predCl=1; else Db[num[i]].predCl=2;	
			
		}
		
	}
	
	
	public static DataBlock[] dataReader() throws NumberFormatException, IOException
	{
		
		File file= new File("C:\\Users\\Rakib bhai\\Downloads\\datafile\\new.txt");
		BufferedReader br =new BufferedReader(new FileReader(file));
		String st;
		DataBlock[] Db= new DataBlock[1110];
		int sLength;
		while((st=br.readLine())!= null)
		{
			
			
			String[] s=st.split(",");
			sLength=s.length-1;
			int []argArray= new int[sLength];
			//System.out.println(j+"  "+s);
			/*String s1 = null,s2 = null,s3 = null,s4 = null;
			
			int attribute1 =Integer.parseInt(s[0]);
			int attribute2 =Integer.parseInt(s[1]);
			int attribute3 =Integer.parseInt(s[2]);
			int classs =Integer.parseInt(s[3]);
			//System.out.println("foo4 here "+foo4);
			 * */
			 
			for(int ii=0;ii<sLength;ii++)
			{
				argArray[ii]=Integer.parseInt(s[ii]);
			}
			int cls=Integer.parseInt(s[sLength]);
			 Db[j]=new DataBlock(argArray,cls);
			
			
			//System.out.println(Db[j].a+" "+Db[j].b+" "+Db[j].c+" "+Db[j].cl);
			j++;
			 //System.out.println(j);
		}
		return Db;
		
	}
	
	
	public static void shuffle(DataBlock[] Db,int[] num,int parcent,int totalData)
	{
		Random ran= new Random(1001);
		
		int k=0;
		while( parcent>0)
	   {
			int n= ran.nextInt(totalData-1)+0;
			if(Db[n].testCase==false) 
			{
				Db[n].testCase=true;
				//System.out.println("I am True "+n+" ");
				num[k]=n;
				k++;
				parcent--;
			}		
		}	
	}
	
}