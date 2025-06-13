
import java.util.concurrent.*;
import java.util.*;



public class ABCPrintingSync {

    
    public static void main(String[] args) throws Exception {

       PrintABC abc = new PrintABC(10);

       Thread t1 = new Thread(()->{
            try {
                Thread.sleep(1000);
                abc.printAOnConsole();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
       });

       Thread t2 = new Thread(()->{
            try {
                Thread.sleep(10);
                abc.printBOnConsole();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       });

       Thread t3 = new Thread(()->{
             try {
                Thread.sleep(20);
                abc.printCConsole();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       });


       t1.start();
       t2.start();
       t3.start();
        
        
    }

    

   
}

class PrintABC{
    private int n = 0;
    boolean printA = true;
    boolean printB = false;
    boolean printC = false;
    final private Object obj = new Object();
    public PrintABC(int _n){
        n = _n;
    }

    public void printAOnConsole(){

        for(int i = 0;i<n;i++){
            synchronized(obj){

                while(!printA){
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("A");
                printA = false;
                printB = true;
                obj.notifyAll();            
                
            }   
        }
    }

    public void printBOnConsole(){
       for(int i = 0;i<n;i++){
            synchronized(obj){

                while(!printB){
                    try{
                        obj.wait();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                System.out.print("B");
                printB = false;
                printC = true;
                obj.notifyAll();
            }   
        }
        
    }

    public void printCConsole(){
        for(int i = 0;i<n;i++){
            synchronized(obj){
                while(!printC){
                    try {
                        obj.wait();    
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("C");
                printC = false;
                printA = true;
                obj.notifyAll();
            }   
        }
       
    }
}


