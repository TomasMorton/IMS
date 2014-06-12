package inventory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import javax.ejb.Local;

/**
 *
 * @author Tomas
 */
@Local
public interface InventoryAnalyzer
{
    public String getTest();
}
