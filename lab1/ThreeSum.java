/**时间复杂度o(n^3)
 * 空间复杂度o(1)
 */
package lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ThreeSum {

    public static void main(String args[] ) {
        int[] nums =  {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null)
            return null;

        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        for (int i=0;i < nums.length;i++){
            //若nums[i]>0,则和一定大于0
            if (nums[i] > 0)
                break;
            //去重i
            if (i > 0 && nums[i] == nums[i-1])
                continue;

            int j = i + 1,k = nums.length - 1;
            while (j < k){
                if (nums[i] + nums[j] + nums[k] == 0){
                    res.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j++;
                    k--;

                    //去重j,k
                    while (j<k && nums[j]==nums[j-1])
                        j++;
                    while (j<k && nums[k]==nums[k+1])
                        k--;

                }else if (nums[i] + nums[j] + nums[k] > 0){
                    k--;
                }else {
                    j++;
                }
            }
        }
        return res;
    }
}