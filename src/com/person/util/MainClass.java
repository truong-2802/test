package com.person.util;

import com.person.model.Person;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        PersonManagement management=new PersonManagement();
        int choose=0;
        Scanner input=new Scanner(System.in);
        try {
            do {
                System.out.println("1. Add new Person");
                System.out.println("2. view all Person");
                System.out.println("3. Get Person by ID");
                System.out.println("4. Update Person by ID");
                System.out.println("5. Delete Person by ID");
                System.out.println("6. Exit");
                System.out.println("Menu choose :");

                choose=input.nextInt();
                switch (choose){
                    case 1:{
                        Person ps=new Person();
                        ps.inputData();
                        management.addNewPerson(ps);
                    }
                    break;
                    case 2:{
                        management.getAllPerson();
                    }
                    break;
                    case 3:{
                        System.out.println(" Enter id");
                        int id=input.nextInt();

                        Person ps=management.getPersonById(id);
                        if (ps!=null){
                            System.out.println(ps.toString());
                        }else{
                            System.out.println("Not found");
                        }
                    }
                    break;
                    case 4:{
                        System.out.println("Enter Person id to update:");
                        int id=input.nextInt();
                        management.updateData(id);
                    }
                    break;
                    case 5:{
                        System.out.println("Enter Person id to update:");
                        int id=input.nextInt();
                        management.deletePerson(id);
                    }
                    break;
                    case 6:{
                        System.out.println("Exit");
                        return;
                    }
                }
            }while (true);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
