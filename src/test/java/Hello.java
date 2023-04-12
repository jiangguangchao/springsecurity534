import java.nio.charset.Charset;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2023-03-09 13:40
 */
public class Hello {
    public static void main(String[] args) {
        System.out.println("测");
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset().name());
    }
}
