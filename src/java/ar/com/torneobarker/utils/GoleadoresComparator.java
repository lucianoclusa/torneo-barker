package ar.com.torneobarker.utils;

import java.util.Comparator;

import ar.com.torneobarker.command.EstadisticaJugador;

public class GoleadoresComparator implements Comparator<EstadisticaJugador> {
    @Override
    public int compare(EstadisticaJugador o1, EstadisticaJugador o2) {
    	if(o1.getGolesFavor()<o2.getGolesFavor())
    		return 1;
    	if(o1.getGolesFavor()>o2.getGolesFavor())
    		return -1;
    	else
    		return 0;
    		
    }
}
