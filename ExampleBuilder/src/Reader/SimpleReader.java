package Reader;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Yuri on 09.11.16.
 */
public class SimpleReader implements Serializable {

    private ArrayList<Integer> integers = new ArrayList<>();
    private ArrayList<SimpleMark> marks = new ArrayList<>();

    public SimpleReader(Integer first) {
        integers.add(first);
    }

    public void add(SimpleMark mark, Integer integer) {
        marks.add(mark);
        integers.add(integer);
    }

    public void replace(int index, SimpleReader sr) {
        integers.remove(index);
        integers.add(sr.getInteger(0));
        for (int i = 1; i < sr.size(); i++) {
            marks.add(sr.getMark(i - 1));
            integers.add(sr.getInteger(i));
        }
    }

    public SimpleMark getMark(int index) {
        return marks.get(index);
    }

    public Integer getInteger(int index) {
        return integers.get(index);
    }

    public int size() {
        return integers.size();
    }

    @Override
    public String toString() {
        String res = "";
        res += integers.get(0);
        res += ' ';
        for (int i = 1; i < size(); i++) {
            res += marks.get(i - 1);
            res += " ";
            res += integers.get(i).toString();
            res += " ";
        }
        return res;
    }
}
