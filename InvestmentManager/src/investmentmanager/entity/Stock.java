package investmentmanager.entity;

import java.util.Random;

/*Class for stock*/
public class Stock implements Comparable<Stock>{
	private static int idCounter = 0;
	private double price;
	private int stockId;
	private String company;
	private double risk;
	private Random rnd;
	
	private Stock(String company, double initialPrice, double stockRisk) {
		stockId = idCounter;
		idCounter++;
		this.company = company;
		price = initialPrice;
		risk = stockRisk;
		rnd = new Random();
		
	}
	
	public void changeStockPrice() {
		/*change the price according to the risk*/
		price = price * (1-risk + rnd.nextDouble()*(2*risk));
	}
	
	public double getPrice() {
		return price;
	}

	public double getRisk() {
		return risk;
	}

	public int getStockId() {
		return stockId;
	}

	public String getCompany() {
		return company;
	}

	@Override
	public int compareTo(Stock o) {
		double dif = price - o.price;
		if (dif > 0) {
			return 1;
		}
		else if (dif == 0){
			return 0;
		}
		return -1;	}
 
	
	
	
}
