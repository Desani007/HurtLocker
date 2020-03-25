import org.apache.commons.io.IOUtils;

import java.util.*;

public class Main {

    public String readRawDataToString() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public String[] putInArray(String result) {
        String[] strArray = result.split("[##;%^@!*]");
        for (String s : strArray) {
            System.out.println(s);
        }
        return strArray;
    }


    public ArrayList<GroceryList> itemsToGroceries(String[] strArray) {
        ArrayList<GroceryList> list = new ArrayList();
        String name = "";
        String price = "";
        String food = "";
        String expiration = "";
        GroceryList groceryList;
        for (int j = 0; j < strArray.length; j++) {
            strArray[j] = strArray[j].toLowerCase();

            if (j % 5 == 0) {
                groceryList = new GroceryList(name, price, food, expiration);
                list.add(groceryList);
                name = "";
                price = "";
                food = "";
                expiration = "";

            }
            //          map= Splitter.on(" ").withKeyValueSeparator(":").split(s);
            String[] split = strArray[j].split(":");
            for (int i = 0; i < split.length - 1; i++) {

                if (split[i].equals("name")) {
                    split[i + 1] = split[i + 1].replaceAll("[0-9]", "o");
                    name = split[i + 1];
                }
                if (split[i].equals("price")) {
                    price = split[i + 1];
                }
                if (split[i].equals("type")) {
                    food = split[i + 1];
                }
                if (split[i].equals("expiration")) {
                    expiration = split[i + 1];

                }
            }
        }
        return list;
    }

    public void putItemInMapForName(ArrayList<GroceryList> list) {
        Map nameMap = new HashMap<String, Integer>();

        Map priceMap = new HashMap<String, Integer>();
        Map<String, ArrayList<String>> myMap = new HashMap();


        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> price = new ArrayList<>();


        for (GroceryList g : list) {
            name.add(g.getName());
            price.add(g.getPrice());


        }

        for (String h : name) {
            int num = Collections.frequency(name, h);
            nameMap.put(h, num);
        }
        for (String s : price) {
            int num2 = Collections.frequency(price, s);
            priceMap.put(s, num2);
        }


        System.out.println(nameMap);
        System.out.println(priceMap);
        System.out.println(myMap);


    }

    public void pricePerItem(ArrayList<GroceryList> list){
        Map map = new HashMap();
        List array = new ArrayList();
        for(GroceryList g : list) {
            for (int i = 0; i < list.size(); i++) {

                if (g.getName().equals(list.get(i).getName())) {
                    array.add(g.getPrice());
                }
                }if (!map.containsKey(g.getName())) {
                map.put(g.getName(),array);
            }      array= new ArrayList();




        }
        System.out.println(map);




    }




    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        System.out.println(output);

        String [] arr = (new Main().putInArray(output));

        ArrayList s= (new Main()).itemsToGroceries(arr);

        (new Main()).pricePerItem(s);
        (new Main()).putItemInMapForName(s);


}
}
