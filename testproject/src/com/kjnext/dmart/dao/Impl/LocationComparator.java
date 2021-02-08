package com.kjnext.dmart.dao.Impl;

import java.util.Comparator;
import com.kjnext.dmart.hibernate.Location;

public class LocationComparator implements Comparator<Location> {

	
		public int compare(Location e1, Location e2) {
	    String s1= e1.getCity();
	    String s2= e2.getCity();
	    return s1.compareTo(s2);
		
		}

}
