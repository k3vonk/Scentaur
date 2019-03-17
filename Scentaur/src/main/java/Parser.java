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

    File file;

    public Parser(String filePath) {
        this.file = new File(filePath);
    }

    private static int countLines(MethodDeclaration method) {
        return method.getBody().toString().split("\r\n|\r|\n").length;
    }

    private static class MethodVisitor extends VoidVisitorAdapter {
        @Override
        public void visit(MethodDeclaration n, Object arg) {
            System.out.print("Found Method - " + n.getName());
            System.out.println(" | Number of lines - " + countLines(n));
        }
    }

    public void parse() {

        // This is also a comment
        try {
            CompilationUnit xd = JavaParser.parse(file);
            List<Comment> comments = xd.getComments();
            new MethodVisitor().visit(xd, null);
            System.out.println("\nFound (" + comments.size() + ") Comments");


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