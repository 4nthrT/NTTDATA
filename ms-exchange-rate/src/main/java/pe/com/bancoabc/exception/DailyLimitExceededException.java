package pe.com.bancoabc.exchangerate.exception;

public class DailyLimitExceededException
        extends RuntimeException {

    public DailyLimitExceededException(String message) {
        super(message);
    }
}