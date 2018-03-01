package treenode;

import views.Observer;
import views.SceneElements.Observable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;
import java.util.*;

public class NodeBuilder{

	private List<Observer> observers = new ArrayList<>();
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	private static final String LANGUAGE_FILE = "English";
	private static final String NUMBERNODE_ADDRESS = "treenode.NumberNode";
	private static final String VARIABLENODE_ADDRESS = "treenode.VariableNode";
	private static String language = LANGUAGE_FILE;
	private static ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+language);
	public static Map<String, String> languageMap = createLanguageMap(myResources);
	//private static final Map<String, Class<?>> classMap = createClassMap();  //creates the classMap of all objects.

//	public static void main(String[] args) {
//		createLanguageMap(myResources);
//		String[] a = new String[2];
//		a[0]= "pi";
//		a[1] = "pi";
//		SlogoNode[] output = CommandFactory.convertStringtoNode(a);
//		System.out.println(output[0]);
//		System.out.println(output[1]);
//		
//	}

	/*
	 * iterates through each value and returns a map of all the languages.
	 */
	private static Map<String, String> createLanguageMap(ResourceBundle myResources){
		Map<String, String> languageMap = new HashMap<>();
		Set<String> keySet = myResources.keySet();
		Iterator<String> it = keySet.iterator();

		while(it.hasNext()) {
			String curr = it.next();
			//System.out.println(curr);
			//put in the resource string as the key, and the key as the value
			if (isMultipleKeyword(myResources.getString(curr))) {
				//System.out.println(myResources.getString(curr));
				String[] splitWords = myResources.getString(curr).split("[|]");
				//System.out.println(splitWords.length);
				for (int i = 0; i<splitWords.length;i++) {
					languageMap.put(splitWords[i],  curr);
				}	
			}else {
				//System.out.println(myResources.getString(curr));
				languageMap.put(myResources.getString(curr), curr);
			}
		}
		return languageMap; //returns a map with all of the 
	}


	/*
	 * checks if there is an Or operator that splits the words
	 */
	private static Boolean isMultipleKeyword(String input) {
		return input.contains("|");
	}
	public static void changeLanguage(String newlanguage){
	    language = newlanguage;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+language);
        languageMap = createLanguageMap(myResources);
    }
	/*
	 * creates Variable Node
	 */
	public static SlogoNode createVariableNode(String input) {
		Class<?> commandObject = null;
		try { //try to create a new class object based on name.
			commandObject = Class.forName(VARIABLENODE_ADDRESS);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Constructor<?> c = commandObject.getConstructors()[0];
		SlogoNode command = null;
		try {
			command = (SlogoNode) c.newInstance(input);
		}
		catch (InstantiationException | IllegalAccessException| IllegalArgumentException |InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return command;
	}
	
	/*
	 * creates Number Node
	 */
	public static SlogoNode createNumberNode(String input) {
		Class<?> commandObject = null;
		try { //try to create a new class object based on name.
			commandObject = Class.forName(NUMBERNODE_ADDRESS);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Constructor<?> c = commandObject.getConstructors()[0];
		SlogoNode command = null;
		try {
			command = (SlogoNode) c.newInstance(Double.parseDouble(input));
		}
		catch (InstantiationException | IllegalAccessException| IllegalArgumentException |InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return command;
	}


	/*
	 * builds an individual Command Node
	 */
	public static SlogoNode createNode(String input) {
		String formalCommandName = null;

		if (languageMap.containsKey(input)) { //if the map exists
			formalCommandName = languageMap.get(input);
		}
		else{
			throw new InvalidParameterException("NOT A COMMAND");
		}
		Class<?> commandObject = null;
		
		try { //try to create a new class object based on name.
			commandObject = Class.forName("Movement."+formalCommandName);
			}
		catch (ClassNotFoundException e1) {
			try { //try to create a new class object based on name.
				commandObject = Class.forName("Bools."+formalCommandName);
			} catch (ClassNotFoundException e2) {
				try { //try to create a new class object based on name.
					commandObject = Class.forName("MathOps."+formalCommandName);
				} catch (ClassNotFoundException e3) {
					try { //try to create a new class object based on name.
						commandObject = Class.forName("Query."+formalCommandName);
					}catch (ClassNotFoundException e4) {
						try {
							commandObject = Class.forName("VarOp." + formalCommandName);
						}catch (ClassNotFoundException e5) {
						e4.printStackTrace();
					}
				}
			}

			}
		}
		Constructor<?> c = commandObject.getConstructors()[0];
		SlogoNode command = null;
		try {
			command = (SlogoNode) c.newInstance();
		}
		catch (InstantiationException | IllegalAccessException| IllegalArgumentException |InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return command;
	}



	//	/*
	//	 * Reflections for retrieving the right class 
	//	 */
	//	private static Class<?> getClassForName(String className) {
	//		try {
	//			return Class.forName(myResources.getString(className));
	//		} catch (Exception e) {
	//			return null;
	//
	//		}
	//	}
	//
	//	/*
	//	 * Method that iterates through entire resource file and adds to the classMap
	//	 * All nodes should be created in the class Map
	//	 */
	//	private static Map<String, Class<?>> createClassMap(){
	//		Map<String, Class<?>> classMap = new HashMap<>();
	//		Enumeration<String> keySet = myResources.getKeys();
	//
	//		while (keySet.hasMoreElements()) { //if there are more elements
	//			String current = keySet.nextElement();  //obtain the next value in the keyset (String)
	//			Class<?> theClass = getClassForName(current); //obtain the object from the class.
	//			classMap.put(keySet.nextElement(), theClass);
	//		} 
	////		//adding the variable node
	////		Class<?> theClass = getClassForName("VariableNode"); //obtain the object from the class.
	////		classMap.put(keySet.nextElement(), theClass);
	////		//adding the number node
	////		theClass = getClassForName("NumberNode"); //obtain the object from the class.
	////		classMap.put(keySet.nextElement(), theClass);
	//		
	//		return classMap;
	//	}


	/*
	 * Method that accesses the classMap to obtain the desired Command Node
	 */
	//	public static Class<?> createNode(String commandName){
	//		return classMap.get(commandName);
	//	}


}
