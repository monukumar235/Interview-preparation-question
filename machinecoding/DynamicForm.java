package machinecoding;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import multithreading.OneToN;

import javax.print.attribute.standard.JobSheets;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

public class DynamicForm {
    public static abstract class Fields{
        public String label;
        public boolean required;


        public Fields(String label, boolean required) {
            this.label = label;
            this.required = required;
        }

        public abstract Object captureInput(Scanner sc);
        public abstract boolean isValid(Object obj);
    }

    public static  class TextField extends Fields{
        public TextField(String label,boolean required) {
            super(label,required);
        }
        @Override
        public Object captureInput(Scanner sc) {
            System.out.print(label + ": ");
            return sc.nextLine();
        }

        @Override
        public boolean isValid(Object obj) {

            return !required || (obj!=null && !obj.toString().isEmpty());
        }
    }
    public static class NumberFields extends Fields{
        public int min;
        public int max;

        public NumberFields(String label, boolean required, int min,int max) {
            super(label, required);
            this.min = min;
            this.max=max;
        }

        @Override
        public Object captureInput(Scanner sc) {
            System.out.print(label + " (Min " + min + " max " + max + ") ");
            return Integer.parseInt(sc.nextLine());
        }

        @Override
        public boolean isValid(Object obj) {
            int val = (int)obj;
            return val>=min && val<=max;
        }
    }
    public static class DropDown extends Fields{
        public List<String> options;

        public DropDown(String label, boolean required, List<String> options) {
            super(label, required);
            this.options = options;
        }

        @Override
        public Object captureInput(Scanner sc) {
            System.out.println(label + ":" + options);
            return sc.nextLine();
        }

        @Override
        public boolean isValid(Object obj) {
            return options.contains(obj);
        }
    }
    public static class CheckBox extends Fields{
        public CheckBox(String label) {
            super(label, false);
        }
        @Override
        public Object captureInput(Scanner sc) {
            System.out.println(label + " (yes/no)");
            String ans = sc.nextLine();
            return ans.equalsIgnoreCase("yes");
        }
        @Override
        public boolean isValid(Object obj) {
            return true;
        }
    }

    public static class  FormFactory{
        public static  Fields getFields(JsonNode jsonNode){
            String type = jsonNode.get("type").asText();
            String label = jsonNode.get("label").asText();
            boolean required = jsonNode.has("required") ? jsonNode.get("required").asBoolean():false;

            switch (type){
                case "text":
                    return new TextField(label,required);
                case "number":
                    int min = jsonNode.has("min") ? jsonNode.get("min").asInt() : Integer.MIN_VALUE;
                    int max = jsonNode.has("max") ? jsonNode.get("max").asInt() : Integer.MAX_VALUE;
                    return new NumberFields(label,required,min,max);
                case "dropdown" :
                    List<String> options = new ArrayList<>();
                    for(JsonNode opt : jsonNode.get("options")){
                        options.add(opt.asText());
                    }
                    return new DropDown(label,required,options);
                case "checkbox" :
                    return new CheckBox(label);
                default:
                    throw new IllegalArgumentException("Invalid Fields!");
            }
        }
    }

    public static class Form {
        public String title;

        public Form(String title) {
            this.title = title;
        }

        public List<Fields> fields = new ArrayList<>();

        public void addFields(Fields field){
            fields.add(field);
        }

        public Map<String,Object> capture(){
            Map<String,Object> answer = new HashMap<>();
            System.out.println("=============================================" + title+ "============================================================");
            Scanner sc = new Scanner(System.in);
            Object input;
            for (Fields field : fields){
                do {
                    input=field.captureInput(sc);
                    if(!field.isValid(input)){
                        throw new IllegalArgumentException("Invalid Input!");
                    }
                }while (!field.isValid(input));
                answer.put(field.label,input);
            }
            return answer;
        }


    }
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(new File("C:\\Users\\USER\\OneDrive\\Desktop\\New folder (2)\\machinecoding\\form.json"));
        JsonNode title = jsonNode.get("title");
        Form form = new Form(title.toString());

        for (JsonNode json : jsonNode.get("fields")){
            form.addFields(FormFactory.getFields(json));
        }

        Map<String, Object> answer = form.capture();

        System.out.println("============================================================= User Response=====================================================");
        answer.forEach((a,b)-> System.out.println(a+ "  " +b));

    }
}
