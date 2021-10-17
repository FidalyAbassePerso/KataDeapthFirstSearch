package fr.kata.depth.first.search.depthFirstSearch.service;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserInterfaceService {
	
	Logger logger = LoggerFactory.getLogger(UserInterfaceService.class);

	
	public int demanderValeurAUtilisateur(Scanner sc,String message) {
		
		System.out.print(message);
		
		int valeur = -1;
		
		while (!sc.hasNextInt()) {
			System.out.print(message);
			sc.nextLine();
		}
		valeur = sc.nextInt();
		
		return valeur;
	}
	
	

}
