package pl.macugajakub;

public class Item {
    private String name;
    private float price;

    public Item(String name, float price) {
        this.name = name;
        this.price = price;
        if(price < 0) {
            throw new IllegalArgumentException("Item price must be >=0: " + price);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.price;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return name.equals(item.name) && Math.abs(price - item.price) < 0.000001;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        return result;
    }


}
