package universite_paris8.iut.rdias.towerdefense.model.algorithm;

import universite_paris8.iut.rdias.towerdefense.model.Ground;

import java.util.*;

/*
    A* avec coûts aléatoires optionnels. Si facteurAleatoire > 0,
    chaque appel de trouverChemin génère un bruit différent par tuile,
    ce qui donne des chemins variés d'un appel à l'autre.
    facteurAleatoire = 0   → déterministe (toujours le même chemin)
    facteurAleatoire = 0.5 → variations légères
    facteurAleatoire = 2.0 → variations importantes
 */
public class AStar {

    private Ground ground;
    private double facteurAleatoire;

    public AStar(Ground aGround) {
        this(aGround, 0.0);
    }

    public AStar(Ground aGround, double aFacteurAleatoire) {
        this.ground = aGround;
        this.facteurAleatoire = aFacteurAleatoire;
    }

    public List<int[]> trouverChemin(int ligneDep, int colDep, int ligneArr, int colArr) {
        int h = ground.heigth();
        int w = ground.width();

        if (!dansGrille(ligneDep, colDep) || !dansGrille(ligneArr, colArr)) return null;
        if (!estTraversable(ligneDep, colDep) || !estTraversable(ligneArr, colArr)) return null;

        // Bruit aléatoire par tuile, regénéré à chaque appel pour varier les chemins
        double[][] bruit = new double[h][w];
        if (facteurAleatoire > 0) {
            for (int i = 0; i < h; i++)
                for (int j = 0; j < w; j++)
                    bruit[i][j] = Math.random() * facteurAleatoire;
        }

        PriorityQueue<Node> ouvert = new PriorityQueue<>();
        boolean[][] ferme = new boolean[h][w];
        double[][] meilleurG = new double[h][w];
        for (double[] row : meilleurG) Arrays.fill(row, Double.MAX_VALUE);

        Node depart = new Node(ligneDep, colDep, 0.0,
                heuristique(ligneDep, colDep, ligneArr, colArr), null);
        ouvert.add(depart);
        meilleurG[ligneDep][colDep] = 0;

        int[] dl = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!ouvert.isEmpty()) {
            Node courant = ouvert.poll();

            if (courant.ligne == ligneArr && courant.col == colArr) {
                return reconstruire(courant);
            }

            if (ferme[courant.ligne][courant.col]) continue;
            ferme[courant.ligne][courant.col] = true;

            for (int i = 0; i < 4; i++) {
                int nl = courant.ligne + dl[i];
                int nc = courant.col + dc[i];

                if (!dansGrille(nl, nc)) continue;
                if (!estTraversable(nl, nc)) continue;
                if (ferme[nl][nc]) continue;

                double nouveauG = courant.g + 1.0 + bruit[nl][nc];
                if (nouveauG >= meilleurG[nl][nc]) continue;
                meilleurG[nl][nc] = nouveauG;

                int hh = heuristique(nl, nc, ligneArr, colArr);
                ouvert.add(new Node(nl, nc, nouveauG, hh, courant));
            }
        }
        return null;
    }

    private int heuristique(int l1, int c1, int l2, int c2) {
        return Math.abs(l1 - l2) + Math.abs(c1 - c2);
    }

    private boolean estTraversable(int ligne, int col) {
        int id = ground.idTuile(ligne, col);
        return id == 1 || id == 2;
    }

    private boolean dansGrille(int ligne, int col) {
        return ligne >= 0 && ligne < ground.heigth()
                && col >= 0 && col < ground.width();
    }

    private List<int[]> reconstruire(Node arrivee) {
        LinkedList<int[]> chemin = new LinkedList<>();
        for (Node n = arrivee; n != null; n = n.parent) {
            chemin.addFirst(new int[]{n.ligne, n.col});
        }
        return chemin;
    }

    private static class Node implements Comparable<Node> {
        int ligne, col;
        double g;
        int h;
        Node parent;

        Node(int ligne, int col, double g, int h, Node parent) {
            this.ligne = ligne;
            this.col = col;
            this.g = g;
            this.h = h;
            this.parent = parent;
        }

        double f() { return g + h; }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.f(), other.f());
        }
    }
}