package adapter;

// Target interface 클라이언트 코드에서 사용하는 목표 인터페이스
interface Socket {
    String getOutput();
}

// Adaptee class 기존에 제공되던, 호환되지 않는 인터페이스를 가진 클래스
class EuropeanSocket {
    public String getEuropeanOutput() {
        return "220V";
    }
}

// Adapter class EuropeanSocket을 Socket 인터페이스와 호환되도록 변환
class SocketAdapter implements Socket {
    private EuropeanSocket europeanSocket;

    public SocketAdapter(EuropeanSocket europeanSocket) {
        this.europeanSocket = europeanSocket;
    }
    //socket 인터페이스를 어댑터클래스 내부에서 직접 구현하면서 
    // 기존에 호환안되던 인터페이스인 EuropeanSocket 객체를 사용
    @Override
    public String getOutput() {
        return europeanSocket.getEuropeanOutput();
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        // 유럽 소켓(EuropeanSocket)은 Socket 인터페이스를 구현하지 않으므로, 직접 사용할 수 없음.
        // 대신, SocketAdapter를 통해 EuropeanSocket을 Socket으로 변환
        EuropeanSocket europeanSocket = new EuropeanSocket();// 일단 유럽소켓 준비
        Socket socketAdapter = new SocketAdapter(europeanSocket);// 어댑터를 이용해 유럽소켓을 한국 소켓으로 변환
        System.out.println("Socket output: " + socketAdapter.getOutput());
    }
}