import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Nearest {
	
	public static void main(String args[]) throws IOException
	{
		
		
		int j=0,k=0,parcent=0;
		System.out.println("lol");
		File file= new File("C:\\Users\\Rakib bhai\\Downloads\\datafile\\new.txt");
		BufferedReader br =new BufferedReader(new FileReader(file));
		String st;
		DataBlock[] Db= new DataBlock[1110];
		
		while((st=br.readLine())!= null)
		{
			
			String[] s=st.split(",");
			//System.out.println(j+"  "+s);
			String s1 = null,s2 = null,s3 = null,s4 = null;
			
			int foo1 =Integer.parseInt(s[0]);
			int foo2 =Integer.parseInt(s[1]);
			int foo3 =Integer.parseInt(s[2]);
			int foo4 =Integer.parseInt(s[3]);
			//System.out.println("foo4 here "+foo4);
			 Db[j]=new DataBlock(foo1,foo2,foo3,foo4);
			
			
			//System.out.println(Db[j].a+" "+Db[j].b+" "+Db[j].c+" "+Db[j].cl);
			j++;	
		}
		/*for(int i=0;i<j;i++)
			System.out.println(i+" "+ Db[i].a+" "+Db[i].b+" "+Db[i].c+" "+Db[i].cl+"\n");
		*/
		Random ran= new Random(1001);
		int bound=j/10+1;
		parcent=bound;
		//System.out.println(parcent);
		int [] num= new int[parcent];
		
		while( parcent>0)
	   {
			
			int n= ran.nextInt(j-1)+0;
			if(Db[n].testCase==false) 
			{
				Db[n].testCase=true;
				System.out.println("I am True "+n+" ");
				num[k]=n;
				k++;
				parcent--;
			}
			
		}
		
		/*for(int l=0;l<bound;l++)
			System.out.println(num[l]+" ");*/
		//for(int i=0;i<k;i++)
			//System.out.println(Db[num[i]].a+"  "+i+"\n");
		
		
		double[] rootValue=new double[j] ;
		double[] rootValue2=new double[j];
		double[] topRootValue= new double[3];
		
		//System.out.println("here j= "+j + "j- bouund=   "+ (j-parcent));
		for(int i=0;i<bound;i++) {
			for(int m=0;m<j;m++)
			{
				if(Db[m].testCase==false) {
					rootValue[m]=root(Db[num[i]],Db[m]);
					rootValue2[m]=rootValue[m];
				}
				else 
				{
					rootValue[m]=99999999;
					rootValue2[m]=9999999;
				}
			}
			/*for(int v=0;v<j;v++)
				System.out.println(rootValue2[v]);
			*/
			Arrays.sort(rootValue);
			//System.out.println(Arrays.toString(rootValue));
			
			int[] vote =new int[3];
			int k1=0,k2=0,k3=0;
			//System.out.println();
			for(int u=0;u<j;u++)
			{
				if(rootValue2[u]==rootValue[0]) {
					k1=u;
				break;
				}
			}
			for(int u=0;u<j;u++)
			{
				if(rootValue2[u]==rootValue[1]) {
					k2=u;
					break;
				}
				
			}
			for(int u=0;u<j;u++)
			{
				if(rootValue2[u]==rootValue[2]) {
					k3=u;
					break;
				}
				
			}
			/*k1=classFinder(rootValue2,rootValue[0],j);
			k2=classFinder(rootValue2,rootValue[1],j);
			k3=classFinder(rootValue2,rootValue[2],j);
			System.out.println(k1+" kk "+k2+" "+k3);
			*/
			System.out.println(k1+" kk "+k2+" "+k3+" "+i+" ");
			vote[0]=Db[k1].cl;
			vote[1]=Db[k2].cl;
			vote[2]=Db[k3].cl;
			
			System.out.println("lolo" +vote[0]+"  "+vote[1]+"  "+vote[2]);
			int for1=0,for2=0;
			for(int p=0;p<3;p++) if(vote[p]==1) for1++; else for2++;
			
			if(for1>for2) Db[num[i]].predCl=1; else Db[num[i]].predCl=2;	
			
		}
		
		int predClass=0;
		for(int v=0;v<bound;v++){
			if(Db[num[v]].cl==Db[num[v]].predCl) predClass++;
			System.out.println(Db[num[v]].cl+"  vs pred "+Db[num[v]].predCl );
		
		}
		System.out.println("predClass "+predClass);
		double validity=0.0;
		//System.out.println(predClass+"  "+bound+"  ");
		
		validity=(predClass/31.0)*100;
		System.out.println("yo    Validity " +validity+" parcent");
	}
	
	public static double root(DataBlock x, DataBlock y)
	{
		return Math.sqrt((x.a-y.a)*(x.a-y.a)+(x.b-y.b)*(x.b-y.b)+(x.c-y.c)*(x.c-y.c));
	}
	
	public static int classFinder( double[] x,double y,int j)
	{
		int l = 0;
		for(int i=0;i<j;i++) {
			
			//System.out.println("hmm "+x[i]+"  -"+y);
			if(x[i]==y) {
				//System.out.println("here + "+l+" ");
				l= i;
				break;
			}
		}
		//System.out.println("returned value "+l+" ");
		return l;
	}
	
	
	
	
}
