package models;

public class account {
	private String nama;
	private double balance;
	
	public account(double startingBalance){
		nama = "Unassigned";
		balance = startingBalance;
	}
	
	public account(String nama, double startingBalance) {
		this.nama = nama;
		balance = startingBalance;
	}
	
	public void add(double val) {
		balance += val;
	}
	
	public void deduct(double val) {
		balance -= val;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String getNama() {
		return nama;
	}
	
	public void setBalance(double val) {
		balance = val;
	}
	
	public void setNama( String val) {
		nama = val;
	}
}
