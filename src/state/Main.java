package state;

// State interface
interface State {
    void write(DocumentContext context, String text);
}

// Concrete State 1
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

// Concrete State 2
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

// Context class
class DocumentContext {
    private State state;

    public DocumentContext() {
        state = new EditingState(); // Default state
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