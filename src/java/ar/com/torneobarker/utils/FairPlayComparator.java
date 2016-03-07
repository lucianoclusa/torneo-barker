package ar.com.torneobarker.utils;

import java.util.Comparator;

import ar.com.torneobarker.command.EstadisticaFairPlay;
import ar.com.torneobarker.command.EstadisticaJugador;

public class FairPlayComparator implements Comparator<EstadisticaFairPlay> {
    @Override
    public int compare(EstadisticaFairPlay o1, EstadisticaFairPlay o2) {
    	if(o1.getPuntos()>o2.getPuntos())
    		return 1;
    	if(o1.getPuntos()<o2.getPuntos())
    		return -1;
    	else{
    		if(o1.getTarjetasRojas()<o2.getTarjetasRojas())
        		return 1;
        	if(o1.getTarjetasRojas()>o2.getTarjetasRojas())
        		return -1;
        	else
        		if(o1.getTarjetasAmarillas()<o2.getTarjetasAmarillas())
            		return 1;
            	if(o1.getTarjetasAmarillas()>o2.getTarjetasAmarillas())
            		return -1;
    		return 0;
    	}
    }
}
