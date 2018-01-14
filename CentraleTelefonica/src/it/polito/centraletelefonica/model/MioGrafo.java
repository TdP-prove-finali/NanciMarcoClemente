package it.polito.centraletelefonica.model;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DefaultWeightedEdge;

public class MioGrafo extends AbstractBaseGraph<Nodo, DefaultWeightedEdge>
		implements UndirectedGraph<Nodo, DefaultWeightedEdge> {

	private static final long serialVersionUID = 1L;

	public MioGrafo() {
		super(new ClassBasedEdgeFactory<Nodo, DefaultWeightedEdge>(DefaultWeightedEdge.class), true, true);
	}
}
