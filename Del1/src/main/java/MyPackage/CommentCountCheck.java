package MyPackage;

import com.puppycrawl.tools.checkstyle.api.*;
import java.util.List;

public class CommentCountCheck extends AbstractCheck {
    private int count;

    @Override
    public int[] getDefaultTokens() {
        return new int[] {
            TokenTypes.SINGLE_LINE_COMMENT,
            TokenTypes.BLOCK_COMMENT_BEGIN
        };
    }

    @Override
    public int[] getAcceptableTokens() {
        return getDefaultTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return getDefaultTokens();
    }

    @Override
    public boolean isCommentNodesRequired() {
        return true; 
    }

    @Override
    public void beginTree(DetailAST rootAST) {
        count = 0; 
    }

    @Override
    public void visitToken(DetailAST ast) {
        count++;
    }

    @SuppressWarnings("deprecation")
	@Override
    public void finishTree(DetailAST rootAST) {
        if (rootAST == null) {
            count = getFileContents().getSingleLineComments().size();
            for (List<TextBlock> comments : getFileContents().getBlockComments().values()) {
                count += comments.size();
            }
        }
        log(1, "comment.count", count);
    }
}
