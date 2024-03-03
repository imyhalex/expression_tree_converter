import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println("Type your expression: ");
            String infix = scn.nextLine();

            Converter converter = new Converter(infix);

            String postfix = converter.toPostFix();

            ExpressionTree tree = new ExpressionTree();

            Node root = tree.buildTree(postfix);

            System.out.print("Prefix: ");
            tree.prefix(root);
            System.out.println();
            System.out.print("Infix: ");
            tree.infix(root);
            System.out.println();
            System.out.print("Postfix: ");
            tree.postfix(root);
            System.out.println();

        }

    }
}
