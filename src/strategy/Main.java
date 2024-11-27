package strategy;

// Strategy interface : 모든 지원 알고리즘에 대한 공통 인터페이스 선언
interface PaymentStrategy {
    void pay(int amount);
}

// Concrete Strategy 1
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card: " + cardNumber);
    }
}

// Concrete Strategy 2
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal: " + email);
    }
}

// Concrete Strategy 3
class BitcoinPayment implements PaymentStrategy {
    private String walletAddress;

    public BitcoinPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Bitcoin: " + walletAddress);
    }
}

// Context class 현재 상태가 무엇이냐에 따라 다른일을 한다.
class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy; // 이게 각각의 state에 따라 다른 상태를 보여줌.
    }

    public void checkout(int amount) {
        paymentStrategy.pay(amount);// 이게 각각의 state에 따라 다른 상태를 보여줌.
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart(); //context클래스 소환

        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
        cart.checkout(100);

        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(200);

        cart.setPaymentStrategy(new BitcoinPayment("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"));
        cart.checkout(300);
    }
}