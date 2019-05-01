package testProject;

public class Mini extends Car{

	@Override
	public int getWheels() {
		return 4;
	}
	
	public int doSme(int a, int b, int c) {
		a += a + b;
		return b;
	}
}
