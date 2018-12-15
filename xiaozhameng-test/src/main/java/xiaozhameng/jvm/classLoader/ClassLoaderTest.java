package xiaozhameng.jvm.classLoader;

import java.io.InputStream;

public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                try {
                    String fileName = name.substring(name.lastIndexOf("."+1)) + ".class";
                    InputStream inputStream = getClass().getResourceAsStream(fileName);

                    if (inputStream == null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[inputStream.available()];
                    inputStream.read(b);
                    return defineClass(name,b,0,b.length);
                }catch (Exception e){

                }

                return super.loadClass(name);
            }
        };
    }

}
