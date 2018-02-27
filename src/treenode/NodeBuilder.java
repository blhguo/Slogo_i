package treenode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class NodeBuilder {

	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String LANGUAGE_FILE = "English";
	public static final ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+LANGUAGE_FILE);

	private static final Map<String, Class<?>> classMap = createClassMap();  //creates the classMap of all objects.

	/*
	 * Reflections for retrieving the right class 
	 */
	private static Class<?> getClassForName(String className) {
		try {
			return Class.forName(myResources.getString(className));
		} catch (Exception e) {
			return null;

		}
	}

	/*
	 * Method that iterates through entire resource file and adds to the classMap
	 * All nodes should be created in the class Map
	 */
	private static Map<String, Class<?>> createClassMap() throws NoSuchMethodException, SecurityException, NumberFormatException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Map<String, Class<?>> classMap = new HashMap<>();
		Enumeration<String> keySet = myResources.getKeys();

		while (keySet.hasMoreElements()) { //if there are more elements
			String current = keySet.nextElement();  //obtain the next value in the keyset (String)
			if (isNumberNode(current)) {//check if its a number node
				Constructor<?> c = getClassForName("NumberNode").getConstructor(Double.TYPE);
				Class<?> foo = (Class<?>) c.newInstance(Integer.parseInt(current)); 
				classMap.put(current, foo); //Adds the number node to the map.
			}
			
			else if (isVariable(current)) { //check if its a variable node 
				/*
				 * may need to add more methods to extract the variable string...
				 */
				Constructor<?> c = getClassForName("VariableNode").getConstructor(String.class, Double.TYPE);
				Class<?> foo = (Class<?>) c.newInstance(current, 0); 
				classMap.put(current, foo); //Adds the number node to the map.
			}
			
				else {
					Class<?> theClass = getClassForName(current); //obtain the object from the class.
					classMap.put(keySet.nextElement(), theClass);
				}
		} 
		return classMap;
	}


	/*
	 * Method that accesses the classMap to obtain the desired Command Node
	 */
	public static Class<?> createNode(String commandName){
		return classMap.get(commandName);
	}


	/*
	 * method to check if a number is an integer (for creating number nodes)	   
	 */
	public static boolean isNumberNode(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		} catch(NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	/*
	 * regex check for variable node
	 */
	public static Boolean isVariable(String input) {
		return input.matches(":(.*)");
	}
}
