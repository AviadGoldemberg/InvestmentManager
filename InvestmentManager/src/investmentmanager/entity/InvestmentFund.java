package investmentmanager.entity;

import java.util.HashMap;
/*Investment fund class*/
class InvestmentFund implements Comparable<InvestmentFund>{
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
	
	public void removeStock(Stock stock) {
		stocks.remove(stock);
	}
	
	private double calculatePercentage(Stock stock) {
		/*Calculate the percentage of each stock from the fund stocks*/
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
		/*Calculate unit fund price*/
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
		}
		else if (dif == 0){
			return 0;
		}
		return -1;
	}

	
	
}
