package by.issoft.domain;

import java.util.Random;

public enum CategoryType {
    BIKE,
    MILK,
    PHONE;

    // method for getting random Category
    private static final Random PRNG = new Random();
    public static CategoryType randomDirection()  {
        CategoryType[] categoryType = values();
        return categoryType[PRNG.nextInt(categoryType.length)];
    }
}


