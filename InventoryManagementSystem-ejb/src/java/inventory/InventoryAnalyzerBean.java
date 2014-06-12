package inventory;





import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Tomas
 */
@ManagedBean
@Path("/analyzer")
public class InventoryAnalyzerBean implements InventoryAnalyzer
{
    @EJB
    private DatabaseBean dbManager;
    
    @GET
    @Produces("text/plain")
    public String getTest()
    {
        return "DBManager is null: " + (dbManager == null);
    }
    
    @PostConstruct
    public void doStuff()
    {
        System.out.println("Null at construct? " + (dbManager == null));
        
    }
}
