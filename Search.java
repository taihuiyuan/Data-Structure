import java.util.*;

public class Search {
    String[] alphabet;
    int beam;
    String[] result;
    Map<Double, Integer> map = new HashMap<>();
    Set<Double> set;
    Iterator<Double> it;
    public Search(int beam, String[] alphabet){
        this.alphabet = alphabet;
        this.beam = beam;
        this.result = new String[beam];
    }

    void beamSearch (int require){
        int j = 0;
        int len = alphabet.length;
        if(beam > len){
            System.out.println("Invalid");
            return;
        }
        int[] choice = new int[beam];
        double[] pro = new double[beam * len];
        for (int length = 0; length < require; length++ ){
            j = 0;
            if(length == 0){
                generateProbability(len);
                pro = new double[len];
            }else {
                generateProbability(len * beam);
                pro = new double[len * beam];
            }
            set = map.keySet();
            it = set.iterator();
            while (it.hasNext()){
                pro[j] = it.next();
                j++;
            }
            insertSort(pro);
            j = 0;
            while (j < beam){
                choice[j] = map.get(pro[pro.length - 1 - j]);
                j++;
            }
            if(length == 0){
                for(int i = 0; i < beam; i++){
                    result[i] = alphabet[choice[i] % len];
                }
            }
            else {
                for(int i = 0; i < beam; i++){
                    result[i] += alphabet[choice[i] % len];
                }
            }


        }
        for(int i = 0; i < beam; i++){
            System.out.println(result[i] + ":" + pro[pro.length - 1 - i]);
        }

        }


   void generateProbability(int len) {
        if(map != null)
            map.clear();
       for (int i = 0; i < len; i++) {
           map.put(Math.random(), i);
       }

   }
   public static void insertSort(double[] arr){
        if(arr.length != 1){
            double insert;
            int j;
            for(int i = 1; i < arr.length; i++){
                insert = arr[i];
                j = i - 1;//j就是insert接下来比的位置
                while (insert < arr[j] && j >= 0){
                    arr[j + 1] = arr[j];
                    arr[j] = insert;
                    if( j  != 0){
                        j--;
                    }
                }

            }
        }
   }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("alphabet: (seperated with .)");
        String s = scanner.nextLine();
        System.out.print("beam:");
        int beam = scanner.nextInt();
        String[] alphabet = s.split(" ");
        System.out.print("length:");
        int len = scanner.nextInt();
        Search search = new Search(beam, alphabet);
        search.beamSearch(len);
    }
}
