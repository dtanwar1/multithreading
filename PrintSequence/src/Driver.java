import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class Driver {
    
    public static void main(String[] args){

        ExecutorService esSyn = Executors.newFixedThreadPool(3);
        PrintABC objSync = new ABCPrintingSync(10);
        
        Consumer<Character> printRes = x->{System.out.print(x);};

        System.out.println("Calling Synchronized Implementation");

        List<Runnable> ltRunnables = List.of(
            createTask(()->{objSync.printAOnConsole(printRes);}, 1000),
            createTask(()->{objSync.printBOnConsole(printRes);}, 20),
            createTask(()->{objSync.printCOnConsole(printRes);}, 40)
        );


        for(Runnable r: ltRunnables){
            esSyn.execute(r);
        }

        
        esSyn.shutdown();

       try {
            Thread.sleep(10000);
       } catch (Exception e) {
            e.printStackTrace();
       }
        


        ExecutorService esSem = Executors.newFixedThreadPool(3);
        PrintABC objSem = new ABCPrintSem(10);
        

        System.out.println("Calling Semaphore Implementation");

        List<Runnable> ltRunnablesSem = List.of(
            createTask(()->{objSync.printAOnConsole(printRes);}, 1000),
            createTask(()->{objSync.printBOnConsole(printRes);}, 20),
            createTask(()->{objSync.printCOnConsole(printRes);}, 3000)
        );


        for(Runnable r: ltRunnablesSem){
            esSem.execute(r);
        }

        esSem.shutdown();        

    }

    public static Runnable createTask(Runnable action, int delayInMillis){
        return ()->{
            try {
                Thread.sleep(delayInMillis);
                action.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
