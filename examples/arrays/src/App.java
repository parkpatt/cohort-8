public class App {
    public static void main(String[] args) {

//        String[] names = new String[3];
//
//        String[] otherNames = {
//                "Name 1",
//                "Name 2",
//                "Name 3"
//        };
//
//        String name1 = "Name 1";
//
//        name1 = new String("Name 1");
//
//        String name2 = "";
//        String name3 = "";
//
//        names = new String[10];


        // Mac & Cheese
        // Tacos
        // Pizza
        // Pad Thai
        // None

        String[] favoriteFoods = {
                "Mac & Cheese",
                "Tacos",
                "Pizza",
                "Pad Thai"
        };


        String[] topFiveFoods = new String[5];

        for (int index = 0; index < topFiveFoods.length; index++) {
            System.out.println(topFiveFoods[index]);
        }

        int[] numbers = new int[5];

        for (int index = 0; index < numbers.length; index++) {
            System.out.println(numbers[index]);
        }


//        topFiveFoods[0] = "Mac & Cheese";
//        topFiveFoods[1] = "Tacos";
//        topFiveFoods[2] = "Pizza";
//        topFiveFoods[3] = "Pad Thai";
//        topFiveFoods[4] = "Tomatoes";
//
//
//        topFiveFoods[4] = "Tomato Soup";

        String[][] grid = new String[10][10];

        int fancyInt = getFancyInt();
    }

    /**
     * This returns a super fancy int just for you
     * @return
     */
    public static int getFancyInt() {
        return 0;
    }
}
