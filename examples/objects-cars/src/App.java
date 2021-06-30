public class App {
    public static void main(String[] args) {

        Model accord = new Model("Honda", "Accord");
        //Model outback = new Model("Subaru", "Outback");

        Car hondaAccord = new Car(accord, 2012, maxMph);
        Car otherAccord = new Car(accord, 2013, maxMph);
        //Car subaruOutback = new Car(outback, 2020);

        System.out.println(hondaAccord);

    }
}
