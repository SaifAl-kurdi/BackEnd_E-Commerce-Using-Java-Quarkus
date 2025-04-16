package importxmlfile.template;

public abstract class TemplateDesignPattern {

    public final void importxml (byte[] file, String field) throws Exception {
        Object object = init(file, field);
        importXML(object);
//        saveToDatabase(objects);
    }

    protected abstract Object init (byte[] file, String field) throws Exception;
    protected abstract void importXML (Object object) throws Exception;
//    protected abstract List<Object> saveToDatabase (List<Object> objects) throws Exception;
}
