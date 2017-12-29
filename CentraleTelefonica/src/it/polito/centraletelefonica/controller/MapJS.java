package it.polito.centraletelefonica.controller;

import java.util.LinkedList;
import java.util.List;

import com.google.maps.model.LatLng;

import it.polito.centraletelefonica.db.OperationCenterDAO;
import it.polito.centraletelefonica.model.OperationCenter;

public class MapJS {
	
	public static String addCentrale(LatLng coordinate) {
		return "addCentrale(" + coordinate.lat + ", " + coordinate.lng + "" + ")";
	}
	
	public String addMarker(LatLng coordinate) {
		return "addMarker(" + coordinate.lat + ", " + coordinate.lng + "" + ")";
	}

	public static String initMap() {
		OperationCenterDAO dao = new OperationCenterDAO();
		List<OperationCenter> centers = new LinkedList<>(dao.getAllOperationCenter());
		String res = "";
		for (OperationCenter operationCenter : centers) {
			res += addCentrale(operationCenter.getLatLng()) + ";";
		}
		return res;
	}


}
