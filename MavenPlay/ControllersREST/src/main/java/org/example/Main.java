package org.example;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main{

    public static void main(String[] args) {
        String[] names = new String[]{"piotr", "amelka", "nazwa"};
        List<String> sss = Arrays.stream(names).filter(n -> n.length() > 3).collect(Collectors.toList());
        System.out.println(sss);
        Function<Integer, Integer> fn = n -> {
            System.out.println("Hello");
            return  n + 12;
        };
        Function<Integer, Integer> fn3 = n -> n+4;
        fn.apply(4);

    }
}

class CardRepo {
    public void deleteCard(CardDisplay card) {

    }

    public Optional<Integer> getCopies(CardDisplay card) {
        return Optional.of(card.getCount());
    }
    public void addCard(CardDisplay card) {

    }
}
class NotBigDeal {


    private CardRepo cardRepo;
    private void deleteCard(CardDisplay card) {
        Optional<Integer> cardCount = cardRepo.getCopies(card);
        if(cardCount.isPresent()) {
            if(cardCount.get() == 0) {
                System.out.println("Nie da sie bo jest 0 kopii");
            }
            cardRepo.deleteCard(card);
        }
    }

    private void addCard(CardDisplay card) {
        Optional<Integer> cardCount = cardRepo.getCopies(card);
        if(cardCount.isPresent()) {
            System.out.println("Taka karta juz jest");
        }
        else {
            cardRepo.addCard(card);
        }

        checkCard(card, (count) -> {System.out.println(count);return count;}, () -> {cardRepo.addCard(card); return -1;});

    }
    private int countCards(CardDisplay card) {
        return checkCard(card, (count) -> count, () -> -1);

    }
    private int checkCard(CardDisplay card, Function<Integer, Integer> whenIsPresent,  Supplier< Integer> whenIsNotPresent) {
        Optional<Integer> cardCount = cardRepo.getCopies(card);
        if(cardCount.isPresent()) {
            return whenIsPresent.apply(cardCount.get());
        }
        else {
            return whenIsNotPresent.get();
        }
    }
}

class CardDisplay {
    private String name;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}