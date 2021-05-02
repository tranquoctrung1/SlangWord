import java.util.List;
import java.util.Scanner;

public class Menu {
	public void showMenu()
	{
		System.out.println("1/ Tim Kiem theo Slang Word");
		System.out.println("2/ Tim Kiem theo Slang Word Theo Definition");
		System.out.println("3/ Hien thi history");
		System.out.println("4/ Them 1 Slang Word moi ");
		System.out.println("5/ Edit 1 Slang Word");
		System.out.println("6/ Delete 1 Slang Word");
		System.out.println("7/ Reset danh sach Slang Word");
		System.out.println("8/ Random 1 Slang Word");
		System.out.println("9/ Do vui theo Slang Word");
		System.out.println("10/ Do vui theo Definition");
		System.out.println("0/ Thoat chương trinh");
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
