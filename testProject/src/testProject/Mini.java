package testProject;

public class Mini extends Car{

	int p;
	
	@Override
	public int getWheels() {
		return 4;
	}
	
	public int doSme(int a, int b) {
		a += a + b;
		return b;
	}
}
