package smell.dispensable;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.comments.Comment;

import smell.Smell;

/**
 * Detects smells where a method is filled with explanatory comments
 */

public class Comments extends Smell{

    public static final double COMMENTS_RATIO_THRESHOLD = .4;

    public Comments() {
        smell = new ArrayList<>();
    }

    /**
     * Checks all comments within a class and compares with our ratio
     */
    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
        int commentsLength = 0;
        List<Comment> comments = n.getAllContainedComments();

        for(Comment comment : comments){	//Obtain length of comments
            commentsLength += comment.getContent().split("\n").length;
        }

        double ratio = (double) commentsLength / n.toString().split("\n").length;

        if(ratio  > COMMENTS_RATIO_THRESHOLD){
            smell.add(n);
            //addComment(n);
        }


    }

    @Override
    public void addComment(Node n) {
    	/*
        //Check if has no comments
        if(!n.getComment().isPresent()) {
            ((NodeWithJavadoc<ConstructorDeclaration>) n).setJavadocComment("SmellDetected (Possibly too many comments?): " + this.getClass().getSimpleName());
        }else {
            Comment comment = n.getComment().get();
            ((NodeWithJavadoc<ConstructorDeclaration>) n).setJavadocComment(
                    comment.getContent() + "\n\nSmellDetected  (Possibly too many comments?): " + this.getClass().getSimpleName());
        }*/
    }

}
