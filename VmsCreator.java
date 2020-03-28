package org.cloudbus.cloudsim.examples;

import java.util.ArrayList;


import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Vm;;



public class VmsCreator {
	
	
	public ArrayList<Vm> createRequiredVms(int reqVms, int brokerId){
		
		ArrayList<Vm> vmlist = new ArrayList<Vm>();
		
    	
    	int vmid = 0;
    	
    	int[] mips={220, 200, 300, 450};
    	long size = 1000; //image size (MB)
    	int ram = 512; //vm memory (MB)
    	long bw = 1000;
    	int[] pesNumber = {1,2,1,1}; //number of cpus
    	String vmm = "Xen"; //VMM name

    	
    	
    	for(vmid=0;vmid<reqVms;vmid++){
    		
    		vmlist.add(new Vm(vmid, brokerId, mips[vmid], pesNumber[vmid], ram, bw, 
    				size, vmm, new CloudletSchedulerTimeShared()));
    	}

    	System.out.println("VmsCreator function Executed... SUCCESS:)");
		return vmlist;
		
	}

}

