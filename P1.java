import java.util.*;

public class P1 {
    private static boolean isValid(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            // Check for opening comments
            if (i + 1 < s.length()) {
                String twoChars = s.substring(i, i + 2);
                if (twoChars.equals("/*")) {
                    stack.push("/*");
                    i++; // Skip the next character
                } else if (twoChars.equals("*/")) {
                    // Ensure that the last opened comment was /* before popping
                    if (stack.isEmpty() || !stack.peek().equals("/*")) {
                        return false; // Invalid closing of a comment
                    }
                    stack.pop();
                    i++; // Skip the next character
                } else if (twoChars.equals("//")) {
                    // Skip the rest of the line for single-line comment
                    i = s.indexOf("\n", i);
                    if (i == -1) break; // End of string, no newline found
                }
            }
            
            // Check for block delimiters
            char ch = s.charAt(i);
            if (ch == '{') {
                stack.push("{");
            } else if (ch == '}') {
                if (stack.isEmpty() || !stack.peek().equals("{")) {
                    return false; // Invalid closing of block
                }
                stack.pop();
            }
        }
        
        // If stack is empty, all comments and blocks are closed properly
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(isValid(s));
        sc.close();
    }
}
