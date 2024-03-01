import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Converter {
    private String infix;
    private Stack<String> myStack;

    public Converter(String infix) {
        this.infix = infix;
        this.myStack = new Stack<>();
    }

    public String toPostFix() {
        char[] arr = infix.toCharArray();
        StringBuilder postfix = new StringBuilder();
        
        List<String> list = new ArrayList<>();

        list = parse(arr);

        for (String item : list) {
            // for numbers
            if (item.matches("\\d+")) {
                postfix.append(item).append(" ");
            }
            // for the operators within parenthese
            else if (item.equals("(")) {
                myStack.push(item);
            } else if (item.equals(")")) {
                while (!myStack.isEmpty() && !myStack.peek().equals("(")) {
                    postfix.append(myStack.pop()).append(" ");
                }
                // After evalutating the operator, we need to pop the open parenthese
                if (!myStack.isEmpty() && myStack.peek().equals("(")) {
                    myStack.pop(); 
                }
            } 
            
            else {
                // For tokens that has lower precedence
                while (!myStack.isEmpty() && precedences(item) <= precedences(myStack.peek())) {
                    postfix.append(myStack.pop()).append(" ");
                }
                // If tokens has higher precedence
                myStack.push(item);
            }
        }

        // enter the rest of the strings
        while (!myStack.isEmpty()) {
            postfix.append(myStack.pop()).append(" ");
        }

        return postfix.toString();
    }

    // helper method
    private int precedences(String operator) {
        switch(operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return -1;
        }
    }

    // parese helper
    private List<String> parse(char[] input) {
        List<String> parsed = new ArrayList<>();

        for (int i = 0; i < input.length; i++) {
            char c = input[i];

            if (Character.isDigit(c)) {
                String number = input[i] + "";

                for (int j = i + 1; i < input.length; j++) {
                    if (Character.isDigit(input[j])) {
                        number += input[j];
                        i = j;
                    } else {
                        break;
                    }
                }

                parsed.add(number);
            } else if (c == '*' || c == '/' || c == '+' || c == '^' ||c == '-' || c == '(' || c == ')') {
                parsed.add(c + "");
            }
        }

        return parsed;
    }
}
