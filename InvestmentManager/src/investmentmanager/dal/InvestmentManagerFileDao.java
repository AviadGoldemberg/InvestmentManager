package investmentmanager.dal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// import the entities
import investmentmanager.entity.Investor;
import investmentmanager.entity.Stock;
import investmentmanager.entity.Entity;

public class InvestmentManagerFileDao implements InvestmentManagerDao {

	private static String FILE_NAME = "InvestmentsData";

	public ArrayList<ArrayList<Entity>> readFromFile() throws Exception {
		// this func reads from the file the arraylist

		// there are three possible lists to pull from the file
		ArrayList<ArrayList<Entity>> al = new ArrayList<ArrayList<Entity>>();

		try {
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			al = (ArrayList<ArrayList<Entity>>) ois.readObject();
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

	public void writeToFile(ArrayList<ArrayList<Entity>> al) throws Exception {
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
	public List<Entity> getAll(int id) throws Exception {
		// id = 0 - stock list
		// id = 1 - investor list
		// id = 2 - investmentFund list
		ArrayList<ArrayList<Entity>> al = (ArrayList<ArrayList<Entity>>) readFromFile();
		return al.get(id);

	}

	@Override
	public void save(Entity t) throws Exception {
		// because there are three different arraylists that needs to be saved as 1
		// each array list will have its own purpose
		// 0 - Stock
		// 1 - Investor
		// 2 - InvestmentFund

		ArrayList<ArrayList<Entity>> al = (ArrayList<ArrayList<Entity>>) readFromFile();
		ArrayList<Entity> entityList;
		// t is Stock
		if (t instanceof Stock) {
			entityList = al.get(0);
		}
		// t is Investor
		else if (t instanceof Investor) {
			entityList = al.get(1);
		}
		// t is InvestmentFund
		else {
			entityList = al.get(2);
		}
		entityList.add(t);
		Collections.sort(entityList);
		// writing to the file the new arrayList
		writeToFile(al);

	}

	@Override
	public void update(Entity t) throws Exception {
		ArrayList<ArrayList<Entity>> al = (ArrayList<ArrayList<Entity>>) readFromFile();
		ArrayList<Entity> entityList;
		// t is Stock
		if (t instanceof Stock) {
			entityList = al.get(0);
		}
		// t is Investor
		else if (t instanceof Investor) {
			entityList = al.get(1);
		}
		// t is InvestmentFund
		else {
			entityList = al.get(2);
		}
		if (entityList.contains((Object)t))
		{
			for (int i = 0; i < entityList.size(); i++) {
				if (entityList.get(i).getId() == t.getId()) {
					entityList.remove(i);
					entityList.add(i, t);
					break;
				}
			}
			// writing to the file the new arrayList
			writeToFile(al);
		}
		else {
			// TODO : custom exception
		}

	}

	@Override
	public void delete(int id, Class c) throws Exception {

		ArrayList<ArrayList<Entity>> al = (ArrayList<ArrayList<Entity>>) readFromFile();
		ArrayList<Entity> entityList;
		// the class is stock
		if (c == Stock.class) {
			entityList = al.get(0);
		}
		// the class is investor
		else if (c == Investor.class) {
			entityList = al.get(1);
		}
		// the class is investmentFund
		else {
			entityList = al.get(2);

		}
		

		for (int i = 0; i < entityList.size(); i++) {
			if (entityList.get(i).getId() == id) {
				entityList.remove(i);
				break;
			}
		}
		// writing to the file the new arrayList
		writeToFile(al);
	}
	

	@Override
	public Entity get(int id, Class c) throws Exception {
		ArrayList<ArrayList<Entity>> al = (ArrayList<ArrayList<Entity>>) readFromFile();

		ArrayList<Entity> entityList;
		// the class is stock
		if (c == Stock.class) {
			entityList = al.get(0);
		}
		// the class is investor
		else if (c == Investor.class) {
			entityList = al.get(1);
		}
		// the class is investmentFund
		else {
			entityList = al.get(2);

		}
		for (int i = 0; i < entityList.size(); i++) {
			if (entityList.get(i).getId() == id) {
				return (Entity) entityList.get(i);

			}
		}
		// if nothing matched
		return null;
	}

}