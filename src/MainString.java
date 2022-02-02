public class MainString {
    public static void main(String[] args) {
        String[] strArray = new String[]{"1", "2", "3", "4", "5"};
        StringBuilder result =  new StringBuilder();
        for (String x : strArray) {
            result.append(x).append(",");
        }
        System.out.println(result);

        String str1 = "abc";
        String str2 = "abcd";
        str1= String.valueOf(new StringBuilder("abcd"));
        System.out.println(str1.equals(str2));

    }
}
