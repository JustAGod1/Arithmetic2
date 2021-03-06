package Parsing.Containers;

import Deciding.Elements.IElement;
import Deciding.Elements.Index;
import Deciding.Elements.RootElement;

/**
 * Created by Yuri on 23.10.16.
 */
public class RootContainer implements Container {

    Container index;
    Container base;

    public RootContainer(Container index, Container base) {

        this.index = index;
        this.base = base;
    }

    @Override
    public IElement toElement() {
        return new RootElement(base.toElement(), (Index) index.toElement());
    }
}
