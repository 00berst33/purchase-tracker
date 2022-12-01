package model.exception;

//represents exception when user enters purchase with >2 sig figs after decimal
public class TooManySigFigsException extends Exception {
    public TooManySigFigsException() {

    }

    public TooManySigFigsException(String msg) {
        super(msg);
    }
}
