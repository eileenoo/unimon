package de.ur.unimon.trainer;

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

	public TrainerList() {
		unimonList = new UnimonList().getTrainerUnimonsList();
		trainerList = new ArrayList<Trainer>();

		trainer1 = new Trainer(1, "Schleimi", 48.9987501, 12.0936204,
				unimonList.get(0), 150, 200, false);
		trainer2 = new Trainer(2, "Kiffi", 48.9937000, 12.0947636,
				unimonList.get(1), 300, 300, false);
		trainer3 = new Trainer(3, "Robomon_5000", 48.9979429, 12.0973199,
				unimonList.get(2), 300, 400, false);
		trainer4 = new Trainer(4, "Hentaicha", 48.9971498, 12.0951285,
				unimonList.get(3), 120, 400, false);
		trainer5 = new Trainer(5, "Hydroxyethan", 48.9956951, 12.0952814,
				unimonList.get(4), 350, 500, false);
		trainer6 = new Trainer(6, "Asymptoterus", 48.997165, 12.0934727,
				unimonList.get(5), 200, 200, false);
		boss = new Trainer(7, "Legdayskipper", 48.9932459, 12.0966834,
				unimonList.get(6), 1000, 1000, false);

		trainerList.add(trainer1);
		trainerList.add(trainer2);
		trainerList.add(trainer3);
		trainerList.add(trainer4);
		trainerList.add(trainer5);
		trainerList.add(trainer6);
		trainerList.add(boss);
	}

	public ArrayList<Trainer> getTrainerList() {
		return trainerList;
	}

}
