package main;

public class player {

	panel p;
	public int money = 100;
	public int stock = 0;
	public int token;

	public player(panel p) {
		this.p = p;
		token = p.acconut.addAccount("Player", money);
//		System.out.println("player token: " + token);
	}
//	public void load() {
//		
//		
//		
//		try {
//			
//			InputStream is = getClass().getResourceAsStream("/save/data.txt");
//			BufferedReader br = new BufferedReader(new InputStreamReader(is));
//			
////			System.out.println(br.readLine());
//	
//			while(br.ready()) {
//				String line = br.readLine();
//			
//				String input[] = line.split(":");
//			
//				switch(input[0]){
//				case "Money":
//					money = Integer.parseInt(input[1]);
//					break;
//				default:
//					break;
//				}
//			}
////			System.out.println(line);
//			
//			br.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

}
