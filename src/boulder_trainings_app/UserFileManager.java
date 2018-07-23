package boulder_trainings_app;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.User;
import boulder_trainings_app.utils.Consts;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabian
 */
public class UserFileManager
{
    private final static Logger LOGGER = Logger.getLogger(UserFileManager.class.getName());

    public static User loadUser()
    {
        User user = null;
        Path path = Paths.get("./data/user/user.data");
        if (!path.toFile().exists())
        {
            path.toFile().getParentFile().mkdirs();
        }
        else
        {
            FileInputStream streamIn;
            ObjectInputStream objectInputStream;
            try
            {
                streamIn = new FileInputStream(path.toFile());
                objectInputStream = new ObjectInputStream(streamIn);
                user = (User) objectInputStream.readObject();
            }
            catch (FileNotFoundException ex)
            {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            catch (IOException | ClassNotFoundException ex)
            {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    public static void saveUser(User user)
    {
        Path path = Paths.get("./data/user/user.data");
        FileOutputStream fout;

        path.toFile().getParentFile().mkdirs();
        try
        {
            fout = new FileOutputStream(path.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(user);
        }
        catch (FileNotFoundException ex)
        {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
}
