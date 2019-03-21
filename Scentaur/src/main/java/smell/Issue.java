package smell;

public class Issue {

    private String className;
    private int lineNumber;
    private String methodName;
    private String issueType;
    private String comment;

    public Issue(String className, String methodName, int lineNumber, String issueType,
            String comment) {
        this.className = className;
        this.lineNumber = lineNumber;
        this.issueType = issueType;
        this.methodName = methodName;
        this.comment = comment;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getComment() {
        return comment;
    }


}
