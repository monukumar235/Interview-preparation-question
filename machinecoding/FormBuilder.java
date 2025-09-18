package machinecoding;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FormBuilder {
    public static abstract class Field{
        public String label;
        public boolean required;

        public Field(String label, boolean required) {
            this.label = label;
            this.required = required;
        }

        public abstract Object captureInput(Scanner sc);
        public abstract boolean isValid(Object obj);
    }

    public static class TextField extends Field{
        public TextField(String label, boolean required) {
            super(label, required);
        }

        @Override
        public Object captureInput(Scanner sc) {
            System.out.println(label+ " : ");
            return sc.nextLine();
        }

        @Override
        public boolean isValid(Object obj) {
            return !required || (obj!=null && !obj.toString().isEmpty());
        }
    }
    public static class NumberFeild extends Field{
        public int min;
        public int max;
        public NumberFeild(String label, boolean required,int min,int max) {
            super(label, required);
            this.min=min;
            this.max=max;
        }

        @Override
        public Object captureInput(Scanner sc) {
            System.out.println(label + "(number "+ min + "-" + max + "): ");
            return Integer.parseInt(sc.nextLine());
        }

        @Override
        public boolean isValid(Object obj) {
            int val = (int) obj;
            return val>=min && val<=max;
        }
    }
    public static class DropDown extends Field{
        public List<String> option;

        public DropDown(String label, boolean required, List<String> option) {
            super(label, required);
            this.option = option;
        }

        @Override
        public Object captureInput(Scanner sc) {
            System.out.println(label + " :" + option + ": ");
            return sc.nextLine();
        }

        @Override
        public boolean isValid(Object obj) {
//            String val = (String) obj;
            return option.contains(obj);
        }
    }

    public static class CheckBox extends Field{

        public CheckBox(String label) {
            super(label, false);
        }

        @Override
        public Object captureInput(Scanner sc) {
            System.out.println(label+ "(yes/no): ");
            String ans = sc.nextLine();
            return ans.equalsIgnoreCase("yes");
        }

        @Override
        public boolean isValid(Object obj) {
            return true;
        }
    }


    public static class FormFactory{
        public static Field createField(JsonNode jsonNode){
            String type = jsonNode.get("type").asText();
            String label = jsonNode.get("label").asText();
            boolean required = jsonNode.has("required") ? jsonNode.get("required").asBoolean() : false;

            switch (type){
                case "text" :
                    return  new TextField(label,required);
                case "number":
                    int min = jsonNode.has("min") ? jsonNode.get("min").asInt() : Integer.MIN_VALUE;
                    int max = jsonNode.has("max") ? jsonNode.get("max").asInt() : Integer.MAX_VALUE;
                    return new NumberFeild(label,required,min,max);
                case "dropdown":
                    List<String> options = new ArrayList<>();
                    for (JsonNode opt : jsonNode.get("options")){
                        options.add(opt.asText());
                    }
                    return new DropDown(label,required,options);
                case "checkbox":
                    return new CheckBox(label);
                default:
                    throw new IllegalArgumentException("UnKnow Fields type"+type);
            }
        }
    }

    public static class FormRunner{
        public String title;
        List<Field> fields = new ArrayList<>();

        public FormRunner(String title) {
            this.title = title;
        }

        public void addFeild(Field field){
            fields.add(field);
        }
        public Map<String,Object> renderAndCapture(){
            System.out.println("=========================" +title + "=========================");
            Scanner sc = new Scanner(System.in);
            Object input;
            Map<String,Object> answer = new HashMap<>();
            for (Field field :fields){
                do {
                    input = field.captureInput(sc);
                    if(!field.isValid(input)){
                        throw new IllegalArgumentException("Invalid input!");
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
        String title = jsonNode.get("title").asText();
        FormRunner formRunner = new FormRunner(title);

        for(JsonNode fieldNode : jsonNode.get("fields")){
            formRunner.addFeild(FormFactory.createField(fieldNode));
        }
        Map<String, Object> answer = formRunner.renderAndCapture();

        System.out.println("============================ User Response ===============================");
        answer.forEach((a,b)-> System.out.println(a + " :" + b));
    }
}
