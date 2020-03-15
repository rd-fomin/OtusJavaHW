package remifo;

import java.util.*;

public class DIYArrayList<T> implements List<T> {
    private final int DEFAULT_CAPACITY = 10;
    private Object[] array = new Object[DEFAULT_CAPACITY];
    private int size = 0;

    public DIYArrayList() { }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.array, this.size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        a = (T1[]) Arrays.copyOf(this.array, this.size);
        return a;
    }

    @Override
    public boolean add(T t) {
        add(this.size, t);
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index >= 0 && index <= this.size) {
            if (this.array.length >= this.size)
                this.size++;
                grow(this.size);
            if (index != size - 1)
                System.arraycopy(this.array, index, this.array, index + 1, size - 1 - index);
            this.array[index] = element;
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = this.array.length;
        if (oldCapacity > 0) {
            int newCapacity = Math.max(minCapacity, oldCapacity << 1);
            this.array = Arrays.copyOf(this.array, newCapacity);
        }
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (this.array[i].equals(o)) {
                System.arraycopy(this.array, i + 1, this.array, i, --this.size - i);
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < this.size) {
            T t = (T) array[index];
            System.arraycopy(this.array, index + 1, this.array, index, --this.size - index);
            return t;
        } else throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] objects = c.toArray();
        int newsize = objects.length;
        this.array = Arrays.copyOf(this.array, this.size + newsize);
        System.arraycopy(objects, 0, this.array, this.size, newsize);
        this.size += newsize;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index <= this.size) {
            Object[] objects = c.toArray();
            int newSize = objects.length;
            grow(this.size += newSize);
            System.arraycopy(this.array, index, this.array, index + newSize, this.size - newSize - index);
            System.arraycopy(objects, 0, this.array, index, newSize);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        this.array = new Object[0];
        this.size = 0;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < this.size) {
            return (T) this.array[index];
        } else throw new IndexOutOfBoundsException();
    }

    @Override
    public T set(int index, T element) {
        if (index >= 0 && index < this.size) {
            this.array[index] = element;
            return element;
        } else throw new IndexOutOfBoundsException();
    }

    @Override
    public int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < this.size; i++) if (o.equals(this.array[i])) return i;
        } else {
            for (int i = 0; i < this.size; i++) if (this.array[i] == null) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o != null) {
            for (int i = this.size - 1; i >= 0; i--) if (o.equals(this.array[i])) return i;
        } else {
            for (int i = this.size - 1; i >= 0; i--) if (this.array[i] == null) return i;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListItr(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        DIYArrayList<T> diyArrayList = new DIYArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            diyArrayList.add((T) this.array[i]);
        }
        return diyArrayList;
    }

    private class Itr implements Iterator<T> {
        int position = 0;

        Itr() {  }

        @Override
        public boolean hasNext() {
            return position != DIYArrayList.this.size;
        }

        @Override
        public T next() {
            int i = position;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = DIYArrayList.this.array;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            position = i + 1;
            return (T) elementData[i];
        }

        @Override
        public void remove() {
            try {
                DIYArrayList.this.remove(position);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class ListItr extends Itr implements ListIterator<T> {
        ListItr(int index) {
            super();
            this.position = index;
        }

        @Override
        public boolean hasPrevious() {
            return this.position != 0;
        }

        @Override
        public T previous() {
            int i = position - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] elementData = DIYArrayList.this.array;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            position = i;
            return (T) elementData[i];
        }

        @Override
        public int nextIndex() {
            return this.position;
        }

        @Override
        public int previousIndex() {
            return this.position - 1;
        }

        @Override
        public void set(T t) {
            try {
                DIYArrayList.this.set(position, t);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(T t) {
            try {
                DIYArrayList.this.add(position, t);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override
    public void sort(Comparator<? super T> c) {
        Arrays.sort(Arrays.copyOf((T[]) this.array, this.size), c);
    }

}
