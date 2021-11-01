
import java.util.LinkedList;

public class RR extends Queue {
	int num=queue.size();
	int idx=0;
	int quantum;
	int thisQuantum=0;
	RR(int num,LinkedList<Process> queue,int quantum) {
		super(num,queue);
		this.quantum=quantum;
		super.name="Round Robin, quantum: "+String.valueOf(quantum);
	}
	void running() {
		if(cpu!=null) {
			thisQuantum++;
			cpu.cburst++;
			cpu.remainingCburst--;
			cpu.remainingCpuTime--;
			if(cpu.remainingCpuTime==0) {
				cpu.processFinishingTime=FinishingTime+1;
				cpu.turnAroundTime=cpu.processFinishingTime-cpu.A;
				cpu=null;
				thisQuantum=0;
				}
			else if(cpu.remainingCburst==0) {
				if(cpu.IO!=0) {
					cpu.remaingIoburst=random.nextInt(cpu.IO)+1;
					IOQueue.add(cpu);
					thisQuantum=0;
					cpu=null;
					}
				else {
					cpu.remainingCburst=random.nextInt(cpu.B)+1;
					
					readyQueue.add(cpu);
					cpu=null;
					thisQuantum=0;
					}
			}else if(quantum==thisQuantum) {
				readyQueue.add(cpu);
				thisQuantum=0;
				cpu=null;
			}
		}
	}
}
