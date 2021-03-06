import com.opencsv.CSVWriter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class app {
    public static void main(String[] args) {

        GUI gui = new GUI();
        String finalFile = gui.runToText();
        String finalCVS = gui.runToCVS();
        appDo(finalFile);
        if (finalCVS != null){
            System.out.println("Converting to CVS");
            convertToCSV(finalFile,finalCVS);
        }
    }
    public static void convertToCSV(String finalFile,String finalFileCVS){
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(finalFileCVS));
            try {
                FileInputStream fileInputStream = new FileInputStream(finalFile);
                Scanner scanner = new Scanner(fileInputStream);
                String temp = "";
                ArrayList<String> tem1p = new ArrayList<>();
                while (scanner.hasNext()){
                    String innerTemp = scanner.nextLine();
                    if(innerTemp.equals("---============---")){
                        temp += ("---=---" + "\n");
                        tem1p.add(temp);
                        temp = " ";
                    }else {
                        temp += (innerTemp + "\n");
                    }
                    if (tem1p.size()==4){
                        String[] array = {tem1p.get(0),tem1p.get(1),tem1p.get(2),tem1p.get(3)};
                        writer.writeNext(array);
                        tem1p.clear();
                    }
                }
                scanner.close();
            }catch (IOException ex){
                System.out.println(ex);
            }
            writer.flush();
            System.out.println("done");
        }catch (Exception ex){
            System.out.println(ex);

        }
    }

    public static void appDo(String path) {
        double total = 0, dph21 = 0, dph15 = 0;
        try {
            File fOut = new File(path);
            FileOutputStream fos = new FileOutputStream(fOut);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
            ArrayList<BillItem> billsItem = createItemList();
            ArrayList<Bill> listOfBills = createListOfBills(billsItem);
            HashMap<String, String> productTax = getProductTax();
            productTax = addExToList(productTax);

            for (Bill bill : listOfBills) {
                bufferedWriter.write(bill.text1);
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text2);
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text3);
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text4);
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text5);
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text6);
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text7);
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text8 + bill.getId());
                bufferedWriter.newLine();
                bufferedWriter.write(bill.getBillItems().get(0).getDatetime());
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                double sum = 0;
                double tax21 = 0, tax15 = 0, tax10 = 0, tax0 = 0;
                double[] taxListSum = new double[4];
                String fik = null, bkp = null;

                for (BillItem item : bill.getBillItems()) {
                    fik = item.getFik();
                    bkp = item.getBkp();

                    //10,15,21,0

                    tax21 = Double.parseDouble(item.getTax21());
                    tax10 = Double.parseDouble(item.getTax10());
                    tax15 = Double.parseDouble(item.getTax15());
                    tax0 = Double.parseDouble(item.getTax0());


                    String taxType = productTax.get(item.getTitle());
                    if (taxType != null && taxType.equals("21.0")) {
                        //tax21
                        taxListSum[2] += Double.parseDouble(item.getSum());
                    } else if (taxType != null && taxType.equals("15.0")) {
                        //tax15
                        taxListSum[1] += Double.parseDouble(item.getSum());
                    } else if (taxType == null) {
                        System.out.println(item.getBill_id());
                        System.out.println(item.getTitle());
                        System.out.println(taxType);
                    } else {
                        System.out.println(item.getTitle());
                        System.out.println(item.getBill_id());
                        System.out.println(taxType);
                        System.out.println("Dunno");
                    }
                    bufferedWriter.write(item.getTitle() + "\n");
                    bufferedWriter.newLine();
                    double price = Double.parseDouble(item.getSum()) / Double.parseDouble(item.getCount());

                    sum += Double.parseDouble(item.getSum());
                    String priceS = String.format("%.2f", price);
                    bufferedWriter.write(item.getCount() + "  x  " + priceS + " Kc" + returnSpace(priceS) + "=" + returnSpace(item.getSum()) + item.getSum() + " Kc");
                    bufferedWriter.newLine();

                }
                bufferedWriter.write(bill.text10);
                bufferedWriter.newLine();
                total += sum;
                dph21 += tax21;
                dph15 += tax15;
                bufferedWriter.write("Suma:     " + returnSpace(String.format("%.2f", sum)) + String.format("%.2f", sum) + " Kc");
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text13 + returnSpace(String.format("%.2f", sum)) + String.format("%.2f", sum) + " Kc");
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text10);
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text14);
                bufferedWriter.newLine();
                String temp = String.format("%.2f", taxListSum[0] - tax10);
                String temp2 = String.format("%.2f", tax10);
                bufferedWriter.write(bill.text15 + returnSpace(temp) + temp + " Kc" + returnSpace(temp2) + temp2 + " Kc");
                bufferedWriter.newLine();

                temp = String.format("%.2f", taxListSum[1] - tax15);
                temp2 = String.format("%.2f", tax15);
                bufferedWriter.write(bill.text16 + returnSpace(temp) + temp + " Kc" + returnSpace(temp2) + temp2 + " Kc");
                bufferedWriter.newLine();

                temp = String.format("%.2f", taxListSum[2] - tax21);
                temp2 = String.format("%.2f", tax21);
                bufferedWriter.write(bill.text17 + returnSpace(temp) + temp + " Kc" + returnSpace(temp2) + temp2 + " Kc");
                bufferedWriter.newLine();

                temp = String.format("%.2f", taxListSum[3] - tax0);
                temp2 = String.format("%.2f", tax0);
                bufferedWriter.write(bill.text18 + returnSpace(temp) + temp + " Kc" + returnSpace(temp2) + temp2 + " Kc");
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text10);
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text19);
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text191);
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text192);
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text20);
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text201);
                bufferedWriter.newLine();
                assert fik != null;
                String fik1 = fik.substring(15);
                fik = fik.substring(0, 15);
                bufferedWriter.write(bill.text21 + fik);
                bufferedWriter.newLine();
                bufferedWriter.write(fik1);
                bufferedWriter.newLine();
                String bkp1 = bkp.substring(18);
                bkp = bkp.substring(0, 18);
                bufferedWriter.write(bill.text22 + bkp);
                bufferedWriter.newLine();
                bufferedWriter.write(bkp1);
                bufferedWriter.newLine();
                bufferedWriter.write(bill.text11);
                bufferedWriter.newLine();

            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        System.out.println("========================");
        System.out.println("The total is " + total);
        System.out.println("The total of dph15 is " + dph15);
        System.out.println("The total of dph21 is " + dph21);
        System.out.println("========================");
    }

    public static ArrayList<BillItem> createItemList() {
        ArrayList<BillItem> billsItem = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\DAA\\Desktop\\ggg.csv"));
            scanner.useDelimiter(";");

            ArrayList<String> item = new ArrayList<>();
            while (scanner.hasNext()) {
                item.add(scanner.next());
                if (item.size() == 2) {
                    String count = item.get(item.size() - 1).replace(".0", "");
                    item.set(item.size() - 1, count);
                }
                if (item.size() == 5) {
                    item.set(4, convertUnix(item.get(4)));
                }
               /* if ((item.size() >= 6 && item.size() <= 9) || item.size() ==3){
                    double sum = Double.parseDouble(item.get(item.size()-1));
                    String formatted = String.format("%.2f",sum);
                    item.set(item.size()-1,formatted);

                }*/
                if (item.size() == 11) {
                    BillItem billItem = new BillItem(item.get(0), item.get(1), item.get(2), item.get(3), item.get(4), item.get(5), item.get(6), item.get(7), item.get(8), item.get(9), item.get(10));
                    billsItem.add(billItem);
                    item.clear();
                }
            }
            scanner.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return billsItem;
    }

    public static String convertUnix(String unixT) {
        unixT = unixT.substring(0, 13);
        long unix = Long.parseLong(unixT);
        Date date = new Date(unix);
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Prague"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static ArrayList<Bill> createListOfBills(ArrayList<BillItem> billItems) {

        HashMap<String, Bill> map = new HashMap<>();
        for (BillItem billItem : billItems) {
            String id = billItem.getBill_id();
            Bill bill;
            if (map.containsKey(id)) {
                bill = map.get(id);
            } else {
                bill = new Bill();
            }
            bill.setId(id);
            bill.getBillItems().add(billItem);
            map.put(id, bill);
        }
        //from  map.values().stream().collect(Collectors.toCollection(ArrayList::new)); to
        ArrayList<Bill> listOfBills = new ArrayList<>(map.values());

        return sort(listOfBills);
    }

    public static ArrayList<Bill> sort(ArrayList<Bill> list) {
        list.sort((Comparator.comparing(Bill::getId)));
        return list;
    }

    public static HashMap<String, String> getProductTax() {
        HashMap<String, String> productTaxH = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\DAA\\Desktop\\productTax.csv"));
            scanner.useDelimiter(";");
            ArrayList<String> product = new ArrayList<>();
            while (scanner.hasNext()) {
                product.add(scanner.next());
                if (product.size() == 2) {
                    productTaxH.put(product.get(0), product.get(1));
                    product.clear();
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return productTaxH;
    }

    public static HashMap<String, String> addExToList(HashMap<String, String> list) {
        //1st shop
        list.put("Burton orginal red 23", "21.0");
        list.put("Burton White 20", "21.0");
        list.put("burton white", "21.0");
        list.put("Burton White 23", "21.0");
        list.put("benson hedges silver 20", "21.0");
        list.put("BENSON- HEDGES white", "21.0");
        list.put("marjhoro", "21.0");
        list.put("benson hedges", "21.0");
        list.put("Vic 23 blue", "21.0");
        list.put("rgd blue", "21.0");
        list.put("viceroy 22 red", "21.0");

        list.put("Zbo????", "21.0");
        list.put("Budweiser 0.5L", "21.0");
        list.put("Bud??jovick?? Sv??tl?? 500g", "21.0");
        list.put("relax 300ml", "15.0");
        list.put("Water Pear", "15.0");
        list.put("brikety 25kg", "21.0");
        list.put("oplat", "15.0");
        list.put("Med Kv??tov?? Sm????en?? 900g", "21.0");
        //2nd shop
        list.put("Benso Hedges black big box", "21.0");
        list.put("Burton Red 23", "21.0");
        list.put("winston blue 22", "21.0");
        list.put("Wiston Red Normal", "21.0");
        list.put("lucky strike", "21.0");
        list.put("Benson Hedgeh silver", "21.0");
        list.put("C.g.Camel Since 1913 Filters", "21.0");
        list.put("LD sl pink", "21.0");
        list.put("Burton orignal red 22", "21.0");
        list.put("Burton White 23", "21.0");
        list.put("bebson hedges silver", "21.0");
        list.put("chesjeptield", "21.0");
        list.put("C.g.Winston blue", "21.0");
        list.put("Burton White Bigbox", "21.0");
        list.put("CAMEL", "21.0");
        list.put("wiston", "21.0");
        list.put("winston 22s", "21.0");
        list.put("Gauloises Blue 20", "21.0");
        list.put("Burton White Bigbo", "21.0");
        list.put("pall mall ksblue", "21.0");
        list.put("C.g.Camel Filters", "21.0");
        list.put("viceroy blue 22", "21.0");
        list.put("cheterfield caps blue", "21.0");
        list.put("Wiston Blue 100s", "21.0");
        list.put("Burton orignal red 23", "21.0");
        list.put("LD sl violet", "21.0");
        list.put("winston red", "21.0");
        list.put("winston blue", "21.0");
        list.put("C.g.Winston Red", "21.0");
        list.put("LD sl pin", "21.0");
        list.put("C.g.Benson & Hedges silver slide", "21.0");
        list.put("chesterfeld blue", "21.0");
        list.put("viceroy   40", "21.0");
        list.put("phall mall", "21.0");
        list.put("BURTON red", "21.0");
        list.put("chsepjield", "21.0");
        list.put("Burton orginal red 23", "21.0");
        list.put("chestepheld", "21.0");
        list.put("burton white", "21.0");
        list.put("Pall Mall blue 23", "21.0");
        list.put("BURTON green", "21.0");
        list.put("Zbo????", "21.0");


        return list;
    }

    public static String returnSpace(String input) {
        int num = 8 - input.length();
        String space = "";
        for (int i = 0; i < num; i++) {
            space += " ";
        }
        return space;
    }


}
