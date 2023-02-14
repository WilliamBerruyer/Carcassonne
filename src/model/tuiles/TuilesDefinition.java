package model.tuiles;

import java.util.ArrayList;

import model.terrain.*;

public class TuilesDefinition extends Tuiles {
    //TerrainTypesCotes CHATEAU,CHAMP;
    //TerrainTypesCentre MONASTERE,CHAMPC,CHATEAUC,ROUTE,NULL,VILLAGE;
    ArrayList<Tuiles> piocheDebut;
    ArrayList<Tuiles> piocheTmp = new ArrayList<>();
    Tuiles ChateauCentre = new Tuiles(1, 1, "CastleCenter");
    public Tuiles ChateauCoteChampCentre = new Tuiles(1, 4, "CastleWall");
    Tuiles ChateauCoteRouteBasDroite = new Tuiles(3, 4, "CastleWallCurveLeft");
    Tuiles ChateauCoteHautRouteBasDroite = new Tuiles(1, 4, "CastleWallCurveRight");
    //Tuiles ChateauChampMilleuChateau = new Tuiles(2,2, "CastleSides");
    Tuiles ChateauRouteGaucheDroite = new Tuiles(1, 4, "CastleWallRoad");
    //Tuiles ChateauRouteGaucheDroiteBas = new Tuiles(3,4,"CastleWallJunction");
    Tuiles ChateauHautDroite = new Tuiles(1, 4, "CastleEdge");
    Tuiles ChateauDroiteHautRouteBasGauche = new Tuiles(1, 4, "CastleEdgeRoad");
    Tuiles ChateauGaucheDroiteEtale = new Tuiles(1, 2, "CastleTube");
    Tuiles ChateauDroiteHautGaucheChampBas = new Tuiles(1, 4, "CastleCenterSide");
    Tuiles ChateauDroiteHautGaucheChampBasRoute = new Tuiles(1, 4, "CastleCenterEntry");
    //Tuiles ChateauColleCote = new Tuiles(1,4, "CastleSidesEdge");
    Tuiles MonastereRoute = new Tuiles(1, 4, "MonasteryRoad");
    Tuiles MonastereCentre = new Tuiles(1, 1, "Monastery");
    Tuiles Route = new Tuiles(2, 2, "Road");
    //Tuiles RouteCroix = new Tuiles(1,1, "RoadJunctionLarge");
    //Tuiles RouteTrois = new Tuiles(4,4, "RoadJunctionSmall");
    Tuiles RouteCourbe = new Tuiles(2, 4, "RoadCurve");

    public TuilesDefinition() {
        piocheDebut = new ArrayList<>();
        String tuileNom;

        ChateauDroiteHautGaucheChampBasRoute.setTypesCotes(TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU);
        ChateauDroiteHautGaucheChampBasRoute.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.ROUTE, TerrainTypesCentre.NULL, TerrainTypesCentre.CHATEAUC);
        tuileNom = ChateauDroiteHautGaucheChampBasRoute.name + 0;
        ChateauDroiteHautGaucheChampBasRoute.setName(tuileNom);
        ChateauDroiteHautGaucheChampBasRoute.setImage();
        ChateauDroiteHautGaucheChampBasRoute.setIndicePions(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1);
        piocheDebut.add(ChateauDroiteHautGaucheChampBasRoute);


        ChateauDroiteHautGaucheChampBas.setTypesCotes(TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU);
        ChateauDroiteHautGaucheChampBas.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.CHATEAUC);
        tuileNom = ChateauDroiteHautGaucheChampBas.name + 0;
        ChateauDroiteHautGaucheChampBas.setName(tuileNom);
        ChateauDroiteHautGaucheChampBas.setImage();
        ChateauDroiteHautGaucheChampBas.setIndicePions(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
        piocheDebut.add(ChateauDroiteHautGaucheChampBas);

        ChateauCentre.setTypesCotes(TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU);
        ChateauCentre.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.CHATEAUC);
        tuileNom = ChateauCentre.name + 0;
        ChateauCentre.setName(tuileNom);
        ChateauCentre.setImage();
        ChateauCentre.setIndicePions(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
        piocheDebut.add(ChateauCentre);

        ChateauGaucheDroiteEtale.setTypesCotes(TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU);
        ChateauGaucheDroiteEtale.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.CHATEAUC);
        tuileNom = ChateauGaucheDroiteEtale.name + 0;
        ChateauGaucheDroiteEtale.setName(tuileNom);
        ChateauGaucheDroiteEtale.setImage();
        ChateauGaucheDroiteEtale.setIndicePions(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
        piocheDebut.add(ChateauGaucheDroiteEtale);

        ChateauDroiteHautRouteBasGauche.setTypesCotes(TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP);
        ChateauDroiteHautRouteBasGauche.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.ROUTE, TerrainTypesCentre.ROUTE, TerrainTypesCentre.NULL);
        tuileNom = ChateauDroiteHautRouteBasGauche.name + 0;
        ChateauDroiteHautRouteBasGauche.setName(tuileNom);
        ChateauDroiteHautRouteBasGauche.setImage();
        ChateauDroiteHautRouteBasGauche.setIndicePions(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0);
        piocheDebut.add(ChateauDroiteHautRouteBasGauche);

        ChateauHautDroite.setTypesCotes(TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP);
        ChateauHautDroite.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL);
        tuileNom = ChateauHautDroite.name + 0;
        ChateauHautDroite.setName(tuileNom);
        ChateauHautDroite.setImage();
        ChateauHautDroite.setIndicePions(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        piocheDebut.add(ChateauHautDroite);

        /*ChateauRouteGaucheDroiteBas.setTypesCotes(TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP);
        ChateauRouteGaucheDroiteBas.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.ROUTE, TerrainTypesCentre.ROUTE, TerrainTypesCentre.ROUTE, TerrainTypesCentre.VILLAGE);
        tuileNom = ChateauRouteGaucheDroiteBas.name + 0;
        ChateauRouteGaucheDroiteBas.setName(tuileNom);
        ChateauRouteGaucheDroiteBas.setImage();
        ChateauRouteGaucheDroiteBas.setIndicePions(0,1,0,0,0,0,0,0,0,1,1,1,0);
        ChateauRouteGaucheDroiteBas.troisRoutes = true;
        piocheDebut.add(ChateauRouteGaucheDroiteBas);*/

        ChateauRouteGaucheDroite.setTypesCotes(TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP);
        ChateauRouteGaucheDroite.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.ROUTE, TerrainTypesCentre.NULL, TerrainTypesCentre.ROUTE, TerrainTypesCentre.NULL);
        tuileNom = ChateauRouteGaucheDroite.name + 0;
        ChateauRouteGaucheDroite.setName(tuileNom);
        ChateauRouteGaucheDroite.setImage();
        ChateauRouteGaucheDroite.setIndicePions(0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0);
        piocheDebut.add(ChateauRouteGaucheDroite);

        /*ChateauChampMilleuChateau.setTypesCotes(TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP);
        ChateauChampMilleuChateau.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL);
        tuileNom = ChateauChampMilleuChateau.name + 0;
        ChateauChampMilleuChateau.setName(tuileNom);
        ChateauChampMilleuChateau.setImage();
        ChateauChampMilleuChateau.setIndicePions(0,1,0,0,1,0,0,0,0,0,0,0,0);
        ChateauChampMilleuChateau.deuxChateaux = true;
        piocheDebut.add(ChateauChampMilleuChateau);*/

        /*ChateauColleCote.setTypesCotes(TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU);
        ChateauColleCote.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL);
        tuileNom = ChateauColleCote.name + 0;
        ChateauColleCote.setName(tuileNom);
        ChateauColleCote.setImage();
        ChateauColleCote.setIndicePions(0,1,0,0,0,0,1,0,0,0,0,0,0);
        ChateauColleCote.deuxChateaux = true;
        piocheDebut.add(ChateauColleCote);*/

        ChateauCoteHautRouteBasDroite.setTypesCotes(TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP);
        ChateauCoteHautRouteBasDroite.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.ROUTE, TerrainTypesCentre.ROUTE, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL);
        tuileNom = ChateauCoteHautRouteBasDroite.name + 0;
        ChateauCoteHautRouteBasDroite.setName(tuileNom);
        ChateauCoteHautRouteBasDroite.setImage();
        ChateauCoteHautRouteBasDroite.setIndicePions(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0);
        piocheDebut.add(ChateauCoteHautRouteBasDroite);

        ChateauCoteRouteBasDroite.setTypesCotes(TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP);
        ChateauCoteRouteBasDroite.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.ROUTE, TerrainTypesCentre.ROUTE, TerrainTypesCentre.NULL);
        tuileNom = ChateauCoteRouteBasDroite.name + 0;
        ChateauCoteRouteBasDroite.setName(tuileNom);
        ChateauCoteRouteBasDroite.setImage();
        ChateauCoteRouteBasDroite.setIndicePions(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0);
        piocheDebut.add(ChateauCoteRouteBasDroite);

        ChateauCoteChampCentre.setTypesCotes(TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHATEAU, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP);
        ChateauCoteChampCentre.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.CHAMPC);
        tuileNom = ChateauCoteChampCentre.name + 0;
        ChateauCoteChampCentre.setName(tuileNom);
        ChateauCoteChampCentre.setImage();
        ChateauCoteChampCentre.setIndicePions(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        piocheDebut.add(ChateauCoteChampCentre);

        MonastereCentre.setTypesCotes(TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP);
        MonastereCentre.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.MONASTERE);
        tuileNom = MonastereCentre.name + 0;
        MonastereCentre.setName(tuileNom);
        MonastereCentre.setImage();
        MonastereCentre.setIndicePions(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
        MonastereCentre.possedeAbbaye = true;
        piocheDebut.add(MonastereCentre);

        MonastereRoute.setTypesCotes(TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP);
        MonastereRoute.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.ROUTE, TerrainTypesCentre.NULL, TerrainTypesCentre.MONASTERE);
        tuileNom = MonastereRoute.name + 0;
        MonastereRoute.setName(tuileNom);
        MonastereRoute.setImage();
        MonastereRoute.setIndicePions(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1);
        MonastereRoute.possedeAbbaye = true;
        piocheDebut.add(MonastereRoute);

        Route.setTypesCotes(TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP);
        Route.setTypesCentre(TerrainTypesCentre.ROUTE, TerrainTypesCentre.NULL, TerrainTypesCentre.ROUTE, TerrainTypesCentre.NULL, TerrainTypesCentre.NULL);
        tuileNom = Route.name + 0;
        Route.setName(tuileNom);
        Route.setImage();
        Route.setIndicePions(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0);
        piocheDebut.add(Route);

        /*RouteCroix.setTypesCotes(TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP);
        RouteCroix.setTypesCentre(TerrainTypesCentre.ROUTE, TerrainTypesCentre.ROUTE, TerrainTypesCentre.ROUTE, TerrainTypesCentre.ROUTE, TerrainTypesCentre.VILLAGE);
        tuileNom = RouteCroix.name + 0;
        RouteCroix.setName(tuileNom);
        RouteCroix.setImage();
        RouteCroix.setIndicePions(0,0,0,0,0,0,0,0,1,1,1,1,0);
        piocheDebut.add(RouteCroix);*/

        /*RouteTrois.setTypesCotes(TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP);
        RouteTrois.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.ROUTE, TerrainTypesCentre.ROUTE, TerrainTypesCentre.ROUTE, TerrainTypesCentre.VILLAGE);
        tuileNom = RouteTrois.name + 0;
        RouteTrois.setName(tuileNom);
        RouteTrois.setImage();
        RouteTrois.setIndicePions(0,0,0,0,0,0,0,0,0,1,1,1,0);
        RouteTrois.troisRoutes = true;
        piocheDebut.add(RouteTrois);*/

        RouteCourbe.setTypesCotes(TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP, TerrainTypesCotes.CHAMP);
        RouteCourbe.setTypesCentre(TerrainTypesCentre.NULL, TerrainTypesCentre.NULL, TerrainTypesCentre.ROUTE, TerrainTypesCentre.ROUTE, TerrainTypesCentre.NULL);
        tuileNom = RouteCourbe.name + 0;
        RouteCourbe.setName(tuileNom);
        RouteCourbe.setImage();
        RouteCourbe.setIndicePions(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0);
        piocheDebut.add(RouteCourbe);

    }

    public ArrayList<Tuiles> getPioche() throws CloneNotSupportedException {
        for (Tuiles tuiles : piocheDebut) {
            tuiles.creerRotation();
            tuiles.rotationCote();
            tuiles.rotationAxes();
            tuiles.rotationIndicePions();
            for (int i = 0; i < tuiles.exemplaire; i++) {
                Tuiles tmp = (Tuiles) tuiles.clone();
                piocheTmp.add(tmp);
            }
        }
        for (Tuiles tuiles : piocheTmp)
            piocheDebut.add(tuiles);
        return piocheDebut;
    }

}