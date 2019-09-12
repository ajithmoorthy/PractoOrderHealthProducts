package org.atmecs.practo.utils;

public class Productaccess {
 public int[] indexgenerator(int[] storage,int limit) {
	 int index=0;
	  int initial=0,temp=0;
	while(temp<limit) {
		if(temp==0)
		{
			storage[index]=initial;
			temp++;
			index++;
		}
		else
		{
			initial=initial+3;
			storage[index]=initial;
			index++;
			for(int count=0; count<2; count++)
			{
				initial=initial+2;
				storage[index]=initial;
				index++;
			}
			temp++;
		}
	}
	return storage;
}
}
