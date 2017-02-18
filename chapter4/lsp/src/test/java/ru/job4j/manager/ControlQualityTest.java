package ru.job4j.manager;

import org.junit.Test;
import ru.job4j.food.Foods;
import ru.job4j.food.IFoods;
import ru.job4j.food.Meat;
import ru.job4j.food.Milk;
import ru.job4j.storage.IStorage;
import ru.job4j.storage.Shop;
import ru.job4j.storage.Warehouse;
import ru.job4j.storage.Trash;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * Test.
 */
public class ControlQualityTest {

    /**
     * Local date.
     */
    private final LocalDate localDate = LocalDate.now();

    /**
     * Test class Foods.
     * Method getName.
     */
    @Test
    public void whenGetNameThenReturnValidValue() {
        final IFoods foods = new Milk("Milk", null, null, 0d);
        final String expectedValue = "Milk";

        assertThat(foods.getName(), is(expectedValue));
    }

    /**
     * Test class Foods.
     * Method getCreateDate.
     */
    @Test
    public void whenGetCreateDateThenReturnValidValue() {
        final IFoods foods = new Milk(null, localDate.minusDays(10), null, 0d);
        final int expectedValue = localDate.minusDays(10).getDayOfYear();

        assertThat(foods.getCreateDate().getDayOfYear(), is(expectedValue));
    }

    /**
     * Test class Foods.
     * Method getExpireDate.
     */
    @Test
    public void whenGetExpireDateThenReturnValidValue() {
        final IFoods foods = new Milk(null, null, localDate.plusDays(10), 0d);
        final int expectedValue = localDate.plusDays(10).getDayOfYear();

        assertThat(foods.getExpireDate().getDayOfYear(), is(expectedValue));
    }

    /**
     * Test class Foods.
     * Method checkShelfLife.
     */
    @Test
    public void whenCallCheckShelfLifeThenReturnsTheRemainingShelfLifeInPercent() {
        final IFoods foods = new Meat(null, localDate.minusDays(10), localDate.plusDays(15), 0d);
        final double expectedValue = 60d;

        assertThat(foods.checkShelfLife(), is(expectedValue));
    }

    /**
     * Test class Foods.
     * Method setDiscount and getDiscount.
     */
    @Test
    public void whenGetAndSetDiscountThenReturnValidValue() {
        final IFoods foods = new Milk(null, null, null, 0d);
        final int expectedValue = 20;

        foods.setDiscount(expectedValue);

        assertThat(foods.getDiscount(), is(expectedValue));
    }

    /**
     * Test class Foods.
     * Method getPrice without discount.
     */
    @Test
    public void whenGetPriceWithoutDiscountThenReturnFullPrice() {
        final IFoods foods = new Milk(null, null, null, 70d);
        final double expectedValue = 70d;

        assertThat(foods.getPrice(), is(expectedValue));
    }

    /**
     * Test class Foods.
     * Method getPrice with discount.
     */
    @Test
    public void whenGetPriseWithDiscountThenReturnDiscountedPrice() {
        final IFoods foods = new Milk(null, null, null, 70);
        final double expectedValue = 56d;

        foods.setDiscount(20);

        assertThat(foods.getPrice(), is(expectedValue));
    }

    /**
     * Test class Foods.
     * Method toString.
     */
    @Test
    public void toStringTest() {
        final Foods foods = new Milk("Milk", null, null, 75);
        final String expectedValue = "{Milk, createDate: null, expireDate: null, price: 75.0}";

        assertThat(foods.toString(), is(expectedValue));
    }

    /**
     * Test class Storage.
     * Method getName.
     */
    @Test
    public void whenGetNameStorageThenReturnValidValue() {
        final IStorage storage = new Shop("Shop");
        final String expectedValue = "Shop";

        assertThat(storage.getName(), is(expectedValue));
    }

    /**
     * Test class Storage.
     * Method getAmountOfFoods.
     */
    @Test
    public void whenAddsOneFoodThenReturnOneValue() {
        final IStorage storage = new Trash("Trash");
        final IFoods foods = new Milk(null, localDate.minusDays(10), localDate.plusDays(0), 0d);
        final int expectedValue = 1;

        storage.addFood(foods);

        assertThat(storage.getAmountOfFoods(), is(expectedValue));
    }

    /**
     * Test class Storage.
     * Method addFood.
     */
    @Test
    public void whenIsNotAddsProductsThenReturnFalse() {
        final IStorage storage = new Trash("Trash");
        final IFoods foods = new Milk(null, localDate.minusDays(10), localDate.plusDays(10), 0d);

        assertFalse(storage.addFood(foods));
    }

    /**
     * Test class Storage.
     * Method removeFood.
     */
    @Test
    public void whenRemoveFoodsThenDecreasesAmountOfFoods() {
        final IStorage storage = new Trash("Trash");
        final IFoods foods = new Milk(null, localDate.minusDays(10), localDate.plusDays(0), 10d);
        final IFoods foods2 = new Milk(null, localDate.minusDays(10), localDate.plusDays(0), 0d);
        final int expectedValue = 1;
        storage.addFood(foods);
        storage.addFood(foods2);

        storage.removeFood(foods);

        assertThat(storage.getAmountOfFoods(), is(expectedValue));
    }

    /**
     * Test class Storage.
     * Method toString.
     */
    @Test
    public void toStringStorageTest() {
        final String sep = System.getProperty("line.separator");
        final IStorage storage = new Trash("Trash");
        final IFoods foods = new Milk(null, localDate.minusDays(10), localDate.plusDays(0), 0d);
        storage.addFood(foods);
        final String expectedValue =
                String.format("Name storage: Trash%sThe number of products to warehouse:" +
                        " 1%s{null, createDate: 2017-02-08, expireDate: 2017-02-18, price: 0.0}%s", sep, sep, sep);

        assertThat(storage.toString(), is(expectedValue));
    }

    /**
     * Test class Shop.
     * Method validatesFood.
     */
    @Test
    public void whenIsValidFoodsShopThenReturnTrue() {
        final IFoods foods = new Meat(null, localDate.minusDays(10), localDate.plusDays(10), 60d);
        final IStorage storage = new Shop("Shop");

        assertTrue(storage.validatesFood(foods));
    }

    /**
     * Test class Shop.
     * Method validatesFood.
     */
    @Test
    public void whenNotAddedFoodsThenReturnFalse() {
        final IFoods foods = new Meat(null, localDate.minusDays(5), localDate.plusDays(50), 60d);
        final IStorage storage = new Shop("Shop");

        assertFalse(storage.validatesFood(foods));
    }

    /**
     * Test class Shop.
     * Method validateFood set discount.
     */
    @Test
    public void whenAddedFoodsDoDiscountThenSetDiscount() {
        final IFoods foods = new Meat(null, localDate.minusDays(10), localDate.plusDays(2), 60d);
        final IStorage storage = new Shop("Shop");
        final double expectedValue = 45d;

        storage.addFood(foods);

        assertThat(foods.getPrice(), is(expectedValue));
    }

    /**
     * Test class Warehouse.
     * Method validatesFood.
     */
    @Test
    public void whenIsValidFoodsWarehouseThenReturnTrue() {
        final IFoods foods = new Meat(null, localDate.minusDays(5), localDate.plusDays(50), 60d);
        final IStorage storage = new Warehouse("Warehouse");

        assertTrue(storage.validatesFood(foods));
    }

    /**
     * Test class ControlQuality.
     * Method addStorage.
     */
    @Test
    public void whenAddedStorageThenNumberIncreases() {
        final IControlQuality quality = new ControlQuality();
        final IStorage storage = new Warehouse("Warehouse");
        final int expectedValue = 1;

        quality.addStorage(storage);
        assertThat(quality.getAmountOfStorages(), is(expectedValue));
    }

    /**
     * Test class ControlQuality.
     * Method removeStorage.
     */
    @Test
    public void whenRemoveStorageThenNumberDecreases() {
        final IControlQuality quality = new ControlQuality();
        final IStorage storage = new Warehouse("Warehouse");
        final IStorage storage2 = new Warehouse("Warehouse");
        final IStorage storage3 = new Warehouse("Warehouse");
        final int expectedValue = 2;

        quality.addStorage(storage);
        quality.addStorage(storage2);
        quality.addStorage(storage3);
        quality.removeStorage(storage);

        assertThat(quality.getAmountOfStorages(), is(expectedValue));
    }

    /**
     * Test class ControlQuality.
     * Method getStorageList.
     */
    @Test
    public void whenCallGetStorageListThenReturnStorageList() {
        final IControlQuality quality = new ControlQuality();
        final IStorage storage = new Warehouse("Warehouse");
        final List<IStorage> expectedList = new ArrayList<>();
        expectedList.add(storage);

        quality.addStorage(storage);

        assertThat(quality.getStorageList(), is(expectedList));
    }

    /**
     * Test class ControlQuality.
     * Method sortFoods.
     */
    @Test
    public void whenCallSortFoodsThenFoodsInDifferentRepositories() {
        final IFoods shopFood = new Milk("milk", localDate.minusDays(10), localDate.plusDays(10), 50d);
        final IFoods trashFood = new Milk("milk", localDate.minusDays(10), localDate.plusDays(0), 0d);
        final IFoods warehouseFood = new Milk("milk", localDate.minusDays(10), localDate.plusDays(60), 100d);
        final IStorage shop = new Shop("Shop");
        final IStorage trash = new Trash("Trash");
        final IStorage warehouse = new Warehouse("Warehouse");
        final int expectedValue = 1;
        final IControlQuality quality = new ControlQuality();
        quality.addStorage(shop);
        quality.addStorage(trash);
        quality.addStorage(warehouse);

        quality.sortFoods(shopFood);
        quality.sortFoods(trashFood);
        quality.sortFoods(warehouseFood);

        assertThat(shop.getAmountOfFoods(), is(expectedValue));
        assertThat(trash.getAmountOfFoods(), is(expectedValue));
        assertThat(warehouse.getAmountOfFoods(), is(expectedValue));
    }
}