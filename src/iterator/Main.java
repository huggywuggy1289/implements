package iterator;

// Iterator interface
interface Iterator {
    boolean hasNext();
    Object next();
}

// Aggregate interface
interface Container {
    Iterator getIterator();
}

// Concrete Aggregate
class BookCollection implements Container {
    private String[] books = {"Design Patterns", "Effective Java", "Clean Code", "Refactoring"};

    @Override
    public Iterator getIterator() {
        return new BookIterator();
    }

    // Concrete Iterator
    private class BookIterator implements Iterator {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < books.length;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return books[index++];
            }
            return null;
        }
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        BookCollection bookCollection = new BookCollection();

        Iterator iterator = bookCollection.getIterator();
        while (iterator.hasNext()) {
            String book = (String) iterator.next();
            System.out.println("Book: " + book);
        }
    }
}