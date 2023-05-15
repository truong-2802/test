package com.person.util;

import com.person.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PersonManagement {
    public void addNewPerson(Person person)throws Exception{
        try {
            Connection conn=DBUtil.getConnection();
            PreparedStatement pstmt=conn.prepareStatement("INSERT INTO Person(Name,Email,Address,Phone) VALUES (?,?,?,?)");
            pstmt.setString(1,person.getName());
            pstmt.setString(2,person.getEmail());
            pstmt.setString(3,person.getAddress());
            pstmt.setString(4,person.getPhone());
            int update=pstmt.executeUpdate();
            if (update>0){
                System.out.println("Insert Person success!!!");
            }
            pstmt.close();
            conn.close();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void getAllPerson(){
        try {
            Connection conn=DBUtil.getConnection();
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT Id,Name,Email,Address,Phone FROM Person");

            while (rs.next()){
                Person ps=new Person(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                System.out.println(ps.toString());
            }
            rs.close();
            stmt.close();
            conn.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public Person getPersonById(int id)throws Exception{
        Person ps=null;
        try {
            Connection conn=DBUtil.getConnection();
            PreparedStatement pstm= conn.prepareStatement("SELECT Id,Name,Email,Address,Phone FROM Person WHERE Id=?");
            pstm.setInt(1,id);
            ResultSet rs= pstm.executeQuery();

            if (rs.next()){
                ps =new Person(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
            }

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return ps;
    }



    public void updateData(int id)throws Exception{
        try {
            Person updatingPerson=this.getPersonById(id);
            if(updatingPerson!=null){
                updatingPerson.inputData();
                Connection conn =  DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "UPDATE Person SET Name = ?, Email = ?, Address = ?,Phone=? WHERE Id = ?");
                pstmt.setString(1, updatingPerson.getName());
                pstmt.setString(2, updatingPerson.getEmail());
                pstmt.setString(3, updatingPerson.getAddress());
                pstmt.setString(4, updatingPerson.getPhone());
                pstmt.setInt(5, updatingPerson.getId());
                int updated=pstmt.executeUpdate();
                if(updated>0){
                    System.out.println("Update Person success!!!");
                }
                pstmt.close();
                conn.close();
            }

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public void deletePerson(int id)throws Exception{
        try {
            Connection conn=DBUtil.getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement("DELETE FROM Person WHERE Id=?");
            preparedStatement.setInt(1,id);
            int update=preparedStatement.executeUpdate();
            if (update>0){
                System.out.println("Deleted Person successfully");
            }
            preparedStatement.close();
            conn.close();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
