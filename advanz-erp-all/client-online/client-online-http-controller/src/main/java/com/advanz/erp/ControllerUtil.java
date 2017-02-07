package com.advanz.erp;

import java.lang.reflect.Method;

public class ControllerUtil  {
	
	
	public static void copyObjectWithoutNull ( Object o1, Object o2) throws Exception
	{
		int i, j;
	//	LogManager logger = LogManager.getInstance("BeanUtils=>");

		//logger.info("ENTERING => copyObject()" );
		Class c1 = o1.getClass();
		Class c2 = o2.getClass();
		Method[] o1Methods = c1.getDeclaredMethods() ;
		Method[] o2Methods = c2.getDeclaredMethods();
		//Arrays.sort(o1Methods, new BeanMethodComparator());
	//	Arrays.sort(o2Methods, new BeanMethodComparator());
		for ( i=0; i< o1Methods.length; i++ )
		{
			if ( o1Methods[i].getName().substring(0,3).equals("get") )
			{
				for ( j=0; j< o2Methods.length; j++ )
				{
					if ( ("set"+ o1Methods[i].getName().substring(3)).equals(o2Methods[j].getName()) )
					{
						//logger.debug ("Calling " + o1Methods[i].getName() + " " + o2Methods[j].getName()) ;
					//	Object[] objectArray = {o1Methods[i].invoke(o1, null)} ;
						Object[] objectArray = new Object[1] ;
						if(o1Methods[i].invoke(o1, new Object[0]) != null){
							objectArray[0] = o1Methods[i].invoke(o1, new Object[0]) ;
							//logger.debug("Object is not null");
						} else
							continue;
						String returnType = ( (Class)o1Methods[i].getReturnType()).getName();
						String paramType = ( (Class []) o2Methods[j].getParameterTypes())[0].getName();
						boolean isParamPrimitive = ( (Class []) o2Methods[j].getParameterTypes())[0].isPrimitive();
						
						if (isParamPrimitive) {
							//logger.debug ( "Data type in set method is primitive" );
							//logger.debug ( "Get method has returned " + objectArray[0] );
							//logger.debug ( "Return type is: " + returnType);
							
							if (returnType.equals("int")) {
								Integer returnValue = ((Integer) objectArray[0]);
								if (returnValue.intValue() == 0) {
									//logger.debug ("The value for int is 0; so continue");
									continue;
								}
							} else if (returnType.equals("float")) {
								Float returnValue = (( Float) objectArray[0]);
								if (returnValue.floatValue() == 0f) {
									//logger.debug ("The value for float is 0; so continue");
									continue;
								}
							} else if (returnType.equals("double")) {
								Double returnValue = (( Double) objectArray[0]);
								if (returnValue.doubleValue() == 0) {
									//logger.debug ("The value for float is 0; so continue");
									continue;
								}
							}
						}
						
					//	logger.debug( "Param type is : " + paramType + " , Return Type is :" + returnType);
//						logger.debug( "isParamPrimitive : " + isParamPrimitive );
						if ( objectArray[0] == null) {
							//logger.debug ( "Get method has returned " + objectArray[0] );
							if (isParamPrimitive)
							{
//								logger.debug ( "Data type in set method is premitive" );
								continue ;
							}
						}
						else
						{
							if ( (paramType.equals("int") || paramType.equals("java.lang.Integer") ) && (returnType.equals("float") || returnType.equals("java.lang.Float")) )
							{
								//float -> int
								//logger.debug("Going to convert float to integer");
								Float returnValue = (( Float) objectArray[0]);
								returnValue = new Float (returnValue.floatValue() * 100);
								Integer paramValue = new Integer ( returnValue.intValue() );
								//logger.debug("Going to set the following integer value : " + paramValue );
								objectArray[0] = paramValue; 
								
//								o2Methods[j].invoke( o2, objectArray ) ;
							}
							
							if ( (paramType.equals("int") || paramType.equals("java.lang.Integer") ) && (returnType.equals("double") || returnType.equals("java.lang.Double")) )
							{
								//float -> int
								//logger.debug("Going to convert float to integer");
								Double returnValue = (( Double) objectArray[0]);
								returnValue = new Double (returnValue.doubleValue() * 100);
								Integer paramValue = new Integer ( returnValue.intValue() );
								//logger.debug("Going to set the following integer value : " + paramValue );
								objectArray[0] = paramValue; 
								
//								o2Methods[j].invoke( o2, objectArray ) ;
							}
							
							if ( (paramType.equals("float") || paramType.equals("java.lang.Float") ) && (returnType.equals("int") || returnType.equals("java.lang.Integer")) )
							{
								//int -> float
								//logger.debug("Going to convert integer to float");
								Integer returnValue = ((Integer) objectArray[0]);
								float fReturnValue = returnValue.floatValue();
								Float paramValue = new Float ( fReturnValue / 100 );
								//logger.debug("Going to set the following float value : " + paramValue );
								objectArray[0] = paramValue; 
								
//								o2Methods[j].invoke( o2, objectArray ) ;
							}
							
							//Dhanshree on 17/11/08
							if ( (paramType.equals("double") || paramType.equals("java.lang.Double") ) && (returnType.equals("int") || returnType.equals("java.lang.Integer")) )
							{
								//int -> float
//								logger.debug("Going to convert integer to float");
								Integer returnValue = ((Integer) objectArray[0]);
								double fReturnValue = returnValue.doubleValue();
								Double paramValue = new Double ( fReturnValue / 100 );
//								logger.debug("Going to set the following float value : " + paramValue );
								objectArray[0] = paramValue; 
								
//								o2Methods[j].invoke( o2, objectArray ) ;
							}
//							logger.debug("Parameter type required in set method is " + params[0].getName() );
							if ( paramType.equals("java.lang.String") )
							{
								//logger.debug ( "String after trim() >"+ objectArray[0]+"<");
								//Dhanshree on 06/10/08
								//Remove trim
							//	objectArray[0] = ((String) objectArray[0]).trim();	
								objectArray[0] = ((String) objectArray[0]);
							}
						}
						if ( (paramType.equals("java.lang.Date") || paramType.equals("java.util.Date") )
									&& (returnType.equals("java.lang.String")) ) {
							//logger.debug ( "Data type mismatch occured between getter and setter method, hence skipping this field");
							continue;
						}
						o2Methods[j].invoke( o2, objectArray ) ;
						break ;
					}
				}
			}
		}
//		logger.info("EXITING => copyObject()" );
	}
}
