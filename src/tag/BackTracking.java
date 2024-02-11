package tag;

public @interface BackTracking {
    String timeComplexity() default "";

    String spaceComplexity() default "";
}
