import java.util.ArrayList;
import java.util.List;

public class Bill {
    private String id;

    public Bill() {
    }

    public Bill(String id, List<BillItem> billItems) {
        this.id = id;
        this.billItems = billItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private List<BillItem> billItems = new ArrayList<>();

    public List<BillItem> getBillItems() {
        return billItems;
    }

    public void setBillItems(List<BillItem> billItems) {
        this.billItems = billItems;
    }

    String text1 = "THANH DAT Mini shop";
    String text2 = "Rozany 6";
    String text3 = "+420 775 383 055";
    String text4 = "DIC: CZ6905249934 ICO: 67424236";
    String text5 = "ID provozovny: 11 ID pokladny: 1";
    String text6 = "O.v. : Hien Tran Van";
    String text7 = "Bezny rezim evidence trzeb";
    String text8 = "EV. c:";
    String text9 = " ";
    String text10 = "------------------";
    String text11 = "---============---";
    String text12 = "Suma:     " + "Kc";
    String text13 = "K platbe: ";
    String text14 = "Sazba       zaklad       DPH";
    String text15 = "DPH 10%";
    String text16 = "DPH 15%";
    String text17 = "DPH 21%";
    String text18 = "DPH 0% ";
    String text19 = "Oteviraci doba";
    String text191 = "Po - Ne : 7h - 20h";
    String text192 = "DEKUJEME ZA VAS NAKUP";
    String text20 = "Uctenka si uchovejte pro";
    String text201 = " pripadnou reklamaci.";
    String text21 = "FIK:";
    String text22 = "BKP:";

}
