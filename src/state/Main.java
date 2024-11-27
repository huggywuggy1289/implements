package state;

// State interface: 상태를 대표하는 부모 클래스
interface State {
    void write(DocumentContext context, String text);
}

// Concrete State 1 구체적인 상태구현
class EditingState implements State {
    @Override
    public void write(DocumentContext context, String text) {
        System.out.println("Editing: " + text);
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Editing State";
    }
}

// Concrete State 2 구체적인 상태구현 
class ReadOnlyState implements State {
    @Override
    public void write(DocumentContext context, String text) {
        System.out.println("Cannot write in read-only mode.");
    }

    @Override
    public String toString() {
        return "Read-Only State";
    }
}

// Context class 클라이언트의 요청을 처리하기 위한 인터페이스
class DocumentContext {
    private State state; // static이 없으므로 클래스 변수이다.

    public DocumentContext() {
        state = new EditingState(); // 생성자에 현제 상태를 Editing state로 지정
        // state는 인스턴스 변수 즉 클래스 레벨에서 선언된 변수이며, 객체의 상태를 저장하므로 지역변수선언을 하면 안된다.
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void write(String text) {
        state.write(this, text);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        DocumentContext document = new DocumentContext();

        document.write("Hello, world!");

        document.setState(new ReadOnlyState());
        document.write("This should not be written.");

        document.setState(new EditingState());
        document.write("Editing again.");
    }
}