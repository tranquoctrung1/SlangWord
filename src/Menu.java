import java.util.List;
import java.util.Scanner;

public class Menu {
	public void showMenu()
	{
		System.out.println("Tim Kiem theo Slang Word");
		System.out.println("Tim Kiem theo Slang Word Theo Definition");
		System.out.println("Hien thi history");
		System.out.println("Them 1 Slang Word moi ");
		System.out.println("Edit 1 Slang Word");
		System.out.println("Reset danh sach Slang Word");
		System.out.println("Random 1 Slang Word");
		System.out.println("Do vui theo Slang Word");
		System.out.println("Do vui theo Definition");
	}
	
	public int Choose()
	{
		System.out.println("Moi ban chon 1 trong 10 chuc nang!!");
		System.out.print("Ban chon: ");
		Scanner sc = new Scanner(System.in);
		int choose = sc.nextInt();
		System.out.println("Ban da cho chuc nang so: " + choose);
		
		return choose;
	}
}
