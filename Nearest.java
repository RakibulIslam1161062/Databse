import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
public class Nearest {
	
	public static void main(String args[]) throws IOException
	{
		
		
		int j=0,k=0;;
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
			 Db[j]=new DataBlock(foo1,foo2,foo3,foo4);
			
			
			System.out.println(Db[j].a+" "+Db[j].b+" "+Db[j].c+" "+Db[j].cl);
			j++;
		
		}
		
		Random ran= new Random(1000);
		while( k<10)
	   {
			
			int n= ran.nextInt(j-1)+0;
			System.out.println(n+" ");
			k++;
		}
		
		
		
	}
	
	
	
}
