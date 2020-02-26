package com.japaneseblades;

import java.util.Scanner;
public class Katana {
	//attributes
	protected boolean isSheathed = true;
	private String name; //samurai named their katanas
	private int bladeLength;
	private int sharpness;
	private int bladeHardness;
	
	//constructor
	public Katana(int bladeLength, int sharpness, int bladeHardness) {
			this.bladeLength = bladeLength;
			this.sharpness = sharpness;
			this.bladeHardness = bladeHardness;
			
	}
	
 	//methods
	public void sheath() {
		if (isSheathed) {
			isSheathed = false;
			System.out.println("\nUNSHEATH: " + this.getName() + " has been unsheathed and ready.");
		} else {
			isSheathed = true;
			System.out.println("\nSHEATH: " + this.getName() + " has been sheathed.");
		}
	}
	
	public void setName(String swordName) {
		name = swordName;
		//System.out.println("\nAs your sword, " + swordName + ", now is the shelter of your warrior spirit!");
	}
	public String getName() {
		return name;
	}
	
	public int getBladeLength() {
		return bladeLength;
	}
	public void sharpenBlade() {
		if (bladeHardness > 0) {
		sharpness += (int)(Math.random()*30 + 30);
		System.out.println("SHARPENBLADE: The katana's edge has been sharpened to " + sharpness);
		} else {
			System.out.println("SHARPENBLADE: Unable to sharpen, for your Katana blade is broken");
		}
	}
	public void sharpenBlade(int sharpAmt) {
		if (bladeHardness > 0) {
			this.sharpness += sharpAmt;
			System.out.println("SHARPENBLADE: The katana's edge has been sharpened to " + sharpness);
			} else {
				System.out.println("SHARPENBLADE: Unable to sharpen, for your Katana blade is broken");
			}
	}
	
	public void setSharpness(int sharpAmt) { 
		this.sharpness = sharpAmt;
	}
	//Although the constructor already does the jobs of these setters, I found that Private super class members are invisible to subclass
	//So these setters and getters allow the subclass (based only on what I know for now) to use the super class attributes in its overridden methods
		
	public void setHardness(int hardAmt) {
		this.bladeHardness = hardAmt;
	}
	
	public int getSharpness() {
		return sharpness;
	}
	
	public int getHardness() {
		return bladeHardness;
	}
	
	public void slice(String targetName, int numOfSlashes) {
		if (isSheathed) {
			System.out.println("\nMust unsheath katana to slash the " + targetName);
		} else if (bladeHardness == 0) {
			System.out.println("\nYou cannot slash with a broken blade!");
		} else if (sharpness <= 15) { //11 slashes from 60 sharpness
			System.out.println("\nSLICE: The blade is dull and unable to thoroughly slash the " + targetName);
			sharpness -= (10*numOfSlashes);
			bladeHardness -= (2*numOfSlashes);
			System.out.println("\nThe katana's sharpness reduced to " + sharpness + " and hardness to " + bladeHardness);
			System.out.println("It endures greater damage when the blade has dulled!");
		} else if (sharpness > 15) {
			System.out.println("\nSLICE: The " + targetName + " had been slashed " + numOfSlashes + " times!");
			sharpness -= (5*numOfSlashes);
			bladeHardness -= (1*numOfSlashes);
			System.out.println("The katana's sharpness reduced to " + sharpness + " and hardness to " + bladeHardness);
		}
	}
	
	public void restoreKatana() {
		sharpness = 70;
		bladeHardness = 60;
		System.out.println("RESTORE: The weapon's edge sharpness has been restored to " + sharpness);
		System.out.println("RESTORE: The weapon's blade hardness has been restored to " + bladeHardness);
	}
	
	public boolean decide(String yn) {					//acknowledging that deciding isn't a behavior of a katana object, for 
		Scanner sc = new Scanner(System.in);				//something like a Person/Samurai class object
		//Checking if the user types only Y or N		
		while (!yn.equalsIgnoreCase("Y") & !yn.equalsIgnoreCase("N")) {
			yn = sc.next();
			if (!yn.equalsIgnoreCase("Y") & !yn.equalsIgnoreCase("N")) {
				System.out.println("Y or N only! [Y / N]?");
			}
		}
		
		if (yn.equalsIgnoreCase("Y")){
			return true;
		} else {
			return false;
		}
	//end while
	}
	
	public void demoSelf(Katana k) { //a method call which passes a polymorphic object
		k.slice("Demo Target",1);
	}
	
	
	//"Katana Training" methods
	public void setupTraining() {
		System.out.println("As the samurai did by tradition, give your new katana a name.");
		Scanner sc = new Scanner(System.in);
				
		String kName = sc.next();
		setName(kName);
		
		System.out.println("\nAs your sword, " + getName() + ", now is the shelter of your warrior spirit!");
		System.out.println("\nCurrently it has a blade length of " + getBladeLength() +
		", sharpness of " + getSharpness() + " and blade hardness of " + getHardness() + ".");
		
	}
	
	public void startTraining() {
		Scanner sc = new Scanner(System.in);
		boolean willDo = true;
		String yn = "";
		String target1 = "Training Post";
		int numOfSlashes;
		
		
		System.out.println("\nTest the strength and efficiency of " + getName() + " by hitting this " + target1);
		System.out.println("NOTE: Katana sharpness and hardness goes down with each hit."
				+ " By sharpness <= 15, it starts to take double damage.\nDO NOT let it reach hardness 0 or it will BREAK.");
		sheath();
		
		System.out.println("\nNOTE: Feel free to hit the " + target1 + " as many times as you want."
				+ "\nBut make sure the katana does not lose too much sharpness and hardness!");
		
		do {
			
			if (getSharpness() <= 0) {
				System.out.println("\nAUTO ASSIST: Katana blade has become blunt, sharpening now!");
				sharpenBlade();
			}
					
			//slice
			System.out.print("\nYou: How many hits should I do?");
			numOfSlashes = sc.nextInt();
			slice(target1, numOfSlashes);
			
			
			if (getHardness() <= 0) {
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
			
			System.out.print("\nDECIDE: Want to keep going? [Y / N]");
			willDo = decide(yn);
			
		} while (willDo);
	}
	
	public void endTraining() {
		if (getHardness() <= 0) {
			System.out.println("\nWell you broke your named katana! Watch your sword's hardness and stop the training before it reaches 0.");
		} else {
			System.out.println("\nYou worked hard, samurai. Let the magic smith restore your katana and be ready for more trainings!");
			restoreKatana();
		}
		
		System.out.println("\nEnd of training!");
		sheath();
	}
}//end class

