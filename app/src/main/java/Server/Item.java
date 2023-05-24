package Server;

public class Item {
    private String name;
    private Integer count;

    public Item() {}

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void useItem(Integer num) {
        this.count -= num;
    }

}
