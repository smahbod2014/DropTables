package com.koda.droptables;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main();
//        ItemGenerator itemGenerator = new ItemGenerator();
    }

    Main() throws IOException {
        MonsterGenerator monsterGenerator = new MonsterGenerator(new ItemGenerator());
        Scanner scanner = new Scanner(System.in);
        String area = "";
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("q")) {
                break;
            }
            if (input.equals("bm")) {
                area = "Blood Moor";
                System.out.println("Entering Blood Moor");
                continue;
            }
            if (input.equals("cp")) {
                area = "Cold Plains";
                System.out.println("Entering Cold Plains");
                continue;
            }
            String monsterName = input;
            Optional<Drop> drop = monsterGenerator.killMonster(monsterName, area);
            if (drop.isPresent()) {
                System.out.println(drop.get());
            } else {
                System.out.println("Nothing");
            }
        }
        System.out.println("Exiting");
        scanner.close();
    }

}
