import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int num1=0;
        int num2=0;
        int sum=0;
        boolean dont=false;
        boolean DO=false;
        ArrayList<String> allMatches =getFileData("src/file");
        ArrayList<String> muls=new ArrayList<>();
        for(int i = 0; i<allMatches.size();i++){
            String searchString = allMatches.get(i);
            String regex = "mul\\([0-9]{1,3},[0-9]{1,3}\\)";
            String hi="don't\\(\\)";
            String hello="do\\(\\)";
            Matcher m = Pattern.compile(regex).matcher(searchString);
            Matcher h = Pattern.compile(hi).matcher(searchString);
            Matcher he = Pattern.compile(hello).matcher(searchString);
            //implement this while loop to find it in order
            while (m.find()) {
                muls.add(m.group(0));
                if(h.find()){
                    muls.add(h.group());
                }else if(he.find()){
                    muls.add(he.group());
                }
            }

        }
        System.out.println(muls);
        for(int e = 0; e<muls.size();e++){
            if(muls.get(e).equals("don't()")){
                dont=true;
                DO=false;
            }else if(muls.get(e).equals("do()")){
                DO=true;
                dont=false;
            }else {
                if(DO) {
                    num1 = Integer.parseInt(muls.get(e).substring(4, muls.get(e).indexOf(",")));
                    num2 = Integer.parseInt(muls.get(e).substring(muls.get(e).indexOf(",") + 1, muls.get(e).length() - 1));
                    sum += num1 * num2;
                }
            }
        }
        System.out.println(sum);
    }
    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}