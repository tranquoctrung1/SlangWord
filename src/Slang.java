import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
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
					String[] d = (part[0]).split("\\|");
					Collections.addAll(meaning, d);
					sizeMap += d.length - 1;
				} else {
					meaning.add(part[0]);
				}
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
	
	public String[][] getListData()
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
	
	public String[][] getMeaningByKeyWord(String key) {
		List<String> listMeaning = map.get(key);
		if (listMeaning == null)
			return null;
		int size = listMeaning.size();
		String s[][] = new String[size][3];
		for (int i = 0; i < size; i++) {
			s[i][0] = String.valueOf(i);
			s[i][1] = key;
			s[i][2] = listMeaning.get(i).trim();
		}
		return s;
	}
	
	public String[][] findDefinition(String key) {
		List<String> keyList = new ArrayList<>();
		List<String> meaningList = new ArrayList<>();
		for (Entry<String, List<String>> entry : map.entrySet()) {
			List<String> meaning = entry.getValue();
			for (int i = 0; i < meaning.size(); i++) {
				if (meaning.get(i).toLowerCase().contains(key.toLowerCase())) {
					keyList.add(entry.getKey());
					meaningList.add(meaning.get(i));
				}
			}
		}
		int size = keyList.size();
		String s[][] = new String[size][3];

		for (int i = 0; i < size; i++) {
			s[i][0] = String.valueOf(i);
			s[i][1] = keyList.get(i);
			s[i][2] = meaningList.get(i);
		}
		return s;
	}
	
	public void saveHistoryFile(String slang, String meaning)
	{
		try 
		{
			File file1 = new File(FILE_HISTORYSLANG);
			FileWriter fr = new FileWriter(file1, true);
			fr.write(slang + "`" + meaning + "\n");
			fr.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public String[][] readHistoryFile() {
		List<String> historySlag = new ArrayList<>();
		List<String> historyDefinition = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File(FILE_HISTORYSLANG));
			scanner.useDelimiter("`");
			String temp = scanner.next();
			String[] part = scanner.next().split("\n");
			historySlag.add(temp);
			historyDefinition.add(part[0]);
			while (scanner.hasNext()) {
				temp = part[1];
				part = scanner.next().split("\n");
				historySlag.add(temp);
				historyDefinition.add(part[0]);
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int size = historySlag.size();
		String s[][] = new String[size][3];
		for (int i = 0; i < size; i++) {
			s[size - i - 1][0] = String.valueOf(size - i);
			s[size - i - 1][1] = historySlag.get(i);
			s[size - i - 1][2] = historyDefinition.get(i);
		}
		return s;
	}
	
	void saveFile(String file) {
		try {
			PrintWriter printWriter = new PrintWriter(new File(file));
			StringBuilder stringBuilder = new StringBuilder();

			stringBuilder.append("Slag`Meaning\n");
			String s[][] = new String[map.size()][3];
			Set<String> keySet = map.keySet();
			Object[] keyArray = keySet.toArray();
			for (int i = 0; i < map.size(); i++) {
				Integer in = i + 1;
				s[i][0] = in.toString();
				s[i][1] = (String) keyArray[i];
				List<String> meaning = map.get(keyArray[i]);
				stringBuilder.append(s[i][1] + "`" + meaning.get(0));
				for (int j = 1; j < meaning.size(); j++) {
					stringBuilder.append("|" + meaning.get(j));
				}
				stringBuilder.append("\n");
			}
			printWriter.write(stringBuilder.toString());
			printWriter.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void addNew(String slag, String meaning) {
		try {
			List<String> meaningList = new ArrayList<>();
			meaningList.add(meaning);
			sizeMap++;
			map.put(slag, meaningList);
			this.saveFile(FILE_SLANG);
		}
		catch(Exception ex)
		{
			System.out.println("Them khong thanh cong");
		}
		
	}
	
	public boolean checkSlangExists(String slag) {
		for (String keyIro : map.keySet()) {
			if (keyIro.equals(slag))
				return true;
		}
		return false;
	}
	
	public void addDuplicate(String slag, String meaning) {
		try {
			List<String> meaningList = map.get(slag);
			meaningList.add(meaning);
			sizeMap++;
			map.put(slag, meaningList);
			this.saveFile(FILE_SLANG);
		}
		catch(Exception ex)
		{
			System.out.println("Them khong thanh cong");
		}
		
	}

	public void addOverwrite(String slag, String meaning) {
		try
		{
			List<String> meaningList = map.get(slag);
			meaningList.set(0, meaning);
			map.put(slag, meaningList);
			this.saveFile(FILE_SLANG);
		}
		catch(Exception ex)
		{
			System.out.println("Them khong thanh cong");
		}
		
	}
	
	public void edit(String slag, String oldValue, String newValue) {
		try
		{
			List<String> meaning = map.get(slag);
			int index = meaning.indexOf(oldValue);
			meaning.set(index, newValue);
			this.saveFile(FILE_SLANG);
			System.out.println(oldValue + "\t" + newValue);
		}
		catch(Exception ex)
		{
			System.out.println("Sua khong thanh cong \n Tu sua va nghia cu phai co trong danh sach tu dien");
			
		}
		
	}
	
	public void delete(String slag, String value) {
		try 
		{
			List<String> meaningList = map.get(slag);
			int index = meaningList.indexOf(value);
			if (meaningList.size() == 1) {
				map.remove(slag);
			} else {
				meaningList.remove(index);
				map.put(slag, meaningList);
			}
			sizeMap--;
			this.saveFile(FILE_SLANG);
			System.out.println("Xoa thanh cong");
		}
		catch(Exception ex)
		{
			System.out.println("Xoa khong thanh cong \n Tu xoa va nghia xoa phai co trong danh sach tu dien");
		}
		
	}
	
	public void reset() {
		try {
			readFile(FILE_ORIGINAL_SLANG);
			this.saveFile(FILE_SLANG);
			System.out.println("Reset file thanh cong");
		} catch (Exception e) {
			System.out.println("Khong the reset file");
			e.printStackTrace();
		}
	}
	
	public String[] random() {
		int minimum = 0;
		int maximum = map.size() - 1;
		int rand = (minimum + (int) (Math.random() * maximum));
		String s[] = new String[2];
		int index = 0;
		for (String key : map.keySet()) {
			if (index == rand) {
				s[0] = key;
				s[1] = map.get(key).get(0);
				break;
			}
			index++;
		}
		return s;
	}
	public String[] quiz(int type) {
		String s[] = new String[6];
		if (type == 1) {
			// Random a number
			String[] slangRandom = this.random();
			s[0] = slangRandom[0];
			int rand = (1 + (int) (Math.random() * 4));
			s[rand] = slangRandom[1];
			s[5] = slangRandom[1];
			for (int i = 1; i <= 4; i++) {
				if (rand == i)
					continue;
				else {
					String[] slangRand = this.random();
					while (slangRand[0] == s[0]) {
						slangRand = this.random();
					}
					s[i] = slangRand[1];
				}
			}
		} else {
			// Random a number
			String[] slangRandom = this.random();
			s[0] = slangRandom[1];
			int rand = (1 + (int) (Math.random() * 4));
			s[rand] = slangRandom[0];
			s[5] = slangRandom[0];
			for (int i = 1; i <= 4; i++) {
				if (rand == i)
					continue;
				else {
					String[] slangRand = this.random();
					while (slangRand[0] == s[0]) {
						slangRand = this.random();
					}
					s[i] = slangRand[0];
				}
			}
		}

		return s;
	}
}
