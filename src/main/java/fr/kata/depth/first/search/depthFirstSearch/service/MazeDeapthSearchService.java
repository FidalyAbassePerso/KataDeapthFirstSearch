package fr.kata.depth.first.search.depthFirstSearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.kata.depth.first.search.depthFirstSearch.entity.Coord;
import fr.kata.depth.first.search.depthFirstSearch.entity.Map;

@Service
public class MazeDeapthSearchService {
	
	@Autowired
	MapService mapService;

	
	/** Recherche de façon recursive avec l'algorithme de parcours en profondeur les sorties/entrée dans la map
	 * 
	 * @param c coordonnee de depart
	 * 
	 * @param recherche attribut recherché 1 pour entrée et -1 pour sortie
	 * 
	 * @param positionsRecherche la liste des positions de l'attribut recherché
	 * 
	 * @param coordMarquees la liste des positions deja visités
	 * 
	 * @param map la map parcouru
	 * 
	 * @return une liste de coordonnees representant les sorties ou entrées dans la map
	 */
	public List<Coord> chercherPositionSortieOuEntree(Coord c,int recherche, List<Coord> positionsRecherche, List<Coord> coordMarquees, Map map) {
		
		coordMarquees.add(c);
		
		if (map.faitPartieDeLaMap(c) && map.getMap()[c.getX()][c.getY()]==recherche && !positionsRecherche.contains(c)) {
			positionsRecherche.add(c);
		}
		
		for (Coord voisin : c.getVoisinsAdjacent()) {

			if (map.faitPartieDeLaMap(voisin) && map.getMap()[voisin.getX()][voisin.getY()]==recherche && !positionsRecherche.contains(voisin)) {
				positionsRecherche.add(voisin);
			}
			if (!coordMarquees.contains(voisin) && map.faitPartieDeLaMap(voisin)) {
				chercherPositionSortieOuEntree(voisin,recherche,positionsRecherche,coordMarquees,map);
			}
		}
		
		
		return positionsRecherche;
	}

}
