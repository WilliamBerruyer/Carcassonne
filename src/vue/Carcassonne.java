package vue;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import Patterns.Observateur;
import controller.AdaptateurBoutons;
import controller.Controleur;
import controller.EcouteurComboBox;
import model.Jeu;
import model.Plateau;
import model.tuiles.Tuiles;
import model.tuiles.TuilesDefinition;


public class Carcassonne implements Observateur {

    JFrame f, partie, menuBoutons;
    BufferedImage icon, background, kb;
    ImageIcon title, np_i, rg_i, ch_i, q_i, co_i, board_i, retour_i, aide_i, tuilePioche, rotateI, validerI, annulerCoupI, aideIAI, hamburgerI, menuPrincipalI, reprendreI, jCourant, jBlue, jRed, lancerDoubleIA, lancerSimpleIA, co_grey;
    JButton np, ch, rg, q, aide, retour, commencer, valider, annulerPose, rotate, menu, aideIA, validerRotation, annulerRotation, menuPrincipal, reprendre, lancerJeu;
    JPanel p, panel, ihmJeu, panScores1, panScores2, panRestants, panBoutons, panRotate;
    JLabel titre, board, playing, tuilesRestantes, tuileCourante, imgTuile, j1Courant, j2Courant, j1Scores, j2Scores, joueur1, joueur2;
    JComboBox<String> j1, j2;
    JOptionPane regles, fin, commandesJeu, aideModes;
    CollecteurEvenements controle;
    PlateauGraphiqueSwing pg;
    boolean maximized = true, j1IA = false, j2IA = false;
    JScrollPane scrollPane;
    JScrollBar verticalScrollBar, horizontalScrollBar;
    Jeu jeu;
    Plateau plateau;
    String scores = "<html>Joueur <br/> Pions restants : 10 <br/> Score : </html>";
    Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
    AdaptateurSourisBoutons coListener;

    public void demarre(JFrame frame) {
        f = frame;
        base(true);
        Controleur cont0 = new Controleur(f);
        cont0.fixerInterfaceUtilisateur(this);
        controle = cont0;
        ecranPrincipal();
    }

    public void base(boolean principal) {
        if (partie != null) {
            partie.dispose();
            if (menuBoutons != null) {
                menuBoutons.dispose();
            }
        }
        //content pane de la fenêtre
        try {
            InputStream inIcon = ClassLoader.getSystemClassLoader().getResourceAsStream("IHM/icon.png");
            InputStream inBg = ClassLoader.getSystemClassLoader().getResourceAsStream("IHM/Start/bg.jpg");
            InputStream inKb = ClassLoader.getSystemClassLoader().getResourceAsStream("IHM/Start/knights_board.png");
            icon = ImageIO.read(inIcon);
            background = ImageIO.read(inBg);
            kb = ImageIO.read(inKb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        p = new JPanel();
        p.setBorder(new EmptyBorder(0, 0, 0, 0));
        f.setContentPane(p);


        //panel qui contient les images et boutons
        if (principal == true) {
            panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(background, 0, 0, null);
                }
            };
        } else {
            panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(kb, 0, 0, null);
                }
            };
        }


        GroupLayout gl_contentPane = new GroupLayout(p);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 719, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        f.setSize(574, 758);
        f.setIconImage(icon);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setVisible(true);
        p.setLayout(gl_contentPane);

    }

    public void ecranPrincipal() {
        try {
            title = new ImageIcon(getImage("IHM/title.png"));
            np_i = new ImageIcon(getImage("IHM/Start/np.png"));
            ch_i = new ImageIcon(getImage("IHM/Start/ch_grey.png"));
            rg_i = new ImageIcon(getImage("IHM/Start/rg.png"));
            q_i = new ImageIcon(getImage("IHM/Start/q.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        titre = createLabel("", title);

        np = createBouton("", np_i, "new");
        boutonInv(np);
        np.addMouseListener(new AdaptateurSourisBoutons(np, "IHM/Start/", "np"));

        q = createBouton("", q_i, "q");
        boutonInv(q);
        q.addMouseListener(new AdaptateurSourisBoutons(q, "IHM/Start/", "q"));

        rg = createBouton("", rg_i, "regle");
        boutonInv(rg);
        rg.addMouseListener(new AdaptateurSourisBoutons(rg, "IHM/Start/", "rg"));

        ch = createBouton("", ch_i, "charger");
        boutonInv(ch);


        // layout des boutons
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(106)
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addComponent(np)
                                        .addComponent(ch, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rg, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(q, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE))
                                .addGap(95))
                        .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                                .addGap(20)
                                .addComponent(titre, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                                .addGap(52)
                                .addComponent(titre, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                                .addComponent(np)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(ch, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(rg, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(q, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                                .addGap(32))
        );
        panel.setLayout(gl_panel);
    }

    public void selectionJoueur() {
        try {
            aide_i = new ImageIcon(getImage("IHM/Start/aide.png"));
            retour_i = new ImageIcon(getImage("IHM/Start/retour.png"));
            co_i = new ImageIcon(getImage("IHM/Start/co.png"));
            co_grey =  new ImageIcon(getImage("IHM/Start/co_grey.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        aide = createBouton("", aide_i, "aide");
        aide.addMouseListener(new AdaptateurSourisBoutons(aide, "IHM/Start/", "aide"));

        retour = createBouton("", retour_i, "principal");
        retour.addMouseListener(new AdaptateurSourisBoutons(retour, "IHM/Start/", "retour"));

        commencer = createBouton("", co_grey, "debut");
        coListener =  new AdaptateurSourisBoutons(commencer, "IHM/Start/", "co");

        boutonInv(aide);
        boutonInv(retour);
        boutonInv(commencer);

        j1 = createJoueur(1);
        j2 = createJoueur(2);

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                                                .addComponent(retour, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, 256, Short.MAX_VALUE)
                                                .addComponent(aide)
                                                .addContainerGap())
                                        .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                                                .addComponent(commencer)
                                                .addGap(94))
                                        .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(j1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(j2, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                                .addGap(138))))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(48)
                                .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(aide)
                                        .addComponent(retour))
                                .addGap(161)
                                .addComponent(j1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                                .addComponent(j2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(101)
                                .addComponent(commencer)
                                .addGap(20))
        );
        panel.setLayout(gl_panel);
    }

    public void clearFrame() {
        p.removeAll();
        panel.removeAll();
    }

    public void popupRegle() {
        regles = new JOptionPane();
        String texte, titre;
        titre = "Règles du jeu";
        texte = "Règles de pose \r\n\r\n"
                + "1.1. Chaque joueur pioche une tuile tour à tour. \r\n"
                + "1.2. Vous devez poser la tuile que vous piochez de façon à ce qu’elle continue le paysage et l’illustration.\r\n"
                + "1.3. Vous pouvez poser un pion sur la tuile que vous venez de placer.\r\n"
                + "1.4. Vous ne pouvez pas poser un pion dans une zone où il y a déjà au moins un pion.\r\n\r\n"
                + "Règles de complétion \r\n\r\n"
                + "2.1. Une route est complétée dès lors que ses deux extrémités mènent à un village, une ville, une abbaye ou qu'elles bouclent. Chaque tuile d’une route complétée rapporte 1 point.\r\n"
                + "2.2. Une ville est complétée dès lors qu'elle est entourée de remparts et qu'il n'y a pas de tuile vide en son sein. Chaque tuile d’une ville complétée rapporte 2 points et chaque blason 2 points de plus.\r\n"
                + "2.3. Une abbaye est complétée dès lors qu'elle est complètement entourée de 8 tuiles. Chacune des tuiles de l’abbaye (les 8 l’entourant et l’abbaye elle-même) rapporte 1 point.\r\n\r\n"
                + "Règles d'évaluation \r\n\r\n"
                + "3.1. Une évaluation a toujours lieu à la fin du tour d’un joueur. À ce moment, tous les joueurs possédant un pion dans une zone complétée marquent des points.\r\n"
                + "3.2. Après chaque évaluation, reprenez vos pions avec lesquels vous avez marqué des points.\r\n"
                + "3.3. Si plusieurs joueurs sont impliqués dans l’évaluation d’une même zone complétée, c’est le joueur qui possède le plus de pions dans cette zone (route ou ville) qui marque la totalité des points.\r\n"
                + "3.4. Dans un cas où plusieurs joueurs possèdent le même nombre de pions dans une telle zone, chacun de ces joueurs marque la totalité des points.";
        JOptionPane.showMessageDialog(f, texte, titre, JOptionPane.DEFAULT_OPTION);
    }

    public void popupFin() {
        fin = new JOptionPane();
        String texte, titre;

        titre = "Partie terminée !";
        texte = plateau.getVainqueur() + " \r\n\r\n" +
                "Score Joueur 1 : " + plateau.getNbScore(1) + " \r\n\r\n"
                + "Score Joueur 2 : " + plateau.getNbScore(2) + " \r\n\r\n";
        UIManager.put("OptionPane.okButtonText", "Menu principal");
        int result = JOptionPane.showConfirmDialog(null, texte, titre, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            controle.commande("principal");
            UIManager.put("OptionPane.okButtonText", "OK");
        }
    }

    public void popupCommandesJeu() {
        commandesJeu = new JOptionPane();
        String texte, titre;
        titre = "Commandes du jeu";
        texte = "Touche z : Permet de zoomer la vue sur le plateau tout en restant centré sur la dernière tuile posée.\r\n"
                + "Touche d : Permet de dézoomer la vue sur le plateau tout en restant centré sur la dernière tuile posée.\r\n"
                + "Touche c : Permet de recentrer la vue sur le plateau sur la dernière tuile posée.\r\n"
                + "Touche Échap : Affichage du menu contextuel.\r\n"
                + "Touche f : Permet d'activer/désactiver le mode plein écran.\r\n\r\n"
                + "Molette Souris : Permet de se déplacer sur le plateau verticalement.\r\n"
                + "Molette Souris + Touche Shift : Permet de se déplacer sur le plateau horizontalement.\r\n";
        JOptionPane.showMessageDialog(f, texte, titre, JOptionPane.DEFAULT_OPTION);

    }

    public void lancerJeu(boolean IAJ1, boolean IAJ2) throws CloneNotSupportedException {
        f.setVisible(false);
        partie = new JFrame("Carcassonne");
        partie.setIconImage(icon);

        partie.setMaximumSize(DimMax);
        partie.setExtendedState(JFrame.MAXIMIZED_BOTH);
        partie.setLocationRelativeTo(null);

        partie.setResizable(true);
        partie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TuilesDefinition definition = new TuilesDefinition();
        plateau = new Plateau(definition);
        jeu = new Jeu(plateau);

        pg = new PlateauGraphiqueSwing(jeu);

        controle.fixerJeu(jeu);

        pg.setPreferredSize(new Dimension(jeu.getZoomFactor() * jeu.getPlateau().getLignes(), jeu.getZoomFactor() * jeu.getPlateau().getLignes()));

        jeu.ajouterObservateur(this);
        scrollPane = new JScrollPane(pg);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        verticalScrollBar = scrollPane.getVerticalScrollBar();
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        partie.add(scrollPane, BorderLayout.CENTER);

        if (!IAJ1) {
            pg.addMouseListener(new AdaptateurSouris(pg, controle));
        }
        partie.addKeyListener(new AdaptateurClavier(controle));

        j1IA = IAJ1;
        j2IA = IAJ2;

        interfaceSetup(jeu);
        affichageBoutons();

        partie.setVisible(true);
        partie.setMinimumSize(new Dimension(500, 500));
        maximized = true;
        partie.dispose();
        partie.setUndecorated(true);
        partie.setVisible(true);
    }

    public void interfaceSetup(Jeu j) {
        try {
            jCourant = new ImageIcon(getImage("IHM/Jeu/play.png"));
            rotateI = new ImageIcon(getImage("IHM/Jeu/rotate.png"));
            validerI = new ImageIcon(getImage("IHM/Jeu/valider.png"));
            annulerCoupI = new ImageIcon(getImage("IHM/Jeu/annulerCoup.png"));
            menuPrincipalI = new ImageIcon(getImage("IHM/Jeu/menuPrincipal.png"));
            reprendreI = new ImageIcon(getImage("IHM/Jeu/reprendre.png"));
            jRed = new ImageIcon(getImage("IHM/Jeu/jRed.png"));
            jBlue = new ImageIcon(getImage("IHM/Jeu/jBlue.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        tuilesRestantes = createLabel("Tuiles restantes : " + plateau.pioche.size(), null);
        tuileCourante = createLabel("Tuile à jouer", null);
        tuilesRestantes.setFont(tuilesRestantes.getFont().deriveFont(16.0f));
        tuileCourante.setFont(tuileCourante.getFont().deriveFont(16.0f));

        playing = createLabel("Tour actuel : " + jeu.getTour(), null);
        playing.setFont(playing.getFont().deriveFont(16.0f));

        joueur1 = createLabel("Joueur 1", null);
        joueur2 = createLabel("Joueur 2", null);
        joueur1.setFont(joueur1.getFont().deriveFont(16.0f));
        joueur2.setFont(joueur2.getFont().deriveFont(16.0f));

        j1Scores = createLabel("", jRed);
        j2Scores = createLabel("", jBlue);
        j1Scores.setText(scores);
        j2Scores.setText(scores);
        j1Scores.setFont(j1Scores.getFont().deriveFont(16.0f));
        j2Scores.setFont(j2Scores.getFont().deriveFont(16.0f));

        imgTuile = createLabel("", null);
        tuilePioche = new ImageIcon(jeu.getPlateau().tuilePioche.getImg());
        tuilePioche.setImage(tuilePioche.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        imgTuile.setIcon(tuilePioche);


        rotate = createBouton("", rotateI, "rotateTuile");
        rotate.addMouseListener(new AdaptateurSourisBoutons(rotate, "IHM/Jeu/", "rotate"));

        valider = createBouton("", validerI, "validerCoup");
        valider.addMouseListener(new AdaptateurSourisBoutons(valider, "IHM/Jeu/", "valider"));

        annulerPose = createBouton("", annulerCoupI, "annulerPose");
        annulerPose.addMouseListener(new AdaptateurSourisBoutons(annulerPose, "IHM/Jeu/", "annulerCoup"));

        menuPrincipal = createBouton("", menuPrincipalI, "principal");
        menuPrincipal.addMouseListener(new AdaptateurSourisBoutons(menuPrincipal, "IHM/Jeu/", "menuPrincipal"));

        reprendre = createBouton("", reprendreI, "reprendre");
        reprendre.addMouseListener(new AdaptateurSourisBoutons(reprendre, "IHM/Jeu/", "reprendre"));

        boutonInv(rotate);
        boutonInv(valider);
        boutonInv(annulerPose);
        boutonInv(menuPrincipal);
        boutonInv(reprendre);

        j1Courant = createLabel("", jCourant);
        j2Courant = createLabel("", jCourant);

        Box barreBoutons = Box.createVerticalBox();
        partie.add(barreBoutons, BorderLayout.EAST);

        barreBoutons.add(joueur1);
        panScores1 = new JPanel();
        panScores1.add(j1Courant);
        panScores1.add(j1Scores);
        barreBoutons.add(panScores1);

        barreBoutons.add(joueur2);
        panScores2 = new JPanel();
        panScores2.add(j2Courant);
        panScores2.add(j2Scores);
        barreBoutons.add(panScores2);

        barreBoutons.add(tuileCourante);
        barreBoutons.add(imgTuile);

        panRestants = new JPanel();
        panRestants.add(playing);
        panRestants.add(tuilesRestantes);
        barreBoutons.add(panRestants);

        panBoutons = new JPanel();
        if (j1IA) {
            if (j2IA) {
                lancerDoubleIA = new ImageIcon(getImage("IHM/Jeu/LancerIA.png"));

                lancerJeu = createBouton(null, lancerDoubleIA, "doubleIA");
                lancerJeu.addMouseListener(new AdaptateurSourisBoutons(lancerJeu, "IHM/Jeu/", "LancerIA"));
                boutonInv(lancerJeu);

            } else {
                lancerSimpleIA = new ImageIcon(getImage("IHM/Jeu/PremierCoupIA.png"));
                lancerJeu = createBouton(null, lancerSimpleIA, "lancerIA");
                lancerJeu.addMouseListener(new AdaptateurSourisBoutons(lancerJeu, "IHM/Jeu/", "PremierCoupIA"));
                boutonInv(lancerJeu);

                panRotate = new JPanel();
                panRotate.add(rotate);
                panBoutons.add(valider);
                panBoutons.add(annulerPose);
                barreBoutons.add(panRotate);
            }
            panBoutons.add(lancerJeu);
        } else {
            panRotate = new JPanel();
            panRotate.add(rotate);
            panBoutons.add(valider);
            panBoutons.add(annulerPose);
            barreBoutons.add(panRotate);
        }
        barreBoutons.add(panBoutons);

        barreBoutons.setBackground(Color.LIGHT_GRAY);

        affichageBoutons();

        JMenuBar menu = createMenu();
        partie.setJMenuBar(menu);
    }

    private JButton createBouton(String nomBouton, ImageIcon icon, String c) {
        JButton bouton = new JButton(nomBouton);
        bouton.addActionListener(new AdaptateurBoutons(controle, c));
        bouton.setIcon(icon);
        bouton.setFocusable(false);
        return bouton;
    }

    private JComboBox<String> createJoueur(int numeroJoueur) {
        String[] joueurs = {"Sélectionnez un joueur", "Joueur", "IA facile"};
        joueurs[0] = "Sélectionnez un joueur " + Integer.toString(numeroJoueur);
        joueurs[1] = "Joueur" + " " + Integer.toString(numeroJoueur);
        joueurs[2] = "J" + Integer.toString(numeroJoueur) + " IA facile";
        JComboBox<String> c = new JComboBox<String>(joueurs);
        c.addActionListener(new EcouteurComboBox(c, controle));
        return c;
    }

    private JLabel createLabel(String s, ImageIcon icon) {
        JLabel lab = new JLabel(s);
        lab.setAlignmentX(Component.CENTER_ALIGNMENT);
        lab.setIcon(icon);
        return lab;
    }

    @Override
    public void metAJour() {
        affichageBoutons();
        tuilePioche = new ImageIcon(jeu.getPlateau().tuilePioche.getImg());
        tuilePioche.setImage(tuilePioche.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        imgTuile.setIcon(tuilePioche);
        tuilesRestantes.setText("Tuiles restantes : " + plateau.pioche.size());
        playing.setText("Tour actuel : " + jeu.getTour());
        j1Scores.setText("<html> Pions restants : " + plateau.getNbPions(1) + "<br/> Score : " + plateau.getNbScore(1) + " </html>");

        j2Scores.setText("<html> Pions restants : " + plateau.getNbPions(2) + "<br/> Score : " + plateau.getNbScore(2) + " </html>");

        pg.setPreferredSize(new Dimension(jeu.getZoomFactor() * jeu.getPlateau().getLignes(), jeu.getZoomFactor() * jeu.getPlateau().getLignes()));
        int index = jeu.getPlateau().getTuilesPoses().size() - 1;
        verticalScrollBar.setValue((jeu.getZoomFactor() * jeu.getPlateau().getTuilesPoses().get(index).getI()) - ((scrollPane.getViewport().getSize().height / jeu.getZoomFactor()) / 2) * jeu.getZoomFactor());
        horizontalScrollBar.setValue((jeu.getZoomFactor() * jeu.getPlateau().getTuilesPoses().get(index).getJ()) - ((scrollPane.getViewport().getSize().width / jeu.getZoomFactor()) / 2) * jeu.getZoomFactor());
        pg.revalidate();
        pg.repaint();
    }

    //TODO a enlever
    public void pose(int ligne, int colonne, Tuiles t) {
        pg.pose(ligne, colonne, t);
    }

    private void boutonInv(JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
    }

    private JMenuBar createMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu menuPr = new JMenu("Menu");

        JMenuItem regle = new JMenuItem("Règles du jeu");
        regle.addActionListener(new AdaptateurBoutons(controle, "regle"));
        JMenuItem pri = new JMenuItem("Menu principal");
        pri.addActionListener(new AdaptateurBoutons(controle, "principal"));
        JMenuItem quitter = new JMenuItem("Quitter");
        quitter.addActionListener(new AdaptateurBoutons(controle, "q"));
        menuPr.add(pri);
        menuPr.add(regle);
        menuPr.add(quitter);

        JMenu aide = new JMenu("Aide");

        JMenuItem commandes = new JMenuItem("Commandes du jeu");
        commandes.addActionListener(new AdaptateurBoutons(controle, "commandesJeu"));
        aide.add(commandes);

        menu.add(menuPr);
        menu.add(aide);
        return menu;
    }

    public void toggleFullscreen() {
        if (maximized) {
            maximized = false;
            partie.dispose();
            partie.setUndecorated(false);
            partie.setVisible(true);
        } else {
            maximized = true;
            partie.dispose();
            partie.setUndecorated(true);
            partie.setVisible(true);
        }
    }

    private void affichageBoutons() {
        switch (jeu.getJoueur()) {
            case 1:
                j2Courant.setVisible(false);
                j1Courant.setVisible(true);
                break;
            case 2:
                if (j1IA) lancerJeu.setVisible(false);
                j1Courant.setVisible(false);
                j2Courant.setVisible(true);
                break;
        }
        if (j1IA && j2IA) {
            rotate.setVisible(false);
            valider.setVisible(false);
            annulerPose.setVisible(false);
        } else {
            if (!jeu.tuilePosee) {
                rotate.setVisible(false);
                valider.setVisible(false);
                annulerPose.setVisible(false);
            } else if (!jeu.pionPose) {
                System.out.println(jeu.getPlateau().getPosesPossibles().size());
                rotate.setVisible(true);
                valider.setVisible(true);
                annulerPose.setVisible(true);
            } else if (jeu.tuilePosee && jeu.pionPose) {
                rotate.setVisible(false);
                valider.setVisible(true);
                annulerPose.setVisible(true);
            }
        }
        if (jeu.isJeuTermine()) {
            rotate.setVisible(false);
            valider.setVisible(false);
            annulerPose.setVisible(false);
        }
        if (jeu.getPlateau().pasDeRotationLastTuile) {
            rotate.setVisible(false);
        }
    }

    public void menuJeu() {
        menuBoutons = new JFrame("Menu");

        menuBoutons.setIconImage(icon);
        menuBoutons.setSize(350, 420);

        Box boxBoutons = Box.createVerticalBox();
        menuBoutons.add(boxBoutons, BorderLayout.CENTER);
        boxBoutons.add(reprendre);
        boxBoutons.add(menuPrincipal);
        boxBoutons.add(rg);
        boxBoutons.add(q);

        menuBoutons.pack();
        menuBoutons.setLocationRelativeTo(partie);

        menuBoutons.setVisible(true);
    }

    public void fermerJeu() {
        if (f != null) f.dispose();
        if (partie != null) partie.dispose();
        if (menuBoutons != null) menuBoutons.dispose();
        System.exit(0);
    }

    public void reprendreJeu() {
        menuBoutons.dispose();
    }

    private Image getImage(String filename) {
        try {
            return ImageIO.read(getClass().getResourceAsStream("/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void activerSouris() {
        pg.addMouseListener(new AdaptateurSouris(pg, controle));
    }
    
    public void activerCommencer(boolean active) {
    	if (active) {
	    	commencer.setIcon(co_i);
	        commencer.addMouseListener(coListener);
    	} 
    	else {
    		commencer.setIcon(co_grey);
    		commencer.removeMouseListener(coListener);
    	}
    }
    
    public void popupAide() {
    	aideModes = new JOptionPane();
        String texte, titre;
        titre = "Modes de jeu";
        texte = "Veuillez sélectionner un mode de jeu parmi les modes disponibles.\r\n"
                + "Pour chacun des deux joueurs, vous pouvez lui assigner un joueur humain, ou une IA aléatoire.\r\n"
                + "Pour démarrer la partie une fois la fenêtre de jeu ouverte: \r\n"
                + "- en joueur contre joueur, le joueur 1 clique sur une case et joue le premier coup.\r\n"
                + "- en joueur contre IA, si le joueur 1 est une IA, vous devez cliquer sur le bouton 'Jouer premier coup IA'. Sinon, jouez le premier coup et l'IA vous suivra.\r\n"
                + "- en IA contre IA, vous devez cliquer sur le bouton 'Lancer partie IAs'. Vous verrez le résultat de la partie apparaître instantanément.\r\n";
        JOptionPane.showMessageDialog(f, texte, titre, JOptionPane.DEFAULT_OPTION);
    }
}

