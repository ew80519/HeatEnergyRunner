import java.util.Scanner;
public class Main {
    
	public static void main(String[] args) {
	    
Scanner reader = new Scanner(System.in);
        System.out.println("Enter in the mass of the water, in grams");	
	    double mass = reader.nextDouble();
	    System.out.println("Enter the starting temperature, in Celsius");
	    double initialTemp = reader.nextDouble();
	    if (initialTemp < -273.14)
	        initialTemp = -273.14;
	    System.out.println("Enter the final temperature, in Celsius");
	    double finalTemp = reader.nextDouble();
	    if(finalTemp < -273.14)
	        finalTemp = -273.14;
	        
	    String initialPhase = "Liquid";
	    if(initialTemp < 0)
	        initialPhase = "Solid";
	    else if(initialTemp > 100)
	        initialPhase = "Gas";
	        
	    String finalPhase = "Liquid";
	    if(finalTemp < 0)
	        finalPhase = "Solid";
	    else if(finalTemp > 100)
	        finalPhase = "Gas";
	        
	    System.out.println("Mass: " + mass + "g");
	    System.out.println("Initial temperature: " + initialTemp + "C" + "\nInitial phase: " + initialPhase);
	    System.out.println("Final temperature: " + finalTemp + "C" + "\nFinal phase: " + finalPhase + "\n");
	    
	    boolean endothermic = false;
	    
	    if(finalTemp > initialTemp)
	        endothermic = true;
	        
	    double heatEnergy = 0;
	    if(initialPhase.equals("Solid")) {
	        heatEnergy += tempChangeSolid(mass, initialTemp, finalTemp, finalPhase, endothermic);
	        
	        if(!finalPhase.equals("Solid")) {
	            heatEnergy += fusion(mass, endothermic);
	            heatEnergy += tempChangeLiquid(mass, 0, finalTemp, finalPhase, endothermic);
	        }
	        
	        if(finalPhase.equals("Gas")) {
	            heatEnergy += vaporization(mass, endothermic);
	            heatEnergy += tempChangeGas(mass, 100, finalTemp, finalPhase, endothermic);
	        }
	        
	    }
	    
	    if(initialPhase.equals("Liquid")) {
	        heatEnergy += tempChangeLiquid(mass, initialTemp, finalTemp, finalPhase, endothermic);
	        
	        if(finalPhase.equals("Solid")) {
	            heatEnergy += fusion(mass, endothermic);
	            heatEnergy += tempChangeSolid(mass, 0, finalTemp, finalPhase, endothermic);
	        }
	        if(finalPhase.equals("Gas")) {
	            heatEnergy += vaporization(mass, endothermic);
	            heatEnergy += tempChangeGas(mass, 100, finalTemp, finalPhase, endothermic);
	        }
	    }
	    
	    if(initialPhase.equals("Gas")) {
	        heatEnergy += tempChangeGas(mass, initialTemp, finalTemp, finalPhase, endothermic);
	        
	        if(!finalPhase.equals("Gas")) {
	            heatEnergy += vaporization(mass, endothermic);
	            heatEnergy += tempChangeLiquid(mass, 100, finalTemp, finalPhase, endothermic);
	        }
	        
	        if(finalPhase.equals("Solid")) {
	            heatEnergy += fusion(mass, endothermic);
	            heatEnergy += tempChangeSolid(mass, 0, finalTemp, finalPhase, endothermic);
	            
	        }    
	    }
	    
	    
	    System.out.println("Total Heat Energy: " + heatEnergy + "kJ");
	}
	public static double tempChangeSolid(double mass, double startTemp, double endTemp, String endPhase, boolean endothermic) {
	    if(!endPhase.equals("Solid"))
	        endTemp = 0;
	    double energyChange = round(mass*0.002108*(endTemp - startTemp));
        if(endothermic)
            System.out.println("Heating (Solid): " + energyChange + " kJ");
        else
            System.out.println("Cooling (Solid): " + energyChange + " kJ");
        return energyChange;
	}
	
	public static double tempChangeLiquid(double mass, double startTemp, double endTemp, String endPhase, boolean endothermic) {
	    if(endPhase.equals("Solid"))
	        endTemp = 0;
	    if(endPhase.equals("Vapor"))
	        endTemp = 100;
	    double energyChange = round(mass*0.004184*(endTemp - startTemp));
        if(endothermic)
            System.out.println("Heating (Liquid): " + energyChange + " kJ");
        else
            System.out.println("Cooling (Liquid): " + energyChange + " kJ");
        return energyChange;
	}
	
	public static double tempChangeGas(double mass, double startTemp, double endTemp, String endPhase, boolean endothermic) {
	    if(!endPhase.equals("Vapor"))
	        endTemp = 100;
	    double energyChange = round(mass*0.001996*(endTemp - startTemp));
        if(endothermic)
            System.out.println("Heating (Gas): " + energyChange + " kJ");
        else
            System.out.println("Cooling (Gas): " + energyChange + " kJ");
        return energyChange;
	}
	
	public static double fusion(double mass, boolean endothermic) {
	    double energyChange;
	    if(endothermic) {
	        energyChange = round(0.333*mass);
	        System.out.println("Melting: " + energyChange + " kJ");
	    }
	    else {
	        energyChange = round(-0.333*mass);
	        System.out.println("Freezing: " + energyChange + " kJ");
	    }
	    return energyChange;
	}
	
	public static double vaporization(double mass, boolean endothermic) {
	    double energyChange;
	    if(endothermic) {
	        energyChange = round(2.257*mass);
	        System.out.println("Evaporation: " + energyChange + " kJ");
	    }
	    else {
	        energyChange = round(-2.257*mass);
	        System.out.println("Condensation: " + energyChange + " kJ");
	    }
	    return energyChange;
	}
	
	public static double round(double x) {
	    x *= 1000;
	    if(x > 0) 
	        return (int)(x + 0.5)/1000.0;
	    else
	        return (int)(x - 0.5)/1000.0;
	}
	
	
}





