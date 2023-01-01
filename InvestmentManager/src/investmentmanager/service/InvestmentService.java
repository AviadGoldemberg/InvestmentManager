package investmentmanager.service;

import java.util.List;
import java.util.TooManyListenersException;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import investmentmanager.dal.InvestmentManagerDao;
import investmentmanager.entity.Entity;
import investmentmanager.entity.InvestmentFund;
import investmentmanager.entity.Investor;
import investmentmanager.entity.Stock;
import investmentmanager.dal.InvestmentManagerFileDao;

@PropertySource("classpath: params.properties")
public class InvestmentService implements Manageable {
	// injected from xml
	@Autowired
	private InvestmentManagerDao dao;

	// injected properties from file
	@Value("${Manageable.max.stock.risk}")
	private int maxStockRisk;

	@Value("${Manageable.max.fund.for.investor}")
	private int maxFundForInvestor;

	@Value("${Manageable.min.stock.initial.price}")
	private double minStockInitialPrice;

	public void setDao(InvestmentManagerDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Entity> getAll(int id) throws Exception {
		return dao.getAll(id);
	}

	@Override
	public void save(Entity t) throws Exception {
		// Checked not saved before
		for (int i = 0; i <= InvestmentManagerFileDao.fund; i++) {
			for (Entity e : getAll(i)) {
				if (e.equals(t)) {
					throw new AlreadySavedEntityException(String.format("%s already saved.", t.toString()));
				}
			}
		}
		// check if investor above max funds for investor
		if (t instanceof Investor) {
			Investor investor = (Investor) t;
			if (investor.getHashMap().size() > maxFundForInvestor) {
				throw new TooManyListenersException("can't save. The max funds for investor is: " + maxFundForInvestor);
			}
		}
		// check min stock initial price
		if (t instanceof Stock) {
			Stock stock = (Stock) t;
			if (stock.getPrice() < minStockInitialPrice) {
				throw new BellowAlowedInitialPriceException(
						"The stock is bellow allowed initial price: " + minStockInitialPrice);
			}
			if (stock.getRisk() > maxStockRisk || stock.getRisk() < 0) {
				throw new RiskNotValidException(
						"The risk you entered for this stock is not valid. risk has to be above 0 and below: "
								+ maxStockRisk);
			}
		}
		dao.save(t);
	}

	@Override
	public void update(Entity t) throws Exception {
		// check if investor above max funds for investor
		if (t instanceof Investor) {
			Investor investor = (Investor) t;
			if (investor.getHashMap().size() > maxFundForInvestor) {
				throw new TooManyListenersException("can't save. The max funds for investor is: " + maxFundForInvestor);
			}
		}
		dao.update(t);
	}

	@Override
	public void delete(int id, Class c) throws Exception {
		dao.delete(id, c);
	}

	@Override
	public Entity get(int idm, Class c) throws Exception {
		return dao.get(idm, c);
	}

	// when the container is up
	public void whenIsUp() throws Exception {
		System.out.println("Hello fellow user!");
		printAllEntities();
	}

	// when the container is down
	public void whenIsDown() throws Exception {
		System.out.println("Goodbye! thank you for using our system!");
		printAllEntities();

	}

	// prints all the entities
	private void printAllEntities() throws Exception {
		List<Entity> stocksList = getAll(0);
		List<Entity> investorsList = getAll(1);
		List<Entity> fundsList = getAll(2);
		System.out.println("here is the list of entinties in the system: ");
		for (Object stock : stocksList) {
			System.out.println(((Stock) stock).toString());

		}

		for (Object investor : investorsList) {
			System.out.println(((Investor) investor).toString());

		}

		for (Object fund : fundsList) {
			System.out.println(((InvestmentFund) fund).toString());
		}
	}
}
