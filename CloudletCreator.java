package org.cloudbus.cloudsim.examples;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;


import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.core.CloudSim;



public class EnhancedMaxMin {

	private static List<Cloudlet> cloudletList;

	private static List<Vm> vmlist;

	private static int reqTasks = 10;
	private static int reqVms = 4;
	
	
	public static void main(String[] args) {

		Log.printLine("Starting Max-Min...");

	        try {
	        	
	            	int num_user = 1;   // number of cloud users
	            	Calendar calendar = Calendar.getInstance();
	            	boolean trace_flag = false;  // mean trace events

	            	
	            	CloudSim.init(num_user, calendar, trace_flag);

	            	
	            	@SuppressWarnings("unused")
					Datacenter datacenter0 = createDatacenter("Datacenter_0");

	            	
	            	EnhancedMaxMinBroker broker = createBroker();
	            	int brokerId = broker.getId();

	            	
	            	
	            	vmlist = new VmsCreator().createRequiredVms(reqVms, brokerId);


	            	broker.submitVmList(vmlist);


	            	
	            	cloudletList = new CloudletCreator3().createUserCloudlet(reqTasks, brokerId);
	            	
	            	
	            	broker.submitCloudletList(cloudletList);
	            	
    	
	            	broker.scheduleTaskstoVms();
   	
            	
	            	
	            	CloudSim.startSimulation();


	            	List<Cloudlet> newList = broker.getCloudletReceivedList();

	            	CloudSim.stopSimulation();

	            	printCloudletList(newList);

	            	Log.printLine("EnhancedMaxMin finished!");
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            Log.printLine("The simulation has been terminated due to an unexpected error");
	        }
	    }

		private static Datacenter createDatacenter(String name){
			Datacenter datacenter=new DataCenterCreator().createUserDatacenter(name, reqVms);			

	        return datacenter;

	    }

	    
	    private static EnhancedMaxMinBroker createBroker(){

	    	EnhancedMaxMinBroker broker = null;
	        try {
			broker = new EnhancedMaxMinBroker("Broker");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	    	return broker;
	    }

	    
	    private static void printCloudletList(List<Cloudlet> list) {
	        int size = list.size();
	        Cloudlet cloudlet;

	        String indent = "    ";
	        Log.printLine();
	        Log.printLine("========== OUTPUT ==========");
	        Log.printLine("Cloudlet ID" + indent + "STATUS" + indent +
	                "Data center ID" + indent + "VM ID" + indent + "Time" + indent + "Start Time" + indent + "Finish Time");

	        DecimalFormat dft = new DecimalFormat("###.##");
	        for (int i = 0; i < size; i++) {
	            cloudlet = list.get(i);
	            Log.print(indent + cloudlet.getCloudletId() + indent + indent);

	            if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS){
	                Log.print("SUCCESS");

	            	Log.printLine( indent + indent + cloudlet.getResourceId() + indent + indent + indent + cloudlet.getVmId() +
	                     indent + indent + dft.format(cloudlet.getActualCPUTime()) + indent + indent + dft.format(cloudlet.getExecStartTime())+
                             indent + indent + dft.format(cloudlet.getFinishTime()));
	            }
	        }

	    }
}
