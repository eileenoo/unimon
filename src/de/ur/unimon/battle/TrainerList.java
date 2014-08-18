package de.ur.unimon.battle;


import java.util.ArrayList;

import de.ur.unimon.unimons.Unimon;
import de.ur.unimon.unimons.UnimonList;

public class TrainerList {
	
	private static Trainer trainer1;
	private static Trainer trainer2;
	private static Trainer trainer3;
	private static Trainer trainer4;
	private static Trainer trainer5;
	private static Trainer trainer6;
	private static Trainer boss;
	public ArrayList<Trainer> TrainerList;
	private ArrayList<Unimon> unimonList;

	public TrainerList(){
		unimonList = new UnimonList().getUnimonList();
		TrainerList = new ArrayList<Trainer>();
		trainer1 = new Trainer(1, "BöserBube1", 48.9979054, 12.0957183, unimonList.get(0), 70, 70);
		trainer2 = new Trainer(2, "BöserBube2", 48.9979054, 12.0957183, unimonList.get(1), 150, 150);
		trainer3 = new Trainer(3, "BöserBube3", 48.9979054, 12.0957183, unimonList.get(2), 250, 250);
		trainer4 = new Trainer(4, "BöserBube4", 48.9979054, 12.0957183, unimonList.get(0), 350, 350);
		trainer5 = new Trainer(5, "BöserBube5", 48.9979054, 12.0957183, unimonList.get(1), 150, 150);
		trainer6 = new Trainer(6, "BöserBube6", 48.9979054, 12.0957183, unimonList.get(2), 150, 150);
		boss = new Trainer(2, "OberböserBube", 48.9979054, 12.0957183, unimonList.get(0), 150, 150);
		TrainerList.add(trainer1);
		TrainerList.add(trainer2);
		TrainerList.add(trainer3);
		TrainerList.add(trainer4);
		TrainerList.add(trainer5);
		TrainerList.add(trainer6);
		TrainerList.add(boss);
	}
}
