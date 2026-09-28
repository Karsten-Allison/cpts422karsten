package MyPackage;

import com.puppycrawl.tools.checkstyle.api.*;

public class LoopCountCheck extends AbstractCheck {
    private int count;

    @Override
    public int[] getDefaultTokens() {
        return new int[] {
            TokenTypes.LITERAL_FOR, 
            TokenTypes.LITERAL_WHILE,
            TokenTypes.LITERAL_DO
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
    public void beginTree(DetailAST rootAST) {
        count = 0;
    }

    @Override
    public void visitToken(DetailAST ast) {
        count++;
    }

    @Override
    public void finishTree(DetailAST rootAST) {
        log(1, "loop.count", count);
    }
}
