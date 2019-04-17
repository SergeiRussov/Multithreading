import deadlock.Deadlock;
import deadlock.DeadlockSolution;
import resourcePool.MyThreads;

public class App {
    public static void main(String[] args) {

        new Deadlock().startDeadlock();

        new DeadlockSolution().startDeadlock();

        new MyThreads().startWork();
    }
}
