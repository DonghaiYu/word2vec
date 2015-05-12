package eva;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Eva {

	public static void main(String[] args) throws FileNotFoundException {
		
		if(args.length != 2){
			System.out.println("arguments error!\ncommond example:java -jar [test file name] [result file name]");
			return;
		}
		
		float precision = 0;
		float recall = 0;
		float F1 = 0;
		
		File test = new File(args[0]);
		File res = new File(args[1]);  

		if(res == null || test == null){
			System.out.println("can't find the files,wrong file name or path!");
		}
		
		int innum = 0;
		int testnum = 0;
		int resnum = 0;
		
		List<String>  testl = new ArrayList<String>();
		
		FileReader fr_test = new FileReader(test);
		FileReader fr_res = new FileReader(res);
		
		String temp = null;
		String[] temps = null;
		
		Scanner sc = new Scanner(fr_test);
		
		while(sc.hasNext()){
			
			temp = sc.nextLine();
			temps = temp.split("\t");
			if(temps.length < 2) continue;
			testl.add(temps[0].trim() + "-" + temps[1].trim());
		}
		testnum = testl.size();
		sc.close();
		
		Scanner sc2 = new Scanner(fr_res);
		while(sc2.hasNext()){
			resnum ++;
			temp = sc2.nextLine();
			temps = temp.split("\t");
			if(temps.length < 2) continue;
			
			temp = temps[0].trim()+ "-" + temps[1].trim();
			if(testl.contains(temp)){
				innum ++;
			}
		}
		sc2.close();
		
		precision = precision_f(innum, resnum);
		recall = recall_f(innum, testnum);
		F1 = F1_f(precision, recall);
		System.out.println("precision :" +precision*100+"%");
		System.out.println("recall :" +recall*100 +"%");
		System.out.println("F1 score:" +F1);
	}
	
	public static float precision_f(int innum,int resnum){
		float precision = (float)innum/(float)resnum;
		return precision;
	}
	
	public static float recall_f(int innum,int testnum){
		float recall = (float)innum/(float)testnum;
		return recall;
	}
	public static float F1_f(float pre,float rec){
		if(pre == 0 && rec == 0) return 0;
		return 2 * pre * rec /(pre + rec);
	}
	
	public static int get_intersection_num(File res,File test) throws FileNotFoundException{
		if(res == null || test == null){
			System.out.println("wrong file name or path!");
			return 0;
		}
		
		int innum = 0;
		int testnum = 0;
		int resnum = 0;
		
		List<String>  testl = new ArrayList<String>();
		
		FileReader fr_test = new FileReader(test);
		FileReader fr_res = new FileReader(res);
		
		String temp = null;
		String[] temps = null;
		
		Scanner sc = new Scanner(fr_test);
		
		while(sc.hasNext()){
			
			temp = sc.nextLine();
			temps = temp.split("\t");
			if(temps.length < 2) continue;
			testl.add(temps[0].trim() + "-" + temps[1].trim());
		}
		testnum = testl.size();
		sc.close();
		
		Scanner sc2 = new Scanner(fr_res);
		while(sc2.hasNext()){
			resnum ++;
			temp = sc2.nextLine();
			temps = temp.split("\t");
			if(temps.length < 2) continue;
			
			temp = temps[0].trim()+ "-" + temps[1].trim();
			if(testl.contains(temp)){
				innum ++;
			}
		}
		sc2.close();
		return innum;
	}

}
