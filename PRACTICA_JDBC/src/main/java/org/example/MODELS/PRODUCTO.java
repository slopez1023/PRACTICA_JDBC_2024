package org.example.MODELS;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PRODUCTO {



        private Long id;
        private String name;
        private double price;
        private LocalDate register_date;

    }


