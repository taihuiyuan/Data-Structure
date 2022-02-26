package pj3;

import java.util.Arrays;

public class Graph {
    int vertexnum;
    String[] vertexes;
    int[][] matrix;//邻接矩阵,记录两点间距离
    String[][] edgename;
    final int INF = 100000;

    public Graph(int vertexnum) {
        this.vertexnum = vertexnum;
        this.vertexes = new String[vertexnum];
        this.matrix = new int[vertexnum][vertexnum];
        this.edgename = new String[vertexnum][vertexnum];
        for (int i=0;i<vertexnum;i++){
            for (int j=0;j<vertexnum;j++){
                matrix[i][j] = INF;
                edgename[i][j]="无名路";
            }
            matrix[i][i] = 0;
        }
    }

    private int num=0;
    public void insertVertex(String vertex){
        vertexes[num] = vertex;
        num++;
    }

    public void insertEdge(int a,int b,String name,int value){
        edgename[a][b] = name;
        edgename[b][a] = name;
        matrix[a][b] = value;
        matrix[b][a] = value;
    }

    public String edgeName(int a,int b){
        return edgename[a][b];
    }

    public void showGraph(){
        for (int i=0;i<vertexnum;i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
