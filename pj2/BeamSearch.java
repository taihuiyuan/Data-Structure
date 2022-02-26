package pj2;

import java.util.*;

public class BeamSearch {
    public static void main(String args[]){
        double[][] data = new double[][]{
                {0.1,0.2,0.4,0.3},
                {0.3,0.5,0.15,0.05},
                {0.25,0.2,0.3,0.15},
                {0.5,0.3,0.08,0.12},
                {0.1,0.4,0.3,0.2}
        };//data[i][j]表示字符串length为i+1时第j+1个字母
        String[] str = new String[]{"A","B","C","D"};
        beamSearch(str,data,3);
    }

    static class Hypothesis{
        String[] path;//可能的路径,长度为data.length
        String currentPath="";
        double currentProb=0;
        Hypothesis(String currentPath,double currentProb,int length){
            this.currentProb = currentProb;
            this.currentPath = currentPath;
            path = new String[length];
        }
        Hypothesis(double currentProb,int length){
            this.currentProb = currentProb;
            path = new String[length];
        }
    }

    static void beamSearch(String[] str,double[][] data,int beamSize){
        Hypothesis[] hypothesis_final = new Hypothesis[beamSize];
        Hypothesis[] hypothesis_current = new Hypothesis[beamSize];//当前轮次得到的概率最大的

        for (int i=0;i<data.length;i++){
            //找到当前轮次概率最大的前beamSize个
            int[] index = new int[data[i].length];
            double[] original_data = new double[data[i].length];
            for(int k=0;k<data[i].length;k++){
                index[k] = k;
                original_data[k] = data[i][k];
            }
            int[] index1=quickSort(original_data,index,0,str.length-1,beamSize);//返回前beamSize大的概率的索引
            for (int j=0;j<beamSize;j++){
                hypothesis_current[j] = new Hypothesis(str[index1[j]],data[i][index1[j]],data.length);
            }

            //每轮结束后计算总概率，留下概率最大的beamSize个
            if (i == 0){
                for (int j=0;j<beamSize;j++){
                    hypothesis_final[j] = new Hypothesis(hypothesis_current[j].currentPath,hypothesis_current[j].currentProb,data.length);
                    hypothesis_final[j].path[i] = hypothesis_current[j].currentPath;
                }
            }else {
                Hypothesis[] hypothesis = new Hypothesis[beamSize * beamSize];
                double[] sum = new double[beamSize * beamSize];
                int num = 0;
                for (int j = 0; j < beamSize; j++) {//遍历之前轮次得到的路径
                    for (int k = 0; k < beamSize; k++) {//遍历当前轮次得到的路径
                        hypothesis[num] = new Hypothesis(hypothesis_final[j].currentProb * hypothesis_current[k].currentProb,data.length);
                        sum[num] = hypothesis[num].currentProb;
                        for (int r=0;r<hypothesis_final[j].path.length;r++) {
                            hypothesis[num].path[r] = hypothesis_final[j].path[r];
                        }
                        hypothesis[num].path[i] = hypothesis_current[k].currentPath;
                        num++;
                    }
                }
                index = new int[sum.length];
                for (int k = 0; k < sum.length; k++) {
                    index[k] = k;
                }
                int[] index2 = quickSort(sum, index, 0, sum.length - 1, beamSize);
                for (int j = 0; j < beamSize; j++) {
                    hypothesis_final[j] = new Hypothesis(hypothesis[index2[j]].currentProb,data.length);
                    for (int r=0;r<hypothesis[index2[j]].path.length;r++) {
                        hypothesis_final[j].path[r] = hypothesis[index2[j]].path[r];
                    }
                }
            }
        }

        /*
        //计算beamSize个可能路径的总概率，选择最大的输出
        double maxprob=0;
        String[] path = new String[data.length];
        for (int i=0;i<beamSize;i++){
            double sum = hypothesis_final[i].currentProb;
            if (sum>maxprob){
                maxprob=sum;
                path = hypothesis_final[i].path;
            }
        }
        System.out.println("最有可能性的路径为"+Arrays.toString(path)+", 概率为"+maxprob);*/
        for (int i=0;i <beamSize;i++){
            System.out.println("最有可能性的路径为"+Arrays.toString(hypothesis_final[i].path)+", 概率为"+hypothesis_final[i].currentProb);
        }
    }

    //寻找数组中前k个最大的数
    static int[] quickSort(double[] prob,int[] index,int p,int r,int beamSize){
        if (p < r){
            int i=p-1;
            int j=p;

            double a = prob[r];
            while (j < r){
                if (prob[j] <= a){
                    i++;
                    double temp = prob[i];
                    prob[i] = prob[j];
                    prob[j] = temp;
                    int temp2 = index[i];
                    index[i] = index[j];
                    index[j] = temp2;
                }
                j++;
            }

            //把最后一个元素提到该在的位置
            double temp = prob[r];
            prob[r] = prob[i+1];
            prob[i+1] = temp;
            int temp2 = index[r];
            index[r] = index[i+1];
            index[i+1] = temp2;

            quickSort(prob,index,p,i,beamSize);
            quickSort(prob,index,i+1,r,beamSize);
        }
        int[] result = new int[beamSize];
        for (int k=0;k<beamSize;k++){
            result[k] = index[index.length-k-1];
        }
        return result;
    }
}
