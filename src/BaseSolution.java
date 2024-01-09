public abstract class BaseSolution {

    public static final String TAG = "fireleetcode";

    public void println(String msg) {
        System.out.println(TAG + " " + msg);
    }

    public void run() {

        try {
            long start = System.currentTimeMillis();
            solution();
            long end = System.currentTimeMillis();

            println("successful !!! \ncost time:" + (end - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
            println("error !!!");
        }

    }

    abstract void solution();

}
