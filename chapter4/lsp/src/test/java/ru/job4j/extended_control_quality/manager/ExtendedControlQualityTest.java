package ru.job4j.extended_control_quality.manager;

import org.junit.Test;
import ru.job4j.extended_control_quality.food.Chocolate;
import ru.job4j.extended_control_quality.food.IRecycleFood;
import ru.job4j.extended_control_quality.storage.ExtendedWareHouse;
import ru.job4j.extended_control_quality.storage.Shop;
import ru.job4j.extended_control_quality.storage.Trash;
import ru.job4j.extended_control_quality.storage.WareHouseRecycle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 */
public class ExtendedControlQualityTest {

    /**
     * Chocolate food.
     */
    private final IRecycleFood chocolate = new Chocolate("chocolate",
            LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 50d);

    /**
     * Chocolate class.
     * Method getName.
     */
    @Test
    public void whenCallGetNameThenReturnValidValue() {
        final String expectedValue = "chocolate";

        assertThat(chocolate.getName(), is(expectedValue));
    }

    /**
     * Chocolate class.
     * Method getCreateDate.
     */
    @Test
    public void whenCallGetCreateDateThenReturnValidValue() {
        final int expectedValue = 42;

        assertThat(chocolate.getCreateDate().getDayOfYear(), is(expectedValue));
    }

    /**
     * Chocolate class.
     * Method getExpireDate.
     */
    @Test
    public void whenCallGetExpireDateThenReturnValidValue() {
        final int expectedValue = 62;

        assertThat(chocolate.getExpireDate().getDayOfYear(), is(expectedValue));
    }

    /**
     * Chocolate class.
     * Method getPrice without discount.
     */
    @Test
    public void whenCallGetPriceThenReturnValidValue() {
        final double expectedValue = 50d;

        assertThat(chocolate.getPrice(), is(expectedValue));
    }

    /**
     * Chocolate class.
     * Method getPrice to discounted.
     */
    @Test
    public void whenSetDiscountAndGetPriceThenReturnDiscountedPrice() {
        final double expectedValue = 40d;
        final int discountValue = 20;

        chocolate.setDiscount(discountValue);

        assertThat(chocolate.getPrice(), is(expectedValue));
        assertThat(chocolate.getDiscount(), is(discountValue));
    }

    /**
     * Chocolate class.
     * Method isRecycle.
     */
    @Test
    public void whenCallIsRecycleThenReturnTrue() {
        final boolean expectedValue = true;

        assertThat(chocolate.isRecycle(), is(expectedValue));
    }

    /**
     * Chocolate class.
     * Method checkShelfLife.
     */
    @Test
    public void checkShelfLifeTest() {
        final double expectedValue = 50d;

        assertThat(chocolate.checkShelfLife(), is(expectedValue));
    }

    /**
     * Shop class.
     * Method addFood return true.
     */
    @Test
    public void whenValidValueThenReturnTrue() {
        final WareHouseRecycle shop = new Shop("Shop");
        final boolean expectedValue = true;

        assertThat(shop.addFood(chocolate), is(expectedValue));
    }

    /**
     * Shop class.
     * Method addFood return false.
     */
    @Test
    public void whenInValidValueThenReturnFalse() {
        final WareHouseRecycle shop = new Shop("Shop");
        final boolean expectedValue = false;
        final IRecycleFood chocolate = new Chocolate(null, LocalDate.now().minusDays(5), LocalDate.now().plusDays(50), 50d);

        assertThat(shop.addFood(chocolate), is(expectedValue));
    }

    /**
     * Shop class.
     * Method validateFood set discount.
     */
    @Test
    public void whenShelfLifeFoodsLessTwentyFivePercentThenSetDiscountToPrice() {
        final WareHouseRecycle shop = new Shop("Shop");
        final double expectedValue = 37.5d;
        final IRecycleFood chocolate = new Chocolate(null, LocalDate.now().minusDays(50), LocalDate.now().plusDays(5), 50d);

        shop.addFood(chocolate);

        assertThat(chocolate.getPrice(), is(expectedValue));
    }

    /**
     * ExtendedWareHouse class.
     * Method removeFood.
     */
    @Test
    public void removeFoodTest() {
        final WareHouseRecycle warehouse = new ExtendedWareHouse("Extended warehouse");
        final IRecycleFood chocolate = new Chocolate(null, LocalDate.now().minusDays(5), LocalDate.now().plusDays(50), 10);
        final IRecycleFood chocolate2 = new Chocolate(null, LocalDate.now().minusDays(3), LocalDate.now().plusDays(65), 10);
        warehouse.addFood(chocolate);
        warehouse.addFood(chocolate2);
        final int expectedValue = 1;

        warehouse.removeFood(chocolate);

        assertThat(warehouse.getAmountOfFoods(), is(expectedValue));
    }

    /**
     * Trash class.
     * Method validateFood.
     */
    @Test
    public void whenValidFoodThenReturnTrue() {
        final WareHouseRecycle trash = new Trash("Trash");
        final IRecycleFood chocolate = new Chocolate(null, LocalDate.now().minusDays(50), LocalDate.now().plusDays(0), 0d);
        final boolean expectedValue = true;

        assertThat(trash.validatesFood(chocolate), is(expectedValue));
    }

    /**
     * Trash class.
     * Method validateFood.
     */
    @Test
    public void whenInvalidFoodThenReturnFalse() {
        final WareHouseRecycle trash = new Trash("Trash");
        final IRecycleFood chocolate = new Chocolate(null, LocalDate.now().minusDays(50), LocalDate.now().plusDays(10), 0d);
        final boolean expectedValue = false;

        assertThat(trash.validatesFood(chocolate), is(expectedValue));
    }

    /**
     * WarehouseRecycle class.
     * Method getRecycleFoodList.
     */
    @Test
    public void whenCallGetRecycleFoodListThenReturnFoodList() {
        final WareHouseRecycle shop = new Shop("Shop");
        final List<IRecycleFood> expectedList = new ArrayList<>();
        expectedList.add(chocolate);
        expectedList.add(chocolate);

        shop.addFood(chocolate);
        shop.addFood(chocolate);

        assertThat(shop.getRecycleFoodList(), is(expectedList));
    }

    /**
     * ExtendedControlQuality class.
     * Method addStorages.
     */
    @Test
    public void whenCallAddStorageThenAddedStorageToList() {
        final ExtendedControlQuality quality = new ExtendedControlQuality();
        final WareHouseRecycle shop = new Shop("Shop");
        final WareHouseRecycle trash = new Trash("Trash");
        final WareHouseRecycle warehouse = new ExtendedWareHouse("Warehouse");
        final int expectedValue = 3;

        quality.addStorages(shop);
        quality.addStorages(trash);
        quality.addStorages(warehouse);

        assertThat(quality.getAmountOfStorages(), is(expectedValue));
    }

    /**
     * ExtendedControlQuality class.
     * Method removeStorage.
     */
    @Test
    public void whenCallRemoveStorageThenRemovedStorageToList() {
        final ExtendedControlQuality quality = new ExtendedControlQuality();
        final WareHouseRecycle shop = new Shop("Shop");
        final WareHouseRecycle trash = new Trash("Trash");
        final WareHouseRecycle warehouse = new ExtendedWareHouse("Warehouse");
        final int expectedValue = 2;

        quality.addStorages(shop);
        quality.addStorages(trash);
        quality.addStorages(warehouse);

        quality.removeStorage(trash);

        assertThat(quality.getAmountOfStorages(), is(expectedValue));
    }

    /**
     * ExtendedControlQuality class.
     * Method getRecycleStorageList.
     */
    @Test
    public void getListStorageTest() {
        final ExtendedControlQuality quality = new ExtendedControlQuality();
        final WareHouseRecycle shop = new Shop("Shop");
        final List<WareHouseRecycle> expectedList = new ArrayList<>();

        expectedList.add(shop);
        quality.addStorages(shop);

        assertThat(quality.getRecycleStorageList(), is(expectedList));
    }

    /**
     * ExtendedControlQuality class.
     * Method sortRecycleFoods.
     */
    @Test
    public void sortRecycleFoodTest() {
        final ExtendedControlQuality quality = new ExtendedControlQuality();
        final WareHouseRecycle shop = new Shop("Shop");
        final WareHouseRecycle warehouse = new ExtendedWareHouse("Warehouse");
        final IRecycleFood chocolate = new Chocolate(null, LocalDate.now().minusDays(5), LocalDate.now().plusDays(50), 0d);
        final IRecycleFood chocolate2 = new Chocolate(null, LocalDate.now().minusDays(50), LocalDate.now().plusDays(5), 0d);
        final int expectedValue = 1;

        quality.addStorages(shop);
        quality.addStorages(warehouse);

        quality.sortRecycleFoods(chocolate);
        quality.sortRecycleFoods(chocolate2);

        assertThat(shop.getAmountOfFoods(), is(expectedValue));
        assertThat(warehouse.getAmountOfFoods(), is(expectedValue));
    }
}