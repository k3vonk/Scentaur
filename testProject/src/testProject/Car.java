package testProject;

import interfaces.Drivable;

public class Car implements Drivable{

	private int wheels;
	private int capacity;
	private int doors;
	private boolean manual;
	
	public Car() {
		wheels = 4;
		capacity = 5;
		doors = 4;
		manual = true;
	}
	
	public int getWheels() {
		return wheels;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getDoors() {
		return doors;
	}


	public boolean isManual() {
		return manual;
	}

	public void longP(int a, int b, int c, int d, int e, int f, int g) {
		//Do something
		//
		
		
		//
	}
}
