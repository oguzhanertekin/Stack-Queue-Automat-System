import java.util.ArrayList;
import java.util.Collections;

public class Queue<E> {
    private ArrayList<E> elements;
    public Queue(){
        this.elements= new ArrayList<E>();
    }
    public void enque(E element){
        elements.add(element);
    }
    public void deque(){
        if(!elements.isEmpty())
            elements.remove(0);
    }

    public void deque(E element){ // this method deletese the used token
        elements.remove(element);
    }

    public void addEnd(E element){elements.add(0,element);}

    public E search(String item){
        for(E element:elements){
            if(element.toString().contains(item)){
                return element;
            }
        }return null;
    }

    public String print(){
        String result ="";
        Collections.reverse(elements);
        for(E element: elements){
            result+=element+"\n";
        }
        return result;
    }

    public ArrayList<E> getElements() {
        return elements;
    }

}

