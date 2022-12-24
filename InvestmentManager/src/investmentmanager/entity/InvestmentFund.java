package investmentmanager.entity;

import java.util.HashMap;

class InvestmentFund {
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
		double price = 0;
		for (Stock stock : stocks.keySet()) {
			price += stock.getPrice() * calculatePercentage(stock);
		}
		return price;
	}

}
