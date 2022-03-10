package pl.macugajakub;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Basket basket = new Basket();
        basket.getItems().put(new Item("b",1.0f),1);
        basket.addItem(new Item("a",1.2f));
        basket.addItems(new Item("a",1.2f),2);
        System.out.println(basket);
    }
}
