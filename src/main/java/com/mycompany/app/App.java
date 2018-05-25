package com.mycompany.app;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;

import lombok.Value;
import lombok.Builder;
import lombok.*;
import lombok.experimental.*;


import lombok.Builder;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;

import javax.swing.text.html.Option;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

/**
 * Find the path in the matrix which has 1's
 *
 */
public class App 
{

    private static boolean[][] inputFlag = null;

    public static void main( String[] args )
    {
        /*
        int[][] input = { {1,1,1,0,0} , {0,0,1,1,0}, {0,0,1,1,1},{0,0,1,0,1} };
        int rows = input.length;
        int cols = input[0].length;
        ArrayList<List<Integer>> retArr = new ArrayList<List<Integer>>();

        inputFlag = new boolean[rows][cols];

        //retArr = find_path(input, 0,0,4,5, retArr);
        //System.out.println(retArr);

        int missingNum = firstMissingPositive(new int[]{-1,1,0,4,3,6,2,-5,5,8});
        System.out.println(missingNum);

        //BuildClass bobj =
*/
        Integer blockStorageSizeGB = null;
        //Preconditions.checkState(blockStorageSizeGB >= 0,"blockStorageSizeGB %d is less than zero", blockStorageSizeGB);


        System.out.println("darshan:{}" + blockStorageSizeGB);

    }

    /*
    // special getter for optional field
    public static Optional<String> getPostcode(String in) {
        Option<String> postcode = new String ("");
        return Optional.ofNullable(postcode);
    }
    */

    private static ArrayList<List<Integer>> find_path(int[][] input, int m, int n, final int rows, final int cols,
                              ArrayList<List<Integer>> retArr){

        if(inputFlag[m][n] == true){
            return retArr;
        }
        // if you reached the last elem then push this
        if (m == rows-1 && n == cols-1){
            if (input[m][n] == 1) {
                List<Integer> inArr = new ArrayList<Integer>(2);
                inArr.add(m);
                inArr.add(n);
                retArr.add(inArr);
                return retArr;
            }
            else{ // input[m][n] == 0
                // dont add anything
                return retArr;
            }
        }

        if(input[m][n] == 0)
        {
            return retArr;
        }
        else{
            // add the current node
            List<Integer> inArr = new ArrayList<Integer>(2);
            inArr.add(m);
            inArr.add(n);
            retArr.add(inArr);
            inputFlag[m][n] = true;

            if(inbounds(m,n-1, rows, cols))
            {
                ArrayList<List<Integer>> leftRet = find_path(input, m, n-1, rows, cols, retArr);
                if(hasDest(leftRet, rows, cols)){
                    return leftRet;
                }
            }
            if(inbounds(m,n+1, rows, cols))
            {
                ArrayList<List<Integer>> rightRet = find_path(input, m, n+1, rows, cols, retArr);
                if(hasDest(rightRet, rows, cols)){
                    return rightRet;
                }
            }
            if(inbounds(m-1,n, rows, cols))
            {
                ArrayList<List<Integer>> topRet = find_path(input,m-1, n, rows, cols, retArr);
                if(hasDest(topRet, rows, cols)){
                    return topRet;
                }
            }
            if(inbounds(m+1,n, rows, cols))
            {
                ArrayList<List<Integer>> bottomRet = find_path(input,m+1, n, rows, cols, retArr);
                if(hasDest(bottomRet, rows, cols)){
                    return bottomRet;
                }
            }

            retArr.remove(retArr.size()-1);
            return retArr;
        }

    }

    private static boolean inbounds(int m, int n, int rows, int cols){
        try {
            if (m <= rows - 1 && m >= 0 && n <= cols - 1 && n >= 0) {
                return true;
            } else return false;
        }
        catch (NullPointerException e){
            System.out.println("abc");
        }
        return false;
    }

    private static boolean hasDest(ArrayList<List<Integer>> testArrList, int rows, int cols){
        if (testArrList.size() == 0){
            return false;
        }

        List<Integer> lastNode = new ArrayList<Integer>();
        lastNode.add(rows-1);
        lastNode.add(cols-1);

        return testArrList.contains(lastNode) ? true : false;
    }

    /*
    Given an unsorted integer array, find the first missing positive integer.

    For example,
    Given [1,2,0] return 3,
    and [3,4,-1,1] return 2.

    Your algorithm should run in O(n) time and uses constant space.
    */
    public static int firstMissingPositive(int[] nums) {
        int inLen = nums.length;
        int largest = 0;

        for(int each: nums) {
            if (each <= 0)
                continue;
            if(largest < each)
                largest = each;

        }
        boolean[] ref = new boolean[largest+1];


        ref[0] = true; // zero is neither +ve nor -ve
        int index = 0;
        for(int each: nums) {
            if (each <= 0)
                continue;
            ref[each] = true;
        }

        for(boolean each: ref) {
            if(index == 0) {
                index++;
                continue;
            }
            index++;
            if (each == false)
                return index-1;
        }
        return largest+1;
    }

}


@JsonDeserialize(builder=BuildClass.Builder.class)
class BuildClass {
    public static class Builder {

    }

    int attr1;

    public static void main(String[] args) {
        BuilderExample bObj1 = BuilderExample.builder().age(25).occupation("blha").build();
        //bObj.occupations.add("raa");
        BuilderExample bObj2 = BuilderExample.builder().age(26).occupation("aaa").build();
        //BuilderExample obj2 = bObj.builder().name("piyanka").build();

       // bObj.occupations.add("hello");
       // bObj.occupations.add("world");
        System.out.println(bObj1.age);
    }
}


@Builder
class BuilderExample {
    private String name;
    public int age;
    @Singular public Set<String> occupations;
}