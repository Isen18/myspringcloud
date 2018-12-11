/**
 * @author Isen
 * @date 2018/9/27 11:27
 * @since 1.0
 */
public class T {

    public static void main(String[] args) {
        PP p = new Pc();
        p.print();
    }
}


interface PP{
    void print();
}

class Pc implements PP{

    @Override
    public void print() {
        System.out.println(this.getClass());
    }
}