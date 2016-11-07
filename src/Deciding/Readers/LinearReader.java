package Deciding.Readers;

import Deciding.Elements.*;
import Deciding.Patterns.Abstraction.ElementPattern;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Yuri on 23.10.16.
 */
public class LinearReader implements Serializable, Iterable<IElement>, Collection<IElement> {

    private ArrayList<IElement> elements;
    private transient int index = 0;

    public LinearReader(ArrayList<IElement> elements) {
        this.elements = elements;
    }


    public boolean hasNextIt(IElement element) {
        if (element instanceof FloatElement) return hasNextFloat();
        if (element instanceof UnknownElement) return hasNextUnknown(((UnknownElement) element).getLetter());
        if (element instanceof MultElement) return hasNextMultiplying();
        if (element instanceof MonomialElement) return hasNextMonomial();
        if (element instanceof BracketsElement) return hasNextBrackets();
        return false;
    }

    public boolean hasNext() {
        return !(index >= size());
    }

    public boolean hasNext(ElementPattern pattern) {
        for (int i = index; i < size(); i++) {
            IElement param = get(i);
            try {
                Class cl = Class.forName(pattern.getClass().getName());
                Constructor con = cl.getConstructor(param.getClass());
                ElementPattern p = (ElementPattern) con.newInstance(param);
                if (p.match()) return true;
            } catch (Exception e) {

            }


        }
        return false;
    }

    public boolean hasNextFloat() {
        for (int i = index; i < size(); i++) {
            if (get(i) instanceof FloatElement) return true;
        }
        return false;
    }

    public boolean hasNextUnknown() {
        for (int i = index; i < size(); i++) {
            if (get(i) instanceof UnknownElement) if (((UnknownElement) get(i)).getExponent() != null) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNextUnknown(char letter) {
        for (int i = index; i < size(); i++) {
            if (get(i) instanceof UnknownElement) {
                if (((UnknownElement) get(i)).getLetter() == letter) return true;
            }
        }

        return false;
    }

    public boolean hasNextMultiplying() {
        for (int i = index; i < size(); i++) {
            if (get(i) instanceof MultElement) return true;
        }
        return false;
    }

    public boolean hasNextMonomial() {
        for (int i = index; i < size(); i++) {
            if (get(i) instanceof MonomialElement) return true;
        }
        return false;
    }

    public boolean hasNextBrackets() {
        for (int i = index; i < size(); i++) {
            if (get(i) instanceof BracketsElement) return true;
        }
        return false;
    }

    public boolean hasNextSimiliarMonomial(MonomialElement example) {
        for (int i = index; i < size(); i++) {
            if (get(i) instanceof MonomialElement) {
                if (((MonomialElement) get(i)).isSimilarMonomial(example)) return true;
            }
        }

        return false;
    }

    public boolean hasNextExponentable() {
        for (int i = index; i < size(); i++) {
            if (get(i) instanceof Exponentable) if (((Exponentable) get(i)).getExponent() != null) {
                return true;
            }
        }
        return false;
    }

    public IElement getNext() {
        IElement res = get(index);
        index++;
        return res;
    }

    public IElement getNext(ElementPattern pattern) {
        if (!hasNext(pattern)) return null;

        for (int i = index; i < size(); i++) {
            IElement param = get(i);
            index++;
            try {
                Class cl = Class.forName(pattern.getClass().getName());
                Constructor con = cl.getConstructor(param.getClass());
                ElementPattern p = (ElementPattern) con.newInstance(param);
                if (p.match()) return param;
            } catch (Exception e) {

            }


        }
        return null;
    }

    public Exponentable getNextExponentable() {
        if (!hasNextExponentable()) return null;

        for (int i = index; i < size(); i++) {
            index++;
            if (get(i) instanceof Exponentable) if (((Exponentable) get(i)).getExponent() != null) {
                return (Exponentable) get(i);
            }
        }
        return null;
    }

    public FloatElement getNextFloatElement() {
        if (!hasNextFloat()) return null;

        for (int i = index; i < size(); i++) {
            index++;
            if (get(i) instanceof FloatElement) return (FloatElement) get(i);
        }
        return null;
    }

    public UnknownElement getNextUnknownElement(char letter) {
        if (!hasNextUnknown(letter)) return null;

        for (int i = index; i < size(); i++) {
            index++;
            if (get(i) instanceof UnknownElement) {

                if (((UnknownElement) get(i)).getLetter() == letter) return (UnknownElement) get(i);
            }
        }

        return null;
    }

    public UnknownElement getNextUnknownElement() {
        if (!hasNextUnknown()) return null;

        for (int i = index; i < size(); i++) {
            index++;
            if (get(i) instanceof UnknownElement) {

                return (UnknownElement) get(i);
            }
        }

        return null;
    }

    public MonomialElement getNextSimiliarMonomial(MonomialElement example) {
        if (!hasNextSimiliarMonomial(example)) return null;

        for (int i = index; i < size(); i++) {
            index++;
            if (get(i) instanceof MonomialElement) {

                if (((MonomialElement) get(i)).isSimilarMonomial(example)) return (MonomialElement) get(i);
            }
        }

        return null;
    }

    public MultElement getNextMultElement() {
        if (!hasNextMultiplying()) return null;

        for (int i = index; i < size(); i++) {
            index++;
            if (get(i) instanceof MultElement) return (MultElement) get(i);
        }

        return null;
    }

    public MonomialElement getNextMonomial() {
        if (!hasNextMonomial()) return null;

        for (int i = index; i < size(); i++) {
            index++;
            if (get(i) instanceof MonomialElement) return (MonomialElement) get(i);
        }

        return null;
    }

    public BracketsElement getNextBrackets() {
        if (!hasNextBrackets()) return null;

        for (int i = index; i < size(); i++) {
            index++;
            if (get(i) instanceof BracketsElement) return (BracketsElement) get(i);
        }

        return null;
    }

    public int getIndex() {
        return index - 1;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setIndex(IElement element) {
        this.index = elements.indexOf(element);
    }

    public void reset() {
        index = 0;
    }

    public ArrayList<IElement> getElements() {
        return elements;
    }

    public IElement get(int index) {
        return elements.get(index);
    }

    public void set(int index, IElement element) {
        elements.set(index, element);
    }

    public void remove(int index) {
        elements.remove(index);
        if (size() == 0) add(new FloatElement(0));
    }

    public void addAll(int index, Collection<IElement> c) {
        elements.addAll(index, c);
    }

    public int indexOf(IElement element) {
        return elements.indexOf(element);
    }

    @Override
    public int size() {
        if (!(elements.size() == 1)) return elements.size();
        else if (get(0).equals(new FloatElement(0))) return 0;
        else return 1;

    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return elements.contains(o);
    }

    @Override
    public Iterator<IElement> iterator() {
        return elements.iterator();
    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return elements.toArray(a);
    }

    @Override
    public boolean add(IElement element) {
        return elements.add(element);
    }

    @Override
    public boolean remove(Object o) {
        return elements.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return elements.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends IElement> c) {
        return elements.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return elements.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return elements.retainAll(c);
    }

    @Override
    public void clear() {
        elements.clear();
        add(new FloatElement(0));
    }


}
