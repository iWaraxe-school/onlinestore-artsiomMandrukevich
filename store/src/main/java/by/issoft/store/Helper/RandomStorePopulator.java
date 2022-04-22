package by.issoft.store.Helper;

import com.github.javafaker.Faker;

public class RandomStorePopulator {

    Faker faker = new Faker();

    public String setName(String categoryName){
        switch(categoryName){
            case "Bike" : return faker.howIMetYourMother().character();
            case "Milk" :  return faker.food().ingredient();
            case "Phone" : return faker.pokemon().name();
            default:
                throw new IllegalStateException("Unexpected category: "
                        + categoryName
                        + ". Look at 'by.issoft.store.Helper.RandomStorePopulator.setName.' for fixing.");
        }
    }

    public int setRate(){
        return faker.number().numberBetween(1, 10);
    }

    public int setPrice(){
        return faker.number().numberBetween(1, 1000);
    }

    public int setRandomInt(){
        return faker.number().numberBetween(5, 10);
    }

}
