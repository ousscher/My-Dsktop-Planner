import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


class Animal implements Comparable{
    private String nom;
    private Tatouage tatouage;
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
    public void setTatouage(Tatouage tatouage){
        this.tatouage = tatouage;
    }
    public Tatouage getTatouage (){
        return this.tatouage;
    }
}
class Tatouage implements Comparable{
    private String nom;
    private int identifiant;
    static public int id;
    public String getNom (){
        return this.nom;
    }
    public int getIdentifiant (){
        return this.identifiant;
    }
    public Tatouage(String nom){
        this.nom = nom;
        this.identifiant = id;
        id++;
    }
    public boolean equals(Object obj) {
if (this == obj)
return true;
if (obj == null)
return false;
if (getClass() != obj.getClass())
return false;
Tatouage other = (Tatouage) obj;
if (identifiant!=other.getIdentifiant()) return false;
return true;
}
public int compareTo(Object obj){
    return this.nom.compareTo(((Tatouage)obj).getNom());
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
        System.out.println("----------------------------------------------");

        Map <Tatouage , Animal> AnimeauxTatouage= new HashMap<Tatouage , Animal>(); 
        Map <Tatouage , Animal> animeauxTatouageTreeMap= new TreeMap<Tatouage , Animal>(); 
        Tatouage tatouage,tatouage2, tatouage3;
        Animal animal;
        tatouage=new Tatouage("t2");
        animal = new Animal("animal1");
        animal.setTatouage(tatouage);
        AnimeauxTatouage.put(tatouage, animal);
        animeauxTatouageTreeMap.put(tatouage, animal);


        animal = new Animal("animal2");
        tatouage2 = new Tatouage("null");
        animal.setTatouage(tatouage2);
        AnimeauxTatouage.put(animal.getTatouage(), animal);
        animeauxTatouageTreeMap.put(tatouage, animal);

        Iterator<Entry<Tatouage,Animal>> itmap =AnimeauxTatouage.entrySet().iterator();
        Entry<Tatouage,Animal> entry;
        while (itmap.hasNext()){
        entry=itmap.next();
        System.out.println(entry.getKey().getIdentifiant()+" "+entry.getValue().getNom());}
        
    }
}
