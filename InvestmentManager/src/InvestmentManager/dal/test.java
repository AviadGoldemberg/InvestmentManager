package InvestmentManager.dal;

import java.util.ArrayList;


public class test {

	public static void main(String[] args) {
		Stock s = new Stock("apple", 100, 1);
		Investor i = new Investor("Dani", 150);
		InvestmentFund imf = new InvestmentFund();
		
		System.out.println(s.getPrice());
		
		ArrayList<Stock> stockList = new ArrayList<Stock>();
		ArrayList<Investor> investorList = new ArrayList<Investor>();
		ArrayList<InvestmentFund> fundsList = new ArrayList<InvestmentFund>();
		
		
		stockList.add(s);
		investorList.add(i);
		fundsList.add(imf);
		
		ArrayList<ArrayList> totalList = new ArrayList<ArrayList>();
		
		totalList.add(stockList);
		totalList.add(investorList);
		totalList.add(fundsList);
		
		System.out.println(totalList);
		
		InvestmentManagerFileDao p = new InvestmentManagerFileDao();
		try {
			p.writeToFile(totalList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(((Stock) p.getAll(0).get(0)).getPrice());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}


}
