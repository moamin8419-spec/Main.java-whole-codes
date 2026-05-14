interface Notification {
    void send(String studentName, String foodName);
}

class EmailNotification implements Notification {

    @Override
    public void send(String studentName, String foodName) {

        System.out.println("Sending EMAIL notification...");
        System.out.println("Dear " + studentName +
                ", your order for " + foodName +
                " has been received.");
    }
}

class SmsNotification implements Notification {

    @Override
    public void send(String studentName, String foodName) {

        System.out.println("Sending SMS notification...");
        System.out.println("Hi " + studentName +
                ", your campus food order is confirmed.");
    }
}

class PushNotification implements Notification {

    @Override
    public void send(String studentName, String foodName) {

        System.out.println("Sending PUSH notification...");
        System.out.println("Your order is being prepared.");
    }
}

class NotificationFactory {

    public static Notification createNotification(String type) {

        if(type.equalsIgnoreCase("EMAIL")) {
            return new EmailNotification();
        }

        else if(type.equalsIgnoreCase("SMS")) {
            return new SmsNotification();
        }

        else if(type.equalsIgnoreCase("PUSH")) {
            return new PushNotification();
        }

        return null;
    }
}

class AppConfig {

    private static AppConfig instance = new AppConfig();

    private String universityName = "Istanbul Aydin University";
    private double deliveryFee = 25.0;

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return instance;
    }

    public String getUniversityName() {
        return universityName;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }
}

public class Main {

    public void placeOrder(String studentName,
                           String foodName,
                           String notificationType) {

        AppConfig config = AppConfig.getInstance();

        System.out.println("Order created for: " + studentName);
        System.out.println("Food: " + foodName);
        System.out.println("University: " + config.getUniversityName());
        System.out.println("Delivery fee: " +
                config.getDeliveryFee() + " TL");

        Notification notification =
                NotificationFactory.createNotification(notificationType);

        if(notification != null) {
            notification.send(studentName, foodName);
        }
        else {
            System.out.println("Unknown notification type.");
        }

        System.out.println("--------------------------------");
    }

    public static void main(String[] args) {

        Main service = new Main();

        service.placeOrder(
                "Ali",
                "Chicken Sandwich",
                "EMAIL"
        );

        service.placeOrder(
                "Zeynep",
                "Vegetarian Pizza",
                "SMS"
        );

        service.placeOrder(
                "Omar",
                "Coffee",
                "PUSH"
        );
    }
}
