public class Item {
    private String id;
    private String name;

    public Item(String id,String name){
        this.id=id;
        this.name=name;

    }

    @Override
    public String toString() {
        return id;
    }
}
