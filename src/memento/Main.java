package memento;

import java.util.ArrayList;
import java.util.List;

// Memento class
class TextMemento {
    private String text;

    public TextMemento(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

// Originator class
class TextEditor {
    private String text;

    public void setText(String text) {
        this.text = text;
        System.out.println("Text set to: " + text);
    }

    public String getText() {
        return text;
    }

    public TextMemento saveTextToMemento() {
        return new TextMemento(text);
    }

    public void getTextFromMemento(TextMemento memento) {
        text = memento.getText();
    }
}

// Caretaker class
class TextCaretaker {
    private List<TextMemento> mementoList = new ArrayList<>();

    public void add(TextMemento state) {
        mementoList.add(state);
    }

    public TextMemento get(int index) {
        return mementoList.get(index);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        TextCaretaker caretaker = new TextCaretaker();

        textEditor.setText("Version 1");
        caretaker.add(textEditor.saveTextToMemento());

        textEditor.setText("Version 2");
        caretaker.add(textEditor.saveTextToMemento());

        textEditor.setText("Version 3");
        System.out.println("Current Text: " + textEditor.getText());

        textEditor.getTextFromMemento(caretaker.get(0));
        System.out.println("First saved Text: " + textEditor.getText());
        textEditor.getTextFromMemento(caretaker.get(1));
        System.out.println("Second saved Text: " + textEditor.getText());
    }
}