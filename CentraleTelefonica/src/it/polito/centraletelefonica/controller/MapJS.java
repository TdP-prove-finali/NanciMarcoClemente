package it.polito.centraletelefonica.controller;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.maps.model.LatLng;

import it.polito.centraletelefonica.db.OperationCenterDAO;
import it.polito.centraletelefonica.db.OperationDAO;
import it.polito.centraletelefonica.model.Operation;
import it.polito.centraletelefonica.model.OperationCenter;

public class MapJS {
	
	public static String addCentrale(LatLng coordinate) {
		return "addCentrale(" + coordinate.lat + ", " + coordinate.lng + "" + ")";
	}
	
	public static String addMarker(LatLng coordinate) {
		return "addMarker(" + coordinate.lat + ", " + coordinate.lng + "" + ")";
	}
	
	public static String addMarkers(LocalDate value) {
		String result = "";
		OperationDAO dao = new OperationDAO();
		List<Operation> operations = new LinkedList<>(dao.openedOperations(value));
		for (Operation operation : operations) {
			result += addMarker(operation.getCoordinate()) + ";";
		}
		return result;
	}

	public static String initMap(Map<String,OperationCenter> centrali) {
		OperationCenterDAO dao = new OperationCenterDAO();
		List<OperationCenter> centers = new LinkedList<>(dao.getAllOperationCenter(centrali));
		String res = "";
		for (OperationCenter operationCenter : centers) {
			res += addCentrale(operationCenter.getLatLng()) + ";";
		}
		return res;
	}


}
