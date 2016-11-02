package Utilities;

import java.io.*;

/**
 * Created by Yuri on 27.10.16.
 */
public class CloneMachine {


    public static Object cloneObject(Serializable target) {
        File file;
        ObjectOutputStream out;
        ObjectInputStream in;
        try {
            file = File.createTempFile("clones", "txt");
            out = new ObjectOutputStream(new FileOutputStream(file));
            in = new ObjectInputStream(new FileInputStream(file));

            out.writeObject(target);
            out.flush();
            Object res = in.readObject();

            out.close();
            in.close();
            return res;
        }
        catch (IOException e) {} catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
}
