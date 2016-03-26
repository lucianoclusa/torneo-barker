package ar.com.torneobarker.utils;

import java.util.Comparator;

import ar.com.torneobarker.command.EstadisticaEquipo;

public class PosicionesComparator implements Comparator<EstadisticaEquipo> {
    @Override
    public int compare(EstadisticaEquipo o1, EstadisticaEquipo o2) {
    	if(o1.getPuntos()<o2.getPuntos()){
    		return 1;
    	}else if(o1.getPuntos()>o2.getPuntos()){
    		return -1;
//    	}else{
//    		//Tienen iguales puntos, pasamos a comparar el Fair Play
//    		if(o1.getPuntosFairPlay()>o2.getPuntosFairPlay()){
//    			return 1;
//    		}else if(o1.getPuntosFairPlay()<o2.getPuntosFairPlay()){
//    			return -1;
    		}else{
    			//Tienen igual Fair Play, pasamos a comparar la diferencia de gol
        		if(o1.getDiferenciaGol()<o2.getDiferenciaGol()){
        			return 1;
        		}else if(o1.getDiferenciaGol()>o2.getDiferenciaGol()){
        			return -1;
        		}else{
        			//Tienen igual diferencia, pasamos a comparar goles a favor
        			if(o1.getGolesFavor()<o2.getGolesFavor()){
            			return 1;
            		}else if(o1.getGolesFavor()>o2.getGolesFavor()){
            			return -1;
            		}
        		}
    		}
    		
    		//Tienen igual puntos, fair play y dif de goles
    		return 0;
    	}
    }
//}
