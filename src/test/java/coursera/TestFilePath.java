package coursera;

public class TestFilePath {
    private ClassLoader classLoader = this.getClass().getClassLoader();
    private String packagePrefix;

    public TestFilePath(String packagePrefix) {
        this.packagePrefix = packagePrefix;
    }

    @SuppressWarnings("ConstantConditions")
    public String file(String fileName) {
        String fullName = packagePrefix + "/" + fileName;
        return classLoader.getResource(fullName).getFile();
    }
}
