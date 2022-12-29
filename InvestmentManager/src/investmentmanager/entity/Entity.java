package investmentmanager.entity;

import java.io.Serializable;

public interface Entity  extends Comparable<Entity>,Serializable {
	
	public int getId();
	public double getValue();
}
