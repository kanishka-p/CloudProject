package org.cloudbus.cloudsim.examples;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;

public class CloudletCreatorDynamic {
	
		public ArrayList<Cloudlet> createUserCloudlet(int reqTasks,int brokerId){
			ArrayList<Cloudlet> cloudletList = new ArrayList<Cloudlet>();
			
	    	
	    	int id = 0;
	    	int pesNumber=1;
	    	long length = 2000;
	    	long fileSize = 300;
	    	long outputSize = 300;
	    	UtilizationModel utilizationModel = new UtilizationModelFull();
	    	   	
	    	
	    	for(id=0;id<reqTasks;id++){
	    		
	    		long v = ThreadLocalRandom.current().nextLong(length, 6000);
	    		
	    		Cloudlet task = new Cloudlet(id, v, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
	    		task.setUserId(brokerId);
	    		
	    		
	    		
	        	cloudletList.add(task);
	    	}

	    	System.out.println("SUCCESSFULLY Cloudletlist created :)");

			return cloudletList;
		}

}

