package fr.kata.depth.first.search.depthFirstSearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.kata.depth.first.search.depthFirstSearch.entity.Coord;
import fr.kata.depth.first.search.depthFirstSearch.entity.Map;
import fr.kata.depth.first.search.depthFirstSearch.enumeration.EAttributRecherche;
import fr.kata.depth.first.search.depthFirstSearch.service.MapService;
import fr.kata.depth.first.search.depthFirstSearch.service.MazeDeapthSearchService;
import fr.kata.depth.first.search.depthFirstSearch.service.UserInterfaceService;

@SpringBootTest
class DepthFirstSearchApplicationTests {
	
	@Autowired
	MapService mapService;
	
	@Autowired
	UserInterfaceService userInterfaceService;
	
	@Autowired
	MazeDeapthSearchService mazeDeapthSearchService;
	
	Logger logger = LoggerFactory.getLogger(DepthFirstSearchApplicationTests.class);

	
	@Test
	void demanderValeurAUtilisateurTest() {
		
		logger.info(" Test demanderValeurAUtilisateurTest \n");
		
		ByteArrayInputStream in = new ByteArrayInputStream("P\nM\nx\n5".getBytes());
		System.setIn(in);
		Scanner sc= new Scanner(System.in);   
		int maxRows = -1;
		
		while (maxRows <= 0) {
			maxRows = userInterfaceService.demanderValeurEntierAUtilisateur(sc, "Entrez un nombre de ligne pour le labyrinthe supérieur à 0 : \n");
		}
		
		assertTrue(maxRows > 0);
		
		logger.info(" Fin Test demanderValeurAUtilisateurTest \n");

	}
	
	@Test
	void creerMapTest() {
		
		logger.info(" Test creerMapTest \n ");

		ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
		System.setIn(in);
		Scanner sc= new Scanner(System.in); 
		int maxRows = -1;
		while (maxRows <= 0) {
			maxRows = userInterfaceService.demanderValeurEntierAUtilisateur(sc, "Entrez un nombre de ligne pour le labyrinthe supérieur à 0 : \n");
		}
		
		in = new ByteArrayInputStream("7".getBytes());
		System.setIn(in);
		sc= new Scanner(System.in); 
		int maxCols = -1;
		while (maxCols <= 0) {
			 maxCols = userInterfaceService.demanderValeurEntierAUtilisateur(sc, "Entrez un nombre de colonnes pour le labyrinthe supérieur à 0 : \n");

		}
		
		
		Map map = mapService.creerMap(maxRows, maxCols);
		
		assertEquals(5, map.getMaxRows());
		assertEquals(5, map.getMap().length);
		
		assertEquals(7, map.getMaxCols());
		assertEquals(7, map.getMap()[0].length);
		
		logger.info(" Fin Test creerMapTest \n");

	}
	
	@Test
	void chercherPositionsSortieDemande() {
		
		logger.info(" Test chercherPositionsSortieDemande \n");

		int recherche = 0;
		ByteArrayInputStream in = new ByteArrayInputStream("-1".getBytes());
		System.setIn(in);
		Scanner sc= new Scanner(System.in);
		
		Map mapEntity = new Map(4,3);
		int[][] map = mapEntity.getMap();
		map[0][2] = -1;
		map[2][2] = -1;
		map[3][1] = -1;
		
		map[0][0] = 1;

		
		Coord[] sorties = {new Coord(0,2),new Coord(2,2),new Coord(3,1)};
		Coord depart = new Coord(0,0);
			
		while (recherche != EAttributRecherche.ENTREE.getValeur() && recherche != EAttributRecherche.SORTIE.getValeur()) {
			
			recherche = userInterfaceService.demanderValeurEntierAUtilisateur(sc, "Tapez 1 pour la position de départ ou -1 pour les positions de sorties : \n");;
			
		}
		
		mapEntity.toString();
        String typeRecherche = recherche == EAttributRecherche.ENTREE.getValeur() ? "entrées" : "sorties";

		List<Coord> sortiesTrouve = mazeDeapthSearchService.chercherPositionSortieOuEntree(depart, recherche, new ArrayList<>(), new ArrayList<>(), mapEntity);
		
		System.out.println("Voici les positions des "+typeRecherche+" recherchés dans la map : " + sortiesTrouve);

	
		assertArrayEquals(sorties, sortiesTrouve.toArray());
		
		logger.info(" Fin Test chercherPositionsSortieDemande \n");


	}
	
	@Test
	void chercherPositionsEntreeDemande() {
		
		logger.info(" Test chercherPositionsEntreeDemande \n");

		int recherche = 0;
		ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
		System.setIn(in);
		Scanner sc= new Scanner(System.in);
		
		Map mapEntity = new Map(4,3);
		int[][] map = mapEntity.getMap();
		map[0][2] = -1;// sortie dans la map
		map[2][2] = -1;// sortie dans la map
		map[3][1] = -1;// sortie dans la map
		
		map[0][1] = 1; // entree dans la map
		
		Coord[] sorties = {new Coord(0,2),new Coord(2,2),new Coord(3,1)};
		Coord depart = new Coord(0,0);
		Coord[] entrees = {new Coord(0,1)};
			
		while (recherche != EAttributRecherche.ENTREE.getValeur() && recherche != EAttributRecherche.SORTIE.getValeur()) {
			
			recherche = userInterfaceService.demanderValeurEntierAUtilisateur(sc, "Tapez 1 pour la position de départ ou -1 pour les positions de sorties : \n");;
			
		}
		
		mapEntity.toString();

        String typeRecherche = recherche == EAttributRecherche.ENTREE.getValeur() ? "entrées" : "sorties";

		List<Coord> entreesTrouve = mazeDeapthSearchService.chercherPositionSortieOuEntree(depart, recherche, new ArrayList<>(), new ArrayList<>(), mapEntity);
		System.out.println("Voici les positions des "+typeRecherche+" recherchés dans la map : " + entreesTrouve);

		assertNotEquals(sorties, entreesTrouve.toArray());
		assertArrayEquals(entrees, entreesTrouve.toArray());
		
		logger.info(" Fin Test chercherPositionsEntreeDemande \n");


	}
	
	@Test
	void testInitialiserAleatoirementPositionEntree() {
		
		logger.info(" Test testInitialiserAleatoirementPositionEntree \n");

		
		ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
		System.setIn(in);
		Scanner sc= new Scanner(System.in); 
		int maxRows = -1;
		while (maxRows <= 0) {
			maxRows = userInterfaceService.demanderValeurEntierAUtilisateur(sc, "Entrez un nombre de ligne pour le labyrinthe supérieur à 0 : \n");
		}
		
		in = new ByteArrayInputStream("7".getBytes());
		System.setIn(in);
		sc= new Scanner(System.in); 
		int maxCols = -1;
		while (maxCols <= 0) {
			 maxCols = userInterfaceService.demanderValeurEntierAUtilisateur(sc, "Entrez un nombre de colonnes pour le labyrinthe supérieur à 0 : \n");

		}
		
		
		Map map = mapService.creerMap(maxRows, maxCols);
		
		boolean isEntreeDansMap = false;
		
		for (int i = 0; i < maxRows; i ++) {
			boolean exit = false;
			for (int j = 0; j < maxCols; j++) {
				
				if (map.getMap()[i][j] == 1) {
					isEntreeDansMap = true;
					exit = true;
					break;
				}
			}
			if (exit) {
				break;
			}
		}
		
		assertTrue(isEntreeDansMap);
		
		logger.info(" Fin Test testInitialiserAleatoirementPositionEntree \n");

	}
	
	@Test
	void testInitialiserAleatoirementPositionSortie() {
		
		logger.info(" Test testInitialiserAleatoirementPositionSortie \n");

		
		ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
		System.setIn(in);
		Scanner sc= new Scanner(System.in); 
		int maxRows = -1;
		while (maxRows <= 0) {
			maxRows = userInterfaceService.demanderValeurEntierAUtilisateur(sc, "Entrez un nombre de ligne pour le labyrinthe supérieur à 0 : \n");
		}
		
		in = new ByteArrayInputStream("7".getBytes());
		System.setIn(in);
		sc= new Scanner(System.in); 
		int maxCols = -1;
		while (maxCols <= 0) {
			 maxCols = userInterfaceService.demanderValeurEntierAUtilisateur(sc, "Entrez un nombre de colonnes pour le labyrinthe supérieur à 0 : \n");

		}
		
		
		Map map = mapService.creerMap(maxRows, maxCols);
		
		boolean isSortieDansMap = false;
		
		for (int i = 0; i < maxRows; i ++) {
			boolean exit = false;
			for (int j = 0; j < maxCols; j++) {
				
				if (map.getMap()[i][j] == -1) {
					isSortieDansMap = true;
					exit = true;
					break;
				}
			}
			if (exit) {
				break;
			}
		}
		
		assertTrue(isSortieDansMap);
		
		logger.info(" Fin Test testInitialiserAleatoirementPositionSortie \n");

	}

}
