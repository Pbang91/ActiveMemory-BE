package com.example.activememory.global.enums;

public enum ExerciseType {
    BARBELL("바벨"),
    DUMBBELL("덤벨"),
    MACHINE("기구"),
    BODYWEIGHT("맨몸");

    private final String koName;

    ExerciseType(String koName) {
        this.koName = koName;
    }

    public String getKoName() {
        return koName;
    }
}
