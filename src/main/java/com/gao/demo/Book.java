package com.gao.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Book {

    //链式编程 + 流式计算
    private int id;
    private String bookName;
    private double price;

    public static void main(String[] args) {
        Book book = new Book();
        book.setId(11);
        book.setBookName("java");
        book.setPrice(33.5d);


        Book book2 = new Book();
        book2.setId(12).setBookName("C++").setPrice(45.5d);


    }

}
