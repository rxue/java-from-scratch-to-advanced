package rx.book.corejava.volume2.ch10;

public class ClassLoaderDemo {
    /**
     * NOTE! This recursively find parent class loader till PlatformClassLoader.
     * Debugger could display the parent of PlatformClassLoader - BootClassLoader, i.e. Bootstrap Class Loader
     *
     * @param args
     */
    public static void main(String[] args) {
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        while (currentClassLoader != null) {
            System.out.println("current level class loader is " + currentClassLoader);
            System.out.println("parent of current level class loader is " + currentClassLoader.getParent());
            currentClassLoader = currentClassLoader.getParent();
        }

    }
}
