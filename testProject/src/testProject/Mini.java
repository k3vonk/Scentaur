package testProject;

public class Mini extends Car{

	int p;
	public static final int j = 0;
	
	@Override
	public int getWheels() {
		return 4;
	}
	
	public int doSme(int a, int b) {
		a += a + b;
		return b;
	}
}
