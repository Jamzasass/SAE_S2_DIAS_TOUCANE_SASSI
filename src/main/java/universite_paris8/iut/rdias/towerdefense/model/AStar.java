package universite_paris8.iut.rdias.towerdefense.model;

import java.util.*;

public class AStar {

    private Ground ground;

    public AStar(Ground aGround) {
        this.ground = aGround;
    }

    public List<int[]> trouverChemin(int ligneDep, int colDep, int ligneArr, int colArr) {
        int h = ground.heigth();
        int w = ground.width();

        // Sortie rapide si départ ou arrivée hors-zone / non traversable
        if (!dansGrille(ligneDep, colDep) || !dansGrille(ligneArr, colArr)) return null;
        if (!estTraversable(ligneDep, colDep) || !estTraversable(ligneArr, colArr)) return null;

        PriorityQueue<Node> ouvert = new PriorityQueue<>();
        boolean[][] ferme = new boolean[h][w];
        int[][] meilleurG = new int[h][w];
        for (int[] row : meilleurG) Arrays.fill(row, Integer.MAX_VALUE);

        Node depart = new Node(ligneDep, colDep, 0,
                heuristique(ligneDep, colDep, ligneArr, colArr), null);
        ouvert.add(depart);
        meilleurG[ligneDep][colDep] = 0;

        // Voisins 4-connexes : haut, bas, gauche, droite
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

                int nouveauG = courant.g + 1;
                if (nouveauG >= meilleurG[nl][nc]) continue;
                meilleurG[nl][nc] = nouveauG;

                int hh = heuristique(nl, nc, ligneArr, colArr);
                ouvert.add(new Node(nl, nc, nouveauG, hh, courant));
            }
        }
        return null; // pas de chemin trouvé
    }

    // Distance de Manhattan : adaptée à un déplacement 4-connexe à coût uniforme.
    // Toujours admissible donc A* retourne le chemin optimal.
    private int heuristique(int l1, int c1, int l2, int c2) {
        return Math.abs(l1 - l2) + Math.abs(c1 - c2);
    }

    private boolean estTraversable(int ligne, int col) {
        int id = ground.idTuile(ligne, col);
        return id == 1 || id == 2; // chemin ou château
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

    // Classe interne : une case explorée par l'algorithme
    private static class Node implements Comparable<Node> {
        int ligne, col;
        int g;       // coût depuis le départ
        int h;       // heuristique vers l'arrivée
        Node parent; // pour reconstruire le chemin

        Node(int ligne, int col, int g, int h, Node parent) {
            this.ligne = ligne;
            this.col = col;
            this.g = g;
            this.h = h;
            this.parent = parent;
        }

        int f() { return g + h; }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f(), other.f());
        }
    }
}