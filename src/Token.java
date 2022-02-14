public class Token {
    private String id;
    private String name;
    private int number;

    public Token(String id,String name,int number){
        this.id=id;
        this.name=name;
        this.number=number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return id+" "+name+" "+number;
    }
}
