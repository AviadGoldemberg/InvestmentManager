package investmentmanager.entity;

import java.util.Random;
/*Class for stock*/
public class Stock extends Entity{
	private static int idCounter = 0;
	private double price;
	private int stockId;
	private String company;
	private double risk;
	private Random rnd;

	private Stock(String company, double initialPrice, double stockRisk) throws RiskNotValidException {
		stockId = idCounter;
		idCounter++;
		this.company = company;
		price = initialPrice;
		if (stockRisk < 0 || stockRisk > 1)
			throw new RiskNotValidException("Given risk is not valid (1 > risk > 0 )");
		risk = stockRisk;
		rnd = new Random();

	}

	@Override
	public String toString() {
		return "Stock [price=" + price + ", stockId=" + stockId + ", company=" + company + ", risk=" + risk + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return stockId == other.stockId;
	}

	public void changeStockPrice() {
		/* change the price according to the risk */
		price = price * (1 - risk + rnd.nextDouble() * (2 * risk));
	}

	public double getPrice() {
		return price;
	}

	public double getRisk() {
		return risk;
	}

	public String getCompany() {
		return company;
	}


	@Override
	public int getId() {
		return stockId;
	}

	@Override
	public double getValue() {
		return price;
	}

}
