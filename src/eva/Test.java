package eva;

import java.io.File;

public class Test {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		File test = new File("test/test2/u1.res");
		if(test == null){
			System.out.println("null!");
		}else{
			System.out.println("ok");
		}
	}

}
