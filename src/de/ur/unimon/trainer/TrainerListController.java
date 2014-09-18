package de.ur.unimon.trainer;

public final class TrainerListController{
	
	private static TrainerList trainerList;
	
	private TrainerListController() {
		
	}
	
	public synchronized static TrainerList getInstance(){
		if (trainerList == null){
			trainerList = new TrainerList();
		}
		return trainerList;
	}
	
	public static void reset() {
	    trainerList = new TrainerList();
	}
	
	public static TrainerList setInstance(TrainerList t){
		trainerList = t;
		return trainerList;
	}

}
