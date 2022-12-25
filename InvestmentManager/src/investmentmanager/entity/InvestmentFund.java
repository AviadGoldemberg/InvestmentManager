package investmentmanager.entity;

import java.util.HashMap;

/*Investment fund class*/
class InvestmentFund implements Comparable<InvestmentFund> {

	private static int idCounter = 0;
	private int id;
	private HashMap<Stock, Integer> stocks; // Stock, percentage

	public InvestmentFund() {
		stocks = new HashMap<>();
		id = idCounter;
		idCounter++;
	}

	public void addStock(Stock stock, int amount) {
		stocks.put(stock, amount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvestmentFund other = (InvestmentFund) obj;
		return id == other.id;
	}

	public void removeStock(Stock stock) throws StockNotExistException {
		if(stocks.containsKey(stock)) {
			stocks.remove(stock);
		}
		else {
			throw new StockNotExistException("this stock dosen't exist in this fund");
		}
	}

	private double calculatePercentage(Stock stock) {
		/* Calculate the percentage of each stock from the fund stocks */
		if (!stocks.containsKey(stock)) {
			return 0;
		}
		int sum = 0;
		for (Integer stockAmount : stocks.values()) {
			sum += stockAmount;
		}
		return (double) sum / stocks.get(stock);
	}

	public double calculateFundPrice() {
		/* Calculate unit fund price */
		double price = 0;
		for (Stock stock : stocks.keySet()) {
			price += stock.getPrice() * calculatePercentage(stock);
		}
		return price;
	}

	public int getId() {
		return id;
	}

	@Override
	public int compareTo(InvestmentFund o) {
		double dif = calculateFundPrice() - o.calculateFundPrice();
		if (dif > 0) {
			return 1;
		} else if (dif == 0) {
			return 0;
		}
		return -1;
	}

	@Override
	public String toString() {
		String str = "stocks = ";
		for (Stock stock : stocks.keySet()) {
			str += stock.toString() + " --> " + stocks.get(stock) + ", ";
		}
		return "InvestmentFund [id=" + id + ", " + str + "]";
	}

}
