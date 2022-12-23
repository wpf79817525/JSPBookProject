package enity;

public class Book {
    private int id;
    private String name;
    private double price;
    private String writer;
    private int nums;


    public Book(int id, String name, double price, String writer,int nums) {  // 编号 书名 价格 剩余数量 作者
        this.id = id;
        this.name = name;
        this.price = price;
        this.writer = writer;
        this.nums = nums;
    }

    public Book(String name, double price, int nums) {      // 书名 价格 剩余数量
        this.name = name;
        this.price = price;
        this.nums = nums;
    }

    public Book(String name, int nums) {        // 书名 数量
        this.name = name;
        this.nums = nums;
    }

    public Book(String name, double price, String writer, int nums) {      // 书名 价格 剩余数量 作者
        this.name = name;
        this.price = price;
        this.writer = writer;
        this.nums = nums;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", nums=" + nums +
                ", writer='" + writer + '\'' +
                '}';
    }
}
