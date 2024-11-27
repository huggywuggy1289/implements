package proxy;

// Subject interface
interface Image {
    void display();
}

// RealSubject class: 실제 객체이미지 파일 로드하고 표시하는 역할
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }
}

// Proxy class
class ProxyImage implements Image {
    private RealImage realImage; // 실제 객체에 대한 참조
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) { // real 이미지 객체가 생성되지 않은경우
            realImage = new RealImage(fileName); //  직접 이미지객체 생성
        }
        realImage.display();  // 로드까지
    }
}

// Client code
// ProxyImage 객체를 생성하고, 이미지를 표시합니다.
//프록시 패턴 덕분에 클라이언트는 실제 객체 (RealImage)를 직접 생성하거나 관리할 필요 없이, 프록시 객체 (ProxyImage)를 통해 간접적으로 접근
public class Main {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        // Image will be loaded from disk
        image1.display();
        System.out.println("");

        // Image will not be loaded from disk
        image1.display();
        System.out.println("");

        // Image will be loaded from disk
        image2.display();
        System.out.println("");

        // Image will not be loaded from disk
        image2.display();
    }
}