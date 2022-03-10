package pl.macugajakub;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class BasketTest {
    private static Item item;
    private static Basket basket;

    @BeforeAll
    static void init() {
        basket = new Basket();
        item = new Item("apple", 1.00f);
    }

    @Test
    void getEmptyMapOfItemsWhenBasketIsEmpty() {
        assertTrue(basket.getItems().isEmpty());
    }

    @Test
    void getAmountOfZeroWhenItemIsNotInBasket() {
        int expectedAmount = 0;
        int actualAmount = basket.getItemAmount(item);
        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    public void addOneItemToBasket() {
        basket.addItem(item);
        assertEquals(1, (int) basket.getItems().get(item));
    }

    @Test
    void getMapOfItemsWhenBasketHasSomething() {
        basket.addItem(item);
        Map<Item, Integer> expectedMap = new HashMap<>();
        expectedMap.put(item, 1);
        Map<Item, Integer> actualMap = basket.getItems();
        assertEquals(expectedMap, actualMap);
    }


    @Test
    public void addTwoSameItemsToBasket() {
        basket.addItem(item);
        basket.addItem(item);
        assertEquals(1, basket.getItems().size());
        assertEquals(2, (int) basket.getItems().get(item));
    }

    @Test
    public void shouldNotRemoveItemNotInBasket() {
        assertFalse(basket.removeItem(new Item("pear", 0.9f)));
    }

    @Test
    public void shouldRemoveOneItemWhenThereIsMoreThanOne() {
        basket.addItem(item);
        basket.addItem(item);
        assertTrue(basket.removeItem(item));
        int expectedAmount = 1;
        int givenAmount = basket.getItems().get(item);
        assertEquals(expectedAmount, givenAmount);
    }

    @Test
    void shouldDeleteItemFromBasketIfOnlyOneExist() {
        basket.addItem(item);
        assertTrue(basket.removeItem(item));
        assertFalse(basket.getItems().containsKey(item));
    }

    @Test
    void shouldThrowExceptionWhenAddingIllegalAmount() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> basket.addItems(item, 0)
        );
        assertEquals("Quantity of items must be >= 1: 0", exception.getMessage());
    }

    @Test
    void shouldAddNewItemWithAmount() {
        basket.addItems(item, 2);
        int expectedAmount = 2;
        int actualAmount = basket.getItemAmount(item);
        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void shouldIncreaseAmountOfExistingItem() {
        basket.addItem(item);
        assertEquals(1, basket.getItemAmount(item));
        basket.addItems(item, 3);
        assertEquals(4, basket.getItemAmount(item));
    }

    @Test
    void shouldNotRemoveItemWhichIsNotInBasket() {
        assertFalse(basket.removeItems(item, 1));
    }

    @Test
    void shouldRemoveItemWhenAmountExceedActualQuantity() {
        basket.addItems(item, 1);
        basket.removeItems(item, 2);
        assertFalse(basket.getItems().containsKey(item));
    }

    @Test
    void shouldDecreaseItemAmountByGivenValue() {
        basket.addItems(item, 2);
        basket.removeItems(item, 1);
        int expectedAmount = 1;
        int actualAmount = basket.getItemAmount(item);
        assertEquals(expectedAmount,actualAmount);

    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenGivenAmountIsLessThanOne() {
        basket.addItem(item);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> basket.removeItems(item, 0));
        assertEquals("Quantity must be greater than 1: 0",exception.getMessage());
    }

    @Test
    void shouldReturnZeroCostWhenBasketIsEmpty() {
        double expectedCost = 0;
        double actualCost = basket.costOfBasket();
        assertEquals(expectedCost, actualCost);
    }

    @Test
    void shouldReturnTotalCostWhenItemsAreInBasket() {
        basket.addItems(item,2);
        basket.addItems(new Item("pear",2.0f),2);
        double expectedCost = 6;
        double actualCost = basket.costOfBasket();
        assertEquals(expectedCost, actualCost);
    }
}

