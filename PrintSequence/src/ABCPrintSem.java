import java.util.concurrent.Semaphore;
import java.util.function.Consumer;


public class ABCPrintSem extends PrintABC{
    int n;

    Semaphore semA = new Semaphore(1);
    Semaphore semB = new Semaphore(0);
    Semaphore semC = new Semaphore(0);

    public ABCPrintSem(int _n){
        this.n = _n;
    }

    public void printAOnConsole(Consumer<Character> print){
        for(int i = 0;i<n;i++){
            try {
                semA.acquire();
                print.accept('A');
                semB.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void printBOnConsole(Consumer<Character> print){
         for(int i = 0;i<n;i++){
            try {
                semB.acquire();
                print.accept('B');
                semC.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void printCOnConsole(Consumer<Character> print){
        for(int i = 0;i<n;i++){
            try {
                semC.acquire();
                print.accept('C');
                semA.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
