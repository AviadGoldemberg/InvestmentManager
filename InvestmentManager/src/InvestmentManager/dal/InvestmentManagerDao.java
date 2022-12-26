package InvestmentManager.dal;

import java.util.List;

public interface InvestmentManagerDao<Object> {
	//al - ArrayList
	
	//returns all of the al's in the file
	public List<Object> getAll(int id) throws Exception;
	
	//saves an al to the file
	public void Save(Object t) throws Exception;
	
	//updates an item in the al in the file
	public void update(Object t) throws Exception;
	
	//Deletes an item in an al in the file
	public void Delete(int id, Class c) throws Exception;
	
	//returns an item from a certain al in the file
	public Object Get(int idm, Class c) throws Exception;

}
