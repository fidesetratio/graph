package com.app;

import java.util.Map;

import org.mvel2.MVEL;

public class MvelParser {
	public static boolean parseMvelExpression( String expression, Map<String, Object> inputObjects){
        try {
        	if(expression == null) {
        		System.out.println(" null");
        	}
            Boolean execute =  MVEL.evalToBoolean(expression,inputObjects);
            if(execute == null) {
            	return false;
            }
        }catch (Exception e){
        	e.printStackTrace();
        }
        return false;
    }
}
