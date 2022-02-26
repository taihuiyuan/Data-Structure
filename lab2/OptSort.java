package lab2;

public class OptSort {
    public void optSort(int[] nums,int k){
        InsertionSort insert = new InsertionSort();
        if (nums.length <= k){
            insert.Insertion(nums);
            return;
        }else {
            int number = nums.length/k + 1;
            for (int i=1;i <= number;i++){
                int[] temp;
                if (i*k < nums.length){
                    //对每k个元素进行插入排序
                    temp = new int[k];
                    for (int j=0; j < k; j++) {
                        temp[j] = nums[(i-1)*k + j];
                    }
                    insert.Insertion(temp);
                    for (int j=0; j < k; j++) {
                        nums[(i-1)*k + j] = temp[j];
                    }
                }else {
                    //对多出来的元素进行插入排序
                    int left = nums.length - (i-1)*k;
                    temp = new int[left];
                    for (int j=0; j < left; j++) {
                        temp[j] = nums[(i-1)*k + j];
                    }
                    insert.Insertion(temp);
                    for (int j=0; j < left; j++) {
                        nums[(i-1)*k + j] = temp[j];
                    }
                }
            }
            combine(nums,0,nums.length-1,k);
        }
    }
    public void combine(int[] nums,int start,int end,int k){
        if ((end-start) >= k) {
            int number = (start+end)/k + 1;
            int mid = number/2 * k - 1;
            combine(nums, start, mid, k);
            combine(nums, mid + 1, end, k);
            MergeSort merge = new MergeSort();
            merge.Merge(nums,start,mid,end);
        }
    }

//double
public void optSort(double[] nums,int k){
    InsertionSort insert = new InsertionSort();
    if (nums.length <= k){
        insert.Insertion(nums);
        return;
    }else {
        int number = nums.length/k + 1;
        for (int i=1;i <= number;i++){
            double[] temp;
            if (i*k < nums.length){
                //对每k个元素进行插入排序
                temp = new double[k];
                for (int j=0; j < k; j++) {
                    temp[j] = nums[(i-1)*k + j];
                }
                insert.Insertion(temp);
                for (int j=0; j < k; j++) {
                    nums[(i-1)*k + j] = temp[j];
                }
            }else {
                //对多出来的元素进行插入排序
                int left = nums.length - (i-1)*k;
                temp = new double[left];
                for (int j=0; j < left; j++) {
                    temp[j] = nums[(i-1)*k + j];
                }
                insert.Insertion(temp);
                for (int j=0; j < left; j++) {
                    nums[(i-1)*k + j] = temp[j];
                }
            }
        }
        combine(nums,0,nums.length-1,k);
    }
}
    public void combine(double[] nums,int start,int end,int k){
        if ((end-start) >= k) {
            int number = (start+end)/k + 1;
            int mid = number/2 * k - 1;
            combine(nums, start, mid, k);
            combine(nums, mid + 1, end, k);
            MergeSort merge = new MergeSort();
            merge.Merge(nums,start,mid,end);
        }
    }

//String
public void optSort(String[] nums,int k){
    InsertionSort insert = new InsertionSort();
    if (nums.length <= k){
        insert.Insertion(nums);
        return;
    }else {
        int number = nums.length/k + 1;
        for (int i=1;i <= number;i++){
            String[] temp;
            if (i*k < nums.length){
                //对每k个元素进行插入排序
                temp = new String[k];
                for (int j=0; j < k; j++) {
                    temp[j] = nums[(i-1)*k + j];
                }
                insert.Insertion(temp);
                for (int j=0; j < k; j++) {
                    nums[(i-1)*k + j] = temp[j];
                }
            }else {
                //对多出来的元素进行插入排序
                int left = nums.length - (i-1)*k;
                temp = new String[left];
                for (int j=0; j < left; j++) {
                    temp[j] = nums[(i-1)*k + j];
                }
                insert.Insertion(temp);
                for (int j=0; j < left; j++) {
                    nums[(i-1)*k + j] = temp[j];
                }
            }
        }
        combine(nums,0,nums.length-1,k);
    }
}
    public void combine(String[] nums,int start,int end,int k){
        if ((end-start) >= k) {
            int number = (start+end)/k + 1;
            int mid = number/2 * k - 1;
            combine(nums, start, mid, k);
            combine(nums, mid + 1, end, k);
            MergeSort merge = new MergeSort();
            merge.Merge(nums,start,mid,end);
        }
    }
}
