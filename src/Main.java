
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu menu = new Menu();
		
		menu.showMenu();
		int chosse = menu.Choose();
		
		if(chosse == 1)
		{
			Slang slang = new Slang();
			slang.GetListData();
			
		}
	}

}