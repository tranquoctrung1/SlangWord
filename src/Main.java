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
				System.out.println("Danh sach nghia cua cac tu ");
				for(int i = 0; i < result.length; i++)
				{
					System.out.println(result[i][2]);
				}
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
		else 
		{
			System.out.println("Da thoat chuong trinh");
			System.exit(0);
		}
	}

}
