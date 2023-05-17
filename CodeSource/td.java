import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.List;


class Animal implements Comparable{
    private String nom;
    public Animal (String nom){
        this.nom = nom;
    }
    public String getNom(){return this.nom;}
    // public boolean equals (Animal animal){
    //     return (animal.getNom().equals(this.nom));
    // }
    public boolean equals (Object o){
        return ((Animal)o).getNom().equals(this.nom);
    }
    public int hashCode(){
        return this.nom.hashCode();
    }
    public int  compareTo(Object c){
        return this.nom.compareTo(((Animal)c).getNom());
    } 
}

public class td {
    
    public static void main(String[] args) {
        Animal a1 = new Animal("chien");
        Animal a2 = new Animal("chat");
        Animal a3 = new Animal("lion");
        Animal a4 = new Animal("giraffe");
        Animal a5 = new Animal("giraffe");
        Set<Animal> eAnimals = new HashSet<Animal>();
        eAnimals.add(a1);
        eAnimals.add(a2);
        eAnimals.add(a3);
        eAnimals.add(a4);
        eAnimals.add(a5);
        // Iterator<Animal> it = eAnimals.iterator();
        // while (it.hasNext()){
        //     System.out.println(it.next().getNom());
        // }
        List<Animal> list = new ArrayList<Animal>(eAnimals);
        list.add(a5);
        list.add(a5);
        Iterator it2 = list.iterator();
        while(it2.hasNext()){
            System.out.println(((Animal)it2.next()).getNom());
        }
        Collections.sort(list);
        System.out.println("------------------");
        it2 = list.iterator();
        while(it2.hasNext()){
            System.out.println(((Animal)it2.next()).getNom());
        }
        
    }
}
