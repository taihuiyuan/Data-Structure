package pj2;

public class MaximumSubarray {
    public static void main(String args[] ) {
        //int[] arr = {100,113,110,85,105,102,86,63,81,101,94,106,101,79,94,90,97,86};
        //int[] arr2 = {0,13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7,-11};
        //int[] arr = {5,4,3,2,1};
        int[] arr = generateArray(40);
        for (int i=0;i < arr.length;i++){
            System.out.print(arr[i]+";");
        }
        System.out.println();
        int[] arr2 = new int[arr.length];
        arr2[0] = 0;
        for (int i=1;i < arr.length;i++){
            arr2[i] = arr[i]-arr[i-1];
        }

        //第一次调用时间比之后调用同一个函数多好多？
        long begin = System.nanoTime();
        Result result = brute_force(arr);
        System.out.println("brute_force maxProfit:"+result.maxProfit+", from day"+result.begin+" to day"+result.end);
        long finish = System.nanoTime();
        System.out.println("brute_force:"+(finish-begin)+"ns");
        //第二次调用时间也？
        long begins = System.nanoTime();
        Result results = dynamic(arr);
        System.out.println("dynamic maxProfit:"+results.maxProfit+", from day"+results.begin+" to day"+results.end);
        long finishs = System.nanoTime();
        System.out.println("recursive:"+(finishs-begins)+"ns");

        System.out.println(arr.length);

        long begin1 = System.nanoTime();
        Result result1 = brute_force(arr);
        System.out.println("brute_force maxProfit:"+result1.maxProfit+", from day"+result1.begin+" to day"+result1.end);
        long finish1 = System.nanoTime();
        System.out.println("brute_force:"+(finish1-begin1)+"ns");

        long begin2 = System.nanoTime();
        Result result2 = dynamic(arr);
        System.out.println("dynamic maxProfit:"+result2.maxProfit+", from day"+result2.begin+" to day"+result2.end);
        long finish2 = System.nanoTime();
        System.out.println("dynamic:"+(finish2-begin2)+"ns");


        long begin_combine = System.nanoTime();
        Result result_combine = combine(arr,20);
        System.out.println("combine maxProfit:"+result_combine.maxProfit+", from day"+result_combine.begin+" to day"+result_combine.end);
        long finish_combine = System.nanoTime();
        System.out.println("combine:"+(finish_combine-begin_combine)+"ns");

        /*
        long begin3 = System.nanoTime();
        Result result3 = brute_force_change(arr2);
        System.out.println("brute_force_change maxProfit:"+result3.maxProfit+", from day"+result3.begin+" to day"+result3.end);
        long finish3 = System.nanoTime();
        System.out.println("brute_force_changes:"+(finish3-begin3)+"ns");

        long begin4 = System.nanoTime();
        Result result4 = dynamic_change(arr2);
        System.out.println("dynamic_change maxProfit:"+result4.maxProfit+", from day"+result4.begin+" to day"+result4.end);
        long finish4 = System.nanoTime();
        System.out.println("dynamic_changes:"+(finish4-begin4)+"ns");*/
    }

    static class Result{
        int maxProfit=0;
        int begin=0;
        int end=0;
        Result(int maxProfit,int begin,int end){
            this.maxProfit = maxProfit;
            this.begin = begin;
            this.end = end;
        }
    }

    static Result brute_force(int[] prices){//暴力破解
        int n=prices.length;
        int begin=0,end=0;
        int maxProfit=Integer.MIN_VALUE;
        for (int i=0;i<n-1;i++){
            for (int j=i+1;j<n;j++){
                int profit = prices[j]-prices[i];
                if (profit > maxProfit){
                    begin=i;//记录买入是第几天
                    end=j;//记录卖出
                    maxProfit = profit;
                }
            }
        }
        Result result = new Result(maxProfit,begin,end);
        return result;
    }

    static Result dynamic(int[] prices){//动态规划
        int n=prices.length;
        int minPrice = prices[0];//记录今天之前买入的最小值
        int maxProfit = Integer.MIN_VALUE;
        int begin=0,end=0,small=0;
        for(int i = 1;i < n;i++){
            int profit = prices[i]-minPrice;
            if (profit > maxProfit){//比较每天最大获利
                begin = small;
                end = i;//记录卖出去是第几天
                maxProfit = profit;
            }
            if (prices[i] < minPrice){//比较今天之前买入的最小值
                small = i;
                minPrice = prices[i];
            }
        }
        Result result = new Result(maxProfit,begin,end);
        return result;
    }

    static Result brute_force_change(int[] changes){
        int n = changes.length;
        int maxProfit=0;
        int begin=0,end=0;
        for (int i=0;i<n-1;i++){
            int sum=0;
            for (int j=i+1;j<n;j++){
                sum = sum+changes[j];
                if (sum > maxProfit){
                    begin=i;//记录买入是第几天
                    end=j;//记录卖出
                    maxProfit = sum;
                }
            }
        }
        return new Result(maxProfit,begin,end);
    }

    static Result dynamic_change(int[] changes){
        int maxProfit=0;
        int sum=0;
        int begin=0,end=0,current=0;
        for (int i=0;i < changes.length;i++){
            if (sum > 0){
                sum = sum + changes[i];
            }else{
                sum = changes[i];
                current = i-1;
            }
            if (sum > maxProfit) {
                begin = current;
                end = i;
                maxProfit = sum;
            }
        }
        Result result = new Result(maxProfit,begin,end);
        return result;
    }

    static Result combine(int[] prices,int k){
        int n=prices.length;
        if (n < k){
            Result result = brute_force(prices);
            return result;
        }else {

            int minPrice = prices[0];//记录今天之前买入的最小值
            int maxProfit = Integer.MIN_VALUE;
            int begin = 0, end = 0, small = 0;

            //前k个元素使用暴力破解
            for (int i=0;i<k-1;i++){
                for (int j=i+1;j<k;j++){
                    int profit = prices[j]-prices[i];
                    if (profit > maxProfit){
                        begin=i;//记录买入是第几天
                        end=j;//记录卖出
                        maxProfit = profit;
                    }
                }
                if (prices[i] < minPrice){//比较今天之前买入的最小值
                    small = i;
                    minPrice = prices[i];
                }
            }

            //剩余用动态规划
            for (int i=k;i<n;i++){
                int profit = prices[i]-minPrice;
                if (profit > maxProfit){//比较每天最大获利
                    begin = small;
                    end = i;//记录卖出去是第几天
                    maxProfit = profit;
                }
                if (prices[i] < minPrice){//比较今天之前买入的最小值
                    small = i;
                    minPrice = prices[i];
                }
            }

            Result result = new Result(maxProfit, begin, end);
            return result;
        }
    }

    public static int[] generateArray(int length){
        int[] arr = new int[length];
        for(int i=0;i<arr.length;i++){
            arr[i]=(int)(Math.random()*100);
        }
        return arr;
    }
}
