package controller;

import java.util.Random;

import model.Coup;
import model.Jeu;
import model.Pion;
import model.Plateau;
import model.Point;
import model.Tuple;

public class IAMinMax extends IA {
	Random r;
	Jeu j;
	int maxCoup = Integer.MIN_VALUE;
	int minCoup = Integer.MAX_VALUE;
	int y = 0;
	Coup m;
	
	public IAMinMax(Jeu jeu) {
		j = jeu;
	}
	
	@Override
	Coup joue(Plateau p, int l, int c) {
		
		try{
            p.setRotationTuiles(p.getPosesPossibles(), l, c);
        }catch (CloneNotSupportedException e){
            System.out.println("Problème clonage");
        }
		
		Coup cp = p.determinerCoup(l, c);
		return cp;
	}
	
	void dejoue(Plateau p, int l, int c) {
		p.removeTuile(l, c);
	}
	
	int heuristiqueCoup(Plateau p) {
		System.out.println("Heuristique : " + (p.getNbScore(recupererJoueur()) - p.getNbScore(recupererAdversaire())));
		return p.getNbScore(recupererJoueur()) - p.getNbScore(recupererAdversaire());
	}
	
	Tuple minMax(Plateau p, int horizon, boolean is_max, int compteur) {
		if (horizon == 0) {
			int valeurCoup = heuristiqueCoup(p);
			Tuple res = new Tuple(valeurCoup, null);
			return res;
		}
		else {
			if (compteur > 1) {
				p.piocherCarte(); }
			for (int k = 0; k < 150 ; k++) {
				for(Point possible:p.getPosesPossibles()) {
					int coupX = possible.x;
					int coupY = possible.y;
					
					Coup c = joue(p, coupX, coupY); 
					
					for (Pion emplacement : p.getTerrain(coupX, coupY).getPionsEmplacement()) {
						if (emplacement != null) {
							j.getPlateau().getTuile(coupX, coupY).setPion(emplacement);
					        minMax(p, horizon-1, !is_max, compteur+1);
					        j.getPlateau().getTuile(coupX, coupY).removePion();
						}
				     }
					
					Tuple result = minMax(p, horizon-1, !is_max, compteur+1);
					
					dejoue(p, coupX, coupY);
					
					if (is_max) {
						if (result.getScore() > maxCoup) {
							maxCoup = result.getScore();
							m = c;
						}
					}
					else {
						if (result.getScore() < minCoup) {
							minCoup = result.getScore(); 
							m = c;
						}
					}
				}
			}
		}
		if (is_max) {
			Tuple meilleurCoup = new Tuple(maxCoup, m);
			return meilleurCoup;
		}
		else {
			Tuple meilleurCoup = new Tuple(minCoup, m);
			return meilleurCoup;
		}
	}
	
	int recupererJoueur() {
		return j.getJoueur();
	}
	
	int recupererAdversaire() {
		if (j.getJoueur() == 1) return 2; else return 1;
	}

	@Override
	Coup joue(Plateau n) {
		return null;
	}
}

