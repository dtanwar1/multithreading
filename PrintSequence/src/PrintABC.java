import java.util.function.Consumer;

public abstract class PrintABC {
    public abstract void printAOnConsole(Consumer<Character> print);
    public abstract void printBOnConsole(Consumer<Character> print);
    public abstract void printCOnConsole(Consumer<Character> print);
}
