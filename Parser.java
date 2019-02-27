import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.util.List;

/**
 * JavaDoc Comment
 */
public class Parser {
    // This is a comment

    private static int countLines(MethodDeclaration method){
        String[] lines = method.getBody().toString().split("\r\n|\r|\n");
        return  lines.length;
    }

    private static class MethodVisitor extends VoidVisitorAdapter {
        @Override
        public void visit(MethodDeclaration n, Object arg) {
            System.out.println("Found Method - " +n.getName());
            if(n.getName().asString().equals("visit")){
                System.out.println("Printing out method definition of method - 'visit'");
                System.out.println("Number of lines in method - "  + countLines(n) + "\n");
                System.out.println(n.getBody());
            }
        }
    }

    public static void main(String[] args) {
        String filePath = "src/main/java/Parser.java";

        // This is also a comment
        try {
            CompilationUnit xd = JavaParser.parse(new File(filePath));
            List<Comment> comments = xd.getComments();
            new MethodVisitor().visit(xd, null);
            System.out.println("\nFound (" + comments.size() + ") Comments");
            comments.forEach((comment ->
                    System.out.println(comment.getContent())
            ));


        } catch (Exception ex) {
            /**
             * Multiline comment
             */
            // Single line comment hmm
            System.err.println(ex);
            System.exit(1);
        }

        System.exit(0);
    }

}