//-----------------------------------------------------------------------------
// Simulation.java
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Simulation{

//-----------------------------------------------------------------------------
//
// The following function may be of use in assembling the initial backup and/or
// storage queues.  You may use it as is, alter it as you see fit, or delete it
// altogether.
//
//-----------------------------------------------------------------------------

   public static Job getJob(Scanner in){
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }

//-----------------------------------------------------------------------------
//
// The following stub for function main contains a possible algorithm for this
// project.  Follow it if you like.  Note that there are no instructions below
// which mention writing to either of the output files.  You must intersperse
// those commands as necessary.
//
//-----------------------------------------------------------------------------

   public static void main(String[] args) throws IOException{

//    1.check command line arguments
		String fileName = "ex1";
		if(args != null && args.length > 0){
			fileName = args[0];
		}else{
			System.err.println("Usage: Simulation input_file");
			System.exit(1);
		}
//    2.open files for reading and writing
		File f = new File(fileName);
		Scanner in = new Scanner(new FileReader(f));
		File f2 = new File(fileName + ".rpt");
		FileWriter out1 = new FileWriter(f2);
		out1.write("Report file: " + fileName + ".rpt\n");
		File f3 = new File(fileName + ".trc");
		FileWriter out2 = new FileWriter(f3);
		out2.write("Trace file: " + fileName + ".trc\n");
//    3.read in m jobs from input file
		int m = in.nextInt(); 
		out1.write(m + " Jobs:\n");
		out2.write(m + " Jobs:\n");
		Job[] jobs = new Job[m];
		for(int i = 0; i < m; i++){
			int a = in.nextInt();
			jobs[i] = new Job(a,in.nextInt());
			if(i > 0) out1.write(" ");
			if(i > 0) out2.write(" ");
			out1.write(jobs[i].toString());
			out2.write(jobs[i].toString());
		}
		out1.write("\n");
		out2.write("\n\n");
		out1.write("\n***********************************************************\n");
//    4.run simulation with n processors for n=1 to n=m-1  {
		for(int n = 1; n < m; n++){
			QueueInterface js = new Queue();
			for(int i = 0; i < m; i++)
				js.enqueue(new Job(jobs[i].getArrival(), jobs[i].getDuration()));
			out2.write("*****************************\n");
			out2.write(n + " processor" + (n>1?"s":"")+":\n");
			out2.write("*****************************\n");
			int time = 0;
//    5.declare and initialize an array of n processor Queues and any 
//      necessary storage Queues
			QueueInterface[] qs = new Queue[n];
			for(int i=0; i < n; i++)
				qs[i] = new Queue();
//    6.while unprocessed jobs remain  {
			out2.write("time=0\n");
			out2.write("0: " + js.toString() + "\n");
			for(int i = 0; i < n; i++)
				out2.write((i+1) + ": \n");
			out2.write("\n");
			while(js.length() < m || ((Job)js.peek()).getFinish() == Job.UNDEF) {
//    7.determine the time of the next arrival or finish event and 
//      update time
				Job nextJob = js.isEmpty()?null:(Job)js.peek();
				if(nextJob != null && nextJob.getFinish() != Job.UNDEF) nextJob = null;
				int nextTime = nextJob==null?Integer.MAX_VALUE:nextJob.getArrival();
				for(int i = 0; i < n; i++)
					if(!qs[i].isEmpty())
						nextTime = Math.min(nextTime,((Job)qs[i].peek()).getFinish());
				time = nextTime;
//    8.complete all processes finishing now
				for(int i = 0; i < n; i++){
					while(!qs[i].isEmpty()){
						Job job = (Job)qs[i].peek();
						if(job.getFinish() == Job.UNDEF)
							job.computeFinishTime(time);
						if(job.getFinish() <= time)
							js.enqueue(qs[i].dequeue());
						else
							break;
					}
				}
//    9.if there are any jobs arriving now, assign them to a processor 
//      Queue of minimum length and with lowest index in the queue array.
				while(nextJob != null && nextJob.getArrival() <= time){
					int minLength = Integer.MAX_VALUE;
					int id = -1;
					for(int j=0; j < n; j++){
						if(qs[j].length() < minLength) {
							minLength = qs[j].length();
							id = j;
						}
					}
					qs[id].enqueue(js.dequeue());
					nextJob = js.isEmpty()?null:(Job)js.peek();
					if(nextJob != null && nextJob.getFinish() != Job.UNDEF) nextJob = null;
				}
				for(int i = 0; i < n; i++)
					if(!qs[i].isEmpty()){
						Job job = (Job)qs[i].peek();
						if(job.getFinish() == Job.UNDEF)
							job.computeFinishTime(time);
					}
				out2.write("time=" + time + "\n");
				out2.write("0: " + js.toString() + "\n");
				for(int i = 0; i < n; i++)
					out2.write((i+1) + ": " + qs[i].toString() + "\n");
				out2.write("\n");
//    10.} end loop
			}
//    11.compute the total wait, maximum wait, and average wait for 
//       all Jobs, then reset finish times
			int waitTime = 0, maxWaitTime = 0;
			for(int i = 0; i < m; i++) {
				Job j = (Job)js.dequeue();
				int wait = j.getWaitTime();
				maxWaitTime = Math.max(maxWaitTime,wait);
				waitTime += wait;
				js.enqueue(j);
			}
			double averageWaitTime = waitTime*1.0 / m;
			out1.write(String.format(
					"%d processor%s: totalWait=%d, maxWait=%d, averageWait=%.2f\n",
					n,n>1?"s":"",waitTime,maxWaitTime,averageWaitTime));
//    12.} end loop
		}
//    13.close input and output files
		out1.close();
		out2.close();
		in.close();
   }
}


