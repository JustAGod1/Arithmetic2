package Exceptions;

import java.io.IOException;

/**
 * Created by Yuri on 21.10.16.
 */
public class ParsingException extends Exception {

    public ParsingException(String msg) {
        super(msg);
    }

    public ParsingException() {
        super("Что-то было написано не правильно");
    }
}
