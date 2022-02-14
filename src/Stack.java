import java.util.ArrayList;
import java.util.Collections;

public class Stack<E>{
    private ArrayList<E> elements;
    public Stack(){
        this.elements= new ArrayList<E>();
    }
    public void push(E element){
        elements.add(0,element);
    }
    public void pop(){
        if(!elements.isEmpty())
            elements.remove(0);
    }
    public String print(){
        String result ="";
        for(E element: elements){
            result+=element+"\n";
        }
        return result;
    }
}
