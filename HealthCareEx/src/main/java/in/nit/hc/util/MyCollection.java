package in.nit.hc.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//JDK 8
public interface MyCollection {

	//static methods
	public static Map<Long, String> convertListToMap(List<Object[]> list) {
		System.out.println("MyCollection.convertListToMap()");
		list.forEach(list1 -> {
			System.out.println(list1[0]);
			System.out.println(list1[1]);
		});
	    //java 8 Stream API
		return list.stream().collect(
				                     Collectors.toMap(ob -> Long.valueOf(ob[0].toString()), ob -> ob[1].toString()));
	}
	
	public static Map<Long, String> convertListToMap2(List<Object[]> list) {
		System.out.println("MyCollection.convertListToMap2()");
		list.forEach(list1 -> {
			System.out.println(list1[0]);
			System.out.println(list1[1]);
			System.out.println(list1[2]);
			System.out.println(list1[3]);
		});
	    //java 8 Stream API
		return list.stream().collect(
				                     Collectors.toMap(ob -> Long.valueOf(ob[0].toString()), ob -> ob[1].toString()+" "+ob[2].toString()+" - "+ob[3].toString()));
	}

}
