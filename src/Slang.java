import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Slang {
	private TreeMap<String, List<String>> map = new TreeMap<>();
	private int sizeMap;
	private String FILE_SLANG = "slang.txt";
	private String FILE_ORIGINAL_SLANG = "slangOri.txt";
	private String FILE_HISTORYSLANG = "history.txt";

	public Slang()
	{
		try 
		{
			String currnentPath  = new java.io.File("./src").getCanonicalPath();
			FILE_SLANG = currnentPath + "\\" + FILE_SLANG;
			FILE_ORIGINAL_SLANG = currnentPath + "\\" + FILE_ORIGINAL_SLANG;
			FILE_HISTORYSLANG = currnentPath + "\\" + FILE_HISTORYSLANG;
			readFile(FILE_SLANG);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
	}
	
	public void readFile(String file)
	{
		try 
		{
			map.clear();
			String slag = null;
			Scanner scanner = new Scanner(new File(file));
			scanner.useDelimiter("`");
			scanner.next();
			String temp = scanner.next();
			String[] part = temp.split("\n");
			int i = 0;
			int flag = 0;
			sizeMap = 0;
			while (scanner.hasNext()) {
				List<String> meaning = new ArrayList<String>();
				slag = part[1].trim();
				temp = scanner.next();
				part = temp.split("\n");
				if (map.containsKey(slag)) {
					meaning = map.get(slag);
				}
				if (part[0].contains("|")) {
					System.out.println(part[0]);
					String[] d = (part[0]).split("\\|");
					for (int ii = 0; ii < d.length; ii++)
						System.out.println(d[ii]);
					Collections.addAll(meaning, d);
					sizeMap += d.length - 1;
				} else {
					meaning.add(part[0]);
				}
				// map.put(slag.trim(), meaning);
				map.put(slag, meaning);
				i++;
				sizeMap++;
			}
			scanner.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public String[][] GetListData()
	{
		String s[][] = new String[sizeMap][3];
		try 
		{
			Set<String> slagListSet = map.keySet();
			Object[] slagList = slagListSet.toArray();
			int index = 0;
			for (int i = 0; i < sizeMap; i++) {
				s[i][0] = String.valueOf(i);
				s[i][1] = (String) slagList[index];
				List<String> meaning = map.get(slagList[index]);
				s[i][2] = meaning.get(0);
				System.out.println(s[i][0] + "\t" + s[i][1] + "\t" + s[i][2]);
				for (int j = 1; j < meaning.size(); j++) {
					if (i < sizeMap)
						i++;
					s[i][0] = String.valueOf(i);
					s[i][1] = (String) slagList[index];
					s[i][2] = meaning.get(j);
					System.out.println(s[i][0] + "\t" + s[i][1] + "\t" + s[i][2]);
				}
				index++;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return s;
	}
	
}
