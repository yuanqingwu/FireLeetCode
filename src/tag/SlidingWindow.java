package tag;

public @interface SlidingWindow {
    String timeComplexity() default "";

    String spaceComplexity() default "";
}
