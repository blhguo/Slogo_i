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
	private static Map<String, Class<?>> createClassMap(){
		Map<String, Class<?>> classMap = new HashMap<>();
		Enumeration<String> keySet = myResources.getKeys();

		while (keySet.hasMoreElements()) { //if there are more elements
			String current = keySet.nextElement();  //obtain the next value in the keyset (String)
			Class<?> theClass = getClassForName(current); //obtain the object from the class.
			classMap.put(keySet.nextElement(), theClass);
		} 
		//adding the variable node
		Class<?> theClass = getClassForName("VariableNode"); //obtain the object from the class.
		classMap.put(keySet.nextElement(), theClass);
		//adding the number node
		theClass = getClassForName("NumberNode"); //obtain the object from the class.
		classMap.put(keySet.nextElement(), theClass);
		return classMap;
	}


	/*
	 * Method that accesses the classMap to obtain the desired Command Node
	 */
	public static Class<?> createNode(String commandName){
		return classMap.get(commandName);
	}


}
