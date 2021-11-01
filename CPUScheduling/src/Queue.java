import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public abstract class Queue {
	int FinishingTime=0,AVGTurnaroundTime,AvgWationgTime;
	Process cpu;
	double CpuUtilization,IOUtilization,Throughput;
	int sumCBurst=0,sumIObusrt=0,sumAvgTurnAroundTime=0,sumAvgWaitingTime=0;
	String name;
	LinkedList<Process> IOQueue= new LinkedList<Process>();	
	LinkedList<Process> readyQueue= new LinkedList<Process>();	
	LinkedList<Process> queue= new LinkedList<Process>();	
	Random random= new Random();
	int num;
	int idx=0;
	void startQueue() {
		if(idx!=num) {
		for(Process pro:queue) {
			if(pro.A==FinishingTime) {
				idx++;
				pro.remainingCburst=random.nextInt(pro.B)+1;
				readyQueue.add(pro);
				}
			}
		}	
	}
	Queue(int num,LinkedList<Process> queue){
		this.queue=queue;
		this.num=num;
	}
	public boolean isremain(){
		Iterator<Process> iter=queue.iterator();
		int sumRemainTime=0;
		while(iter.hasNext()) {
			Process pro=iter.next();
			sumRemainTime+=pro.remainingCpuTime;
		}
		if(sumRemainTime==0)
			return false;
		else return true;
	}
	void insertCpu() {
		if(cpu==null) {
			cpu=readyQueue.poll();
		}
	}
	
	void running() {
		if(cpu!=null) {
			cpu.cburst++;
			cpu.remainingCburst--;
			cpu.remainingCpuTime--;
			if(cpu.remainingCpuTime==0) {
				cpu.processFinishingTime=FinishingTime+1;
				cpu.turnAroundTime=cpu.processFinishingTime-cpu.A;
				cpu=null;
				return;
				}
			else if(cpu.remainingCburst==0) {
				if(cpu.IO!=0) {
					cpu.remaingIoburst=random.nextInt(cpu.IO)+1;
					IOQueue.add(cpu);
					cpu=null;
					return;
					}else {
					cpu.remainingCburst=random.nextInt(cpu.B)+1;
					readyQueue.add(cpu);
					cpu=null;
					return;
					}
				}
			}
		}
	void ready() {
		Iterator<Process> iter=readyQueue.iterator();
		while(iter.hasNext()) {
			Process pro=iter.next();
			pro.waitingTime++;
		}
	}
	void waiting() {
		LinkedList<Process> temp= new LinkedList<Process>();
		int q=IOQueue.size();
		if(q>0) {
			for(Process pro:IOQueue) {
				pro.remaingIoburst--;
				pro.IoBusrt++;
				if(pro.remaingIoburst==0) {
					temp.add(pro);
				}
			}
			Iterator<Process> iter1=temp.iterator();
			while(iter1.hasNext()) {
				Process pro1=iter1.next();
				pro1.remainingCburst=random.nextInt(pro1.B)+1;
				IOQueue.remove(pro1);
				readyQueue.add(pro1);
			}
		}
	}

	void run() {
		while(isremain()) {
			startQueue();
			insertCpu();
			ready();
			waiting();
			running();
			FinishingTime+=1;
		}
	}
	void print() {
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		System.out.println(name);
		System.out.println("Number of processes: "+num);
		Iterator<Process> iter=queue.iterator();
		while(iter.hasNext()) {
			Process pro=iter.next();
			sumCBurst+=pro.cburst;
			sumIObusrt+=pro.IoBusrt;
			sumAvgTurnAroundTime+=pro.turnAroundTime;
			sumAvgWaitingTime+=pro.waitingTime;
			System.out.println("pid: "+pro.pid+"  A: "+pro.A+" C: "+pro.C+" B: "+pro.B+" I/O: "+pro.IO+" Finishing time: "+pro.processFinishingTime+" Turnaround time: "+pro.turnAroundTime+" CPU time: "+pro.cburst+" I/O time: "+pro.IoBusrt+" Waiting time: "+pro.waitingTime);
		}
		CpuUtilization= ((double)sumCBurst/FinishingTime*100);
		IOUtilization= ((double)sumIObusrt/FinishingTime*100);
		Throughput=(double)num/FinishingTime*100;
		AVGTurnaroundTime=(int) ((double)sumAvgTurnAroundTime/num);
		AvgWationgTime=(int) ((double)sumAvgWaitingTime/num);
		System.out.println("Finishting time: "+FinishingTime);
		System.out.println("CPU utilization: "+CpuUtilization);
		System.out.println("I/O utilization: "+IOUtilization);
		System.out.println("Throughput in processes completed per hundred time units: "+Throughput);
		System.out.println("AVGTurnaroundTime: "+AVGTurnaroundTime);
		System.out.println("AvgWationgTime: "+AvgWationgTime);
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
	}	
	}