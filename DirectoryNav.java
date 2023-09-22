import java.util.*;
import java.io.*;

class StringSet {
    private String[] aa;

    File currentDirectory = new File(".");
    File [] dirFiles = currentDirectory.listFiles();
    public StringSet() {
        aa = new String[dirFiles.length];
        for (int i = 0; i < dirFiles.length; i++) {
            if (dirFiles[i].isFile())
                aa[i] = dirFiles[i].getPath();
        }
    }
    public int size() {
        int count=0;
        for (int i = 0; i< aa.length; i++) if (aa[i]!=null) count++;
        return count;
    }
    private int find(String s) {
        if (s==null)
            return -1;
        for (int i = 0; i< aa.length; i++)
            if (s.equals(aa[i]))
                return i;
        return -1;
    }
    public boolean hasMember(String s) {
        return find(s)!=-1;
    }
    public void containsString(String s) {
        for (int i = 0; i < aa.length; i++) {
            if (aa[i] != null && aa[i].contains(s)) {
                System.out.println(aa[i]);
            }
        }
    }
    public int containsStringCount(String s) {
        int count = 0;
        for (int i = 0; i < aa.length; i++) {
            if (aa[i] != null && aa[i].contains(s)) {
                count++;
            }
        }
        return count;
    }
    public void loopThruFile(String a, String s) throws Exception {
        String aWord = "";
        for (int i = 0; i < aa.length; i++) {
            if (aa[i]!= null) {
                Scanner stdin = new Scanner(new File(aa[i]));
                aWord = handleFile(stdin, s);
                if (!aWord.equals("") && aa[i].contains(a)) {
                    System.out.println(aa[i]);
                }
            }

        }
    }
    public String handleFile(Scanner stdin, String s) {
        String theWord = "";
        while (stdin.hasNext()) {
            String word = stdin.next();
            if (word.contains(s))
                theWord = word;
        }
        return theWord;
    }

    public boolean isEmpty() {
        return size()==0;
    }
    public String toString() {
        String s="";
        for (int i = 0; i< aa.length; i++)
            if (aa[i]!=null)
                s+= aa[i]+"\n";
        if (s.length()>0)
            s = s.substring(0,s.length()-1);
        return s;
    }
    public static void main (String [] args) throws Exception {
        StringSet aSet = new StringSet();
        int a = 0;
        if (args.length == 0)
            System.out.println(aSet);
        else if (args.length == 1 && args[0].equals("-c"))
            System.out.println(aSet.size());
        else if (args.length == 2 && args[0].equals("-fc"))
            aSet.containsString(args[1]);
        else if (args.length == 3 && args[0].equals("-c") && args[1].equals("-fc")) {
            a = aSet.containsStringCount(args[2]);
            System.out.println(a);
        }
        else if (args.length > 2 && args[0].equals("-fc") && args[2].equals("-contains")) {
            aSet.loopThruFile(args[1], args[3]);
        }

    }
}
