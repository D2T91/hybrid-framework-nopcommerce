package phuongHoaXuan;

public class XuanLan {
    // Private : chỉ có XuanLan mới có
    private String latte = "Latte";

    String espresso = "Espresso";
    protected String cappuccino = "Cappuccino";
    String macchiato = "Macchiato";

    // Private : chỉ có XuanLan mới lấy được
    private String getLatte() {
        return latte;
    }

    protected String getCappuccino() {
        return cappuccino;
    }

    public static void main(String[] args) {
        XuanLan xuanLan = new XuanLan();
        System.out.println(xuanLan.latte);
    }
}
