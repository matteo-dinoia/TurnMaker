package backend;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Utils {
	public final static String file="nomi.txt";
	
	

	private static String formattaTesto(ArrayList<String> list, int n) {
		String risultato = "";

		int i = 0;
		if (n <= 1)
			n = list.size() + 1;

		for (String s : list) {

			if (i % n == 0 && i != 0)
				risultato += "\n";
			risultato += s + "\n";

			i++;
		}

		return risultato;
	}



	public static String getTextFormatted(String perTurnoStringa)  throws FileVuoto {
		int perTurno =intValueOf(perTurnoStringa);
		
		ArrayList<String> array=readLinesFile();
		Collections.shuffle(array);
		
		return formattaTesto(array, perTurno);
	}

	@SuppressWarnings("resource")
	private static ArrayList<String> readLinesFile() throws FileVuoto{
		ArrayList<String> risultato=null;
		try {
			File f = new File(Utils.file);
			if(!f.exists()) f.createNewFile();
			BufferedReader bReader=new BufferedReader(new FileReader(f));
			
			List<String> list=bReader.lines().collect(Collectors.toList());
			bReader.close();
			risultato=new ArrayList<String>(list);
		}
		catch(Exception e){
			e.printStackTrace();
			throw new FileVuoto();
		}
		
		
		if(risultato.size()<=0) {
			throw new FileVuoto();
		}
		
		return risultato;
	}

	private static int intValueOf(String perTurnoStringa) {
		int perTurno;
		
		try {
			perTurno = Integer.valueOf(perTurnoStringa);
		} catch (NumberFormatException numEx) {
			return -1;
		}
		
		return perTurno;
	}
}
