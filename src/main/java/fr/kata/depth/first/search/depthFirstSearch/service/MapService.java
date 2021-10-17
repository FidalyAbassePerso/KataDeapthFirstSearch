package fr.kata.depth.first.search.depthFirstSearch.service;

import java.util.Random;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import fr.kata.depth.first.search.depthFirstSearch.entity.Coord;
import fr.kata.depth.first.search.depthFirstSearch.entity.Map;

@Service
public class MapService{
	
	Logger logger = LoggerFactory.getLogger(MapService.class);

	
	public Map creerMap(int maxRows, int maxCols) {
		
		logger.info("############## On initialise la map avec les valeurs ligne,colonne : "+maxRows+","+maxCols+" ##############");
		
		Map mapEntity = new Map(maxRows,maxCols);
		int[][] map = mapEntity.getMap(); // matrice représentant le labyrinthe.
		
		for (int i = 0; i < 2 ; i ++) {
			
			for (int j = 0; j < 5 ; j ++) {
				map[i][j] = 0;
			}
			
		}
		
		logger.info("############## On initialise aléatoirement la position de départ du joueur/ l'entrée ##############");
		
		if (mapEntity.getPositionDepart() == null) {
			mapEntity = initialiserPositionAleatoireJoueur(maxRows,maxCols,mapEntity);
			map = mapEntity.getMap(); // la fonction initialisation a modifier la map donc on la met a jour
		}
		
		logger.info("############## On initialise aléatoirement dans la map la position des sorties ##############");

		map = initialiserPositionAleatoireDesSorties(maxRows,maxCols,map);
		
		mapEntity.setMap(map);
		return mapEntity;
		
	}
	
	private int[][] initialiserPositionAleatoireDesSorties(int maxRows,int maxCols,int[][] map) {
		
		Random rand = new Random(); 
		int randomNbExit = rand.nextInt((maxRows*maxCols)/2);
		logger.info("Nombre de sorties : "+randomNbExit);
		
		int randomX = rand.nextInt(maxRows);
		int randomY = rand.nextInt(maxCols);

		
		while (randomNbExit > 0) {
			
			if (map[randomX][randomY] == 0 && map[randomX][randomY] != 1 && map[randomX][randomY] != -1) {
				map[randomX][randomY] = -1;
				randomNbExit -= 1;
			}
			else {
				randomX = rand.nextInt(maxRows);
				randomY = rand.nextInt(maxCols);
			}
		}
		
		logger.info("Les sorties ont été initialisés dans la map");
		
		return map;
	}
	
	private Map initialiserPositionAleatoireJoueur(int maxRows,int maxCols,Map mapEntity){
		
		Random rand = new Random(); 

		int randomX = rand.nextInt(maxRows);
		int randomY = rand.nextInt(maxCols);
		
		// on met à jour la position de depart dans l'objet map
		Coord coord = new Coord(randomX,randomY);
		mapEntity.setPositionDepart(coord);
		
		// on met à jour la position de depart dans le tableau representant la map
		int[][] map = mapEntity.getMap();
		map[randomX][randomY] = 1;
		mapEntity.setMap(map);
		
		return mapEntity;

	}

	
	
	

}
