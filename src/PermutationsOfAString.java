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
        permutation(s, "");
    }
    public static void permutation(String str, String ans){
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

    }
}
