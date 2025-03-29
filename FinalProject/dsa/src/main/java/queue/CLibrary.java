package queue;

import com.sun.jna.Library;
import com.sun.jna.Native;


public interface CLibrary extends Library {
    CLibrary INSTANCE = Native.load("msvcrt", CLibrary.class);

    void printf(String format, Object... args);
}