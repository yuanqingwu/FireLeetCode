package tag;

public @interface TrieTag {
    String timeComplexity() default "";

    String spaceComplexity() default "";
}
