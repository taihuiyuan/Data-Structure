package lab2;

public class MergeSort {
//int
    public void mergeSort(int[] nums, int left, int right) {
        if(left>=right)
            return;

        int mid = (left + right) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        Merge(nums, left, mid, right);
    }

    public void Merge(int[] nums,int start,int mid,int end) {
        int[] temp  = new int[end-start+1];

        int i = start;
        int j = mid+1;
        int k = 0;
        while (i <= mid && j <= end){
            if (nums[i]<=nums[j]){
                temp[k] = nums[i];
                i++;
            }else {
                temp[k] = nums[j];
                j++;
            }
            k++;
        }

        // 把左边剩余的数移入数组
        if (i <= mid){
            for (;i <= mid;i++,k++){
                temp[k] = nums[i];
            }
        }
        // 把右边剩余的数移入数组
        if (j <= end){
            for (;j <= end;j++,k++){
                temp[k] = nums[j];
            }
        }

        int x = 0;
        while (start <= end){
            nums[start] = temp[x];
            x++;
            start++;
        }
    }

//double
public void mergeSort(double[] nums, int left, int right) {
    if(left>=right)
        return;

    int mid = (left + right) / 2;
    mergeSort(nums, left, mid);
    mergeSort(nums, mid + 1, right);
    Merge(nums, left, mid, right);
}

    public void Merge(double[] nums,int start,int mid,int end) {
        double[] temp  = new double[end-start+1];

        int i = start;
        int j = mid+1;
        int k = 0;
        while (i <= mid && j <= end){
            if (nums[i] < nums[j]){
                temp[k] = nums[i];
                i++;
            }else {
                temp[k] = nums[j];
                j++;
            }
            k++;
        }

        // 把左边剩余的数移入数组
        if (i <= mid){
            for (;i <= mid;i++,k++){
                temp[k] = nums[i];
            }
        }
        // 把右边剩余的数移入数组
        if (j <= end){
            for (;j <= end;j++,k++){
                temp[k] = nums[j];
            }
        }

        int x = 0;
        while (start <= end){
            nums[start] = temp[x];
            x++;
            start++;
        }
    }

//String
public void mergeSort(String[] nums, int left, int right) {
    if(left>=right)
        return;

    int mid = (left + right) / 2;
    mergeSort(nums, left, mid);
    mergeSort(nums, mid + 1, right);
    Merge(nums, left, mid, right);
}

    public void Merge(String[] nums,int start,int mid,int end) {
        String[] temp  = new String[end-start+1];

        int i = start;
        int j = mid+1;
        int k = 0;
        while (i <= mid && j <= end){
            if (Test.compareStr(nums[j],nums[i])){
                temp[k] = nums[i];
                i++;
            }else {
                temp[k] = nums[j];
                j++;
            }
            k++;
        }

        // 把左边剩余的数移入数组
        if (i <= mid){
            for (;i <= mid;i++,k++){
                temp[k] = nums[i];
            }
        }
        // 把右边剩余的数移入数组
        if (j <= end){
            for (;j <= end;j++,k++){
                temp[k] = nums[j];
            }
        }

        int x = 0;
        while (start <= end){
            nums[start] = temp[x];
            x++;
            start++;
        }
    }
}
