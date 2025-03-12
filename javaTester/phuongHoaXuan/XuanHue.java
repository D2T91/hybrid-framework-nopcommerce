package phuongHoaXuan;

public class XuanHue extends XuanLan {
    XuanLan xuanLan = new XuanLan();

    public static void main(String[] args) {
        XuanHue xuanHue = new XuanHue();
        xuanHue.test();

        // Trong phương thức main, muốn gọi phương thức của đối tượng thuộc lớp khác phải dùng CÚ PHÁP:
        xuanHue.xuanLan.getCappuccino();
        xuanHue.getCappuccino();

        // Không lấy xuanLan.test() được vì Class XuanLan không có phương thức test()
        //xuanHue.xuanLan.test();
    }

    public void test() {
        System.out.println(xuanLan.getCappuccino());
        System.out.println(" In ra = " + getCappuccino());
    }
}
