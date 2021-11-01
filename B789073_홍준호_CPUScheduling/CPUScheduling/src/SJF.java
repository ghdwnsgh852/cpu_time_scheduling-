import java.util.Iterator;
import java.util.LinkedList;

public class SJF extends Queue {
	int num=queue.size();
	
	SJF(int num,LinkedList<Process> queue) {
		super(num,queue);
		super.name="SJF";
	}
	void enterReadyQueue(Process pro) {
		int i=0;
		Iterator<Process> iter=readyQueue.iterator();
		while(iter.hasNext()) {
			Process pro1=iter.next();
			if(pro1.remainingCburst<=pro.remainingCburst) {
				i++;
			}
		}
		readyQueue.add(i, pro);}

	void startQueue() {
		if(idx!=num) {
		for(Process pro:queue) {
			if(pro.A==FinishingTime) {
				idx++;
				pro.remainingCburst=random.nextInt(pro.B)+1;
				enterReadyQueue(pro);
				}
			}
		}
	}
	
	void waiting() {
		Iterator<Process> iter=IOQueue.iterator();
		LinkedList<Process> temp= new LinkedList<Process>();
		while(iter.hasNext()) {
			Process pro=iter.next();
			pro.remaingIoburst--;
			pro.IoBusrt++;
			if(pro.remaingIoburst==0) {
				temp.add(pro);
			}
		}
		iter=temp.iterator();
		while(iter.hasNext()) {
			Process pro=iter.next();
			pro.remainingCburst=random.nextInt(pro.B)+1;
			IOQueue.remove(pro);
			enterReadyQueue(pro);
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
				}
			else if(cpu.remainingCburst==0) {
				if(cpu.IO!=0) {
					cpu.remaingIoburst=random.nextInt(cpu.IO)+1;
					IOQueue.add(cpu);
					cpu=null;
					}
				else {
					cpu.remainingCburst=random.nextInt(cpu.B)+1;
					enterReadyQueue(cpu);
					cpu=null;
					}
				}
			}
		}
}
