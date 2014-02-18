package se.modu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Generator {
	

	//public ArrayList<String> businessName;
	public String businessName[] = {"Bordello of",
			"Parlour of",
			"Den of",
			"House of",
			"Boudoir of",
			"Garden of",
			"Brothel of",
			"Haven of",
			"Grand (Roll again)",
			"Chateu of",
			"Temple of",
			"Home of",
			"Hall of",
			"Palace of",
			"Dress-House of",
			"Shrine of",
			"Madam <Name>'s",
			"<Name>'s",
			"<Name>'s",
			"Reverse 1&2"};
	public String businessName2[] = {"a Dozen",
			"Countless",
			"Silent",
			"Golden",
			"Forbidden",
			"Incredible",
			"Golded",
			"Wanton",
			"One Thousand",
			"Beautiful",
			"Wondrous",
			"Lovely",
			"Heavenly",
			"Common",
			"Welcome",
			"Secret",
			"Earthly",
			"Jade",
			"Simple",
			"Painted"};
	public String businessName3[] = {"Wenches",
			"Delights",
			"Relaxations",
			"Secrets",
			"Company",
			"Harlots",
			"Lusts",
			"Tarts",
			"Comforts",
			"Petals",
			"Ladies",
			"Flowers",
			"Massages",
			"Treasures",
			"Lasses",
			"Pleasures",
			"Lanterns",
			"Courtesans",
			"Acts",
			"Seamstresses"};
	
	private String name = "";
	private Random rand;
	private String qualityAndPrice;
	private String size;
	private int noOfCourtesans;
	private List<String> specialties;
	private ArrayList<Integer> ignoreList = new ArrayList<Integer>(); // ugly solution, used for the specialties
	
	public Generator(String charName){
		
		rand = new Random();
		name = genName(charName,new ArrayList<Integer>());
		qualityAndPrice = qualityAndPrice();
		size = sizeOfBrothel();
		noOfCourtesans = noOfCourtesans(size); 
		specialties = specialties();
	}
	
	private String genName(String charName, ArrayList<Integer> ignoreList){
		
		String name = "";
		int d20 = rand.nextInt(20);
		int d20_2 = rand.nextInt(20);
		int d20_3 = rand.nextInt(20);

		
		while(ignoreList.contains(d20)){
			d20 = rand.nextInt(20);
		}
		//System.out.println("Debug d20: "+d20);
		//System.out.println("Debug d20_2: "+d20_2);
		if(d20 ==  8){
			ignoreList.add(d20);
			name = "Grand "+genName(charName, ignoreList);
		} else if (d20 == 16){
			ignoreList.add(16);
			name = "Madam "+charName+"'s"; 
		} else if (d20 == 17 || d20 == 18){
			ignoreList.add(17);
			ignoreList.add(18); // I think, ask tarra if both should be added or just d20
			name = charName + "'s "+genName(charName, ignoreList); 
		} else if (d20 == 19){
			while(d20==19){
				d20 = rand.nextInt(20);
			}
			name = businessName2[d20_2]+" "+businessName[d20]+" "+businessName3[d20_3];
		} else {
			name = businessName[d20]+" "+businessName2[d20_2]+" "+businessName3[d20_3];
		}
		return name;
	}
	
	
	private String qualityAndPrice(){
		int d4_1 = rand.nextInt(4)+1;
		int d4_2 = rand.nextInt(4)+1;
		if(d4_1+d4_2 == 2){
			return "Filthy & Flea Infested (x 1/6)";
		} else if(d4_1+d4_2 == 3){
			return "Dirty & Poor (x 1/6)";
		} else if(d4_1+d4_2 == 4){
			return "Poorly Maintained (x 4/5)";
		} else if(d4_1+d4_2 == 5){
			return "Average (x 1)";
		} else if(d4_1+d4_2 == 6){
			return "Well Maintained (x 2)";
		} else if(d4_1+d4_2 == 7){
			return "Upper class (x 5)";
		} 
		return "5-Star Accomodations (x 10)"; // if it reaches here it should be 8
	}
	
	private String sizeOfBrothel(){
		int d4_1 = rand.nextInt(4)+1;
		int d4_2 = rand.nextInt(4)+1;
		if(d4_1+d4_2 == 2){
			return "Single Room";
		} else if(d4_1+d4_2 == 3){
			return "Two Room House";
		} else if(d4_1+d4_2 == 4){
			return "Multi-Room House";
		} else if(d4_1+d4_2 == 5){
			return "Two-Story Building";
		} else if(d4_1+d4_2 == 6){
			return "Large Building(s)";
		} else if(d4_1+d4_2 == 7){
			return "Mansion";
		} 
		return "Estate"; // if it reaches here it should be 8
	}
	
	
	/*
	 * This could be done differently, but I like sending it as an argument with
	 * the string, otherwise we could store the previous roll as an int or whatever,
	 * but this is good enough
	*/
	private int noOfCourtesans(String buildingType){
		if(buildingType.equals("Single Room")){
			return rand.nextInt(2)+1; // 1d2
		} else if(buildingType.equals("Two Room House")){
			return rand.nextInt(4)+1; // 1d4
		} else if(buildingType.equals("Multi-Room House")){
			return rand.nextInt(6)+1+1; // 1d6+1
		} else if(buildingType.equals("Two-Story Building")){
			return rand.nextInt(8)+1+1; // 1d8+1
		} else if(buildingType.equals("Large Building(s)")){
			return rand.nextInt(6)+1+rand.nextInt(6)+1; // 2d6
		} else if(buildingType.equals("Mansion")){
			return rand.nextInt(10)+1+rand.nextInt(10)+1; // 2d10
		}
		// if it gets this far, the result should be mansion:
		return rand.nextInt(20)+1+rand.nextInt(20)+1; // 2d20
	}

	private List<String> specialties(){
		ArrayList<String> list = new ArrayList<String>();
		int d8_1 = rand.nextInt(8)+1;
		int d8_2 = rand.nextInt(8)+1;
		while(ignoreList.contains(d8_1+d8_2)){// according to tarra
			d8_1 = rand.nextInt(8)+1;
			d8_2 = rand.nextInt(8)+1;
		}
		int d8 = d8_1+d8_2;
		if(d8 == 2){
			ignoreList.add(2);
			ignoreList.add(3);// according to tarra
			System.out.println("DEBUG: ROLLING THREE MORE TIMES");
			list.addAll(specialties());
			list.addAll(specialties());
			list.addAll(specialties());
		} else if (d8 == 3){
			ignoreList.add(2);
			ignoreList.add(3);// according to tarra
			System.out.println("DEBUG: ROLLING TWO MORE TIMES");
			list.addAll(specialties());
			list.addAll(specialties());
		} else if(d8 == 4){
			ignoreList.add(4);
			list.add("Drugs / Narcotics (x2)");
		} else if(d8 == 5){
			ignoreList.add(5);
			list.add("Illusions (x3)");
		} else if(d8 == 6){
			ignoreList.add(6);
			list.add("Slaves (x1)");
		} else if(d8 == 7){
			ignoreList.add(7);
			list.add("Young Courtesans (x2)");
		} else if(d8 == 8){
			ignoreList.add(8);
			list.add("Massage Service (x1/2)");
		} else if(d8 == 9){
			ignoreList.add(9);
			list.add("Large Courtesans (x1)");
		} else if(d8 == 10){
			ignoreList.add(10);
			list.add("Unusual Races (x2-5)");
		} else if(d8 == 11){
			ignoreList.add(11);
			list.add("Unusual Races (x2-5)");
		} else if(d8 == 12){ 
			ignoreList.add(12);
			list.add("Domination (x3)");
		} else if(d8 == 13){
			ignoreList.add(13);
			list.add("Sodomy (x2)");
		} else if(d8 == 14){
			ignoreList.add(14);
			list.add("Monstrous Races (x5)");
		} else if(d8 == 15){
			ignoreList.add(15);
			int d4_3 = rand.nextInt(4)+1;
			int d4_4 = rand.nextInt(4)+1;
			list.add("Unspeakable Acts (x"+(d4_3+d4_4)+")"); // ask tarra about, roll?
		} else if(d8 == 16){
			ignoreList.add(16);
			list.add("Torture & Murder (x50)");
		}
		return list;
	}
	
	public void print(){
		System.out.println("Name: " + name);
		System.out.println("Quality and price: "+qualityAndPrice);
		System.out.println("Size of brothel: "+size);
		System.out.println("Number of courtesans: "+noOfCourtesans);
		System.out.println("Specialties: ");
		for(String specialty : specialties){
			System.out.println(specialty);
		}
	}
	public static void main(String[] args) {
		System.out.print("Enter character name: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Generator gen = new Generator(input);
		gen.print();
	}
}
