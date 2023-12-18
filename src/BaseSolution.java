public abstract class BaseSolution {

    public static final String TAG = "firecode";

    public void println(String msg) {
        System.out.println(TAG + " " + msg);
    }

    public void run() {

        try {
            long start = System.nanoTime();
            solution();
            long end = System.nanoTime();

            println("successful !!! cost time:" + (end - start) + " ns");
        } catch (Exception e) {
            e.printStackTrace();
            println("error !!!");
        }

    }

    abstract void solution();

}
