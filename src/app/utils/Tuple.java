package app.utils;

import java.io.Serializable;

public class Tuple<L, R> implements Serializable {
    private final L left;
    private final R right;

    private Tuple(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Tuple<L, R> from(L left, R right) {
        return new Tuple<>(left, right);
    }

    public L getLeft() {
        return this.left;
    }

    public R getRight() {
        return this.right;
    }

    @Override
    public String toString() {
        return "Tuple: " + getLeft() + ":" + getRight();
    }
}
