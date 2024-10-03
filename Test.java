import java.util.*;

public class Test {
    public static boolean isValid(String s, int n) {
        Stack<String> st = new Stack<>();
        int i = 0;
        
        while (i < n) {
            if (s.charAt(i) == '{') {
                // Push opening curly bracket
                st.push("{");
                i++;
            } 
            else if (s.charAt(i) == '}') {
                // Check if there's a matching opening curly bracket
                if (!st.isEmpty() && st.peek().equals("{")) {
                    st.pop();
                    i++;
                } else {
                    return false;  // Mismatched closing bracket
                }
            } 
            else if (i + 1 < n && s.charAt(i) == '/' && s.charAt(i + 1) == '*') {
                // Push opening block comment "/*"
                st.push("/*");
                i += 2;
            } 
            else if (i + 1 < n && s.charAt(i) == '*' && s.charAt(i + 1) == '/') {
                // Check if there's a matching opening block comment "/*"
                if (!st.isEmpty() && st.peek().equals("/*")) {
                    st.pop();
                    i += 2;
                } else {
                    return false;  // Mismatched closing block comment
                }
            } 
            else if (i + 1 < n && s.charAt(i) == '/' && s.charAt(i + 1) == '/') {
                // Single line comment "//", skip the rest of the line
                i += 2;
                while (i < n && s.charAt(i) != '\n') {
                    i++;
                }
            } 
            else {
                // Other characters, just move to the next one
                i++;
            }
        }
        
        // If stack is empty, all blocks and comments are properly closed
        return st.isEmpty();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        System.out.println(isValid(line, line.length()));
        sc.close();
    }
}
