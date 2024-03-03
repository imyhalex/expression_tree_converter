import java.util.Stack;

public class ExpressionTree {

    private Stack<Node> stack;

    public ExpressionTree() {
        this.stack = new Stack<>();
    }
    
    // Helper
    private boolean isOperator(String s) {
        return (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^"));
    }

    public Node buildTree(String postFixExpression) {
        String[] tokens = postFixExpression.split(" ");

        for (String t : tokens) {
            if (!isOperator(t)) {
                Node node = new Node(t);
                stack.push(node);
            } else {
                Node operator = new Node(t);

                Node right = stack.pop();
                Node left = stack.pop();

                operator.leftChild = left;
                operator.rightChild = right;

                stack.push(operator);
            }
        }

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

        if (isOperator(node.element.toString()) && node.leftChild != null)
            System.out.print("(");

        infix(node.leftChild);
        System.out.print(node.element.toString());
        infix(node.rightChild);

        if (isOperator(node.element.toString()) && node.rightChild != null)
            System.out.print(")");
    }

    public void postfix(Node node) {
        if (node != null) {
            postfix(node.leftChild);
            postfix(node.rightChild);
            System.out.print(node.element.toString());
        }
    }
}
