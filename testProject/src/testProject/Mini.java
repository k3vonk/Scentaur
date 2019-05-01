package testProject;

public class Mini extends Car{

	public int i;
	public int j;
	@Override
	public int getWheels() {
		return 4;
	}
	
	public int doSme(int a, int b, int c) {
		a += a + b;
		return b;
	}
}
