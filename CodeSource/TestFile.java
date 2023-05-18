import java.io.File;
import java.io.RandomAccessFile;
class point{

}

public class TestFile {
    public static void main(String[] args) {
        RandomAccessFile monFichier;
        
        try {
        monFichier = new RandomAccessFile("monfichier.dat", "rw");
        for (int i = 0; i < 10; i++) monFichier.writeInt(i);
        }
        catch (Exception e) {e.printStackTrace();}
        
        try {
        monFichier = new RandomAccessFile("monfichier.dat", "rw");
        System.out.println("les éléments du fichier sont:");
        for (int i = 0; i < 10; i++)
        {System.out.println(monFichier.readInt());}
        monFichier.close();
        }
        catch (Exception e) { e.printStackTrace(); }}}