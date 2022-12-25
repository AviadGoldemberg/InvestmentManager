package investmentmanager.entity;

import java.util.HashMap;

/*Investor class*/
public class Investor implements Comparable<Investor>{
	private static int idCounter = 0;
	private int investorId;
	private String name;
	private HashMap<InvestmentFund, Integer> investments;
	private double availableMoney;

	public Investor(String name, double money) {
		this.name = name;
		availableMoney = money;
		investorId = idCounter;
		idCounter++;
		investments = new HashMap<InvestmentFund, Integer>();

	}
	
	public double investorValue() {
		/*Calculate current value of investor */
		double value = 0;
		for (InvestmentFund fund : investments.keySet()) {
			value += fund.calculateFundPrice() * investments.get(fund);
		}
		return value + availableMoney;
	}

	public void buyUnits(InvestmentFund fund, int units) {
		/*Buy Investment Fund units*/
		double moneyToSpend = fund.calculateFundPrice() * units;
		if (availableMoney > moneyToSpend) {
			availableMoney -= moneyToSpend;
			if (investments.containsKey(fund)) {
				investments.put(fund, investments.get(fund) + units);
			} else {
				investments.put(fund, units);
			}
		} else {
			// build custom exception
		}
	}

	public void sellUnits(InvestmentFund fund, int units) {
		/*Sell Investment Fund units*/
		if(investments.containsKey(fund)) {
			if(units < investments.get(fund)) {
				availableMoney += fund.calculateFundPrice() * units;
				investments.put(fund, investments.get(fund) - units);
			}
			else if (units == investments.get(fund)) {
				availableMoney += fund.calculateFundPrice() * units;
				investments.remove(fund);
			}
			else {
				//custom exception
			}
		}
		else {
			// custom exception
		}
	}

	public int getInvestorId() {
		return investorId;
	}

	public String getName() {
		return name;
	}

	@Override
	public int compareTo(Investor o) {
		double dif = investorValue() - o.investorValue();
		if (dif > 0) {
			return 1;
		}
		else if (dif == 0){
			return 0;
		}
		return -1;
	}

}
