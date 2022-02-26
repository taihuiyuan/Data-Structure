package lab2;

public class InsertionSort {

    public void Insertion(int[] nums) {
        for (int i=1;i < nums.length;i++){
            for (int j=i;j > 0;j--){
                if (nums[j] <nums[j-1]){
                    int key = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = key;
                }else break;
            }
        }
    }

    public void Insertion(double[] nums) {
        for (int i=1;i < nums.length;i++){
            for (int j=i;j > 0;j--){
                if (nums[j] < nums[j-1]){
                    double key = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = key;
                }else break;
            }
        }
    }

    public void Insertion(String[] nums) {
        for (int i=1;i < nums.length;i++){
            for (int j=i;j > 0;j--){
                if (Test.compareStr(nums[j-1],nums[j])){
                    String key = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = key;
                }else break;
            }
        }
    }
}
