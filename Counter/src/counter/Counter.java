package counter;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Counter {

    private Map<Character, Integer> charCounter = new HashMap<>();
    private Map<String, Integer> wordCounter = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your content: ");
        String content = scanner.nextLine();

        Counter counter = new Counter();
        counter.analyze(content);
        counter.display();
    }

    public void display() {
        System.out.println(wordCounter);
        System.out.println(charCounter);
    }

    public void analyze(String content) {
        analyzeChars(content);
        analyzeWords(content);
    }

    private void analyzeChars(String content) {
        for (char ch : content.toCharArray()) {
            if (!Character.isSpaceChar(ch)) {
                charCounter.put(ch, charCounter.getOrDefault(ch, 0) + 1);
            }
        }
    }

    private void analyzeWords(String content) {
        StringTokenizer tokenizer = new StringTokenizer(content);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            wordCounter.put(token, wordCounter.getOrDefault(token, 0) + 1);
        }
    }
}
