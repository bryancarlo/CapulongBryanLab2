package com.japaneseblades;

import java.util.Scanner;

//The tanto, a type of Japanese sword (katana), was a weapon with a single-edged blade and a curved shape. It was designed for soft targets and was thought 
//to be a hidden weapon of the samurai. It was extremely effective in close fighting.

public class Tanto extends Katana {
	
	private boolean isHidden;
	private boolean isAlive = true;
	
	public Tanto(int bladeLength, int sharpness, int bladeHardness) {
		super (bladeLength, sharpness, bladeHardness);
		isHidden = false;
		
}
	//METHODS for the "actions" of the TANTO-type katana
	public void slice(String targetName, int numOfSlashes) { //overrides Katana class' slice() Method; The difference is in the last else-if
		if (isSheathed) {
			System.out.println("\nMust unsheath tanto to slash the " + targetName);
		} else if (numOfSlashes == 0) {
			this.hide(1);
		} else if (getHardness() == 0) {
			System.out.println("\nYou cannot slash with a broken blade!");
		} else if (getSharpness() <= 15) { //11 slashes from 60 sharpness
			System.out.println("\nSLICE: The blade is dull and unable to thoroughly slash the " + targetName);
			sharpenBlade(getSharpness() - (8*numOfSlashes));
			setHardness(getHardness() - (2*numOfSlashes));
			System.out.println("\nThe tanto's sharpness reduced to " + getSharpness() + " and hardness to " + getHardness());
			System.out.println("It endures greater damage when the blade has dulled!");
		} else if (getSharpness() > 15) {
			System.out.println("\nSLICE: The " + targetName + " had been slashed " + numOfSlashes + " times!");
			setSharpness((getSharpness())-(4*numOfSlashes)); 	//Tanto is more durable due to its short length (Googled it) so it reduces less in sharpness 
			setHardness(getHardness() - (1*numOfSlashes));		//than the standard katana and other longer katanas. 
			System.out.println("The tanto's sharpness reduced to " + getSharpness() + " and hardness to " + getHardness());;
		}
		
	}
	
	public void stab() {
		System.out.println("STAB: You stabbed with the tanto!");
	}
	
	public void stab(String target) {
		System.out.println("STAB: " + target + " were stabbed!");
	}
	
	public void hide(int yesNo){
		if (yesNo == 0) {
			isHidden = false;
			System.out.println("\nUNHIDE: You have put out your tanto.");
		} else if (yesNo == 1) {
			isHidden = true;
			System.out.println("\nHIDE: You have hidden your tanto.");
		}
	}
	
	//Methods for the MAIN METHOD to execute; what the Tanto is used for
	//Overrides Katana superclass "Training" methods
	public void setupTraining() {
		
		System.out.println("It is shorter and is used by the samurai as a hidden weapon. Although short, because of its "
				+ "shorter length, it is a actually more durable!");
		System.out.println("\nAs the samurai did by tradition, give your new weapon a name.");
		
		Scanner sc = new Scanner(System.in);
				
		String tName = sc.next();
		setName(tName);
		System.out.println("\nCurrently it has a blade length of " + getBladeLength() +
		" cm, sharpness of " + getSharpness() + " and blade hardness of " + getHardness() + ".");
		
		
		
	}
	
	
	public void startTraining() { //There is a difference in the scenario w/ the tanto and katana usage
		Scanner sc = new Scanner(System.in);
		boolean willDo = true;
		String yn = "";
		String target1 = "Training Post";
		int numOfSlashes;
		int rand = (int)(Math.random()*4 + 2);
		int ctr = 1;
		
		
		System.out.println("\nTest the strength and efficiency of " + getName() + " by hitting this " + target1);
		System.out.println("NOTE: Your weapon's sharpness and hardness goes down with each hit."
				+ " By sharpness <= 15, it starts to take double damage.\nDO NOT let it reach hardness 0 or it will BREAK.");
		
		sheath();
		
		System.out.println("\nNOTE: Feel free to hit the " + target1 + " as many times as you want."
				+ "\nBut make sure the weapon does not lose too much sharpness and hardness!");
		
		System.out.println("\nWARNING: That tanto is actually stolen from the boss, so if he is nearby, do ZERO[0] hits!"); 
		//With tanto training there is a wandering boss
		
		
		
		do {
			
			if (getSharpness() <= 0) {
				System.out.println("\nAUTO ASSIST: Weapon blade has become blunt, sharpening now!");
				sharpenBlade(50);
			}
			
			if (ctr % rand == 0) { //the boss appears when ctr becomes divisible by int rand as ctr increments.
				System.out.println("(!!!) The boss is nearby, hide() the tanto for a while. He will be enraged "
						+ "if he finds out you are using his tanto for training!");
			}
			//slice
			System.out.print("\nYou: How many hits should I do?");
			numOfSlashes = sc.nextInt();
			slice(target1, numOfSlashes);
			
			
			if (ctr % rand == 0 && numOfSlashes >= 1) {//if the boss is around and he sees u using his weapon
				System.out.println("\n[!!!]The boss saw you using his tanto! He's enraged but don't fight him!\n");
				stab();
				System.out.println("\nThe boss is even angrier, he took the tanto from you!\n");
				stab("You");
				willDo = false;
				isAlive = false;
				break;
			} else if (getHardness() <= 0) {
				System.out.println("Blade hardness reduced to 0. YOU BROKE " + getName() + "!");
				willDo = false;
				break;
			} else if (getSharpness() <= 15) {
				boolean willSharpen = false;
				String yesNo = "";
				System.out.print("\nDECIDE: The blade has gotten dull, would you sharpen it for a while? [Y / N]");
				willSharpen = decide(yesNo);
				
				if (willSharpen) sharpenBlade();
				
			}
			ctr++;
			
			System.out.println("\nThe boss is a bit farther now, but he's still looking for it.");
			System.out.print("\nDECIDE: Want to keep going? [Y / N]");
			willDo = decide(yn);
			
			
			
			
		} while (willDo);
	}
	
	public void endTraining() {
		if (!isAlive) {
			System.out.println("\nYou are dead.");
		} else if (getHardness() <= 0) {
			System.out.println("\nWell you broke your named weapon! Watch your tanto's hardness and stop the training before it reaches 0.");
		} else {
			System.out.println("\nYou worked hard, samurai. Let the magic smith restore your weapon and be ready for more trainings!");
			restoreKatana();
		}
		
		System.out.println("\nEnd of training!");
		if(isAlive) sheath();
	}
}

