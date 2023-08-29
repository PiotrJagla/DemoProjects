package org.example;

import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        System.out.println("This is not world");
        Solution s = new Solution();
        s.garbageCollection(new String[]{"G","P","GP","GG"}, new int[]{2,4,3});
    }

}
class Solution {
    public int garbageCollection(String[] garbage, int[] travel) {

        int result = 0;
        int[] trucks = new int[travel.length];
        Arrays.fill(trucks, 0);
        int i = garbage.length - 1;
        boolean m = false;
        boolean p= false;
        boolean g = false;
        while(true) {
            if(i-1 < 0) {
                break;
            }
            if(garbage[i].contains("M") || m){
                trucks[i-1]++;
                m = true;
            }

            if(garbage[i].contains("G") || g){
                trucks[i-1]++;
                g = true;
            }

            if(garbage[i].contains("P") || p){
                trucks[i-1]++;
                p = true;
            }
            --i;
        }

        result += garbage[0].length();
        for(int n = 1 ; n < garbage.length ; ++n) {
            result += travel[n-1] * trucks[n-1];
            result += garbage[n].length();
        }

        return result;
    }
}