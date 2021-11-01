import java.util.Random;

public class Process {
Random random= new Random();
int pid;
int A;
int B;
int C;
int IO;
int processFinishingTime=0;
int remainingCpuTime;
int cburst=0;
int remainingCburst;
int remaingIoburst;
int IoBusrt=0;
int waitingTime=0;
int turnAroundTime;
public Process(int pid,int A,int B,int C,int IO){
	this.pid=pid;
	this.A=A;
	this.B=B;
	this.C=C;
	this.IO=IO;
	this.remainingCpuTime=C;
}
	
}
