public abstract class BaseSolution {


    public void run() {

        long start = System.nanoTime();
        solution();
        long end = System.nanoTime();

        System.out.println("cost time:" + (end - start) + " ns");
    }

    abstract void solution();

}
