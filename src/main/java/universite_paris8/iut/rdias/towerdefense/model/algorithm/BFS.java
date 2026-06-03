package universite_paris8.iut.rdias.towerdefense.model.algorithm;

import universite_paris8.iut.rdias.towerdefense.model.Ground;
import java.util.*;

public class BFS {
    private Ground ground;
    private int[][][][] distancesMap;

    public BFS(Ground ground) {
        this.ground = ground;
        this.distancesMap = new int[ground.heigth()][ground.width()][ground.heigth()][ground.width()];
        initialiseDistanceMap();
        createDistancesMap();
    }

    private boolean dansGrille(int line, int col) {
        return line >= 0 && line < ground.heigth()
                && col >= 0 && col < ground.width();
    }
    public void createDistancesMap() {
        for (int li = 0; li < ground.heigth(); li++) {
            for (int col = 0; col < ground.width(); col++) {
                if (ground.isPath(li, col) || ground.isCastle(li, col)) {
                    bfsFrom(li, col);
                }
            }
        }
    }
    private void initialiseDistanceMap() {
        for (int i=0; i<ground.heigth(); i++) {
            for (int j=0; j<ground.width(); j++) {
                for (int k=0; k<ground.heigth(); k++) {
                    for (int l=0; l<ground.width(); l++) {
                        distancesMap[i][j][k][l] = -1;
                    }
                }
            }
        }
    }

    private void bfsFrom(int lOrigin, int cOrigin) {
        Queue<int[]> file = new ArrayDeque<>();
        distancesMap[lOrigin][cOrigin][lOrigin][cOrigin] = 0;
        file.add(new int[]{lOrigin, cOrigin});

        int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
        while (!file.isEmpty()) {
            int[] c = file.poll();
            int l = c[0], co = c[1];
            int d = distancesMap[lOrigin][cOrigin][l][co];
            for (int[] dir : dirs) {
                int nl = l + dir[0], nc = co + dir[1];
                if (dansGrille(nl, nc)
                        && (ground.isPath(nl, nc) || ground.isCastle(nl, nc))
                        && distancesMap[lOrigin][cOrigin][nl][nc] == -1) {
                    distancesMap[lOrigin][cOrigin][nl][nc] = d + 1;
                    file.add(new int[]{nl, nc});
                }
            }
        }
    }

    public int[][][][] getDistancesMap() {
        return distancesMap;
    }
}
