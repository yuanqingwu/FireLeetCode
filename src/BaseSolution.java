public abstract class BaseSolution {

    public static final String TAG = "firecode";


    public void println(String msg){
        System.out.println(TAG + " "+ msg);
    }

    public void run() {

        long start = System.nanoTime();
        solution();
        long end = System.nanoTime();

        println(" cost time:" + (end - start) + " ns");
    }

    abstract void solution();

}
