package model.exception;

//represents exception that occurs when user enters purchase with multiple decimals
public class MultipleDecimalPointsException extends Exception {
    public MultipleDecimalPointsException() {

    }

    public MultipleDecimalPointsException(String msg) {
        super(msg);
    }
}
