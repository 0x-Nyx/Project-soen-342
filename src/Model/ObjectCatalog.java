import java.util.ArrayList;
import java.util.List;

public class ObjectCatalog {

    private List<Object> objects = new ArrayList<>();

    public void addObject(Object object) {
        objects.add(object);
    }

    public void displayAllObjects() {
        if (objects.isEmpty()) {
            System.out.println("There are no objects in the catalog.");
        } else {
            System.out.println("\n------------------------- Catalog ------------------------ ");
            System.out.println("\n ID   | Name                 | Type       | Owned by | Auctioned");
            System.out.println("-------------------------------------------------------------");
            for (int i = 0; i < objects.size(); i++) {
                Object obj = objects.get(i);
                System.out.println(obj);
            }
        }
    }

    public List<Object> getObjects() {
        return objects;
    }
}