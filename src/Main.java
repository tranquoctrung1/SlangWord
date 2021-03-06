import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Menu menu = new Menu();
		int choose;
		do {
			menu.showMenu();
			choose = menu.Choose();
			
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
			else if(choose == 6)
			{
				Slang slang = new Slang();
				System.out.print("Nhap tu can xoa vao: ");
				Scanner sc = new Scanner(System.in);
				String key = sc.nextLine();
				System.out.print("Nhap nghia can xoa vao: ");
				sc = new Scanner(System.in);
				String meaning = sc.nextLine();
				
				slang.delete(key, meaning);
				
			}
			else if(choose == 7)
			{
				Slang slang = new Slang();
				slang.reset();
			}
			else if(choose == 8 ) {
				Slang slang = new Slang();
				String[] result = slang.random();
				
				System.out.println(result[0] + "\t" + result[1]);
			}
			else if(choose == 9)
			{
				Slang slang = new Slang();
				
				String[] result = slang.quiz(1);
				System.out.println("Slang Word la: " + result[0] + " voi 4 dap an ");
				System.out.println("1/ " + result[1]);
				System.out.println("2/ " + result[2]);
				System.out.println("3/ " + result[3]);
				System.out.println("4/ " + result[4]);
				System.out.print("Ban chon dap an so: ");
				Scanner sc = new Scanner(System.in);
				int choosen = sc.nextInt();
				if(choosen == 1 || choosen == 2 || choosen == 3 || choosen == 4)
				{
					if(result[choosen] == result[5])
					{
						System.out.println("Dap an chinh xac");
					}
					else 
					{
						System.out.println("Dap an khong chinh xac");
					}
				}
				else 
				{
					System.out.println("Khong co dap an");
				}
				
			}
			else if(choose == 10)
			{
				Slang slang = new Slang();
				
				String[] result = slang.quiz(2);
				
				System.out.println("Definition la: " + result[0] + " voi 4 dap an ");
				System.out.println("1/ " + result[1]);
				System.out.println("2/ " + result[2]);
				System.out.println("3/ " + result[3]);
				System.out.println("4/ " + result[4]);
				System.out.print("Ban chon dap an so: ");
				Scanner sc = new Scanner(System.in);
				int choosen = sc.nextInt();
				if(choosen == 1 || choosen == 2 || choosen == 3 || choosen == 4)
				{
					if(result[choosen] == result[5])
					{
						System.out.println("Dap an chinh xac");
					}
					else 
					{
						System.out.println("Dap an khong chinh xac");
					}
				}
				else 
				{
					System.out.println("Khong co dap an");
				}
			}
			else 
			{
				System.out.println("Da thoat chuong trinh");
				System.exit(0);
			}
		}
		while(choose >= 1 && choose <= 10);
		
	}

}

