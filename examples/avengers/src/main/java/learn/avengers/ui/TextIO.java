package learn.avengers.ui;

public interface TextIO {
    void println(String text);

    void print(String text);

    void printf(String text, Object... args);

    String readString(String prompt);

    int readInt(String prompt, int min, int max);

    boolean readBoolean(String prompt);
}
