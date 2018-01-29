package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import ICCharger.IDManager;
import ICCharger.ShopCharger;
import ICCharger.StudentCard;

public class Scenario {
    static String CONTAINER = "app/container";

    public static void main(String[] args) {
	File container = new File(CONTAINER);
	File ds = null;
	if(container.exists()){
	    BufferedReader br = null;
	    try{
		br = new BufferedReader(new FileReader(container));
		String line = br.readLine();
		if(line == null){
		    throw new IOException("empty file");
		}
		ds = new File(line);
		if(! ds.exists()){
		    throw new IOException("no datasource file");
		}
	    }catch(Exception e){
		e.printStackTrace();
	    }finally{
		if(br != null){
		    try{
			br.close();
		    }catch(Exception e){
			e.printStackTrace();
		    }
		}
	    }

	    IDManager idm;
	    ShopCharger cg = new ShopCharger();
	    try{
		idm = IDManager.getInstance();
		idm.setFile(ds);
		StudentCard c1 = idm.getCard(1);
		StudentCard c2 = idm.getCard(2);

		cg.chargeMoney(500);
		cg.insertStudentCard(c1);
		cg.chargeMoney(1000);
		cg.insertStudentCard(c2);
		cg.chargeMoney(1500);
		cg.chargeMoney(-500);

		Map<String, Integer> emotions = idm.getEmotion(c1);
		System.out.println(c1.getName()+"'s text: "+c1.getText());
		System.out.println("Emotion: (like, joy, anger) = "+
				   String.format("(%d, %d, %d)",
						 emotions.get("likedislike"),
						 emotions.get("joysad"),
						 emotions.get("angerfear")));
	    }catch(Exception e){
		e.printStackTrace();
	    }
	}else{
	    System.err.println("Cannot find \"container\" file");
	}
    }
}
