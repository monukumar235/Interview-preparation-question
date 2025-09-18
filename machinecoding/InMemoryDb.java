package machinecoding;

import multithreading.OneToN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class InMemoryDb {
    public  static  class  MysqlOperation{
        Map<String, List<Map<String,Object>>> tables = new HashMap<>();

        public void createTable(String tableName){
            tables.putIfAbsent(tableName,new ArrayList<>());
        }

        public void insert(String tableName,Map<String,Object>row){
            validateTable(tableName);
            tables.get(tableName).add(row);
        }

        public void delete(String tableName , Predicate<Map<String,Object>> condition){
            validateTable(tableName);
            tables.get(tableName).removeIf(condition);
        }
        public void update(String tableName,Predicate<Map<String,Object>>condition,Map<String,Object>updateRow){
            validateTable(tableName);
            for (Map<String,Object> row : tables.get(tableName)){
                if(condition.test(row)){
                    row.putAll(updateRow);
                }
            }
        }

        public List<Map<String,Object>> select(String tableName, Predicate<Map<String,Object>> condition){
            validateTable(tableName);
            List<Map<String,Object>> result = new ArrayList<>();
            for(Map<String,Object> row : tables.get(tableName)){
                if(condition.test(row)){
                    result.add(new HashMap<>(row));
                }
            }
            return  result;
        }
        public List<Map<String,Object>> selectAll(String tableName){
            validateTable(tableName);
            List<Map<String ,Object>> res = new ArrayList<>();
            res.addAll(tables.get(tableName));
            return res;
        }
        private void validateTable(String tableName) {
            if(!tables.containsKey(tableName)){
                throw new IllegalArgumentException("Tables does not exists "+tableName);
            }
        }
    }
    public static void main(String[] args) {

        MysqlOperation mysqlOperation = new MysqlOperation();

        mysqlOperation.createTable("users");

        Map<String,Object> row1 = new HashMap<>();
        row1.put("id",1);
        row1.put("name","monu");
        row1.put("age",10);

        Map<String,Object> row2= new HashMap<>();
        row2.put("id",2);
        row2.put("name","rohit");
        row2.put("age",11);



        mysqlOperation.insert("users",row1);
        mysqlOperation.insert("users",row2);

        List<Map<String, Object>> users = mysqlOperation.selectAll("users");
//        System.out.println("All records "+ users);


        List<Map<String, Object>> query = mysqlOperation.select("users", row -> (int) row.get("age") > 30);
//        System.out.println("User having age greater 30 " + query);

        HashMap<String, Object> updateVale = new HashMap<>();
        updateVale.put("age",25);

        mysqlOperation.update("users",row->row.get("name").equals("monu"),updateVale);
//        System.out.println(mysqlOperation.select("users",row->row.get("name").equals("monu")));

//        System.out.println(mysqlOperation.selectAll("users"));

        mysqlOperation.delete("users",row->row.get("id").equals(2));
//        System.out.println(mysqlOperation.selectAll("users"));
    }
}
