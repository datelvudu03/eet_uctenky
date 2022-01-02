public class BillItem {
    private String bill_id,count,sum,title,datetime,tax21,tax15,tax10,tax0,fik,bkp;

    public BillItem() {
    }

    public BillItem(String bill_id, String count, String sum, String title, String datetime, String tax21, String tax15, String tax10, String tax0, String fik, String bkp) {
        this.bill_id = bill_id;
        this.count = count;
        this.sum = sum;
        this.title = title;
        this.datetime = datetime;
        this.tax21 = tax21;
        this.tax15 = tax15;
        this.tax10 = tax10;
        this.tax0 = tax0;
        this.fik = fik;
        this.bkp = bkp;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTax21() {
        return tax21;
    }

    public void setTax21(String tax21) {
        this.tax21 = tax21;
    }

    public String getTax15() {
        return tax15;
    }

    public void setTax15(String tax15) {
        this.tax15 = tax15;
    }

    public String getTax10() {
        return tax10;
    }

    public void setTax10(String tax10) {
        this.tax10 = tax10;
    }

    public String getTax0() {
        return tax0;
    }

    public void setTax0(String tax0) {
        this.tax0 = tax0;
    }

    public String getFik() {
        return fik;
    }

    public void setFik(String fik) {
        this.fik = fik;
    }

    public String getBkp() {
        return bkp;
    }

    public void setBkp(String bkp) {
        this.bkp = bkp;
    }
}
