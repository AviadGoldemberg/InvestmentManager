package investmentmanager.entity;

import java.io.Serializable;

public abstract class Entity implements Comparable<Entity>,Serializable {
	
	public abstract int getId();
	public abstract double getValue();
	
	// compare two Entity objects
	@Override
	public int compareTo(Entity o) {
		double dif = this.getValue() - o.getValue();
		if (dif > 0) {
			return 1;
		} else if (dif == 0) {
			return 0;
		}
		return -1;
	}
	
}
