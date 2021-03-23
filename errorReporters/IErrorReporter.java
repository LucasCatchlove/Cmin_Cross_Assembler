package errorReporters;

public interface IErrorReporter {

    void recordError(ErrorMsg errorMsg);

    void reportErrors();
    public boolean isEmpty();

}