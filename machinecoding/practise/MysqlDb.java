package machinecoding.practise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class MysqlDb {
    public static class MysqlOperation{
        Map<String, List<Map<String,Object>>> tables= new HashMap<>();

        public void createTable(String tableName){
            tables.putIfAbsent(tableName,new ArrayList<>());
        }
        public void insert(String tableName, Map<String,Object> row){
            validateTable(tableName);
            tables.get(tableName).add(row);
        }

        public void delete(String tableName,Predicate<Map<String,Object>>condition){
            validateTable(tableName);
            tables.get(tableName).removeIf(condition);
        }

        public void update(String tableName,Predicate<Map<String,Object>>condition,Map<String,Object>updatedValue){
            validateTable(tableName);
            for(Map<String,Object> row : tables.get(tableName)){
                if(condition.test(row)){
                    row.putAll(updatedValue);
                }
            }
        }

        public List<Map<String,Object>> select(String tableName,Predicate<Map<String,Object>> condition){
            validateTable(tableName);
            List<Map<String,Object>> query = new ArrayList<>();
            for (Map<String,Object> row : tables.get(tableName)){
                if(condition.test(row)){
                    query.add(row);
                }
            }
            return query;
        }
        public List<Map<String ,Object>> selectWithSort(String tableName,Predicate<Map<String,Object>> condition,String sortKey,boolean isAsc){
            List<Map<String, Object>> filter = select(tableName, condition);

            filter.sort((a,b)->{
                Comparable valA = (Comparable) a.get(sortKey);
                Comparable valB = (Comparable) b.get(sortKey);
                return isAsc ? valA.compareTo(valB) : valB.compareTo(valA);
            });
            return filter;
        }

        private void validateTable(String tableName) {
            if(!tables.containsKey(tableName))
                throw new IllegalArgumentException("Tables Already exists!");
        }
    }
    public static void main(String[] args) {
        MysqlOperation mysqlOperation = new MysqlOperation();

        mysqlOperation.createTable("Users");

        Map<String,Object> row1 = new HashMap<>();
        row1.put("id","Monu");
        row1.put("age",23);
        row1.put("city","ranchi");
        mysqlOperation.insert("Users",row1);

        Map<String,Object> row2 = new HashMap<>();
        row2.put("id","Rohit");
        row2.put("age",34);
        row2.put("city","bangalore");
        mysqlOperation.insert("Users",row2);

//        Map<String, Object> select = mysqlOperation.select("Users", row -> row.get("age").equals(23));
//        System.out.println(select);

        Map<String,Object> updatedVal = new HashMap<>();
        updatedVal.put("age",50);

        mysqlOperation.update("Users",row->row.get("id").equals("Monu"),updatedVal);
//        Map<String, Object> select1 = mysqlOperation.select("Users", row -> row.get("id").equals("Monu"));
//        System.out.println(select1);

        mysqlOperation.delete("Users",row->row.get("id").equals("Monu"));
        System.out.println(mysqlOperation.select("Users",row->row.get("id").equals("Monu")));
    }
}
