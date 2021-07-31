public class Extend {
    public static void main(String[] args) {
//        B b = new D();
//        System.out.println(b.m);

        System.out.println(divide(10,0));
        System.out.println("hello");
    }

    public static int divide(int x,int y){
        if(y == 0)
            throw new ArithmeticException("除0异常");
        return x/y;
    }
}

//class B{
//    public int m = 10;
//}
//
//class D extends B{
//    public int m = 11;
//}