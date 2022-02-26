package pj3;

import java.util.Arrays;

public class Floyd {
    static int[][] pathmatrix;//记录对应点的最小路径的前驱点
    static int[][] shortpath;//记录顶点间的最短路径权值

    //floyd算法
    public static void floyd(Graph graph){
        pathmatrix = new int[graph.vertexnum][graph.vertexnum];
        shortpath = new int[graph.vertexnum][graph.vertexnum];

        //初始化
        for (int i=0;i<graph.vertexnum;i++){
            for (int j=0;j<graph.vertexnum;j++){
                pathmatrix[i][j] = j;
                shortpath[i][j] = graph.matrix[i][j];
            }
        }

        for (int k=0;k<graph.vertexnum;k++) {//k为中转顶点
            for (int i = 0; i < graph.vertexnum; i++) {//起始顶点
                for (int j = 0; j < graph.vertexnum; j++) {//终止顶点
                    //若路径i-k-j比i-j短则更新两个矩阵
                    if (shortpath[i][j] > shortpath[i][k]+shortpath[k][j]){
                        pathmatrix[i][j] = pathmatrix[i][k];
                        shortpath[i][j] = shortpath[i][k]+shortpath[k][j];
                    }
                }
            }
        }
    }

    public static int[][] GetShortpath(){
        return shortpath;
    }

    public static int[][] GetPathmatrix(){
        return pathmatrix;
    }

    //获取两个顶点间的最短路径
    public static void getShortestPath(Graph graph,int a,int b){
        System.out.println("辅助矩阵：");
        for (int i=0;i<pathmatrix.length;i++){
            System.out.println(Arrays.toString(pathmatrix[i]));
        }

        System.out.println("最短路径权值矩阵：");
        for (int i=0;i<shortpath.length;i++){
            System.out.println(Arrays.toString(shortpath[i]));
        }

        if (shortpath[a][b] == graph.INF){
            System.out.println(graph.vertexes[a]+"和"+graph.vertexes[b]+"间不可达");
        }else {
            System.out.println(graph.vertexes[a] + "和" + graph.vertexes[b] + "间的最短路径距离为：" + shortpath[a][b]+"米");
            System.out.println("这两个顶点间的最短路径为：");
            System.out.println(graph.vertexes[a]);
            System.out.println("|");
            int k = a;//中转顶点
            System.out.println(graph.edgeName(k,pathmatrix[k][b])+"("+shortpath[k][b]+"m,"+((shortpath[k][b] / 1.5)/60)+"min)");
            System.out.println("|");
            while (pathmatrix[k][b] != b) {
                System.out.println(graph.vertexes[pathmatrix[k][b]]);
                System.out.println("|");
                System.out.println(graph.edgeName(k,pathmatrix[k][b])+"("+shortpath[k][b]+"m,"+((shortpath[k][b] / 1.5)/60)+"min)");
                System.out.println("|");
                k = pathmatrix[k][b];
            }
            System.out.println(graph.vertexes[b]);
            System.out.println("所需时间为："+((shortpath[a][b] / 1.5)/60)+"min");//按每秒1.5m算
        }
    }
}
