import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String partsFile= args[0];
        String itemsFile=args[1];
        String tokensFile=args[2];
        String tasksFile=args[3];
        String outputFile=args[4];
        Methods.addItems(partsFile,itemsFile);
        Methods.addTokens(tokensFile);
        Methods.tasks(tasksFile);

        FileWriter fileWriter= new FileWriter(outputFile,false);
        BufferedWriter bWriter= new BufferedWriter(fileWriter);

        for(String part: Methods.items.keySet()){
            bWriter.write(part+":\n");
            bWriter.write(Methods.items.get(part).print());
            bWriter.write("---------------\n");
        }
        bWriter.write("Token Box:\n");

        bWriter.write(Methods.prioritySort(Methods.tokens).print());
        bWriter.close();
    }
}
