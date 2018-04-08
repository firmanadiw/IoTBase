package models;

public class SensorValue extends SensorManager{
	public int value, jumlah, input, output;
	public String node;
	public int addnode() {
		return jumlah;
	}
	
	public void addsensor(String nodal) {
		node= nodal;
	}
	
	public void setvalue() {
		input = 10;
		output = 20;
	}
	
	public int getvalue() {
		return value;
	}

	@Override
	public void addsensor() {
		// TODO Auto-generated method stub
		
	}
}
