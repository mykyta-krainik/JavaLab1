package io.github.mykytko;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        var url = "https://raw.githubusercontent.com/rashida048/Datasets/master/cars.csv";
        var fileName = "cars.csv";

        List<String> lines;
        try {
            downloadData(url, fileName);
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        var cars = new ArrayList<Car>();
        var rand = new Random();
        for (int i = 1; i < lines.size(); i++) {
            var splits = lines.get(i).split(",");
            var brand = splits[1];
            var model = splits[2];
            var year = Integer.parseInt(splits[0]);
            var color = rand.nextInt(0xffffff + 1);
            var price = 10000 + rand.nextInt(400) * 100 - rand.nextInt(1);
            var registrationNumber = generateRegistrationNumber();

            var car = new Car(brand, model, year, color, price, registrationNumber);
            cars.add(car);
        }

        for (var car : cars) {
            System.out.println(car);
            System.out.println();
        }
    }

    private static String generateRegistrationNumber() {
        return "";
    }

    private static ArrayList<Car> taskA(ArrayList<Car> cars) {
        return null;
    }

    private static ArrayList<Car> taskB(ArrayList<Car> cars) {
        return null;
    }

    private static ArrayList<Car> taskC(ArrayList<Car> cars) {
        return null;
    }

    private static void downloadData(String url, String fileName) throws IOException {
        var readableByteChannel = Channels.newChannel(new URL(url).openStream());
        var fileOutputStream = new FileOutputStream(fileName);
        var fileChannel = fileOutputStream.getChannel();

        fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        fileChannel.close();
        fileOutputStream.close();
        readableByteChannel.close();
    }

}