package testProject;

import interfaces.Drivable;

public class Truck implements Drivable{

	private int wheels;
	private int capacity;
	private int doors;
	private boolean manual;
	
	public Truck() {
		wheels = 8;
		capacity = 3;
		doors = 2;
		manual = false;
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
		return getDoors();
	}
	
	public int isHeavy(int a, int c, int d, int j, int f){
		isManual();
		return 0;
	}
	
	public void switchStat(){
		switch(capacity){
			case 1:
			break;
			case 2:
			break;
			case 3:
			break;
			case 4:
			break;
			case 5:
			break;
			case 6:
			break;
			case 7:
			break;
			case 8:
			break;
			case 9:
			break;
			default:
			break;
		}
	}
}
