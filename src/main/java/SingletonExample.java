class ShowMessage
{
    private static ShowMessage singletonPrinter = new ShowMessage();

    private ShowMessage(){}

    public static  ShowMessage getInstance(){
        return singletonPrinter;
    }

    protected static void showData(String data){
        System.out.println(data);
    }
}


public class SingletonExample {
    public static void main(String[] args) {
        ShowMessage showMessage = ShowMessage.getInstance();
        showMessage.showData("Message is showing from ShowMessage Singleton!");
    }
}
