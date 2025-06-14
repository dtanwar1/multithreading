import java.util.function.Consumer;

class ABCPrintingSync extends PrintABC {
    private int n = 0;
    boolean printA = true;
    boolean printB = false;
    boolean printC = false;
    final private Object obj = new Object();

    public ABCPrintingSync(int _n) {
        n = _n;
    }

    public void printAOnConsole(Consumer<Character> consumer) {

        for (int i = 0; i < n; i++) {
            synchronized (obj) {
                while (!printA) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                consumer.accept('A');
                printA = false;
                printB = true;
                obj.notifyAll();

            }
        }
    }

    public void printBOnConsole(Consumer<Character> consumer) {
        for (int i = 0; i < n; i++) {
            synchronized (obj) {

                while (!printB) {
                    try {
                        obj.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                consumer.accept('B');
                printB = false;
                printC = true;
                obj.notifyAll();
            }
        }

    }

    public void printCOnConsole(Consumer<Character> consumer) {
        for (int i = 0; i < n; i++) {
            synchronized (obj) {
                while (!printC) {
                    try {
                        obj.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                consumer.accept('C');
                printC = false;
                printA = true;
                obj.notifyAll();
            }
        }

    }
}
