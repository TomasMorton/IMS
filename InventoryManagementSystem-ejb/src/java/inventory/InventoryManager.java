package inventory;




import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Class to handle RESTful HTTP requests containing a JSON encoded String.
 * <br>
 * This can be tested at localhost:8080/RestStockServer/test-resbeans.html
 * @author Tomas
 */
@ManagedBean
@Path("/manager")
public class InventoryManager
{

    
    @EJB 
    private DatabaseBean dbManager;
    @DefaultValue("") @QueryParam("json") 
    private String jsonString;
    
    
    /**
     * Returns a list of names of all items in the 
     * database.
     * @return 
     */
    @GET
    @Produces("text/plain")
    public String getInventory()
    {
        System.err.println("DBManager = " + (dbManager == null));
        Inventory inventory = dbManager.getInventory();
        return inventory.convertItemArrayToJSON();
    }


    /**
     * Get the name, price and quantity of an item provided
     * as a <code>name</code> query parameter. <br>
     * If no name parameter is provided, a list of the entire
     * database is returned.
     * @return 
     */
    @GET
    @Path("/get")
    @Produces("text/plain")
    public String getItem()
    {
        Inventory result = new Inventory();  
        //Check for parameters
        if (!jsonString.isEmpty())
        {
            //Get the item name to lookup
            InventoryItem item = new Inventory(jsonString).getFirstItem();
            String itemName = item.getName();
            if (dbManager.itemExists(itemName))
            {
                //Lookup and return the item
                result.addItem(dbManager.getItem(itemName));
            } else
            {
                return "Item '" + itemName + "' does not exist.";
            }
        } else
        {
            //No item name provided
            return getInventory();
        }
        return result.convertItemArrayToJSON();
    }
    
    /**
     * Returns a list of names of all items in
     * the database.
     * @return 
     */
    private String[] getItemNames()
    {
        return dbManager.getItemNames();
    }
    
    /**
     * Adds a new item to the database.  Providing price and
     * quantity parameters are optional, and will be set to 0
     * if they do not exist.
     * @return
     */
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addItem(Inventory inventory)
    {        
        Inventory result = new Inventory();  
        //Check for parameters
        if (inventory == null )//!jsonString.isEmpty())
        {
            //Get the item name to lookup
//            InventoryItem item = new Inventory(jsonString).getFirstItem();
            InventoryItem item = inventory.getFirstItem();
            String itemName = item.getName();
            if (!dbManager.itemExists(itemName))
            {
                dbManager.addItem(item);
            } else
            {
                updateItem();
            }
        } else
        {
            //No item name provided
            return "Please provide an item name as a parameter.";
        }
        return result.convertItemArrayToJSON();
    }

    @POST
    @Path("/update")
    @Produces("text/plain")
    public String updateItem()
    {
        InventoryItem item = new Inventory(jsonString).getFirstItem();
        return dbManager.updateItem(item);
    }
    
    @POST
    @Path("/add")
    @Produces("text/plain")
    public String addToItem()
    {
        InventoryItem item = new Inventory(jsonString).getFirstItem();
        return dbManager.addToItem(item);     
    }
    
    
    @POST
    @Path("/remove")
    @Produces("text/plain")
    public String removeFromItem()
    {
        InventoryItem item = new Inventory(jsonString).getFirstItem();
        return dbManager.addToItem(item.getName(), 
                (int) -(item.getPrice() *100),
                -item.getQuantity());     
    }    
    
    
}