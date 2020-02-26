package com.japaneseblades;

import java.util.Scanner;

public class Test {
//A simplified version w/ less procedures in the main
	//The procedures in Test.java put into three methods

public static void main(String[] args) {
	
	System.out.println("Welcome back to Samurai weapons training! Last time we had the katana. "
			+ "\nThis time you will have an option to use a type of katana called tanto, which I *ehem* borrowed from the boss.");
	
	//**Testing of instance methods at MAIN
	
		System.out.println("\nHere's a comparison between the Standard Katana and Tanto.\n");
		Katana [] demoKatanas = new Katana[2];
		demoKatanas[0] = new Katana(60, 40, 60); //60, 70, 60
		demoKatanas[0].setName("Demo Katana");
		
		//**a parent reference assigned to a child object
		demoKatanas[1] = new Tanto(30, 50, 80); //30, 70, 80
		demoKatanas[1].setName("Demo Tanto");
		
		for (int i = 0; i < demoKatanas.length; i++) {
			System.out.println("\n[" + i + "] " + demoKatanas[i].getName() + " ~--------------------------------------------------------");
			demoKatanas[i].sheath();
			System.out.println("\nIt has a blade length of " + demoKatanas[i].getBladeLength() + " cm.");
			System.out.println("Sharpness of " + demoKatanas[i].getSharpness());
			System.out.println("Hardness of " + demoKatanas[i].getHardness());
			System.out.println("We now test their efficiency when hit against objects but before that...\n");
			demoKatanas[i].sharpenBlade(30);
			System.out.println("Now Samurai Luke will hit the Target with the " + demoKatanas[i].getName());
		
		//**a method call which passes a polymorphic object
			
			demoKatanas[i].demoSelf(demoKatanas[i]); 	
			//Without a Samurai Class, for now the katana objects will demo themselves
			
			System.out.println("\nThat is it for the demo, the swordsmith shall restore the katana blades to their original state.");
			demoKatanas[i].restoreKatana();
		}//end of for-loop
		
		
		
		System.out.println("\nNow that you have seen them in demo, you may try them in training.");
	
		Scanner sc = new Scanner(System.in);
		System.out.println("\nKatana [0]");
		System.out.println("\nTanto  [1]");
		System.out.print("\nChoose a weapon to train with: [0 | 1]");
		int choose = sc.nextInt();
		
		switch (choose) {
		case 0: Katana myKatana = new Katana(60, 70, 60);
				myKatana.setupTraining();
				myKatana.startTraining();
				myKatana.endTraining();
				break;
		case 1: Katana myTanto = new Tanto(30, 70, 80);
				myTanto.setupTraining();
				myTanto.startTraining();
				myTanto.endTraining();
				break;
		}
		
	}
}
