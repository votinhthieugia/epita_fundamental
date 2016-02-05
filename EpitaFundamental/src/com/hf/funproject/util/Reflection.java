package com.hf.funproject.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reflection {

	public static Object invokeGetter(Object obj, String variableName){
	    Object variableValue = null;  
		try {
	        /**
	         * Get object of PropertyDescriptor using variable name and class
	         * Note: To use PropertyDescriptor on any field/variable, the field must have both `Setter` and `Getter` method.
	         */
	         PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(variableName, obj.getClass());
	        /**
	         * Get field/variable value using getReadMethod()
	         * variableValue is Object because value can be an Object, Integer, String, etc...
	         */
	         variableValue = objPropertyDescriptor.getReadMethod().invoke(obj);	        
	      } catch (IllegalAccessException | IllegalArgumentException
	        | InvocationTargetException | IntrospectionException e) {
	       /* Java 8: Multiple exception in one catch. Use Different catch block for lower version. */
	        e.printStackTrace();
	      }
		return variableValue;
	   }
	
	public static void invokeSetter(Object obj, String variableName, Object variableValue){
	      /* variableValue is Object because value can be an Object, Integer, String, etc... */
	      try {
	        /**
	         * Get object of PropertyDescriptor using variable name and class
	         * Note: To use PropertyDescriptor on any field/variable, the field must have both `Setter` and `Getter` method.
	         */
	         PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(variableName, obj.getClass());
	         /* Set field/variable value using getWriteMethod() */
	         objPropertyDescriptor.getWriteMethod().invoke(obj, variableValue);
	      } catch (IllegalAccessException | IllegalArgumentException
	        | InvocationTargetException | IntrospectionException e) {
	        /* Java 8: Multiple exception in one catch. Use Different catch block for lower version. */
	        e.printStackTrace();
	      }
	   }
	
	public static List<Field> getFields(Object object){
		List<Field> fields = new ArrayList<Field>(Arrays.asList(object.getClass().getDeclaredFields()));		
		return fields;		
	}			
}
