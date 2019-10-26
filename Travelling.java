import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int[] connectedCities(int n, int g, int[] originCities, int[] destinationCities) {
        // Complete this function
        
        int[] root = new int[n+1];
        int[] ids = new int[n+1];
        
        for(int i=0; i<=n; i++){
            root[i] = i;
            ids[i]  = 1;
        }
        
        for(int i=g+1; i<=n; i++) {
            for(int j=2*i; j<=n; j+=i){
                unionFind(i, j, root, ids);
            }
        }
        
        int[] res = new int[originCities.length];
        for(int k=0; k<originCities.length; k++){
            res[k] = (getRoot(originCities[k],root) == getRoot(destinationCities[k], root)) ? 1 : 0;
        }
        return res;
    }
    
    private static void unionFind(int a, int b, int[] root, int[] ids) {
        int aRoot = getRoot(a, root);
        int bRoot = getRoot(b, root);
        
        if(aRoot == bRoot)
            return ;
        if(ids[aRoot] > ids[bRoot]){
            ids[aRoot] += ids[bRoot];
            root[bRoot] = root[aRoot];
        }
        else{
            ids[bRoot] += ids[aRoot];
            root[aRoot] = root[bRoot];
        }
            
    }
    
    private static int getRoot(int val, int[] root) {
        if(root[val] == val)
            return val;
        return getRoot(root[val], root);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int g = in.nextInt();
        int originCities_cnt = in.nextInt();
        int[] originCities = new int[originCities_cnt];
        for(int originCities_i = 0; originCities_i < originCities_cnt; originCities_i++){
            originCities[originCities_i] = in.nextInt();
        }
        int destinationCities_cnt = in.nextInt();
        int[] destinationCities = new int[destinationCities_cnt];
        for(int destinationCities_i = 0; destinationCities_i < destinationCities_cnt; destinationCities_i++){
            destinationCities[destinationCities_i] = in.nextInt();
        }
        int[] res = connectedCities(n, g, originCities, destinationCities);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + (i != res.length - 1 ? "\n" : ""));
        }
        System.out.println("");


        in.close();
    }
}