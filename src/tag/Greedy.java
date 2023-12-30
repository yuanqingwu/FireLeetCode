package tag;

public @interface Greedy {
    String timeComplexity() default "";

    String spaceComplexity() default "";
}
