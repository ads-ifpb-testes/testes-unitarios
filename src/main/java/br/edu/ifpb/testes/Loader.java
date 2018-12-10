package br.edu.ifpb.testes;

import java.util.UUID;

public class Loader {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

}
