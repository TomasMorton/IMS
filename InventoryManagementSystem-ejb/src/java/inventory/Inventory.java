package inventory;



import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author markburton
 */
@XmlRootElement
public class Inventory {

    @XmlElement private List<InventoryItem> itemArrayList;
    @XmlElement private List<String> itemNameList;

    public Inventory(ArrayList itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    public Inventory() {
        this.itemArrayList = new ArrayList<>();
    }

    public Inventory(String json) {
        this.equals(new Gson().fromJson(json, Inventory.class));
    }

    public String convertItemArrayToJSON() {
        return new Gson().toJson(this);
    }

    public void createItemNameList() {
        itemNameList = new ArrayList<>();
//        itemArrayList.stream().forEach((item) -> {
//            itemNameList.add(item.getName());
//        });
    }

    public InventoryItem getItem(String name) {
        for (InventoryItem item : itemArrayList) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
    
    public InventoryItem getFirstItem()
    {
        return itemArrayList.get(0);
    }

    public double getItemPrice(String name) {
        InventoryItem item = getItem(name);
        if (item != null) {
            return item.getPrice();
        }
        return -1;
    }

    public int getItemQuantity(String name) {
        InventoryItem item = getItem(name);
        if (item != null) {
            return item.getQuantity();
        }
        return -1;
    }

    public void addItem(InventoryItem item) {
        itemArrayList.add(item);
    }

    public void removeItem(InventoryItem item) {
        itemArrayList.remove(item);
    }

}
