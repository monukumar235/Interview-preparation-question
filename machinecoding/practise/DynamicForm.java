package machinecoding.practise;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DynamicForm {
    public  static abstract class Fields{
        public String label;
        public boolean required;
        Fields(String label,boolean required){
            this.label =label;
            this.required = required;
        }

       abstract public Object captureInput(Scanner sc);
        abstract public boolean isValid(Object obj);
    }

    public static class TextField extends Fields{

        TextField(String label,boolean required){
            super(label,required);
        }
        @Override
        public Object captureInput(Scanner sc) {
            System.out.print(label  + " :" );
            return sc.nextLine();
        }

        @Override
        public boolean isValid(Object obj) {
            return !required || (obj!=null && !obj.toString().isEmpty());
        }
    }
    public static class NumberField extends Fields{
        int max;
        int min;
        NumberField(String label,boolean required,int min,int max){
            super(label,required);
            this.min=min;
            this.max = max;
        }

        @Override
        public Object captureInput(Scanner sc) {
            System.out.println(label+ " ( "+ min + " / " + max + " ) ");
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
        DropDown(String label,boolean required,List<String>options){
            super(label,required);
            this.options=options;
        }

        @Override
        public Object captureInput(Scanner sc) {
            System.out.println(label + " " + options);
            return sc.nextLine();
        }

        @Override
        public boolean isValid(Object obj) {
            return options.contains(obj);
        }
    }
    public static class CheckBox extends Fields{
        CheckBox(String label){
            super(label,false);
        }

        @Override
        public Object captureInput(Scanner sc) {
            System.out.println(label+ " (yes/no)");
            String ans = sc.nextLine();
            return ans.equalsIgnoreCase("yes");
        }

        @Override
        public boolean isValid(Object obj) {
            return true;
        }
    }

    public static  class FormFactory{
        public static Fields getFields(JsonNode jsonNode){
            String type = jsonNode.get("type").asText();
            String label = jsonNode.get("label").asText();
            boolean required =jsonNode.has("required")? jsonNode.get("required").asBoolean() : false;
            switch (type){
                case "text" :
                    return  new TextField(label,required);
                case "number":
                    int min = jsonNode.get("min").asInt();
                    int max = jsonNode.get("max").asInt();
                    return new NumberField(label,required,min,max);
                case "dropdown" :
                    List<String> options = new ArrayList<>();
                    for(JsonNode opt : jsonNode.get("options")){
                        options.add(opt.asText());
                    }
                    return new DropDown(label,required,options);
                case "checkbox" :
                    return new CheckBox(label);
                default:
                    throw new IllegalArgumentException("Invalid fileds Please try again!"+type);
            }
        }
    }
    public static class FormBuilder{
        String title;
        List<Fields> fields=new ArrayList<>();
        FormBuilder(String title){
            this.title=title;
        }
        public void addFields(Fields field){
            fields.add(field);
        }

        public Map<String,Object> renderAndCapture(){
            Map<String,Object> answer = new HashMap<>();
            System.out.println("================" + title+"=============================");
            Scanner sc = new Scanner(System.in);
            Object input;
            for(Fields field : fields){
                do {
                    input =field.captureInput(sc);
                    if(!field.isValid(input))
                    {
                        throw new IllegalArgumentException("Invalid Input!" + input);
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
        FormBuilder formBuilder = new FormBuilder(title.toString());

        for(JsonNode node : jsonNode.get("fields")){
            formBuilder.addFields(FormFactory.getFields(node));
        }

        Map<String, Object> output = formBuilder.renderAndCapture();
        System.out.println("============================ User Response =================================");
        output.forEach((a,b)-> System.out.println(a + " "+ b));
    }
}
