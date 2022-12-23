package enity;

import java.util.Date;

public class Orders {
    private String customer;
    private String buyName;
    private int buyNum;
    private Date buyDate;



    public Orders(String customer, String buyName, int buyNum, Date buyDate) {
        this.customer = customer;
        this.buyName = buyName;
        this.buyNum = buyNum;
        this.buyDate = buyDate;
    }

    public String getBuyName() {
        return buyName;
    }

    public void setBuyName(String buyName) {
        this.buyName = buyName;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public String getCustomer() {
        return customer;
    }

    public void setUsername(String username) {
        this.customer = username;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "customer='" + customer + '\'' +
                ", buyName='" + buyName + '\'' +
                ", buyNum=" + buyNum +
                ", buyDate=" + buyDate +
                '}';
    }
}
