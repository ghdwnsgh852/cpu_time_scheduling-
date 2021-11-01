import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		int num=Integer.parseInt(args[0]);
		LinkedList<Process> fcfsqueue=new LinkedList<Process>();
		LinkedList<Process> sjfqueue=new LinkedList<Process>();
		LinkedList<Process> rr1queue=new LinkedList<Process>();
		LinkedList<Process> rr10queue=new LinkedList<Process>();
		LinkedList<Process> rr100queue=new LinkedList<Process>();
		for(int i=0; i<num; i++) {
			int A=Integer.parseInt(args[4*i+1].substring(1));
			int C=Integer.parseInt(args[4*i+2]);
			int B=Integer.parseInt(args[4*i+3]);
			int IO=Integer.parseInt(args[4*i+4].substring(0, args[4*i+4].indexOf(")")));
			int pid=i+1;
			fcfsqueue.add(new Process(pid,A,B,C,IO));
			sjfqueue.add(new Process(pid,A,B,C,IO));
			rr1queue.add(new Process(pid,A,B,C,IO));
			rr10queue.add(new Process(pid,A,B,C,IO));
			rr100queue.add(new Process(pid,A,B,C,IO));
		}
		FCFS fcfs=new FCFS(num,fcfsqueue);
		fcfs.run();	
		fcfs.print();

		SJF sjf=new SJF(num,sjfqueue);
		sjf.run();
		sjf.print();
		
		RR rr1=new RR(num,rr1queue,1);
		rr1.run();
		rr1.print();
		
		RR rr10=new RR(num,rr10queue,10);
		rr10.run();
		rr10.print();
		
		RR rr100=new RR(num,rr100queue,100);
		rr100.run();
		rr100.print();
	}

}
