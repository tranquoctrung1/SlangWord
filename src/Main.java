import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu menu = new Menu();
		
		menu.showMenu();
		int choose = menu.Choose();
		
		if(choose == 1)
		{
			System.out.print("Nhap tu can tim kiem vao: ");
			Scanner sc = new Scanner(System.in);
			String key = sc.nextLine();
			
			Slang slang = new Slang();
			
			String[][] result = slang.getMeaningByKeyWord(key);
			if(result != null)
			{
				String meaningToWriteHistoryFile = "";
				
				System.out.println("Danh sach nghia cua cac tu ");
				for(int i = 0; i < result.length; i++)
				{
					System.out.println(result[i][2]);
					if(i == 0)
					{
						meaningToWriteHistoryFile += result[i][2];
					}
					else 
					{
						meaningToWriteHistoryFile += "| " + result[i][2];
					}
				}
				
				slang.saveHistoryFile(key, meaningToWriteHistoryFile);
			}
			else {
				System.out.println("Khong co du lieu");
			}
			
		}
		else if(choose == 2)
		{
			System.out.print("Nhap tu can tim kiem vao: ");
			Scanner sc = new Scanner(System.in);
			String key = sc.nextLine();
			
			Slang slang = new Slang();
			
			String[][] result = slang.findDefinition(key);
			if(result != null)
			{
				System.out.println("Danh sach Slang Word co chua tu do");
				for(int i = 0; i < result.length; i++)
				{
					System.out.println(result[i][1]);
				}
			}
			else 
			{
				System.out.println("Khong co du lieu");
			}
			
		}
		else if(choose == 3)
		{
			Slang slang = new Slang();
			String[][] result = slang.readHistoryFile();
			
			System.out.println("Danh sach cac tu da tim kiem");
			for(int i =0; i < result.length; i++)
			{
				System.out.println(result[i][1] + "\t" + result[i][2]);
			}
		}
		else if(choose == 4)
		{
			Slang slang = new Slang();
			
			System.out.print("Nhap tu can them vao: ");
			Scanner sc = new Scanner(System.in);
			String key = sc.nextLine();
			System.out.print("Nhap nghia can them vao: ");
			sc = new Scanner(System.in);
			String meaning = sc.nextLine();
			
			boolean checkSlangExists = slang.checkSlangExists(key);
			if(checkSlangExists == true)
			{
				System.out.println("Tu nay da co trong tu dien!");
				System.out.println("Chon cac cach de xu ly trung!");
				System.out.println("1/ Overwrite");
				System.out.println("2/ Duplicate");
				System.out.println("0/ Thoat");
				System.out.print("Ban chon: ");
				sc = new Scanner(System.in);
				int choosen = sc.nextInt();
				if(choosen == 1)
				{
					slang.addOverwrite(key, meaning);
					System.out.println("Them theo kieu Overwrite thanh cong");
				}
				else if(choosen == 2)
				{
					slang.addDuplicate(key, meaning);
					System.out.println("Them theo kieu Duplicate thanh cong");
				}
				else 
				{
					System.out.println("Da thoat chuong trinh");
					System.exit(0);
				}
			}
			else 
			{
				slang.addNew(key, meaning);
				System.out.println("Them moi thanh cong");
			}
		}
		else if(choose == 5 )
		{
			Slang slang = new Slang();
			
			System.out.print("Nhap tu can sua vao: ");
			Scanner sc = new Scanner(System.in);
			String key = sc.nextLine();
			System.out.print("Nhap nghia cu can sua vao: ");
			sc = new Scanner(System.in);
			String oldMeaning = sc.nextLine();
			System.out.print("Nhap nghia moi can sua vao: ");
			sc = new Scanner(System.in);
			String newMeaning = sc.nextLine();
			
			slang.edit(key, oldMeaning, newMeaning);
		}
		else 
		{
			System.out.println("Da thoat chuong trinh");
			System.exit(0);
		}
	}

}

