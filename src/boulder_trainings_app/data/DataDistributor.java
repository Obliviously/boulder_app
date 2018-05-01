/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.data;

/**
 * This class distributes the access to the data.
 *
 * @author fabian
 */
public class DataDistributor
{
    private static DataDistributor instance;

    private DataDistributor()
    {
    }

    public static DataDistributor getInstance()
    {
        if (DataDistributor.instance == null)
        {
            DataDistributor.instance = new DataDistributor();
        }
        return DataDistributor.instance;
    }
}
