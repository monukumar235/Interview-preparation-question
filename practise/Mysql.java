package practise;

import java.util.*;
import java.util.function.Predicate;

public class Mysql {
    public static class DbOperation{
        Map<String,List<Map<String, Object>>> tables = new HashMap<>();

        public void createTable(String tableName){
            tables.putIfAbsent(tableName,new ArrayList<>());
        }

        public void insert(String tableName,Map<String,Object> row){
            validTables(tableName);
            tables.get(tableName).add(row);
        }
        public void delete(String tableName, Predicate<Map<String,Object>> conditions){
            validTables(tableName);
            tables.get(tableName).remove(conditions);
        }

        public void update(String tableName,Predicate<Map<String,Object>> condition,Map<String,Object> updatedVal){
            validTables(tableName);
            for (Map<String,Object> row : tables.get(tableName)){
                if(condition.test(row)){
                    row.putAll(updatedVal);
                }
            }
        }
        public List<Map<String,Object>> select(String tableName,Predicate<Map<String,Object>> condition){
            validTables(tableName);
            List<Map<String,Object>> results = new ArrayList<>();
            for (Map<String ,Object> row : tables.get(tableName)){
                if(condition.test(row)){
                    results.add(row);
                }
            }
            return results;
        }

        public List<Map<String,Object>> selectWithsorting(String tableName,Predicate<Map<String,Object>>condition,String sortKey,boolean isAsc){
//            validTables(tableName);
            List<Map<String, Object>> filter = select(tableName, condition);
            filter.sort((a,b)->{
                Comparable valA = (Comparable) a.get(sortKey);
                Comparable valB = (Comparable) b.get(sortKey);
                return isAsc ? valA.compareTo(valB) : valB.compareTo(valA);
            });
            return filter;
        }
        public void validTables(String tableName){
            if(!tables.containsKey(tableName)) {
                throw new IllegalArgumentException("Invalid tables name");
            }
        }
    }
    public static void main(String[] args) {
        DbOperation db=new DbOperation();
        db.createTable("users");

        Map<String,Object> row1 = new HashMap<>();
        row1.put("id","Monu");
        row1.put("age",23);
        row1.put("city","ranchi");
        db.insert("Users",row1);

        Map<String,Object> row2 = new HashMap<>();
        row2.put("id","Rohit");
        row2.put("age",34);
        row2.put("city","bangalore");
        db.insert("Users",row2);


    }
}
