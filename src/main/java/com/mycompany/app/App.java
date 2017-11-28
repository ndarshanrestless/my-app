package com.mycompany.app;
import java.io.*;
import java.util.*;

/**
 * Find the path in the matrix which has 1's
 *
 */
public class App 
{

    private static boolean[][] inputFlag = null;

    public static void main( String[] args )
    {
        int[][] input = { {1,1,1,0,0} , {0,0,1,1,0}, {0,0,1,1,1},{0,0,1,0,1} };
        int rows = input.length;
        int cols = input[0].length;
        ArrayList<List<Integer>> retArr = new ArrayList<List<Integer>>();


        inputFlag = new boolean[rows][cols];


        retArr = find_path(input, 0,0,4,5, retArr);
        System.out.println(retArr);

    }

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

}
