package it.polito.centraletelefonica.controller;

import com.google.maps.model.LatLng;

public class MapJS {

	private static final String OPERATION_ICON = "var opImg = {\r\n" + 
			"        url: \"http://shmector.com/_ph/7/375131848.png\",\r\n" + 
			"        scaledSize: new google.maps.Size(30, 30), // scaled size\r\n" + 
			"        origin: new google.maps.Point(0,0), // origin\r\n" + 
			"        anchor: new google.maps.Point(0,0) // anchor\r\n" + 
			"        };";

	public static String addOperationMarker(String title, LatLng coordinate) {

		String script = "";
		String positionVar = "var pos = {lat:" + coordinate.lat + ", lng:" + coordinate.lng + "};";
		String var = "var op" + title + "= new google.maps.Marker({";
		String position = "position: pos,";
		String map = "map: map,";
		String icon = "icon: opImg";
		script = OPERATION_ICON + positionVar + var + position + map + icon +"});";

		return script;

	}

}
