package memento;

import java.util.ArrayList;
import java.util.List;

// Memento class
// 객체의 상태를 저장합니다. 이 클래스는 상태를 캡처하고, 상태를 반환하는 메서드를 제공합니다.
class TextMemento {
    private String text;

    public TextMemento(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

// Originator class: 클래스는 상태를 생성하고, Memento 객체에 상태를 저장하거나 복원하는 역할
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

// Caretaker class : 이 클래스는 Memento 객체를 저장하고, 필요한 Memento 객체를 반환
class TextCaretaker { // 연산수행은 xx
    private List<TextMemento> mementoList = new ArrayList<>();

    public void add(TextMemento state) {
        mementoList.add(state);
    }

    public TextMemento get(int index) {
        return mementoList.get(index); // 지정된 인덱스의 Memento 객체를 반환
    }
}

// Client code : TextEditor와 TextCaretaker 객체를 생성
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