package pj2;

import org.junit.Test;

import java.util.Scanner;

public class IntervalGraphColor {
    private static class Activity{
        int number = 0;//编号
        int begin;//活动开始时间
        int end;//活动结束时间
        boolean flag;//是否已选
        int roomNumber;//房间号

        Activity(int begin,int end,int number,boolean flag,int roomNumber){
            this.begin = begin;
            this.end = end;
            this.number = number;
            this.flag  = flag;
            this.roomNumber = roomNumber;
        }
    }

    //按照结束时间对活动进行排序
    @Test
    public void quickSort(Activity[] activity,int p,int r){
        if (p < r){
            int i=p-1;
            int j=p;

            Activity a = activity[r];
            while (j < r){
                if (activity[j].end <= a.end){
                    i++;
                    Activity temp = activity[i];
                    activity[i] = activity[j];
                    activity[j] = temp;
                }
                j++;
            }

            //把最后一个元素提到该在的位置
            Activity temp = activity[r];
            activity[r] = activity[i+1];
            activity[i+1] = temp;

            quickSort(activity,p,i);
            quickSort(activity,i+1,r);
        }
    }

    private int selectRoom(Activity[] activity,int[] time,int n){
        int sumRoom = 1;//已经占用的room
        int sumActivity = 1;//已经选择的活动
        time[1] = activity[0].end;//第一间room最后一个活动的结束时间为活动1的结束时间
        activity[0].roomNumber = 1;//第一个活动占用room1

        for (int i=1;i < n;i++){
            for (int j=1;j <= sumRoom;j++){//遍历room，放入活动
                if (activity[i].begin >= time[j] && (!activity[i].flag)){
                    activity[i].roomNumber = j;
                    activity[i].flag = true;
                    time[j] = activity[i].end;
                    sumActivity++;
                }
            }

            if (sumActivity < n && i == n-1){//这间room满了，放在下一间
                i = 0;
                sumRoom++;
            }
        }

        return sumRoom;
    }

    public static void main(String[] args){
        IntervalGraphColor graphColor = new IntervalGraphColor();
        int n=11;//活动个数
        Activity[] activity = new Activity[n];//记录活动
        int[] time = new int[n+1];//记录每间room末尾活动的结束时间
        for (int i = 0;i < n;i++){
            time[i+1] = 0;
            activity[i] = new Activity(0,0,i+1,false,0);
            /**
             System.out.println("This is activity "+(i+1)+", please enter the begin time:");
            Scanner input = new Scanner(System.in);
            int begin = input.nextInt();
            activity[i].begin = begin;
            System.out.println("This is activity "+(i+1)+", please enter the end time:");
            Scanner input2 = new Scanner(System.in);
            int end = input2.nextInt();
            activity[i].end = end;*/
        }
        activity[0].begin = 1;
        activity[0].end = 4;
        activity[1].begin = 3;
        activity[1].end = 5;
        activity[2].begin = 0;
        activity[2].end = 6;
        activity[3].begin = 5;
        activity[3].end = 7;
        activity[4].begin = 3;
        activity[4].end = 8;
        activity[5].begin = 5;
        activity[5].end = 9;
        activity[6].begin = 6;
        activity[6].end = 10;
        activity[7].begin = 8;
        activity[7].end = 11;
        activity[8].begin = 8;
        activity[8].end = 12;
        activity[9].begin = 2;
        activity[9].end = 13;
        activity[10].begin = 12;
        activity[10].end = 14;
        graphColor.quickSort(activity,0,n-1);
        int sum = graphColor.selectRoom(activity,time,n);
        System.out.println("共需要"+sum+"间room");

        for (int i=0;i<n;i++){
            System.out.println("活动"+activity[i].number+"在room"+activity[i].roomNumber);
        }
    }
}
