/*
* Permutations of a String
Description
You are given a string, you have to print all the permutations of the given string.

A permutation is an arrangement of all or part of a set of objects, with regard to the order of
* the arrangement. For example- “act” and “tac” are two different permutations instances of the word cat.

All the permutations of a given word should be different.



Example-

Input: pqr
         Output: pqr prq qpr qrp rpq rqp

Input: abb
          Output: abb bab bba



Approach: You need to write a recursive function that prints every permutation of the given string.
* Terminating condition will be when the passed string is empty.
*
* */


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PermutationsOfAString {
    public static void main(String args[] ) throws Exception {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String s = read.readLine();
        permutation(s);
    }
    public static void permutation(String s) {
        recursivePrint(s.toCharArray(), "");
    }
    public static void recursivePrint(char[] charArr, String left) {
        int n = charArr.length;
        if (n == 1) {
            System.out.println(left + charArr[0]);
            return;
        }
        for (int i = 0; i < n; i++) {

            left = left + charArr[i];

            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    stringBuilder.append(charArr[j]);
                }
            }
            char[] newCharArr = stringBuilder.toString().toCharArray();
            recursivePrint(newCharArr, left);
            left = left.substring(0, left.length() - 1);
        }
    }
}
