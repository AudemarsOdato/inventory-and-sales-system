import utilities.JCrypt;

public class Main {
        // start command: java -classpath ".\lib\postgresql-42.2.29.jre7.jar" .\src\Main.java
        public static void main(String[] args) {
                JCrypt jcrypt = new JCrypt();

                System.out.println(jcrypt.hash("trisha"));
                System.out.println(jcrypt.isMatch("trisha", "c721f0c3e3ed6a1bc6c76deda4bd38e2da768971da2879513c69e541b529382e"));
        }
}