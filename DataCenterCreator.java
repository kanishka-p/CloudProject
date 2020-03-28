package org.cloudbus.cloudsim.examples;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;


public class DataCenterCreator  {
	


	public Datacenter createUserDatacenter(String name,int reqVms){

      
    	List<Host> hostList = new ArrayList<Host>();

     
    	List<Pe> peList = new ArrayList<Pe>();

    	int mips = 1000*reqVms;

        
    	peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

        
        int hostId=0;
        int ram = 2048; 
        long storage = 1000000; //host storage
        int bw = 10000;

        hostList.add(
    			new Host(
    				hostId,
    				new RamProvisionerSimple(ram),
    				new BwProvisionerSimple(bw),
    				storage,
    				peList,
    				new VmSchedulerTimeShared(peList)
    			)
    		); 


       
        String arch = "x86";      // system architecture
        String os = "Linux";          // operating system
        String vmm = "Xen";
        double time_zone = 10.0;         
        double cost = 3.0;              
        double costPerMem = 0.05;		
        double costPerStorage = 0.001;	
        double costPerBw = 0.0;			
        LinkedList<Storage> storageList = new LinkedList<Storage>();	

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, cost, costPerMem, costPerStorage, costPerBw);


      
        Datacenter datacenter = null;
        try {
            datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Success!! DatacenterCreator is executed!!");
        return datacenter;
    }


}

