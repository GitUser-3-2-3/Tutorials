package com.parth.queue;

public class StudentMarks implements Comparable<StudentMarks> {
    private final int maths;
    private final int physics;

    public static class Builder {
        private final int maths;
        private final int physics;

        public Builder(int maths, int physics) {
            this.maths = maths;
            this.physics = physics;
        }

        public StudentMarks build() {
            return new StudentMarks(this);
        }
    }

    private StudentMarks(Builder builder) {
        maths = builder.maths;
        physics = builder.physics;
    }

    public int getMaths() {
        return maths;
    }

    public int getPhysics() {
        return physics;
    }

    @Override
    public int compareTo(StudentMarks o) {
        return o.maths - this.maths;
    }

    @Override
    public String toString() {
        return "StudentMarks{" +
            "maths=" + maths +
            ", physics=" + physics +
            '}';
    }
}


