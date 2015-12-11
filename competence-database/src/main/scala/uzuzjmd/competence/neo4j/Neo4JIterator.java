package uzuzjmd.competence.neo4j;

import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import com.hp.hpl.jena.util.iterator.Map1;
import org.apache.commons.collections.IteratorUtils;

import java.util.*;

/**
 * Created by dehne on 09.12.2015.
 */
public class Neo4JIterator<T> implements ExtendedIterator<T> {

    private final Iterator<T> items;

    public Neo4JIterator(Iterator<T> items) {
        this.items = items;
    }

    @Override
    public T removeNext() {
        T result = items.next();
        items.remove();
        return result;
    }

    @Override
    public <X extends T> ExtendedIterator<T> andThen(Iterator<X> other) {
        return null;
    }

    @Override
    public ExtendedIterator<T> filterKeep(Filter<T> f) {
        List<T> result = new ArrayList<T>();
        List<T> input = toList();
        for (T elem : input
                ) {
            if (f.accept(elem)) {
                result.add(elem);
            }
        }
        return new Neo4JIterator<T>(result.iterator());
    }

    @Override
    public ExtendedIterator<T> filterDrop(Filter<T> f) {
        List<T> result = new ArrayList<T>();
        List<T> input = toList();
        for (T elem : input
                ) {
            if (!f.accept(elem)) {
                result.add(elem);
            }
        }
        return new Neo4JIterator<T>(result.iterator());
    }

    @Override
    public <U> ExtendedIterator<U> mapWith(Map1<T, U> map1) {
        return null;
    }

    @Override
    public List<T> toList() {
        return IteratorUtils.toList(items);
    }

    @Override
    public Set<T> toSet() {
        return new HashSet<T>(toList());
    }

    @Override
    public void close() {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return items.next();
    }

    @Override
    public void remove() {
        items.remove();
    }
}
