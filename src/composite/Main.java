package composite;

import java.util.ArrayList;
import java.util.List;
// 객체를 트리구조로 구성
// Component interface : leaf와 composite를 동일하게 다룰수 있게 해주는 부모 클래스
interface FileSystemComponent {
    void showDetails();
}

// Leaf class : 다른 객체를 포함할 수 없는 클래스
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

// Composite class : 다른 객체를 포함할 수 있는 클래스 - 복합체
class Directory implements FileSystemComponent {
    private String name;
    // 디렉토리 내부에 포함된 파일이나 하위 디렉토리를 저장하는 리스트
    private List<FileSystemComponent> components = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("Directory: " + name);
        // 재귀 호출
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