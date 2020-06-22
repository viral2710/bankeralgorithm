package solution;
import java.util.*;
public class solution{
	public static void main(String[]args) {
		String type="";
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter no. of processes");
		int process=sc.nextInt();
		System.out.println("Enter no. of resources");
		int	nresource=sc.nextInt();
		int []tresource=new int[nresource];
		System.out.println("Enter available resources");
		for(int i=0;i<nresource;i++) {
			tresource[i]=sc.nextInt();
		}
		int [][]ar=new int[process][nresource];
		int [][]max=new int[process][nresource];
		System.out.println("Enter no. of resources allocated to processes");
		for(int i=0;i<process;i++) {
			for(int j=0;j<nresource;j++) {
				ar[i][j]=sc.nextInt();
			}
		}
		System.out.println("Enter no. of max. resources required for processes to  terminated");
		for(int i=0;i<process;i++) {
			for(int j=0;j<nresource;j++) {
				max[i][j]=sc.nextInt();
			}
		}
		int [][]need=new int[process][nresource];
		for(int i=0;i<process;i++) {
			for(int j=0;j<nresource;j++) {
				need[i][j]=max[i][j]-ar[i][j];
			}
		}
		System.out.println("needed resources");
		for(int i=0;i<process;i++) {
			for(int j=0;j<nresource;j++) {
				System.out.print(need[i][j]+" ");
			}
			System.out.println();
		}
		int[] left= new int[nresource];
		for(int i=0;i<nresource;i++) {
			for(int j=0;j<process;j++) {
				left[i]+=ar[j][i];
			}
		}
		for(int i=0;i<nresource;i++) {
			left[i]=tresource[i]-left[i];
		}
		int count=0,loop=0;
		
		List<Integer> a=new ArrayList<Integer>();
		while(a.size()!=process) {
			for(int i=0;i<process;i++) {
				if(a.contains(i+1)) {
					continue;
				}
				for(int j=0;j<nresource;j++) {
					if(left[j]>=need[i][j])
						count++;
					
				}
				if(count==3) {
					a.add(i+1);
					for(int j=0;j<nresource;j++) {
						left[j]+=ar[i][j];
					}
				}
				count=0;
			}
			loop++;
			if(loop>process) {
				type="Unsafe";
				break;
			}
		}
		if(type=="") {
			type="Safe";
		}
		if(a.size()!=process) {
			System.out.println("State is "+type+"Deadlock occur in following state:");
			System.out.print("<");
			for(int i=0;i<process;i++) {
				if(!a.contains(i+1)) {
					System.out.print("P"+(i+1)+" ");
				}
			}System.out.print(">");
		}else {
			System.out.println("State is "+type);
			System.out.print("<");
			for(int i=0;i<process;i++) {
				System.out.print("P"+a.get(i)+" ");
			}
			System.out.print(">");
		}
		sc.close();
	}
		
}

