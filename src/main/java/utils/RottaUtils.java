package utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RottaUtils {
	public static Object populaReq(Object obj, Map params){
		Class<? extends Object> classe = obj.getClass();
		Set<String> parametro = params.keySet();
		for(String p : parametro){
			try {
				
				
				Field f  = classe.getDeclaredField(p);
		        	String methodName = "set" + Character.toUpperCase(f.getName().charAt(0)) + f.getName().substring(1);

		            final Method method;

		            method = classe.getMethod(methodName, f.getType());
		            f.setAccessible(true);
		            method.setAccessible(true);
		            try {
		            	Object[] vet = (String[]) params.get(p);
		            	if(!f.getType().equals(vet[0].getClass())){
		            	Method metodo2 = f.getType().getDeclaredMethod("valueOf", vet[0].getClass());
		            	method.invoke(obj, metodo2.invoke(null, vet[0]));
		            	}
		            	else
						method.invoke(obj, vet[0]);
					} catch (IllegalAccessException | IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} catch (NoSuchMethodException | InvocationTargetException | NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		return obj;
	}	
  
	public static List<? extends Object> populaResult(Object obj, ResultSet rs) throws SQLException {
	    ResultSetMetaData md = rs.getMetaData();
	    int columns = md.getColumnCount();
	    List<Object> rows = new ArrayList<>();
	    while (rs.next()){
	    	Map<String, String[]> row = new HashMap<String, String[]>(columns);
	        for(int i = 1; i <= columns; ++i){
	        	String[] valor = new String[1];
	        	valor[0] = rs.getObject(i).toString();
	            row.put(md.getColumnName(i), valor);
	        }
	        try {
				rows.add(populaReq(obj.getClass().newInstance(), row));
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return rows;
	}
}