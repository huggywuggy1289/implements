package iterator;

// Iterator interface
interface Iterator {
    boolean hasNext(); // 다음요속 있는지 확인
    Object next(); // 다음요소를 반환
}

// Aggregate interface : 컬렉션에 대한 반복자를 반환하는 메서드를 정의
interface Container {
    Iterator getIterator();
}

// Concrete Aggregate
class BookCollection implements Container {
    // 이 배열은 private 접근 제한자를 사용하여 외부 클래스에서 직접 접근할 수 없다.
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
        BookCollection bookCollection = new BookCollection(); // BookCollection 객체 생성 후

        Iterator iterator = bookCollection.getIterator(); // BookCollection 객체에 Iterator를 사용하여 BookIterator 객체를 생성
        // 외부 클래스는 books 배열에 직접 접근하지 않고, getIterator() 메서드를 통해서만 접근
        while (iterator.hasNext()) {
            String book = (String) iterator.next();
            System.out.println("Book: " + book);
        }
    }
}