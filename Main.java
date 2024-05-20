import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "/Users/bulatsagdiev/Downloads/data.txt";

        try {
            List<String> lines = FileUtil.readFile(filePath);
            List<Program> allPrograms = new ArrayList<>();

            for (String line : lines) {
                String[] parts = line.split(",");
                String channel = parts[0].trim();
                BroadcastsTime time = new BroadcastsTime(parts[1].trim());
                String title = parts[2].trim();

                Program program = new Program(channel, time, title);
                allPrograms.add(program);
            }

            // 5. Вывести все программы в порядке возрастания канал, время показа
            allPrograms.sort(Comparator.comparing(Program::getChannel).thenComparing(Program::getTime));
            System.out.println("All programs in sorted order:");
            allPrograms.forEach(System.out::println);

            // 6. Вывести все программы, которые идут сейчас
            BroadcastsTime now = new BroadcastsTime("12:00");
            System.out.println("\nPrograms currently airing:");
            allPrograms.stream().filter(p -> p.getTime().equals(now)).forEach(System.out::println);

            // 7. Найти все программы по некоторому названию
            String searchTitle = "Крым. Как это было";
            System.out.println("\nPrograms with title '" + searchTitle + "':");
            allPrograms.stream().filter(p -> p.getTitle().equalsIgnoreCase(searchTitle)).forEach(System.out::println);

            // 8. Найти все программы определенного канала, которые идут сейчас
            String searchChannel = "#Первый";
            System.out.println("\nPrograms on channel '" + searchChannel + "' currently airing:");
            allPrograms.stream()
                    .filter(p -> p.getChannel().equalsIgnoreCase(searchChannel) && p.getTime().equals(now))
                    .forEach(System.out::println);

            // 9. Найти все программы определенного канала, которые будут идти в некотором промежутке времени
            BroadcastsTime startTime = new BroadcastsTime("09:00");
            BroadcastsTime endTime = new BroadcastsTime("20:00");
            System.out.println("\nPrograms on channel '" + searchChannel + "' between " + startTime + " and " + endTime + ":");
            allPrograms.stream()
                    .filter(p -> p.getChannel().equalsIgnoreCase(searchChannel) && p.getTime().between(startTime, endTime))
                    .forEach(System.out::println);

            // 10. Сохранение отсортированных данных в файл формата .xlsx
            XLSXWriter.writeProgramsToFile(allPrograms, "sorted_programs.xlsx");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
