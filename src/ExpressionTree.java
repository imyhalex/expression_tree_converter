import java.util.Stack;

public class ExpressionTree {

    private Stack<Node> stack;

    public ExpressionTree() {
        this.stack = new Stack<>();
    }

    public Node bulildTree(String postFixtExpression) {

        for (int i = 0; i < postFixtExpression.length(); i++) {
            if (Character.isDigit(postFixtExpression.charAt(i))) {
                Node node = new Node(postFixtExpression.charAt(i));
                stack.push(node);
            } else {
                Node opeartor = new Node(postFixtExpression.charAt(i));

                Node right = stack.pop();
                Node left = stack.pop();

                opeartor.leftChild = left;
                opeartor.rightChild = right;

                stack.push(opeartor);
            }
        }

        // last node in the stack
        Node last = stack.pop();

        return last;
    }

    public void prefix(Node node) {
        if (node != null) {
            System.out.print(node.element.toString());
            prefix(node.leftChild);
            prefix(node.rightChild);
        }
    }

    public void infix(Node node) {
        if (node == null) return;
    
        boolean isOperator = ((String) node.element).matches("[-+*/]");
        if (isOperator && node.leftChild != null) {
            System.out.print("(");
        }
    
        infix(node.leftChild);
    
        System.out.print(node.element + " "); // Print the current node's value
    
        infix(node.rightChild);
    
        if (isOperator && node.rightChild != null) {
            System.out.print(")");
        }
    }

    public void postfix(Node node) {
        if (node != null) {
            postfix(node.leftChild);
            postfix(node.rightChild);
            System.out.print(node.element.toString());
        }
    }
}
