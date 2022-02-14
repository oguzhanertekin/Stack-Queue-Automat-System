import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Methods {
    public static Queue<Token> tokens= new Queue<Token>();
    public static LinkedHashMap<String,Stack<Item>> items=new LinkedHashMap<>();

    public static List<String> readFile(String fileName) throws IOException { // this method read any file and return
                                                                              // that list line by line in List
        String line;
        File file = new File(fileName);
        FileReader fReader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(fReader);
        List<String> list = new ArrayList();
        while ((line = bReader.readLine()) != null) {
            list.add(line);
        }
        return list;
    }
    // this method sorts queue in priority order
    public static Queue<Token> prioritySort(Queue<Token> tokens) {
        TreeMap<Integer, ArrayList<Token>> priority = new TreeMap<>(Collections.reverseOrder());
        ArrayList<Token> queueList = tokens.getElements();
        for (Token token : queueList) {
            if (!priority.keySet().contains(token.getNumber())) {
                ArrayList<Token> list = new ArrayList<Token>();
                list.add(token);
                priority.put(token.getNumber(), list);
            } else {
                priority.get(token.getNumber()).add(token);
            }
        }
        Queue<Token> tokensCopy = new Queue<Token>();
        for (Integer key : priority.keySet()) {
            for (Token token : priority.get(key)) {
                tokensCopy.enque(token); // enque the token into the tokens queue.
            }
        }
        return tokensCopy;
    }
    public static void addTokens(String tokenFile) throws IOException {  // this method adds tokens to the tokens queue
        for (String line : readFile(tokenFile)) {
            String id = line.split(" ")[0];
            String name = line.split(" ")[1];
            int number = Integer.parseInt(line.split(" ")[2]);
            Token token = new Token(id, name, number);
            tokens.enque(token);

        }
    }
    public static void addItems(String partsFile,String itemsFile) throws IOException {  // this method adds items to the items stack

        for(String part: readFile(partsFile)){
            if(!items.keySet().contains(part)){
                Stack<Item> stack= new Stack<Item>();
                items.put(part,stack);
            }
        }
        for(String line: readFile(itemsFile)){
            String id= line.split(" ")[0];
            String name= line.split(" ")[1];
            Item item= new Item(id,name);
            items.get(name).push(item);  // add item into stack   // items.get(name)= Stack
        }

    }
    public static void tasks(String tasksFile) throws IOException {
        for (String line : readFile(tasksFile)) {
            String task = line.split("\t")[0];
            line = line.substring(4);
            if (task.equals("BUY")) {
                for (String set : line.split("\t")) {
                    String item = set.split(",")[0];
                    int number = Integer.parseInt(set.split(",")[1]);
                    boolean control = true;

                    while (control) {
                        tokens=prioritySort(tokens);
                        Token token = tokens.search(item);
                        if (token.getNumber() > number) {
                            int newNumber = token.getNumber() - number;
                            token.setNumber(newNumber);
                            tokens.deque(token);
                            tokens.enque(token);
                            control = false;

                        } else if (token.getNumber() <= number) {
                            int key = number;
                            while(true){
                                if (token.getNumber() == key) {
                                    tokens.deque(token);
                                    break;
                                } else {
                                    while (token.getNumber() < key) {
                                        key -= token.getNumber();
                                        Token token1 = token;
                                        tokens.deque(token);
                                        token = tokens.search(item);
                                    }
                                }
                            }

                        }
                        for (int i = 0; i < number; i++) {
                            items.get(item).pop();
                        }
                        control=false;
                    }
                }
            } else if (task.equals("PUT")) {
                for (String set : line.split("\t")) {
                    String part = set.split(",")[0];
                    for (int key = 1; key < set.split(",").length; key++) {
                        String id = set.split(",")[key];
                        Item item = new Item(id, part);
                        items.get(part).push(item);
                    }
                }
            }
        }
    }
}
