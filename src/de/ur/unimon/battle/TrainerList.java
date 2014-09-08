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
	public ArrayList<Trainer> trainerList;
	private ArrayList<Unimon> unimonList;

	public TrainerList(){
		unimonList = new UnimonList().getUnimonList();
		trainerList = new ArrayList<Trainer>();
		trainer1 = new Trainer(1, "Schleimi", 48.9987501, 12.0936204, unimonList.get(0), 70, 70, false); //Wirtschaft
		trainer2 = new Trainer(2, "Käffchen", 49.0004036, 12.0942936, unimonList.get(1), 150, 150, false); //Vielberth
		trainer3 = new Trainer(3, "Robomon", 48.9979429, 12.0973199, unimonList.get(2), 250, 250, false); //rechtsNebenBib
		//trainer3 = new Trainer(3, "Kugel", 48.998227, 12.095232, unimonList.get(2), 250, 250, false);
		trainer4 = new Trainer(4, "Watermon", 48.9971498, 12.0951285, unimonList.get(0), 350, 350, false); //Brücke
		trainer5 = new Trainer(5, "Cyclohexylenon", 48.9956951, 12.0952814, unimonList.get(1), 150, 150, false); //Chemie
		trainer6 = new Trainer(6, "Asymptoterus", 48.997165, 12.0934727, unimonList.get(2), 150, 150, false); //Mathe
		boss = new Trainer(7, "Legdayskipper", 48.9932459, 12.0966834, unimonList.get(0), 150, 150, false); //Stadion
		trainerList.add(trainer1);
		trainerList.add(trainer2);
		trainerList.add(trainer3);
		trainerList.add(trainer4);
		trainerList.add(trainer5);
		trainerList.add(trainer6);
		trainerList.add(boss);
	}
	
	public ArrayList<Trainer> getTrainerList(){
		return trainerList;
	}
	
}
