package util;

import java.util.Random;
import lists.List;

/**
 * La clase Randoms se encarga de ofrecer facilidades de aleatoriedad al desarrollador
 * 
 * @author (Tribeth Rivas Pérez)
 * @version (1.0 01/08/2018)
 */

public class Randoms {

    /**
     * Retorna un entero entre el rango establecido
     * 
     * @param  int inicio y fin del rango
     * @return Retorna un entero entre el rango establecido [min,max]
     */
    public static int randInt(int min, int max){
        return new Random().nextInt(max-min+1)+min;
    }
    public static boolean randTrueInProbability(int probability) {
    	return randInt(1,100)<probability;
    }
    /**
     * Selecciona un elemento aleatorio de la lista
     * 
     * @param  lista a evaluar
     * @return un elemento aleatorio de la lista
     */
    public static Object choice(Object[] lista) {
    	return lista[randInt(0,lista.length-1)];
    }
    public static Object choice(int[] lista) {
    	return lista[randInt(0,lista.length-1)];
    }
    public static String[] choice(List<String[]> lista) {
    	return lista.getValue(randInt(0,lista.getLength()-1));
    }
    
}
