package org.compiere.util;
/**
 *
 *  @author     Sam Cardenas
 *  @version    $Id: EnviromentTools.java,v 1.10 2007/01/20 01:35:36 scardenas Exp $
 */
public final  class EnviromentTools {	
	/**Clogger	*/
	private static CLogger			log = CLogger.getCLogger (EnviromentTools.class);
	/*
	 * Free memory available
	 */
	public static void callingFinal(){
		Runtime run = Runtime.getRuntime();
		System.out.println("Amount of free memory 	: "+ run.freeMemory());	
		System.out.println("Maximum amount of mem : "+ run.maxMemory());	
		System.out.println("Total amount of memory 	: "+ run.totalMemory());	
		System.out.println("Number of processors available		 	: "+  run.availableProcessors());
		System.out.println("Running the final  ");
		run.runFinalization() ;
	}
	public static void explicitCleanProcess(){
		Runtime run = Runtime.getRuntime();				
		System.out.println("Amount of free memory 	: "+ run.freeMemory());	
		System.out.println("Maximum amount of mem 	: "+ run.maxMemory());	
		System.out.println("Total amount of memory 	: "+ run.totalMemory());	
		System.out.println("Number of processors available 			 	: "+  run.availableProcessors());
		System.out.println("Running the explicit cleaning  ");
		run.gc();			
	}
	public static void seggestedCleaing(){
		Runtime run = Runtime.getRuntime();				
		System.out.println("Amount of free memory 	: "+ run.freeMemory());	
		System.out.println("Maximum amount of mem 	: "+ run.maxMemory());	
		System.out.println("Total amount of memory 	: "+ run.totalMemory());	
		System.out.println("Number of processors available 			 	: "+  run.availableProcessors());
		System.out.println("Running the suggested cleaning  ");
		System.gc();
	}
}
