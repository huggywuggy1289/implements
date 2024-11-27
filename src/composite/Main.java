package composite;

import java.util.ArrayList;
import java.util.List;

// Component interface
interface FileSystemComponent {
    void showDetails();
}

// Leaf class
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}

// Composite class
class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");

        Directory dir1 = new Directory("dir1");
        dir1.addComponent(file1);

        Directory dir2 = new Directory("dir2");
        dir2.addComponent(file2);
        dir2.addComponent(dir1);

        dir2.showDetails();
    }
}