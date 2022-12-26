package InvestmentManager.dal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InvestmentManagerFileDao<Object> implements InvestmentManagerDao<Object> {

	private static String FILE_NAME = "InvestmentsData";

	public List readFromFile() throws Exception {
		// this func reads from the file the arraylist

		// there are three possible lists to pull from the file
		ArrayList<ArrayList> al = new ArrayList<ArrayList>();

		try {
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			al = (ArrayList) ois.readObject();
			ois.close();
			fis.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Class Not Found");
			cnfe.printStackTrace();
			return null;
		}

		// if succeeded now in al is the arraylist with all of the entities
		return al;
	}

	public void writeToFile(ArrayList<ArrayList> al) throws Exception {
		// this func writes to the file the arraylist
		try {
			FileOutputStream fos = new FileOutputStream(FILE_NAME);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(al);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public List getAll(int id) throws Exception {
		// id = 0 - stock list
		// id = 1 - investor list
		// id = 2 - investmentFund list
		ArrayList<Object> al = (ArrayList<Object>) readFromFile();
		return (List) al.get(id);

	}

	@Override
	public void Save(Object t) throws Exception {
		// because there are three different arraylists that needs to be saved as 1
		// each array list will have its own purpose
		// 0 - Stock
		// 1 - Investor
		// 2 - InvestmentFund

		ArrayList<ArrayList> al = (ArrayList<ArrayList>) readFromFile();

		// t is Stock
		if (t instanceof Stock) {
			ArrayList<Stock> stockList = (ArrayList<Stock>) al.get(0);
			stockList.add((Stock) t);
			Collections.sort(stockList);

		}
		// t is Investor
		else if (t instanceof Investor) {
			ArrayList<Investor> investorList = (ArrayList<Investor>) al.get(1);
			investorList.add((Investor) t);
			Collections.sort(investorList);

		}
		// t is InvestmentFund
		else {
			ArrayList<InvestmentFund> fundsList = (ArrayList<InvestmentFund>) al.get(2);
			fundsList.add((InvestmentFund) t);
			Collections.sort(fundsList);

		}

		// writing to the file the new arrayList
		writeToFile(al);

	}

	@Override
	public void update(Object t) throws Exception {
		ArrayList<ArrayList> al = (ArrayList<ArrayList>) readFromFile();

		// t is Stock
		if (t instanceof Stock) {
			ArrayList<Stock> stockList = (ArrayList<Stock>) al.get(0);
			for (int i = 0; i < stockList.size(); i++) {
				if (stockList.get(i).getStockId() == ((Stock) t).getStockId()) {
					stockList.remove(i);
					stockList.add(i, (Stock) t);
					break;
				}
			}

		}
		// t is Investor
		else if (t instanceof Investor) {
			ArrayList<Investor> investorList = (ArrayList<Investor>) al.get(1);
			for (int i = 0; i < investorList.size(); i++) {
				if (investorList.get(i).getInvestorId() == ((Investor) t).getInvestorId()) {
					investorList.remove(i);
					investorList.add(i, (Investor) t);
					break;
				}
			}

		}
		// t is InvestmentFund
		else {
			ArrayList<InvestmentFund> fundsList = (ArrayList<InvestmentFund>) al.get(2);
			for (int i = 0; i < fundsList.size(); i++) {
				if (fundsList.get(i).getId() == ((InvestmentFund) t).getId()) {
					fundsList.remove(i);
					fundsList.add(i, (InvestmentFund) t);
					break;
				}
			}

		}

		// writing to the file the new arrayList
		writeToFile(al);

	}

	@Override
	public void Delete(int id, Class c) throws Exception {

		ArrayList<ArrayList> al = (ArrayList<ArrayList>) readFromFile();

		// the class is stock
		if (c == Stock.class) {
			ArrayList<Stock> stockList = (ArrayList<Stock>) al.get(0);
			for (int i = 0; i < stockList.size(); i++) {
				if (stockList.get(i).getStockId() == id) {
					stockList.remove(i);
					break;
				}
			}

		}
		// the class is investor
		else if (c == Investor.class) {
			ArrayList<Investor> investorList = (ArrayList<Investor>) al.get(1);
			for (int i = 0; i < investorList.size(); i++) {
				if (investorList.get(i).getInvestorId() == id) {
					investorList.remove(i);
					break;
				}
			}

		}
		// the class is investmentFund
		else {
			ArrayList<InvestmentFund> fundsList = (ArrayList<InvestmentFund>) al.get(2);
			for (int i = 0; i < fundsList.size(); i++) {
				if (fundsList.get(i).getId() == id) {
					fundsList.remove(i);
					break;
				}
			}
		}

		// writing to the file the new arrayList
		writeToFile(al);

	}

	@Override
	public Object Get(int id, Class c) throws Exception {
		ArrayList<Object> al = (ArrayList<Object>) readFromFile();

		// the class is stock
		if (c == Stock.class) {
			ArrayList<Stock> stockList = (ArrayList<Stock>) al.get(0);
			for (int i = 0; i < stockList.size(); i++) {
				if (stockList.get(i).getStockId() == id) {
					return (Object) stockList.get(i);
					
				}
			}

		}
		// the class is investor
		else if (c == Investor.class) {
			ArrayList<Investor> investorList = (ArrayList<Investor>) al.get(1);
			for (int i = 0; i < investorList.size(); i++) {
				if (investorList.get(i).getInvestorId() == id) {
					return (Object) investorList.get(i);
					
				}
			}

		}
		// the class is investmentFund
		else {
			ArrayList<InvestmentFund> fundsList = (ArrayList<InvestmentFund>) al.get(2);
			for (int i = 0; i < fundsList.size(); i++) {
				if (fundsList.get(i).getId() == id) {
					return (Object) fundsList.get(i);
					
				}
			}
		}
		
		
		//if nothing matched
		return null;
	}

}
