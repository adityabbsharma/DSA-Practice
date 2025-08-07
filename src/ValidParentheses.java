import java.util.Stack;

/*
* 20. Valid Parentheses
* Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.


Example 1:

Input: s = "()"

Output: true

Example 2:

Input: s = "()[]{}"

Output: true

Example 3:

Input: s = "(]"

Output: false

Example 4:

Input: s = "([])"

Output: true

Example 5:

Input: s = "([)]"

Output: false



Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
*
* */
public class ValidParentheses {
    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        System.out.println(validParentheses.isValid("([)]"));
    }

    public boolean isValid(String s) {
        // use stack
        Stack<Character> bracket = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c==')') {
                if(bracket.isEmpty() || bracket.peek() != '(' ) {
                    return false;
                } else {
                    bracket.pop();
                }
            }  else if (c=='}') {
                if(bracket.isEmpty() || bracket.peek()!='{') {
                    return false;
                } else {
                    bracket.pop();
                }
            } else if (c==']') {
                if(bracket.isEmpty() || bracket.peek()!='[') {
                    return false;
                } else {
                    bracket.pop();
                }
            }  else if(c=='(' || c=='{' || c=='[') {
                bracket.push(c);
            }
        }
        if(bracket.isEmpty()) {
            return true;
        }
        return false;
    }
}
