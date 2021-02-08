package com.kjnext.dmart.dao.Impl;



	import java.util.Comparator;

import com.kjnext.dmart.hibernate.LocationArea;

	public class AreaComparator implements Comparator<LocationArea> {

		
			public int compare(LocationArea e1, LocationArea e2) {
		    String s1= e1.getAreaName();
		    String s2= e2.getAreaName();
		    return s1.compareTo(s2);
			
			}

}
